package edu.nyu.cs.ak6118;
import java.util.*;
import java.io.*;
interface AdminInterface {
	void create(ArrayList<Course> courses);
	void delete(ArrayList<Course> courses);
	void edit(ArrayList<Course> courses);
	void information(ArrayList<Course> courses);
	void registerStudent(ArrayList<Course> courses);
	void exit();
	void view(ArrayList<Course> a);
	void viewFull(ArrayList<Course> a);
	void writeFull(ArrayList<Course> a) throws IOException;
	void viewStudents(ArrayList<Course> a);
	void registeredTo(ArrayList<Course> a);
	void sort(ArrayList<Course> a);
}
