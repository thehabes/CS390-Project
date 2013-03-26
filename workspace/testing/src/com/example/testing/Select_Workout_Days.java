package com.example.testing;

import java.util.Calendar;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class Select_Workout_Days extends Activity{
		private CheckBox mon, tues, wed, thurs, fri, sat, sun; //Variables for the checkboxes
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_workout_days);
        
        //addListenerOnCheckbox(); //Listeners for checkboxes.  only uncomment if you plan to use the test code further down the page.
        addListenerOnSubmit(); //Listener for what to do with the days selected
        	
    }
	
	public void addListenerOnCheckbox(){
		// these are the listeners on the specific checkboxes. Names are correct
		mon = (CheckBox) findViewById(R.id.Monday);
		tues = (CheckBox) findViewById(R.id.Tuesday);
		wed = (CheckBox) findViewById(R.id.Wednesday);
		thurs= (CheckBox) findViewById(R.id.Thursday);
		fri = (CheckBox) findViewById(R.id.Friday);
		sat = (CheckBox) findViewById(R.id.Saturday);
		sun = (CheckBox) findViewById(R.id.Sunday);
		
		// defines variables for the checkboxes noted on the xml
		
		//the following code can be unchecked to see if the app has knowledge of what is checked before submit is pushed.
		/*
		mon.setOnClickListener(new OnClickListener() {
		 
			  @Override
			  public void onClick(View v) {
		             
				if (((CheckBox) v).isChecked()) {
					Toast.makeText(Select_Workout_Days.this,"Workin out Monday", 2000).show();
				}
		 
			  }
			});
		 
		tues.setOnClickListener(new OnClickListener() {
			 
			  @Override
			  public void onClick(View v) {
		               
				if (((CheckBox) v).isChecked()) {
					Toast.makeText(Select_Workout_Days.this,"Workin out Tuesday", 2000).show();
				}
		 
			  }
			});
		wed.setOnClickListener(new OnClickListener() {
			 
			  @Override
			  public void onClick(View v) {
		             
	 			if (((CheckBox) v).isChecked()) {
					Toast.makeText(Select_Workout_Days.this,"Workin out Wednesday", 2000).show();
				}
		 
			  }
			});
		thurs.setOnClickListener(new OnClickListener() {
			 
			  @Override
			  public void onClick(View v) {
		                
				if (((CheckBox) v).isChecked()) {
					Toast.makeText(Select_Workout_Days.this,"Workin out Thursday", 2000).show();
				}
		 
			  }
			});
		fri.setOnClickListener(new OnClickListener() {
			 
			  @Override
			  public void onClick(View v) {
		              
				if (((CheckBox) v).isChecked()) {
					Toast.makeText(Select_Workout_Days.this,"Workin out Friday", 2000).show();
				}
		 
			  }
			});
		sat.setOnClickListener(new OnClickListener() {
			 
			  @Override
			  public void onClick(View v) {
		                
				if (((CheckBox) v).isChecked()) {
					Toast.makeText(Select_Workout_Days.this,"Workin out Saturday", 2000).show();
				}
		 
			  }
			});
		sun.setOnClickListener(new OnClickListener() {
			 
			  @Override
			  public void onClick(View v) {
		      
				if (((CheckBox) v).isChecked()) {
					Toast.makeText(Select_Workout_Days.this,"Workin out Sunday", Toast.LENGTH_LONG).show();
				}
		 
			  }
			});
		  */
	
	
	}
	
	public void addListenerOnSubmit(){
		// same thing with as with the checkbox listener, except I have the button here. 
		mon = (CheckBox) findViewById(R.id.Monday);
		tues = (CheckBox) findViewById(R.id.Tuesday);
		wed = (CheckBox) findViewById(R.id.Wednesday);
		thurs= (CheckBox) findViewById(R.id.Thursday);
		fri = (CheckBox) findViewById(R.id.Friday);
		sat = (CheckBox) findViewById(R.id.Saturday);
		sun = (CheckBox) findViewById(R.id.Sunday);
		Button submit = (Button) findViewById(R.id.SubmitDays);
		
		Toast.makeText(this, "Preparing Alarms and Calendar...", Toast.LENGTH_LONG).show();
		

		Intent timerIntent = new Intent(this, AlarmBroadcastReceiver.class);
		final PendingIntent pIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 0 , timerIntent, 0);
		final Intent calIntent = new Intent(Intent.ACTION_INSERT);
		calIntent.setType("vnd.android.cursor.item/event");
		final AlarmManager myAlarm = (AlarmManager) getSystemService(ALARM_SERVICE);
		final Calendar objCalendar = Calendar.getInstance();
		objCalendar.set(Calendar.YEAR, objCalendar.get(Calendar.YEAR)); 
		objCalendar.set(Calendar.MONTH, objCalendar.get(Calendar.MONTH));
		objCalendar.set(Calendar.WEEK_OF_MONTH, objCalendar.get(Calendar.WEEK_OF_MONTH)); //sets it up starting this week.  Gives users time to prepare
		objCalendar.set(Calendar.HOUR_OF_DAY, 10); //10 AM is when the all day reminder starts
		objCalendar.set(Calendar.MINUTE, 0);
		objCalendar.set(Calendar.SECOND, 0);
		objCalendar.set(Calendar.MILLISECOND, 0);
		
		
		submit.setOnClickListener(new OnClickListener() {
			 
			  @Override
			  public void onClick(View v) {
		             
				  if (mon.isChecked()){
						objCalendar.set(Calendar.DAY_OF_WEEK, 1); // Monday of the next week 
					
							if(com.example.testing.Select_workout.loseWeight == true){
								// calendar stuff
								calIntent.putExtra(Events.TITLE, "Workout"); 
						        calIntent.putExtra(Events.DESCRIPTION, "Run a mile");    
						        calIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, objCalendar.getTimeInMillis());
						        //calIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endCal.getTimeInMillis()); //shouldnt need this if all day event
						        calIntent.putExtra(Events.ALL_DAY, "allDayFlag"); //Heres the all day event thing.  This can always be changed.
						        calIntent.putExtra(Events.VISIBLE, 1);
						        calIntent.putExtra(Events.HAS_ALARM, 1);
						        calIntent.putExtra(Events.RRULE, "FREQ=WEEKLY;COUNT=2;WKST=SU;BYDAY=MO"); // every monday for the next 12 weeks
						        startActivity(calIntent);
								myAlarm.set(AlarmManager.RTC_WAKEUP, objCalendar.getTimeInMillis(), pIntent); // Alarm is set for start time of event
								//Intent nextScreen = new Intent(getApplicationContext(), Third_Page.class);
						        //startActivity(thirdScreen);
							}
							
							else if (com.example.testing.Select_workout.gainMuscle == true){
								// calendar stuff
								calIntent.putExtra(Events.TITLE, "Workout"); 
						        calIntent.putExtra(Events.DESCRIPTION, "Lift Weights");    
						        calIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, objCalendar.getTimeInMillis());
						        //calIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endCal.getTimeInMillis());
						        calIntent.putExtra(Events.ALL_DAY, "allDayFlag"); //Heres the all day event thing.  This can always be changed.
						        calIntent.putExtra(Events.VISIBLE, 1);
						        calIntent.putExtra(Events.HAS_ALARM, 1);
						        calIntent.putExtra(Events.RRULE, "FREQ=WEEKLY;COUNT=2;WKST=SU;BYDAY=MO"); // every monday for the next 12 weeks
						        startActivity(calIntent);
								myAlarm.set(AlarmManager.RTC_WAKEUP, objCalendar.getTimeInMillis(), pIntent);	
								//Intent nextScreen = new Intent(getApplicationContext(), Third_Page.class);
						        //startActivity(thirdScreen);
							}
							
							else if (com.example.testing.Select_workout.both == true){
								// calendar stuff
								calIntent.putExtra(Events.TITLE, "Workout"); 
						        calIntent.putExtra(Events.DESCRIPTION, "Run a mile and lift weights");    
						        calIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, objCalendar.getTimeInMillis());
						        //calIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endCal.getTimeInMillis());
						        calIntent.putExtra(Events.ALL_DAY, "allDayFlag"); //Heres the all day event thing.  This can always be changed.
						        calIntent.putExtra(Events.VISIBLE, 1);
						        calIntent.putExtra(Events.HAS_ALARM, 1);
						        calIntent.putExtra(Events.RRULE, "FREQ=WEEKLY;COUNT=3;WKST=SU;BYDAY=MO"); // every monday for the next 3 weeks
						        startActivity(calIntent);
								myAlarm.set(AlarmManager.RTC_WAKEUP, objCalendar.getTimeInMillis(), pIntent);
								//Intent nextScreen = new Intent(getApplicationContext(), Third_Page.class);
						        //startActivity(thirdScreen);
							}
							// may need an else here in case none of them happen
				  }
					
					
					
					/*if (tues.isChecked()){
						if(com.example.testing.Select_workout.loseWeight == true){
							
							}
						
							else if (com.example.testing.Select_workout.gainMuscle == true){
							
							}
						
							else if (com.example.testing.Select_workout.both == true){
							
							}
							else{
							//ERROR
							}
					}
					if (wed.isChecked()){
						if(com.example.testing.Select_workout.loseWeight == true){
							
						}
					
						else if (com.example.testing.Select_workout.gainMuscle == true){
						
						}
					
						else if (com.example.testing.Select_workout.both == true){
						
						}
						else{
						//ERROR
						}
					}
					if (thurs.isChecked()){
						if(com.example.testing.Select_workout.loseWeight == true){
							
						}
					
						else if (com.example.testing.Select_workout.gainMuscle == true){
						
						}
					
						else if (com.example.testing.Select_workout.both == true){
						
						}
						else{
						//ERROR
						}
					}
					if (fri.isChecked()){
						if(com.example.testing.Select_workout.loseWeight == true){
							
						}
					
						else if (com.example.testing.Select_workout.gainMuscle == true){
						
						}
					
						else if (com.example.testing.Select_workout.both == true){
						
						}
						else{
						//ERROR
						}
					}
					if (sat.isChecked()){
						if(com.example.testing.Select_workout.loseWeight == true){
							
						}
					
						else if (com.example.testing.Select_workout.gainMuscle == true){
						
						}
					
						else if (com.example.testing.Select_workout.both == true){
						
						}
						else{
						//ERROR
						}
					}
					if (sun.isChecked()){
						if(com.example.testing.Select_workout.loseWeight == true){
							
						}
					
						else if (com.example.testing.Select_workout.gainMuscle == true){
						
						}
					
						else if (com.example.testing.Select_workout.both == true){
						
						}
						else{
						//ERROR
						} */
					}
		 
			  });
	}
		

		
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.select_workout_days, menu);
        return true;
			}
}
