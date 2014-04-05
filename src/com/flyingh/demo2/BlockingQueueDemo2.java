package com.flyingh.demo2;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BlockingQueueDemo2 {
	static class Task {
		private final BlockingQueue<Integer> subBlockingQueue = new ArrayBlockingQueue<>(1);
		private final BlockingQueue<Integer> mainBlockingQueue = new ArrayBlockingQueue<Integer>(1) {
			private static final long serialVersionUID = 1L;
			{
				try {
					put(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};

		public void sub(int count) throws InterruptedException {
			subBlockingQueue.put(new Random().nextInt(10));
			for (int i = 0; i < count; i++) {
				System.out.println(Thread.currentThread().getName() + " is running:" + i);
			}
			Thread.sleep(new Random().nextInt(3000));
			mainBlockingQueue.take();
		}

		public void main(int count) throws InterruptedException {
			mainBlockingQueue.put(new Random().nextInt(10));
			for (int i = 0; i < count; i++) {
				System.out.println(Thread.currentThread().getName() + " is running:" + i);
			}
			Thread.sleep(new Random().nextInt(1000));
			subBlockingQueue.take();
		}

	}

	public static void main(String[] args) throws InterruptedException {
		final Task task = new Task();
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 5; i++) {
			executorService.execute(new Runnable() {

				@Override
				public void run() {
					try {
						task.sub(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			task.main(10);
		}
		executorService.shutdown();
	}
}
