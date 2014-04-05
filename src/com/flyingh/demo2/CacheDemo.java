package com.flyingh.demo2;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheDemo {
	static class Cache {
		private final ReadWriteLock lock = new ReentrantReadWriteLock();
		private final Map<String, Object> map = new HashMap<>();

		public Object get(String key) {
			try {
				lock.readLock().lock();
				if (map.containsKey(key)) {
					return map.get(key);
				} else {
					lock.readLock().unlock();
					lock.writeLock().lock();
					if (map.containsKey(key)) {
						lock.readLock().lock();
						lock.writeLock().unlock();
						return map.get(key);
					}
					Object result = query(key);
					map.put(key, result);
					lock.readLock().lock();
					lock.writeLock().unlock();
					return result;
				}
			} finally {
				lock.readLock().unlock();
			}
		}

		private Object query(String key) {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public static void main(String[] args) {

	}
}
