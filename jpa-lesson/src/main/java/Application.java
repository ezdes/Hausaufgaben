import entity.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
public class Application {
    public static void main(String[] args) throws Exception {
        User user1 = User.builder().login("user1").password("12345678").biography("java dev").build();
        User user2 = User.builder().login("user2").password("12345678").biography("python dev").build();
        User user3 = User.builder().login("user3").password("12345678").biography("c# dev").build();
        User user4 = User.builder().login("admin").password("admin").biography("im admin").build();
        User user5 = User.builder().login("pro_gamer").password("asdfg").biography("Im pro gamer").build();
        saveOrUpdate(user1);
        saveOrUpdate(user2);
        saveOrUpdate(user3);
        saveOrUpdate(user4);
        saveOrUpdate(user5);

        Post post1 = Post.builder().text("работаю в офисе").user(user1).createdDate(LocalDateTime.now()).build();
        Post post2 = Post.builder().text("Вышел в отпуск").user(user1).createdDate(LocalDateTime.of(2014, Month.AUGUST, 22, 22, 1, 22)).build();
        Post post3 = Post.builder().text("Я на ИК").user(user2).createdDate(LocalDateTime.now()).build();
        Post post4 = Post.builder().text("Купил новую машину, зацените").user(user2).createdDate(LocalDateTime.now()).build();
        Post post5 = Post.builder().text("играю в теннис").user(user3).createdDate(LocalDateTime.now()).build();
        Post post6 = Post.builder().text("Приболел").user(user4).createdDate(LocalDateTime.now()).build();
        Post post7 = Post.builder().text("Буду тех работы 15.04.2021 19:00").user(user5).createdDate(LocalDateTime.now()).build();
        Post post8 = Post.builder().text("Тех работы завершены").user(user5).createdDate(LocalDateTime.now()).build();
        Post post9 = Post.builder().text("Взял топ1").user(user5).createdDate(LocalDateTime.now()).build();
        Post post10 = Post.builder().text("Выйграл турнир").user(user5).createdDate(LocalDateTime.now()).build();
        saveOrUpdate(post1);
        saveOrUpdate(post2);
        saveOrUpdate(post3);
        saveOrUpdate(post4);
        saveOrUpdate(post5);
        saveOrUpdate(post6);
        saveOrUpdate(post7);
        saveOrUpdate(post8);
        saveOrUpdate(post9);
        saveOrUpdate(post10);

        Likes likes1 = Likes.builder().post(post1).user(user1).build();
        Likes likes2 = Likes.builder().post(post2).user(user2).build();
        Likes likes3 = Likes.builder().post(post4).user(user3).build();
        Likes likes4 = Likes.builder().post(post3).user(user4).build();
        Likes likes5 = Likes.builder().post(post1).user(user4).build();

        saveOrUpdate(likes1);
        saveOrUpdate(likes2);
        saveOrUpdate(likes3);
        saveOrUpdate(likes4);
        saveOrUpdate(likes5);

        List<User> likedComments = Arrays.asList(user4, user2);
        Comment comment1 = Comment.builder().user(user1).post(post5).likedComments(likedComments).text("Классная машина!").build();
        Comment comment2 = Comment.builder().user(user2).post(post5).likedComments(likedComments).text("Понравился розовый цвет машины :)").build();
        Comment comment3 = Comment.builder().user(user3).post(post2).likedComments(likedComments).text("Хорошо играешь!").build();
        saveOrUpdate(comment1);
        saveOrUpdate(comment2);
        saveOrUpdate(comment3);

        Follower follower1 = Follower.builder().userFollower(user1).userFollowed(user4).build();
        Follower follower2 = Follower.builder().userFollower(user3).userFollowed(user2).build();
        Follower follower3 = Follower.builder().userFollower(user3).userFollowed(user1).build();
//        Follower follower4 = Follower.builder().userFollower(user1).userFollowed(user1).build();
        saveOrUpdate(follower1);
        saveOrUpdate(follower2);
        saveOrUpdate(follower3);
//        saveOrUpdate(follower4); throws exception -> "Нельзя подписываться на самого себя!"

        System.out.println();
        alphabetOrder();
        System.out.println();
        mostPosts();
        System.out.println();
        mostLikesUserId();
        System.out.println();
        mostLikesPostId();
        System.out.println();
        firstUserPosts();
    }
    public static <T> void saveOrUpdate(T entity) throws Exception {
        boolean flag = false;
        if (entity.getClass().equals(Follower.class)) {
            flag = check((Follower) entity);
        }
        if (!flag) {
            Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
            hibernateSession.beginTransaction();
            hibernateSession.saveOrUpdate(entity);
            hibernateSession.getTransaction().commit();
            hibernateSession.close();
            System.out.println("Запись создана/обновлена успешно");
        }
        else throw new Exception("Нельзя подписываться на самого себя.");
    }
    public static boolean check(Follower follower) {
        return follower.getUserFollowed().getId().equals(follower.getUserFollower().getId());
    }

    //Вывести все данные, всех пользователей в алфавитном порядке имени
    public static void alphabetOrder() {
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        @SuppressWarnings("unchecked")
        List<User> query = hibernateSession.createQuery("SELECT u FROM User u ORDER BY u.login").list();
        System.out.println("Alphabetical order by login:");
        System.out.println(query);
        hibernateSession.close();
    }

    //Вывести ID пользователя, с наибольшим количеством постов
    public static void mostPosts() {
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        @SuppressWarnings("unchecked")
        Query<Object[]> query = hibernateSession.createQuery("select u.id, count(p.user) from User u join Post p on u.id = p.user group by u.id order by count(p.user) desc").setMaxResults(1);
        char temp = Arrays.toString(query.list().get(0)).charAt(1);
        System.out.println("The ID of the user who posted most of the posts: " + temp);
        hibernateSession.close();
    }

    //Вывести ID пользователя, который поставил наибольшее количество лайков
    public static void mostLikesUserId() {
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        @SuppressWarnings("unchecked")
        Query<Object[]> query = hibernateSession.createQuery("select u.id, count(l.user) from User u join Likes l on u.id = l.user group by u.id order by count(l.user) desc").setMaxResults(1);
        char temp = Arrays.toString(query.list().get(0)).charAt(1);
        System.out.println("The ID of the user who gave the most likes: " + temp);
        hibernateSession.close();
    }

    //Вывести ID поста, с наибольшим количеством лайков
    public static void mostLikesPostId() {
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        @SuppressWarnings("unchecked")
        Query<Object[]> query = hibernateSession.createQuery("select p.id, count(l.post) from Post p join Likes l on p.id = l.post group by p.id order by count(l.post) desc").setMaxResults(1);
        char temp = Arrays.toString(query.list().get(0)).charAt(1);
        System.out.println("The ID of the post that received the most likes: " + temp);
        hibernateSession.close();
    }

    //Выведите все данные, всех постов первого клиента, в порядке убывания даты поста
    public static void firstUserPosts() {
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        @SuppressWarnings("unchecked")
        List<Post> query = hibernateSession.createQuery("from Post p where p.user = 1 order by p.createdDate desc").list();
        System.out.println("The first user posts by created date descending order:");
        System.out.println(query);
        hibernateSession.close();
    }
}