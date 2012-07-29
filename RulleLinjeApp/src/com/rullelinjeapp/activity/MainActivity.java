package com.rullelinjeapp.activity;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rullelinjeapp.R;

public class MainActivity extends Activity {

	final static private String TAG = "##### Main Activity";

	public void logm(String line) {
		Log.i(TAG, line);
	}

	final Context context = this;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setUpDialog();
		setUpFinnKrengevinkel();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		 getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	private void setUpFinnKrengevinkel() {
		Button finnKrengevinkelButton = (Button) findViewById(R.id.finnKrengevinkel);
		finnKrengevinkelButton
				.setOnClickListener(finnKrengevinkelButtonListener);
	}

	private OnClickListener finnKrengevinkelButtonListener = new OnClickListener() {
		public void onClick(View v) {
			logm("clicked krengebutton.");
			Intent ResultIntent = new Intent(MainActivity.this,
					BoatInclineView.class);
			ResultIntent.putExtra("angle", Math.random());
			MainActivity.this.startActivity(ResultIntent);
		}
	};


	private void setUpDialog() {
		final Dialog dialog = new Dialog(context);
		dialog.setContentView(R.layout.dialog_instruksjoner);
		dialog.setTitle("Instruksjoner");

		TextView text = (TextView) dialog.findViewById(R.id.text);
		text.setText(R.string.instruksjoner_hovedside);

		Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
		dialogButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		Button instruksjonButton = (Button) findViewById(R.id.instruksjon);
		instruksjonButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				 dialog.show();
			}
		});
	}
}
