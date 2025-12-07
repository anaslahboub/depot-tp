package com.anas.tpspringbatch.controller;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller optionnel pour lancer le job manuellement via une API REST
 * Accessible via : http://localhost:8080/batch/run
 */
@RestController
@RequestMapping("/batch")
public class JobLauncherController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job jobFacturation;

    @GetMapping("/run")
    public String runJob() {
        try {
            // Ajouter un paramètre timestamp pour permettre plusieurs exécutions
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("timestamp", System.currentTimeMillis())
                    .toJobParameters();

            jobLauncher.run(jobFacturation, jobParameters);
            return "Batch Job lancé avec succès !";
        } catch (Exception e) {
            return "Erreur lors du lancement du batch : " + e.getMessage();
        }
    }
}