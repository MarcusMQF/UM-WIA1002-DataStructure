package L01;

import java.util.Scanner;
import java.io.*;

public class L1Q1 {
    public static void main(String[] args) {

        String file = "L01/L1Q1.txt";

        try(Scanner fileScanner = new Scanner(new File(file))){
            while(fileScanner.hasNextLine()){
                String line = fileScanner.nextLine();
                System.out.println(line);
            }
        }catch(FileNotFoundException e){
            System.out.println("File not found" + file);
        }
    }
}