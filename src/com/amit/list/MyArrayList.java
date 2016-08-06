package com.amit.list;

import java.util.Arrays;

public class MyArrayList<T> {

	private Object[] array;
	private int size;

	public MyArrayList() {
		super();
		array = new Object[2];
		size = 0;
	}

	public T[] getArray() {
		return (T[]) array;
	}

	public int size() {
		return size;
	}

	public void add(T object) {
		if (size == array.length) {
			array = Arrays.copyOf(array, array.length * 2);
		}
		array[size] = object;
		size++;
	}

	public void remove(int index) {
		if (index < size) {
			int temp = index + 1;
			while (temp < size) {
				array[temp - 1] = array[temp];
				temp++;
			}
			size--;
		}
	}

	public T get(int index) {
		if (index < size) {
			return (T) array[index];
		}

		return null;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MyArrayList [");
		if (array != null) {
			for (int i = 0; i < size; i++) {
				if (i > 0)
					builder.append(", ");
				builder.append(array[i]);
			}
		}
		builder.append("]");
		return builder.toString();
	}

	public static void main(String[] args) {
		MyArrayList<String> list = new MyArrayList<String>();
		list.add("amit");
		list.add(" ");
		list.remove(0);
		list.remove(0);
		list.add("amit");
		System.out.println(list);

	}

}
