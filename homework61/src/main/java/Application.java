import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {

    }
    public static List<Integer> getBanknotes() {

        List<Integer> banknotes = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            banknotes.add(100);
            banknotes.add(200);
            banknotes.add(500);
        }
        banknotes.add(1000);
        banknotes.add(1000);
        banknotes.add(1000);

        return banknotes;
    }
}
