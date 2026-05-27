package org.example.session12_bai1.repository;


import org.example.session12_bai1.entity.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepository {

    private final List<Book> books = new ArrayList<>();

    // Hàm lấy toàn bộ sách
    public List<Book> findAll() {
        return books;
    }

    // Hàm tìm sách theo id
    public Optional<Book> findById(Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
    }

    // Hàm thêm sách
    public Book save(Book book) {
        books.add(book);
        return book;
    }

    // Hàm xóa sách
    public void delete(Book book) {
        books.remove(book);
    }
}