import java.awt.*;
import java.util.*;


/**
 *This is the Flock class, the point of this class is to handle all boid objects as a flock
 * these are grouped together boids in an array list
 * Documentation Statement: I received some help from Dr. Hadfield who informed me that I needed to use a boolean
 * to ensure that I was pausing.
 * @author C22Andrew.Lee
 */
public class Flock {
    private ArrayList<Boid> flock = new ArrayList<>(); //stores the flock
    private double cRadius; //radius for cohesion
    private double aRadius; //radius for alignment
    private double sRadius; //radius for separation
    private double dRadius; //radius for dispersion
    private double flockSpeed; //the speed of the flock

    private java.util.Random rand = new Random(System.currentTimeMillis());


    private double vScale; //scale for velocity
    private double cScale; //cohesion scale
    private double aScale; //alignment scale
    private double sScale; //separation Scale

    /**
     * Flock() - constructor for the flock sets
     * variables and loads the arrayList
     * @param color - the color to make flock
     * @param size - the size of each boid
     * @param boids - number of boids
     * @param cRadius - radius of cohesion
     * @param aRadius - radius of alignment
     * @param sRadius - radius of separation
     * @param dRadius - radius of dispersion
     * @param flockSpeed - speed of the flock
     */
    public Flock(Color color, int size, int boids, double cRadius,double aRadius, double sRadius,double dRadius, double flockSpeed){
        this.cRadius = cRadius;
        this.aRadius = aRadius;
        this.sRadius = sRadius;
        this.dRadius = dRadius;
        this.flockSpeed = flockSpeed;

        vScale = .4;
        cScale = .2;
        aScale = .2;
        sScale = .2;


        int i = 0;
        while(i < boids){
            //populate the array list
            flock.add(new Boid(color, size));
            i++;
        }
    }

    /**
     * drawFlock() - draws the flock
     * @param g - graphics2D object
     */
    public void drawFlock(Graphics2D g){
        //loop through flock and draw
        for (Boid boid: flock)
            boid.draw(g);
    }

    /**
     * moveFlock() - moves the flock
     * @param g - graphics2D object
     */
    public void moveFlock(Graphics2D g){
        //loop through flock and move
        for (Boid boid: flock)
            boid.move();
    }

    /**
     * setScreenHeight() - sets the height of the screen
     * @param height - graphics2D object
     */
    public static void setScreenHeight(int height){
        //set the screen height
        Boid.setScreenHeight(height);
    }

    /**
     * setScreenWidth() - sets the width of the screen
     * @param width - graphics2D object
     */
    public static void setScreenWidth(int width){
        //set the screen width
        Boid.setScreenWidth(width);
    }

    /**
     * cohesion() - brings the boids closer together by acting as a
     * gravity of sort, meet at the average. Loops through the flock and
     * checks each Boid against each other.
     * @param checking - the Boid that is going to be checked against
     * @return ret - the average computed as a unit vector
     */
    private Vector330 cohesion(Boid checking){
        //the total
        int tot = 0;
        double averageLocY = 0; //running total for Y
        double averageLocX = 0; //running total for X

        for (Boid boid: flock) {
            if(Math.abs(boid.getLocation().getX() - checking.getLocation().getX()) < cRadius){
                if(Math.abs(boid.getLocation().getY() - checking.getLocation().getY()) < cRadius){
                    //if other boid is within radius of one being checked
                    tot++;
                    averageLocX += boid.getLocation().getX();
                    averageLocY += boid.getLocation().getY();
                }
            }
        }
        Vector330 avg= new Vector330(averageLocX/tot, averageLocY/tot); //find the average location
        Vector330 ret = (avg.subtract(checking.getLocation())).normalize(); //subtract and find unit vector
        return ret; //return
    }

    /**
     * alignment() - acts to ensure that all the boids are going in the
     * correct direction by adding all vectors up. Loops through the flock and
     * checks each Boid against each other.
     * @param checking - the Boid that is going to be checked against
     * @return ret - the total vectors added up and then found as unit vector
     */
    private Vector330 alignment(Boid checking){
        double xVel = 0; //running total
        double yVel = 0; //running total
        for (Boid boid: flock) {
            if(Math.abs(boid.getLocation().getX() - checking.getLocation().getX()) < aRadius){
                if(Math.abs(boid.getLocation().getY() - checking.getLocation().getY()) < aRadius){
                    //if within the radius add
                    xVel += boid.getVelocity().getX();
                    yVel += boid.getVelocity().getY();
                }
            }
        }
        Vector330 ret = new Vector330(xVel, yVel); //make a new vector with totals
        return ret.normalize(); //return as a unit vector
    }

