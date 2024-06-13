package com.batch.process.writer;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.batch.process.dto.EmployeeDTO;

@Configuration
public class EmployeeWriter {

	private final DataSource dataSource;

	public EmployeeWriter(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Bean
	public JdbcBatchItemWriter<EmployeeDTO> writer() {
		return new JdbcBatchItemWriterBuilder<EmployeeDTO>().sql(
				"INSERT INTO employee (first_name, last_name, email_id, address) VALUES (:firstName, :lastName, :emailId, :address)")
				.dataSource(dataSource).build();
	}
}
