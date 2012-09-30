package com.rullelinjeapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.rullelinjeapp.R;

public class CameraActivity extends Activity implements SurfaceHolder.Callback,
		SensorEventListener {

	Camera mCamera;
	SurfaceView mSurfaceView;
	SurfaceHolder surfaceHolder;
	float lastXAccelero = 0;
	boolean isPreviewRunning = false;
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	
	final static private String TAG = "##### CameraActivity";

	public void logm(String line) {
		Log.i(TAG, line);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera_layout);
		Button finnKrengevinkelButton = (Button) findViewById(R.id.finnKrengeVinkel_Result);
		getWindow().setFormat(PixelFormat.UNKNOWN);
		mSurfaceView = (SurfaceView) findViewById(R.id.surfaceview);
		surfaceHolder = mSurfaceView.getHolder();
		surfaceHolder.addCallback(this);
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		mAccelerometer = mSensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		finnKrengevinkelButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				findAnlge();

			}
		});

	}


	private void findAnlge() {
		double angle = (Double) (lastXAccelero/2
				* Math.PI/10);
		logm("found angle: "+ angle); 
		Intent resultIntent = new Intent();
				resultIntent.putExtra("angle", angle);
		// TODO Add extras or a data URI to this intent as appropriate.
		setResult(Activity.RESULT_OK, resultIntent);
		releaseCamera();
		finish();
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		if (isPreviewRunning) {
			mCamera.stopPreview();
		}

		Parameters parameters = mCamera.getParameters();
		Display display = ((WindowManager) getSystemService(WINDOW_SERVICE))
				.getDefaultDisplay();

		if (display.getRotation() == Surface.ROTATION_0) {
			parameters.setPreviewSize(height, width);
			mCamera.setDisplayOrientation(90);
		}

		if (display.getRotation() == Surface.ROTATION_90) {
			parameters.setPreviewSize(width, height);
		}

		if (display.getRotation() == Surface.ROTATION_180) {
			parameters.setPreviewSize(height, width);
		}

		if (display.getRotation() == Surface.ROTATION_270) {
			parameters.setPreviewSize(width, height);
			mCamera.setDisplayOrientation(180);
		}

		mCamera.setParameters(parameters);
		previewCamera();
	}

	public void previewCamera() {
		try {
			mCamera.setPreviewDisplay(surfaceHolder);
			mCamera.startPreview();
			isPreviewRunning = true;
		} catch (Exception e) {
			logm("Cannot start preview" + e);
		}
	}

	public void surfaceCreated(SurfaceHolder holder) {
		mCamera = Camera.open();
		if (mCamera != null) {
			previewCamera();
		}
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		releaseCamera();

	}

	private void releaseCamera() {
		if (mCamera != null) {
			mCamera.stopPreview();
			mCamera.release();
			mCamera = null;
			isPreviewRunning = false;
		}
	}

	protected void onResume() {
		super.onResume();
		mSensorManager.registerListener(this, mAccelerometer,
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	protected void onPause() {
		super.onPause();
		mSensorManager.unregisterListener(this);
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

	public void onSensorChanged(SensorEvent event) {

		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			lastXAccelero = event.values[0];

			// ay=event.values[1];

			// az=event.values[2];
			// logm("xAksen: " + event.values[0] + "/n" + " yAksen: "
			// + event.values[1] + "/n" + " zAkses: " + event.values[2]);

		}
	}

}