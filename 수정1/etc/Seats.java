package etc;

public class Seats {

    char[][] seats = null;
    int column = 0;
    int row = 0;
    public String grade;
    public int price;

    public void selectSeat(char seatRow, int seatColumn) {
        int seatLine = 0;
        if ((int) seatRow <= 90) {
            seatLine = (26 + seatRow) - 65; // ASCII 코드로 받음 'a'=97
            pricing(seatLine);
            seats[seatLine][seatColumn - 1] = 'X';
            return;
        }
        if ((96 < (int)seatRow)&&((int)seatRow < 123)) {
            seatLine = seatRow - 97;
            pricing(seatLine);
            seats[seatLine][seatColumn - 1] = 'X';
            return;
        }
    }

    private void pricing(int seatLine) {
        if (this.row == 10 && this.column == 10) {
            grade = "S";
            price = 90000;
            return;
        } else if (this.row == 30 && this.column == 20) {
            if (seatLine < 10) {
                grade = "R";
                price = 120000;
                return;
            }
            grade = "S";
            price = 90000;
            return;
        } else if (this.row == 50 && this.column == 40) {
            if (seatLine < 10) {
                grade = "VIP";
                price = 150000;
                return;
            } else if (seatLine < 20) {
                grade = "R";
                price = 120000;
                return;
            } else if (seatLine < 30) {
                grade = "S";
                price = 90000;
                return;
            } else if (seatLine < 40) {
                grade = "A";
                price = 70000;
                return;
            }
        }
    }

    public void printSeat() {
        System.out.print(" ");
        for (int n = 0; n < this.row; n++)
            System.out.format(" %2d", n + 1);
        System.out.println("");
        for (int i = 0; i < seats.length; i++) {
            if (i < 26)
                System.out.format("%2c", 'a' + i);
            if (i >= 26)
                System.out.format("%2c", 'A' + (i - 26));
            for (int j = 0; j < seats[i].length; j++) {
                System.out.format("%2s ", seats[i][j]);
            }
            System.out.println();
        }
    }
    
    public String printString() {
    	return "("+this.grade+"등급, "+this.price+"원)";
    }

    public void seatSmall() {
        column = 10;
        row = 10;
        seats = new char[column][row];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                seats[i][j] = 'O';
            }
        }
    }

    public void seatMid() {
        column = 20;
        row = 30;
        seats = new char[column][row];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 30; j++)
                seats[i][j] = 'O';
        }
    }

    public void seatLar() {
        column = 40;
        row = 50;
        seats = new char[column][row];
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 50; j++) {
                seats[i][j] = 'O';
            }
        }
    }

    public void printInfo() {
        System.out.format(" (%s등급 %d원)", grade, price);
        System.out.println();
    }


    public void remove(char seatRow, int seatColumn) {
        int seatLine = 0;
        //System.out.printf("%c %d", seatRow, seatColumn);
        if ((int) seatRow <= 90) {
            seatLine = (26 + seatRow) - 65; // ASCII 코드로 받음 'a'=97
            if(seats[seatLine][seatColumn - 1] == 'X')
                seats[seatLine][seatColumn-1]='O';
            return;
        }
        if ((96 < (int)seatRow)&&((int)seatRow < 123)) {
            seatLine = seatRow - 97;
            if(seats[seatLine][seatColumn - 1] == 'X')
                seats[seatLine][seatColumn-1]='O';
            return;
        }
    }

	/*public void seatremove(char seatRow, int seatColumn) {
		// TODO Auto-generated method stub
		int seatLine=0;
		if((int)seatRow<=90) {
			seatLine=(26+seatRow)-65;
			seats[seatLine][seatColumn-1]='O';
			return;
		}
		if ((96 < (int)seatRow)&&((int)seatRow < 123)) {
			seatLine=seatRow-97;
			seats[seatLine][seatColumn-1]='O';
			return;
		}
			
	}*/
}