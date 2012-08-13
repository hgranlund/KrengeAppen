package com.rullelinjeapp.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.rullelinjeapp.R;
import com.rullelinjeapp.R.id;
import com.rullelinjeapp.customviews.DrawingBoatLines;

public class BoatInclineView extends Activity {
	final static private String TAG = "##### BoatInclineView";

	public void logm(String line) {
		Log.i(TAG, line);
	}
	
	final static String basePath = Environment.getExternalStorageDirectory()
			.toString() + "/BoatApp";
	final static int ID_MENU_SAVE_CANVAS = 1;
	final static String photoPath = basePath + "/temp_photo.jpg";
	private static final int CAMERA_PIC_REQUEST = 1;
	DrawingBoatLines angleLineView;
	ArrayList<Button> angleButtons;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		setContentView(R.layout.activity_result);
		File baseFile = new File(basePath);
		if (!baseFile.exists()) {
			try {
				baseFile.mkdirs();
				baseFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			baseFile.mkdirs();
		}
		startCamera();
		setUpButtons();
		angleLineView = (DrawingBoatLines) findViewById(id.draw_boat_lines);
	}

	private void saveAllAngleLines() {
		for (int i = 0; i < angleLineView.angles.size(); i++) {
			saveCanvas(i);
		}
	}

	private void saveCanvas(int boatNumber) {
		FileOutputStream fileOutPut = null;
		File file = new File(basePath + File.separator + "BaatNummer"
				+ (boatNumber + 1) + ".jpg");
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			fileOutPut = new FileOutputStream(file);
			Bitmap bitmap = Bitmap.createBitmap(angleLineView.getWidth(),
					angleLineView.getHeight(), Bitmap.Config.ARGB_8888);
			Canvas canvas = new Canvas(bitmap);
			canvas.drawColor(Color.WHITE);
			angleLineView.drawR(canvas, boatNumber);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutPut);
			logm("Lagret Canvas");
			fileOutPut.flush();
			fileOutPut.close();
			fileOutPut = null;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logm("Klarte ikke lagre canvas");
		} catch (IOException e) {
			logm("Klarte ikke lage fil");
			e.printStackTrace();
		}
	}

	private void startCamera() {
		logm("clicked krengebutton.");
		Intent cameraIntent = new Intent(
				android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		File _photoFile = new File(photoPath);
		try {
			if (_photoFile.exists() == false) {
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

	private void setUpButtons() {
		angleButtons = new ArrayList<Button>();
		angleButtons.add((Button) findViewById(id.boat1));
		angleButtons.add((Button) findViewById(id.boat2));
		angleButtons.add((Button) findViewById(id.boat3));
		angleButtons.add((Button) findViewById(id.boat4));
		for (int i=0; i<angleButtons.size() ;i++){
			final int index = i;
			angleButtons.get(i).setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					angleLineView.setSelectedIndex(index);
				}
			});
		}
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
				Toast.makeText(getApplicationContext(),
						TAG + " bildet er lagret", Toast.LENGTH_LONG).show();
				addAngle(Math.random());
			} else {
				logm("Taking picture failed. Try again!");
			}
		}
	}
	
	private void addAngle(Double angle){
		int angleIndex = angleLineView.setAngle(angle);
		if (angleIndex >2){
			((Button) findViewById(id.finnKrengeVinkel_Result)).setEnabled(false);
		}
			angleButtons.get(angleIndex).setVisibility(0);	
		
		
	}

	// andoird menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, ID_MENU_SAVE_CANVAS, Menu.NONE,
				R.string.menu_save_all_lines);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == ID_MENU_SAVE_CANVAS) {
			saveAllAngleLines();
			return true;
		}
		return false;
	}

}
