package ro.emanuel.gift.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ro.emanuel.gift.helper.DBHelper;
import ro.emanuel.gift.pojo.Gift;



public class GiftDao{
	

    public static List<Gift> getGift() throws SQLException {
        List<Gift> gifts = new ArrayList<>();

        // Conexiune la baza de date si executam un query simplu
        Connection conn = DBHelper.getConnection();
        Statement stmt = conn.createStatement();
        
        // Luam toate datele din tabelul gift
        ResultSet rs = stmt.executeQuery("select * from gift");
        while(rs.next()) {
            // Construim fiecare cadou din randurile citite
            int id = rs.getInt("id");
            String address = rs.getString("address");
            String ocasion = rs.getString("ocasion");
            double price = rs.getInt("price");
            Gift gift = new Gift(id, address, ocasion, price);
            gifts.add(gift); // Adaugam cadourile in lista
        }
        
        DBHelper.closeConnection(); // Inchidem conexiunea
        
        return gifts; // Returnam lista cu cadourilor gasite
    }

    // Metoda care sterge un cadou din baza de date, bazat pe id
    public static int deleteGift(int id) throws SQLException {
        Connection conn = DBHelper.getConnection();
        Statement stmt = conn.createStatement();
        
        // Stergem cadoul cu id-ul specificat si primim nr de randuri sterse
        int deletedRows = stmt.executeUpdate("delete from gift where id = " + id);
        
        DBHelper.closeConnection();
        
        return deletedRows; // Returnam nr de randuri afectate
    }

    // Metoda care adauga un cadou nou in baza de date
    public static int createGift(Gift giftToCreate) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String query = "insert into gift (address, ocasion, price) values(?,?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        
        // Punem valorile cadourilor in query
        ps.setString(1, giftToCreate.getAddress());
        ps.setString(2, giftToCreate.getOcasion());
        ps.setDouble(3, giftToCreate.getPrice());
        
        // Executam si primim nr de randuri afectate
        int affectedRows = ps.executeUpdate();
        
        DBHelper.closeConnection();
        
        return affectedRows; // Returnam nr de randuri afectate
    }

    // Metoda care face update la un cadou existent
    public static int updateGift(Gift gift) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String query = "update gift set address = ?, ocasion = ?, price = ? where id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        
        // Punem noile valori in query
        ps.setString(1, gift.getAddress());
        ps.setString(2, gift.getOcasion());
        ps.setDouble(3, gift.getPrice());
        ps.setInt(4, gift.getId());
        
        // Executam si primim nr de randuri afectate
        int rowsUpdated = ps.executeUpdate();
        
        DBHelper.closeConnection();
        
        return rowsUpdated; // Returnam nr de randuri afectate
    }

    // Metoda care ia un cadou dupa ID-ul ei
    public static Gift getById(int id) throws SQLException {
        Connection conn = DBHelper.getConnection();
        
        // Folosim metoda getGifts sa luam toate cadourile si cautam ID-ul dorit
        List<Gift> gifts = getGift();
        for(Gift g : gifts) {
            if(g.getId() == id) {
                return g; // Daca gasim un cadou, o returnam
            }
        }
        
        // Daca nu gasim cadoul, returnam null
        return null;
    }
}
