package com.phissy.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="posts")
public class Post {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @NotBlank(message="title cannot be null!")
    private String title;

    @NotBlank(message="post body cannot be null!")
    private String body;

    @Column(name="creation_date", nullable = false)
    @CreationTimestamp
    private Timestamp creationDate;

    @Column(name="updated_date")
    @UpdateTimestamp
    private Timestamp updatedDate;
}
