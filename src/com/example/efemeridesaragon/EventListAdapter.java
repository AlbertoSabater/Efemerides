package com.example.efemeridesaragon;

import java.util.Vector;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class EventListAdapter extends BaseAdapter{
	
	private final Activity activity;
	private final Vector<Event> list;
	private SQLiteEvents db;
	
	public EventListAdapter (Activity activity, Vector<Event> list, SQLiteEvents db) {
		super();
		this.activity = activity;
		this.list = list;
		this.db = db;
	}

	
	public View getView(final int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = activity.getLayoutInflater();
		View view = inflater.inflate(R.layout.event_row,  null, true);
		
		TextView date = (TextView) view.findViewById(R.id.date);
		date.setText(list.elementAt(position).getDate());
		
		TextView text = (TextView) view.findViewById(R.id.text);
		text.setText(list.elementAt(position).getText());
		
		final ImageButton btFav = (ImageButton) view.findViewById(R.id.btFav);
		if (list.elementAt(position).isFavorite()) {
			btFav.setImageResource(R.drawable.btn_star_big_on);
		}
		else {
			btFav.setImageResource(R.drawable.btn_star_big_off);
		}
		btFav.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (list.elementAt(position).isFavorite()) {
					btFav.setImageResource(R.drawable.btn_star_big_off);
					list.elementAt(position).setFavorite(false);
					
					// Update BD
					db.deleteFavorite(list.elementAt(position).getId());
				}
				else {
					btFav.setImageResource(R.drawable.btn_star_big_on);
					list.elementAt(position).setFavorite(true);
					
					// Update BD
					db.insertFavorite(list.elementAt(position));
				}
			}
		});
				
		return view;
	}
	
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		return list.elementAt(arg0);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
}
	