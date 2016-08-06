package com.amit.monitor;

public class Consumer implements Runnable {

	private SharedResource resource;
	private String item;

	public Consumer(SharedResource resource, String item) {
		super();
		this.resource = resource;
		this.item = item;
	}

	@Override
	public void run() {
		synchronized (resource) {
			if (resource.size() == 0) {
				try {
					System.out.println(Thread.currentThread().getName()
							+ " is waiting...");
					resource.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
			resource.remove(item);
			resource.notify();
			System.out.println(Thread.currentThread().getName()
					+ " has consumed item: " + item);
		}

	}
}
