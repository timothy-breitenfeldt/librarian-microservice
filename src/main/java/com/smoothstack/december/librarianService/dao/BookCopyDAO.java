package com.smoothstack.december.librarianService.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smoothstack.december.librarianService.entity.BookCopy;
import com.smoothstack.december.librarianService.entity.BookCopy.BookCopyId;

@Repository
public interface BookCopyDAO extends JpaRepository<BookCopy, BookCopyId> {

    @Query("FROM BookCopy BC WHERE BC.bookCopyId.branch.branchId = :branchId")
    public List<BookCopy> findBookCopiesById(@Param("branchId") Long branchId);

}
