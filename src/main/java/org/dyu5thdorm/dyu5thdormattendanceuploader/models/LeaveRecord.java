package org.dyu5thdorm.dyu5thdormattendanceuploader.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "leave_record")
@Getter
@Setter
@ToString
@IdClass(LeaveRecordId.class)
@Component
public class LeaveRecord {
    @Id
    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    private Student student;

    @Id
    @Column(name = "leave_date")
    private LocalDate leaveDate;

    @Column(name = "leave_reason", length = 50)
    private String leaveReason;

    @Column(name = "leave_request_time", nullable = false)
    private LocalTime leaveRequestTime;

    @ManyToOne
    @JoinColumn(name = "bed_id", referencedColumnName = "bed_id")
    private Bed bed;
}
