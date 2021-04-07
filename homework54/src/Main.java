import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Map<Integer, String> hashMap = new HashMap<>();
        for (int i = 1; i <= 10; i++) {
            hashMap.put(i, "Value" + i);
        }
        hashMap.keySet().stream().filter(x -> x > 5).map(hashMap::get).forEach(x -> System.out.print(x + " "));
        System.out.println();

        Optional<String> values = hashMap.keySet().stream().filter(x -> x % 3 == 0).map(hashMap::get).reduce((value1, combined) -> value1 + "," + combined);
        System.out.print(values.orElse(null) + " ");
        System.out.println();

        Optional<Integer> key = hashMap.keySet().stream().filter(x -> hashMap.get(x).length() > 5).reduce((value2, combined) -> value2 * combined);
        System.out.println(key.orElse(0));
        System.out.println();

        //--------------------------------------------------------------------------------------------------------------

        Integer[] array = new Integer[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = ThreadLocalRandom.current().nextInt(1, 100);
        }

        Stream.of(array).filter(x -> x % 2 == 0).forEach(x -> System.out.print(x + " "));
        System.out.println();

        List<Integer> integerList = Stream.of(array).filter(x -> x % 2 != 0).map(x -> x + 10).collect(Collectors.toList());
        System.out.println(integerList);
    }
}
