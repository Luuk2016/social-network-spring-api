package dev.lkenselaar.social.network.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;

/**
 * @author Luuk Kenselaar (https://lkenselaar.dev)
 * @since 12-10-2022
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private OffsetDateTime datetime;

    @ManyToOne
    @JsonBackReference
    private User user;

    private String content;
}
