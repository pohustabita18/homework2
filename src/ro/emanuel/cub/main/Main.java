package ro.emanuel.cub.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import ro.emanuel.cub.dao.CubDAO;
import ro.emanuel.cub.pojo.Cub;

public class Main {
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {

        List<Cub> cuburi = CubDAO.getCub();
        System.out.println("Lista cuburi:");
        for (Cub Cub : cuburi) {
            System.out.println(cuburi);
        }
        
        System.out.println("=====================");
        System.out.println("=====================");
        
        
        Cub newCub = new Cub("GoCube", 75, 7);
        int rowsInserted = CubDAO.createCub(newCub);
        System.out.println("Cubul nou a fost adaugat: " + rowsInserted);
        
        System.out.println();
        System.out.println("=====================");
        System.out.println("=====================");
        System.out.println();


        Cub cub = CubDAO.getById(3);
        System.out.println("Cubul gasita dupa ID: " + cub);
        
        System.out.println();
        System.out.println("=====================");
        System.out.println("=====================");
        System.out.println();



        Cub cubToUpdate = new Cub(5, "GAN", 85, 8);
        int rowsUpdated = CubDAO.updateCub(cubToUpdate);
        System.out.println("Cubul a fost actualizata. Rinduri updatate: " + rowsUpdated);

        
        cub = CubDAO.getById(7);
        System.out.println("Banca actualizata: " + cub);
        
        System.out.println();
        System.out.println("=====================");
        System.out.println("=====================");
        System.out.println();

        

        int rowsDeleted = CubDAO.deleteCub(2);
        System.out.println("Cubul a fost stears. Rinduri sterse: " + rowsDeleted);
        cub = CubDAO.getById(3);
        System.out.println(cub);

        
        

		
				
	}
	


}
