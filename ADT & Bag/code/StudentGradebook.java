/**
 * A student gradebook system that demonstrates the use of Bag ADT
 * for managing student grades, assignments, and academic records.
 * 
 * @author Data Structure Course
 * @version 1.0
 */
public class StudentGradebook {
    
    /**
     * Represents a single grade entry.
     */
    public static class Grade {
        private String assignmentName;
        private String category; // "Quiz", "Exam", "Homework", "Project"
        private double points;
        private double maxPoints;
        private String date;
        
        public Grade(String assignmentName, String category, double points, double maxPoints, String date) {
            this.assignmentName = assignmentName;
            this.category = category;
            this.points = points;
            this.maxPoints = maxPoints;
            this.date = date;
        }
        
        public String getAssignmentName() { return assignmentName; }
        public String getCategory() { return category; }
        public double getPoints() { return points; }
        public double getMaxPoints() { return maxPoints; }
        public String getDate() { return date; }
        
        public double getPercentage() {
            return maxPoints > 0 ? (points / maxPoints) * 100 : 0;
        }
        
        public String getLetterGrade() {
            double percentage = getPercentage();
            if (percentage >= 90) return "A";
            else if (percentage >= 80) return "B";
            else if (percentage >= 70) return "C";
            else if (percentage >= 60) return "D";
            else return "F";
        }
        
        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            if (other == null || getClass() != other.getClass()) return false;
            Grade grade = (Grade) other;
            return assignmentName.equals(grade.assignmentName) && 
                   category.equals(grade.category) &&
                   date.equals(grade.date);
        }
        
