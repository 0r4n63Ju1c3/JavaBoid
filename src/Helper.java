import java.awt.*;


/**
 *This is the helper class, the point of this class is create a way for the GUI to interact with
 * the other classes, including flocksim, main, etc. In other words, this allowed there to be a constant flow
 * of communication between GUI and main.
 * Documentation Statement: none used
 * @author C22Andrew.Lee
 */

public class Helper {

    //basic flock attributes
    private double flockSpeed;
    private Color color; //color
    private int size; //size
    private int boids; //num boids
    private int numOfFlocks;

    //radius for alignment, separation, cohesion and dispersion
    private int aRadius;
    private int sRadius;
    private int cRadius;
    private int dRadius;

    //flags to check if GUI triggered
    //these are used for communication
    private boolean adding;
    private boolean subtracting;
    private boolean editing;
    private boolean done;
    private boolean reset;
    private boolean pause;
    private boolean end;

    //index of the flock
    private int index;
    //flock name
    private String name;
    //set the different weights for velocity, cohesion, alignment, separation
    private double vScale, cScale, aScale, sScale;


    /**
     * Helper() - constructor for Helper class.
     * sets all of the flags to no triggered
     * sets all of the values to 0
     */
    public Helper(){
        this.color = Color.CYAN;
        this.size = 0;
        this.boids = 0;
        this.aRadius = 0;
        this.cRadius = 0;
        this.sRadius = 0;
        this.dRadius = 0;
        this.flockSpeed = 0;
        this.name = "name";
        this.index = 0;
        this.reset = false;
        this.pause = false;
        this.end = false;
        this.done = false;
        this.adding = false;
        this.subtracting = false;
        this.editing = false;
        this.numOfFlocks = 0;

        vScale = 0;
        cScale = 0;
        aScale = 0;
        sScale = 0;

    }

    /**
     * @return color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @return size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @return number of boids
     */
    public int getBoids() {
        return boids;
    }

    /**
     * @param boids num boids to set
     */
    public void setBoids(int boids) {
        this.boids = boids;
    }

    /**
     * @return alignment radius
     */
    public int getaRadius() {
        return aRadius;
    }

    /**
     * @param aRadius - alignment radius to set
     */
    public void setaRadius(int aRadius) {
        this.aRadius = aRadius;
    }

    /**
     * @return separation radius
     */
    public int getsRadius() {
        return sRadius;
    }

    /**
     * @param sRadius seperation radius to set
     */
    public void setsRadius(int sRadius) {
        this.sRadius = sRadius;
    }

    /**
     * @return dispersion radius
     */
    public int getdRadius() {
        return dRadius;
    }

    /**
     * @param dRadius dispersion radius to set
     */
    public void setdRadius(int dRadius) {
        this.dRadius = dRadius;
    }

    /**
     * @return flock speed
     */
    public double getFlockSpeed() {
        return flockSpeed;
    }

    /**
     * @param flockSpeed flock speed to set
     */
    public void setFlockSpeed(double flockSpeed) {
        this.flockSpeed = flockSpeed;
    }

    /**
     * @return number of flocks
     */
    public int getNumOfFlocks() {
        return numOfFlocks;
    }

    /**
     * @param numOfFlocks number of flocks to set
     */
    public void setNumOfFlocks(int numOfFlocks) {
        this.numOfFlocks = numOfFlocks;
    }

    /**
     * @return cohesion radius
     */
    public int getcRadius() {
        return cRadius;
    }

    /**
     * @param cRadius cohesion radius to set
     */
    public void setcRadius(int cRadius) {
        this.cRadius = cRadius;
    }

    /**
     * @return adding flag
     */
    public boolean isAdding() {
        return adding;
    }

    /**
     * @param adding set flag
     */
    public void setAdding(boolean adding) {
        this.adding = adding;
    }

    /**
     * @return subtracting flag
     */
    public boolean isSubtracting() {
        return subtracting;
    }

    /**
     * @param subtracting set flag
     */
    public void setSubtracting(boolean subtracting) {
        this.subtracting = subtracting;
    }

    /**
     * @return editing flag
     */
    public boolean isEditing() {
        return editing;
    }

    /**
     * @param editing set editing flag
     */
    public void setEditing(boolean editing) {
        this.editing = editing;
    }

    /**
     * @return done flag
     */
    public boolean isDone() {
        return done;
    }

    /**
     * @param done set done flag
     */
    public void setDone(boolean done) {
        this.done = done;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param index index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * @return paused flag
     */
    public boolean isPause() {
        return pause;
    }

    /**
     * @param pause set pause flag
     */
    public void setPause(boolean pause) {
        this.pause = pause;
    }

    /**
     * @return reset flag
     */
    public boolean isReset() {
        return reset;
    }

    /**
     * @param reset flag to set
     */
    public void setReset(boolean reset) {
        this.reset = reset;
    }

    /**
     * @return end flag
     */
    public boolean isEnd() {
        return end;
    }

    /**
     * @param end flag to set
     */
    public void setEnd(boolean end) {
        this.end = end;
    }

    /**
     * @return velocity weight
     */
    public double getvScale() {
        return vScale;
    }

    /**
     * @param vScale velocity weight to set
     */
    public void setvScale(double vScale) {
        this.vScale = vScale;
    }

    /**
     * @return cohesion weight
     */
    public double getcScale() {
        return cScale;
    }

    /**
     * @param cScale cohesion weight to set
     */
    public void setcScale(double cScale) {
        this.cScale = cScale;
    }

    /**
     * @return alignment weight
     */
    public double getaScale() {
        return aScale;
    }

    /**
     * @param aScale alignment weight to set
     */
    public void setaScale(double aScale) {
        this.aScale = aScale;
    }

    /**
     * @return separation weight
     */
    public double getsScale() {
        return sScale;
    }

    /**
     * @param sScale separation weight to set
     */
    public void setsScale(double sScale) {
        this.sScale = sScale;
    }

}
