package com.batch.process.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.batch.process.dto.EmployeeDTO;
import com.batch.process.listener.JobCompletionNotificationListener;
import com.batch.process.processor.EmployeeProcessor;
import com.batch.process.reader.EmployeeReader;
import com.batch.process.writer.EmployeeWriter;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	public EmployeeReader employeeReader;

	@Autowired
	public EmployeeProcessor employeeProcessor;

	@Autowired
	public EmployeeWriter employeeWriter;

	@Autowired
	public JobCompletionNotificationListener listener;

	@Bean
	public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManage) {
		var step = new StepBuilder("step1", jobRepository).<EmployeeDTO, EmployeeDTO>chunk(5, transactionManage)
				.reader(employeeReader.employeeItemReader()).processor(employeeProcessor).writer(employeeWriter.writer()).build();
		return step;
	}

	@Bean
	public Job productJob(JobRepository jobRepository, @Qualifier("step1") Step step1) {
		return new JobBuilder("importUserJob", jobRepository).incrementer(new RunIdIncrementer()).start(step1).build();
	}

//	@Bean
//	public Job importUserJob(JobRepository jobRepository, PlatformTransactionManager transactionManage) {
//		return new JobBuilder("importUserJob", jobRepository).start(step1(jobRepository, transactionManage)).build();
//	}

//	@Bean
//	public PlatformTransactionManager transactionManager(DataSource dataSource) {
//	    return new DataSourceTransactionManager(dataSource);
//	}
//
//	@Bean
//	public FlatFileItemReader<EmployeeDTO> reader() {
//		return new FlatFileItemReaderBuilder<EmployeeDTO>().name("employeeItemReader")
//				.resource(new ClassPathResource("employee.csv")).delimited()
//				.names("firstName", "lastName", "emailId", "address").targetType(EmployeeDTO.class).build();
//	}
//
//	@Bean
//	public EmployeeProcessor processor() {
//		return new EmployeeProcessor();
//	}
//
//	@Bean
//	public JdbcBatchItemWriter<EmployeeDTO> writer(DataSource dataSource) {
//		return new JdbcBatchItemWriterBuilder<EmployeeDTO>().sql(
//				"INSERT INTO employee (first_name, last_name, email_id, address) VALUES (:firstName, :lastName, :emailId, :address)")
//				.dataSource(dataSource).beanMapped().build();
//	}
//
//	@Bean
//	public Job importUserJob(JobRepository jobRepository, Step step1) {
//		return new JobBuilder("importUserJob", jobRepository).start(step1).build();
//	}
//
//	@Bean
//	public Step step1(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
//			FlatFileItemReader<EmployeeDTO> reader, EmployeeProcessor processor,
//			JdbcBatchItemWriter<EmployeeDTO> writer) {
//		return new StepBuilder("step1", jobRepository).<EmployeeDTO, EmployeeDTO>chunk(3, transactionManager)
//				.reader(reader).processor(processor).writer(writer).build();
//	}
}
