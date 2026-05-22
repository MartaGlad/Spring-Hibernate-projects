package com.gladysz.csvconverter.person.batch;

import com.gladysz.csvconverter.person.domain.PersonInput;
import com.gladysz.csvconverter.person.domain.PersonOutput;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.parameters.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.batch.infrastructure.item.ItemReader;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.batch.infrastructure.item.file.FlatFileItemReader;
import org.springframework.batch.infrastructure.item.file.FlatFileItemWriter;
import org.springframework.batch.infrastructure.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.infrastructure.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.infrastructure.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.infrastructure.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.infrastructure.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.infrastructure.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.infrastructure.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Configuration
@EnableBatchProcessing
public class PersonBatchConfiguration {

    @Bean
    FlatFileItemReader<PersonInput> personReader() {

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter(","); // "," is also default
        tokenizer.setNames("firstName", "lastName", "birthDate");

        BeanWrapperFieldSetMapper<PersonInput> mapper = new BeanWrapperFieldSetMapper<>();
        mapper.setTargetType(PersonInput.class);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");

        DefaultLineMapper<PersonInput> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSet ->
                new PersonInput(fieldSet.readString("firstName"),
                        fieldSet.readString("lastName"),
                        LocalDate.parse(Objects.requireNonNull(fieldSet.readString("birthDate")), formatter)));

        return new FlatFileItemReaderBuilder<PersonInput>()
                .name("personInputReader")
                .resource(new ClassPathResource("person/personInput.csv"))
                .lineMapper(lineMapper)
                .build();
    }


    @Bean
    PersonProcessor personProcessor() {

        return new PersonProcessor();
    }


    @Bean
    FlatFileItemWriter<PersonOutput> personWriter() {

        BeanWrapperFieldExtractor<PersonOutput> extractor = new BeanWrapperFieldExtractor<>();
        extractor.setNames(new String[] {"firstName", "lastName", "age"});

        DelimitedLineAggregator<PersonOutput> aggregator = new DelimitedLineAggregator<>();
        aggregator.setDelimiter(",");
        aggregator.setFieldExtractor(extractor);

        return new FlatFileItemWriterBuilder<PersonOutput>()
                .name("personWriter")
                .resource(new FileSystemResource("csv-converter/src/main/resources/person/personOutput.csv"))
                .shouldDeleteIfExists(true)
                .lineAggregator(aggregator)
                .build();
    }


    @Bean
    Step personAgeStep (
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            ItemReader<PersonInput> reader,
            ItemProcessor<PersonInput, PersonOutput> processor,
            ItemWriter<PersonOutput> writer
    ) {
        return new StepBuilder("personAgeStep", jobRepository)
                .<PersonInput,PersonOutput> chunk(100, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }


    @Bean
    Job personAgeJob(JobRepository jobRepository, Step personAgeStep) {

        return new JobBuilder("personAgeJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .flow(personAgeStep)
                .end()
                .build();
    }
}


