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

		//db.deleteAllData(); 

		Vector<Event> V = db.fetchAllEvents();
		if (V == null || V.size() == 0) {
       	 	Log.w("INSERTANDO", "Insertando nuevos datos");
       	 	insertarDatos(db);
		}
		
		for (Event e : V) {
			Log.w("EVENTO: ", e.toString());
		}

		this.setListAdapter(new EventListAdapter(this, db.fetchAllEvents(), db));
		
		//pruebaFechas(db);

	}
	
	
	
	// **************** BORRAR BORRAR BORRAR BORRAR ***************************
	public void pruebaFechas(SQLiteEvents db) {
		Event e = new Event(1, "2015-03-04", true, "Prueba de fechas con modulos en la búsqueda", 0);
		
		//db.insertEvent(e);
		
		//String query = "SELECT _idEvent, strftime('%m-%d', date), event, type, isFavorite FROM events";
		String query = "SELECT * FROM events WHERE strftime('%d', date) = '04'";
		
		Vector<Event> V = db.fetchAux(query);
		
		if (V == null || V.size() == 0) {
       	 	Log.w("V vacio", "V vacio");
		} 
		else {
       	 	Log.w("V NO vacio", "V no vacio");
		}
		
		for (Event d : V) {
			Log.w("EVENTO: ", d.toString());
		}
		
		this.setListAdapter(new EventListAdapter(this, V, db));

	}
	
	

	// **************** BORRAR BORRAR BORRAR BORRAR ***************************
	public void insertarDatos(SQLiteEvents db) {
		Vector<Event> V = new Vector<Event>();
		
		V.add(new Event(1,"2015-03-06", false, "texto1",1));
        V.add(new Event(2,"2015-03-06", false, "texto2",1));
        V.add(new Event(3,"2015-03-06", false, "texto3",1));
        V.add(new Event(4,"2015-03-06", false, "texto4",1));
        V.add(new Event(5,"2015-03-07", false, "texto5",1));
        V.add(new Event(6,"2015-03-07", false, "texto6",1));
        V.add(new Event(7,"2015-03-07", false, "texto7",1));
        V.add(new Event(8,"2015-03-07", false, "texto8",1));
        V.add(new Event(9,"2015-03-07", false, "texto9",1));
        V.add(new Event(10,"2015-03-08", false, "texto10",1));
        V.add(new Event(11,"2015-03-08", false, "texto11",1));
        V.add(new Event(12,"2015-03-08", false, "textoo12",1));
		V.add(new Event(1,"2015-03-01", false, "texto1",1));
		V.add(new Event(1,"2015-03-01", false, "texto1",1));
		V.add(new Event(1,"2015-03-01", false, "texto1",1));
		V.add(new Event(1,"2015-03-01", false, "texto1",1));
		V.add(new Event(1,"2015-03-03", false, "texto1",1));
		V.add(new Event(1,"2015-03-03", false, "texto1",1));
		V.add(new Event(1,"2015-03-03", false, "texto1",1));
		V.add(new Event(1,"2015-03-03", false, "texto1",1));
		V.add(new Event(1,"2015-03-11", false, "texto1",1));
		V.add(new Event(1,"2015-03-11", false, "texto1",1));
		V.add(new Event(1,"2015-03-11", false, "texto1",1));
		V.add(new Event(1,"2015-03-11", false, "texto1",1));
		V.add(new Event(1,"2015-03-13", false, "texto1",1));
		V.add(new Event(1,"2015-03-13", false, "texto1",1));
		V.add(new Event(1,"2015-03-13", false, "texto1",1));

		V.add(new Event(1,"2015-04-06", false, "texto1",1));
        V.add(new Event(2,"2015-04-06", false, "texto2",1));
        V.add(new Event(3,"2015-04-06", false, "texto3",1));
        V.add(new Event(4,"2015-04-06", false, "texto4",1));
        V.add(new Event(5,"2015-04-07", false, "texto5",1));
        V.add(new Event(6,"2015-04-07", false, "texto6",1));
        V.add(new Event(7,"2015-04-07", false, "texto7",1));
        V.add(new Event(8,"2015-04-07", false, "texto8",1));
        V.add(new Event(9,"2015-04-07", false, "texto9",1));
		V.add(new Event(1,"2015-04-01", false, "texto1",1));
		V.add(new Event(1,"2015-04-01", false, "texto1",1));
		V.add(new Event(1,"2015-04-01", false, "texto1",1));
		V.add(new Event(1,"2015-04-01", false, "texto1",1));
		V.add(new Event(1,"2015-04-03", false, "texto1",1));
		V.add(new Event(1,"2015-04-03", false, "texto1",1));
		V.add(new Event(1,"2015-04-03", false, "texto1",1));
		V.add(new Event(1,"2015-04-03", false, "texto1",1));
		V.add(new Event(1,"2015-04-11", false, "texto1",1));
		V.add(new Event(1,"2015-04-11", false, "texto1",1));
		V.add(new Event(1,"2015-04-11", false, "texto1",1));
		V.add(new Event(1,"2015-04-11", false, "texto1",1));
		V.add(new Event(1,"2015-04-13", false, "texto1",1));
		V.add(new Event(1,"2015-04-13", false, "texto1",1));
		V.add(new Event(1,"2015-04-13", false, "texto1",1));

		V.add(new Event(1,"2015-05-06", false, "texto1",1));
        V.add(new Event(2,"2015-05-06", false, "texto2",1));
        V.add(new Event(3,"2015-05-06", false, "texto3",1));
        V.add(new Event(4,"2015-05-06", false, "texto4",1));
        V.add(new Event(5,"2015-05-07", false, "texto5",1));
        V.add(new Event(6,"2015-05-07", false, "texto6",1));
        V.add(new Event(7,"2015-05-07", false, "texto7",1));
        V.add(new Event(8,"2015-05-07", false, "texto8",1));
        V.add(new Event(9,"2015-05-07", false, "texto9",1));
		V.add(new Event(1,"2015-05-01", false, "texto1",1));
		V.add(new Event(1,"2015-05-01", false, "texto1",1));
		V.add(new Event(1,"2015-05-01", false, "texto1",1));
		V.add(new Event(1,"2015-05-01", false, "texto1",1));
		V.add(new Event(1,"2015-05-03", false, "texto1",1));
		V.add(new Event(1,"2015-05-03", false, "texto1",1));
		V.add(new Event(1,"2015-05-03", false, "texto1",1));
		V.add(new Event(1,"2015-05-03", false, "texto1",1));
		V.add(new Event(1,"2015-05-11", false, "texto1",1));
		V.add(new Event(1,"2015-05-11", false, "texto1",1));
		V.add(new Event(1,"2015-05-11", false, "texto1",1));
		V.add(new Event(1,"2015-05-11", false, "texto1",1));
		V.add(new Event(1,"2015-05-13", false, "texto1",1));
		V.add(new Event(1,"2015-05-13", false, "texto1",1));
		V.add(new Event(1,"2015-05-13", false, "texto1",1));

        
        
        for (Event e : V) {
       	 	Log.w("INSERTANDO MAIN", "Insertando " + e.toString());
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
