import java.io.*;
import java.util.Scanner;

public class L1Q2_1 {
    public static void main(String[] args) {
        processFile("Labs/L01/Question 2/text1.txt");
    }
    
    private static void processFile(String fileName) {
        try {
            System.out.println("\nProcessing " + fileName + ":");
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            StringBuilder allChars = new StringBuilder();
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] chars = line.split(",");
                for (String ch : chars) {
                    allChars.append(ch.trim());
                }
            }
            
            String result = allChars.toString();
            System.out.println("Number of characters: " + result.length());
            System.out.println("Characters without delimiters: " + result);
            
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File " + fileName + " not found.");
        }
    }
}