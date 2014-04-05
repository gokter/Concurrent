package com.flyingh.demo;

public class SubMainDemo {
	static class Task {
		private boolean isSub = true;

		public synchronized void sub(int count) throws InterruptedException {
			while (!isSub) {
				wait();
			}
			for (int i = 0; i < count; i++) {
				System.out.println("Sub:" + i);
			}
			isSub = false;
			notifyAll();
		}

		public synchronized void main(int count) throws InterruptedException {
			while (isSub) {
				wait();
			}
			for (int i = 0; i < count; i++) {
				System.out.println("Main:" + i);
			}
			isSub = true;
			notifyAll();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		final Task task = new Task();
		for (int i = 0; i < 5; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						task.sub(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
			task.main(20);
		}
	}
}
