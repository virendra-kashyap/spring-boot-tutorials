package com.boot.graph.service;

import java.util.List;

import com.boot.graph.model.Book;

public interface BookService {

	public Book create(Book book);

	public List<Book> getAll();

	public Book getId(long id);

}