package numbers;

public class Main {
    public static void main(String[] args) {
        // write your code here
        UserInput userInput = new UserInput();
        do {
            userInput.collectInput();
            if (userInput.doContinue()) {
                new NumberAnalyzer(userInput);
            }
        } while (userInput.doContinue());
        System.out.println("Goodbye!");
    }
}