import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertFalse;
public class ApplicationTest {

    //Если в купюрнике меньше 5 купюр  какого-то номинала выдать сообщение что «мало купюр, надо пополнить».
    @Test
    public void testOne() {
        Integer[] array = countBanknotes();
        boolean flag = array[0] < 5 || array[1] < 5 || array[2] < 5 || array[3] < 5;
        assertFalse("Мало купюр, надо пополнить", flag);
    }

    //Если купюр одного номинала больше чем остальных в 2 и более раза выдать сообщение «перекос».
    @Test
    public void testTwo() {
        Integer[] array = countBanknotes();
        boolean flag = array[0] / (array[1] + array[2] + array[3]) >= 2 ||
                array[1] / (array[0] + array[2] + array[3]) >= 2 ||
                array[2] / (array[0] + array[1] + array[3]) >= 2 ||
                array[3] / (array[0] + array[1] + array[2]) >= 2;

        assertFalse("Перекос", flag);
    }

    public Integer[] countBanknotes() {
        List<Integer> banknotes = Application.getBanknotes();

        int banknotes100 = 0;
        int banknotes200 = 0;
        int banknotes500 = 0;
        int banknotes1000 = 0;

        for (Integer banknote : banknotes) {

            switch (banknote) {
                case 100:
                    banknotes100++;
                    break;

                case 200:
                    banknotes200++;
                    break;

                case 500:
                    banknotes500++;
                    break;

                case 1000:
                    banknotes1000++;
            }
        }
        return new Integer[]{banknotes100, banknotes200, banknotes500, banknotes1000};
    }
}


