package com.flyingh.demo2;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		final Semaphore semaphore = new Semaphore(3);
		for (int i = 0; i < 10; i++) {
			executorService.execute(new Runnable() {

				@Override
				public void run() {
					try {
						semaphore.acquire();
						System.out.println(Thread.currentThread().getName() + " get the semaphore," + (3 - semaphore.availablePermits())
								+ " is running");
						Thread.sleep(new Random().nextInt(1000));
						System.out.println(Thread.currentThread().getName() + " before release:" + semaphore.availablePermits()
								+ " left,releasing...");
						semaphore.release();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		executorService.shutdown();

	}
}
