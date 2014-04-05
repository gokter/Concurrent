package com.flyingh.demo;

import java.util.Random;

public class ThreadLocalDemo {
	private static int data;
	private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

	public static void main(String[] args) {
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					data = new Random().nextInt(100);
					threadLocal.set(data);
					System.out.println(Thread.currentThread().getName()
							+ " put data:" + data);
					new A().get();
					new B().get();
				}
			}).start();
		}
	}

	static class A {
		public void get() {
			System.out.println("A:" + Thread.currentThread().getName() + "-->"
					+ threadLocal.get());
		}
	}

	static class B {
		public void get() {
			System.out.println("B:" + Thread.currentThread().getName() + "-->"
					+ threadLocal.get());
		}
	}
}
