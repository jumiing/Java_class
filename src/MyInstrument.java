

public class MyInstrument {
    public static void main(String[] args) {
        Instrument[] myIns = {new Piano(), new Flute()};

        for(Instrument obj : myIns){
            obj.volumeUp();
            obj.volumeDown();
            obj.play();
        }
    }
}
abstract class Instrument {
    public abstract String name();
    public abstract void volumeUp();
    public abstract void volumeDown();
    public abstract void play();

}
class Piano extends Instrument{

    @Override
    public String name() {
        return "Piano: ";
    }

    @Override
    public void volumeUp() {
        System.out.println(name() + "volumeUP() 호출됨");
    }

    @Override
    public void volumeDown() {
        System.out.println(name() + "volumeDown() 호출됨");
    }

    @Override
    public void play() {
        System.out.println(name() + "play() 호출됨");
    }
}

class Flute extends Instrument {

    @Override
    public String name() {
        return "Flute: ";
    }

    @Override
    public void volumeUp() {
        System.out.println(name() + "volumeUP() 호출됨");
    }

    @Override
    public void volumeDown() {
        System.out.println(name() + "volumeDown() 호출됨");
    }

    @Override
    public void play() {
        System.out.println(name() + "play() 호출됨");
    }
}
