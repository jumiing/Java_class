package table;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import etc.Manageable;

public class ConSearchTable extends JPanel implements ActionListener, ListSelectionListener {
	// [3]공연검색
	private static final long serialVersionUID = 1L;
	JTable table = null;
	JTextField edits = new JTextField();
	JLabel text = new JLabel();
	static Concert concert;
	static ConMgr mgr = new ConMgr();
	Main main = new Main();

	public ConSearchTable() {
		super(new BorderLayout());
		ConTableInit();
		JPanel pane = makeBottomPane();
		add(pane, BorderLayout.PAGE_END);
	}

	void ConTableInit() {
		mgr.readAllConcert("입력.txt", main);
		// readAllUser("users.txt",main);
		final String[] columnNames = { "순서", "공연명", "날짜", "장소", "극장크기", "별점" };
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
		pane.setLayout(new GridLayout(2, 1));

		JPanel top = new JPanel();
		top.setLayout(new GridLayout(1, 1));
		text = new JLabel("궁금하신 공연을 검색해보세요!");
		text.setHorizontalAlignment(JLabel.CENTER);
		top.add(text);
		pane.add(top);

		JPanel center = new JPanel();
		center.setLayout(new FlowLayout());

		JLabel info = new JLabel("공연명, 극장명을 이용하여 검색 ==>");
		center.add(info);
		edits = new JTextField("", 20);
		center.add(edits);

		JButton searchBtn = new JButton("검색하기");
		// searchBtn.setActionCommand("Done");
		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel data = (DefaultTableModel) (table.getModel());
				if (e.getActionCommand().equals("검색하기")) {
					if (search(mgr) == 1) {
						edits.setText("");
						text.setText("검색 성공.");
					} else if (search(mgr) == 0)
						text.setText("검색 실패.");
				}
			}

		});
		center.add(searchBtn);
		pane.add(center);

		return pane;
	}

	int selectedIndex = -1;

	@Override
	public void valueChanged(ListSelectionEvent e) {
		ListSelectionModel lsm = (ListSelectionModel) e.getSource();
		if (!lsm.isSelectionEmpty()) {
			selectedIndex = lsm.getMinSelectionIndex();
			// moveSelectedToEdits();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DefaultTableModel data = (DefaultTableModel) (table.getModel());
		if (e.getActionCommand().equals("검색하기")) {
			if (search(mgr) == 1) {
				edits.setText("");
				text.setText("검색 성공.");
			} else if (search(mgr) == 0)
				text.setText("검색 실패.");
		}
	}

	public int search(ConMgr manager) {
		String kwd = edits.getText();
		concert = (Concert) (manager.find(kwd));
		if (concert != null) {
			if (concert.matches(kwd))
				return 1;
		}
		return 0;
	}

	public Manageable create(Scanner scan) {
		return null;
	}
}