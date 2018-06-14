package edu.nyu.cs.ak6118;
import java.util.*;
interface StudentInterface {
	void view(ArrayList<Course> a);
	void notFull(ArrayList<Course> a);
	void withdraw(Student stduent, ArrayList<Course> a);
	void registeredIn(Student student, ArrayList<Course> a);
	void exit();
}
