package app;

import dao.MovieDAO;
import model.Movie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Principal {
    private static Connection conexao;

    public static void main(String[] args) {
        try {
            // Configurar a conexão
            conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/movies", "postgres", "m4rc3114");
            MovieDAO movieDAO = new MovieDAO(conexao);

            Scanner scanner = new Scanner(System.in);
            int opcao = 0;

            do {
                System.out.println("==== Menu ====");
                System.out.println("1) Listar");
                System.out.println("2) Inserir");
                System.out.println("3) Excluir");
                System.out.println("4) Atualizar");
                System.out.println("5) Sair");
                System.out.print("Escolha uma opção: ");
                
                try {
                    opcao = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer após nextInt()
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
                    scanner.nextLine(); // Limpar o buffer após a exceção
                    continue;
                }

                switch (opcao) {
                    case 1:
                        List<Movie> movies = movieDAO.listAll();
                        for (Movie movie : movies) {
                            System.out.println("Código: " + movie.getCode() + ", Nome: " + movie.getName() +
                                    ", Classificação: " + movie.getClassification() + ", Gênero: " + movie.getGenero());
                        }
                        break;

                    case 2:
                        try {
                            System.out.print("Código: ");
                            int code = scanner.nextInt();
                            scanner.nextLine(); // Limpar o buffer após nextInt()
                            System.out.print("Nome: ");
                            String name = scanner.nextLine();
                            System.out.print("Classificação: ");
                            int classification = scanner.nextInt();
                            scanner.nextLine(); // Limpar o buffer após nextInt()
                            System.out.print("Gênero: ");
                            String genero = scanner.nextLine();

                            Movie newMovie = new Movie(code, name, classification, genero);
                            movieDAO.insert(newMovie);
                            System.out.println("Filme inserido com sucesso!");
                        } catch (InputMismatchException e) {
                            System.out.println("Entrada inválida. Verifique os dados fornecidos.");
                            scanner.nextLine(); // Limpar o buffer após a exceção
                        }
                        break;

                    case 3:
                        try {
                            System.out.print("Digite o código do filme a ser excluído: ");
                            int deleteCode = scanner.nextInt();
                            scanner.nextLine(); // Limpar o buffer após nextInt()
                            movieDAO.delete(deleteCode);
                            System.out.println("Filme excluído com sucesso!");
                        } catch (InputMismatchException e) {
                            System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
                            scanner.nextLine(); // Limpar o buffer após a exceção
                        }
                        break;

                    case 4:
                        try {
                            System.out.print("Código do filme a ser atualizado: ");
                            int updateCode = scanner.nextInt();
                            scanner.nextLine(); // Limpar o buffer após nextInt()
                            System.out.print("Novo nome: ");
                            String newName = scanner.nextLine();
                            System.out.print("Nova classificação: ");
                            int newClassification = scanner.nextInt();
                            scanner.nextLine(); // Limpar o buffer após nextInt()
                            System.out.print("Novo gênero: ");
                            String newGenero = scanner.nextLine();

                            Movie updatedMovie = new Movie(updateCode, newName, newClassification, newGenero);
                            movieDAO.update(updatedMovie);
                            System.out.println("Filme atualizado com sucesso!");
                        } catch (InputMismatchException e) {
                            System.out.println("Entrada inválida. Verifique os dados fornecidos.");
                            scanner.nextLine(); // Limpar o buffer após a exceção
                        }
                        break;

                    case 5:
                        System.out.println("Saindo...");
                        break;

                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                        break;
                }
            } while (opcao != 5);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conexao != null && !conexao.isClosed()) {
                    conexao.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
