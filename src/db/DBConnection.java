package db;

import java.util.List;

import entity.Item;

public interface DBConnection {
	/**
	 * Close the connection.
	 */
	public void close();

	/**
	 * Search items.
	 * 
	 * @param searchBy
	 * @return list of items
	 */
	public List<Item> searchItems(String searchBy);

	/**
	 * Get full name of a user. (This is not needed for main course, just for demo
	 * and extension).
	 * 
	 * @param userId
	 * @return full name of the user
	 */
	public String getFullname(String username);

	/**
	 * Return whether the credential is correct. (This is not needed for main
	 * course, just for demo and extension)
	 * 
	 * @param userId
	 * @param password
	 * @return boolean
	 */
	public boolean verifyLogin(String username, String password);

	/**
	 * Register one user
	 * 
	 * Return whether the user is successfully registered.
	 * 
	 * @param userId
	 * @param password
	 * @param firstname
	 * @param lastname
	 * @param address
	 * @return boolean
	 */
	public boolean registerUser(String username, String password, String firstname, String lastname, String address);

	public boolean registerItem(String itemname, String imageUrl, String usernameOfPossession, int quantity, String address);

	public String getAddress(String username);
}
