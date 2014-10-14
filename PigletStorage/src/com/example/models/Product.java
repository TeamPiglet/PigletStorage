package com.example.models;

import java.util.UUID;

import android.os.Parcel;
import android.os.Parcelable;

import com.telerik.everlive.sdk.core.model.base.DataItem;
import com.telerik.everlive.sdk.core.serialization.ServerProperty;
import com.telerik.everlive.sdk.core.serialization.ServerType;

@ServerType("Product")
public class Product extends DataItem implements Parcelable {
	private UUID idTBS;
	private long idSQLite;

	@ServerProperty("name")
	private String name;
	@ServerProperty("type")
	private String type;
	@ServerProperty("price")
	private String price;
	@ServerProperty("quantity")
	private String quantity;
	@ServerProperty("image")
	private byte[] image;
	@ServerProperty("longitude")
	private String longitude;
	@ServerProperty("latitude")
	private String latitude;

	// Parcelable stuff

	public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
		public Product createFromParcel(Parcel in) {
			return new Product(in);
		}

		public Product[] newArray(int size) {
			return new Product[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeString(type);
		dest.writeString(price);
		dest.writeString(quantity);
		dest.writeInt(image.length);
		dest.writeByteArray(image);
	}

	// Parcelable constructor
	private Product(Parcel in) {
		this.name = in.readString();
		this.type = in.readString();
		this.price = in.readString();
		this.quantity = in.readString();
		this.image = new byte[in.readInt()];
		in.readByteArray(this.image);
	}
	// End Of parcelable stuff
	
	public Product (){
		
	}
	
	public long getIdSQLite() {
		return idSQLite;
	}

	public void setIdSQLite(long id) {
		this.idSQLite = id;
	}

	// for Telerik Backend
	public UUID getId() {
		return idTBS;
	}

	// for Telerik Backend
	public void setId(UUID id) {
		this.idTBS = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getLogitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@Override
	public String toString() {
		return "Name: " + name + "\n  Price: " + price + "\n  Type: " + type
				+ "\n  Quantity " + quantity + "\n  ID: " + idSQLite
				+ "\n  Longitude: " + longitude + "\n  Latitude: " + latitude;
	}
}
