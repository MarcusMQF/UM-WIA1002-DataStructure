import java.util.*;

public class L08_Q2_Alternative {
    public static void main(String[] args) {
        System.out.println("Input String: \"ABC\"");
        System.out.println("Output Permutation:");
        
        List<String> permutations = permuteString("ABC");
        for (String perm : permutations) {
            System.out.println(perm);
        }
    }

    public static List<String> permuteString(String str) {
        // Base case: if string is empty or has one character
        if (str.length() <= 1) {
            List<String> result = new ArrayList<>();
            result.add(str);
            return result;
        }
        
        // Step 1: Take out the first character
        char firstChar = str.charAt(0);
        String remaining = str.substring(1);
        
        // Step 2: Get all permutations of the remaining characters
        List<String> subPermutations = permuteString(remaining);
        
        // Step 3: Insert first character at all possible positions
        List<String> allPermutations = new ArrayList<>();
        
        for (String subPerm : subPermutations) {
            // Insert firstChar at each possible position in subPerm
            for (int pos = 0; pos <= subPerm.length(); pos++) {
                String newPermutation = insertCharAt(subPerm, firstChar, pos);
                allPermutations.add(newPermutation);
            }
        }
        
        return allPermutations;
    }
    
    // Helper method to insert a character at a specific position
    public static String insertCharAt(String str, char ch, int position) {
        return str.substring(0, position) + ch + str.substring(position);
    }
} 