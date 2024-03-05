package org.dyu5thdorm.dyu5thdormattendanceuploader.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Entity
@Table(name = "no_call_roll_date")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class NoCallRollDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "day")
    LocalDate day;
}
