import java.math.*;
/** This test is just a combination of all other tests. 
 * It is a good way to see what's the pattern for testing doc.
 */
public class TestPlanet {
    public static void main(String[] args) {
        checkUpdate();
        checkCalcDistance();
        checkCalcForceExertedBy();
        calcNetForceExertedByXY();
        checkCalcForceExertedByXY();
        checkPlanetConstructor();
    }

    /** check update */
    private static void checkUpdate() {
        System.out.println("Checking update...");

        Planet p1 = new Planet(1.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");

        p1.update(2.0, 1.0, -0.5);

        checkEquals(3.4, p1.xxVel, "xxVel update()", 0.01);
        checkEquals(3.8, p1.yyVel, "yyVel update()", 0.01);
        checkEquals(7.8, p1.xxPos, "xxPos update()", 0.01);
        checkEquals(8.6, p1.yyPos, "yyPos update()", 0.01);
    }

    /** check calc distance */
    private static void checkCalcDistance() {
        System.out.println("Checking calcDistance...");

        Planet p1 = new Planet(1.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
        Planet p2 = new Planet(2.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
        Planet p3 = new Planet(4.0, 5.0, 3.0, 4.0, 5.0, "jupiter.gif");

        checkEquals(p1.calcDistance(p2), 1.0, "calcDistance()", 0.01);
        checkEquals(p1.calcDistance(p3), 5.0, "calcDistance()", 0.01);
    }

    /** check calc force exerted by */
    private static void checkCalcForceExertedBy() {
        System.out.println("Checking calcForceExertedBy...");

        Planet p1 = new Planet(1.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
        Planet p2 = new Planet(2.0, 1.0, 3.0, 4.0, 4e11, "jupiter.gif");
        Planet p3 = new Planet(4.0, 5.0, 3.0, 4.0, 5.0, "jupiter.gif");

        checkEquals(p1.calcForceExertedBy(p2), 133.4, "calcForceExertedBy()", 0.01);
        checkEquals(p1.calcForceExertedBy(p3), 6.67e-11, "calcForceExertedBy()", 0.01);
    }

    /** check calc net force exerted by XY */
    private static void calcNetForceExertedByXY() {
        System.out.println("Checking calcNetForceExertedByXY...");

        Planet p1 = new Planet(1.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
        Planet p2 = new Planet(2.0, 1.0, 3.0, 4.0, 4e11, "jupiter.gif");
        
        Planet p3 = new Planet(4.0, 5.0, 3.0, 4.0, 5.0, "jupiter.gif");
        Planet p4 = new Planet(3.0, 2.0, 3.0, 4.0, 5.0, "jupiter.gif");

        Planet[] planets = {p2, p3, p4};

        double xNetForce = p1.calcNetForceExertedByX(planets);
        double yNetForce = p1.calcNetForceExertedByY(planets);

        checkEquals2(133.4, round(xNetForce, 2), "calcNetForceExertedByX()");
        checkEquals2(0.0, round(yNetForce, 2), "calcNetForceExertedByY()");
    
        System.out.println("Running test again, but with array that contains the target planet.");

        planets = new Planet[]{p1, p2, p3, p4};

        xNetForce = p1.calcNetForceExertedByX(planets);
        yNetForce = p1.calcNetForceExertedByY(planets);

        checkEquals2(133.4, round(xNetForce, 2), "calcNetForceExertedByX()");
        checkEquals2(0.0, round(yNetForce, 2), "calcNetForceExertedByY()");

    }

    /** check calc force exerted by XY */
    private static void checkCalcForceExertedByXY() {
        System.out.println("Checking calcForceExertedByX and calcForceExertedByY...");

        Planet p1 = new Planet(1.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
        Planet p2 = new Planet(2.0, 1.0, 3.0, 4.0, 4e11, "jupiter.gif");
        Planet p3 = new Planet(4.0, 5.0, 3.0, 4.0, 5.0, "jupiter.gif");

        checkEquals3(p1.calcForceExertedByX(p2), 133.4, "calcForceExertedByX()", 0.01);
        checkEquals3(p1.calcForceExertedByX(p3), 4.002e-11, "calcForceExertedByX()", 0.01);
        checkEquals3(p1.calcForceExertedByY(p2), 0.0, "calcForceExertedByY()", 0.01);
        checkEquals3(p1.calcForceExertedByY(p3), 5.336e-11, "calcForceExertedByY()", 0.01);
    }

    /** check planet constructor */
    private static void checkPlanetConstructor() {
        System.out.println("Checking first Planet constructor...");

        double xxPos = 1.0,
               yyPos = 2.0,
               xxVel = 3.0,
               yyVel = 4.0,
               mass = 5.0;

        String imgFileName = "jupiter.gif";

        Planet p = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);

        checkEquals2(xxPos, p.xxPos, "xxPos");
        checkEquals2(yyPos, p.yyPos, "yyPos");
        checkEquals2(xxVel ,p.xxVel, "xxVel");
        checkEquals2(yyVel, p.yyVel, "yyVel");
        checkEquals2(mass, p.mass, "mass");
        checkStringEquals(imgFileName, p.imgFileName, "path to image");

        System.out.println("Checking second Planet constructor...");

        Planet pCopy = new Planet(p);
        checkEquals2(p.xxPos, pCopy.xxPos, "xxPos");
        checkEquals2(p.yyPos, pCopy.yyPos, "yyPos");
        checkEquals2(p.xxVel, pCopy.xxVel, "xxVel");
        checkEquals2(p.yyVel, pCopy.yyVel, "yyVel");
        checkEquals2(p.mass, pCopy.mass, "mass");
        checkStringEquals(p.imgFileName, pCopy.imgFileName, "path to image");
    }

    private static void checkStringEquals(String expected, String actual, String label) {
        if (expected.equals(actual)) {
            System.out.println("PASS: " + label + ": Expected " + expected + " and you gave " + actual);
        } else {
            System.out.println("FAIL: " + label + ": Expected " + expected + " and you gave " + actual);
        }
    }

    private static void checkEquals3(double actual, double expected, String label, double eps) {
        if (approxEqual(actual, expected, eps)) {
            System.out.println("PASS: " + label + ": Expected " + expected + " and you gave " + actual);
        } else {
            System.out.println("FAIL: " + label + ": Expected " + expected + " and you gave " + actual);
            if (approxEqual(actual, expected, eps)) {
                System.out.println("      Hint: Your answer is exactly opposite of the correct answer.");
            }
        }
    }

    private static void checkEquals2(double expected, double actual, String label) {
        if (expected == actual) {
            System.out.println("PASS: " + label + ": Expected " + expected + " and you gave " + actual);
        } else {
            System.out.println("FAIL: " + label + ": Expected " + expected + " and you gave " + actual);
        }
    }

    private static void checkEquals(double expected, double actual, String label, double eps) {
        if (Math.abs(expected - actual) <= eps * Math.max(expected, actual)) {
            System.out.println("PASS: " + label + ": Expected " + expected + " and you gave " + actual);
        } else {
            System.out.println("FAIL: " + label + ": Expected " + expected + " and you gave " + actual);
        }
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private static boolean approxEqual(double actual, double expected, double eps) {
        return Math.abs(expected - actual) <= eps * Math.max(expected, actual);
    }

}
