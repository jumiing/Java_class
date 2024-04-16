package table;

import java.util.Scanner;

import javax.swing.JFrame;

import etc.Factory;
import etc.Manageable;
import etc.Seats;

public class Main extends ConMgr implements Factory {

    public static void main(String args[]) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    protected static void createAndShowGUI() {
        JFrame frame = new JFrame("ReservationTableSelectionDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ReservationTable newContentPane = new ReservationTable();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public Manageable create(Scanner scan) {
        return new Concert();
    }
}