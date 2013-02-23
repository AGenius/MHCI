package com.android.mhci.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;

import com.android.mhci.task.Task;

public class TaskListItem extends LinearLayout {
	
	private Task task;
	private CheckedTextView checkbox;
	
	public TaskListItem(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	// get the reference for checkbox
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		checkbox = ((CheckedTextView)findViewById(android.R.id.text1));
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task t) {
		this.task = t;
		checkbox.setText(task.getName());
		checkbox.setChecked(task.isComplete());
	}	

}