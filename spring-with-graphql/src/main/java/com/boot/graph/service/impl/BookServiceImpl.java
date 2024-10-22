package com.boot.graph.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boot.graph.model.Book;
import com.boot.graph.repository.BookRepository;
import com.boot.graph.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;

	public BookServiceImpl(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Override
	public Book create(Book book) {
		return this.bookRepository.save(book);
	}

	@Override
	public List<Book> getAll() {
		return this.bookRepository.findAll();
	}

	@Override
	public Book getId(long id) {
		return this.bookRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Book you are looking for not found on server !!"));
	}
}