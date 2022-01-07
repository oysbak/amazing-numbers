package numbers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInput {
    private static final Scanner scanner = new Scanner(System.in);
    private String[] input;
    private Number number;
    private int count;
    private List<Property> properties;
    private String feedback;

    public UserInput() {
        System.out.println("Welcome to Amazing Numbers!\n");
        System.out.println("Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameter shows how many consecutive numbers are to be printed;\n" +
                "- two natural numbers and properties to search for;\n" +
                "- a property preceded by minus must not be present in numbers;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.");
    }

    public void collectInput() {
        do {
            System.out.print("\nEnter a request: ");
            input = scanner.nextLine().split(" ");
            count = 0;
            properties = new ArrayList<>();
            feedback = "";
            int index = 0;
            for (String ignored : input) {
                switch (index) {
                    case 0:
                        extractNumber();
                        break;
                    case 1:
                        extractCount();
                        break;
                    default:
                        extractProperty(index);
                }
                index++;
            }
            if (properties.size() > 1 && feedback.length() == 0) {
                String[] mep = Property.anyMutuallyExclusive(properties);
                if (mep != null) {
                    feedback = "The request contains mutually exclusive properties: [" + mep[0] + ", " + mep[1] + "]" +
                            "\nThere are no numbers with these properties.";
                }
            }
            System.out.println(feedback);
        } while (feedback.length() > 0);
    }

    private void extractProperty(int index) {
        try {
            String property = input[index].toUpperCase().replace("-", "NOT_");
            properties.add(Property.valueOf(property));
        } catch (IllegalArgumentException e) {
            if (feedback.length() > 0) {
                feedback = "\nThe properties [" + this.input[index - 1] + ", " + this.input[index] + "] are wrong." +
                        "\nAvailable properties: " + Property.allPropertiesToString();
            } else {
                feedback = "\nThe property [" + input[index] + "] is wrong." +
                        "\nAvailable properties: " + Property.allPropertiesToString();
            }
        }
    }

    private void extractCount() {
        try {
            count = Integer.parseInt(input[1]);
            if (count < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            feedback = "\nThe second parameter should be a natural number.";
        }
    }

    private void extractNumber() {
        try {
            number = new Number(Long.parseLong(input[0]));
            if (number.isLessThanZero()) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            feedback = "\nThe first parameter should be a natural number or zero.";
        }
    }

    public Number getNumber() {
        return number;
    }

    public int getCount() {
        return count;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public boolean doContinue() {
        return number.getValue() != 0;
    }
}
