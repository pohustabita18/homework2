package ro.emanuel.event.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import ro.emanuel.event.dao.EventDao;
import ro.emanuel.event.pojo.Event;


public class Main {
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {

        List<Event> events = EventDao.getEvent();
        System.out.println("Lista cuburi:");
        for (Event Event : events) {
            System.out.println(events);
        }
        
        System.out.println("=====================");
        System.out.println("=====================");
        
        
        Event newEvent = new Event("Opera", "Str. Bulevandului", 400);
        int rowsInserted = EventDao.createEvent(newEvent);
        System.out.println("Evenumentul nou a fost creat: " + rowsInserted);
        
        System.out.println();
        System.out.println("=====================");
        System.out.println("=====================");
        System.out.println();


        Event event = EventDao.getById(3);
        System.out.println("Cubul gasita dupa ID: " + event);
        
        System.out.println();
        System.out.println("=====================");
        System.out.println("=====================");
        System.out.println();



        Event eventToUpdate = new Event(5, "Coloseum", "Str. Florilor,18", 600);
        int rowsUpdated = EventDao.updateEvent(eventToUpdate);
        System.out.println("Evenimentul a fost actualizat. Rinduri updatate: " + rowsUpdated);

        
        event = EventDao.getById(5);
        System.out.println("Banca actualizata: " + event);
        
        System.out.println();
        System.out.println("=====================");
        System.out.println("=====================");
        System.out.println();

        

        int rowsDeleted = EventDao.deleteEvent(2);
        System.out.println("Cubul a fost stears. Rinduri sterse: " + rowsDeleted);
        event = EventDao.getById(3);
        System.out.println(event);

        
        

		
				
	}
	


}
