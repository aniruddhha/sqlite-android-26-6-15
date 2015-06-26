package com.melayer.sqlite;

import com.melayer.helper.MyHelper;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button btnInsert, btnSelect;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final MyHelper helper = new MyHelper(this, "my_db.sqlite", null, 1);

		btnInsert = (Button) findViewById(R.id.btnInsert);
		btnInsert.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				SQLiteDatabase sqDb = helper.getWritableDatabase();

				ContentValues values = new ContentValues();
				values.put("MY_NAME", "android");
				values.put("MY_ID", 10);

				sqDb.insert("MY_TAB", null, values);

				// sqDb.execSQL("insert into MY_TAB values('iOS',20)");
				sqDb.close();
			}
		});

		btnSelect = (Button) findViewById(R.id.btnSelect);
		btnSelect.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				SQLiteDatabase sqDb = helper.getReadableDatabase();

				/*
				 * Cursor c = sqDb.query("MY_TAB", new String[] { "MY_NAME" },
				 * "MY_ID = ?", new String[] { "10" }, null, null, null);
				 */

				Cursor c = sqDb.query("MY_TAB", null, null, null, null, null,
						null);

				while (c.moveToNext()) {

					String name = c.getString(c.getColumnIndex("MY_NAME"));

					Log.i("######### NAME IS #######", "" + name);
				}

				sqDb.close();

			}
		});
	}

}
