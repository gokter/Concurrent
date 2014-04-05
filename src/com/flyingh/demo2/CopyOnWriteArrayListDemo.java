package com.flyingh.demo2;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

class Person {
	private String name;
	private int age;

	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

}

public class CopyOnWriteArrayListDemo {
	public static void main(String[] args) {
		List<Person> persons = new CopyOnWriteArrayList<>();
		persons.add(new Person("a", 18));
		persons.add(new Person("b", 20));
		persons.add(new Person("c", 19));
		for (Person person : persons) {
			if ("c".equals(person.getName())) {
				persons.remove(person);
			}
			System.out.println(person);
		}
		System.out.println(persons.size());
	}
}
