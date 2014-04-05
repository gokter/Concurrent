package com.flyingh.demo2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {
	static class Task {
		private final Lock lock = new ReentrantLock();
		private final Condition condition = lock.newCondition();
		private boolean isSub = true;

		public void sub(int count) throws InterruptedException {
			try {
				lock.lock();
				if (!isSub) {
					condition.await();
				}
				for (int i = 0; i < count; i++) {
					System.out.println(Thread.currentThread().getName() + ":" + i);
				}
				isSub = false;
				condition.signal();
			} finally {
				lock.unlock();
			}
		}

		public void main(int count) throws InterruptedException {
			try {
				lock.lock();
				if (isSub) {
					condition.await();
				}
				for (int i = 0; i < count; i++) {
					System.out.println(Thread.currentThread().getName() + ":" + i);
				}
				isSub = true;
				condition.signal();
			} finally {
				lock.unlock();
			}
		}

	}

	public static void main(String[] args) {
		final Task task = new Task();
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 10; i++) {
			executorService.execute(new Runnable() {

				@Override
				public void run() {
					try {
						task.sub(2);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			try {
				task.main(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
