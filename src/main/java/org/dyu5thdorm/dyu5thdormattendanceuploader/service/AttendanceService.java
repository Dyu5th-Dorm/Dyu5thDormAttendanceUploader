package org.dyu5thdorm.dyu5thdormattendanceuploader.service;

import org.dyu5thdorm.dyu5thdormattendanceuploader.Repo.AttendanceRepo;
import org.dyu5thdorm.dyu5thdormattendanceuploader.models.AttendanceRecord;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
public class AttendanceService {
    final
    AttendanceRepo attendanceRepo;

    public AttendanceService(AttendanceRepo attendanceRepo) {
        this.attendanceRepo = attendanceRepo;
    }

    public Set<AttendanceRecord> findOutByDate(LocalDate localDate) {
        return attendanceRepo.findByAttendanceDateOut(localDate);
    }
}
