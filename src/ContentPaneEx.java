import javax.swing.*;
import java.awt.*;

public class ContentPaneEx extends JFrame {
    public ContentPaneEx(){
        setTitle("CP와 JFrame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container ctPane = getContentPane();
        ctPane.setBackground(Color.PINK);
        ctPane.setLayout(new FlowLayout());

        ctPane.add(new JButton("OK"));
        ctPane.add(new JButton("Cancel"));
        ctPane.add(new JButton("Ignore"));

        setSize(300,150);
        setVisible(true);
    }

    public static void main(String[] args){
        new ContentPaneEx();
    }
}
