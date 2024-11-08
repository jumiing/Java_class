import javax.swing.*;
import java.awt.*;

class FLabel extends JLabel implements Runnable {
    private long delay;
    public FLabel(String text, long delay){
        super(text);
        this.delay = delay;
        setOpaque(true);
        Thread th = new Thread(this);
        th.start();
    }

    @Override
    public void run(){
        int n = 0;
        while (true) {
            if(n==0)
                setBackground(Color.GREEN);
            else
                setBackground(Color.PINK);
            if(n ==0)
                n=1;
            else
                n=0;
            try {
                Thread.sleep(delay);
            }
            catch (InterruptedException e){
                return;
            }
        }
    }
}

public class Flickering  extends JFrame {
    public Flickering() {
        setTitle("깜박이는거 해보기");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        FLabel la1 = new FLabel("반짝",300);
        JLabel la2 = new JLabel("가만히");
        FLabel la3 = new FLabel("바반짝", 600);

        c.add(la1);
        c.add(la2);
        c.add(la3);

        setSize(300,170);
        setVisible(true);
    }

    public static void main(String[] args){
        new Flickering();
    }
}
