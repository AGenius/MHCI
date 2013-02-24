package com.android.mhci.adapter;

import java.util.ArrayList;

import org.joda.time.DateTime;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.android.mhci.R;
import com.android.mhci.task.Task;
import com.android.mhci.view.TaskListItem;

public class TaskListAdapter extends BaseAdapter {

	private ArrayList<Task> tasks;
	private Context context;

	public TaskListAdapter(Context context, ArrayList<Task> tasks) {
		this.tasks = tasks;
		this.context = context;
	}

	public int getCount() {
		return tasks.size();
	}

	public Task getItem(int position) {
		return (null == tasks) ? null : tasks.get(position);
	}

	public long getItemId(int position) {
		return position;
	}
	
	public ArrayList<Task> getTasks() {
		return tasks;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		TaskListItem tli;
		if (null == convertView) {
			tli = (TaskListItem)View.inflate(context, R.layout.task_list_item, null);
		} else {
			tli = (TaskListItem)convertView;
		}
		tli.setTask(tasks.get(position));
		if (tasks.get(position).getPriority().equalsIgnoreCase("normal"))
			tli.setBackgroundColor(Color.CYAN);
		else if (tasks.get(position).getPriority().equalsIgnoreCase("moderate"))
			tli.setBackgroundColor(Color.GREEN);
		else 
			tli.setBackgroundColor(Color.RED);
		return tli;
	}

	public void forceReload() {
		notifyDataSetChanged();
	}

	public void toggleTaskCompleteAtPosition(int position) {
		Task task = getItem(position);
		task.toggleComplete();
		notifyDataSetChanged();
	}

	public void removeCompletedTasks() {
		ArrayList<Task> completedTasks = new ArrayList<Task>();
		for (Task task : tasks) {
			if (task.isComplete()) {
				completedTasks.add(task);
			}
		}
		tasks.removeAll(completedTasks);
		notifyDataSetChanged();
	}
}
