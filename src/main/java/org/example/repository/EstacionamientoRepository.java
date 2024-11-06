/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.repository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.example.configs.MysqlConexion;
import org.example.domain.Estacionamiento;

/**
 *
 * @author クリス
 */
public class EstacionamientoRepository {
    public void save(Estacionamiento estacionamiento) throws SQLException, IOException {
        var con = MysqlConexion.getConnection();
        var statement = con.prepareStatement("INSERT INTO estacionamiento (estacionamiento_id, placa, fechaRegistro, disponible) VALUES (?, ?, ?, 1)");
        statement.setInt(1, estacionamiento.getEstacionamientoId());
        statement.setString(2, estacionamiento.getPlaca());
        statement.setDate(3, estacionamiento.getFechaRegistro());
        statement.executeUpdate();
        
        JOptionPane.showMessageDialog(null, "Estacionamiento para vehiculo ".concat(estacionamiento.getPlaca()).concat(" fue creado correctamente"), "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    public void delete(Estacionamiento estacionamiento) throws SQLException, IOException {
        var con = MysqlConexion.getConnection();
        var statement = con.prepareStatement("DELETE FROM estacionamiento WHERE estacionamiento_id = ?");
        statement.setInt(1, estacionamiento.getEstacionamientoId());
        statement.executeUpdate();
    }

    public void update(Estacionamiento estacionamiento) throws SQLException, IOException {
        var con = MysqlConexion.getConnection();
        var statement = con.prepareStatement("UPDATE estacionamiento SET placa = ?, fechaRegistro = ?, disponible = ? WHERE estacionamiento_id = ?");
        statement.setString(1, estacionamiento.getPlaca());
        statement.setDate(2, estacionamiento.getFechaRegistro());
        statement.setBoolean(3, estacionamiento.getDisponible());
        statement.setInt(4, estacionamiento.getEstacionamientoId());
        statement.executeUpdate();
        
        JOptionPane.showMessageDialog(null, "Estacionamiento para vehiculo ".concat(estacionamiento.getPlaca()).concat(" fue actualizado correctamente"), "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    public void read(Estacionamiento estacionamiento) throws SQLException, IOException {
        var con = MysqlConexion.getConnection();
        var statement = con.prepareStatement("SELECT * FROM estacionamiento WHERE estacionamiento_id = ?");
        statement.setInt(1, estacionamiento.getEstacionamientoId());
        statement.executeQuery();
    }

    public List<Estacionamiento> readAll() throws SQLException, IOException {
        var con = MysqlConexion.getConnection();
        var statement = con.prepareStatement("SELECT * FROM estacionamiento");
        var result = statement.executeQuery();

        var estacionamientos = new ArrayList<Estacionamiento>();

        while (result.next()) {
            var estacionamiento = new Estacionamiento();
            estacionamiento.setPlaca(result.getString("placa"));
            estacionamiento.setEstacionamientoId(result.getInt("estacionamiento_id"));
            estacionamiento.setFechaRegistro(result.getDate("fechaRegistro"));
            estacionamiento.setDisponible(result.getBoolean("disponible"));
            estacionamientos.add(estacionamiento);
        }
        return estacionamientos;
    }
}
