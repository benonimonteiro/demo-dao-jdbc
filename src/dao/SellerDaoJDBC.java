package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
				Department department = instantiateDepartment(rs);				
				Seller seller = instantiateSeller(rs, department);	
				return seller;
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
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT seller.*, department.NAME AS DEP_NAME "
						+ "FROM seller INNER JOIN department "
						+ "ON seller.DEPARTMENT_ID = department.ID "
						+ " WHERE seller.DEPARTMENT_ID = ? "
						+ "ORDER BY seller.NAME";
			
			st = conn.prepareStatement(sql);
			st.setInt(1, department.getId());
			rs = st.executeQuery();
			
			List<Seller> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();
			
			while (rs.next()) {
				Department dep = map.get(rs.getInt("DEPARTMENT_ID"));
				
				if (dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DEPARTMENT_ID"), dep);
				}
				
				Seller seller = instantiateSeller(rs, dep);
				list.add(seller);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			Db.closeResultSet(rs);
			Db.closeStatement(st);
		}
	}
	
	@Override
	public List<Seller> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT seller.*, department.NAME AS DEP_NAME "
						+ "FROM seller INNER JOIN department "
						+ "ON seller.DEPARTMENT_ID = department.ID "
						+ "ORDER BY seller.NAME";
		
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			
			List<Seller> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();
			
			while (rs.next()) {
				Department department = map.get(rs.getInt("DEPARTMENT_ID"));
				
				if (department == null) {
					department = instantiateDepartment(rs);
					map.put(rs.getInt("DEPARTMENT_ID"), department);
				}
		
				Seller seller = instantiateSeller(rs, department);
				list.add(seller);
			}
			
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getLocalizedMessage());
		}
		finally {
			Db.closeResultSet(rs);
			Db.closeStatement(st);
		}
	}

	private Seller instantiateSeller(ResultSet rs, Department department) throws SQLException {
		Seller seller = new Seller();
		seller.setId(rs.getInt("ID"));
		seller.setName(rs.getString("NAME"));
		seller.setEmail(rs.getString("EMAIL"));
		seller.setBirthDate(rs.getDate("BIRTH_DATE"));
		seller.setBaseSalary(rs.getDouble("BASE_SALARY"));
		seller.setDepartment(department);
		return seller;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department department = new Department();
		department.setId(rs.getInt("DEPARTMENT_ID"));
		department.setName(rs.getString("DEP_NAME"));
		return department;
	}
}