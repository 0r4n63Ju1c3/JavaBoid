import javax.swing.*;
import javax.swing.text.EditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private JComboBox Boids; //num boids
    private JButton addFlockButton; //add flock
    private JButton editFlockButton; //edit flock
    private JButton deleteFlockButton; //delete flock
    private JTextField names; //names
    private JSlider Count; //count
    private JSlider Speed; //speed
    private JSlider Size; //size
    private JComboBox Colour; //color
    private JSlider Alignment; //alignment
    private JSlider Evasion; //evasion
    private JSlider Cohesion; //cohesion
    private JSlider Separation; //separation
    private JButton doneButton; //DONE

    public JPanel panelMain;
    private JSlider velocityScale; //velocity weight
    private JSlider alignmentScale; //alignment weight
    private JSlider cohesionScale; //cohesion weight
    private JSlider separationScale; //separation weight
    private JButton pauseButton; //pause
    private JButton restartButton; //restart
    private JTextField countSliderTextField;
    private JTextField speedSliderTextField;
    private JTextField sizeSliderTextField;
    private JTextField alignmentVelocityTextField;
    private JTextField evasionAlignmentTextField;
    private JTextField separationTextField;
    private JTextField cohesionTextField;
    private JButton Close;
    private JButton toggleWrapButton;

    //private String colors[] = {"red, blue, cyan, black, green, purple"};


    /**
     * GUI() main gui component / constructor that takes care of all inputs
     * and interactions with the GUI
     * @param h - the helper clss
     */
    public GUI(Helper h) {

        //listener for the add flock button
        addFlockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //set flag
                h.setAdding(true);

                //enable buttons
                addFlockButton.setEnabled(false);
                deleteFlockButton.setEnabled(false);
                editFlockButton.setEnabled(false);
                Colour.setEnabled(true);
                doneButton.setEnabled(true);
                names.setEnabled(true);
                Size.setEnabled(true);
                Count.setEnabled(true);
                Speed.setEnabled(true);

                //radius enable
                Alignment.setEnabled(true);
                Evasion.setEnabled(true);
                Cohesion.setEnabled(true);
                Separation.setEnabled(true);

                //weight enable
                velocityScale.setEnabled(true);
                alignmentScale.setEnabled(true);
                separationScale.setEnabled(true);
                cohesionScale.setEnabled(true);

                Boids.setEnabled(false);


            }
        });

        //listener for a delete call
        deleteFlockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Boids.getItemCount() > 0 && Boids.getSelectedItem() != null) {
                    //as long as there are flocks to delete
                    //set flags
                    h.setSubtracting(true);
                    h.setIndex(Boids.getSelectedIndex());

                    doneButton.setEnabled(true);

                    addFlockButton.setEnabled(false);
                    deleteFlockButton.setEnabled(false);
                    //disable main buttons
                    editFlockButton.setEnabled(false);
                    names.setEnabled(false);
                    Size.setEnabled(false);
                    Count.setEnabled(false);
                    Speed.setEnabled(false);

                    //radius enable
                    Alignment.setEnabled(false);
                    Evasion.setEnabled(false);
                    Cohesion.setEnabled(false);
                    Separation.setEnabled(false);

                    //weight enable
                    velocityScale.setEnabled(false);
                    alignmentScale.setEnabled(false);
                    separationScale.setEnabled(false);
                    cohesionScale.setEnabled(false);
                }
            }
        });

        //done listener
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //sets all of the flags
                //passes values to the helper class
                h.setFlockSpeed((Speed.getValue() / 100.0) + 1.25);
                h.setSize(Size.getValue());
                h.setColor(Colour.getGraphics().getColor());
                h.setaRadius(Alignment.getValue());
                h.setcRadius(Cohesion.getValue());
                h.setdRadius(Evasion.getValue());
                h.setsRadius(Separation.getValue());
                h.setBoids(Count.getValue());

                //set the proper color
                int color = Colour.getSelectedIndex();
                if(color == 0)
                    h.setColor(Color.RED);
                if(color == 1)
                    h.setColor(Color.CYAN);
                if(color == 2)
                    h.setColor(Color.GREEN);
                if(color == 3)
                    h.setColor(Color.BLACK);
                if(color == 4)
                    h.setColor(Color.BLUE);
                if(color == 5)
                    h.setColor(Color.MAGENTA);


                //set the name
                h.setName(names.getText());

                //calculate appropiate weights
                double total = alignmentScale.getValue() + separationScale.getValue() + cohesionScale.getValue() + velocityScale.getValue();
                h.setvScale(velocityScale.getValue() / total);
                h.setcScale(cohesionScale.getValue() / total);
                h.setaScale(alignmentScale.getValue() / total);
                h.setsScale(separationScale.getValue() / total);

                //remove from combo box
                if(h.isAdding()){
                    Boids.addItem(h.getName());
                }

                if(h.isSubtracting()){
                    Boids.removeItemAt(h.getIndex());
                }

                if(h.isEditing()){
                    Boids.removeItemAt(h.getIndex());
                    Boids.insertItemAt(h.getName(), h.getIndex());
                }


                //disable unused buttons
                Colour.setEnabled(false);
                Boids.setEnabled(true);
                editFlockButton.setEnabled(true);
                addFlockButton.setEnabled(true);
                deleteFlockButton.setEnabled(true);

                //disable unused buttons
                names.setEnabled(false);
                Size.setEnabled(false);
                Count.setEnabled(false);
                Speed.setEnabled(false);

                //disable unused buttons
                Alignment.setEnabled(false);
                Evasion.setEnabled(false);
                Cohesion.setEnabled(false);
                Separation.setEnabled(false);

                doneButton.setEnabled(false);
                //disable unused buttons
                velocityScale.setEnabled(false);
                alignmentScale.setEnabled(false);
                separationScale.setEnabled(false);
                cohesionScale.setEnabled(false);

                //set done flag
                h.setDone(true);
            }

        });

        //puase button
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                h.setPause(!(h.isPause()));
                //set pause flag
            }
        });


        //edit button listener
        //the way edit works is by deleting THE OLD flock
        //it then replaces this flock with a new one with the updated parameters
        //this will create new random values
        editFlockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (Boids.getItemCount() > 0 && Boids.getSelectedItem() != null) {
                    //as long as there are boids to edit
                    //edit flag
                    h.setEditing(true);
                    h.setIndex(Boids.getSelectedIndex());

                    //pass the index
                    Boids.setEnabled(false);
                    addFlockButton.setEnabled(false);
                    deleteFlockButton.setEnabled(false);

                    Colour.setEnabled(true);

                    doneButton.setEnabled(true);

                    //enable buttons
                    names.setEnabled(true);
                    Size.setEnabled(true);
                    Count.setEnabled(true);
                    Speed.setEnabled(true);

                    //enable radius
                    Alignment.setEnabled(true);
                    Evasion.setEnabled(true);
                    Cohesion.setEnabled(true);
                    Separation.setEnabled(true);

                    //enable weights
                    velocityScale.setEnabled(true);
                    alignmentScale.setEnabled(true);
                    separationScale.setEnabled(true);
                    cohesionScale.setEnabled(true);

                }
            }
        });

        //restart listener
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //set restart flag
                h.setReset(true);
                //Boids.removeAllItems();
                //addNames();
                enableInitialControls();
            }
        });

        //close listener
        Close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //exit gracefully
                h.setEnd(true);
                System.exit(0);
            }
        });

        //togle listener
        toggleWrapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FlockSim.wrap = !FlockSim.wrap;
                //toggle GLOBAL boolean set
            }
        });
    }


    public void enableInitialControls(){
        //enable main buttons
        Boids.setEnabled(true);
        editFlockButton.setEnabled(true);
        addFlockButton.setEnabled(true);
        deleteFlockButton.setEnabled(true);
        pauseButton.setEnabled(true);
        Close.setEnabled(true);

        toggleWrapButton.setEnabled(true);

        //add the proper colors
        Colour.addItem("Red");
        Colour.addItem("Cyan");
        Colour.addItem("Green");
        Colour.addItem("Black");
        Colour.addItem("Blue");
        Colour.addItem("Magenta");

        //enable
        Colour.setEnabled(false);

        //disable all other
        names.setEnabled(false);
        Size.setEnabled(false);
        Count.setEnabled(false);
        Speed.setEnabled(false);

        //disable
        Alignment.setEnabled(false);
        Evasion.setEnabled(false);
        Cohesion.setEnabled(false);
        Separation.setEnabled(false);
        doneButton.setEnabled(false);

        //disable
        velocityScale.setEnabled(false);
        alignmentScale.setEnabled(false);
        separationScale.setEnabled(false);
        cohesionScale.setEnabled(false);

    }

    /**
     * this function creates the starting flock and gives
     * the proper name to it
     */
    public void addNames(){
        Boids.addItem("Start");
    }

}
