package com.batch.process.writer;

import javax.sql.DataSource;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.batch.process.model.Person;

@Configuration
public class PersonItemWriter implements ItemWriter<Person> {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public PersonItemWriter(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void write(Chunk<? extends Person> chunk) throws Exception {
		for (Person person : chunk) {
			int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM people WHERE email_id = ?",
					new Object[] { person.getEmailId() }, Integer.class);

			if (count > 0) {
				jdbcTemplate.update("UPDATE people SET first_name = ?, last_name = ? WHERE email_id = ?",
						person.getFirstName(), person.getLastName(), person.getEmailId());
			} else {
				jdbcTemplate.update("INSERT INTO people (first_name, last_name, email_id) VALUES (?, ?, ?)",
						person.getFirstName(), person.getLastName(), person.getEmailId());
			}
		}
	}
}
