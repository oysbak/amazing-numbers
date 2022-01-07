package numbers;

import java.util.List;

public class NumberAnalyzer {
    public NumberAnalyzer(UserInput userInput) {
        if (userInput.getCount() > 0) {
            if (userInput.getProperties().size() == 0) {
                print(userInput.getNumber(), userInput.getCount());
            } else {
                print(userInput.getNumber(), userInput.getCount(), userInput.getProperties());
            }
        } else {
            print(userInput.getNumber());
        }
    }

    private void print(Number number) {
        System.out.println(number.toStringAllProperties());
    }

    private void print(Number number, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(number.toStringPropertiesSingleLine());
            number = number.increment();
        }
    }

    private void print(Number number, int count, List<Property> properties) {
        int found = 0;
        do {
            if (number.verifyProperties(properties)) {
                System.out.println(number.toStringPropertiesSingleLine());
                found++;
            }
            number = number.increment();
        } while (found < count);
    }
}
