package com.rullelinjeapp.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.PopupWindow;

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
		pupulateVariables();
	}

	public CameraLineView(Context context) {
		super(context);
		pupulateVariables();
	}

	private void pupulateVariables() {
		red.setColor(Color.RED);
		cellWidth = 30;
		gridBottom = getBottom() - getHeight() % cellWidth;
	}

	
	private int getXAxes() {
		int numberOfLines = getHeight() / cellWidth;
		return (int) (numberOfLines * 2.0 / 3.0 * cellWidth);
	}


	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		float xAxes = getXAxes();
		red.setColor(Color.RED);
		red.setStrokeWidth(8);
		canvas.drawLine(0+25,xAxes,getWidth()-25, xAxes, red);
		logm("Views height: " + getHeight() + " Views width: " + getWidth()
				+ " viewTop: " + getTop() + " viewBottom: " + getBottom()
				+ " myBottom: " + gridBottom);
	}

}
