package com.android.mhci;

import java.util.ArrayList;

import org.joda.time.DateTime;

import android.app.Application;
import android.util.Log;

import com.android.mhci.task.Task;

public class TaskManagerApplication extends Application {

	private ArrayList<Task> currentTasks;
	private ArrayList<Task> allTasks;
	private ArrayList<Task> approachingTasks;
	
	@Override
	public void onCreate() {
		super.onCreate();
		if (null == allTasks) {
			allTasks = new ArrayList<Task>();
			currentTasks = new ArrayList<Task>();
			approachingTasks = new ArrayList<Task>();
		}
	}

	public void setallTasks(ArrayList<Task> tasks) {
		this.allTasks = tasks;
	}

	public ArrayList<Task> getallTasks() {
		return allTasks;
	}
	// get todays date tasks
	public ArrayList<Task> getCurrentTasks() {
		return currentTasks;
	}
	// get approaching tasks
	public ArrayList<Task> getApproachingTasks() {
		return approachingTasks;
	}
	
	public void addTask(Task t) {
		assert(null != t);
		if (null == allTasks) {
			allTasks = new ArrayList<Task>();
		}
		if (t.getDate().toLocalDate().toString().equals(new DateTime().toLocalDate().toString()))
			currentTasks.add(t);
		if (t.getDate().toLocalDate().toString().equals(new DateTime().plusDays(7).toLocalDate().toString()))
			approachingTasks.add(t);
		allTasks.add(t);
		
	}
	
	
	public ArrayList<Task> getCompletedTasks() {
		ArrayList<Task> completed = new ArrayList<Task>();
		for (Task t : allTasks) {
			if (t.isComplete())
				completed.add(t);
		}
		return completed;
	}
	/*
	 * returns a list of approaching tasks in one week
	 */
	/*
	public ArrayList<Task> getApproachingTasks() {
		ArrayList<Task> approaching = new ArrayList<Task>();
		DateTime dt = new DateTime();
		dt.plusDays(7);
		for (Task t : allTasks) {
			if (t.getDate().toLocalDate().equals(dt.toLocalDate()))
				approaching.add(t);
		}
		return approaching;
	}
	*/
}
