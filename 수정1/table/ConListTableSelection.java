package table;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import etc.Manageable;

public class ConListTableSelection extends JPanel implements ActionListener, ListSelectionListener {
    private static final long serialVersionUID = 1L;
    static JFrame subFrame = new JFrame("공연 정보");
    static JPanel infoPane = new JPanel();
    static JPanel Panel = new JPanel(new BorderLayout());

    JTable table = null;
    JTextField edits[] = new JTextField[4];

    static String[] conInfo = new String[3];
    ConMgr mgr = new ConMgr();
    Main main = new Main();

    public ConListTableSelection() {
        super(new BorderLayout());
        ConTableInit();
        JPanel pane = makeBottomPane();
        add(pane, BorderLayout.PAGE_END);
    }

    public static void createAndShowGUI() {
        subFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Dimension dim = new Dimension(300, 300);
        subFrame.setPreferredSize(dim);
        infoPane = newWindow(conInfo[0],conInfo[1],conInfo[2]);
        Panel.add(infoPane, BorderLayout.WEST);
        subFrame.getContentPane().add(Panel);
        subFrame.pack();
        subFrame.setVisible(true);
    }

    void ConTableInit() {
        mgr.readAllConcert("입력.txt", main);
        final String[] columnNames = {"순서", "공연명", "날짜", "장소", "극장크기", "별점"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        mgr.addRow(tableModel);
        table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row < 0) return;
                if (e.getClickCount() == 1) {
                    JTable t =(JTable)e.getSource();

                    TableModel data = table.getModel();
                    conInfo[0] = (String) data.getValueAt(row, 1);
                    conInfo[1] = (String) data.getValueAt(row, 2);
                    conInfo[2] = (String) data.getValueAt(row, 3);

                    newWindow(conInfo[0], conInfo[1], conInfo[2]);
                    createAndShowGUI();
                }
            }

        });
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
        pane.setLayout(new GridLayout(3, 1));

        JPanel top = new JPanel();
        top.setLayout(new GridLayout(1, 1));
        JLabel text = new JLabel("F조의 공연 예약 시스템에 오신 것을 환영합니다~");
        text.setHorizontalAlignment(JLabel.CENTER);
        top.add(text);
        pane.add(top);
        return pane;
    }
    
    static JPanel p = new JPanel(new GridLayout(4, 2));
    
    public static JPanel newWindow(String conName, String conDate, String conPlace) {
    	p.revalidate();
    	p.repaint();
       // JPanel p = new JPanel(new GridLayout(4, 2));
        p.setSize(400,200);
        p.setVisible(true);
        JLabel nameInfo = new JLabel("공연명");
        JLabel dateInfo = new JLabel("공연날짜");
        JLabel placeInfo = new JLabel("공연장소");

        JTextField name = new JTextField();
        name.setText(conName);
        JTextField date = new JTextField();
        date.setText(conDate);
        JTextField place = new JTextField();
        place.setText(conPlace);

        p.removeAll();

        p.add(nameInfo);
        p.add(name);
        p.add(dateInfo);
        p.add(date);
        p.add(placeInfo);
        p.add(place);

        return p;
    }

    int selectedIndex = -1;

    @Override
    public void valueChanged(ListSelectionEvent e) {
       /* ListSelectionModel lsm = (ListSelectionModel) e.getSource();
        if (!lsm.isSelectionEmpty()) {
            selectedIndex = lsm.getMinSelectionIndex();
            //moveSelectedToEdits();
        }*/
    }

    private class MouseListener extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            int row = table.getSelectedRow();

            TableModel data = table.getModel();
            String conInfo[] = new String[3];
            conInfo[0] = (String) data.getValueAt(row, 1);
            conInfo[1] = (String) data.getValueAt(row, 2);
            conInfo[2] = (String) data.getValueAt(row, 3);

            newWindow(conInfo[0], conInfo[1], conInfo[2]);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public Manageable create(Scanner scan) {
        return null;
    }

}