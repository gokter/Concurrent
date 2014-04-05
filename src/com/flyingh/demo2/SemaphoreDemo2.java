package com.flyingh.demo2;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo2 {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		final Task task = new Task();
		for (int i = 0; i < 5; i++) {
			executorService.execute(new Runnable() {

				@Override
				public void run() {
					task.sub(5);
				}
			});
			task.main(10);
		}
		executorService.shutdown();
	}

	static class Task {
		private final Semaphore semaphore = new Semaphore(1);
		private volatile boolean isSub = true;

		public void sub(int count) {
			try {
				while (!isSub) {
					semaphore.acquire();
				}
				for (int i = 0; i < count; i++) {
					System.out.println(Thread.currentThread().getName() + " is running:" + i);
				}
				Thread.sleep(new Random().nextInt(3000));
				isSub = false;
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaphore.release();
			}
		}

		public void main(int count) {
			try {
				while (isSub) {
					semaphore.acquire();
				}
				for (int i = 0; i < count; i++) {
					System.out.println(Thread.currentThread().getName() + " is running:" + i);
				}
				Thread.sleep(new Random().nextInt(3000));
				isSub = true;
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaphore.release();
			}
		}
	}
}
