package table;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.table.DefaultTableModel;

import etc.Factory;
import etc.Manageable;
import etc.Reservation;
import etc.Seats;

public class ConMgr {
	public ArrayList<Manageable> ConcertList = new ArrayList<>();
	public ArrayList<Reservation> ReservationList = new ArrayList<>();
	public ArrayList<Users> UserList = new ArrayList<>();
	HashMap<String, Reservation> ReservationMap = new HashMap<>();
	public Scanner scan = new Scanner(System.in);
	Seats getSeats;

	void reservation() {
		Reservation res = null;
		String order = null;
		printAllConcert();

		while (true) {
			System.out.println("예약을 도와드릴까요? (y or n)");
			order = scan.next();
			if (order.equals("n"))
				break;
			res = new Reservation();
			res.read(scan, this);
			ReservationList.add(res);
			ReservationMap.put(res.name, res);
			System.out.println();
		}
	}

	public void findReservation() {
		String name;
		System.out.print("예약자 이름을 적어주세요 : ");
		name = scan.next();
		Reservation res = ReservationMap.get(name);
		if (res == null) {
			System.out.println("존재하지 않는 예약자입니다. ");
			System.out.println();
			return;
		}
		res.print();
	}

	public void readAllConcert(String filename, Factory fac) {
		Scanner filein = openFile(filename);
		Manageable c = null;
		while (filein.hasNext()) {
			c = fac.create(filein);
			c.read(filein);
			ConcertList.add(c);
		}
		filein.close();
	}
	
	public void readAllUser(String filename, Factory fac) {
		Scanner filein = openFile(filename);
        Users u = null;
        while (filein.hasNext()) {
            u = new Users();
            u.read(filein,this);
            UserList.add(u);
        }
        filein.close();
		
	}

	Scanner openFile(String filename) {
		Scanner filein = null;
		try {
			filein = new Scanner(new File(filename));
		} catch (Exception e) {
			System.out.printf("파일 오픈 실패: %s\n", filename);
			System.exit(0);
		}
		return filein;
	}

	void printAllConcert() {
		int i = 1;
		for (Manageable c : ConcertList) {
			System.out.printf("[%2d] ", i);
			i++;
			c.print();
		}
		System.out.println();
	}

	public void searchConcert() {
		String kwd = null;
		while (true) {
			System.out.print("검색 키워드 입력 (종료는 end) >> ");
			kwd = scan.next();
			if (kwd.equals("end"))
				break;
			for (Manageable b : ConcertList)
				if (b.matches(kwd))
					b.print();
		}
	}

	public void cancelRes() {
		String kwd = null;
		while (true) {
			System.out.print("예매자명 입력(종료는 end) >> ");
			kwd = scan.next();
			if (kwd.equals("end"))
				break;
			for (Reservation r : ReservationList) {
				if (r.matches(kwd)) {
					ReservationList.remove(r);
					ReservationMap.remove(r.name);
					r.remSeat(kwd);
					System.out.println("취소가 완료되었습니다.");
					System.out.println();
					return;
				}
			}
			System.out.print("예약자가 존재하지 않습니다.\n");
		}
	}

	public Manageable find(String kwd) {
		for (Manageable m : ConcertList)
			if (m.matches(kwd))
				return m;
		return null;
	}

	public boolean findDate(String[] date, Concert concert) {
		if (concert.matchesDate(date))
			return true;
		return false;
	}

	public void addRow(DefaultTableModel tableModel) {
		for (Manageable c : ConcertList)
			tableModel.addRow(c.getTexts());
	}

	public void remove(String name,char seatRow, int seatColumn) {
		// TODO Auto-generated method stub
		for (Reservation r : ReservationList) {
			if (r.matches(name)) {
				ReservationList.remove(r);
				ReservationMap.remove(r.name);
				//System.out.printf("%c %d", r.seatRow, r.seatColumn);
				r.remSeat2(name,seatRow,seatColumn);
				System.out.println("취소가 완료되었습니다.");
				System.out.println();
				return;
			}
		}
	}
}