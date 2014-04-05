package com.flyingh.demo2;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangerDemo {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		final Exchanger<String> exchanger = new Exchanger<>();
		executorService.execute(new Runnable() {

			@Override
			public void run() {
				try {
					String data = "hahaha!!!";
					System.out.println(Thread.currentThread().getName() + " start exchange with data " + data);
					Thread.sleep((long) (Math.random() * 10000));
					String result = exchanger.exchange(data);
					System.out.println(Thread.currentThread().getName() + " end exchange with result " + result);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		});
		executorService.execute(new Runnable() {

			@Override
			public void run() {
				try {
					String data = "hehehe";
					System.out.println(Thread.currentThread().getName() + " start exchange with data " + data);
					Thread.sleep((long) (Math.random() * 10000));
					String result = exchanger.exchange(data);
					System.out.println(Thread.currentThread().getName() + " end exchange with result " + result);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		});
		executorService.shutdown();
	}
}
