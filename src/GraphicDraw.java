import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class GraphicDraw extends JFrame {
    private MyPanel panel = new MyPanel();
    public GraphicDraw() {
        setTitle("drawing by mouse 사용예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        setSize(400,350);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GraphicDraw();
    }

    class MyPanel extends JPanel{
        private Vector<Point> vStart = new Vector<Point>();
        private Vector<Point> vEnd = new Vector<Point>();
        public MyPanel(){
            addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e){
                    Point startP = e.getPoint();
                    vStart.add(startP);
                }
                public void mouseReleased(MouseEvent e){
                    Point endP = e.getPoint();
                    vEnd.add(endP);
                    repaint();
                }
            });
        }
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.PINK);
            for(int i = 0; i<vStart.size(); i++){
                Point s = vStart.elementAt(i);
                Point e = vEnd.elementAt(i);
                g.drawLine((int)s.getX(), (int)s.getY(),(int)e.getX(), (int)e.getY());
            }
        }
    }
}
