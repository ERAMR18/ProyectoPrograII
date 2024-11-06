package org.example.repository;

import org.example.domain.Persona;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.example.configs.MysqlConexion;
import utils.Utils;

/**
 *
 * @author クリス
 */
public class PersonaRepository {
    public void save(Persona persona) throws SQLException, IOException, ParseException {
        var con = MysqlConexion.getConnection();
        var statement = con.prepareStatement("INSERT INTO persona (cui, nombres, apellidos, domicilio, cel, nit, fechaNacimiento) VALUES (?, ?, ?, ?, ?, ?, ?)");
        statement.setLong(1, persona.getCui());
        statement.setString(2, persona.getNombres());
        statement.setString(3, persona.getApellidos());
        statement.setString(4, persona.getDomicilio());
        statement.setString(5, persona.getCel());
        statement.setString(6, persona.getNit());
        statement.setDate(7, Utils.convertirStringADate(persona.getFechaNacimiento()));
        statement.executeUpdate();
        
        JOptionPane.showMessageDialog(null, "Persona ".concat(persona.getNombres()).concat(" fue creado correctamente"), "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    public void delete(Persona persona) throws SQLException, IOException {
        var con = MysqlConexion.getConnection();
        var statement = con.prepareStatement("DELETE FROM persona WHERE cui = ?");
        statement.setLong(1, persona.getCui());
        statement.executeUpdate();
    }

    public void update(Persona persona) throws SQLException, IOException {
        var con = MysqlConexion.getConnection();
        var statement = con.prepareStatement("UPDATE persona SET nombres = ?, apellidos = ?, domicilio = ?, cel = ?, nit = ?, fechaNacimiento = ? WHERE cui = ?");
        statement.setString(1, persona.getNombres());
        statement.setString(2, persona.getApellidos());
        statement.setString(3, persona.getDomicilio());
        statement.setString(4, persona.getCel());
        statement.setString(5, persona.getNit());
        statement.setDate(6, Utils.convertirStringADate(persona.getFechaNacimiento()));
        statement.setLong(7, persona.getCui());
        statement.executeUpdate();
        
        JOptionPane.showMessageDialog(null, "Persona ".concat(persona.getNombres()).concat(" fue actualizado correctamente"), "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    public void read(Persona persona) throws SQLException, IOException {
        var con = MysqlConexion.getConnection();
        var statement = con.prepareStatement("SELECT * FROM persona WHERE cui = ?");
        statement.setLong(1, persona.getCui());
        statement.executeQuery();
    }

    public List<Persona> readAll() throws SQLException, IOException {
        var con = MysqlConexion.getConnection();
        var statement = con.prepareStatement("SELECT * FROM persona");
        var result = statement.executeQuery();

        var personas = new ArrayList<Persona>();

        while (result.next()) {
            var persona = new Persona();
            persona.setApellidos(result.getString("apellidos"));
            persona.setCui(result.getLong("cui"));
            persona.setNombres(result.getString("nombres"));
            persona.setCel(result.getString("cel"));
            persona.setDomicilio(result.getString("domicilio"));
            persona.setNit(result.getString("nit"));
            persona.setFechaNacimiento(result.getString("fechaNacimiento"));
            personas.add(persona);
        }
        return personas;
    }
}
