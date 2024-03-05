package org.dyu5thdorm.dyu5thdormattendanceuploader;

import org.dyu5thdorm.dyu5thdormattendanceuploader.Repo.AttendanceRepo;
import org.dyu5thdorm.dyu5thdormattendanceuploader.models.AttendanceRecord;
import org.dyu5thdorm.dyu5thdormattendanceuploader.schedule.AutoAttendanceInsert;
import org.dyu5thdorm.dyu5thdormattendanceuploader.service.AttendanceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;

@SpringBootTest
class Dyu5thDormAttendanceUploaderApplicationTests {
    @Autowired
    AttendanceRepo attendanceRepo;
    @Autowired
    AttendanceWebApi attendanceWebApi;
    @Autowired
    AttendanceService attendanceService;
    @Autowired
    AutoAttendanceInsert autoAttendanceInsert;

    @Test
    void contextLoads() throws URISyntaxException, IOException, InterruptedException {
        var a = attendanceRepo.findByAttendanceDateOut(
                LocalDate.now().minusDays(1), 112, 2
        ).size();
        System.out.println(a);
    }

}
