import entity.Country;
import entity.Department;
import entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;
import javax.persistence.criteria.*;
import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        Department department1 = Department.builder().name("Разработка ПО").build();
        Department department2 = Department.builder().name("Мобильная разработка").build();
        Department department3 = Department.builder().name("Отдел тестирования").build();
        create(department1);
        create(department2);
        create(department3);

        Country country1 = Country.builder().name("Кыргызстан").build();
        Country country2 = Country.builder().name("КНР").build();
        Country country3 = Country.builder().name("Польша").build();
        Country country4 = Country.builder().name("Казахстан").build();
        create(country1);
        create(country2);
        create(country3);
        create(country4);

        Employee employee1 = Employee.builder().name("Александр").age(30).department(department1)
                .country(country1).salary(600.0).build();
        Employee employee2 = Employee.builder().name("Егор").age(20).department(department2)
                .country(country2).salary(450.0).build();
        Employee employee3 = Employee.builder().name("Павел").age(32).department(department3)
                .country(country3).salary(800.15).build();
        Employee employee4 = Employee.builder().name("Кылыч").age(22).department(department1)
                .country(country1).salary(400.5).build();
        Employee employee5 = Employee.builder().name("Сергей").salary(1987.0).department(department2).age(25).country(country2).build();
        Employee employee6 = Employee.builder().name("Максим").age(19).country(country2).build();
        Employee employee7 = Employee.builder().name("Айбек").age(19).department(department3).salary(760.90).country(country1).build();
        Employee employee8 = Employee.builder().name("Аяна").age(16).country(country1).build();

        create(employee1);
        create(employee2);
        create(employee3);
        create(employee4);
        create(employee5);
        create(employee6);
        create(employee7);
        create(employee8);

        System.out.println();
        getAllEmployeesWithoutSalaryAndDepartment();
        System.out.println();
        getEmployeesByDescSalaries();
        System.out.println();
        getStanCountries();
        System.out.println();
        getOlderEmployees(country1.getId());
        System.out.println();
        getAvgSalaryByDepartmentName(department3.getName());
        System.out.println();
        getEmployeeBetweenSalary(department1.getId(), 400.0, 1000.0);
    }

    //Вывести Сотрудников без отдела и без зарплаты
    public static void getAllEmployeesWithoutSalaryAndDepartment() {
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = hibernateSession.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> rootEmployee = criteriaQuery.from(Employee.class);
        criteriaQuery.select(rootEmployee)
                .where(criteriaBuilder.and(
                        rootEmployee.get("salary").isNull(),
                        rootEmployee.get("department").isNull())

                );

        Query<Employee> employeeQuery = hibernateSession.createQuery(criteriaQuery);
        List<Employee> employees = employeeQuery.getResultList();
        System.out.println(employees);
    }

    //Вывести Сотрудников по зарплате по убыванию
    public static void getEmployeesByDescSalaries() {
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = hibernateSession.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> rootEmployee = criteriaQuery.from(Employee.class);
        criteriaQuery.select(rootEmployee)
                .where(rootEmployee.get("salary").isNotNull())
                .orderBy(criteriaBuilder.desc(rootEmployee.get("salary")));
        Query<Employee> employeeQuery = hibernateSession.createQuery(criteriaQuery);
        List<Employee> employees = employeeQuery.getResultList();
        System.out.println(employees);
    }

    //Вывести Страны, названия которых заканчиваются на "стан"
    public static void getStanCountries() {
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = hibernateSession.getCriteriaBuilder();
        CriteriaQuery<Country> criteriaQuery = criteriaBuilder.createQuery(Country.class);
        Root<Country> rootCountry = criteriaQuery.from(Country.class);
        criteriaQuery.select(rootCountry)
                .where(criteriaBuilder.like(rootCountry.get("name").as(String.class), "%стан"));

        Query<Country> countryQuery = hibernateSession.createQuery(criteriaQuery);
        List<Country> countries = countryQuery.getResultList();
        System.out.println(countries);
    }

    //Вывести самого старшего(-их) Сотрудника(-ов) по ID Страны
    public static void getOlderEmployees(Long id) {
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = hibernateSession.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> rootEmployee = criteriaQuery.from(Employee.class);
        criteriaQuery.select(rootEmployee)
        .where(criteriaBuilder.equal(rootEmployee.get("country"), id))
        .orderBy(criteriaBuilder.desc(rootEmployee.get("age")));
        Query<Employee> employeeQuery = hibernateSession.createQuery(criteriaQuery);
        List<Employee> employees = employeeQuery.getResultList();
        System.out.println(employees);
    }

    //Вывести Среднюю зарплату по названию Отдела
    public static void getAvgSalaryByDepartmentName(String name) {
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = hibernateSession.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Employee> rootEmployee = criteriaQuery.from(Employee.class);
        criteriaQuery.multiselect(rootEmployee.get("department").get("name"), criteriaBuilder.avg(rootEmployee.<Number>get("salary"))).groupBy(rootEmployee.get("department").get("name"))
                .where(criteriaBuilder.equal(rootEmployee.get("department").get("name"), name));
        Query<Object[]> employeeQuery = hibernateSession.createQuery(criteriaQuery);
        System.out.println(Arrays.toString(employeeQuery.getSingleResult()));
    }

    //Вывести Сотрудников по ID Отдела, заработок которых находится между двух значений
    public static void getEmployeeBetweenSalary(Long departmentId, Double minSalary, Double maxSalary) {
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = hibernateSession.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> rootEmployee = criteriaQuery.from(Employee.class);
        criteriaQuery.select(rootEmployee)
                .where(criteriaBuilder.and(criteriaBuilder.between(rootEmployee.get("salary").as(Double.class), minSalary, maxSalary),
                        criteriaBuilder.equal(rootEmployee.get("department"), departmentId)));
        Query<Employee> employeeQuery = hibernateSession.createQuery(criteriaQuery);
        List<Employee> employees = employeeQuery.getResultList();
        System.out.println(employees);
    }

    public static <T> T create(T entity) {
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        hibernateSession.beginTransaction();
        hibernateSession.save(entity);
        hibernateSession.getTransaction().commit();
        hibernateSession.close();
        System.out.println("Успешно создан " + entity.toString());
        return entity;
    }
}
