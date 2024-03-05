package org.dyu5thdorm.dyu5thdormattendanceuploader.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "attendance_record")
@IdClass(AttendanceRecordId.class)
@Getter
@Setter
@ToString
@Component
public class AttendanceRecord {
    @Id
    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    private Student student;

    @Id
    @Column(name = "attendance_date")
    private LocalDate attendanceDate;

    @Column(name = "attendance_time")
    private LocalTime attendanceTime;

    @ManyToOne
    @JoinColumn(name = "bed_id", referencedColumnName = "bed_id")
    private Bed bed;

    @ManyToOne
    @JoinColumn(name = "attendance_status_id", referencedColumnName = "attendance_status_id")
    private AttendanceStatus attendanceStatus;

    @ManyToOne
    @JoinColumn(name = "cadre_id", referencedColumnName = "cadre_id")
    private Cadre cadre;
}