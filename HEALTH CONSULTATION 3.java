package com.lib.DAO;

import com.lib.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private Connection connection;

    public BookDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Book> getAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("category"),
                        rs.getString("publisher"),
                        rs.getString("isbn"),
                        rs.getString("description"),
                        rs.getInt("available_copies")
                );
                books.add(book);
            }
        }
        return books;
    }

    public Book getBookById(int bookId) throws SQLException {
        String query = "SELECT * FROM books WHERE book_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, bookId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Book(
                            rs.getInt("book_id"),
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getString("category"),
                            rs.getString("publisher"),
                            rs.getString("isbn"),
                            rs.getString("description"),
                            rs.getInt("available_copies")
                    );
                }
            }
        }
        return null; // Return null if no book is found
    }

    // Other methods like addBook, updateBook, deleteBook, etc.
}