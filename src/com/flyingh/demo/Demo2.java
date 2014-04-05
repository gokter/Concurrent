package com.flyingh.demo;

public class Demo2 {
	static class Task {
		private static final int COUNT = 2;
		private int i;
		private boolean isAdding = true;
		private int addCount;

		public synchronized void add() throws InterruptedException {
			while (!isAdding) {
				wait();
			}
			++i;
			System.out.println(Thread.currentThread().getName() + " add j:" + i);
			if (++addCount % COUNT == 0) {
				isAdding = false;
			}
			notifyAll();
		}

		public synchronized void sub() throws InterruptedException {
			while (isAdding) {
				wait();
			}
			--i;
			System.out.println(Thread.currentThread().getName() + " add j:" + i);
			if (--addCount % 2 == 0) {
				isAdding = true;
			}
			notifyAll();
		}

	}

	public static void main(String[] args) {
		final Task task = new Task();
		Runnable addRunnable = new Runnable() {

			@Override
			public void run() {
				try {
					task.add();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		};
		Runnable subRunnable = new Runnable() {

			@Override
			public void run() {
				try {
					task.sub();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		new Thread(addRunnable, "A").start();
		new Thread(addRunnable, "B").start();
		new Thread(subRunnable, "C").start();
		new Thread(subRunnable, "D").start();
	}
}
