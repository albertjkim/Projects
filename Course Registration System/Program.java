package edu.nyu.cs.ak6118;
import java.util.*;
import java.io.*;
public class Program {
	static boolean exit = false;
	static boolean start = false;
	static int userType;
	static final int ADMIN = 0;
	static final int STUDENT = 1;
	static boolean access = false;
	
	public static void logOn() {
		start = true;
	}
	public static void logOff() {
		exit = true;
	}
	public static void mainProgram(Admin admin, ArrayList<Course> courseList) throws IOException {
		System.out.println("Please enter 0 to access the Coruse Management menu or 1 to access the Reports menu: ");
		Scanner scn = new Scanner(System.in);
		int response1 = scn.nextInt();
		if (response1 == 0) {
			System.out.println("Please enter the integer corresponding to the desired comamnd: ");
			System.out.println("1. Create a new course \n2. Delete a course \n3. Edit a course \n4. Display information for a given course \n5. Register a student \n6. Exit" );
			int response2 = scn.nextInt();
			switch(response2) {
			case 1: 
				admin.create(courseList);
				break;
			case 2:
				admin.delete(courseList);
				break;
			case 3:
				admin.edit(courseList);
				break;
			case 4:
				admin.information(courseList);
				break;
			case 5:
				admin.registerStudent(courseList);
				break;
			case 6:
				admin.exit();
				Program.logOff();
				break;
			default:
				System.out.println("Please enter an integer between 1 and 6: ");
				break;
			}
		}
		if (response1 == 1) {
			System.out.println("Please enter the integer corresponding to the desired command: ");
			System.out.println("1. View all courses \n2. View all courses that are full \n3. Write to a file the list of courses that are full \n4. View the names of the students being registered in a specific course \n5. View the list of courses that a given student is being registered on \n6. Sort \n7. Exit");
			int response2 = scn.nextInt();
			switch (response2) {
			case 1:
				admin.view(courseList);
				break;
			case 2:
				admin.viewFull(courseList);
				break;
			case 3:
				admin.writeFull(courseList);
				break;
			case 4:
				admin.viewStudents(courseList);
				break;
			case 5:
				admin.registeredTo(courseList);
				break;
			case 6:
				admin.sort(courseList);
				break;
			case 7:
				admin.exit();
				Program.logOff();
				break;
			default:
				System.out.println("Please enter an integer between 1 and 6: ");
			}
		}
	}
	public static void mainProgram(Student student, ArrayList<Course> courseList) {
		System.out.println("Please enter the desired command: ");
		System.out.println("1. View all courses that are available: \n2. View all courses that are not FULL \n3. Register on a course\n4. Withdraw from a course\n5. View all courses that the current student is being registered in\n6. Exit");
		Scanner scn = new Scanner(System.in);
		int response = scn.nextInt();
		switch(response) {
		case 1:
			student.view(courseList);
			break;
		case 2:
			student.notFull(courseList);
			break;
		case 3:
			student.register(student, courseList);
			break;
		case 4:
			student.withdraw(student, courseList);
			break;
		case 5:
			student.registeredIn(student, courseList);
			break;
		case 6:
			student.exit();
			Program.logOff();
			break;
		default:
			System.out.println("Please enter an integer between 1 and 6: ");
		}
		
	}
	/*
	public static ArrayList<Course> deserialize(int i, File file) {
		file = new File("src/Courses.sir");
		if (file.exists() == false) {
			file = new File("src/MyUniversityCourses.csv");
		}
		else {
			ArrayList<Course> courseList = new ArrayList<Course>();
			while()
			try {
				FileInputStream fis = new FileInputStream("src/Courses.sir");
				ObjectInputStream ois = new ObjectInputStream(fis);
				courseList.add((Course)ois.readObject());
			}
		}
		
	}
	*/
	public static void main(String[] args) throws IOException {
		ArrayList<Course> courseList = new ArrayList<Course>();

		File file = new File("Courses.ser");
		if (file.exists() == false) {
			FileReader fil = new FileReader("src/MyUniversityCourses.csv");
			BufferedReader buf = new BufferedReader(fil);
			String line = buf.readLine();
			while ((line = buf.readLine()) != null) {
				String[] something = new String[8];
				something = line.split("\\s*,\\s*");
				ArrayList<Student> s = new ArrayList<Student>();
				Course toAdd = Course.makeCourse(something[0], something[1], something[2], something[3], s, something[5], something[6], something[7]);
				courseList.add(toAdd);
			}
		}
		
		else {
			try {
				FileInputStream fis = new FileInputStream("Courses.ser");
				ObjectInputStream ois = new ObjectInputStream(fis);
				courseList = (ArrayList<Course>) ois.readObject();
				
			}
			catch(IOException e) {
				e.printStackTrace();
				return;
			}
			catch(ClassNotFoundException c) {
				c.printStackTrace();
				return;
			}
		}
		Course zero = new Course("zero");
		courseList.add(zero);
		User user = new User();
		while (access == false) {
			System.out.println("Welcome to CRS. \nPlease enter your username first, then password: ");
			Scanner scn = new Scanner(System.in);
			String username = scn.nextLine();
			String password = scn.nextLine();
			if (username.equals("Admin") && password.equals("Admin001")) {
				access = true;
				user = new Admin();
				userType = ADMIN;
				start = true;
				break;
			}
			if (username.equals("Student") && password.equals("Student001")) {
				access = true;
				System.out.println("Please enter your first name: ");
				String firstName = scn.nextLine();
				System.out.println("Please enter your last name: ");
				String lastName = scn.nextLine();
				user = new Student(firstName, lastName);
				userType = STUDENT;
				start = true;
				break;
			}
			System.out.println(access);
		}
		while(start == true && exit == false && userType == ADMIN) {
			Program.mainProgram((Admin)user, courseList);
		}
		while(start == true && exit == false && userType == STUDENT) {
			Program.mainProgram((Student)user, courseList);
		}
		try {
			File file2 = new File("Courses.ser");
			file2.createNewFile();
			System.out.println("Files have been updated!");
			FileOutputStream fo = new FileOutputStream(file2);
			ObjectOutputStream oo = new ObjectOutputStream(fo);
			oo.writeObject(courseList);
			oo.close();
			
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
	}
}
