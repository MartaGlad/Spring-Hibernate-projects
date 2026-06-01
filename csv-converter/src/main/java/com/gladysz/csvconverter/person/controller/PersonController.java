package com.gladysz.csvconverter.person.controller;

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
@RequestMapping("csv-converter/person")
public class PersonController {

    private final Job personAgeJob;
    private final JobLauncher jobLauncher;

    public PersonController(Job personAgeJob, JobLauncher jobLauncher) {
        this.personAgeJob = personAgeJob;
        this.jobLauncher = jobLauncher;
    }


    @PostMapping(value = "/run")
    public ResponseEntity<String> run() {

        JobExecution execution;

        try {
            JobParameters parameters = new JobParametersBuilder()
                .addLong("timestamp", System.currentTimeMillis())
                .toJobParameters();

            execution = jobLauncher.run(personAgeJob, parameters);

        } catch (JobExecutionException e) {

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to execute job " + e.getMessage());
        }
        return ResponseEntity.ok(execution.getStatus().toString());
    }
}
