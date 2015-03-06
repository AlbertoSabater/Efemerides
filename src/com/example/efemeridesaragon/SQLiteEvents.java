/*
 * Copyright (C) 2008 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.example.efemeridesaragon;

import java.util.Vector;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Simple notes database access helper class. Defines the basic CRUD operations
 * for the notepad example, and gives the ability to list all notes as well as
 * retrieve or modify a specific note.
 * 
 * This has been improved from the first version of this tutorial through the
 * addition of better error handling and also using returning a Cursor instead
 * of using a collection of inner classes (which is less scalable and not
 * recommended).
 */
public class SQLiteEvents extends SQLiteOpenHelper {

	/*
	 * public static final String KEY_TITLE = "title"; public static final
	 * String KEY_BODY = "body"; public static final String KEY_ROWID = "_id";
	 * 
	 * private static final String TAG = "NotesDbAdapter";
	 */

	private SQLiteDatabase mDb;

	private static final String DATABASE_NAME = "BD_EVENTS";
	private static final int DATABASE_VERSION = 2;

	private static final String DATABASE_CREATE_EVENTS = "create table events("
			+ "_idEvent integer primary key autoincrement,"
			+ "date text not null," + "event text not null,"
			+ "type integer not null," + "isFavorite integer not null" + ");";

	// 0 -> no favorite ; 1 -> favorite

	private static final String DATABASE_CREATE_FAVORITES = "create table favorites("
			+ "idFavorite references events(_idEvent)" + ");";

	/**
	 * Database creation sql statement
	 */
	/*
	 * private static final String DATABASE_CREATE =
	 * "create table notes (_id integer primary key autoincrement, " +
	 * "title text not null, body text not null);";
	 * 
	 * private static final String DATABASE_NAME = "data"; private static final
	 * String DATABASE_TABLE = "notes"; private static final int
	 * DATABASE_VERSION = 2;
	 */

	/**
	 * Constructor - takes the context to allow the database to be
	 * opened/created
	 * 
	 * @param ctx
	 *            the Context within which to work
	 */
	public SQLiteEvents(Context ctx) {
		super(ctx, DATABASE_NAME, null, DATABASE_VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE_EVENTS);
		db.execSQL(DATABASE_CREATE_FAVORITES);
		
		//insertarDatos();
	}
	
