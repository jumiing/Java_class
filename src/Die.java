public class Die {
    int value;
    int roll(){
        java.util.Random r = new java.util.Random();
        this.value = r.nextInt(6);
        this.value += 1;
        return this.value;
    }

}
