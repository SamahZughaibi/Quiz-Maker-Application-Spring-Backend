package com.project1.quizmaker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.*;

import java.util.Objects;

@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@Setter
@Getter
@ToString
public class User {
    @Id
    private String email;
    private String fullName;

    public User(String email, String fullName) {
        this.email = email.toLowerCase();
        this.fullName = fullName;
    }
    public User(String email) {
        this.email = email.toLowerCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) && Objects.equals(fullName, user.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, fullName);
    }
}
