package com.gladysz.csvconverter.product.controller;

import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.JobExecution;
import org.springframework.batch.core.job.JobExecutionException;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("csv-converter/product")
public class ProductController {

    private final Job changePriceJob;
    private final JobLauncher jobLauncher;

    public ProductController(Job changePriceJob, JobLauncher jobLauncher) {
        this.changePriceJob = changePriceJob;
        this.jobLauncher = jobLauncher;
    }


    @PostMapping("/run")
    public ResponseEntity<String> run() {

        try {
            JobParameters parameters = new JobParametersBuilder()
                    .addLong("timestamp", System.currentTimeMillis())
                    .toJobParameters();

            JobExecution execution = jobLauncher.run(changePriceJob, parameters);

            return ResponseEntity.ok(execution.getStatus().toString());

        } catch (JobExecutionException e) {

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to execute job: " + e.getMessage());
        }
    }
}

