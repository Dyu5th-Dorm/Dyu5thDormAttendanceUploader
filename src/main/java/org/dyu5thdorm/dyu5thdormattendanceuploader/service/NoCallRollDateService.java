package org.dyu5thdorm.dyu5thdormattendanceuploader.service;

import org.dyu5thdorm.dyu5thdormattendanceuploader.Repo.NoCallRollDateRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class NoCallRollDateService {
    final
    NoCallRollDateRepo noCallRollDateRepo;

    public NoCallRollDateService(NoCallRollDateRepo noCallRollDateRepo) {
        this.noCallRollDateRepo = noCallRollDateRepo;
    }

    public boolean exists(LocalDate localDate) {
        return noCallRollDateRepo.existsByDay(localDate);
    }
}
