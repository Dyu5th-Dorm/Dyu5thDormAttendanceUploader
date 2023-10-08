package org.dyu5thdorm.dyu5thdormattendanceuploader.schedule;

import org.dyu5thdorm.dyu5thdormattendanceuploader.AttendanceWebApi;
import org.dyu5thdorm.dyu5thdormattendanceuploader.models.AttendanceRecord;
import org.dyu5thdorm.dyu5thdormattendanceuploader.service.AttendanceService;
import org.dyu5thdorm.dyu5thdormattendanceuploader.service.NoCallRollDateService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class AutoAttendanceInsert {
    final
    AttendanceWebApi api;
    final
    AttendanceService attendanceService;
    final
    NoCallRollDateService noCallRollDateService;

    public AutoAttendanceInsert(AttendanceWebApi api, AttendanceService attendanceService, NoCallRollDateService noCallRollDateService) {
        this.api = api;
        this.attendanceService = attendanceService;
        this.noCallRollDateService = noCallRollDateService;
    }

    @Scheduled(cron = "0 30 0 * * ?")
    public void run() {
        LocalDate dayBefore = LocalDate.now().minusDays(1);
        if (noCallRollDateService.exists(dayBefore)) return;
        Set<AttendanceRecord> attendanceOutRecord = attendanceService.findOutByDate(dayBefore);
        List<String> outStudentIdList = attendanceOutRecord.stream().map(
                e -> e.getStudent().getStudentId()
        ).toList();
        try {
            api.login();
            Integer statusCode = api.insert(outStudentIdList, dayBefore);
            if (statusCode == 200) {
                System.out.println("insert successful!");
            }
        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
