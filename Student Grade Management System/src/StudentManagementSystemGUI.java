import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.HashMap;

public class StudentManagementSystemGUI {
    private JFrame frame;
    private Map<Integer, Student> studentMap;

    public StudentManagementSystemGUI() {
        frame = new JFrame("Student Management System");
        frame.setSize(450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        studentMap = new HashMap<>();

        createUI();

        frame.setVisible(true);
    }

    private void createUI() {
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel label = new JLabel("Student Management System");
        label.setBounds(10, 20, 300, 25);
        panel.add(label);

        JButton addButton = new JButton("Add Student");
        addButton.setBounds(10, 60, 120, 25);
        panel.add(addButton);

        JButton updateButton = new JButton("Update Student");
        updateButton.setBounds(140, 60, 120, 25);
        panel.add(updateButton);

        JButton deleteButton = new JButton("Delete Student");
        deleteButton.setBounds(270, 60, 120, 25);
        panel.add(deleteButton);

        JButton displayButton = new JButton("Display Student Info");
        displayButton.setBounds(10, 100, 200, 25);
        panel.add(displayButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAddStudent();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleUpdateStudent();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDeleteStudent();
            }
        });

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDisplayStudentInfo();
            }
        });
    }

    private void handleAddStudent() {
        String name = JOptionPane.showInputDialog(frame, "Enter student name:");
        if (name != null) {
            int rollNumber = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter student roll number:"));

            Student student = new Student(name, rollNumber);

            int numSubjects = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter the number of subjects:"));
            for (int i = 0; i < numSubjects; i++) {
                String subject = JOptionPane.showInputDialog(frame, "Enter subject name:");
                int mark = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter marks for " + subject + ":"));
                student.addSubjectMark(subject, mark);
            }

            studentMap.put(rollNumber, student);
            JOptionPane.showMessageDialog(frame, "Student added successfully!");
        }
    }

    private void handleUpdateStudent() {
        int rollNumberToUpdate = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter student roll number to update:"));

        if (studentMap.containsKey(rollNumberToUpdate)) {
            Student student = studentMap.get(rollNumberToUpdate);

            String subjectToUpdate = JOptionPane.showInputDialog(frame, "Enter subject name to update:");
            if (student.getSubjectMarks().containsKey(subjectToUpdate)) {
                int newMark = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter new marks for " + subjectToUpdate + ":"));
                student.updateSubjectMark(subjectToUpdate, newMark);
                JOptionPane.showMessageDialog(frame, "Student information updated successfully!");
            } else {
                JOptionPane.showMessageDialog(frame, "Subject not found for the student.");
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Student not found with the given roll number.");
        }
    }

    private void handleDeleteStudent() {
        int rollNumberToDelete = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter student roll number to delete:"));

        if (studentMap.containsKey(rollNumberToDelete)) {
            studentMap.remove(rollNumberToDelete);
            JOptionPane.showMessageDialog(frame, "Student deleted successfully!");
        } else {
            JOptionPane.showMessageDialog(frame, "Student not found with the given roll number.");
        }
    }

    private void handleDisplayStudentInfo() {
        int rollNumberToDisplay = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter student roll number to display information:"));

        if (studentMap.containsKey(rollNumberToDisplay)) {
            Student student = studentMap.get(rollNumberToDisplay);

            StringBuilder info = new StringBuilder("Student Information:\n");
            info.append("Name: ").append(student.getName()).append("\n");
            info.append("Roll Number: ").append(student.getRollNumber()).append("\n");
            info.append("Subject Marks: ").append(student.getSubjectMarks()).append("\n");
            info.append("Overall Percentage: ").append(student.calculatePercentage()).append("%\n");
            info.append("Grade: ").append(student.calculateGrade());

            JOptionPane.showMessageDialog(frame, info.toString());
        } else {
            JOptionPane.showMessageDialog(frame, "Student not found with the given roll number.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StudentManagementSystemGUI();
            }
        });
    }
}
