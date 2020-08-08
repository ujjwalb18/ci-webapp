package com.lti.dao;
import com.lti.entity.*;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class ProductDao {

	public Product select(int id) {
		 Connection conn = null;
	        PreparedStatement stmt =  null;
	        ResultSet rs = null;
	        try {
	        	Class.forName("oracle.jdbc.driver.OracleDriver");
	        	conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "system", "Pranjali2");
	            String sql = "Select * from tbl_product where id = ?";
	            stmt = conn.prepareStatement(sql);
	            stmt.setInt(1, id);
	            rs = stmt.executeQuery();
	            if(rs.next()) {
	                Product product= new Product();
	                product.setId(rs.getInt("id"));
	                product.setName(rs.getString("name"));
	                product.setPrice(rs.getDouble("price"));
	                return product;
	            }
	            return null;
	        }
	        catch(Exception e) {        //ClassNotFoundException | SQLException
	            e.printStackTrace();
	            return null;
	        }
	        finally {
	            try {conn.close(); } catch(Exception e) {}
	        }
	}
	
	public List<Product> selectAll(){
		Connection conn = null;
        PreparedStatement stmt =  null;
        ResultSet rs = null;
        try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
        	conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "system", "Pranjali2");
            String sql = "Select * from tbl_product";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();
            List<Product> list= new ArrayList<>();
            while(rs.next()) {
                Product product= new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                list.add(product);
            }
            return list;
        }
        catch(Exception e) {        //ClassNotFoundException | SQLException
            e.printStackTrace();
            return null;
        }
        finally {
            try {conn.close(); } catch(Exception e) {}
        }
	}
	
	public void insert(Product product) {
		Connection conn=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url="jdbc:oracle:thin:@localhost:1521:ORCL";
			String user="system";
			String pass="Pranjali2";
			
			conn=DriverManager.getConnection(url,user,pass);
			System.out.println("Succesful");
			
			Statement st=conn.createStatement();
			st.execute("INSERT INTO tbl_products VALUES (1,'John','10-JUN-1995')");
			
			PreparedStatement pst=conn.prepareStatement("INSERT INTO tbl_product VALUES(?,?,?)");
			pst.setInt(1,444);
			pst.setString(2,"Xiaomi");
			pst.setDouble(3, 17999);
			//int count=pst.executeUpdate();
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			try { conn.close(); } 
			catch(Exception e) {}
		}
	}
}
