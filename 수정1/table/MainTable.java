package table;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainTable extends JFrame {

	static JPanel leftPane = new JPanel();
	static JPanel rightPane = new JPanel();
	static JPanel seat = new JPanel();

	public static void main(String args[]) {
		MainTable mainTable = new MainTable();
		mainTable.startGUI();
	}

	public void startGUI() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainTable.createAndShowGUI();
			}
		});
	}

	static JFrame mainFrame = new JFrame("f�� ���Ż���Ʈ");
	static JPanel Panel = new JPanel(new BorderLayout());

	public static void createAndShowGUI() {
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = new Dimension(950, 500);
		mainFrame.setPreferredSize(dim);
		leftPane = setupLeftPane();
		rightPane = setupRightPane();
		Panel.add(leftPane, BorderLayout.WEST);
		Panel.add(rightPane, BorderLayout.CENTER);
		mainFrame.getContentPane().add(Panel);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

	public static JPanel setupRightPane() {
		JPanel rPanel = new JPanel();
		JLabel label = new JLabel("���� ���� �ý���");
		rPanel.setVisible(true);
		rPanel.setSize(700, 300);
		rPanel.add(label);
		return rPanel;
	}

	public static JPanel setupLeftPane() {
		JPanel lPanel = new JPanel();
		lPanel.setLayout(new BoxLayout(lPanel, BoxLayout.Y_AXIS));
		JButton button1 = new JButton("[1]�������");
		JButton button2 = new JButton("[2]�����ϱ�");
		JButton button3 = new JButton("[3]�����˻�");
		JButton button4 = new JButton("[4]����Ȯ��");

		lPanel.add(button1);
		lPanel.add(button2);
		lPanel.add(button3);
		lPanel.add(button4);

		seat.setLayout(new BoxLayout(seat, BoxLayout.Y_AXIS));
		JLabel seatInfo = new JLabel("<�¼� �ȳ�>");
		seat.add(seatInfo);
		JLabel seatSInfo = new JLabel("�ұ��� : a1~j10");
		seat.add(seatSInfo);
		JLabel seatMInfo = new JLabel("�߱��� : a1~D30");
		seat.add(seatMInfo);
		JLabel seatLInfo = new JLabel("����� : a1~X50");
		seat.add(seatLInfo);

		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				leftPane.remove(seat);
				leftPane.repaint();
				JPanel Pane1 = new JPanel();
				ConListTableSelection con = new ConListTableSelection();
				rightPane.removeAll();
				rightPane.add(con);
				Panel.add(rightPane, BorderLayout.CENTER);
				rightPane.revalidate();
				rightPane.repaint();
			}

		});
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				leftPane.add(seat);
				leftPane.repaint();
				JPanel Pane2 = new JPanel();
				ReservationTable res = new ReservationTable();
				rightPane.removeAll();
				rightPane.add(res);
				Panel.add(rightPane, BorderLayout.CENTER);
				rightPane.revalidate();
				rightPane.repaint();
			}
		});
		button3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				leftPane.remove(seat);
				leftPane.repaint();
				JPanel Pane3 = new JPanel();
				ConSearchTable sch = new ConSearchTable();
				rightPane.removeAll();
				rightPane.add(sch);
				Panel.setVisible(true);
				Panel.add(rightPane, BorderLayout.CENTER);
				rightPane.revalidate();
				rightPane.repaint();
			}

		});

		ResCheckTable rct = new ResCheckTable();

		button4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				rightPane.remove(rct);
				rightPane.repaint();
				leftPane.remove(seat);
				leftPane.repaint();
				JPanel Pane4 = new JPanel();
				rightPane.removeAll();
				rightPane.add(rct);
				Panel.setVisible(true);
				Panel.add(rightPane, BorderLayout.CENTER);
				rightPane.revalidate();
				rightPane.repaint();
			}

		});
		return lPanel;
	}

}