	// **************** BORRAR BORRAR BORRAR BORRAR ***************************
	public void insertarDatos() {
		Vector<Event> V = new Vector<Event>();
		
		V.add(new Event(1,"fecha1", false, "texto1",1));
        V.add(new Event(2,"fecha2", false, "texto2",1));
        V.add(new Event(3,"fecha3", false, "texto3",1));
        V.add(new Event(4,"fecha4", false, "texto4",1));
        V.add(new Event(5,"fecha5", false, "texto5",1));
        V.add(new Event(6,"fecha6", false, "texto6",1));
        V.add(new Event(7,"fecha7", false, "texto7",1));
        V.add(new Event(8,"fecha8", false, "texto8",1));
        V.add(new Event(9,"fecha9", false, "texto9",1));
        V.add(new Event(10,"fecha10", false, "texto10",1));
        V.add(new Event(11,"fecha11", false, "texto11",1));
        V.add(new Event(12,"fecha12", false, "textoo12",1));
        
        
        for (Event e : V) {
       	 	Log.w("INSERTANDO", "Insertando " + e.toString());
       	 	insertEvent(e);
        }
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w("UPGRADING DB", "Upgrading database from version " + oldVersion
				+ " to " + newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS events");
		db.execSQL("DROP TABLE IF EXISTS favorites");
		onCreate(db);
	}

	/**
	 * Return a Cursor over the list of all events in the database
	 * 
	 * @return Cursor over all notes
	 */
	public Vector<Event> fetchAllEvents() {
		Vector<Event> result = new Vector<Event>();
		SQLiteDatabase db = getReadableDatabase();

		Cursor cursor = db.query("events", new String[] { "_idEvent", "date",
				"isFavorite", "event", "type" }, null, null, null, null, null);

		while (cursor.moveToNext()) {

			Event aux = new Event(cursor.getInt(0), cursor.getString(1), false,
					cursor.getString(3), cursor.getInt(4));

			if (cursor.getString(2).equals("1")) {
				aux.setFavorite(true);
			}

			result.add(aux);
		}

		cursor.close();

		return result;
	}

	public void insertEvent(Event e) {
		SQLiteDatabase db = getWritableDatabase();

		if (e.isFavorite()) {
			String aux = "INSERT INTO events VALUES ( null, '" + e.getDate()
					+ "', '" + e.getText() + "', " + e.getType() + ", " + "1)";
			Log.e("INSERTANDO", aux);
			db.execSQL(aux);
		} else {
			String aux = "INSERT INTO events VALUES ( null, '" + e.getDate()
					+ "', '" + e.getText() + "', " + e.getType() + ", " + "0)";
			Log.e("INSERTANDO", aux);
			db.execSQL(aux);
		}
	}

	public void insertFavorite(int id) {
		SQLiteDatabase db = getWritableDatabase();

		String aux = "INSERT INTO favorites VALUES ( " + id + ")";
		Log.e("INSERTANDO FAV", aux);
		db.execSQL(aux);

		aux = "UPDATE events SET isFavorite = 1 WHERE _idEvent = " + id;
		Log.e("UPDATING FAV", aux);
		db.execSQL(aux);
	}

	public void deleteFavorite(int id) {
		SQLiteDatabase db = getWritableDatabase();

		String aux = "DELETE FROM favorites WHERE idFavorite = " + id;
		Log.e("INSERTANDO FAV", aux);
		db.execSQL(aux);

		aux = "UPDATE events SET isFavorite = 0 WHERE _idEvent = " + id;
		Log.e("UPDATING FAV", aux);
		db.execSQL(aux);
	}

	public void deleteAllData() {
		SQLiteDatabase db = getWritableDatabase();

		db.execSQL("DELETE FROM events");
		db.execSQL("DELETE FROM favorites");

		Log.w("DELETED", "All data has been deleted");
	}

	/**
	 * Create a new note using the title and body provided. If the note is
	 * successfully created return the new rowId for that note, otherwise return
	 * a -1 to indicate failure.
	 * 
	 * @param title
	 *            the title of the note
	 * @param body
	 *            the body of the note
	 * @return rowId or -1 if failed
	 */
	/*
	 * public long createNote(String title, String body) { Log.e("INSERT",
	 * "NotesDbAdapter,createNote()");
	 * 
	 * ContentValues initialValues = new ContentValues();
	 * initialValues.put(KEY_TITLE, title); initialValues.put(KEY_BODY, body);
	 * 
	 * return mDb.insert(DATABASE_TABLE, null, initialValues); }
	 */

	/**
	 * Delete the note with the given rowId
	 * 
	 * @param rowId
	 *            id of note to delete
	 * @return true if deleted, false otherwise
	 */
	/*
	 * public boolean deleteNote(long rowId) {
	 * 
	 * return mDb.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0; }
	 */

	/**
	 * Return a Cursor over the list of all notes in the database
	 * 
	 * @return Cursor over all notes
	 */
	/*
	 * public Cursor fetchAllNotes() {
	 * 
	 * return mDb.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_TITLE,
	 * KEY_BODY}, null, null, null, null, null); }
	 */

	/**
	 * Return a Cursor positioned at the note that matches the given rowId
	 * 
	 * @param rowId
	 *            id of note to retrieve
	 * @return Cursor positioned to matching note, if found
	 * @throws SQLException
	 *             if note could not be found/retrieved
	 */
	/*
	 * public Cursor fetchNote(long rowId) throws SQLException {
	 * 
	 * Cursor mCursor =
	 * 
	 * mDb.query(true, DATABASE_TABLE, new String[] {KEY_ROWID, KEY_TITLE,
	 * KEY_BODY}, KEY_ROWID + "=" + rowId, null, null, null, null, null); if
	 * (mCursor != null) { mCursor.moveToFirst(); } return mCursor;
	 * 
	 * }
	 */

	/**
	 * Update the note using the details provided. The note to be updated is
	 * specified using the rowId, and it is altered to use the title and body
	 * values passed in
	 * 
	 * @param rowId
	 *            id of note to update
	 * @param title
	 *            value to set note title to
	 * @param body
	 *            value to set note body to
	 * @return true if the note was successfully updated, false otherwise
	 */
	/*
	 * public boolean updateNote(long rowId, String title, String body) {
	 * ContentValues args = new ContentValues(); args.put(KEY_TITLE, title);
	 * args.put(KEY_BODY, body);
	 * 
	 * return mDb.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) >
	 * 0; }
	 */
}
