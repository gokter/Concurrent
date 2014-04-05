package com.flyingh.demo2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo2 {
	public static void main(String[] args) {
		final Task task = new Task();
		ExecutorService executorService1 = Executors.newSingleThreadExecutor();
		ExecutorService executorService2 = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 5; i++) {
			executorService1.execute(new Runnable() {

				@Override
				public void run() {
					try {
						task.fun1(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			executorService2.execute(new Runnable() {

				@Override
				public void run() {
					try {
						task.fun2(15);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			try {
				task.main(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		executorService1.shutdown();
		executorService2.shutdown();
	}

	static class Task {
		private final Lock lock = new ReentrantLock();
		private final Condition condition1 = lock.newCondition();
		private final Condition condition2 = lock.newCondition();
		private final Condition condition3 = lock.newCondition();
		private int current = 1;

		public void fun1(int count) throws InterruptedException {
			try {
				lock.lock();
				if (current != 1) {
					condition1.await();
				}
				for (int i = 0; i < count; i++) {
					System.out.println(Thread.currentThread().getName() + ":" + i);
				}
				current = 2;
				condition2.signal();
			} finally {
				lock.unlock();
			}
		}

		public void fun2(int count) throws InterruptedException {
			try {
				lock.lock();
				if (current != 2) {
					condition2.await();
				}
				for (int i = 0; i < count; i++) {
					System.out.println(Thread.currentThread().getName() + ":" + i);
				}
				current = 3;
				condition3.signal();
			} finally {
				lock.unlock();
			}
		}

		public void main(int count) throws InterruptedException {
			try {
				lock.lock();
				if (current != 3) {
					condition3.await();
				}
				for (int i = 0; i < count; i++) {
					System.out.println(Thread.currentThread().getName() + ":" + i);
				}
				current = 1;
				condition1.signal();
			} finally {
				lock.unlock();
			}
		}
	}
}
