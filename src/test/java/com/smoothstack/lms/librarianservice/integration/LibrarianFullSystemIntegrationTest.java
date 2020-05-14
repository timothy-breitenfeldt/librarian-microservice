package com.smoothstack.lms.librarianservice.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smoothstack.lms.librarianservice.LibrarianEntityFactory;
import com.smoothstack.lms.librarianservice.entity.BookCopy;
import com.smoothstack.lms.librarianservice.entity.LibraryBranch;

@SpringBootTest
@AutoConfigureMockMvc
@Sql({ "../sql/schema.sql", "../sql/data.sql" })
public class LibrarianFullSystemIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateBookCopy() throws JsonProcessingException, Exception {
        Long bookId = 7l;
        Long branchId = 2l;
        Long amount = 1l;
        BookCopy bookCopy = LibrarianEntityFactory.createBookCopy(bookId, branchId, amount);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/lms/librarian/book-copies")
                        .content(this.objectMapper.writeValueAsString(bookCopy)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id.book.id").value(bookId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id.branch.id").value(branchId));
    }

    @Test
    public void testGetBooksNotInBookCopies() throws Exception {
        Long branchId = 1l;
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/lms/librarian/books/book-copies/branches/{branchId}", branchId)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").isNotEmpty());
    }

    @Test
    public void testGetLibraryBranches() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/lms/librarian/branches").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").isNotEmpty());
    }

    @Test
    public void testGetLibraryBranchById() throws Exception {
        Long branchId = 1l;
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/lms/librarian/branches/{branchId}", branchId)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(branchId));
    }

    @Test
    public void testGetBookCopies() throws Exception {
        Long branchId = 1l;
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/lms/librarian/book-copies/branches/{branchId}", branchId)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").isNotEmpty());
    }

    @Test
    public void testUpdateBookCopy() throws JsonProcessingException, Exception {
        Long bookId = 1l;
        Long branchId = 1l;
        Long amount = 1l;
        BookCopy bookCopy = LibrarianEntityFactory.createBookCopy(null, null, amount);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/lms/librarian/book-copies/books/{bookId}/branches/{branchId}", bookId, branchId)
                        .content(this.objectMapper.writeValueAsString(bookCopy)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id.book.id").value(bookId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id.branch.id").value(branchId));
    }

    @Test
    public void testUpdateLibraryBranch() throws JsonProcessingException, Exception {
        Long branchId = 1l;
        String name = "name";
        String address = "address";
        LibraryBranch branch = LibrarianEntityFactory.createLibraryBranch(null, name, address);

        this.mockMvc
                .perform(MockMvcRequestBuilders.put("/lms/librarian/branches/{branchId}", branchId)
                        .content(this.objectMapper.writeValueAsString(branch)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(branchId));
    }

    @Test
    public void testRemoveBookCopy() throws Exception {
        Long bookId = 1l;
        Long branchId = 1l;

        this.mockMvc
                .perform(MockMvcRequestBuilders.delete("/lms/librarian//book-copies/books/{bookId}/branches/{branchId}",
                        bookId, branchId))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

}
