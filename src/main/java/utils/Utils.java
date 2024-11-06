/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author クリス
 */
public class Utils {
    private static boolean isValidPrefix(String prefix) {
        HashMap<String, Boolean> prefixes = new HashMap<>();

        prefixes.put("P", true);
        prefixes.put("C", true);

        prefix = prefix.toUpperCase();
        var isValid = prefixes.get(prefix);
        return isValid != null && isValid;
    }

    private static boolean isPrefixValid(String prefix) {
        var regex = "^[\\d]{3}[B-DF-HJ-NP-TV-Zb-df-hj-np-tv-z]{3}$";
        var pattern = Pattern.compile(regex);
        var matcher = pattern.matcher(prefix);
        return matcher.matches();
    }

    public static Boolean validations(String placa) {
        if (placa == null || placa.isEmpty()) {
            JOptionPane.showMessageDialog(null, "La placa no puede ser nula", "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        var size = placa.length();

        if (size < 7) {
            JOptionPane.showMessageDialog(null, "La placa debe tener al menos 7 caracteres", "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (size > 7) {
            JOptionPane.showMessageDialog(null, "La placa no puede tener más de 7 caracteres", "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        var rest = placa.substring(size-6, size);
        var prefix = placa.replace(rest, "");

        if (!isPrefixValid(rest)) {
            JOptionPane.showMessageDialog(null, "El prefijo no es válido", "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (!isValidPrefix(prefix)) {
            JOptionPane.showMessageDialog(null, "El prefijo no es válido", "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return Boolean.TRUE;
    }

    public static boolean isValidDPI(String dpi) {
        if (dpi == null || !Pattern.matches("\\d{13}", dpi)) {
            return false;
        }

        return true;
    }
    public static boolean celIsValid(String number) {
        if (number == null || !Pattern.matches("502\\d{8}", number)) {
            return false;
        }

        return true;
    }
    
    public static boolean validarNITMod11(String nit) {
        // Encontrar la posición del guion
        int pos = nit.indexOf("-");
        if (pos == -1) {
            JOptionPane.showMessageDialog(null, "Formato de NIT incorrecto.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Separar el correlativo y el dígito verificador
        String correlativo = nit.substring(0, pos);
        String digitoVerificador = nit.substring(pos + 1);

        // Calcular el valor de validación
        int factor = correlativo.length() + 1;
        int valor = 0;
        for (int i = 0; i < correlativo.length(); i++) {
            valor += Character.getNumericValue(correlativo.charAt(i)) * factor;
            factor--;
        }

        int residuo = valor % 11;
        int resultado = 11 - residuo;

        // Ajustar el resultado en caso de ser 11 o más
        if (resultado >= 11) {
            resultado %= 11;
        }

        // Validar el dígito verificador
        if (resultado == 10) {
            if (digitoVerificador.equalsIgnoreCase("K")) {
                return true;
            }
        } else if (digitoVerificador.equals(String.valueOf(resultado))) {
            return true;
        }

        JOptionPane.showMessageDialog(null, "El NIT es inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    public static java.sql.Date convertirStringADate(String fechaString) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            // Convierte la fecha de tipo String a java.util.Date
            Date fechaUtil = formato.parse(fechaString);
            // Convierte java.util.Date a java.sql.Date
            return new java.sql.Date(fechaUtil.getTime());
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Error al convertir la fecha: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error al convertir la fecha: " + e.getMessage());
            return null; // Retorna null si ocurre un error de conversión
        }
    }
}
