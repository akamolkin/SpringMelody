package ru.stepup.homework4;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "logins")
@NoArgsConstructor
public class Logins {
    @Id
    @Setter
    @Getter
    Integer id;
    @Setter
    @Getter
    @Column(name = "access_date")
    String accessDate;
    @Setter
    @Getter
    @Column(name = "user_id")
    Integer userId;
    @Setter
    @Getter
    @Column(name = "application")
    String application;
}
