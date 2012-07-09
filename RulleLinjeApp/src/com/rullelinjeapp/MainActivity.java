package com.rullelinjeapp;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity {

	final Context context = this;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpDialog();
        
        

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    private void setUpDialog(){
        final Dialog dialog = new Dialog(context);
		dialog.setContentView(R.layout.dialog_instruksjoner);
		dialog.setTitle("Instruksjoner");

		TextView text = (TextView) dialog.findViewById(R.id.text);
		text.setText(R.string.instruksjoner_hovedside);

		Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
		dialogButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
        
        final Button button = (Button) findViewById(R.id.instruksjon);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	dialog.show();
            }
        });
    }
}
