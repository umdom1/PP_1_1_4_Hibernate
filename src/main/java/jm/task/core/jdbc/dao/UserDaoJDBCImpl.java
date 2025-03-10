package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50) NOT NULL, " +
                "lastname VARCHAR(100) NOT NULL, " +
                "age INT)";

        try (Connection conn = getConnection()) {

            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            System.out.println("Таблица users создана или уже существует!");

        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к базе данных!");
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";

        try (Connection conn = getConnection()) {

            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            System.out.println("Таблица users удалена или не существовала!");

        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к базе данных!");
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        String sql = "INSERT INTO users (name, lastname, age) VALUES (?, ?, ?)";

        try (Connection conn = getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, lastName);
            pstmt.setInt(3, age);
            pstmt.executeUpdate();
            System.out.println("User с именем — " + name + " добавлен в базу данных");

        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к базе данных!");
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection conn = getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            int rows = pstmt.executeUpdate();
            if (rows == 0) {
                System.out.println("Пользователь с " + id + " удален");
            } else {
                System.out.println("Пользователь с " + id + " не найден");
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при удалении пользователя!");
        }
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String lastname = rs.getString("lastname");
                Byte age = (byte) rs.getInt("age");

                users.add(new User(id, name, lastname, age));
            }

        } catch (SQLException e) {
            System.err.println("Ошибка при получении пользователей!");
        }

        return users;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";

        try (Connection conn = getConnection()) {

            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            System.out.println("Таблица users очищена!");

        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к базе данных!");
        }
    }
}
