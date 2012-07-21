<<<<<<< HEAD:RulleLinjeApp/src/com/rullelinjeapp/activity/MainActivity.java
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
import com.rullelinjeapp.R.id;
import com.rullelinjeapp.R.layout;
import com.rullelinjeapp.R.menu;
import com.rullelinjeapp.R.string;

public class MainActivity extends Activity {

	final static private String TAG = "##### Main Activity";
	final static String photoPath = Environment.getExternalStorageDirectory().getName() + File.separatorChar + "temp_photo.jpg";


	private static final int CAMERA_PIC_REQUEST = 1;

	Button camera_button;

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
		Button finnKrengevinkelButton = (Button) findViewById(id.finnKrengevinkel);
		finnKrengevinkelButton
				.setOnClickListener(finnKrengevinkelButtonListener);

	}

	private OnClickListener finnKrengevinkelButtonListener = new OnClickListener() {
		public void onClick(View v) {
			Intent ResultIntent = new Intent(MainActivity.this, Result.class);
			ResultIntent.putExtra("angle", 0.5 );
			MainActivity.this.startActivity(ResultIntent);
//			logm("clicked krengebutton.");
//
//			Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//
//			File _photoFile = new File(photoPath);
//			try {
//				if (_photoFile.exists() == false) {
//					_photoFile.getParentFile().mkdirs();
//					_photoFile.createNewFile();
//				}
//			} catch (IOException e) {
//				Log.e(TAG, "Could not create file.", e);
//			}
//			Log.i(TAG + " photo path: ", photoPath);
//
//			Uri _fileUri = Uri.fromFile(_photoFile);
//			cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, _fileUri);
//			startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
		}
	};

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAMERA_PIC_REQUEST) {
			logm("Camera onActivityResult!" );
			if (resultCode == Activity.RESULT_OK) {
				Bitmap bitmap = BitmapFactory.decodeFile(photoPath);
				Toast.makeText(context, "bildet er lagret", Toast.LENGTH_LONG).show();
				Intent ResultIntent = new Intent(MainActivity.this, Result.class);
				ResultIntent.putExtra("angle", 0.5 );
				MainActivity.this.startActivity(ResultIntent);
			} else {
				logm("Taking picture failed. Try again!");
			}
		}
	}

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
=======
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
	final static String photoPath = Environment.getExternalStorageDirectory()
			.getName() + File.separatorChar + "temp_photo.jpg";

	private static final int CAMERA_PIC_REQUEST = 1;

	Button camera_button;

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

			Intent cameraIntent = new Intent(
					android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

			File _photoFile = new File(photoPath);
			try {
				if (_photoFile.exists() == false) {
					_photoFile.getParentFile().mkdirs();
					_photoFile.createNewFile();
				}
			} catch (IOException e) {
				Log.e(TAG, "Could not create file.", e);
			}
			Log.i(TAG + " photo path: ", photoPath);

			Uri _fileUri = Uri.fromFile(_photoFile);
			cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, _fileUri);
			startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
		}
	};

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAMERA_PIC_REQUEST) {
			logm("Camera onActivityResult!");
			if (resultCode == Activity.RESULT_OK) {
				Bitmap bitmap = BitmapFactory.decodeFile(photoPath);
				Toast.makeText(context, "bildet er lagret", Toast.LENGTH_LONG)
						.show();
			} else {
				logm("Taking picture failed. Try again!");
			}
		}
	}

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
>>>>>>> 97f34236e7e919f86e1b2edea101f85aa5624c0e:RulleLinjeApp/src/com/rullelinjeapp/activity/MainActivity.java
