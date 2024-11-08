import javax.swing.*;
import java.awt.*;
class TimerThread implements Runnable {//인터페이스 이용! 달라진부분
    private JLabel timerLabel;

    public TimerThread(JLabel timerLabel){
        this.timerLabel = timerLabel;
    }

    @Override
    public void run() {
        int n = 0;
        while (true){
            timerLabel.setText(Integer.toString(n));
            n++;
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e) {
                return;
            }
        }
    }

}

public class ThreadTimer extends JFrame {
    public ThreadTimer(){
        setTitle("Thread를 상속받은 타이머 스레드");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        JLabel timerL = new JLabel();
        timerL.setFont(new Font("Gothic", Font.ITALIC, 70));
        c.add(timerL);

        //인터페이스 이용! 달라진부분
        TimerThread runTh = new TimerThread(timerL);
        Thread th = new Thread(runTh);

        setSize(300,180);
        setVisible(true);

        th.start();


    }
    public static void main(String[] args){
        new ThreadTimer();
    }
}
