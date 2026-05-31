package com.gladysz.csvconverter.product.batch;

import com.gladysz.csvconverter.product.domain.Product;
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
import org.springframework.batch.infrastructure.item.file.transform.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.batch.infrastructure.item.file.transform.DelimitedLineTokenizer;


@Configuration
@EnableBatchProcessing
public class ProductBatchConfiguration {

    @Bean
    FlatFileItemReader<Product> reader() {

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter(",");
        tokenizer.setNames("id", "quantity", "price");

        BeanWrapperFieldSetMapper<Product> mapper = new BeanWrapperFieldSetMapper<>();
        mapper.setTargetType(Product.class);

        DefaultLineMapper<Product> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(mapper);


        return new FlatFileItemReaderBuilder<Product>()
                .name("productReader")
                .resource(new ClassPathResource("product/productInput.csv"))
                .lineMapper(lineMapper)
                .build();
    }


    @Bean
    ProductProcessor processor() {

        return new ProductProcessor();
    }


    @Bean
    FlatFileItemWriter<Product> writer() {

        BeanWrapperFieldExtractor<Product> extractor = new BeanWrapperFieldExtractor<>();
        extractor.setNames(new String[] {"id", "quantity", "price"});

        DelimitedLineAggregator<Product> aggregator = new DelimitedLineAggregator<>();
        aggregator.setDelimiter(",");
        aggregator.setFieldExtractor(extractor);

        return new FlatFileItemWriterBuilder<Product>()
                .name("productWriter")
                .resource(new FileSystemResource("csv-converter/output/productOutput.csv"))
                .shouldDeleteIfExists(true)
                .lineAggregator(aggregator)
                .build();
    }


    @Bean
    Step priceChangeStep(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            ItemReader<Product> reader,
            ItemProcessor<Product, Product> processor,
            ItemWriter<Product> writer
    ) {
        return new StepBuilder("priceChange", jobRepository)
                .<Product, Product> chunk (50, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }


    @Bean
    Job changePriceJob(JobRepository jobRepository, Step priceChangeStep) {

        return new JobBuilder("changePriceJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .flow(priceChangeStep)
                .end()
                .build();
    }
}
