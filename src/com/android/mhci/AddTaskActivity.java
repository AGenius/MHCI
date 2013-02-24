package com.android.mhci;

import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.mhci.task.Task;

public class AddTaskActivity extends TaskManagerActivity {

	private Button addButton;
	private Button cancelButton;
	private Button locationButton;
	private Button timeButton;
	private Spinner priority;
	
	private PopupWindow pw;
	private Button saveAddButton;
	private Button cancelAddButton;
	private CustomDateTimePicker custom;
	
	private DateTime taskDate;
	private String taskPriority;
	private String taskLocation;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.task_option);

		setUpViews();
	}

	protected void addTask() {
		Task t = null;
		Bundle data = getIntent().getExtras();
		taskPriority = (String) priority.getSelectedItem();
		if (data != null) {
			String taskName = data.getString("TASK");
			if (taskDate == null)
				taskDate = new DateTime();
			t = new Task(taskName, taskPriority, taskDate);
		} else {
			finish();
		}
		if (taskLocation != "")
			t.setLocation(taskLocation);
		getStuffApplication().addTask(t);
		finish();
	}

	protected void cancel() {
		finish();
	}

	private void setUpViews() {
		addButton = (Button) findViewById(R.id.save_button);
		cancelButton = (Button) findViewById(R.id.cancel_button);
		locationButton = (Button) findViewById(R.id.location_button);
		timeButton = (Button) findViewById(R.id.date_button);
		priority = (Spinner) findViewById(R.id.priority_spinner);
		
		setDate();

		priority.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1, new String[] {
						"Normal", "Moderate", "High" }));
		priority.setSelection(0);

		addButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				addTask();
			}
		});
		cancelButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				cancel();
			}
		});
		locationButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				popLocationWindow();
			}
		});
		timeButton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						custom.showDialog();
					}
				});
	}

	private void setDate() {
		custom = new CustomDateTimePicker(this,
				new CustomDateTimePicker.ICustomDateTimeListener() {

					@Override
					public void onSet(Dialog dialog, Calendar calendarSelected,
							Date dateSelected, int year, String monthFullName,
							String monthShortName, int monthNumber, int date,
							String weekDayFullName, String weekDayShortName,
							int hour24, int hour12, int min, int sec,
							String AM_PM) {
						
						taskDate = new DateTime(calendarSelected);
						
						((TextView) findViewById(R.id.date_textView))
								.setText("REMIND ME ON-"
										+ calendarSelected
												.get(Calendar.DAY_OF_MONTH)
										+ ":" + monthNumber + ":" + year
										+ ",-" + hour12 + ":" + min
										+ " " + AM_PM);
					}

					@Override
					public void onCancel() {

					}
				});
		/**
		 * Pass Directly current time format it will return AM and PM if you set
		 * false
		 */
		custom.set24HourFormat(false);
		/**
		 * Pass Directly current data and time to show when it pop up
		 */
		custom.setDate(Calendar.getInstance());
		
	}

	private void popLocationWindow() {
		try {
			LayoutInflater inflater = (LayoutInflater) 
					AddTaskActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			View popupView = inflater.inflate(R.layout.location_popup,
					(ViewGroup) findViewById(R.id.popup_element));
			
			pw = new PopupWindow(popupView, 400, 600, true);
			pw.showAtLocation(popupView, Gravity.CENTER, 0, 0);
			
			saveAddButton = (Button) popupView.findViewById(R.id.save_address_button);
			cancelAddButton = (Button) popupView.findViewById(R.id.cancel_address_button);
						
			saveAddButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					pw.dismiss();
				}
			});
			cancelAddButton.setOnClickListener(new OnClickListener() {
			    public void onClick(View v) {
			        pw.dismiss();
			    }
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
