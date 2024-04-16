package table;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import etc.Manageable;

public class Users implements Manageable{

	Random random = new Random();
    ArrayList<Concert> viewList = new ArrayList<>();

    String name;
    //String userId;
    int rated=0;

	void readCon(Scanner scan, ConMgr c) {
		
	}

	public void read(Scanner scan, ConMgr c) {
		name = scan.next();
		Concert con = null;
		// userId = scan.next();
		while (true) {
			String code = scan.next();
			if (code.contentEquals("0"))
				break;
			con = (Concert) c.find(code);
			if (con == null)
				continue;
			viewList.add(con);
			
		}setrated(scan, con);

	}

    public void setrated(Scanner scan, Concert con) {
        for (Concert c : viewList) {
            rated = 1+random.nextInt(4);
            c.cntrated(rated);
        }
    }

	@Override
	public void print() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean matches(String kwd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String[] getTexts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void read(Scanner scan) {
		// TODO Auto-generated method stub
		
	}

}
