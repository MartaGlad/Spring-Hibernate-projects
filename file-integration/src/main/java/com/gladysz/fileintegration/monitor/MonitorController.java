package com.gladysz.fileintegration.monitor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;


@RestController
@RequestMapping("/file-integration/monitor")
public class MonitorController {

    @Value("${app.paths.monitor-observed}")
    private String monitorObservedPath;

    @Value("${app.paths.monitor-result}")
    private String monitorResultPath;

    @Value("${app.paths.monitor-result-simple}")
    private String monitorResultSimplePath;


    @PostMapping("/create")
    public ResponseEntity<String> createFile(@RequestParam String fileName, @RequestParam String content) {

        try {
            Path path = Paths.get(monitorObservedPath).resolve(fileName);
            Files.writeString(path, content);
            return ResponseEntity.ok("File created: " + fileName);

        } catch (IOException e) {
            return ResponseEntity
                    .internalServerError()
                    .body("Failed to create file " + e.getMessage());
        }
    }


    @GetMapping("/results")
    public ResponseEntity<String> getResults() {

        Path resultFile = Paths.get(monitorResultPath).resolve("observed-files.txt");

        if (Files.notExists(resultFile)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No observed files yet");
        }

        try {
            return ResponseEntity
                    .ok(Files.readString(resultFile));

        } catch (IOException e) {
            return ResponseEntity
                    .internalServerError()
                    .body("Failed to read file " + e.getMessage());
        }
    }


    @GetMapping("/results/simple")
    public ResponseEntity<List<String>> getSimpleResults() {

        Path simpleResultPath = Paths.get(monitorResultSimplePath);

        if (Files.notExists(simpleResultPath)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonList("Simple result directory does not exist"));
        }

        try (Stream<Path> paths = Files.list(simpleResultPath)) {

            List<String> fileNames = paths.map(path -> path.getFileName().toString()).toList();

            if (fileNames.isEmpty()) {
                return ResponseEntity.ok(Collections.emptyList());
            }

            return ResponseEntity.ok(fileNames);

        }  catch (IOException e) {
            return ResponseEntity
                    .internalServerError()
                    .body(Collections.singletonList("Failed to get file names " + e.getMessage()));
        }
    }
}
