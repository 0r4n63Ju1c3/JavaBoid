import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *This is the Vector330 class, the point of this class is to handle all vector objects
 * Documentation Statement: I received some help from Dr. Hadfield who informed me that I was parsing my inputs
 * the incorrect way. He showed me how to do it along with where I was going wrong.
 * @author C22Andrew.Lee
 */
public class Vector330 {

    private static double EPS = 1.0E-9; //constant epsilon
    private double x; //instance variable of x
    private double y; //instance variable of y

    /**
     * Vector330()
     * constructor, instance variable set to 0 with no parameters
     */
    Vector330(){
        this.x = 0;
        this.y = 0;
    }

    /**
     * Vector330() constructor based off of two double values
     * @param x - first x value based on double
     * @param y - first y value based on double
     */
    Vector330(double x, double y){
        this.x = x;
        this.y = y;
    }

    /**
     * Vector330() constructor based off of two int values
     * @param x - first x value based on int
     * @param y - first y value based on int
     */
    Vector330(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Vector330() constructor based off of two long values
     * @param x - first x value based on long
     * @param y - first y value based on long
     */
    Vector330(long x, long y){
        this.x = x;
        this.y = y;
    }

    /**
     * add() - computes the sum of two vectors
     * @param v vector to be subtracted
     * @return new sum vector
     */
    public Vector330 add(Vector330 v){
        return new Vector330(v.getX() + x, v.getY() + y);
    }

    /**
     * subtract() - computes the difference of two vectors
     * @param v vector to be added
     * @return new difference vector
     */
    public Vector330 subtract(Vector330 v){
         return new Vector330(x - v.getX(), y - v.getY());
    }

    /**
     * dotProduct() - computes the dot product of two vectors
     * @param v vector to be multiplied with
     * @return the dot product as a double
     */
    public double dotProduct(Vector330 v){
        return (v.getX() * x) + (v.getY() * y);
    }

    /**
     * direction() - finds the direction using arctan of the vector and returns in radians
     * @return the direction in radians
     */
    double direction(){
        return Math.atan((y/x));
    }

    /**
     * scale() - scales a vector by the inputted double value
     * @param s the value to be scaled by
     * @return a new scaled vector
     */
    public Vector330 scale(double s){
        return new Vector330(x*s, y*s);
    }

    /**
     * magnitude() - returns the magnitude of the vector, calculated using pythagorean theorem
     * @return the magnitude that was found
     */
    double magnitude(){
        return Math.sqrt((x*x) + (y*y));
    }

    /**
     * equals() - checks to see if inputted vector is equal to current vector
     * @param v the inputted vector
     * @return true if equal, false if not
     */
    public boolean equals(Vector330 v){
        return Math.abs(v.getX() - x)< EPS && Math.abs(v.getY() -  y) < EPS;
    }

    /**
     * normalize() - creates a unit vector from the original vector
     * if the magnitude is less than EPS returns a zero vector
     * @return zero vector or unit vector accordingly
     */
    public Vector330 normalize(){
        double total = Math.sqrt((x*x) + (y*y));
        if(total < EPS) {
            return new Vector330();
        }
        return new Vector330(x/total,y/total);
    }

    /**
     * toString() - overrides the toString method to print
     * @return returns the formatted string
     */
    @Override
    public String toString(){
        return "< " + x + ", " + y + " >";
    }

    /**
     * parseVector() - parses the input vector to create a new Vector330 object
     * @param s scanner for parsing
     * @return new Vector330 object
     * @throws java.lang.Exception
     */
    public static Vector330 parseVector (Scanner s) throws java.lang.Exception{
//        String toVector = s.nextLine();
//        int length = toVector.length();
//        int comma = toVector.indexOf(',');
//        String first = toVector.substring(1, comma);
//        String second = toVector.substring(comma+2, length - 3);
//        return new Vector330(Double.parseDouble(first), Double.parseDouble(second));
        //original way I was going to parse

        double newX; //new x value
        double newY; //new y value

        Pattern originalPattern = s.delimiter(); //retain original delimiter
        s.useDelimiter("[" + originalPattern + ",]"); //add comma

        if(s.hasNext("<")){
            s.next("<"); // eat the <
            if(s.hasNextDouble()){
                newX = s.nextDouble(); //scan double to x value
                s.useDelimiter(originalPattern); //restore original delimiter

                if(s.hasNext(",")){
                    s.next(","); //eat the comma

                    if(s.hasNextDouble()){
                        newY = s.nextDouble(); //scan double to y value

                        if(s.hasNext(">")){
                            s.next(">"); //eat the >
                            return new Vector330(newX, newY); //create a new Vector330
                        }
                        else{
                            throw new Exception("PARSE ERROR: Missing '>' to end vector");
                            //throw exception if > is missing
                        }
                    }
                    else{
                        throw new Exception("PARSE ERROR: Missing y value");
                        //throw exception if y value cannot be found
                    }
                }
                else{
                    throw new Exception("PARSE ERROR: Missing ',' for separation");
                    //throw exception if there is no comma
                }
            }
            else{
                throw new Exception("PARSE ERROR: Missing x value");
                //throw exception if there is no x value
            }
        }
        else{
            throw new Exception("PARSE ERROR: Missing '<' to start vector");
            //throw exception if there is no <
        }

    }



    //-------------------------------------------------------------------------------------------------------//

    /**
     * getX() - get x
     * @return x as double
     */
    public double getX(){
        return x;
    }

    /**
     * getXint() - get x as int
     * @return x as int
     */
    public int getXint(){
        return (int) x;
    }

    /**
     * getXlong() - get x as long
     * @return x as long
     */
    public long getXlong(){
        return (long) x;
    }

    /**
     * getY() - get y
     * @return y as double
     */
    public double getY(){
        return y;
    }

    /**
     * getYint() - get y as int
     * @return y as int
     */
    public int getYint(){
        return (int) y;
    }

    /**
     * getYlong() - get y as long
     * @return y as long
     */
    public long getYlong(){
        return (long) y;
    }

    /**
     * setX() sets x as double
     * @param x value to set
     */
    public void setX(double x){
        this.x = x;
    }

    /**
     * setXing() sets x as int
     * @param x value to set
     */
    public void setXint(int x){
        this.x = x;
    }

    /**
     * setXlong() sets x as long
     * @param x value to set
     */
    public void setXlong(long x){
        this.x = x;
    }

    /**
     * setY() sets y as double
     * @param y value to set
     */
    public void setY(double y){
        this.y = y;
    }

    /**
     * setYint() sets y as int
     * @param y value to set
     */
    public void setYint(int y){
        this.y = y;
    }

    /**
     * setXlong() sets y as long
     * @param y value to set
     */
    public void setYlong(long y){
        this.y = y;
    }


}
