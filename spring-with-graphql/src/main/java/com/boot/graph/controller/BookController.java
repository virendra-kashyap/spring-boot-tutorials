package com.boot.graph.controller;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.boot.graph.model.Book;
import com.boot.graph.service.BookService;

@Controller
public class BookController {

	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	public Book create(@RequestBody Book book) {
		return this.bookService.create(book);
	}

	@QueryMapping("allBooks")
	public List<Book> getAll() {
		return this.bookService.getAll();
	}

	@QueryMapping("getBook")
	public Book get(@Argument long id) {
		return this.bookService.getId(id);
	}

}
