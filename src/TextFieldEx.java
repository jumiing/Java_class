import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextFieldEx extends JFrame {
    private  JTextField tf = new JTextField(20);
    private  JTextArea ta = new JTextArea(7,20);

    public TextFieldEx() {
        setTitle(("텍스트영역만들기 예제"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        c.add(new JLabel("입력 후 엔터키 누르세요"));
        c.add(tf);
        c.add(new JScrollPane(ta));

        tf.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField t = (JTextField) e.getSource();
                ta.append(t.getText()+"\n");
                t.setText("");
            }
        }));
        setSize(300,300);
        setVisible(true);

    }

    public static void main(String[] args){
        new TextFieldEx();
    }
}
