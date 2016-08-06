package com.amit.monitor;

public class Producer implements Runnable {

	private SharedResource resource;
	private String item;

	public Producer(SharedResource resource, String item) {
		super();
		this.resource = resource;
		this.item = item;
	}

	@Override
	public void run() {

		synchronized (resource) {

			if (resource.size() == resource.getMaxSize()) {
				try {
					System.out.println("Producer["
							+ Thread.currentThread().getName()
							+ "] is waiting...");
					resource.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

			resource.addItem(item);
			System.out.println(Thread.currentThread().getName()
					+ " has added item: " + item);
			resource.notify();

		}

	}
}
