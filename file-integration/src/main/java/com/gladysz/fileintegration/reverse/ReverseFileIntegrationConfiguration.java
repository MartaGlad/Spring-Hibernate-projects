package com.gladysz.fileintegration.reverse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.inbound.FileReadingMessageSource;
import org.springframework.integration.file.outbound.FileWritingMessageHandler;

import java.io.File;



@Configuration
@Profile("reverse")
public class ReverseFileIntegrationConfiguration {

    @Value("${app.paths.reverse-input}")
    private String reverseInputPath;

    @Value("${app.paths.reverse-output}")
    private String reverseOutputPath;


    @Bean
    IntegrationFlow fileIntegrationFlow(FileReadingMessageSource reverseFileAdapter,
                                        FileTransformer transformer,
                                        FileWritingMessageHandler reverseOutputHandler) {

        return IntegrationFlow.from(reverseFileAdapter, config -> config.poller(Pollers.fixedDelay(1000)))
                .transform(transformer, "transformFile")
                .handle(reverseOutputHandler)
                .get();
    }


    @Bean
    FileReadingMessageSource reverseFileAdapter() {

        FileReadingMessageSource fileSource = new FileReadingMessageSource();
        fileSource.setDirectory(new File(reverseInputPath));

        return fileSource;
    }


    @Bean
    FileTransformer transformer() {

        return new FileTransformer();
    }


    @Bean
    FileWritingMessageHandler reverseOutputHandler() {
        File directory = new File(reverseOutputPath);
        FileWritingMessageHandler handler = new FileWritingMessageHandler(directory);
        handler.setExpectReply(false);

        return handler;
    }
}