        @Override
        public String toString() {
            return String.format("%-20s [%s] %.1f/%.1f (%.1f%%) - %s", 
                assignmentName, category, points, maxPoints, getPercentage(), getLetterGrade());
        }
    }
    
    /**
     * Represents a student in the gradebook.
     */
    public static class Student {
        private String studentId;
        private String firstName;
        private String lastName;
        private String email;
        private BagInterface<Grade> grades;
        
        public Student(String studentId, String firstName, String lastName, String email) {
            this.studentId = studentId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.grades = new ResizableArrayBag<>();
        }
        
        public String getStudentId() { return studentId; }
        public String getFirstName() { return firstName; }
        public String getLastName() { return lastName; }
        public String getFullName() { return firstName + " " + lastName; }
        public String getEmail() { return email; }
        
        /**
         * Adds a grade to the student's record.
         */
        public boolean addGrade(Grade grade) {
            return grades.add(grade);
        }
        
        /**
         * Removes a specific grade.
         */
        public boolean removeGrade(Grade grade) {
            return grades.remove(grade);
        }
        
        /**
         * Gets all grades for this student.
         */
        public BagInterface<Grade> getAllGrades() {
            return grades;
        }
        
        /**
         * Gets grades by category.
         */
        public BagInterface<Grade> getGradesByCategory(String category) {
            BagInterface<Grade> categoryGrades = new LinkedBag<>();
            Grade[] allGrades = grades.toArray();
            
            for (Grade grade : allGrades) {
                if (grade.getCategory().equalsIgnoreCase(category)) {
                    categoryGrades.add(grade);
                }
            }
            
            return categoryGrades;
        }
        
        /**
         * Calculates overall average percentage.
         */
        public double getOverallAverage() {
            if (grades.isEmpty()) return 0.0;
            
            double totalPoints = 0;
            double totalMaxPoints = 0;
            Grade[] allGrades = grades.toArray();
            
            for (Grade grade : allGrades) {
                totalPoints += grade.getPoints();
                totalMaxPoints += grade.getMaxPoints();
            }
            
            return totalMaxPoints > 0 ? (totalPoints / totalMaxPoints) * 100 : 0;
        }
        
        /**
         * Calculates category average.
         */
        public double getCategoryAverage(String category) {
            BagInterface<Grade> categoryGrades = getGradesByCategory(category);
            if (categoryGrades.isEmpty()) return 0.0;
            
            double totalPoints = 0;
            double totalMaxPoints = 0;
            Grade[] catGrades = categoryGrades.toArray();
            
            for (Grade grade : catGrades) {
                totalPoints += grade.getPoints();
                totalMaxPoints += grade.getMaxPoints();
            }
            
            return totalMaxPoints > 0 ? (totalPoints / totalMaxPoints) * 100 : 0;
        }
        
        /**
         * Gets overall letter grade.
         */
        public String getOverallLetterGrade() {
            double average = getOverallAverage();
            if (average >= 90) return "A";
            else if (average >= 80) return "B";
            else if (average >= 70) return "C";
            else if (average >= 60) return "D";
            else return "F";
        }
        
        /**
         * Gets the number of grades in each category.
         */
        public String getCategoryBreakdown() {
            StringBuilder breakdown = new StringBuilder();
            String[] categories = {"Quiz", "Exam", "Homework", "Project"};
            
            for (String category : categories) {
                BagInterface<Grade> categoryGrades = getGradesByCategory(category);
                int count = categoryGrades.getCurrentSize();
                double average = getCategoryAverage(category);
                
                if (count > 0) {
                    breakdown.append(String.format("%-10s: %2d grades, %.1f%% avg\n", 
                        category, count, average));
                }
            }
            
            return breakdown.toString();
        }
        
        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            if (other == null || getClass() != other.getClass()) return false;
            Student student = (Student) other;
            return studentId.equals(student.studentId);
        }
        
        @Override
        public String toString() {
            return String.format("%s (%s) - %s - Overall: %.1f%% (%s)", 
                getFullName(), studentId, email, getOverallAverage(), getOverallLetterGrade());
        }
    }
    
    /**
     * Main gradebook class managing multiple students.
     */
    public static class Gradebook {
        private String courseName;
        private String instructor;
        private BagInterface<Student> students;
        
        public Gradebook(String courseName, String instructor) {
            this.courseName = courseName;
            this.instructor = instructor;
            this.students = new LinkedBag<>();
        }
        
        /**
         * Adds a student to the gradebook.
         */
        public boolean addStudent(Student student) {
            if (students.contains(student)) {
                System.out.println("Student already exists: " + student.getFullName());
                return false;
            }
            return students.add(student);
        }
        
        /**
         * Finds a student by ID.
         */
        public Student findStudentById(String studentId) {
            Student[] studentArray = students.toArray();
            for (Student student : studentArray) {
                if (student.getStudentId().equals(studentId)) {
                    return student;
                }
            }
            return null;
        }
        
        /**
         * Adds a grade to a specific student.
         */
        public boolean addGradeToStudent(String studentId, Grade grade) {
            Student student = findStudentById(studentId);
            if (student != null) {
                return student.addGrade(grade);
            }
            System.out.println("Student not found: " + studentId);
            return false;
        }
        
        /**
         * Gets class statistics.
         */
        public String getClassStatistics() {
            if (students.isEmpty()) {
                return "No students in the gradebook.";
            }
            
            StringBuilder stats = new StringBuilder();
            stats.append("=== ").append(courseName).append(" Statistics ===\n");
            stats.append("Instructor: ").append(instructor).append("\n");
            stats.append("Total Students: ").append(students.getCurrentSize()).append("\n\n");
            
            // Calculate class average
            double totalAverage = 0;
            int studentCount = 0;
            String[] letterGradeCount = new String[5]; // A, B, C, D, F
            java.util.Arrays.fill(letterGradeCount, "0");
            
            Student[] studentArray = students.toArray();
            for (Student student : studentArray) {
                double average = student.getOverallAverage();
                totalAverage += average;
                studentCount++;
                
                // Count letter grades
                String letterGrade = student.getOverallLetterGrade();
                switch (letterGrade) {
                    case "A": letterGradeCount[0] = String.valueOf(Integer.parseInt(letterGradeCount[0]) + 1); break;
                    case "B": letterGradeCount[1] = String.valueOf(Integer.parseInt(letterGradeCount[1]) + 1); break;
                    case "C": letterGradeCount[2] = String.valueOf(Integer.parseInt(letterGradeCount[2]) + 1); break;
                    case "D": letterGradeCount[3] = String.valueOf(Integer.parseInt(letterGradeCount[3]) + 1); break;
                    case "F": letterGradeCount[4] = String.valueOf(Integer.parseInt(letterGradeCount[4]) + 1); break;
                }
            }
            
            double classAverage = studentCount > 0 ? totalAverage / studentCount : 0;
            stats.append(String.format("Class Average: %.1f%%\n", classAverage));
            stats.append("Grade Distribution:\n");
            stats.append(String.format("  A: %s students\n", letterGradeCount[0]));
            stats.append(String.format("  B: %s students\n", letterGradeCount[1]));
            stats.append(String.format("  C: %s students\n", letterGradeCount[2]));
            stats.append(String.format("  D: %s students\n", letterGradeCount[3]));
            stats.append(String.format("  F: %s students\n", letterGradeCount[4]));
            
            return stats.toString();
        }
        
        /**
         * Displays all students and their grades.
         */
        public String displayAllStudents() {
            StringBuilder display = new StringBuilder();
            display.append("=== All Students in ").append(courseName).append(" ===\n");
            
            Student[] studentArray = students.toArray();
            for (Student student : studentArray) {
                display.append(student.toString()).append("\n");
            }
            
            return display.toString();
        }
        
        /**
         * Displays detailed report for a specific student.
         */
        public String getStudentReport(String studentId) {
            Student student = findStudentById(studentId);
            if (student == null) {
                return "Student not found: " + studentId;
            }
            
            StringBuilder report = new StringBuilder();
            report.append("=== Student Report ===\n");
            report.append("Name: ").append(student.getFullName()).append("\n");
            report.append("ID: ").append(student.getStudentId()).append("\n");
            report.append("Email: ").append(student.getEmail()).append("\n\n");
            
            report.append("=== Grade Breakdown ===\n");
            report.append(student.getCategoryBreakdown());
            
            report.append("\n=== All Grades ===\n");
            Grade[] grades = student.getAllGrades().toArray();
            for (Grade grade : grades) {
                report.append(grade.toString()).append("\n");
            }
            
            report.append("\n=== Summary ===\n");
            report.append(String.format("Overall Average: %.1f%%\n", student.getOverallAverage()));
            report.append(String.format("Letter Grade: %s\n", student.getOverallLetterGrade()));
            
            return report.toString();
        }
        
        /**
         * Gets students who need academic support (below 70%).
         */
        public BagInterface<Student> getStudentsNeedingSupport() {
            BagInterface<Student> needSupport = new LinkedBag<>();
            Student[] studentArray = students.toArray();
            
            for (Student student : studentArray) {
                if (student.getOverallAverage() < 70.0) {
                    needSupport.add(student);
                }
            }
            
            return needSupport;
        }
        
        /**
         * Gets honor roll students (90% and above).
         */
        public BagInterface<Student> getHonorRollStudents() {
            BagInterface<Student> honorRoll = new LinkedBag<>();
            Student[] studentArray = students.toArray();
            
            for (Student student : studentArray) {
                if (student.getOverallAverage() >= 90.0) {
                    honorRoll.add(student);
                }
            }
            
            return honorRoll;
        }
    }
    
    /**
     * Demo method showing gradebook operations.
     */
    public static void main(String[] args) {
        // Create gradebook
        Gradebook gradebook = new Gradebook("Data Structures & Algorithms", "Dr. Smith");
        
        System.out.println("=== Student Gradebook System Demo ===\n");
        
        // Add students
        gradebook.addStudent(new Student("S001", "Alice", "Johnson", "alice@university.edu"));
        gradebook.addStudent(new Student("S002", "Bob", "Smith", "bob@university.edu"));
        gradebook.addStudent(new Student("S003", "Carol", "Davis", "carol@university.edu"));
        gradebook.addStudent(new Student("S004", "David", "Wilson", "david@university.edu"));
        gradebook.addStudent(new Student("S005", "Eva", "Brown", "eva@university.edu"));
        
        // Add grades for Alice (S001)
        gradebook.addGradeToStudent("S001", new Grade("Quiz 1", "Quiz", 18, 20, "2024-01-15"));
        gradebook.addGradeToStudent("S001", new Grade("Homework 1", "Homework", 95, 100, "2024-01-20"));
        gradebook.addGradeToStudent("S001", new Grade("Midterm Exam", "Exam", 85, 100, "2024-02-15"));
        gradebook.addGradeToStudent("S001", new Grade("Project 1", "Project", 92, 100, "2024-02-28"));
        gradebook.addGradeToStudent("S001", new Grade("Quiz 2", "Quiz", 19, 20, "2024-03-05"));
        
        // Add grades for Bob (S002)
        gradebook.addGradeToStudent("S002", new Grade("Quiz 1", "Quiz", 16, 20, "2024-01-15"));
        gradebook.addGradeToStudent("S002", new Grade("Homework 1", "Homework", 88, 100, "2024-01-20"));
        gradebook.addGradeToStudent("S002", new Grade("Midterm Exam", "Exam", 78, 100, "2024-02-15"));
        gradebook.addGradeToStudent("S002", new Grade("Project 1", "Project", 85, 100, "2024-02-28"));
        
        // Add grades for Carol (S003)
        gradebook.addGradeToStudent("S003", new Grade("Quiz 1", "Quiz", 20, 20, "2024-01-15"));
        gradebook.addGradeToStudent("S003", new Grade("Homework 1", "Homework", 98, 100, "2024-01-20"));
        gradebook.addGradeToStudent("S003", new Grade("Midterm Exam", "Exam", 94, 100, "2024-02-15"));
        gradebook.addGradeToStudent("S003", new Grade("Project 1", "Project", 97, 100, "2024-02-28"));
        gradebook.addGradeToStudent("S003", new Grade("Quiz 2", "Quiz", 20, 20, "2024-03-05"));
        
        // Add grades for David (S004)
        gradebook.addGradeToStudent("S004", new Grade("Quiz 1", "Quiz", 12, 20, "2024-01-15"));
        gradebook.addGradeToStudent("S004", new Grade("Homework 1", "Homework", 75, 100, "2024-01-20"));
        gradebook.addGradeToStudent("S004", new Grade("Midterm Exam", "Exam", 65, 100, "2024-02-15"));
        
        // Add grades for Eva (S005)
        gradebook.addGradeToStudent("S005", new Grade("Quiz 1", "Quiz", 17, 20, "2024-01-15"));
        gradebook.addGradeToStudent("S005", new Grade("Homework 1", "Homework", 92, 100, "2024-01-20"));
        gradebook.addGradeToStudent("S005", new Grade("Midterm Exam", "Exam", 89, 100, "2024-02-15"));
        gradebook.addGradeToStudent("S005", new Grade("Project 1", "Project", 90, 100, "2024-02-28"));
        
        // Display class statistics
        System.out.println(gradebook.getClassStatistics());
        
        // Display all students
        System.out.println(gradebook.displayAllStudents());
        
        // Display detailed report for Alice
        System.out.println(gradebook.getStudentReport("S001"));
        
        // Show honor roll students
        System.out.println("=== Honor Roll Students (90%+) ===");
        BagInterface<Student> honorRoll = gradebook.getHonorRollStudents();
        System.out.println(BagOperations.bagToString(honorRoll));
        
        // Show students needing support
        System.out.println("\n=== Students Needing Academic Support (<70%) ===");
        BagInterface<Student> needSupport = gradebook.getStudentsNeedingSupport();
        System.out.println(BagOperations.bagToString(needSupport));
        
        System.out.println("\n=== Gradebook Demo Complete ===");
    }
} 