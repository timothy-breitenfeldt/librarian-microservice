package com.smoothstack.lms.librarianservice.controller;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smoothstack.lms.librarianservice.dao.AuthorDAO;
import com.smoothstack.lms.librarianservice.dao.BookCopyDAO;
import com.smoothstack.lms.librarianservice.dao.BookDAO;
import com.smoothstack.lms.librarianservice.dao.GenreDAO;
import com.smoothstack.lms.librarianservice.dao.LibraryBranchDAO;
import com.smoothstack.lms.librarianservice.service.LibrarianService;

@SpringBootTest
@AutoConfigureMockMvc
public class LibrarianControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private AuthorDAO authorDAO;

    @Autowired
    private GenreDAO genreDAO;

    @Autowired
    private BookCopyDAO bookCopyDAO;

    @Autowired
    private LibraryBranchDAO libraryBranchDAO;

    @Autowired
    private LibrarianService librarianService;

    @Autowired
    private LibrarianController librarianController;

    @Test
    public void testCreateBookCopy() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetBooksNotInBookCopies() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetLibraryBranches() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/lms/librarian/branches").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetLibraryBranchById() throws Exception {
        int branchId = 1;

        mockMvc.perform(MockMvcRequestBuilders.get("/lms/librarian/branches/{branchId}", branchId)
                .accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(branchId));
    }

    @Test
    public void testGetBookCopies() {
        fail("Not yet implemented");
    }

    @Test
    public void testUpdateBookCopy() {
        fail("Not yet implemented");
    }

    @Test
    public void testUpdateLibraryBranch() {
        fail("Not yet implemented");
    }

    @Test
    public void testRemoveBookCopy() {
        fail("Not yet implemented");
    }

}
