package org.dyu5thdorm.dyu5thdormattendanceuploader.models;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
public class LongLeaveId implements Serializable {
    Student student;
    SchoolTimestamp schoolTimestamp;
}
