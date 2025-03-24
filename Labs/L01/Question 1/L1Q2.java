import java.util.Scanner;
import java.io.*;

public class L1Q2 {
    public static void main(String[] args) {
        String originalFile = "L01/L1Q1.txt";
        String file = "L01/L1Q2.txt";
        
        // First, read the original file
        try {
            // Create a copy of the original file
            copyFile(originalFile, file);
            
            // Read and display the current content of the file
            System.out.println("Current content of the file:");
            readAndDisplayFile(file);
            
            // Append the second part of the letter
            System.out.println("\nNow, let's write the second part of your letter.");
            appendSecondPartToFile(file);
            
            // Read and display the updated content
            System.out.println("\nUpdated content of the file:");
            readAndDisplayFile(file);
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    // Method to copy the original file to a new file
    private static void copyFile(String sourceFile, String destinationFile) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(destinationFile))) {
            
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
        }
    }
    
    // Method to read and display the content of a file
    private static void readAndDisplayFile(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
    
    // Method to append the second part of the letter
    private static void appendSecondPartToFile(String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            Scanner scanner = new Scanner(System.in);
            
            // Add a blank line and the date
            writer.newLine();
            writer.write("Thursday, 18 June 2021.");
            writer.newLine();
            writer.newLine();
            
            // Ask questions and append answers
            System.out.println("How have you performed in the class?");
            String performance = scanner.nextLine();
            
            System.out.println("Are you happy with your performance? (yes/no)");
            String happy = scanner.nextLine();
            
            System.out.println("What has learning DS taught you / what did you learn from DS?");
            String learning = scanner.nextLine();
            
            System.out.println("Is there any change to your target grade?");
            String gradeChange = scanner.nextLine();
            
            System.out.println("What you did well during the course?");
            String didWell = scanner.nextLine();
            
            System.out.println("What could have been done better during the course?");
            String improvements = scanner.nextLine();
            
            // Write the second part of the letter
            writer.write("It's me again. Finally, it's the end of the term and the DS class has finished! I think I did ");
            writer.write(performance + " in this course. ");
            
            writer.write("Am I happy with my performance? ");
            writer.write(happy + ". ");
            
            writer.write("Learning DS has taught me " + learning + ". ");
            
            writer.write("Regarding my target grade, " + gradeChange + ". ");
            
            writer.write("During this course, I did well in " + didWell + ". ");
            
            writer.write("However, I think I could have " + improvements + ".");
            
            writer.newLine();
            writer.write("Overall, it has been a great semester learning Data Structures!");

            scanner.close();
        }
    }
}
