package table;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import etc.Manageable;
import etc.Reservation;

public class ReservationTable extends JPanel implements ActionListener, ListSelectionListener {
	// �����ϱ�
	private static final long serialVersionUID = 1L;
	JTable table = null;
	JTextField edits[] = new JTextField[4];
	JLabel text = new JLabel();
	Concert concert;
	public static ConMgr mgr = new ConMgr();
	Main main = new Main();

	public ReservationTable() {
		super(new BorderLayout());
		ConTableInit();
		JPanel pane = makeBottomPane();
		add(pane, BorderLayout.PAGE_END);
	}

	void ConTableInit() {
		mgr.readAllConcert("�Է�.txt", main);
		// readAllUser("users.txt",main);
		final String[] columnNames = { "����", "������", "��¥", "���", "����ũ��", "����" };
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
		mgr.addRow(tableModel);
		table = new JTable(tableModel);
		table.setPreferredScrollableViewportSize(new Dimension(700, 300));
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel rowSM = table.getSelectionModel();
		rowSM.addListSelectionListener(this);
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);
	}

	JPanel makeBottomPane() {
		JPanel pane = new JPanel();
		pane.setLayout(new GridLayout(4, 1));

		JPanel top = new JPanel();
		top.setLayout(new GridLayout(1, 1));
		text = new JLabel("F���� ���� ���� �ý��ۿ� ���� ���� ȯ���մϴ�~");
		text.setHorizontalAlignment(JLabel.CENTER);
		top.add(text);
		pane.add(top);

		JPanel center = new JPanel();
		center.setLayout(new FlowLayout());

		JLabel info = new JLabel("�����̸�, ��¥, �̸�, �¼� ������ �Է� ==>");
		center.add(info);

		for (int i = 0; i < 4; i++) {
			edits[i] = new JTextField("", 10);
			center.add(edits[i]);
		}

		JButton editBtn = new JButton("�����ϱ�");
		editBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel data = (DefaultTableModel) (table.getModel());
				if (e.getActionCommand().equals("�����ϱ�")) {
					if (search(mgr) == 1) {
						for (int i = 0; i < 4; i++) {
							edits[i].setText("");
						}
						text.setText("���� ����.");
					} else if (search(mgr) == 2)
						text.setText("�̹� ���õ� �¼��Դϴ�.");
					else if (search(mgr) == 3)
						text.setText("���� ����");
				}
			}
		});
		center.add(editBtn);
		pane.add(center);

		return pane;
	}

	int selectedIndex = -1;

	@Override
	public void valueChanged(ListSelectionEvent e) {
		ListSelectionModel lsm = (ListSelectionModel) e.getSource();
		if (!lsm.isSelectionEmpty()) {
			selectedIndex = lsm.getMinSelectionIndex();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DefaultTableModel data = (DefaultTableModel) (table.getModel());
		if (e.getActionCommand().equals("�����ϱ�")) {
			if (search(mgr) == 1) {
				for (int i = 0; i < 4; i++) {
					edits[i].setText("");
				}
				text.setText("���� ����.");
			} else if (search(mgr) == 2)
				text.setText("�̹� ���õ� �¼��Դϴ�.");
			else if (search(mgr) == 3)
				text.setText("���� ����");
		}
	}

	public int search(ConMgr manager) {
		String title = edits[0].getText();
		concert = (Concert) manager.find(title);
		String[] date = edits[1].getText().split("/");
		if (manager.findDate(date, concert)) {
			String name = edits[2].getText();
			while (true) {
				String seat = edits[3].getText();
				char seatRow = seat.charAt(0);
				int seatColumn = Integer.parseInt(seat.substring(1));
				if (concert.findSeat(seat)) {
					concert.addSeat(seatRow, seatColumn);
					Reservation res = new Reservation();
					res.create(title, concert, date, name, seat);
					(manager.ReservationList).add(res);
					(manager.ReservationMap).put(res.name, res);
					return 1;
				}
				text.setText("�̹� ���õ� �¼��Դϴ�.");
				return 2;
			}
		}
		return 3;
	}

	public Manageable create(Scanner scan) {
		return null;
	}

	public static ConMgr getMgr() {

		return mgr;
	}
}