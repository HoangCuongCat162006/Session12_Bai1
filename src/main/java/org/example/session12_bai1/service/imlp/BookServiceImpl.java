package org.example.session12_bai1.service.imlp;


import org.example.session12_bai1.entity.Book;
import org.example.session12_bai1.repository.BookRepository;
import org.example.session12_bai1.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private Long currentId = 1L;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Hàm lấy toàn bộ sách
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Hàm lấy sách theo id
    @Override
    public Book getBookById(Long id) {

        return bookRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Không tìm thấy sách"
                        )
                );
    }

    // Hàm thêm sách
    @Override
    public Book createBook(Book book) {
        book.setId(currentId++);
        return bookRepository.save(book);
    }

    // Hàm cập nhật sách
    @Override
    public Book updateBook(Long id, Book book) {

        Book oldBook = getBookById(id);

        oldBook.setTitle(book.getTitle());
        oldBook.setAuthor(book.getAuthor());
        oldBook.setPrice(book.getPrice());

        return oldBook;
    }

    // Hàm xóa sách
    @Override
    public void deleteBook(Long id) {

        Book book = getBookById(id);

        bookRepository.delete(book);
    }
}
