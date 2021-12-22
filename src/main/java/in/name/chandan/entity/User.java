package in.name.chandan.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_user")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    //------------OneToManyMapping-------------//
    @OneToMany(targetEntity = Comment.class, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "user_id")
    private List<Comment> comments;

}
