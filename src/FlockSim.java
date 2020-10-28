import java.awt.*;
import java.util.ArrayList;

/**
 *This is the FlockSim class, the point of this class is to handle all flocks and run the main logics of
 * the simulation. Takes care of all user input
 * Documentation Statement: I received some help from Dr. Hadfield who informed me that I needed to use a boolean
 * to ensure that I was pausing.
 * @author C22Andrew.Lee
 */
public class FlockSim {
    private int screenHeight;
    private int screenWidth;

    public static boolean wrap = false;

    private ArrayList<Flock> flocks = new ArrayList<>(); //list of flocks

    /**
     * Screen() constructor used to make the screen,
     * @param screenHeight - height of screen
     * @param screenWidth - width of screen
     */
    public FlockSim(int screenWidth, int screenHeight){
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        //pass variables onto the Flock class
        Flock.setScreenHeight(screenHeight);
        Flock.setScreenWidth(screenWidth);
    }

    /**
     * active() used to make the screen active
     * no parameters and no return value
     */
    public void active(Helper h){
        boolean paused = h.isPause();
        DrawingPanel panel = new DrawingPanel(screenWidth, screenHeight); //makes the panel
        //initialize flocks
        Flock one = new Flock(Color.RED, 10, 20, 30, 30 , 25, 50, 2.25);
        flocks.add(one);
        //intiliaze graphics
        Graphics2D g = panel.getGraphics();
        panel.setBackground(Color.WHITE);
        //draw the flocks
//        one.drawFlock(g);
//        two.drawFlock(g);
//        three.drawFlock(g);
        //draw the screen
        panel.copyGraphicsToScreen();

        //keep repeating
        while (true) {
            while (!h.isPause()) {

                //check flags to see if adding
                if(h.isAdding() && h.isDone()) {
                    flocks.add(new Flock(h.getColor(), h.getSize(), h.getBoids(), h.getcRadius(), h.getaRadius(), h.getsRadius(), h.getdRadius(), h.getFlockSpeed()));
                    flocks.get(flocks.size()-1).setScales(h.getvScale(), h.getcScale(), h.getaScale(), h.getsScale());
                    h.setAdding(false);
                    h.setAdding(false);
                    h.setDone(false);
                }

                //check flag to see if removing
                if(h.isSubtracting() && h.isDone()) {
                    flocks.remove(h.getIndex());
                    h.setAdding(false);
                    h.setSubtracting(false);
                    h.setDone(false);
                }

                //check reset flag
                if(h.isReset()){
                    for(Flock f: flocks){
                        f.randomize();
                    }

                    h.setReset(false);
                }

                //check end flag
                if(h.isEnd()){
                    panel.closeWindow();
                    return;
                }

                //check editing flag
                if(h.isEditing() && h.isDone()){
                    flocks.remove(h.getIndex());
                    flocks.add(new Flock(h.getColor(), h.getSize(), h.getBoids(), h.getcRadius(), h.getaRadius(), h.getsRadius(), h.getdRadius(), h.getFlockSpeed()));
                    flocks.get(flocks.size()-1).setScales(h.getvScale(), h.getcScale(), h.getaScale(), h.getsScale());

                    h.setAdding(false);
                    h.setSubtracting(false);
                    h.setDone(false);
                    h.setEditing(false);
                }

                panel.setBackground(Color.WHITE);

                //check to see if there was a mouse click that would move boids around
                if(panel.mouseClickHasOccurred(0)){
                    int x = panel.getMouseClickX(0);
                    int y = panel.getMouseClickY(0);

                    for(Flock f : flocks)
                        f.disperse(x, y);

                }

                //move the flock, draw the flock and then check to see if they group
//                one.moveFlock(g);
//                one.drawFlock(g);
//                one.accumalate(g);
//                //move the flock, draw the flock and then check to see if they group
//                two.moveFlock(g);
//                two.drawFlock(g);
//                two.accumalate(g);
//                //move the flock, draw the flock and then check to see if they group
//                three.moveFlock(g);
//                three.drawFlock(g);
//                three.accumalate(g);

                //move the flock, draw the flock and then check to see if they group
                for(Flock f : flocks) {
                    f.moveFlock(g);
                    f.drawFlock(g);
                    f.accumalate();
                }

                panel.sleep(30);
                panel.copyGraphicsToScreen();

//                //if mouse 2 then exit the game
//                if(panel.mouseClickHasOccurred(2)){
//                    System.out.println("ending");
//                    panel.closeWindow();
//                    return;
//                }

            }
            System.out.flush();
        }
    }
}

