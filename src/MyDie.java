
public class MyDie {

    public static void main(String[] args){
        Die d = new Die();
        int postD, curD=-0;
        postD = d.roll();
        System.out.println(postD);
        while(postD != curD){
            postD = curD;
            curD = d.roll();
            System.out.println(curD);
        }

    }
}
