package com.anas.batch.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class JobLauncherService {

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    @Qualifier("jobFacture") // Spécifiez le nom du bean
    private Job jobFacture;

    public void launchJob() throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addLong("run.id", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(jobFacture, params);
    }
}
