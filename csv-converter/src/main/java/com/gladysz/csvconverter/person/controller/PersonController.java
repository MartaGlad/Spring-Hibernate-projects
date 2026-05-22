package com.gladysz.csvconverter.person.controller;

import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.JobExecutionException;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/person")
public class PersonController {

    private final Job personAgeJob;
    private final JobLauncher jobLauncher;

    public PersonController(Job personAgeJob, JobLauncher jobLauncher) {
        this.personAgeJob = personAgeJob;
        this.jobLauncher = jobLauncher;
    }


    @PostMapping(value = "/run")
    public ResponseEntity<Void> run() throws JobExecutionException {

        JobParameters parameters = new JobParametersBuilder()
                .addLong("timestamp", System.currentTimeMillis())
                .toJobParameters();

        jobLauncher.run(personAgeJob, parameters);

        return ResponseEntity.accepted().build();
    }
}
