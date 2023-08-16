package com.phissy.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(generator="UUID")
    @GenericGenerator(name="UUID", strategy="org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String name;

    private String email;

    @JsonIgnore
    private String password;

    private String role;

    @CreationTimestamp
    @Column(name="created_at", nullable=false, updatable=false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name="updated_at")
    private Timestamp updatedAt;
}
