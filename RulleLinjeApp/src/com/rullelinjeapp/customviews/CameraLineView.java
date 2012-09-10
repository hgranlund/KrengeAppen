package com.rullelinjeapp.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class CameraLineView extends View {
	final static private String TAG = "##### DrawingBoatLines";
	int cellWidth;
	int gridBottom;

	public void logm(String line) {
		Log.i(TAG, line);
	}

	Paint red = new Paint();

	public CameraLineView(Context context, AttributeSet attrs) {
		super(context, attrs);
		cellWidth = 30;
		gridBottom = getBottom() - getHeight() % cellWidth;
	}

	public CameraLineView(Context context) {
		super(context);
		pupulateVariables();
	}

	private void pupulateVariables() {
		red.setColor(Color.RED);
	}

	private int getXAxes() {
		int numberOfLines = getWidth() / cellWidth;
		return (int) (numberOfLines / 2.0 * cellWidth);
	}


	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		float xAxes = getXAxes();
		red.setStrokeWidth(5);
		canvas.drawRect(xAxes - 2, getTop(), xAxes + 2, gridBottom, red);
		logm("Views height: " + getHeight() + " Views width: " + getWidth()
				+ " viewTop: " + getTop() + " viewBottom: " + getBottom()
				+ " myBottom: " + gridBottom);
	}

}
