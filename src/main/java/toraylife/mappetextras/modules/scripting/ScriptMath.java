package toraylife.mappetextras.modules.scripting;

public class ScriptMath {
    public double toDegrees(double radians) {
        return Math.toDegrees(radians);
    }

    public double toRadians(double degrees) {
        return Math.toRadians(degrees);
    }

    public double sign(double number){
        return Math.signum(number);
    }

    public double factorial(double number) {
        return (number != 1) ? number * factorial(number - 1) : 1;
    }

    public double gcd(double a, double b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public double lcm(double a, double b) {
        return (a * b) / gcd(a, b);
    }
}
