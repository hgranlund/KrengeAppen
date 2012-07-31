package com.rullelinjeapp.customviews;

import java.util.ArrayList;

import com.rullelinjeapp.R;

import android.R.id;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class DrawingBoatLines extends View {
	final static private String TAG = "##### DrawingBoatLines";

	public void logm(String line) {
		Log.i(TAG, line);
	}

	Paint blue = new Paint();
	Paint black = new Paint();
	Paint cyan = new Paint();
	Paint green = new Paint();
	Paint yellow = new Paint();
	Paint[] paints = { blue, cyan, green, yellow };
	int cellWidth;
	int gridBottom;
	int selectedAngle;
	public ArrayList<Double> angels = new ArrayList<Double>();

	public DrawingBoatLines(Context context, AttributeSet attrs) {
		super(context, attrs);
		pupulateVariables();
	}
	
	public void setAngle(double angel){
		angels.add(angel);
	}

	public DrawingBoatLines(Context context) {
		super(context);
		pupulateVariables();
	}

	private void pupulateVariables() {
		cellWidth = 30;
		blue.setColor(Color.BLUE);
		black.setColor(Color.BLACK);
		cyan.setColor(Color.CYAN);
		green.setColor(Color.GREEN);
		yellow.setColor(Color.YELLOW);
		selectedAngle = 0;
	}

	private int getYAxes() {
		int numberOfLines = getHeight() / cellWidth;
		return (int) (numberOfLines * 2.0 / 3.0 * cellWidth);
	}

	private int getXAxes() {
		int numberOfLines = getWidth() / cellWidth;
		return (int) (numberOfLines / 2.0 * cellWidth);
	}

	private float[] getPointsFromAngle(double angle) {
		float kat = (float) Math.tan(angle) * getWidth() / 2;
		float[] points = { 0, getYAxes() + kat, getWidth(), getYAxes() - kat };
		return points;
	}
	


	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		gridBottom = getBottom() - getHeight() % cellWidth;
		// draw grid
		for (int i = 0; i <= getWidth(); i += cellWidth) {
			canvas.drawLine(i, getTop(), i, gridBottom, black);
		}
		for (int i = getTop(); i <= getHeight(); i += cellWidth) {
			canvas.drawLine(0, i, getWidth(), i, black);
		}
		// draw angelLines
		int lenPaints = paints.length;
		for (int i = 0; i < angels.size(); i++) {
			canvas.drawLines(getPointsFromAngle(angels.get(i)), paints[i%lenPaints]);
		}
		Bitmap boat = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		canvas.drawBitmap(boat, 500	, 500, null);
		
		
		//TODO Draw boat 
		//TODO Draw fatLine
		
		//draw selectedBoat
//		float[] points =getPointsFromAngle(angels.get(selectedAngle));
//		Rect rect = new Rect((int)points[0], (int)points[1], (int)points[2], (int)points[3]);
//		canvas.drawRect(rect, paints[selectedAngle%lenPaints]);
		
		// draw axes
		float yAxes = getXAxes();
		canvas.drawRect(yAxes - 2, getTop(), yAxes + 2, gridBottom, black);
		float xAxes = getYAxes();
		canvas.drawRect(0, xAxes - 2, getWidth(), xAxes + 2, black);

		logm("Views height: " + getHeight() + " Views width: " + getWidth()
				+ " viewTop: " + getTop() + " viewBottom: " + getBottom()
				+ " myBottom: " + gridBottom);
	}

}