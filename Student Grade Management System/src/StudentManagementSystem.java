import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StudentManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Integer, Student> studentMap = new HashMap<>();

        while (true) {
            System.out.println("1. Add Student\n2. Update Student\n3. Delete Student\n4. Display Student Info\n5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addStudent(scanner, studentMap);
                    break;
                case 2:
                    updateStudent(scanner, studentMap);
                    break;
                case 3:
                    deleteStudent(scanner, studentMap);
                    break;
                case 4:
                    displayStudentInfo(scanner, studentMap);
                    break;
                case 5:
                    System.out.println("Exiting program. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void addStudent(Scanner scanner, Map<Integer, Student> studentMap) {
        System.out.print("Enter student name: ");
        String name = scanner.next();
        System.out.print("Enter student roll number: ");
        int rollNumber = scanner.nextInt();

        Student student = new Student(name, rollNumber);

        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter subject name: ");
            String subject = scanner.next();
            System.out.print("Enter marks for " + subject + ": ");
            int mark = scanner.nextInt();
            student.addSubjectMark(subject, mark);
        }

        studentMap.put(rollNumber, student);
        System.out.println("Student added successfully!");
    }

    private static void updateStudent(Scanner scanner, Map<Integer, Student> studentMap) {
        System.out.print("Enter student roll number to update: ");
        int rollNumber = scanner.nextInt();

        if (studentMap.containsKey(rollNumber)) {
            Student student = studentMap.get(rollNumber);

            System.out.print("Enter subject name to update: ");
            String subject = scanner.next();
            System.out.print("Enter new marks for " + subject + ": ");
            int newMark = scanner.nextInt();

            student.updateSubjectMark(subject, newMark);
            System.out.println("Student information updated successfully!");
        } else {
            System.out.println("Student not found with the given roll number.");
        }
    }

    private static void deleteStudent(Scanner scanner, Map<Integer, Student> studentMap) {
        System.out.print("Enter student roll number to delete: ");
        int rollNumber = scanner.nextInt();

        if (studentMap.containsKey(rollNumber)) {
            studentMap.remove(rollNumber);
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student not found with the given roll number.");
        }
    }

    private static void displayStudentInfo(Scanner scanner, Map<Integer, Student> studentMap) {
        System.out.print("Enter student roll number to display information: ");
        int rollNumber = scanner.nextInt();

        if (studentMap.containsKey(rollNumber)) {
            Student student = studentMap.get(rollNumber);

            System.out.println("Student Information:");
            System.out.println("Name: " + student.getName());
            System.out.println("Roll Number: " + student.getRollNumber());
            System.out.println("Subject Marks: " + student.getSubjectMarks());
            System.out.println("Overall Percentage: " + student.calculatePercentage() + "%");
            System.out.println("Grade: " + student.calculateGrade());
        } else {
            System.out.println("Student not found with the given roll number.");
        }
    }
}


