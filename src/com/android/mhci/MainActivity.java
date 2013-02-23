package com.android.mhci;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.mhci.adapter.TaskListAdapter;

public class MainActivity extends ListActivity {

	private Button addButton;
	private TaskListAdapter adapter;
	private TaskManagerApplication app;
	private Button removeButton;
	private EditText newTaskTxt;
	
	// top buttons
	private ImageButton approaching;
	private TextView currentTime;
	private Button completed;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        setUpViews();
        app = (TaskManagerApplication)getApplication();
        adapter = new TaskListAdapter(this, app.getCurrentTasks());
        setListAdapter(adapter);
    }
	
	@Override
	protected void onResume() {
		super.onResume();
		clearEntry();
		adapter.forceReload();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		adapter.toggleTaskCompleteAtPosition(position);
	}
	
	protected void removeCompletedTasks() {
		adapter.removeCompletedTasks();
	}
	
	private void setUpViews() {
		newTaskTxt = (EditText)findViewById(R.id.new_task);
		addButton = (Button)findViewById(R.id.add_button);
		removeButton = (Button)findViewById(R.id.remove_button);
		
		approaching = (ImageButton)findViewById(R.id.imageButton);
		currentTime = (TextView)findViewById(R.id.textView1);
		completed = (Button)findViewById(R.id.button3);
		
		addButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
				Bundle data = new Bundle();
				data.putString("TASK", newTaskTxt.getText().toString());
				Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
				intent.putExtras(data);
				startActivity(intent);
			}
		});
		removeButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				removeCompletedTasks();
			}
		});
		approaching.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			   Toast.makeText(MainActivity.this,
				"Approaching tasks Button is clicked!", Toast.LENGTH_SHORT).show();
			}
		});
		completed.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			   Toast.makeText(MainActivity.this,
				"comepleted tasks button is clicked!", Toast.LENGTH_SHORT).show();
 
			}
 
		});
	}

	private void clearEntry() {
		newTaskTxt.setText("");
	}

	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
