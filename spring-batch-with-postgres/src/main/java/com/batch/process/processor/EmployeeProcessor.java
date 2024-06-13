package com.batch.process.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;

import com.batch.process.dto.EmployeeDTO;

@Configuration
public class EmployeeProcessor implements ItemProcessor<EmployeeDTO, EmployeeDTO> {

	@Override
	public EmployeeDTO process(EmployeeDTO item) throws Exception {
		return item;
	}

}
