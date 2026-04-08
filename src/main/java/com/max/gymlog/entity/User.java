package com.max.gymlog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="users") // створює таблицю
@Getter
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) // автоматично створює id яке не повторюється
    private Long id;

    @Setter
    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true) // налаштовує колонку на не нуль і унікальний
    private String email;

    @Column(nullable = false)
    private String passwordHash ;

    @Enumerated(EnumType.STRING) // записує енум не 0 або 1, а USER aбо ADMIN
    @Column(nullable = false)
    private Role role;

    public User(String username, String email, String passwordHash, Role role) {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    protected User() {

    }// protected - це доступ в нащадків та в папці

}
