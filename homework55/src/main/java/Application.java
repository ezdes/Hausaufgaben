import Entity.Pizza;
import Util.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        Pizza pizza1 = new Pizza("Pizza1", 700.0, 2.50);
        Pizza pizza2 = new Pizza("Pizza2", 800.0, 1.50);
        Pizza pizza3 = new Pizza("Pizza3", 900.0, 3.50);
        Pizza pizza4 = new Pizza("Pizza4", 70.0, 2.50);
        Pizza pizza5 = new Pizza("Pizza5", 80.0, 1.50);
        Pizza pizza6 = new Pizza("Pizza6", 90.0, 3.50);

        create(pizza1);
        create(pizza2);
        create(pizza3);
        create(pizza4);
        create(pizza5);
        create(pizza6);


//        deleteById(3L);

//        getAll();

//        update(new Pizza(1L, "Pizza100", 10.45, 200.99));

//        getPriceLower350();
    }

    public static Long create(Pizza pizza) {
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        hibernateSession.beginTransaction();
        hibernateSession.save(pizza);
        hibernateSession.getTransaction().commit();
        hibernateSession.close();
        System.out.println("Successfully created " + pizza);
        return pizza.getId();
    }

    public static List<Pizza> getAll() {
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        @SuppressWarnings("unchecked")
        List<Pizza> pizzaList = hibernateSession.createQuery("FROM Pizza").list();
        hibernateSession.close();
        System.out.println("Found " + pizzaList.size() + " pizzas");
        System.out.println(pizzaList);
        return pizzaList;
    }

    public static void deleteById(Long id) {
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        hibernateSession.beginTransaction();
        hibernateSession.createQuery("DELETE FROM Pizza WHERE id=" + id).executeUpdate();
        hibernateSession.getTransaction().commit();
        hibernateSession.close();
        System.out.println("Successfully deleted " + id);
    }

    public static Pizza update(Pizza pizza) {
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        hibernateSession.beginTransaction();
        Query query = hibernateSession.createQuery("UPDATE Pizza set " +
                                                      "name=:name, " +
                                                      "price=:price, " +
                                                      "weight=:weight " +
                                                      "WHERE id=:id");
        query.setParameter("price", pizza.getPrice());
        query.setParameter("name", pizza.getName());
        query.setParameter("weight", pizza.getWeight());
        query.setParameter("id", pizza.getId());
        query.executeUpdate();
        hibernateSession.getTransaction().commit();
        hibernateSession.close();
        System.out.println(pizza);
        return pizza;
    }

    public static List<Pizza> getPriceLower350() {
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        @SuppressWarnings("unchecked")
        List<Pizza> pizzaList = hibernateSession.createQuery("FROM Pizza").list();
        pizzaList.stream().filter(x -> x.getPrice() < 350).forEach(System.out::println);
        hibernateSession.close();
        return pizzaList;
    }
}
