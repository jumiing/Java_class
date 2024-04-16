package table;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

import etc.Manageable;
import etc.Reservation;
import etc.Seats;

public class Concert implements Manageable{

    ArrayList<String> seats = new ArrayList<>();

    int id;
    public String title;
    String[] Period;
    String[] place;
    Seats seat = new Seats();
    int rated=5;
    int viewd=1;

    public void read(Scanner scan) {
        id=scan.nextInt();
        title = scan.next();
        Period = (scan.next()).split("/");
        place = (scan.next()).split("-");
        switch (place[1]) {
            case "소극장":
                seat.seatSmall();
                break;
            case "중극장":
                seat.seatMid();
                break;
            case "대극장":
                seat.seatLar();
                break;
            default:
                break;
        }
    }

    public void print() {
        int i, j;
        System.out.printf("%s - ", title);
        for (j = 0; j < place.length; j++) {
            System.out.print(place[j]);
            if (j != (place.length - 1))
                System.out.print(" ");
        }
        System.out.print(" [");
        for (i = 0; i < Period.length; i++) {
            System.out.print(Period[i]);
            if (i != (Period.length - 1))
                System.out.print("/");
        }
        System.out.print("]");
        System.out.println();
    }

    void printSeat() {
        seat.printSeat();
        seat.printInfo();
    }

    public boolean matches(String kwd) {
        if (title.contentEquals(kwd))
            return true;
        if (place[0].equals(kwd))
            return true;
        for(String s:seats){
            s.contentEquals(kwd);
            seats.remove(s);
            return true;
        }
        return false;
    }

    public boolean matchesDate(String[] date) {
        if ((Integer.parseInt(Period[0]) == Integer.parseInt(date[0]))
                && ((Integer.parseInt(Period[1]) <= Integer.parseInt(date[1])))
                && ((Integer.parseInt(Period[2]) <= Integer.parseInt(date[2]))))
            return true;
        return false;
    }

    public boolean findSeat(String seat) {
        while (true) {
            if (seats.isEmpty()) {
                setSeat(seat);
               return true;
            }
            if (!(seats.isEmpty())) {
                for (String s : seats) {
                    if (s.contentEquals(seat)) {
                        System.out.print("이미 선택된 좌석입니다.");
                        System.out.println();
                        return false;
                    }
                    setSeat(seat);
                    break;
                }
                break;
            }
            return true;
        }
		return true;
    }

    void setSeat(String seat) {
        seats.add(seat);
        System.out.println("예약이 완료되었습니다.");
        return;
    }

    public void seatPrint() {
        seat.printSeat();
    }

    public void addSeat(char seatRow, int seatColumn) {
        seat.selectSeat(seatRow, seatColumn);
    }

    public void seatInfo() {
        seat.printInfo();
    }

    public void remSeat(char seatRow, int seatColumn) {
        seat.remove(seatRow, seatColumn);
    }

    public String[] getTexts() {
    	 return new String[] {""+id,title, String.join("/", Period), place[0], place[1],printrated()};
    }

    public void cntrated(int r) {
        viewd++;
        rated+=r;
    }
    public String printrated() {
        float a= avgrated();
        return ""+a+""+"("+viewd+")";
    }
    public float avgrated() {
        return rated/viewd;
    }
}