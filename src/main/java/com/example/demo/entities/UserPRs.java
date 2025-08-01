package com.example.demo.entities;

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

    @ColumnDefault(value = "'n/a'")
    private String deadlift;
    @ColumnDefault(value = "'n/a'")
    private String squat;
    @ColumnDefault(value = "'n/a'")
    private String benchPress;

    @OneToOne(mappedBy = "pRs")
    UserProfile userProfile;


}
