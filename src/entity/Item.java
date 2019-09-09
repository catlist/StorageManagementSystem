package entity;

import org.json.JSONException;
import org.json.JSONObject;

public class Item {
	private String name;
	private String imageUrl;
	private String usernameOfPossession;

	private Item(ItemBuilder builder) {
		this.name = builder.itemname;
		this.imageUrl = builder.imageUrl;
		this.usernameOfPossession = builder.usernameOfPossession;
	}

	public String getName() {
		return name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getUsernameOfPossession() {
		return usernameOfPossession;
	}

	public JSONObject toJsonObject() {
		JSONObject obj = new JSONObject();
		try {
			obj.put("name", name);
			obj.put("image_url", imageUrl);
			obj.put("username_of_possession", usernameOfPossession);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return obj;
	}

	public static class ItemBuilder {
		private String itemname;
		private String imageUrl;
		private String usernameOfPossession;

		public ItemBuilder setName(String itemname) {
			this.itemname = itemname;
			return this;
		}

		public ItemBuilder setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
			return this;
		}

		public ItemBuilder setUsernameOfPossession(String usernameOfPossession) {
			this.usernameOfPossession = usernameOfPossession;
			return this;
		}

		public Item build() {
			return new Item(this);
		}
	}
}
