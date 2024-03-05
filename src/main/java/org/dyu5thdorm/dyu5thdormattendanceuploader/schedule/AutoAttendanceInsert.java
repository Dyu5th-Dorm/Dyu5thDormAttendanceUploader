package org.dyu5thdorm.dyu5thdormattendanceuploader.schedule;

import jakarta.annotation.PostConstruct;
import org.dyu5thdorm.dyu5thdormattendanceuploader.AttendanceWebApi;
import org.dyu5thdorm.dyu5thdormattendanceuploader.models.AttendanceRecord;
import org.dyu5thdorm.dyu5thdormattendanceuploader.service.AttendanceService;
import org.dyu5thdorm.dyu5thdormattendanceuploader.service.NoCallRollDateService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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

    @Value("${s_smye}")
    Integer year;
    @Value("${s_smty}")
    Integer semester;
    @Value("${format.date}")
    String dateFormat;
    SimpleDateFormat formatter;

    @PostConstruct
    void init() {
        formatter = new SimpleDateFormat(dateFormat);
    }

    public AutoAttendanceInsert(AttendanceWebApi api, AttendanceService attendanceService, NoCallRollDateService noCallRollDateService) {
        this.api = api;
        this.attendanceService = attendanceService;
        this.noCallRollDateService = noCallRollDateService;
    }

    @Scheduled(cron = "${upload.date}")
    public void run() {
        LocalDate dayBefore = LocalDate.now().minusDays(1);
        if (noCallRollDateService.exists(dayBefore)) return;
        Set<AttendanceRecord> attendanceOutRecord = attendanceService.findOutByDate(dayBefore, year, semester);
        List<String> outStudentIdList = attendanceOutRecord.stream().map(
                e -> e.getStudent().getStudentId()
        ).toList();
        try {
            api.login();
            Integer statusCode = api.insert(outStudentIdList, dayBefore);
            if (statusCode == 200) {
                System.out.printf("---%s---%n", formatter.format(new Date()));
                if (attendanceOutRecord.isEmpty()) {
                    System.out.println("No one today.");
                } else {
                    for (AttendanceRecord attendanceRecord : attendanceOutRecord) {
                        System.out.printf(
                                "%s %s %s%n",
                                attendanceRecord.getBed().getBedId(),
                                attendanceRecord.getStudent().getStudentId(),
                                attendanceRecord.getStudent().getName()
                        );
                    }
                }
            }
        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
