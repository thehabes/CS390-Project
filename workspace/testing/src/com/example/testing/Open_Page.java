package com.example.testing;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class Open_Page extends Activity {

	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);

        Button next = (Button) findViewById(R.id.Button05);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Open_Page.class);
                startActivityForResult(myIntent, 0);
	    // TODO Auto-generated method stub
	}

        });
	}
}
