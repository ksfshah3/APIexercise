package com.model;

public class JsonModel {

	private int id;
	private String name;
	private String last;
	private int age;
	private String gender;

	// Getter Methods
	public float getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getLast() {
		return last;
	}

	public float getAge() {
		return age;
	}

	public String getGender() {
		return gender;
	}

	// Setter Methods
	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "JsonModel [id=" + id + ", name=" + name + ", last=" + last + ", age=" + age + ", gender=" + gender
				+ "]";
	}	
	
}
