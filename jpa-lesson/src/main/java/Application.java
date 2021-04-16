import entity.*;
import org.hibernate.Session;
import util.HibernateUtil;
import java.time.LocalDateTime;
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

        List<User> likedUser1 = Arrays.asList(user1, user4);
        List<User> likedUser2 = Arrays.asList(user5, user3);
        Post post1 = Post.builder().text("работаю в офисе").user(user1).createdDate(LocalDateTime.now()).likedPosts(likedUser1).build();
        Post post2 = Post.builder().text("Вышел в отпуск").user(user1).createdDate(LocalDateTime.now()).likedPosts(likedUser2).build();
        Post post3 = Post.builder().text("Я на ИК").user(user3).createdDate(LocalDateTime.now()).likedPosts(likedUser2).build();
        Post post4 = Post.builder().text("Купил новую машину, зацените").user(user3).createdDate(LocalDateTime.now()).likedPosts(likedUser1).build();
        Post post5 = Post.builder().text("играю в теннис").user(user2).createdDate(LocalDateTime.now()).likedPosts(likedUser2).build();
        Post post6 = Post.builder().text("Приболел").user(user2).createdDate(LocalDateTime.now()).likedPosts(likedUser1).build();
        Post post7 = Post.builder().text("Буду тех работы 15.04.2021 19:00").user(user4).createdDate(LocalDateTime.now()).likedPosts(likedUser2).build();
        Post post8 = Post.builder().text("Тех работы завершены").user(user4).createdDate(LocalDateTime.now()).likedPosts(likedUser1).build();
        Post post9 = Post.builder().text("Взял топ1").user(user5).createdDate(LocalDateTime.now()).likedPosts(likedUser2).build();
        Post post10 = Post.builder().text("Выйграл турнир").user(user5).createdDate(LocalDateTime.now()).likedPosts(likedUser1).build();
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
}