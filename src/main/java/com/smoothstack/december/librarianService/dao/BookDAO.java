package com.smoothstack.december.librarianService.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.december.librarianService.entity.Book;

@Repository
public interface BookDAO extends JpaRepository<Book, Long> {

}
