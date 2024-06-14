package com.batch.process.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.batch.process.listener.JobCompletionNotificationListener;
import com.batch.process.model.Person;
import com.batch.process.processor.PersonItemProcessor;
import com.batch.process.writer.PersonItemWriter;

@Configuration
public class BatchConfiguration {

	@Autowired
	private DataSource dataSource;

	@Bean
	public PersonItemProcessor processor() {
		return new PersonItemProcessor();
	}

	@Bean
	public DataSourceTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean
	public Job importUserJob(JobRepository jobRepository, Step step1, JobCompletionNotificationListener listener) {
		return new JobBuilder("importUserJob", jobRepository).listener(listener).start(step1).build();
	}

	@Bean
	public Step step1(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
			FlatFileItemReader<Person> reader, PersonItemProcessor processor, PersonItemWriter writer) {
		return new StepBuilder("step1", jobRepository).<Person, Person>chunk(10, transactionManager).reader(reader)
				.processor(processor).writer(writer).build();
	}

	@Bean
	public PersonItemWriter personItemWriter() {
		return new PersonItemWriter(dataSource);
	}

}
