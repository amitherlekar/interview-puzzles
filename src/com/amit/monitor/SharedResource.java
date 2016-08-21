package com.amit.monitor;

import java.util.ArrayList;
import java.util.List;

public class SharedResource {

	private List<String> list;
	private int maxSize;

	public SharedResource(int maxSize) {
		super();
		this.maxSize = maxSize;
		System.out.println("Max size = " + maxSize);
		list = new ArrayList<String>();
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public int size() {
		System.out.println("Current size = " + this.list.size());
		return this.list.size();
	}

	public void addItem(String item) {
		this.list.add(item);
	}

	public void remove(String item) {
		list.remove(item);
	}
}
