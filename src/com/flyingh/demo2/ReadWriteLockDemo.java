package com.flyingh.demo2;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
	public static void main(String[] args) {
		final Task task = new Task();
		ExecutorService executorService1 = Executors.newFixedThreadPool(3);
		ExecutorService executorService2 = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 3; i++) {
			executorService1.execute(new Runnable() {

				@Override
				public void run() {
					task.put();
				}
			});
			executorService2.execute(new Runnable() {

				@Override
				public void run() {
					task.take();
				}
			});
		}
	}

	static class Task {
		private int num;
		private ReadWriteLock lock = new ReentrantReadWriteLock();

		public void put() {
			try {
				lock.writeLock().lock();
				System.out.println(Thread.currentThread().getName() + " start put:");
				num = new Random().nextInt(100);
				Thread.sleep(500);
				System.out.println(Thread.currentThread().getName() + " end put");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.writeLock().unlock();
			}
		}

		public void take() {
			try {
				lock.readLock().lock();
				System.out.println(Thread.currentThread().getName() + " start take:");
				System.out.println("take:" + num);
				Thread.sleep(500);
				System.out.println(Thread.currentThread().getName() + " end take");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.readLock().unlock();
			}
		}
	}
}
