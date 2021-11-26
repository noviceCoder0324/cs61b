/** This class will actually run this simulation */
public class NBody {
    
    /** take in the file address as String and return the radius of universe */
    public static double readRadius(String file){
        In in = new In(file);
        int x = in.readInt();
        return in.readDouble();
    }

    /**return an array of Planets corresponding to the planets in the file */
    public static Planet[] readPlanets(String file){
        In in = new In(file);
        int num = in.readInt();
        double y = in.readDouble();
        Planet[] planets_lst = new Planet[num];
        int i = 0;
        while (i < num){
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            planets_lst[i] = new Planet(xP, yP, xV, yV, m, img);
            i += 1;
        }
        return planets_lst;
    }

    /** This is the run code for this project */
    public static void main(String[] args){
        /** read information from args */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        /** read in information from file*/
        Planet[] planets = NBody.readPlanets(filename);
        double radius = NBody.readRadius(filename);

        /** STEP 1 prevent flickering in the animation */
        StdDraw.enableDoubleBuffering();
        StdAudio.play("audio/2001.mid");

        /** STEP 2 create time variable and set the loop */
        double time = 0;
        while (time < T){
            /** STEP 3 create 2 arrays to save the data for all forces */
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for (int i=0; i < planets.length; i++){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            /** update all the positions for all the planets */
            for (int i=0; i < planets.length; i++){
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            /** draw the background */
            StdDraw.setScale(-radius, radius);
            StdDraw.picture(0, 0, "images/starfield.jpg");

            /** draw all planets */
            for (Planet p:planets){
                p.draw();
            }
            /** show the offscreen buffer */
            StdDraw.show();

            /** pause for 10*/
            StdDraw.pause(10);

            /** increase time */
            time += dt;
        }

        /** print out the information, answer provided by teacher. */
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++){
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", planets[i].xxPos, planets[i].yyPos, planets[i].xxVel, planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }

    }
}
