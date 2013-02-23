package com.android.mhci.task;

import java.util.Date;

public class Task {
	
	private String name;
	private String priority;
	private String location;
	private String details;
	private Date date;
	private boolean complete;

	public Task(String taskName) {
		name = taskName;
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

	public String toString() {
		return name;
	}

	public void toggleComplete() {
		complete = !complete;
	}

}
