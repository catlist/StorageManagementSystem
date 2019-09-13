package db.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class MySQLTableCreation {
	// Run this as Java application to reset db schema.
	public static void main(String[] args) {
		try {
			// Step 1 Connect to MySQL.
			System.out.println("Connecting to " + MySQLDBUtil.URL);
			try {
				Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
				Connection conn = DriverManager.getConnection(MySQLDBUtil.URL);

				if (conn == null) {
					return;
				}

				// Step 2 Drop tables in case they exist.
				Statement statement = conn.createStatement();

				String sql = "DROP TABLE IF EXISTS items";
				statement.executeUpdate(sql);

				sql = "DROP TABLE IF EXISTS users";
				statement.executeUpdate(sql);

				// Step 3 Create new tables
				sql = "CREATE TABLE items (" + "itemname VARCHAR(255)," + "possession VARCHAR(255)," + "quantity int," + "address VARCHAR(255)," + "imageUrl VARCHAR(255)" + ")";
				statement.executeUpdate(sql);

				sql = "CREATE TABLE users (" + "username VARCHAR(255) NOT NULL," + "password VARCHAR(255) NOT NULL,"
						+ "first_name VARCHAR(255)," + "last_name VARCHAR(255)," + "address VARCHAR(255)," + "PRIMARY KEY (username)" + ")";
				statement.executeUpdate(sql);

				/*
				 * sql = "INSERT INTO users VALUES(?,?,?,?)"; PreparedStatement ps =
				 * conn.prepareStatement(sql); ps.setString(1, "1111"); ps.setString(2, "2222");
				 * ps.setString(3, "John"); ps.setString(4, "Doe"); ps.execute();
				 */

				conn.close();
				System.out.println("Import done successfully");

			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
