package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity(name = "user_pr")
@Data
@NoArgsConstructor
@Getter
@Setter
public class UserPRs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ColumnDefault(value = "'na'")
    private String deadlift;
    @ColumnDefault(value = "'na'")
    private String squat;
    @ColumnDefault(value = "'na'")
    private String benchPress;

    @OneToOne(mappedBy = "pRs")
    UserProfile userProfile;
}
