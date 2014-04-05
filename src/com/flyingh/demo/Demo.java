package com.flyingh.demo;

public class Demo {
	public static void main(String[] args) {
		new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName());
					System.out.println(getName());
				}
			}
		}.start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName());
				}
			}
		}, "ahha").start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("here");
			}
		}) {
			@Override
			public void run() {
				System.out.println("there");
			}
		}.start();
	}
}
