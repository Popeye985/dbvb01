package org.beta.vzw.db;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final String SELECT = "SELECT * from persoon";
    public static void main( String[] args ) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        try (Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/testdb?useSSL=false&serverTimezone=Europe/Brussels", "root", "Vdab")) {
            try(Statement select = conn.createStatement()){
                try(ResultSet rs = select.executeQuery(SELECT)){
                    while (rs.next()){
                        int id = rs.getInt("id");
                        String voornaam = rs.getString("voornaam");
                        String achternaam = rs.getString("achternaam");
                        System.out.printf("%d: %s %s%n", id, voornaam, achternaam);
                        LocalDate geboortedatum = rs.getDate("geboortedatum").toLocalDate();
                        System.out.printf("%d: %s %s geboren op %s%n", id, voornaam, achternaam, geboortedatum.format(formatter));
                    }
                }
            }
        }

    }
}
