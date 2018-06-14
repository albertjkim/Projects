package edu.nyu.cs.ak6118;

import java.util.*;
import java.io.*;

public class Admin extends User implements AdminInterface {
	private String username;
	private String password;
	
	public void create(ArrayList<Course> a) {
		System.out.println("Please enter the name of the new course: ");
		Scanner scn = new Scanner(System.in);
		String name = scn.nextLine();
		Course c = new Course(name);
		for (Course course:a) {
			if(course.name.equals(c.name)) {
				System.out.println("Course with the same name exists already!");
				return;
			}
		}
		a.add(c);
	}
	public void delete(ArrayList<Course> a) {
		System.out.println("Please enter the name of the course to delete: ");
		Scanner scn = new Scanner(System.in);
		String name = scn.nextLine();
		Iterator itr = a.iterator();
		while (itr.hasNext()) {
			Course x = (Course)itr.next();
			if (x.name.equals(name)) {
				itr.remove();
			}
		}
	}
	public void edit(ArrayList<Course> a) {
		System.out.println("Please enter the name of the course you woud like to edit: ");
		Scanner scn = new Scanner(System.in);
		String name = scn.nextLine();
		int x = -1;
		for ( int i=0; i < a.size(); i++) {
			if (name.equals(a.get(i).name)) {
				System.out.println(a.get(i).name);
				x = a.indexOf(a.get(i));
				break;
			}
		}
		if(x ==-1) {
			System.out.println("This course does not exist.");
		}
		else {
			Course blah = a.get(x);
			System.out.println("Please enter the new course name: ");
			blah.name = scn.nextLine();
			System.out.println("Please enter the new course ID: ");
			blah.id = scn.nextLine();
			System.out.println("Please enter the new course instructore name: ");
			blah.instructor = scn.nextLine();
			System.out.println("The course has been updated!");
		}
	}
	public void information(ArrayList<Course> a) {
		System.out.println("Please enter the name of the course you would like the information of: ");
		Scanner scn = new Scanner(System.in);
		String name = scn.nextLine();
		int x = -1;
		for ( int i=0; i < a.size(); i++) {
			if (name.equals(a.get(i).name)) {
				System.out.println(a.get(i).name);
				x = a.indexOf(a.get(i));
				break;
			}
		}
		if(x ==-1) {
			System.out.println("This course does not exist.");
		}
		else {
			Course blah = a.get(x);
			System.out.println("Course id: " + blah.id + "\nCourse Maximum student number: " + blah.max + "\nCourse Students list : " + blah.studentList + "\nCourse instructor: " + blah.instructor + "\nCourse section: " + blah.section + "\nCourse location: " + blah.location);
		}
	}
	public void registerStudent(ArrayList<Course> a) {
		System.out.println("Please enter the first name of the student you would like to register: ");
		Scanner scn = new Scanner(System.in);
		String name = scn.nextLine();
		System.out.println("Please enter the last name of the student you would like to register: ");
		String name2 = scn.nextLine();
		Student someone = new Student(name, name2);
		int x = -1;
		name = "zero";
		for ( int i=0; i < a.size(); i++) {
			if (name.equals(a.get(i).name)) {
				System.out.println(a.get(i).name);
				x = a.indexOf(a.get(i));
				break;
			}
		}
			Course blah = a.get(x);
			System.out.println(blah);
			blah.register(someone);
	}
	public void exit() {
		
	}
	public void view(ArrayList<Course> courses) {
		System.out.println("These are the courses available: ");
		for(Course a: courses) {
			System.out.println(a.name);
		}
	}
	public void viewFull(ArrayList<Course> courses) {
		System.out.println("These are the courses that are full: ");
		for(Course a: courses) {
			if(a.max <= a.registered) {
				System.out.println(a.name);
			}
		}
	}
	public void writeFull(ArrayList<Course> courses) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("src/CoursesFull.txt"));
		System.out.println("Writing the list of courses that are full..." );
		for(Course a: courses) {
			if(a.max <= a.registered) {
				writer.write(a.name);
			}
		}
		writer.close();
	}
	public void viewStudents(ArrayList<Course> courses) {
		System.out.println("Please enter the name of the course you would like to see the students registered to: ");
		Scanner scn = new Scanner(System.in);
		String input = scn.nextLine();
		int loc = -1;
		for(Course a:courses) {
			if(a.name.equals(input)) {
			loc = courses.indexOf(a);
			}
			
		}
		if(loc ==-1) {
			System.out.println("the course does not exist.");
			return;
		}
		ArrayList<Student> list = new ArrayList<Student>();
		System.out.println(courses.get(loc));
		System.out.println(courses.get(loc).studentList);
		list = courses.get(loc).studentList;
		if(list == null) {
			System.out.println("There are no students registered");
			return;
		}
		for(Student student : list) {
			System.out.println(student.firstName +" "+ student.lastName);
		}
	}
	public void registeredTo(ArrayList<Course> courses) {
		System.out.println("Please enter the first name of the student: ");
		Scanner scn = new Scanner(System.in);
		String firstName = scn.nextLine();
		System.out.println("Please enter the last name of the student: ");
		String lastName = scn.nextLine();
		Student someone = new Student(firstName, lastName);
		System.out.println("Student is registered to the following courses: ");
		for (Course course: courses) {
			if(course.studentList != null) {
				for(Student a:course.studentList) {
					if(a.name.equals(someone.name)) {
						System.out.println(course.name);
					}
				}
			}
		}
	}
	public void sort(ArrayList<Course> courses) {
		 Collections.sort(courses);
		 for(Course a: courses) {
			 System.out.println(a.name);
		 }
	}
}
