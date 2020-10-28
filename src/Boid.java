import java.awt.*;
import java.util.Random;

/**
 *This is the Boid class, the point of this class is to handle all boid objects
 * Documentation Statement: I received some help from Dr. Hadfield who informed me that I needed to use a boolean
 * to ensure that I was pausing.
 * @author C22Andrew.Lee
 */
public class Boid {
    private java.awt.Color color; //color class
    private Vector330 location; //location vector
    private static java.util.Random rand = new Random(System.currentTimeMillis());
    public static int screenHeight;
    public static int screenWidth;
    private int size; //size of the boid to draw
    private Vector330 velocity; //velocity vector

    /**
     * Boid() - constructor, random location and velocity
     * @param color - color of the boid
     * @param size - size of the boid
     */
    public Boid(Color color, int size){
        this.location = new Vector330(screenHeight * rand.nextDouble(), screenWidth * rand.nextDouble());
        //set random velocity, can be negative but not zero
        double xV = (14 * rand.nextDouble()) - 7;
        if(xV == 0)
            xV++;

        //set random velocity, can be negative but no zero
        double yV = (14 * rand.nextDouble()) - 7;
        if(yV == 0)
            yV++;

        this.velocity = new Vector330(xV, yV);
        this.color = color;
        this.size = size;
    }

    /**
     * draw() - draw the boid and the little line that is pointing (velocity as
     * a unit vector)
     * @param g - graphics object
     */
    void draw(Graphics2D g){
        g.setColor(color);
        //need the size / 2 otherwise behavior is off
        g.fillOval((int) this.location.getX() - size/2, (int) this.location.getY() - size/2, size, size);
        //find the endpoint by normalizing / unit vector
        Vector330 endPoint = location.add(velocity.normalize().scale(size));
        g.drawLine(location.getXint(), location.getYint(), endPoint.getXint(), endPoint.getYint());
    }

    /**
     * move() - move boid by adding the velocity to the location and check to see that
     * it is still within the proper bounds
     */
    void move(){
        location.setX(location.getX() + velocity.getX());
        location.setY(location.getY() + velocity.getY());
        //moves the bad boy
        //the 2 acts as a slight buffer / frame for smaller sized boids
        //if x is greater than the width -2 or under 2

        if(FlockSim.wrap){
            if(location.getX() > screenWidth - size/2.0 || location.getX() < size/2.0) {
                //turn around
                velocity.setX(velocity.getX() * -1);
            }

            //if y is greater than the height -2 or under 2
            if(location.getY() > screenHeight - size/2.0 || location.getY() < size/2.0) {
                //turn around
                velocity.setY(velocity.getY() * -1);
            }
        }
        else {
            //makes the wrap around
            if(location.getX() > screenWidth - size/2.0) {
              location.setX(size /2.0);
            }
            else if(location.getX() < size/2.0){
                location.setX(screenWidth - size/2.0);
            }

            if(location.getY() > screenHeight - size/2.0){
                location.setY(size/2.0);
            }
            else if(location.getY() < size/2.0){
                location.setY(screenHeight - size/2.0);
            }

        }


    }

    /**
     * setScreenHeight() - used to set the height of the screen
     * @param screenHeight - the height to be set to
     */
    public static void setScreenHeight(int screenHeight){
        Boid.screenHeight = screenHeight;
    }

    /**
     * setScreenHeight() - used to set the width of the screen
     * @param screenWidth - the width to be set to
     */
    public static void setScreenWidth(int screenWidth){
        Boid.screenWidth = screenWidth;
    }

    /**
     * newVelocity() - sets the new velocity
     * @param x - new x value
     * @param y - new y value
     */
    public void newVelocity(double x, double y){
        velocity.setX(x);
        velocity.setY(y);
    }

    /**
     * TnewVelocity() - sets the velocity plus the x value
     * this was only used for the testing of flock and
     * cohesion / alignment / separation
     * @param x - new x value
     * @param y - new y value
     */
    public void TnewVelocity(double x, double y){
        velocity.setX(velocity.getX() + x);
        velocity.setY(velocity.getY() + y);
    }

    /**
     * getLocation() - gets the location as a vector
     * @return returns the location Vector
     */
    public Vector330 getLocation(){
        return location;
    }

    /**
     * getVelocity() - gets the velocity as a vector
     * @return - return the velocity
     */
    public Vector330 getVelocity(){
        return velocity;
    }
}
