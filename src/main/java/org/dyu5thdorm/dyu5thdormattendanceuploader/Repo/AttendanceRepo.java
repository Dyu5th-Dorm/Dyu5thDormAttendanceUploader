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
            "left join LongLeave ll on ar.student.studentId = ll.student.studentId and ll.schoolTimestamp.schoolYear = :year and " +
            "ll.schoolTimestamp.semester = :semester " +
            "where ats.attendanceStatusId = 2 and lr.student.studentId is null and c.student.studentId is null " +
            "and ar.attendanceDate = :attendanceDate " +
            "and (ll.schoolTimestamp.semester is null or ll.schoolTimestamp.semester is null or " +
            "ll.schoolTimestamp.schoolYear <> :year or ll.schoolTimestamp.semester <> :semester)")
    Set<AttendanceRecord> findByAttendanceDateOut(LocalDate attendanceDate, Integer year, Integer semester);
}
