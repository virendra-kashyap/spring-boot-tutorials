package com.boot.graph.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.graph.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
