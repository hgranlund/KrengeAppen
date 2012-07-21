package com.rullelinjeapp.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.rullelinjeapp.R;
import com.rullelinjeapp.customviews.DrawingBoatLines;

public class Result extends Activity {

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DrawingBoatLines drawBoat = new DrawingBoatLines(getApplicationContext()); 
//        setContentView(new DrawingBoatLines(this));
        setContentView(R.layout.activity_result);
    }


}

