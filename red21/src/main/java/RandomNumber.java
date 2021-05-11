import java.util.Random;

public class RandomNumber {
    int x;
    public RandomNumber (int x){
        this.x = x;
    }
    public int getNumber (int x){
        x = new Random().nextInt(6);
        return (x);
    }
}
