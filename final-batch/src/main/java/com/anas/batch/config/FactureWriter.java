package com.anas.batch.config;

import com.anas.batch.models.Facture;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FactureWriter {

    @Bean
    public JdbcBatchItemWriter<Facture> itemWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Facture>()
                .sql("INSERT INTO facture (id, montantHT, tauxTVA, montantTTC) VALUES (:id, :montantHT, :tauxTVA, :montantTTC)")
                .beanMapped()
                .dataSource(dataSource)
                .build();
    }
}
