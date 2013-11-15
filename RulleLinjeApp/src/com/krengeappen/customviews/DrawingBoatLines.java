package com.krengeappen.customviews;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


import com.krengeappen.R;

public class DrawingBoatLines extends View {
	final static private String TAG = "##### DrawingBoatLines";

	public void logm(String line) {
		Log.i(TAG, line);
	}

	Paint green = new Paint();
	Paint black = new Paint();
	Paint lightred = new Paint();
	Paint orange = new Paint();
	Paint purple = new Paint();
	Paint[] paints = { green, lightred, orange, purple };
	int[] drawables = { R.drawable.inclineboat72p_green_0f8000,
			R.drawable.inclineboat72p_lightred_f03932,
			R.drawable.inclineboat72p_orange_fc9700,
			R.drawable.inclineboat72p_purple_8e2abf };
	int cellWidth;
	int gridBottom;
	int selectedAngleIndex;
	public ArrayList<Double> angles = new ArrayList<Double>();

	public DrawingBoatLines(Context context, AttributeSet attrs) {
		super(context, attrs);
		pupulateVariables();
	}

	public int setAngle(double angle) {
		angles.add(angle);
		selectedAngleIndex = angles.size() - 1;
		this.invalidate();
		return selectedAngleIndex;
	}

	public void setSelectedIndex(int index) {
		selectedAngleIndex = index;
		this.invalidate();
	}

	public DrawingBoatLines(Context context) {
		super(context);
		pupulateVariables();
	}

	private void pupulateVariables() {
		cellWidth = 30;
		green.setARGB(255, 15, 128, 0);
		black.setColor(Color.BLACK);
		lightred.setARGB(255, 240, 57, 50);
		orange.setARGB(255, 252, 151, 0);
		purple.setARGB(255, 142, 42, 191);
		selectedAngleIndex = 0;
	}

	private int getXAxes() {
		int numberOfLines = getHeight() / cellWidth;
		return (int) (numberOfLines * 2.0 / 3.0 * cellWidth);
	}

	private int getYAxes() {
		int numberOfLines = getWidth() / cellWidth;
		return (int) (numberOfLines / 2.0 * cellWidth);
	}

	private float[] getPointsFromAngle(double angle) {
		float[] points = new float[4];
		float kat = (float) Math.tan(angle) * getWidth() / 2;
		points[0] = 0;
		points[2] = getWidth();
		points[1] = getXAxes() + kat;
		points[3] = getXAxes() - kat;
		return points;
	}

	public void drawR(Canvas canvas, int angleToDraw) {
		gridBottom = getBottom() - getHeight() % cellWidth;

		// draw grid
		for (int i = 0; i <= getWidth(); i += cellWidth) {
			canvas.drawLine(i, getTop(), i, gridBottom, black);
		}
		for (int i = getTop(); i <= getHeight(); i += cellWidth) {
			canvas.drawLine(0, i, getWidth(), i, black);
		}

		// draw axes
		float yAxes = getYAxes();
		canvas.drawRect(yAxes - 2, getTop(), yAxes + 2, gridBottom, black);
		float xAxes = getXAxes();
		canvas.drawRect(0, xAxes - 2, getWidth(), xAxes + 2, black);

		if (angles.size() < 1) {
			return;
		}
		// draw angelLines
		int lenPaints = paints.length;
		String angle_str = "";
		double angle = 0;
		if (angleToDraw == 999) {
			paints[selectedAngleIndex % lenPaints].setStrokeWidth(5);
			paints[selectedAngleIndex % lenPaints].setTextSize(cellWidth*2);
			for (int i = 0; i < angles.size(); i++) {
				angle = angles.get(i)*10;
				angle = Math.round(angle);
				angle = angle/10;
				if (angle == 0){
					angle_str = "0.0°";
				}
				else{
					
					angle_str = ""+angle+ '°';
				}
				
				canvas.drawLines(getPointsFromAngle(angles.get(i)), paints[i
						% lenPaints]);
			}
			canvas.drawText(angle_str, getWidth() - cellWidth*6, cellWidth*6, paints[selectedAngleIndex % lenPaints]);
			paints[selectedAngleIndex % lenPaints].setStrokeWidth(0);
			drawKrengeBoat(selectedAngleIndex, canvas);
		} else {
			angle = angles.get(angleToDraw)*10;
			angle = Math.round(angle);
			angle = angle/10;
			if (angle == 0){
				angle_str = "0.0°";
			}
			else{
				
				angle_str = ""+angle+ '°';
			}
			paints[angleToDraw % lenPaints].setStrokeWidth(5);
			paints[angleToDraw % lenPaints].setTextSize(cellWidth*2);
			canvas.drawText(angle_str, getWidth() - cellWidth*6, cellWidth*6, paints[angleToDraw % lenPaints]);
			canvas.drawLines(getPointsFromAngle(angles.get(angleToDraw)),
					paints[angleToDraw % lenPaints]);
			paints[angleToDraw % lenPaints].setStrokeWidth(0);
			drawKrengeBoat(angleToDraw, canvas);
		}
		
		// TODO draw angle half circle
	}

	private void drawKrengeBoat(int angleIndex, Canvas canvas) {
		Bitmap boat = BitmapFactory.decodeResource(getResources(),
				drawables[angleIndex]);
		Matrix matrix = new Matrix();
		double rad = -angles.get(angleIndex);
		float sin = (float) Math.sin(rad);
		float cos = (float) Math.cos(rad);
		float[] points = {
				cos,
				-sin,
				getYAxes() - ((boat.getWidth() / 2) * cos)
						+ ((boat.getHeight() / 2) * sin),
				sin,
				cos,
				getXAxes() - ((boat.getWidth() / 2) * sin)
						- ((boat.getHeight() / 2) * cos) - 3, 0F, 0F, 1F };

		matrix.setValues(points);
		// Toast.makeText(getContext(),
		// matrix.toString(), Toast.LENGTH_LONG).show();
		canvas.drawBitmap(boat, matrix, null);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		drawR(canvas, 999);

		logm("Views height: " + getHeight() + " Views width: " + getWidth()
				+ " viewTop: " + getTop() + " viewBottom: " + getBottom()
				+ " myBottom: " + gridBottom);
	}

}