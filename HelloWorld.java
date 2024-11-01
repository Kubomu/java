import java.util.Scanner;  // Import Scanner for user input

public class HelloWorld {
    public static void main(String[] args) {
        // Create a Scanner object for reading input
        Scanner scanner = new Scanner(System.in);
        
        // Prompt the user for their name
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();  // Read user input
        
        // Display a personalized greeting
        System.out.println("Hello, " + name + "! Welcome to Java.");
        
        // Close the Scanner
        scanner.close();
    }
}
