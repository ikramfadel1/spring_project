package projet.blog.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import projet.blog.enums.PostState;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Date createAt;
    private String title;
    private String content;
    private PostState postState = PostState.New;
    @OneToMany(mappedBy = "post1", fetch = FetchType.LAZY)
    private List<PostDetail> orderDetails = new ArrayList<>();
    @OneToMany(mappedBy = "post1", fetch = FetchType.LAZY)
    private List<Payment> payments = new ArrayList<>();
    @ManyToOne
    private Customer customer;
}
