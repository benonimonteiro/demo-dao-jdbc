package dao;

import db.Db;

public class DaoFactory {

	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC(Db.getConnection());
	}
}