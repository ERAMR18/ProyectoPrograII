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
import org.example.domain.Vehiculo;

/**
 *
 * @author クリス
 */
public class VehiculoRepository {
    public void save(Vehiculo vehiculo) throws SQLException, IOException {
        var con = MysqlConexion.getConnection();
        var statement = con.prepareStatement("INSERT INTO vehiculo (placa, marca, linea, modelo, color, cui) VALUES (?, ?, ?, ?, ?, ?)");
        statement.setString(1, vehiculo.getPlaca());
        statement.setString(2, vehiculo.getMarca());
        statement.setString(3, vehiculo.getLinea());
        statement.setString(4, vehiculo.getModelo());
        statement.setString(5, vehiculo.getColor());
        statement.setLong(6, vehiculo.getCui());
        statement.executeUpdate();
        
        JOptionPane.showMessageDialog(null, "Vehiculo ".concat(vehiculo.getPlaca()).concat(" fue creado correctamente"), "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    public void delete(Vehiculo vehiculo) throws SQLException, IOException {
        var con = MysqlConexion.getConnection();
        var statement = con.prepareStatement("DELETE FROM vehiculo WHERE placa = ?");
        statement.setString(1, vehiculo.getPlaca());
        statement.executeUpdate();
        
        JOptionPane.showMessageDialog(null, "Vehiculo ".concat(vehiculo.getPlaca()).concat(" fue eliminado correctamente"), "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    public void update(Vehiculo vehiculo) throws SQLException, IOException {
        var con = MysqlConexion.getConnection();
        var statement = con.prepareStatement("UPDATE vehiculo SET marca = ?, linea = ?, modelo = ?, color = ? WHERE placa = ?");
        statement.setString(1, vehiculo.getMarca());
        statement.setString(2, vehiculo.getLinea());
        statement.setString(3, vehiculo.getModelo());
        statement.setString(4, vehiculo.getColor());
        statement.setString(5, vehiculo.getPlaca());
        statement.executeUpdate();
        
        JOptionPane.showMessageDialog(null, "Vehiculo ".concat(vehiculo.getPlaca()).concat(" fue actualizado correctamente"), "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    public void read(Vehiculo vehiculo) throws SQLException, IOException {
        var con = MysqlConexion.getConnection();
        var statement = con.prepareStatement("SELECT * FROM vehiculo WHERE placa = ?");
        statement.setString(1, vehiculo.getPlaca());
        statement.executeQuery();
    }

    public List<Vehiculo> readAll() throws SQLException, IOException {
        var con = MysqlConexion.getConnection();
        var statement = con.prepareStatement("SELECT * FROM vehiculo");
        var result = statement.executeQuery();

        var vehiculos = new ArrayList<Vehiculo>();

        while (result.next()) {
            var vehiculo = new Vehiculo();
            vehiculo.setMarca(result.getString("marca"));
            vehiculo.setLinea(result.getString("linea"));
            vehiculo.setModelo(result.getString("modelo"));
            vehiculo.setColor(result.getString("color"));
            vehiculo.setPlaca(result.getString("placa"));
            vehiculo.setCui(result.getLong("cui"));
            vehiculos.add(vehiculo);
        }
        return vehiculos;
    }
}
