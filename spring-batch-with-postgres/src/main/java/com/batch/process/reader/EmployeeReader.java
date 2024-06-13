package com.batch.process.reader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.batch.process.dto.EmployeeDTO;

@Configuration
public class EmployeeReader {

	@Bean
	public FlatFileItemReader<EmployeeDTO> employeeItemReader() {
		return new FlatFileItemReaderBuilder<EmployeeDTO>().name("employeeItemReader")
				.resource(new ClassPathResource("employee.csv")).delimited()
				.names(new String[] { "firstName", "lastName", "emailId", "address" })
				.fieldSetMapper(new BeanWrapperFieldSetMapper<EmployeeDTO>() {
					{
						setTargetType(EmployeeDTO.class);
					}
				}).build();
	}

}