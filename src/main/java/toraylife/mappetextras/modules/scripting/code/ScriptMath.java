package toraylife.mappetextras.modules.scripting.code;

import toraylife.mappetextras.modules.scripting.user.IScriptMath;

public class ScriptMath implements IScriptMath {
    @Override
    public double toDegrees(double radians) {
        return Math.toDegrees(radians);
    }

    @Override
    public double toRadians(double degrees) {
        return Math.toRadians(degrees);
    }

    @Override
    public double sign(double number) {
        return Math.signum(number);
    }

    @Override
    public double factorial(double number) {
        return (number != 1) ? number * factorial(number - 1) : 1;
    }

    @Override
    public double gcd(double a, double b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    @Override
    public double lcm(double a, double b) {
        return (a * b) / gcd(a, b);
    }
}