package L01;

import java.io.*;
import java.util.Scanner;

public class L1Q2_2 {
    public static void main(String[] args) {
        // Process all text files with their respective delimiters
        processFile("text1.txt", ",");
        processFile("text2.txt", ",\\s*"); // Matches comma followed by optional whitespace
        processFile("text3.txt", ";\\s*"); // Matches semicolon followed by optional whitespace
        processFile("text4.txt", "\\d+");  // Matches one or more digits
    }
    
    private static void processFile(String fileName, String delimiter) {
        try {
            System.out.println("\nProcessing " + fileName + ":");
            File file = new File("L01/" + fileName);
            Scanner scanner = new Scanner(file);
            StringBuilder content = new StringBuilder();
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(delimiter);
                for (String part : parts) {
                    content.append(part.trim());
                }
            }
            
            String result = content.toString();
            System.out.println("Number of characters: " + result.length());
            System.out.println("Characters without delimiters: " + result);
            
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File " + fileName + " not found.");
        }
    }
}
