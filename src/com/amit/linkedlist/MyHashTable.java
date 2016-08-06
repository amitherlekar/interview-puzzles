package com.amit.linkedlist;

import java.util.Arrays;

class HashNode {

	private HashNode next;
	private String key;
	private String value;

	public void next(HashNode node) {
		this.next = node;
	}

	public HashNode next() {
		return this.next;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}

public class MyHashTable {

	private HashNode[] table;
	private int size;

	public MyHashTable() {
		table = new HashNode[10];
		size = 0;
	}

	private int hash(String key) {
		return key.hashCode() % table.length;
	}

	public void put(String key, String value) {
		if (size == table.length) {
			table = Arrays.copyOf(table, table.length + 10);
		}

		int hash = hash(key);

		System.out.println("Inserting into table at index: " + hash);
		HashNode newNode = new HashNode();
		newNode.setKey(key);
		newNode.setValue(value);

		if (table[hash] == null) {
			table[hash] = newNode;
		} else {
			HashNode p = table[hash];
			HashNode t = p;
			while (p.next() != null && !key.equals(p.getKey())) {
				t = p;
				System.out.println("Node [" + t.getKey() + "]=[" + t.getValue()
						+ "]");
				p = p.next();
			}
			if (key.equals(p.getKey())) {
				p.setValue(value);
			} else {
				p.next(newNode);
			}
		}
		size++;
	}

	public boolean remove(String key) {

		if (key == null)
			return false;

		int hash = hash(key);

		if (table[hash] == null) {
			return false;
		}
		HashNode p = table[hash];
		HashNode t = null;
		while (p.next() != null && !key.equals(p.getKey())) {
			t = p;
			System.out.println("Node [" + t.getKey() + "]=[" + t.getValue()
					+ "]");
			p = p.next();
		}
		if (key.equals(p.getKey())) {
			if (t == null)
				table[hash] = p.next();
			else
				t.next(p.next());
			size--;
			return true;
		}
		return false;
	}

	public String get(String key) {
		if (key == null)
			return null;

		int hash = hash(key);

		if (table[hash] != null || key != null) {
			HashNode n = table[hash];
			while (n != null && !n.getKey().equals(key)) {
				n = n.next();
			}

			if (n != null && key.equals(n.getKey())) {
				return n.getValue();
			}
		}

		return null;
	}

	public static void main(String[] args) {
		MyHashTable table = new MyHashTable();
		table.put("name", "amit");
		table.put("name", "anagha");
		table.put("name", "harsha");
		table.put("naem", "amit");
		System.out.println(table.get("name"));
		System.out.println(table.get("naem"));
		table.put("key1", "v1");
		System.out.println(table.remove("key1"));
		System.out.println(table.get("key1"));

	}
}
