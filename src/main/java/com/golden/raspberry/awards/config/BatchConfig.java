
package com.golden.raspberry.awards.config;

import com.golden.raspberry.awards.model.Award;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
     
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
 
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
 
    @Value("classPath:/input/movielist.csv")
    private Resource inputResource;
 
    @Bean
    public Job readCSVFileJob() {
        return jobBuilderFactory
                .get("readCSVFileJob")
                .incrementer(new RunIdIncrementer())
                .start(step())
                .build();
    }
 
    @Bean
    public Step step() {
        return stepBuilderFactory
                .get("step")
                .<Award, Award>chunk(5)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
     
    @Bean
    public ItemProcessor<Award, Award> processor() {
        return new DBLogProcessor();
    }
     
    @Bean
    public FlatFileItemReader<Award> reader() {
        FlatFileItemReader<Award> itemReader = new FlatFileItemReader<Award>();
        itemReader.setLineMapper(lineMapper());
        itemReader.setLinesToSkip(1);
        itemReader.setResource(inputResource);
        return itemReader;
    }
 
    @Bean
    public LineMapper<Award> lineMapper() {
        DefaultLineMapper<Award> lineMapper = new DefaultLineMapper<Award>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames(new String[] {"year","title", "studios", "producers", "winner" });
        lineTokenizer.setIncludedFields(new int[] { 0, 1, 2, 3, 4});
        lineTokenizer.setDelimiter(";");
        BeanWrapperFieldSetMapper<Award> fieldSetMapper = new BeanWrapperFieldSetMapper<Award>();
        fieldSetMapper.setTargetType(Award.class);
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }
 
    @Bean
    public JdbcBatchItemWriter<Award> writer() {
        JdbcBatchItemWriter<Award> itemWriter = new JdbcBatchItemWriter<Award>();
        itemWriter.setDataSource(dataSource());
        itemWriter.setSql("INSERT INTO AWARD (YEAR, TITLE, STUDIOS, PRODUCERS, WINNER) VALUES (:year, :title, :studios , :producers , :winner )");
        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Award>());
        return itemWriter;
    }

    @Bean
    public DataSource dataSource(){
        EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
        return embeddedDatabaseBuilder.addScript("classpath:org/springframework/batch/core/schema-drop-h2.sql")
                .addScript("classpath:org/springframework/batch/core/schema-h2.sql")
                .addScript("classpath:award.sql")
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }




}
