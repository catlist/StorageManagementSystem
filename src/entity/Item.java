package entity;

import org.json.JSONException;
import org.json.JSONObject;

public class Item {
	private String itemname;
	private String imageUrl;
	private String username;
	private String quantity;
	private String address;

	private Item(ItemBuilder builder) {
		this.itemname = builder.itemname;
		this.imageUrl = builder.imageUrl;
		this.username = builder.username;
		this.quantity = builder.quantity;
		this.address = builder.address;
	}

	public String getItemName() {
		return itemname;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getUsername() {
		return username;
	}

	public JSONObject toJsonObject() {
		JSONObject obj = new JSONObject();
		try {
			obj.put("itemname", itemname);
			obj.put("image_url", imageUrl);
			obj.put("username", username);
			obj.put("quantity", quantity);
			obj.put("address", address);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return obj;
	}

	public static class ItemBuilder {
		private String itemname;
		private String imageUrl;
		private String username;
		private String quantity;
		private String address;

		public ItemBuilder setItemName(String itemname) {
			this.itemname = itemname;
			return this;
		}

		public ItemBuilder setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
			return this;
		}

		public ItemBuilder setUsername(String username) {
			this.username = username;
			return this;
		}
		
		public ItemBuilder setQuantity(String quantity) {
			this.quantity = quantity;
			return this;
		}
		
		public ItemBuilder setAddress(String address) {
			this.address = address;
			return this;
		}

		public Item build() {
			return new Item(this);
		}
	}
}
