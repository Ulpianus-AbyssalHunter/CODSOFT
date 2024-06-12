//Task 5 - Student Course Registration System

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CRS {
    private List<Course> courses;
    private List<Student> students;

    public CRS() {
        courses = new ArrayList<>();
        students = new ArrayList<>();
        initializeCourses();
        initializeStudents();
    }

    private void initializeCourses() {
        courses.add(new Course("CS101", "Introduction to Computer Science", "Basic concepts of computer science", 30, "MWF 9-10AM"));
        courses.add(new Course("MA101", "Calculus I", "Introduction to differential calculus", 25, "TTh 11-12:30PM"));
        courses.add(new Course("PH101", "Physics I", "Introduction to classical mechanics", 20, "MWF 10-11AM"));
    }

    private void initializeStudents() {
        students.add(new Student("S001", "Alice Johnson"));
        students.add(new Student("S002", "Bob Smith"));
        students.add(new Student("S003", "Charlie Brown"));
    }

    public void displayCourses() {
        System.out.println("Available Courses:");
        for (Course course : courses) {
            System.out.println(course);
            System.out.println("Available Slots: " + course.getAvailableSlots());
            System.out.println("-----------------------------");
        }
    }

    public void registerStudentForCourse(String studentID, String courseCode) {
        Student student = findStudentByID(studentID);
        Course course = findCourseByCode(courseCode);

        if (student != null && course != null) {
            student.registerCourse(course);
            System.out.println("Student registered for course successfully.");
        } else {
            System.out.println("Student or Course not found.");
        }
    }

    public void dropStudentFromCourse(String studentID, String courseCode) {
        Student student = findStudentByID(studentID);
        Course course = findCourseByCode(courseCode);

        if (student != null && course != null) {
            student.dropCourse(course);
            System.out.println("Student dropped from course successfully.");
        } else {
            System.out.println("Student or Course not found.");
        }
    }

    public void displayStudentCourses(String studentID) {
        Student student = findStudentByID(studentID);
        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Student not found.");
        }
    }

    private Student findStudentByID(String studentID) {
        for (Student student : students) {
            if (student.getStudentID().equals(studentID)) {
                return student;
            }
        }
        return null;
    }

    private Course findCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }

    public void addNewStudent(String studentID, String name) {
        if (findStudentByID(studentID) == null) {
            students.add(new Student(studentID, name));
            System.out.println("New student added successfully.");
        } else {
            System.out.println("Student ID already exists. Please try again with a different ID.");
        }
    }

    public static void main(String[] args) {
        CRS system = new CRS();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("1. Display Courses");
            System.out.println("2. Register for Course");
            System.out.println("3. Drop Course");
            System.out.println("4. Display Student Courses");
            System.out.println("5. Add New Student");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    system.displayCourses();
                    break;
                case 2:
                    System.out.print("Enter Student ID: ");
                    String studentID = scanner.nextLine();
                    System.out.print("Enter Course Code: ");
                    String courseCode = scanner.nextLine();
                    system.registerStudentForCourse(studentID, courseCode);
                    break;
                case 3:
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.nextLine();
                    System.out.print("Enter Course Code: ");
                    courseCode = scanner.nextLine();
                    system.dropStudentFromCourse(studentID, courseCode);
                    break;
                case 4:
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.nextLine();
                    system.displayStudentCourses(studentID);
                    break;
                case 5:
                    System.out.print("Enter New Student ID: ");
                    String newStudentID = scanner.nextLine();
                    System.out.print("Enter New Student Name: ");
                    String newName = scanner.nextLine();
                    system.addNewStudent(newStudentID, newName);
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }

        scanner.close();
    }
}