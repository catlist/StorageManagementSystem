package rpc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import db.DBConnection;
import db.DBConnectionFactory;

/**
 * Servlet implementation class AddNewItem
 */
@WebServlet("/AddNewItem")
public class AddNewItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddNewItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DBConnection connection = DBConnectionFactory.getConnection();

		try {
			JSONObject input = RpcHelper.readJsonObject(request);
			String itemname = input.getString("itemname");
			String imageUrl = input.getString("imageUrl");
			String usernameOfPossession = input.getString("username");
			int quantity = input.getInt("quantity");

			JSONObject obj = new JSONObject();
			if (connection.registerItem(itemname, imageUrl, usernameOfPossession, quantity)) {
				obj.put("status", "ok");
			} else {
				obj.put("status", "Registeration Failed");
			}
			RpcHelper.writeJsonObject(response, obj);
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

}
