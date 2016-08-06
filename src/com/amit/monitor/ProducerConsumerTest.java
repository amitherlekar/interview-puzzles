package com.amit.monitor;

public class ProducerConsumerTest {

	public static void main(String[] args) throws InterruptedException {
		SharedResource sharedResource = new SharedResource(3);
		for (int i = 0; i < 5; i++) {
			Runnable p = new Producer(sharedResource, "" + (i + 1));
			Thread producer = new Thread(p, "Producer-" + (i + 1));
			System.out.println("Starting thread: " + producer.getName());
			producer.start();
		}

		for (int i = 0; i < 6; i++) {
			Runnable c = new Consumer(sharedResource, "" + (i + 1));
			Thread consumer = new Thread(c, "Consumer-" + (i + 1));
			System.out.println("Starting thread: " + consumer.getName());
			consumer.start();
		}
	}
}
