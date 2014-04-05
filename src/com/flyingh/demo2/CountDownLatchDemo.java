package com.flyingh.demo2;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo {

	public static void main(String[] args) {
		final CountDownLatch orderCountDownLatch = new CountDownLatch(1);
		final CountDownLatch resultCountDownLatch = new CountDownLatch(3);
		ExecutorService executorService = Executors.newCachedThreadPool();
		for (int i = 0; i < 3; i++) {
			executorService.execute(new Runnable() {

				@Override
				public void run() {
					try {
						System.out.println(Thread.currentThread().getName() + " is waiting:");
						orderCountDownLatch.await();
						System.out.println(Thread.currentThread().getName() + " is running");
						resultCountDownLatch.countDown();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		try {
			System.out.println("start order:");
			orderCountDownLatch.countDown();
			System.out.println("wait for the result");
			resultCountDownLatch.await();
			System.out.println("all result is here");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		executorService.shutdown();
	}
}
