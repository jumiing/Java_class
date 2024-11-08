import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MouseEventEx extends JFrame {
    public MouseEventEx(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Mouse Event");
        this.setLayout(new FlowLayout());

        JTextField tX = new JTextField(10);
        JTextField tY = new JTextField(10);

        this.add(tX);
        this.add(tY);

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e){
                int x = e.getX();
                int y = e.getY();
                tX.setText(String.valueOf(x));
                tY.setText(String.valueOf(y));
            }
        });
        setSize(500,200);
        setVisible(true);
    }
    public static void main(String[] args){
        new MouseEventEx();
    }

}
