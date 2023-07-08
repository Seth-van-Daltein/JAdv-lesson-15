package ua.lviv.lgs.one_to_many;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.HashSet;
import java.util.Set;

public class Application {
    public static void main(String[] args) {

        Configuration config = new Configuration();
        config.configure("/META-INF/hibernate.cfg.xml");

        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties())
                .buildServiceRegistry();
        Session session = config.buildSessionFactory(serviceRegistry).openSession();


        //CREATING
        Post post = new Post();
        post.setTitle("My first POST");


        Comment firstComment = new Comment();
        firstComment.setAuthor_name("Maxim");
        firstComment.setPost(post);

        Comment secondComment = new Comment();
        secondComment.setAuthor_name("Oksana");
        secondComment.setPost(post);

        Set<Comment> comments = new HashSet<>();
        comments.add(firstComment);
        comments.add(secondComment);

        post.setComments(comments);


        //SAVE TO DATABASE
        Transaction transaction = session.beginTransaction();
        session.save(post);
        transaction.commit();

        //READ FROM DATABASE
        Post postDataBase = (Post) session.get(Post.class, 1);
        System.out.println(postDataBase + "---->" + postDataBase.getComments());

        Comment commenDataBase = (Comment) session.get(Comment.class, 2);
        System.out.println(commenDataBase + "---->" + commenDataBase.getPost());


    }
}