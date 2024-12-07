package com.lib.DAO;

import com.lib.model.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO {
    private Connection connection;

    public AuthorDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean addAuthor(Author author) {
        String query = "INSERT INTO authors (name, bio) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, author.getName());
            stmt.setString(2, author.getBio());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error adding author: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<Author> getAllAuthors() {
        List<Author> authors = new ArrayList<>();
        String query = "SELECT * FROM authors";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                authors.add(new Author(rs.getInt("id"), rs.getString("name"), rs.getString("bio")));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving authors: " + e.getMessage());
            e.printStackTrace();
        }
        return authors;
    }

    public Author getAuthorById(int authorId) {
        String query = "SELECT * FROM authors WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, authorId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Author(rs.getInt("id"), rs.getString("name"), rs.getString("bio"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving author by ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateAuthor(Author author) {
        String query = "UPDATE authors SET name = ?, bio = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, author.getName());
            stmt.setString(2, author.getBio());
            stmt.setInt(3, author.getAuthorId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating author: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAuthor(int authorId) {
        String query = "DELETE FROM authors WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, authorId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting author: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}