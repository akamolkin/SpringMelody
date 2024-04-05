package ru.stepup.homework4;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
public class Users {
    @Id
    @Setter
    @Getter
    Integer id;
    @Setter
    @Getter
    @Column(name = "username")
    String userName;
    @Setter
    @Getter
    @Column(name = "fio")
    String fio;
}
