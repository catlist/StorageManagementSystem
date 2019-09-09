package rpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RpcHelper {
	// Writes a JSONArray to http response.
	public static void writeJsonArray(HttpServletResponse response, JSONArray array) throws IOException {
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter writer = response.getWriter();
		writer.print(array);
		writer.close();
	}

	// Writes a JSONObject to http response.
	public static void writeJsonObject(HttpServletResponse response, JSONObject obj) throws IOException {
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter writer = response.getWriter();
		writer.print(obj);
		writer.close();
	}

	// Parse a JSONObject from http request.
	public static JSONObject readJsonObject(HttpServletRequest request) throws JSONException {

		StringBuilder stringBuilder = new StringBuilder();
		try (BufferedReader reader = request.getReader()) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
			}
			return new JSONObject(stringBuilder.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new JSONObject();

	}
}
