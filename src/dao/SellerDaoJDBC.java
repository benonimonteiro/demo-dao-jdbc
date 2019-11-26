package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.Db;
import db.DbException;
import entities.Department;
import entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT seller.*, department.NAME AS DEP_NAME "
						+ "FROM seller INNER JOIN department "
						+ "ON seller.DEPARTMENT_ID = department.ID "
						+ "WHERE seller.ID = ?";
			
			st = conn.prepareStatement(sql);
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if (rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("DEPARTMENT_ID"));
				dep.setName(rs.getString("DEP_NAME"));
				
				Seller obj = new Seller();
				obj.setId(rs.getInt("ID"));
				obj.setName(rs.getString("NAME"));
				obj.setEmail(rs.getString("EMAIL"));
				obj.setBirthDate(rs.getDate("BIRTH_DATE"));
				obj.setBaseSalary(rs.getDouble("BASE_SALARY"));
				obj.setDepartment(dep);
				
				return obj;
			}
			
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			Db.closeStatement(st);
			Db.closeResultSet(rs);
		}
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
