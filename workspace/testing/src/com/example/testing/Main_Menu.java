package com.example.testing;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


public class Main_Menu extends Activity {
	public static final String PREFS_NAME = "winFitPref";
	
	PopupWindow popUp;
    LinearLayout layout;
    TextView tv;
    LayoutParams params;
    LinearLayout mainLayout;
    Button but;
    boolean click = true;

	
	/** Called when the activity is first created. */
	@Override
	public void onBackPressed() {
		Intent startMain = new Intent(Intent.ACTION_MAIN);
		startMain.addCategory(Intent.CATEGORY_HOME);
		startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(startMain);
	}
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main_menu);
	    
	    TextView Welcome = (TextView)findViewById(R.id.Welcome);
	    SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
	    String name = prefs.getString("userName", "Guest");
	    Welcome.setText("Welcome " + name+"!");
	    
	    Button btnFirstScreen = (Button) findViewById(R.id.GPS_Button);
	    
        //Listening to button event
        btnFirstScreen.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
		        //Starting a new Intent
		            Intent nextScreen = new Intent(getApplicationContext(), GPS.class);
			    // TODO Auto-generated method stub
		            startActivity(nextScreen);
			}
        });
        
        Button btnThirdScreen = (Button) findViewById(R.id.About_Us);
	    
        //Listening to button event
        btnThirdScreen.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
		        //Starting a new Intent
		            Intent thirdScreen = new Intent(getApplicationContext(), About_Us.class);
			    // TODO Auto-generated method stub
		            startActivity(thirdScreen);
			}
        });
        Button btnFourthScreen = (Button) findViewById(R.id.Workout_Button);
	    
        //Listening to button event
        btnFourthScreen.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
		        //Starting a new Intent
		            Intent thirdScreen = new Intent(getApplicationContext(), Select_workout.class);
			    // TODO Auto-generated method stub
		            startActivity(thirdScreen);
			}
        });
        Button btnSettings = (Button) findViewById(R.id.Settings);
	    
        //Listening to button event
        btnSettings.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
		        //Starting a new Intent
		            Intent settingsScreen = new Intent(getApplicationContext(), Settings.class);
			    // TODO Auto-generated method stub
		            startActivity(settingsScreen);
			}
        });
        Button btnWeather = (Button) findViewById(R.id.Weather);
	    
        //Listening to button event
        btnWeather.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				Uri uri = Uri.parse("http://www.weather.com/");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
			}
        });

	}
}
	
	/*public void timerAlert(View view) {
			Toast.makeText(this, "Preparing Alarms...", Toast.LENGTH_LONG).show();
			Intent timerIntent = new Intent(this, AlarmBroadcastReceiver.class);
			PendingIntent pIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 0 , timerIntent, 0);
			AlarmManager myAlarm = (AlarmManager) getSystemService(ALARM_SERVICE);
			myAlarm.set(AlarmManager.RTC_WAKEUP, objCalendar.getTimeInMillis(), pIntent);
			
			// thank you http://stackoverflow.com/questions/4700285/android-how-to-set-an-alarm-to-a-specific-date
			
		}
		
		public void calEvent(View view){
			Toast.makeText(this, "Events being added to calendar", Toast.LENGTH_LONG).show();
			Calendar beginCal = Calendar.getInstance(); //Current cal settings.  This should be known from the Alarm already though
	        beginCal.set(year, mnth, day, hrs, min); // This should also be info passed from the calendar in the alarm
	        long startTime = beginCal.getTimeInMillis(); // This also

	        Calendar endCal = Calendar.getInstance(); // and this
	        endCal.set(year, mnth, day, hrs, min); // this may have to be manually set
	        long endTime = endCal.getTimeInMillis();     

	        Intent intent = new Intent(Intent.ACTION_INSERT);
	        intent.setType("vnd.android.cursor.item/event");
	        intent.putExtra(Events.TITLE, "Title"); 
	        intent.putExtra(Events.DESCRIPTION, "RUNNNNNN");
	        intent.putExtra(Events.EVENT_LOCATION, "Dark side of the moon");     
	        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginCal.getTimeInMillis());
	        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endCal.getTimeInMillis());
	        intent.putExtra(Events.ALL_DAY, "allDayFlag"); //Heres the all day event thing.  This can always be changed.
	        intent.putExtra(Events.STATUS, 1);
	        intent.putExtra(Events.VISIBLE, 0);
	        intent.putExtra(Events.HAS_ALARM, 1);
	        startActivity(intent);
	        
	        // I didnt quite make this working condition yet.  I didn't because I notice that both the little methods will be using
	        //the same information for setting calendar events and alarms, so I'm going to wait until we have a concrete way of
	        // knowing what those values are going to be.  
		}
	}
*/
