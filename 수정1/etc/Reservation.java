package etc;

import java.awt.Component;
import java.util.Scanner;

import table.Concert;
import table.ConMgr;

public class Reservation {

	public Concert concert;
	public Seats getSeat = new Seats();
	public String title;
	public String[] date;
	public String name;
	public String seat;
	public char seatRow;
	public int seatColumn;

	public void read(Scanner scan, ConMgr manager) {
		System.out.print("예약하실 공연 명을 입력해주세요.\n>>");
		title = scan.next();
		concert = (Concert) manager.find(title);
		System.out.print("예약하실 날짜를 입력해주세요.\n>>");
		date = (scan.next()).split("/");
		if (manager.findDate(date, concert)) {
			System.out.print("예약자 이름을 입력해주세요.\n>>");
			name = scan.next();
			while (true) {
				concert.seatPrint();
				System.out.print("예약하실 좌석을 입력해주세요.\n>>");
				seat = scan.next();
				seatRow = seat.charAt(0);
				seatColumn = Integer.parseInt(seat.substring(1));
				concert.addSeat(seatRow, seatColumn);
				if (concert.findSeat(seat))
					concert.seatPrint();
				// concert.seatPrint();
				break;
			}
			return;
		}
		System.out.print("해당 날짜에 공연이 존재하지 않습니다.");
		return;
	}

	public void print() {
		int i;
		System.out.format("%s [", title);
		for (i = 0; i < date.length; i++) {
			System.out.format("%s", date[i]);
			if (i != (date.length - 1))
				System.out.print("/");
		}
		System.out.println("]");
		System.out.format("%s님이 예약하신 좌석 : %s", name, seat);
		System.out.println();
	}

	public String printString() {
		return title + " [ " + date[0] + "/" + date[1] + "/" + date[2] + " ] -> " + name + "님이 예약하신 좌석 : " + seat;
				//+ " ("+ (this.getSeat).grade + "등급, " + (this.getSeat).price + "원)";
	}

	public boolean matches(String kwd) {
		if (name.contentEquals(kwd))
			return true;
		return 
				false;
	}

	public void remSeat(String kwd) {
		if (matches(kwd))
			concert.remSeat(this.seatRow, this.seatColumn);
	}

	public void create(String title, Concert concert, String[] date, String name, String seat) {
		this.title = title;
		this.concert = concert;
		this.date = date;
		this.name = name;
		this.seat = seat;
	}

	public void remSeat2(String kwd,char seatRow2, int seatColumn2) {
		// TODO Auto-generated method stub
		if (matches(kwd))
			concert.remSeat(seatRow2, seatColumn2);
	}
}