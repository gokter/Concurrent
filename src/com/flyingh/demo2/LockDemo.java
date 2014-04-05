package com.flyingh.demo2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {
	public static void main(String[] args) {
		final Task task = new Task();
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					try {
						task.print("hello world!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 30; i++) {
					try {
						task.print("hahahahhahahahhahahahahaha");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	static class Task {
		private Lock lock = new ReentrantLock();

		public void print(String string) throws InterruptedException {
			try {
				lock.lock();
				for (int i = 0, len = string.length(); i < len; i++) {
					Thread.sleep(10);
					System.out.print(string.charAt(i));
				}
				System.out.println();
			} finally {
				lock.unlock();
			}
		}
	}
}
