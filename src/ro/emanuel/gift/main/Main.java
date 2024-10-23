package ro.emanuel.gift.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import ro.emanuel.gift.dao.GiftDao;
import ro.emanuel.gift.pojo.Gift;



public class Main {
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
		// 1. Luam toate bancile si le afisam
        List<Gift> gifts = GiftDao.getGift();
        System.out.println("Lista cadourilor:");
        for (Gift gift : gifts) {
            System.out.println(gift);
        }
        
        System.out.println("=====================");
        System.out.println("=====================");
        
        // 2. Cream o banca noua
        Gift newGift = new Gift("Str.Bobocilor", "Serbare", 80);
        int rowsInserted = GiftDao.createGift(newGift);
        System.out.println("Cadoul nou a fost livrat. Rinduri inserate: " + rowsInserted);
        
        System.out.println();
        System.out.println("=====================");
        System.out.println("=====================");
        System.out.println();


        // 3. Luam o banca dupa ID si o afisam
        Gift gift = GiftDao.getById(4);
        System.out.println("Banca gasita dupa ID: " + gift);
        
        System.out.println();
        System.out.println("=====================");
        System.out.println("=====================");
        System.out.println();



        // 4. Facem update la banca cu ID-ul 1, ii setam numele "Banca BNR", swift si adresa noua
        // apoi afisam si verificam actualizarea
        Gift giftToUpdate = new Gift(8, "Str. Bulbucilor", "Botez", 150);
        int rowsUpdated = GiftDao.updateGift(giftToUpdate);
        System.out.println("Cadoul a fost livrat. Rinduri updatate: " + rowsUpdated);
        // iau banca cu id-ul specificat din db folosind dao, si verific daca s-a actualizat
        gift = GiftDao.getById(7);
        System.out.println("Banca actualizata: " + gift);
        
        System.out.println();
        System.out.println("=====================");
        System.out.println("=====================");
        System.out.println();

        
        // 5. Stergem banca cu ID-ul 1
        int rowsDeleted = GiftDao.deleteGift(5);
        System.out.println("Cadoul a fost livrat. Rinduri sterse: " + rowsDeleted);
        // acum incerc sa o iau din nou din db, si pentru ca tocmai am sters-o, ar trebui sa primim null fiindca asa am facut in DAO.
        gift = GiftDao.getById(7);
        System.out.println(gift);

        
        

		
				
	}
	


}
