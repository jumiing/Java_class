package table;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class infoDialog extends JDialog{

	static JPanel panel=new JPanel();
	JTextArea infoTa=new JTextArea();
	
	
	public infoDialog(String title) {
		super(new BorderLayout());
		JFrame frame=new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(500, 200));
		
		JPanel mainPane=new JPanel();
		mainPane.setLayout(new FlowLayout());
		infoTa.append("µÆ´Ï?");
		mainPane.add(infoTa);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//new infoDialog();
	}
}
