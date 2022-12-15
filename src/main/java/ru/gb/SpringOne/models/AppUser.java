package ru.gb.SpringOne.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class AppUser {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String username;
    @Column(columnDefinition = "varchar(255) default ''")
    private String firstname;
    @Column(columnDefinition = "varchar(255) default ''")
    private String lastname;
    @Column
    private String password;
    @Column(columnDefinition = "boolean default true")
    private boolean enabled;
    @Column(columnDefinition = "timestamp default ''")
    private Timestamp lastEntry;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<AppRole> roles;
}
