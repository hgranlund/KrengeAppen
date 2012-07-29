package com.rullelinjeapp.activity;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.rullelinjeapp.R;
import com.rullelinjeapp.R.id;
import com.rullelinjeapp.customviews.DrawingBoatLines;

public class BoatInclineView extends Activity{
	final static private String TAG = "##### BoatInclineView";

	public void logm(String line) {
		Log.i(TAG, line);
	}
	final static String photoPath = Environment.getExternalStorageDirectory()
			.getName() + File.separatorChar + "temp_photo.jpg";

	private static final int CAMERA_PIC_REQUEST = 1;
	DrawingBoatLines angleLineView;
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		angleLineView = (DrawingBoatLines) findViewById(id.draw_boat_lines);
		setUpFinnKrengevinkel();
	}
	private void setUpFinnKrengevinkel() {
		Button finnKrengevinkelButton = (Button) findViewById(R.id.finnKrengeVinkel_Result);
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
				Toast.makeText(getApplicationContext(),TAG + " bildet er lagret", Toast.LENGTH_LONG)
						.show();
				angleLineView.setAngle(Math.random());
			} else {
				logm("Taking picture failed. Try again!");
			}
		}
	}

}
