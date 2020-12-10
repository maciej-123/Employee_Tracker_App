import javax.swing.*;
import java.awt.*;

//needs a reference
public class Main {
    static GraphicsConfiguration gc; // Class field containing config info
    public static void main(String[] args){
        //Create JFrame
        JFrame f = new JFrame(gc);
        f.setSize(1024,600);
        //declare instance of interface to add to panel
        Interface i = new Interface();
        f.add(i.returnMainPanel());

        //test push


        f.setVisible(true);// This next line closes the program when the frame is close
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}