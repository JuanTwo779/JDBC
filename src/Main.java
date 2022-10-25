import UI.MovieInfo; //package containing UI

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        //generate UI
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createGUI();
            }
        });
    }

    private static void createGUI(){
        MovieInfo ui = new MovieInfo();
        JPanel root = ui.getRootPanel();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(root);
        frame.pack();
        frame.setSize(1500,750);
        frame.setMinimumSize(new Dimension(500,500));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
