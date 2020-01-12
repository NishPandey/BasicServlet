package com.dev.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import com.dev.beans.User;

import java.sql.Statement;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class JDBCImpl implements UserDAO {

	@Override
	public Boolean createProfile(User user) {

		Connection con = null;
		PreparedStatement pstmt = null;
		Integer rs;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String dbUrl = "jdbc:mysql://localhost:123/cleveridiots_db";
			con = DriverManager.getConnection(dbUrl,"root","root");
			String sql = "insert into users_info "
					+ "values(?,?,?,?)";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, user.getUserId());
			pstmt.setString(2, user.getFirstName());
			pstmt.setString(3, user.getLastName());
			pstmt.setString(4, user.getPassword());

			rs = pstmt.executeUpdate();

			if(rs>0){

				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public User searchUser(Integer userId) {

		Connection conn = null;
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		User user = new User();
		try {

			// Load the Driver
			java.sql.Driver div = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(div);
			System.out.println("Driver Loaded....");

			//Get DB connection via driver
			String url="jdbc:mysql://localhost:123/cleveridiots_db" + "?user=root&password=root";
			conn = DriverManager.getConnection(url);

			//Issue SQL Query via connection
			String query = "SELECT * FROM users_info where uid=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, userId);

			rs=pstmt.executeQuery();
			//			System.out.println(rs.next());


			//Process the Results

			if(rs.next())
			{

				user.setUserId(rs.getInt("uid"));
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
				user.setPassword(rs.getString("password"));
				return user;	
			}


		}catch(SQLException e) {
			e.printStackTrace();
		}

		//close all the jdbc object
		finally {
			if(conn!=null) {
				try {
					conn.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;

	}






	@Override
	public Boolean updatePassword(Integer userId, String oldPassword, String newPassword) {

		PreparedStatement pstmt=null;
		Connection conn = null;  
		Integer rs = null;  

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String dbUrl = "jdbc:mysql://localhost:123/cleveridiots_db";
			conn = DriverManager.getConnection(dbUrl,"root","root");
			String sql = "UPDATE users_info "
					+ "set password =? where uid= ? and password =?";

			pstmt = conn.prepareStatement(sql);

			User user = null;

			pstmt.setInt(1, user.getUserId());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(2, user.getPassword());

			rs = pstmt.executeUpdate();

		}
		catch(Exception e){
			System.out.println(e);
		}
		finally { 
			if (conn != null) {  
				try {  
					conn.close();  
				} catch (SQLException e) {  
					e.printStackTrace();  
				}  
			}
			if (pstmt != null) {  
				try {  
					pstmt.close();  
				} catch (SQLException e) {  
					e.printStackTrace();  
				}  
			}  

		}
		return null;  


	}















	@Override
	public Boolean deleteUser(Integer userId, String password) {


		Connection conn=null;
		Statement stmt= null;
		Integer rs ;
		try {

			//load the driver
			java.sql.Driver div = new com.mysql.jdbc.Driver();

			DriverManager.registerDriver(div);

			//Get the connection via Driver
			String dbUrl = "jdbc:mysql://Localhost:123/cleveridiots_db";
			conn = DriverManager.getConnection(dbUrl,"root","root");


			//Issue SQL Query via conn
			String query = "DELETE from users_info"
					+ " where uid=? and password=?";


			rs = stmt.executeUpdate(query);


			//Process the Results..


			if(rs!=0) {

				return true;
			}else {
				return false;
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}

		//Close all the JDBC Objects
		finally {
			if(conn!=null) {
				try {
					conn.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmt!=null) {
				try {
					stmt.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return true;


	}
















	@Override
	public User login(Integer userId, String password) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String dbUrl = "jdbc:mysql://localhost:123/cleveridiots_db";
			con = DriverManager.getConnection(dbUrl,"root","root");
			String sql = "select * from users_info "
					+ "	where uid=? and password = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if(rs.next()){
				user = new User();
				user.setUserId(rs.getInt("uid"));
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
				user.setPassword(rs.getString("password"));
			}


		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return user;
	}

}
