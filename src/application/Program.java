package application;

import dao.DaoFactory;
import dao.SellerDao;
import entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("=== TEST 1: Seller.findById ===");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
	}
}
