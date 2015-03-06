package com.example.efemeridesaragon;

import android.os.Parcel;
import android.os.Parcelable;

public class Event {

	private int id;
	private String date;
	private boolean favorite; 		// true -> favorito
									// false -> no favorito
	private String text;
	private int type;

	public Event(int id, String date, boolean favorite, String text, int type) {
		this.id = id;
		this.date = date;
		this.favorite = favorite;
		this.text = text;
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public boolean isFavorite() {
		return favorite;
	}

	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
	
	public String toString() {
		return id + "  " + date + "  " + favorite + "  " + text + "  " + type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
