package musterloesung.game;

public class BasicCalculator {

    public static final String DIVIDED_BY_ZERO = "can't divide by 0";
    public static final String NUMBER_REGEX = "[0-9]+";

    private boolean isExited = false;

    public int sum(final int augend, final int addend) {
        return augend + addend;
    }

    public int difference(final int minuend, final int subtrahend) {
        return minuend - subtrahend;
    }

    public int product(final int multiplier, final int multiplicand) {
        return multiplier * multiplicand;
    }

    public double ratio(final int numerator, final int denominator) {
        if (denominator == 0) {
            throw new ArithmeticException(DIVIDED_BY_ZERO);
        }

        return (double) numerator / denominator;
    }

    public long power(final int base, final int exponent) {
        if (exponent == 0) {
            return 1;
        } else if (exponent % 2 == 0) {
            return power(base, exponent / 2) * power(base, exponent / 2);
        } else {
            return base * power(base, exponent - 1);
        }
    }

    public void exit() {
        this.isExited = true;
    }

    public boolean isExited() {
        return isExited;
    }
}
