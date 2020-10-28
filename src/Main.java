import javax.swing.*;

/**
 *This is the Main class, the point of this class is create everything
 * Documentation Statement: I received some help from Dr. Hadfield who informed me that I needed to use a boolean
 * to ensure that I was pausing.
 * @author C22Andrew.Lee
 */

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Helper h = new Helper();

        //create the gui that a person can interact with
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("SimpleGUI");
                GUI thisGUI = new GUI(h);
                frame.setContentPane(thisGUI.panelMain);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                thisGUI.addNames();
                thisGUI.enableInitialControls();
                frame.setVisible(true);
            }
        } );


        FlockSim s = new FlockSim(600, 600); //make the sim
        s.active(h); //make it active

    }

}
