package com.anas.batch.config;

import com.anas.batch.models.FactureDto;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class FactureReader {

    @Bean
    public FlatFileItemReader<FactureDto> itemReader() {
        FlatFileItemReader<FactureDto> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("input/factures.csv"));
        reader.setLinesToSkip(1);

        // Mapping CSV columns to DTO fields
        DefaultLineMapper<FactureDto> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(new DelimitedLineTokenizer() {{
            setNames("id", "montantHT", "tauxTVA");
            setDelimiter(",");
        }});
        BeanWrapperFieldSetMapper<FactureDto> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(FactureDto.class);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        reader.setLineMapper(lineMapper);

        return reader;
    }
}
