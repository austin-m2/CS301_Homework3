package edu.cpp.austin.CS301_Homework3;

public class Main {

    public static void main(String[] args) {
	    exercise_3_1_5();
	    exercise_3_1_8();
	    kashi();
    }

    //Use the bisection method to find a zero of the equation
    //λcosh(50/λ) = λ + 10 that begins this chapter.
    private static void exercise_3_1_5() {
        System.out.println("Exercise 3.1 #5");
        double a = 100.0;
        double b = 200.0;
        int nmax = 20;
        double e = 0.5 * Math.pow(10.0, -3.0);
        bisection('f', a, b, nmax, e);
    }

    private static void exercise_3_1_8() {
        System.out.println("\nExercise 3.1 #8");
        double a = 1.0;
        double b = 2.0;
        int nmax = 50;
        double e = 0.5 * Math.pow(10.0, -9.0);
        System.out.println("Bisection method:");
        bisection('g', a, b, nmax, e);
        System.out.println("\nModified false position method:");
        mfp(a, b, nmax, e);
    }

    private static void kashi() {
        System.out.println("\nCalculating sin(1) using Kashi's fixed-point method:");
        double sin3 = (Math.pow(2d, 0.5) / 16.0) * ((Math.pow(5d, 0.5) - 1) * (Math.pow(3, 0.5) + 1) - (Math.pow(10 + 2.0 * (Math.pow(5, 0.5)), 0.5)) * (Math.pow(3, 0.5) - 1));
        double x = 0;

        for (int i = 0; i < 5; i++) {
            x = (4.0/3.0) * Math.pow(x, 3.0) + sin3 / 3.0;
            System.out.println("Iteration " + i + ":  " + x);
        }
        System.out.print("Actual value: ");
        System.out.println(Math.sin(Math.toRadians(1.0)));
    }


    private static void bisection(char function, double a, double b, int nmax, double e) {
        double c, fa, fb, fc, error;
        if (function == 'f') {
            fa = f(a);
            fb = f(b);
        } else {
            fa = g(a);
            fb = g(b);
        }

        if (Math.signum(fa) == Math.signum(fb)) {
            System.out.println("a: " + a + " b: " + b + " fa: " + fa + " fb: " + fb);
            System.out.println("Function has same signs at a and b.");
            return;
        }
        error = b - a;
        for (int n = 0; n < nmax; n++) {
            error = error / 2.0;
            c = a + error;
            fc = (function == 'f' ? f(c) : g(c));
            System.out.println("n: " + n + " c: " + c + " fc: " + fc + " error: " + error);
            if (Math.abs(error) < e) {
                System.out.println("convergence");
                return;
            }
            if (Math.signum(fa) != Math.signum(fc)) {
                b = c;
                fb = fc;
            } else {
                a = c;
                fa = fc;
            }
        }
    }

    private static void mfp(double a, double b, int nmax, double e) {
        double c, ga, gb, gc, error;
        ga = g(a);
        gb = g(b);
        if (Math.signum(ga) == Math.signum(gb)) {
            System.out.println("a: " + a + " b: " + b + " ga: " + ga + " gb: " + gb);
            System.out.println("Function has same signs at a and b.");
            return;
        }

        for (int n = 0; n < nmax; n++) {
            c = (a * gb - b * ga) / (gb - ga);
            error = Math.abs(c - 1.368808108);
            gc = g(c);
            System.out.println("n: " + n + " c: " + c + " gc: " + gc + " error: " + error);
            if (error < e) {
                System.out.println("convergence");
                return;
            }
            if (Math.signum(ga) == Math.signum(gc)) {
                a = c;
                ga = gc;
            } else {
                b = c;
                gb = gc;
            }
        }


    }

    private static double f(double x) {
        return x * Math.cosh(50.0 / x) - x - 10.0;
    }

    private static double g(double x) {
        return -20.0 + x * (10.0 + x * (x + 2));
    }
}
