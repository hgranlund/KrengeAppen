<<<<<<< HEAD
package com.rullelinjeapp.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
	int cellWidth;
	int gridBottom;

	public DrawingBoatLines(Context context, AttributeSet attrs) {
		super(context, attrs);
		pupulateVariables();
		// TODO Auto-generated constructor stub
	}

	public DrawingBoatLines(Context context) {
		super(context);
		pupulateVariables();
		// TODO Auto-generated constructor stub
	}
	private void pupulateVariables() {
		cellWidth=30;
		gridBottom= getBottom() - getHeight()%cellWidth;
	}
	
	private int getYAxes(){
		int numberOfLines = getHeight()/cellWidth;
		return (int) (numberOfLines * 2.0 / 3.0 * cellWidth); 
//		return getHeight()/2;
	}

	private float[] getPointsFromAngle(double angle){
		 float kat= (float) Math.tan(angle)*getWidth()/2;
		 float[] points = {0,getYAxes()+kat,
				 		 getWidth(),getYAxes()-kat};
		return points; 
	}


	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		blue.setColor(Color.RED);
		black.setColor(Color.BLACK);
		gridBottom= getBottom() - getHeight()%cellWidth;
		
		
		
		for (int i = 0; i <= getWidth(); i += cellWidth) {
			canvas.drawLine(i, getTop(), i, gridBottom, black);
		}
		for (int i = getTop(); i <= getHeight(); i += cellWidth) {
			canvas.drawLine(0, i, getWidth(), i, black);
		}
		
		canvas.drawLines(getPointsFromAngle(0.5), blue);
		
		logm("Views height: " + getHeight() + " Views width: " + getWidth()
				+ " viewTop: " + getTop() + " viewBottom: " + getBottom()
				+ " myBottom: " + gridBottom);
	}

=======
package com.rullelinjeapp.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
	int cellWidth;
	int gridBottom;

	public DrawingBoatLines(Context context, AttributeSet attrs) {
		super(context, attrs);
		pupulateVariables();
		// TODO Auto-generated constructor stub
	}

	public DrawingBoatLines(Context context) {
		super(context);
		pupulateVariables();
		// TODO Auto-generated constructor stub
	}
	private void pupulateVariables() {
		cellWidth=30;
		gridBottom= getBottom() - getHeight()%cellWidth;
	}
	
	private int getYAxes(){
		int numberOfLines = getHeight()/cellWidth;
		return (int) (numberOfLines * 2.0 / 3.0 * cellWidth); 
//		return getHeight()/2;
	}

	private float[] getPointsFromAngle(double angle){
		 float kat= (float) Math.tan(angle)*getWidth()/2;
		 float[] points = {0,getYAxes()+kat,
				 		 getWidth(),getYAxes()-kat};
		return points; 
	}


	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		blue.setColor(Color.RED);
		black.setColor(Color.BLACK);
		gridBottom= getBottom() - getHeight()%cellWidth;
		
		
		
		for (int i = 0; i <= getWidth(); i += cellWidth) {
			canvas.drawLine(i, getTop(), i, gridBottom, black);
		}
		for (int i = getTop(); i <= getHeight(); i += cellWidth) {
			canvas.drawLine(0, i, getWidth(), i, black);
		}
		
		canvas.drawLines(getPointsFromAngle(0.5), blue);
		
		logm("Views height: " + getHeight() + " Views width: " + getWidth()
				+ " viewTop: " + getTop() + " viewBottom: " + getBottom()
				+ " myBottom: " + gridBottom);
	}

>>>>>>> 5441153a9da9b9ca0c434b283300d85603bfe13d
}