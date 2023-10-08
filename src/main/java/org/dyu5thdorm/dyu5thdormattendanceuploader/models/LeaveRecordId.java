package org.dyu5thdorm.dyu5thdormattendanceuploader.models;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDate;

@Component
@Data
public class LeaveRecordId implements Serializable {
    private Student student;
    private LocalDate leaveDate;
}
