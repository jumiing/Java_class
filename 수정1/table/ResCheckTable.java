package table;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import etc.Reservation;

public class ResCheckTable extends JPanel implements ActionListener {
	Scanner scan = new Scanner(System.in);
	private static final long serialVersionUID = 1L;

	JTable table = null;
	JTextField edit = new JTextField();
	JLabel text = new JLabel();
	static ConMgr mgr = ReservationTable.getMgr();
	Main main = new Main();
	static Reservation res = new Reservation();
	static JPanel panel = new JPanel();
	//static JPanel pane = new JPanel();

	public ResCheckTable() {
		super(new BorderLayout());
		panel.revalidate();
		panel.repaint();
		ResConListTable();
		JPanel pane = makePane();
		add(pane, BorderLayout.PAGE_END);
	}

	private void ResConListTable() {
		panel.setSize(new Dimension(700, 300));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		add(panel, BorderLayout.CENTER);
	}

	static JPanel mainPane = new JPanel();

	public JPanel makePane() {
		mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.Y_AXIS));

		JPanel top = new JPanel();
		top.setLayout(new GridLayout(1, 1));
		text = new JLabel("");
		text.setHorizontalAlignment(JLabel.CENTER);
		top.add(text);
		panel.add(top);

		JPanel center = new JPanel();
		center.setLayout(new FlowLayout());

		JLabel info = new JLabel("예매자명 입력 ==>");
		center.add(info);
		edit = new JTextField("", 15);
		center.add(edit);
		mainPane.add(center);

		JButton resCheckBtn = new JButton("조회하기");
		resCheckBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("조회하기")) {
					if (search() == 1) {
						edit.setText("");
						text.setText("존재하지 않는 예매자입니다.");
						return;
					} else if (search() == 0) {
						resTa.setText(null);
						resInfo(res);
						edit.setText("");
						text.setText("");
						text.setText("조회에 성공하였습니다.");
						panel.revalidate();
						panel.repaint();
					}
				}
			}
		});

		JButton removeBtn = new JButton("예매취소");
		removeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("예매취소")) {
					(res.concert).matches(res.seat);
					mgr.remove(res.name, (res.seat).charAt(0), Integer.parseInt((res.seat).substring(1)));
					edit.setText("");
					text.setText("");
					infoPane.remove(resTa);
					text.setText("예매가 취소되었습니다.");
					panel.revalidate();
					panel.repaint();
					return;
				}
			}
		});

		center.add(resCheckBtn);
		center.add(removeBtn);
		mainPane.add(center);
		panel.add(mainPane);
		return top;
	}

	public int search() {
		String name = edit.getText();
		res = (mgr.ReservationMap).get(name);
		if (res == null) {
			return 1;
		}
		return 0;
	}

	static JTextArea resTa = new JTextArea();
	static JPanel infoPane = new JPanel();

	public void resInfo(Reservation reser) {
		infoPane.setLayout(new FlowLayout());
		resTa.append(reser.printString());
		infoPane.add(resTa);
		mainPane.add(infoPane);
		panel.add(mainPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("조회하기")) {
			if (search() == 1) {
				edit.setText("");
				text.setText("존재하지 않는 예매자입니다.");
				return;
			} else if (search() == 0) {
				resInfo(res);
				text.setText("");
				text.setText("조회에 성공하였습니다.");
			}
		}
	}
}
