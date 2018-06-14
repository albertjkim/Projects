package edu.nyu.cs.ak6118;
import java.io.*;
import java.util.*;
public class Course implements Serializable, Comparable<Course> {
	public String name;
	public String id;
	public int max = 100;
	public int registered;
	ArrayList<Student> studentList = new ArrayList<Student>(); 
	public String instructor;
	public int section;
	public String location;
	
	public Course(String name) {
		this.name = name;
		
	}
	public Course(String name, String id, int max, int registered,ArrayList<Student> studentList, String instructor, int section, String location) {
		this.name = name;
		this.id = id;
		this.max = max;
		this.registered = registered;
		this.studentList = studentList;
		this.instructor = instructor;
		this.section = section;
		this.location = location;
	}
	public static Course makeCourse(String name, String id, String max, String registered, ArrayList<Student> studentList, String instructor, String section, String location) {
		int max1 = Integer.parseInt(max);
		int registered1 = Integer.parseInt(registered);
		int section1 = Integer.parseInt(section);
		
		Course crs = new Course(name, id, max1, registered1, studentList, instructor, section1, location);
		return crs;
	}
	public void register(Student name) {
		this.studentList.add(name);

	}
	public int compareTo(Course a) {
		int compareAge = (a.registered);
		return compareAge-this.registered;
	}
	public String toString() {
		return this.name;
	}
}
