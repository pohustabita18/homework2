package ro.emanuel.cub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ro.emanuel.cub.helper.DBHelper;
import ro.emanuel.cub.pojo.Cub;


public class CubDAO {
	
      public static List<Cub> getCub() throws SQLException {
        List<Cub> banks = new ArrayList<>();

        Connection conn = DBHelper.getConnection();
        Statement stmt = conn.createStatement();
        
  
        ResultSet rs = stmt.executeQuery("select * from rubikscube");
        while(rs.next()) {

            int id = rs.getInt("id");
            String brand = rs.getString("brand");
            double weight = rs.getDouble("weight");
            int sides = rs.getInt("sides");
            Cub bank = new Cub(id, brand, weight, sides);
            banks.add(bank); 
        }
        
        DBHelper.closeConnection(); 
        
        return banks; 
    }

    public static int deleteCub(int id) throws SQLException {
        Connection conn = DBHelper.getConnection();
        Statement stmt = conn.createStatement();
        
        int deletedRows = stmt.executeUpdate("delete from rubikscube where id = " + id);
        
        DBHelper.closeConnection();
        
        return deletedRows; 
    }

    public static int createCub(Cub cubToCreate) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String query = "insert into rubikscube (brand, weight, sides) values(?,?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        
        ps.setString(1, cubToCreate.getBrand());
        ps.setDouble(2, cubToCreate.getWeight());
        ps.setInt(3, cubToCreate.getSides());
        
        int affectedRows = ps.executeUpdate();
        
        DBHelper.closeConnection();
        
        return affectedRows; 
    }

    public static int updateCub(Cub cub) throws SQLException {
        Connection conn = DBHelper.getConnection();
        String query = "update rubikscube set brand = ?, weight = ?, sides = ? where id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        
        ps.setString(1, cub.getBrand());
        ps.setDouble(2, cub.getWeight());
        ps.setInt(3, cub.getSides());
        ps.setInt(4, cub.getId());
        
        int rowsUpdated = ps.executeUpdate();
        
        DBHelper.closeConnection();
        
        return rowsUpdated; 
    }

    public static Cub getById(int id) throws SQLException {
        Connection conn = DBHelper.getConnection();
        
        List<Cub> cuburi = getCub();
        for(Cub b : cuburi) {
            if(b.getId() == id) {
                return b;
            }
        }
        
        return null;
    }
}

