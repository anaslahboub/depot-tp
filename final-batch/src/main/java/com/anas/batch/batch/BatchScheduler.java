package com.anas.batch.batch;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class BatchScheduler {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private JobLauncherService jobLauncherService;

    // Exécution toutes les 30 secondes (aux secondes 0 et 30 de chaque minute)
    @Scheduled(cron = "0,30 * * * * *")
    public void scheduleBatchExecution() {
        try {
            System.out.println("=== Démarrage planifié du batch Spring ===");
            jobLauncherService.launchJob();
            System.out.println("=== Batch Spring terminé avec succès ===");
        } catch (Exception e) {
            System.err.println("=== Erreur lors de l'exécution du batch : " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Alternative : Exécution toutes les minutes
    @Scheduled(cron = "0 * * * * *")
    public void scheduleBatchEveryMinute() {
        try {
            System.out.println("=== Démarrage planifié (toutes les minutes) ===");
            jobLauncherService.launchJob();
            System.out.println("=== Batch terminé avec succès ===");
        } catch (Exception e) {
            System.err.println("=== Erreur lors de l'exécution du batch : " + e.getMessage());
        }
    }

    // Exécution toutes les 5 minutes
    @Scheduled(cron = "0 */5 * * * *")
    public void scheduleBatchEvery5Minutes() {
        try {
            System.out.println("=== Démarrage planifié (toutes les 5 minutes) ===");
            jobLauncherService.launchJob();
            System.out.println("=== Batch terminé avec succès ===");
        } catch (Exception e) {
            System.err.println("=== Erreur lors de l'exécution du batch : " + e.getMessage());
        }
    }
}