package com.phissy.blog.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(generator="UUID")
    @GenericGenerator(name="UUID",strategy="org.hibernate.id.UUIDGenerator")
    private UUID id;

    @NotBlank(message="Email cannot be null!")
    private String email;

    @NotBlank(message="Body cannot be null!")
    private String body;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="post_id", nullable = false)
    @JsonBackReference
    private Post post;

    @CreationTimestamp
    @Column(name="created_at",nullable=false, updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name="updated_at")
    private Timestamp updatedAt;
}
