package db.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DBConnection;
import entity.Item;

public class MySQLDBConnection implements DBConnection {
	private Connection conn;

	public MySQLDBConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
			conn = DriverManager.getConnection(MySQLDBUtil.URL);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Item> searchItems(double lat, double lon, String term) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean registerItem(String itemname, String imageUrl, String usernameOfPossession, int quantity) {
		if (conn == null) {
			System.err.println("DB connection error.");
			return false;
		}
		try {
			String sql = "INSERT IGNORE INTO items VALUES (?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, itemname);
			ps.setString(2, imageUrl);
			ps.setString(3, usernameOfPossession);
			ps.setInt(4, quantity);
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public String getFullname(String username) {
		if (conn == null) {
			return null;
		}
		String name = "";

		String sql = "SELECT first_name, last_name FROM users WHERE username = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				name = rs.getString("first_name") + " " + rs.getString("last_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return name;
	}

	@Override
	public boolean verifyLogin(String username, String password) {
		if (conn == null) {
			return false;
		}

		String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean registerUser(String userId, String password, String firstname, String lastname) {
		if (conn == null) {
			System.err.println("DB connection error.");
			return false;
		}
		try {
			String sql = "INSERT IGNORE INTO users VALUES (?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setString(2, password);
			ps.setString(3, firstname);
			ps.setString(4, lastname);
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
