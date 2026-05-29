package com.gladysz.fileintegration.monitor;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;


@RestController
@RequestMapping("/file-integration/monitor")
public class MonitorController {

    @PostMapping("/create")
    public String createFile(@RequestParam String fileName, @RequestParam String content) throws IOException {

        Path path = Paths.get("file-integration/data/monitor/observed").resolve(fileName);
        Files.writeString(path, content);

        return "File created: " + fileName;
    }


    @GetMapping("/results")
    public String getResults() throws IOException {

        return Files.readString(Paths.get("file-integration/data/monitor/result/observed-files.txt"));
    }


    @GetMapping("/results/simple")
    public List<String> getSimpleResults() throws IOException {

        try (Stream<Path> paths = Files.list(Paths.get("file-integration/data/monitor/result/simple"))) {

            return paths
                    .map(path -> path.getFileName().toString())
                    .toList();
        }
    }
}
