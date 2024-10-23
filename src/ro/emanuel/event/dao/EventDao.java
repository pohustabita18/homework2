package ro.emanuel.event.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ro.emanuel.event.helper.DBHelper;
import ro.emanuel.event.pojo.Event;



public class EventDao{
	

    public static List<Event> getEvent() throws SQLException {
        List<Event> events = new ArrayList<>();

        // Conexiune la baza de date si executam un query simplu
        Connection conn = DBHelper.getConnection();
        Statement stmt = conn.createStatement();
        
        // Luam toate datele din tabelul event
        ResultSet rs = stmt.executeQuery("select * from event");
        while(rs.next()) {
            // Construim fiecare evenment din randurile citite
            int id = rs.getInt("id");
            String brand = rs.getString("brand");
            String location = rs.getString("location");
            int pret = rs.getInt("pret");
            Event event = new Event(id, brand, location, pret);
            events.add(event); 
        }
        
        DBHelper.closeConnection(); // Inchidem conexiunea
        
        return events; 
    }

    // Metoda care sterge un eveniment din baza de date, bazat pe id
    public static int deleteEvent(int id) throws SQLException {
        Connection conn = DBHelper.getConnection();
        Statement stmt = conn.createStatement();
        
        // Stergem evenimentul cu id-ul specificat si primim nr de randuri sterse
        int deletedRows = stmt.executeUpdate("delete from event where id = " + id);
        
        DBHelper.closeConnection();
        
        return deletedRows; // Returnam nr de randuri afectate
    }

    // Metoda care adauga un eveniment nou in baza de date
    public static int createEvent(Event eventToCreate) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String query = "insert into event (brand, location, pret) values(?,?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        
        // Punem valorile evenimentului in query
        ps.setString(1, eventToCreate.getBrand());
        ps.setString(2, eventToCreate.getLocation());
        ps.setDouble(3, eventToCreate.getPret());
        
        // Executam si primim nr de randuri afectate
        int affectedRows = ps.executeUpdate();
        
        DBHelper.closeConnection();
        
        return affectedRows; // Returnam nr de randuri afectate
    }

    // Metoda care face update la un eveniment existent
    public static int updateEvent(Event event) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String query = "update event set barnd = ?, location = ?, pret = ? where id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        
        // Punem noile valori in query
        ps.setString(1, event.getBrand());
        ps.setString(2, event.getLocation());
        ps.setDouble(3, event.getPret());
        ps.setInt(4, event.getId());
        
        // Executam si primim nr de randuri afectate
        int rowsUpdated = ps.executeUpdate();
        
        DBHelper.closeConnection();
        
        return rowsUpdated; // Returnam nr de randuri afectate
    }

    // Metoda care ia un eveniment dupa ID-ul lui
    public static Event getById(int id) throws SQLException {
        Connection conn = DBHelper.getConnection();
        
        // Folosim metoda getEvent sa luam toate evenimentele si cautam ID-ul dorit
        List<Event> events = getEvent();
        for(Event e : events) {
            if(e.getId() == id) {
                return e; // Daca gasim evenimentul, il returnam
            }
        }
        
        // Daca nu gasim evenimentul, returnam null
        return null;
    }
}
