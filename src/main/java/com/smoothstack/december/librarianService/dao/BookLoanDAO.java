package com.smoothstack.december.librarianService.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.december.librarianService.entity.BookLoan;
import com.smoothstack.december.librarianService.entity.BookLoan.BookLoanId;

@Repository
public interface BookLoanDAO extends JpaRepository<BookLoan, BookLoanId> {

}
