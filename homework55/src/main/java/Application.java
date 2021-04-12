import Entity.Pizza;
import Entity.PizzaBox;
import Util.HibernateUtil;
import org.hibernate.Session;
import java.util.List;

public class Application {

    public static void main(String[] args) throws Exception {

        Pizza pizza1 = new Pizza("Pizza1", 700.0, 2.50);
        Pizza pizza2 = new Pizza("Pizza2", 800.0, 1.50);
        Pizza pizza3 = new Pizza("Pizza3", 900.0, 3.50);

        create(pizza1);
        create(pizza2);
        create(pizza3);

        PizzaBox pizzaBox1 = new PizzaBox("Red", "Redmond", pizza1);
        PizzaBox pizzaBox2 = new PizzaBox("Blue", "Bellevue", pizza2);
        PizzaBox pizzaBox3 = new PizzaBox("Black", "Portland", pizza3);
        PizzaBox pizzaBox4 = new PizzaBox("White", "Salt Lake City", null);

        create(pizzaBox1);
        create(pizzaBox2);
        create(pizzaBox3);
        create(pizzaBox4);

//        getAllPizzaBoxes();
//        getAllPizzas();

        //replacePizzaById(2L, new Pizza("Yellow", 6.70, 2.55)); replacing with new pizza
        //replacePizzaById(3L, findPizzaByID(1L)); replacing with existing pizza
        //replacePizzaById(1L, null); empty pizza box

//        delivery(findPizzaBoxByID(1L));
    }

    public static <T> void create(T t) {
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        hibernateSession.beginTransaction();
        hibernateSession.save(t);
        hibernateSession.getTransaction().commit();
        hibernateSession.close();
        System.out.println("Successfully created " + t.getClass());
    }

    public static List<Pizza> getAllPizzas() {
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        @SuppressWarnings("unchecked")
        List<Pizza> pizzaList = hibernateSession.createQuery("FROM Pizza").list();
        hibernateSession.close();
        System.out.println("Found " + pizzaList.size() + " pizzas");
        System.out.println(pizzaList);
        return pizzaList;
    }

    public static List<PizzaBox> getAllPizzaBoxes() {
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        @SuppressWarnings("unchecked")
        List<PizzaBox> pizzaBoxList = hibernateSession.createQuery("FROM PizzaBox").list();
        hibernateSession.close();
        System.out.println("Found " + pizzaBoxList.size() + " pizza boxes");
        System.out.println(pizzaBoxList);
        return pizzaBoxList;
    }

    public static void replacePizzaById(Long id, Pizza newPizza) {
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        hibernateSession.beginTransaction();
        PizzaBox pizzaBox = hibernateSession.get(PizzaBox.class, id);
        if (newPizza != null && newPizza.getId() == null)
            create(newPizza);
        pizzaBox.setPizza(newPizza);
        hibernateSession.getTransaction().commit();
        hibernateSession.close();
        System.out.println("Successfully replaced!");
    }

    public static void delivery(PizzaBox pizzaBox) throws Exception {
        if (pizzaBox.getPizza() == null)
            throw new Exception();
        else
            System.out.println("Delivering pizza " + pizzaBox.getPizza().getName()
                    + " to the address " + pizzaBox.getDestinationAddress() +
                    ", color of the box is " + pizzaBox.getColor() + ", price is " + pizzaBox.getPizza().getPrice());
    }

    public static Pizza findPizzaByID(Long id) {
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        Pizza pizza = hibernateSession.get(Pizza.class, id);
        hibernateSession.close();
        return pizza;
    }

    public static PizzaBox findPizzaBoxByID(Long id) {
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        PizzaBox pizzaBox = hibernateSession.get(PizzaBox.class, id);
        hibernateSession.close();
        return pizzaBox;
    }
}
