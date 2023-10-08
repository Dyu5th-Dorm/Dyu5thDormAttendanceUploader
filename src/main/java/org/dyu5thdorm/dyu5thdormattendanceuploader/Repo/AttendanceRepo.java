package org.dyu5thdorm.dyu5thdormattendanceuploader.Repo;

import org.dyu5thdorm.dyu5thdormattendanceuploader.models.AttendanceRecord;
import org.dyu5thdorm.dyu5thdormattendanceuploader.models.AttendanceRecordId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Set;

public interface AttendanceRepo extends JpaRepository<AttendanceRecord, AttendanceRecordId> {
    @Query("select ar from AttendanceRecord ar " +
            "inner join AttendanceStatus ats on ar.attendanceStatus.attendanceStatusId = ats.attendanceStatusId " +
            "left join LeaveRecord lr on ar.attendanceDate = lr.leaveDate and lr.student.studentId = ar.student.studentId and " +
            "lr.bed.bedId = ar.bed.bedId " +
            "left join Cadre c on c.student.studentId = ar.student.studentId " +
            "where ats.attendanceStatusId = 2 and lr.student.studentId is null and c.student.studentId is null " +
            "and ar.attendanceDate = :attendanceDate")
    Set<AttendanceRecord> findByAttendanceDateOut(LocalDate attendanceDate);
}
