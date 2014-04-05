package com.flyingh.demo2;

import java.time.LocalTime;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CallableAndFutureTest {
	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
		CompletionService<Integer> cs = new ExecutorCompletionService<>(Executors.newFixedThreadPool(10));
		for (int i = 0; i < 10; i++) {
			final int taskIndex = i;
			cs.submit(new Callable<Integer>() {

				@Override
				public Integer call() throws Exception {
					Thread.sleep(1000);
					return taskIndex;
				}
			});
		}
		for (int i = 0; i < 10; i++) {
			System.out.println(cs.take().get());
		}
	}

	@SuppressWarnings("unused")
	private static void test() throws InterruptedException, ExecutionException {
		Future<String> future = Executors.newSingleThreadExecutor().submit(new Callable<String>() {

			@Override
			public String call() throws Exception {
				Thread.sleep(TimeUnit.SECONDS.toMillis(2));
				return "hello world!";
			}
		});
		System.out.println(LocalTime.now().getSecond());
		System.out.println("get result:" + future.get());
		System.out.println(LocalTime.now().getSecond());
	}
}
