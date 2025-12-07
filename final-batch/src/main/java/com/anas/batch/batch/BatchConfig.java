package com.anas.batch.batch;

import com.anas.batch.config.FactureProcessor;
import com.anas.batch.config.FactureReader;
import com.anas.batch.config.FactureWriter;
import com.anas.batch.models.Facture;
import com.anas.batch.models.FactureDto;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BatchConfig {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private FactureReader factureReader;

    @Autowired
    private FactureProcessor factureProcessor;

    @Autowired
    private FactureWriter factureWriter;

    @Autowired
    private DataSource dataSource;

    // /Configuration manuelle du JobRepository
//    @Bean
//    public JobRepository jobRepository() throws Exception {
//        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
//        factory.setDataSource(dataSource);
//        factory.setTransactionManager(transactionManager);
//        factory.setIsolationLevelForCreate("ISOLATION_DEFAULT");
//        factory.setTablePrefix("BATCH_");
//        factory.setMaxVarCharLength(1000);
//        factory.afterPropertiesSet();
//        return factory.getObject();
//    }

    @Bean
    public Step stepFacture() throws Exception {
        return new StepBuilder("stepFacture", jobRepository)
                .<FactureDto, Facture>chunk(2, transactionManager) // Chunk size de 2 comme demandé dans le TP
                .reader(factureReader.itemReader())
                .processor(factureProcessor)
                .writer(factureWriter.itemWriter(dataSource))
                .build();
    }

    @Bean
    public Job jobFacture() throws Exception { // Le nom doit correspondre à l'injection
        return new JobBuilder("jobFacture", jobRepository)
                .start(stepFacture())
                .build();
    }
}