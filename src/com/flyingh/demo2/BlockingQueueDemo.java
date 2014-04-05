package com.flyingh.demo2;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BlockingQueueDemo {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		final BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(3);
		for (int i = 0; i < 2; i++) {
			executorService.execute(new Runnable() {

				@Override
				public void run() {
					try {
						while (true) {
							System.out.println("blockingQueue.size():" + blockingQueue.size() + "," + Thread.currentThread().getName()
									+ " start to put:");
							int value = new Random().nextInt(100);
							Thread.sleep((long) (Math.random() * 10000));
							System.out.println("blockingQueue.size():" + blockingQueue.size() + "," + Thread.currentThread().getName()
									+ " put value:" + value);
							blockingQueue.put(value);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		executorService.execute(new Runnable() {

			@Override
			public void run() {
				try {
					while (true) {
						System.out.println("blockingQueue.size():" + blockingQueue.size() + "," + Thread.currentThread().getName()
								+ " start to take:");
						Thread.sleep((long) (Math.random() * 10000));
						Integer value = blockingQueue.take();
						System.out.println("blockingQueue.size():" + blockingQueue.size() + "," + Thread.currentThread().getName() + " end to take:"
								+ value);
					}

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		executorService.shutdown();
	}
}
