/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.configs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;

/**
 *
 * @author クリス
 */
public class MysqlConexion {
    private static Connection connection;
    private static final Dotenv dotenv = Dotenv.configure()
            .directory("src/main/resources")
            .ignoreIfMalformed()
            .ignoreIfMissing()
            .load();
    
    private static final String USER = dotenv.get("DB_USER");
    private static final String PASSWORD = dotenv.get("DB_PASS");
    private static final String URL = dotenv.get("DB_URL");
    
    // "file:src/main/resources/mysql-config.json"
    
    public static Connection getConnection() throws SQLException, IOException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }
}
