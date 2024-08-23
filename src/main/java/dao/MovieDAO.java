package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Movie;

public class MovieDAO {
    private Connection conexao;

    public MovieDAO(Connection conexao) {
        this.conexao = conexao;
    }

    // Método para inserir um novo filme
    public void insert(Movie movie) throws SQLException {
        String sql = "INSERT INTO released_2024 (code, name, classification, genero) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, movie.getCode());
            stmt.setString(2, movie.getName());
            stmt.setInt(3, movie.getClassification());
            stmt.setString(4, movie.getGenero());
            stmt.executeUpdate();
        }
    }

    // Método para listar todos os filmes
    public List<Movie> listAll() throws SQLException {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT * FROM released_2024";
        try (Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Movie movie = new Movie(
                        rs.getInt("code"),
                        rs.getString("name"),
                        rs.getInt("classification"),
                        rs.getString("genero")
                );
                movies.add(movie);
            }
        }
        return movies;
    }

    // Método para atualizar um filme
    public void update(Movie movie) throws SQLException {
        String sql = "UPDATE released_2024 SET name = ?, classification = ?, genero = ? WHERE code = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, movie.getName());
            stmt.setInt(2, movie.getClassification());
            stmt.setString(3, movie.getGenero());
            stmt.setInt(4, movie.getCode());
            stmt.executeUpdate();
        }
    }

    // Método para excluir um filme pelo código
    public void delete(int code) throws SQLException {
        String sql = "DELETE FROM released_2024 WHERE code = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, code);
            stmt.executeUpdate();
        }
    }
}
