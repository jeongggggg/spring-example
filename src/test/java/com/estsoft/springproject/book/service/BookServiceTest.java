package com.estsoft.springproject.book.service;

import com.estsoft.springproject.book.domain.Book;
import com.estsoft.springproject.book.repository.BookRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private Book book;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mocking a book object
        book = new Book();
        book.setId("1");
        book.setName("Book Title");
        book.setAuthor("Author Name");
    }

    @Test
    void findAll_ShouldReturnBooks_WhenBooksExist() {
        // Given
        Book book1 = new Book();
        book1.setId("1");
        book1.setName("Book 1");
        book1.setAuthor("Author 1");
        Book book2 = new Book();
        book2.setId("2");
        book2.setName("Book 2");
        book2.setAuthor("Author 2");

        when(bookRepository.findAll(any(Sort.class))).thenReturn(Arrays.asList(book1, book2));

        // When
        List<Book> books = bookService.findAll();

        // Then
        assertNotNull(books);
        assertEquals(2, books.size());
        verify(bookRepository, times(1)).findAll(any(Sort.class));
    }

    @Test
    void findBy_ShouldReturnBook_WhenBookExists() {
        // Given
        when(bookRepository.findById("1")).thenReturn(Optional.of(book));

        // When
        Book foundBook = bookService.findBy("1");

        // Then
        assertNotNull(foundBook);
        assertEquals("1", foundBook.getId());  // expected, actual 순서로 변경
        assertEquals("Book Title", foundBook.getName());  // expected, actual 순서로 변경
        assertEquals("Author Name", foundBook.getAuthor());  // expected, actual 순서로 변경
        verify(bookRepository, times(1)).findById("1");
    }

    @Test
    void findBy_ShouldReturnEmptyBook_WhenBookDoesNotExist() {
        // Given
        when(bookRepository.findById("1")).thenReturn(Optional.empty());

        // When
        Book foundBook = bookService.findBy("1");

        // Then
        assertNotNull(foundBook);
        assertEquals(null, foundBook.getId());  // expected, actual 순서로 변경
        assertEquals(null, foundBook.getName());  // expected, actual 순서로 변경
        assertEquals(null, foundBook.getAuthor());  // expected, actual 순서로 변경
        verify(bookRepository, times(1)).findById("1");
    }

    @Test
    void saveOne_ShouldSaveBook_WhenBookIsValid() {
        // Given
        when(bookRepository.save(book)).thenReturn(book);

        // When
        Book savedBook = bookService.saveOne(book);

        // Then
        assertNotNull(savedBook);
        assertEquals("1", savedBook.getId());  // expected, actual 순서로 변경
        assertEquals("Book Title", savedBook.getName());  // expected, actual 순서로 변경
        assertEquals("Author Name", savedBook.getAuthor());  // expected, actual 순서로 변경
        verify(bookRepository, times(1)).save(book);
    }
}