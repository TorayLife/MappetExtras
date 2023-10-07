package toraylife.mappetextras.modules.scripting.user;

public interface IScriptMath {
    /**
     * Converts the given radians to degrees.
     *
     * @param radians the radians value to convert
     * @return the number of degrees
     */
    double toDegrees(double radians);

    /**
     * Converts the given degrees to radians.
     *
     * @param degrees the degrees value to convert
     * @return the number of radians
     */
    double toRadians(double degrees);

    /**
     * Gets the sign of the given number.
     *
     * @param number the number to check
     * @return -1 if the number is negative,
     * 1 if positive, 0 if zero
     */
    double sign(double number);

    /**
     * Calculates the factorial of the given number.
     *
     * @param number the number to calculate factorial for
     * @return the factorial value
     */
    double factorial(double number);

    /**
     * Calculates the greatest common divisor of the given numbers.
     *
     * @param a the first number
     * @param b the second number
     * @return the greatest common divisor
     */
    double gcd(double a, double b);

    /**
     * Calculates the least common multiple of the given numbers.
     *
     * @param a the first number
     * @param b the second number
     * @return the least common multiple
     */
    double lcm(double a, double b);
}
