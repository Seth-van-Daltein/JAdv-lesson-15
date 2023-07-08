package ua.lviv.lgs.one_to_many;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @Column
    private String author_name;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_post_id", nullable = false)
    private Post post;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) && Objects.equals(author_name, comment.author_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author_name);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", author_name='" + author_name + '\'' +
                '}';
    }
}
