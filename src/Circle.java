public class Circle extends Object{
    int r;

    public Circle(int r){
        this.r = r;
        return;
    }

    int getRadius(){
        return this.r;
    }

    @Override
    public String toString() {
        return "radius: "+this.r;
    }

    @Override
    public boolean equals(Object obj) {
        Circle c = (Circle)obj;
        if(this.r==c.r)
            return true;
        else
            return false;
    }
}
