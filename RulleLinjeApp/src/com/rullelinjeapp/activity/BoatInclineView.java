package com.rullelinjeapp.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
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
	final static String basePath = Environment.getExternalStorageDirectory()
			.getName() + File.separatorChar + "SavedCanvas" + File.pathSeparatorChar;
	private static final int CAMERA_PIC_REQUEST = 1;
	DrawingBoatLines angleLineView;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		angleLineView = (DrawingBoatLines) findViewById(id.draw_boat_lines);
		startCamera();
		setUpFinnKrengevinkel();
	}
	
	private void saveCanvas(int boatNumber){
		FileOutputStream fileOutPut;
		try {
			fileOutPut = new FileOutputStream(new File(basePath + "båtNummer" +boatNumber));
			Bitmap  bitmap = Bitmap.createBitmap( angleLineView.getWidth(), angleLineView.getHeight(), Bitmap.Config.ARGB_8888);
			Canvas canvas = new Canvas(bitmap);
			angleLineView.draw(canvas); 
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutPut); 
			logm("Lagret Canvas");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logm("Klarte ikke lagre canvas");
		}

		//TODO save canvas as image...
	}
	
	private void startCamera(){
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
	private void setUpFinnKrengevinkel() {
		Button finnKrengevinkelButton = (Button) findViewById(R.id.finnKrengeVinkel_Result);
		finnKrengevinkelButton
				.setOnClickListener(finnKrengevinkelButtonListener);

	}
	
	private OnClickListener finnKrengevinkelButtonListener = new OnClickListener() {
		public void onClick(View v) {
			startCamera();
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
				saveCanvas(0);
			} else {
				logm("Taking picture failed. Try again!");
			}
		}
	}

}
