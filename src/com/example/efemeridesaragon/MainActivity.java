package com.example.efemeridesaragon;

import java.util.Vector;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends ListActivity {

	private ListView LV;
	private EventsDbAdapter mDbHelper;
	private Cursor mEventsCursor;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		SQLiteEvents db = new SQLiteEvents(this);

		// this.setTitle("Conversor de Unidades");

		// this.setListAdapter(new EventListAdapter(this, V));

		//db.deleteAllData();
		//insertarDatos(db);

		Vector<Event> V = db.fetchAllEvents();
		if (V == null || V.size() == 0) {
			insertarDatos(db);
       	 	Log.w("INSERTANDO", "Insertando nuevos datos");

		} 

		this.setListAdapter(new EventListAdapter(this, db.fetchAllEvents(), db));

	}

	// **************** BORRAR BORRAR BORRAR BORRAR ***************************
	public void insertarDatos(SQLiteEvents db) {
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
       	 	db.insertEvent(e);
        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
