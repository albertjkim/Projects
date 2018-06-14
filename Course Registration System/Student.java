package edu.nyu.cs.ak6118;

import java.io.*;
import java.util.*;

public class Student extends User implements Serializable, StudentInterface{
	private String username;
	private String password;
	public String firstName;
	public String lastName;
	public String name;
	public Student(String nameF, String nameL) {
		this.firstName = nameF;
		this.lastName = nameL;
		this.name = nameF + nameL;
	}
	public void view(ArrayList<Course> courseList) {
		for (Course course: courseList) {
			System.out.println(course.name);
		}
	}
	public void notFull(ArrayList<Course> courseList) {
		for (Course course: courseList) {
			if(course.max != course.registered) {
				System.out.println(course.name);
			}
		}
	}
	public void register(Student student, ArrayList<Course> courseList) {
		System.out.println("Please enter the Course you would like to register to: ");
		Scanner scn = new Scanner(System.in);
		String name = scn.nextLine();
		for (Course course: courseList) {
			if(course.name.equals(name)) {
				course.studentList.add(student);
				course.registered++;
			}
		}
	}
	public void withdraw(Student student, ArrayList<Course> courseList) {
		System.out.println("Please enter the Course you would like to withdraw from: ");
		Scanner scn = new Scanner(System.in);
		String name = scn.nextLine();
		for(Course course: courseList) {
			if(course.name.equals(name)) {
				for(Student student2:course.studentList) {
					if(student2.name.equals(student.name)) {
						course.studentList.remove(student);
					}
				}
			}
		}
		
	}
	public void registeredIn(Student student, ArrayList<Course> courseList) {
		for(Course course:courseList) {
			for(Student student2:course.studentList) {
				if(student2.name.equals(student.name)) {
					System.out.println(course);
				}
			}
		}
	}
	public void exit() {
		
	}
	public String toString() {
		return this.name;
	}
	
}
