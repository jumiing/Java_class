import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IndepClassListener extends JFrame {
    public  IndepClassListener(){
        setTitle("action이벤트리스너예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        JButton bt = new JButton("짠");
        bt .addActionListener(new MyActionListener());
        c.add(bt);

        setSize(350,150);
        setVisible(true);
    }
    public static void main(String[] args){
        new IndepClassListener();
    }
}

class MyActionListener implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton)e.getSource();
        if(b.getText().equals("짠"))
            b.setText("Boom");
        else
            b.setText("짠");
    }
}