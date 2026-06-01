package com.gladysz.fileintegration.monitor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileHeaders;
import org.springframework.integration.file.inbound.FileReadingMessageSource;
import org.springframework.integration.file.outbound.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;

import java.io.File;

@Configuration
public class MonitorIntegrationConfiguration {

    @Value("${app.paths.monitor-observed}")
    private String monitorObservedPath;

    @Value("${app.paths.monitor-result}")
    private String monitorResultPath;

    @Value("${app.paths.monitor-result-simple}")
    private String monitorResultSimplePath;

    @Bean
    @Profile("append")
    IntegrationFlow monitorFlow(FileReadingMessageSource monitorFileAdapter,
                                FileWritingMessageHandler monitorOutputHandler) {

        return IntegrationFlow.from(monitorFileAdapter, config -> config.poller(Pollers.fixedDelay(1000)))
                .transform(File.class, File::getName)
                .handle(monitorOutputHandler)
                .get();
    }


    @Bean
    @Profile("simple")
    IntegrationFlow monitorSimpleFlow(FileReadingMessageSource monitorFileAdapter,
                                      FileWritingMessageHandler monitorSimpleOutputHandler) {

        return IntegrationFlow.from(monitorFileAdapter, config -> config.poller(Pollers.fixedDelay(1000)))
                .enrichHeaders(h -> h.headerExpression(FileHeaders.FILENAME, "payload.name"))
                .transform(file -> "")
                .handle(monitorSimpleOutputHandler)
                .get();
    }


    @Bean
    @Profile({"simple", "append"})
    FileReadingMessageSource monitorFileAdapter() {

        FileReadingMessageSource fileSource = new FileReadingMessageSource();
        fileSource.setDirectory(new File(monitorObservedPath));

        return fileSource;
    }


    @Bean
    @Profile("append")
    FileWritingMessageHandler monitorOutputHandler() {

        File directory = new File(monitorResultPath);
        FileWritingMessageHandler handler = new FileWritingMessageHandler(directory);

        handler.setFileNameGenerator(message -> "observed-files.txt");
        handler.setFileExistsMode(FileExistsMode.APPEND);

        handler.setAppendNewLine(true);
        handler.setExpectReply(false);

        return handler;
    }


    @Bean
    @Profile("simple")
    FileWritingMessageHandler monitorSimpleOutputHandler() {

        File directory = new File(monitorResultSimplePath);
        FileWritingMessageHandler handler = new FileWritingMessageHandler(directory);

        handler.setExpectReply(false);

        return handler;
    }
}


