package numbers;

import java.util.ArrayList;
import java.util.List;

class Number {
    public final boolean isEven;
    public final boolean isOdd;
    public final boolean isBuzz;
    public final boolean isDuck;
    public final boolean isPalindromic;
    public final boolean isGapful;
    public final boolean isSpy;
    public final boolean isSquare;
    public final boolean isSunny;
    public final boolean isJumping;
    public final boolean isHappy;
    public final boolean isSad;
    public final boolean isNotEven;
    public final boolean isNotOdd;
    public final boolean isNotBuzz;
    public final boolean isNotDuck;
    public final boolean isNotPalindromic;
    public final boolean isNotGapful;
    public final boolean isNotSpy;
    public final boolean isNotSquare;
    public final boolean isNotSunny;
    public final boolean isNotJumping;
    public final boolean isNotHappy;
    public final boolean isNotSad;
    private final long numeral;
    private final String numeralAsString;
    private final String[] numeralAsStringArray;
    private final int[] numeralAsIntArray;


    Number(long number) {
        numeral = number;
        numeralAsString = String.valueOf(numeral);
        numeralAsStringArray = numeralAsString.split("");
        numeralAsIntArray = longToIntArray(numeral);

        isEven = number % 2 == 0;
        isOdd = !isEven;
        isBuzz = numeralAsString.endsWith("7") || numeral % 7 == 0;
        isDuck = numeralAsString.contains("0");
        isPalindromic = numeralAsString.equals(new StringBuilder(numeralAsString).reverse().toString());
        isGapful = isGapful(numeral, numeralAsStringArray);
        isSpy = isSpy(numeralAsIntArray);
        isSquare = isSquare(numeral);
        isSunny = isSunny(numeral);
        isJumping = isJumping(numeralAsString);
        isHappy = isHappy(numeral);
        isSad = !isHappy;

        isNotEven = !isEven;
        isNotOdd = !isOdd;
        isNotBuzz = !isBuzz;
        isNotDuck = !isDuck;
        isNotPalindromic = !isPalindromic;
        isNotGapful = !isGapful;
        isNotSpy = !isSpy;
        isNotSquare = !isSquare;
        isNotSunny = !isSunny;
        isNotJumping = !isJumping;
        isNotHappy = !isHappy;
        isNotSad = !isSad;
    }

    static boolean isGapful(long value, String[] digits) {
        String divisor = digits[0] + digits[digits.length - 1];
        return digits.length > 2 && value % Long.parseLong(divisor) == 0;
    }

    static boolean isSpy(int[] digits) {
        int sum = 0;
        int product = 1;
        for (int i : digits) {
            sum += i;
            product *= i;
        }
        return sum == product;
    }

    static boolean isSquare(long value) {
        long root = (long) Math.sqrt(value);
        return root * root == value;
    }

    static boolean isSunny(long value) {
        return isSquare(value + 1);
    }

    static boolean isJumping(String number) {
        char[] digits = number.toCharArray();
        if (digits.length == 1) {
            return true;
        }
        for (int i = 1; i < digits.length; i++) {
            if (Math.abs(digits[i] - digits[i - 1]) != 1) {
                return false;
            }
        }
        return true;
    }

    static boolean isHappy(long value) {
        int[] digits = longToIntArray(value);
        List<Integer> previousNumbers = new ArrayList<>();
        int currentNumber = 0;
        do {
            previousNumbers.add(currentNumber);
            currentNumber = 0;
            for (int digit : digits) {
                currentNumber += digit * digit;
            }
            digits = longToIntArray(currentNumber);
        } while (currentNumber != 1 && !previousNumbers.contains(currentNumber));
        return currentNumber == 1;

    }

    static int[] longToIntArray(long numeral) {
        String[] numeralAsStringArray = String.valueOf(numeral).split("");
        int[] intArray = new int[numeralAsStringArray.length];
        int i = 0;
        for (String digit : numeralAsStringArray) {
            intArray[i++] = Integer.parseInt(digit);
        }
        return intArray;
    }

    long getValue() {
        return numeral;
    }

    boolean isLessThanZero() {
        return numeral < 0;
    }

    Number increment() {
        return new Number(this.numeral + 1);
    }

    boolean verifyProperties(List<Property> properties) {
        for (Property property : properties) {
            if (!hasProperty(property)) {
                return false;
            }
        }
        return true;
    }

    private boolean hasProperty(Property property) {
        switch (property) {
            case EVEN:
                return isEven;
            case ODD:
                return isOdd;
            case BUZZ:
                return isBuzz;
            case DUCK:
                return isDuck;
            case PALINDROMIC:
                return isPalindromic;
            case GAPFUL:
                return isGapful;
            case SPY:
                return isSpy;
            case SQUARE:
                return isSquare;
            case SUNNY:
                return isSunny;
            case JUMPING:
                return isJumping;
            case HAPPY:
                return isHappy;
            case SAD:
                return isSad;
            case NOT_EVEN:
                return isNotEven;
            case NOT_ODD:
                return isNotOdd;
            case NOT_BUZZ:
                return isNotBuzz;
            case NOT_DUCK:
                return isNotDuck;
            case NOT_PALINDROMIC:
                return isNotPalindromic;
            case NOT_GAPFUL:
                return isNotGapful;
            case NOT_SPY:
                return isNotSpy;
            case NOT_SQUARE:
                return isNotSquare;
            case NOT_SUNNY:
                return isNotSunny;
            case NOT_JUMPING:
                return isNotJumping;
            case NOT_HAPPY:
                return isNotHappy;
            case NOT_SAD:
                return isNotSad;
            default:
                return false;
        }
    }

    String toStringAllProperties() {
        return "Properties of " + numeral +
                "\nbuzz: " + isBuzz +
                "\nduck: " + isDuck +
                "\npalindromic: " + isPalindromic +
                "\ngapful: " + isGapful +
                "\nspy: " + isSpy +
                "\nsquare: " + isSquare +
                "\nsunny: " + isSunny +
                "\njumping: " + isJumping +
                "\nhappy: " + isHappy +
                "\nsad: " + isSad +
                "\neven: " + isEven +
                "\nodd: " + isOdd;
    }

    String toStringPropertiesSingleLine() {
        StringBuilder sb = new StringBuilder(numeral + " is ")
                .append(isBuzz ? "buzz, " : "")
                .append(isDuck ? "duck, " : "")
                .append(isPalindromic ? "palindromic, " : "")
                .append(isGapful ? "gapful, " : "")
                .append(isSpy ? "spy, " : "")
                .append(isSquare ? "square, " : "")
                .append(isSunny ? "sunny, " : "")
                .append(isJumping ? "jumping, " : "")
                .append(isHappy ? "happy, " : "")
                .append(isSad ? "sad, " : "")
                .append(isEven ? "even, " : "")
                .append(isOdd ? "odd, " : "");
        return sb.substring(0, sb.length() - 2);
    }

    @Override
    public String toString() {
        return numeralAsString;
    }
}
