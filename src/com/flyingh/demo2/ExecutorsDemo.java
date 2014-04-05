package com.flyingh.demo2;

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorsDemo {
	public static void main(String[] args) {
		// ExecutorService executorService = Executors.newFixedThreadPool(3);
		// ExecutorService executorService = Executors.newCachedThreadPool();
		// ExecutorService executorService = Executors.newSingleThreadExecutor();
		// for (int i = 0; i < 10; i++) {
		// final int taskIndex = i;
		// executorService.execute(new Runnable() {
		//
		// @Override
		// public void run() {
		// for (int j = 0; j < 10; j++) {
		// try {
		// Thread.sleep(TimeUnit.MILLISECONDS.toMillis(10));
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// System.out.println(Thread.currentThread().getName() + " loop " + j + " for task " + taskIndex);
		// }
		// }
		// });
		// }
		// executorService.shutdown();
		System.out.println("*********************");
		Executors.newScheduledThreadPool(3).scheduleWithFixedDelay(new Runnable() {

			@Override
			public void run() {
				System.out.println(LocalTime.now().getSecond());
				System.out.println("here");
			}
		}, 1, 2, TimeUnit.SECONDS);
	}
}
