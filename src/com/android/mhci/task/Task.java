package com.android.mhci.task;

import java.util.Date;

import org.joda.time.DateTime;

public class Task {
	
	private String name;
	private String priority;
	private String location;
	private String details;
	private DateTime date;
	private boolean complete;

	public Task(String name, String priority, DateTime date) {
		super();
		this.name = name;
		this.setPriority(priority);
		this.date = date;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public boolean isComplete() {
		return complete;
	}

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public String toString() {
		return name;
	}

	public void toggleComplete() {
		complete = !complete;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

}
