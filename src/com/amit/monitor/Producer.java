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

					synchronized (resource) {
						if (resource.size() == resource.getMaxSize()) {
							System.out.println("Producer["
									+ Thread.currentThread().getName()
									+ "] is waiting...");
							resource.wait();

						}
					}
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
		/*
		 * 2 Synchronized blocks have been used means before producer produces
		 * by entering below synchronized block consumer can consume.
		 */

		// as soon as producer produces (by adding in sharedQueue) it notifies
		// consumerThread.

		synchronized (resource) {
			resource.addItem(item);
			System.out.println(Thread.currentThread().getName()
					+ " has added item: " + item);
			resource.notify();

		}
	}
}
