package com.anas.tpspringbatch.config;


import com.anas.tpspringbatch.batch.FactureItemProcessor;
import com.anas.tpspringbatch.model.Facture;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;
import jakarta.persistence.EntityManagerFactory;

@Configuration
public class BatchConfiguration {

    // ========== READER ==========
    @Bean
    public FlatFileItemReader<Facture> factureReader() {
        FlatFileItemReader<Facture> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("input/factures.csv"));
        reader.setLinesToSkip(1); // Ignorer la ligne d'en-tête

        // Configuration du LineMapper
        DefaultLineMapper<Facture> lineMapper = new DefaultLineMapper<>();

        // Tokenizer pour découper les lignes CSV
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames("montantHT", "tauxTVA");
        tokenizer.setDelimiter(",");

        // FieldSetMapper pour mapper vers l'objet Facture
        BeanWrapperFieldSetMapper<Facture> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Facture.class);

        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        reader.setLineMapper(lineMapper);

        return reader;
    }

    // ========== PROCESSOR ==========
    // Déjà défini comme @Component dans FactureItemProcessor

    // ========== WRITER ==========
    @Bean
    public JpaItemWriter<Facture> factureWriter(EntityManagerFactory entityManagerFactory) {
        JpaItemWriter<Facture> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }

    // ========== STEP ==========
    @Bean
    public Step stepCalculFactures(JobRepository jobRepository,
                                   PlatformTransactionManager transactionManager,
                                   FlatFileItemReader<Facture> reader,
                                   FactureItemProcessor processor,
                                   JpaItemWriter<Facture> writer) {
        return new StepBuilder("stepCalculFactures", jobRepository)
                .<Facture, Facture>chunk(2, transactionManager) // Chunk size de 2
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    // ========== JOB ==========
    @Bean
    public Job jobFacturation(JobRepository jobRepository, Step stepCalculFactures) {
        return new JobBuilder("jobFacturation", jobRepository)
                .start(stepCalculFactures)
                .build();
    }
}