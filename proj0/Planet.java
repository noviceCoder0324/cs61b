/** This is the Planet class, which will make planet instances */
public class Planet {

    /** instance variables */
    public double xxPos; //Its current x position
    public double yyPos; //Its current y position
    public double xxVel; //Its current velocity in the x direction
    public double yyVel; //Its current velocity in the y direction
    public double mass; //Its mass
    public String imgFileName; // The name of the file that corresponds to the image that depicts the planet(for example, jupiter.gif)

    /** constant */
    private static double G_CONSTANT = 6.67e-11;

    /** Constructors */
    /** construct a Planet in a normal way*/
    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    /** construct a planet by copying another one */
    public Planet(Planet p){
        this(p.xxPos, p.yyPos, p.xxVel, p.yyVel, p.mass, p.imgFileName);
    }

    /** Methods */
    /** calculate the distance from this planet to the other planet p */
    public double calcDistance(Planet p) {
        double dx = this.xxPos - p.xxPos;
        double dy = this.yyPos - p.yyPos;
        return Math.sqrt(dx*dx+dy*dy);
    }
    /** calculate the force between this planet and the other planet p */
    public double calcForceExertedBy(Planet p) {
        return (G_CONSTANT * this.mass * p.mass)/Math.pow(this.calcDistance(p), 2);
    }

    /** calculate the exerted force on the direction of x */
    public double calcForceExertedByX(Planet p) {
        double dx = p.xxPos - this.xxPos;
        return (this.calcForceExertedBy(p) * dx)/this.calcDistance(p);
    }
    /** calculate the exerted force on the direction of y */
    public double calcForceExertedByY(Planet p) {
        double dy = p.yyPos - this.yyPos;
        return (this.calcForceExertedBy(p) * dy)/this.calcDistance(p);
    }

    /** calculate the net force for X on a planet from a list of other planets together. */
    public double calcNetForceExertedByX(Planet[] lst) {
        double result = 0;
        for (Planet p:lst){
            if (!this.equals(p)){
                result += this.calcForceExertedByX(p);
            }
        }
        return result;
    }
    /** calculate the net force for Y on a planet from a list of other planets together. */
    public double calcNetForceExertedByY(Planet[] lst) {
        double result = 0;
        for (Planet p:lst){
            if (!this.equals(p)){
                result += this.calcForceExertedByY(p);
            }
        }
        return result;
    }
    /** update this planet's position and velocity instance variables */
    public void update(double dt, double fx, double fy){
        this.xxVel += dt * (fx / this.mass);
        this.yyVel += dt * (fy / this.mass);
        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;
    }
    /** draw the planet itself. */
    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "images/"+this.imgFileName);
    }
}