package com.smoothstack.december.librarianService.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.december.librarianService.entity.BookCopy;

@Repository
public interface BookCopyDAO extends JpaRepository<BookCopy, Long> {

}
