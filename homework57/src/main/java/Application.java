import Entity.Championship;
import Entity.Country;
import Entity.SportType;
import Entity.Team;
import Util.HibernateUtil;
import org.hibernate.Session;
public class Application {
    public static void main(String[] args) {
        Country country1 = Country
                .builder()
                .name("England")
                .build();

        Country country2 = Country
                .builder()
                .name("USA")
                .build();

        Country country3 = Country
                .builder()
                .name("Nigeria")
                .build();

        create(country1);
        create(country2);
        create(country3);
        SportType sportType1 = SportType
                .builder()
                .name("Football")
                .build();

        SportType sportType2 = SportType
                .builder()
                .name("Basketball")
                .build();

        SportType sportType3 = SportType
                .builder()
                .name("Volleyball")
                .build();

        create(sportType1);
        create(sportType2);
        create(sportType3);

        Team team1 = Team
                .builder()
                .name("Manchester United")
                .logoAddress("idk")
                .officialCite("reddevils.com")
                .sportType(sportType1)
                .build();

        Team team2 = Team
                .builder()
                .name("Los Angeles Lakers")
                .logoAddress("idk")
                .officialCite("lal.com")
                .sportType(sportType2)
                .build();

        Team team3 = Team
                .builder()
                .name("Nigerian Nightmares")
                .logoAddress("idk")
                .officialCite("nn.com")
                .sportType(sportType3)
                .build();

        create(team1);
        create(team2);
        create(team3);

        Championship championship1 = Championship
                .builder()
                .country(country1)
                .name("UCL")
                .sportType(sportType1)
                .build();

        Championship championship2 = Championship
                .builder()
                .country(country2)
                .name("NCAA")
                .sportType(sportType2)
                .build();

        Championship championship3 = Championship
                .builder()
                .country(country3)
                .name("Nigerian championship")
                .sportType(sportType3)
                .build();

        create(championship1);
        create(championship2);
        create(championship3);
    }

    public static <T> void create(T t) {
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        hibernateSession.beginTransaction();
        hibernateSession.saveOrUpdate(t);
        hibernateSession.getTransaction().commit();
        hibernateSession.close();
        System.out.println("Successfully created " + t.getClass());
    }
}
