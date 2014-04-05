package com.flyingh.demo2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierDemo {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		final CyclicBarrier cb = new CyclicBarrier(5);
		for (int i = 0; i < 5; i++) {
			executorService.execute(new Runnable() {

				@Override
				public void run() {
					try {
						System.out.println("arrived to place A:" + cb.getNumberWaiting() + " is waiting");
						cb.await();
						System.out.println("arrived to place B:" + cb.getNumberWaiting() + " is waiting");
						cb.await();
						System.out.println("arrived to place C:" + cb.getNumberWaiting() + " is waiting");
						cb.await();

					} catch (InterruptedException | BrokenBarrierException e) {
						e.printStackTrace();
					}
				}
			});
		}
		executorService.shutdown();
	}
}
