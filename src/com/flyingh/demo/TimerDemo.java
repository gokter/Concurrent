package com.flyingh.demo;

import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class TimerDemo {
	public static void main(String[] args) throws InterruptedException {
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				System.out.println("haha");
			}
		}, TimeUnit.SECONDS.toMillis(2));
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				System.out.println("bomb");
			}
		}, TimeUnit.SECONDS.toMillis(10), TimeUnit.SECONDS.toMillis(3));
		while (true) {
			System.out.println(LocalTime.now().getSecond());
			Thread.sleep(TimeUnit.SECONDS.toMillis(1));
		}
	}
}