    /**
     * separation() - brings the boids farther apart by acting as anti gravity, if
     * they are close then go opposite direction. Loops through the flock and
     * checks each Boid against each other.
     * @param checking - the Boid that is going to be checked against
     * @return ret - the direction that Boid should move to escape
     */
    private Vector330 separation(Boid checking){
        double xVal = 0; //running total
        double yVal = 0; //runing total


        for (Boid boid: flock) {
            if(Math.abs(boid.getLocation().getX() - checking.getLocation().getX()) < sRadius){
                if(Math.abs(boid.getLocation().getY() - checking.getLocation().getY()) < sRadius){
                    //if boid exists within radius of one being checked
                    Vector330 Offset = checking.getLocation().subtract(boid.getLocation()); //create new difference vector
                    Vector330 unit = Offset.normalize(); //unit vector
                    //scale the unit vector - distance
                    //find the distance using distance formula
                    double xdistance = (checking.getLocation().getX() - boid.getLocation().getX()) * (checking.getLocation().getX() - boid.getLocation().getX());
                    double ydistance = (checking.getLocation().getY() - boid.getLocation().getY()) * (checking.getLocation().getY() - boid.getLocation().getY());
                    Vector330 scaled = unit.scale(sRadius-(Math.sqrt(xdistance + ydistance))); //scale
                    //add to the running total
                    xVal += scaled.getX();
                    yVal += scaled.getY();
                }
            }
        }
        Vector330 ret = new Vector330(xVal, yVal);
        return ret.normalize(); //return normalized vector
    }

    /**
     * disperse() - takes the location of the mouse click and has the nearby boids
     * avoid by moving away to get radius away from epicenter
     * @param x - X location of the mouse
     * @param y - Y location of the mouse
     */
    public void disperse(double x, double y){
        for(Boid boid: flock){
            //check how far away from radius
            double xLoc = dRadius - Math.abs(x - boid.getLocation().getX());
            double yLoc  = dRadius - Math.abs(y - boid.getLocation().getY());

            //if it is within the bounds of the radius
            if(Math.abs(boid.getLocation().getX() - x) < dRadius && Math.abs(boid.getLocation().getY() - y) < dRadius) {
                //if it is closer to y loc
                if (xLoc >= yLoc) {
                    //move the to the y radius max depending whether you are above or below
                    if (boid.getLocation().getY() >= y)
                        boid.getLocation().setY(y + dRadius);
                    if (boid.getLocation().getY() < y)
                        boid.getLocation().setY(y - dRadius);
                } else {
                    //move the to the x radius max depending whether you are left or right
                    if (boid.getLocation().getX() >= x)
                        boid.getLocation().setX(x + dRadius);
                    if (boid.getLocation().getX() < x)
                        boid.getLocation().setX(x - dRadius);
                }

                //check to ensure that the boid was not pushed outside boundary of the box
                if (boid.getLocation().getX() >= Boid.screenWidth)
                    boid.getLocation().setX(Boid.screenWidth - 3);
                if (boid.getLocation().getX() <= 0)
                    boid.getLocation().setX(2);
                if (boid.getLocation().getY() >= Boid.screenHeight)
                    boid.getLocation().setY(Boid.screenHeight - 3);
                if (boid.getLocation().getY() <= 0)
                    boid.getLocation().setY(2);
            }


        }
    }

    /**
     * accumulate() - loops through the flock and does cohesion, alignment and separation if necessary
     * combines all values using the proper weights .4 for velocity, .2 for everything else and then
     * sets the old velocity values to this new velocity value
     */
    public void accumalate(){
        for(Boid boid: flock){
            //loop through get old velocity value
            double velX = boid.getVelocity().getX() * vScale;
            double velY = boid.getVelocity().getY() * vScale;

            //get values for cohesion
            Vector330 cohesion = cohesion(boid);
            double velXc = cohesion.getX() * cScale;
            double velYc = cohesion.getY() * cScale;

            //get values for alignment
            Vector330 alignment = alignment(boid);
            double velXa = alignment.getX() * aScale;
            double velYa = alignment.getY() * aScale;

            //get values for seperation
            Vector330 separation = separation(boid);
            double velXs = separation.getX() * sScale;
            double velYs = separation.getY() * sScale;

            //add everything together to get the new velocity value
            Vector330 newVel = new Vector330(velX + velXc + velXa + velXs, velY + velYc + velYa + velYs);
            Vector330 velToBe = newVel.scale(flockSpeed); //scale by the flock speed
            boid.newVelocity(velToBe.getX(), velToBe.getY()); //set new velocity
        }
    }

    /**
     * setScales() - sets the weights
     * sets all the weights that are being used from the GUI
     * @param vScale - velocity weight
     * @param aScale - alignment scale
     * @param cScale - cohesion scale
     * @param sScale - separation scale
     */
    public void setScales(double vScale, double cScale, double aScale, double sScale){
        this.vScale = vScale;
        this.cScale = cScale;
        this.aScale = aScale;
        this.sScale = sScale;
    }

    /**
     * randomize() - randomizes all of the values including the location
     * and the velocities
     */
    public void randomize(){
        for(Boid b : flock){
            b.getLocation().setX(600 * rand.nextDouble());
            b.getLocation().setY(600 * rand.nextDouble());

            double xV = (14 * rand.nextDouble()) - 7;
            if(xV == 0)
                xV++;
            //set random velocity, can be negative but no zero
            double yV = (14 * rand.nextDouble()) - 7;
            if(yV == 0)
                yV++;

            b.getVelocity().setX(xV);
            b.getVelocity().setY(yV);
        }
    }

}
