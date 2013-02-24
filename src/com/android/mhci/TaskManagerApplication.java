package com.android.mhci;

import java.util.ArrayList;

import org.joda.time.DateTime;

import android.app.Application;
import android.util.Log;

import com.android.mhci.task.Task;

public class TaskManagerApplication extends Application {

	private ArrayList<Task> currentTasks;
	
	@Override
	public void onCreate() {
		super.onCreate();
		if (null == currentTasks) {
			currentTasks = new ArrayList<Task>();
		}
	}

	public void setCurrentTasks(ArrayList<Task> currentTasks) {
		this.currentTasks = currentTasks;
	}

	public ArrayList<Task> getCurrentTasks() {
		return currentTasks;
	}
	
	public void addTask(Task t) {
		assert(null != t);
		if (null == currentTasks) {
			currentTasks = new ArrayList<Task>();
		}
		currentTasks.add(t);
	}
	
	
	public ArrayList<Task> getCompletedTasks() {
		ArrayList<Task> completed = new ArrayList<Task>();
		for (Task t : currentTasks) {
			if (t.isComplete())
				completed.add(t);
		}
		return completed;
	}
	/*
	 * returns a list of approaching tasks in one week
	 */
	public ArrayList<Task> getApproachingTasks() {
		ArrayList<Task> approaching = new ArrayList<Task>();
		DateTime dt = new DateTime();
		dt.plusDays(7);
		for (Task t : currentTasks) {
			if (t.getDate().toLocalDate().equals(dt.toLocalDate()))
				approaching.add(t);
		}
		return approaching;
	}
}
