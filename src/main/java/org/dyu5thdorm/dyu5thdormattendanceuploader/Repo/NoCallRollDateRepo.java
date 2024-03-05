package org.dyu5thdorm.dyu5thdormattendanceuploader.Repo;

import org.dyu5thdorm.dyu5thdormattendanceuploader.models.NoCallRollDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface NoCallRollDateRepo extends JpaRepository<NoCallRollDate, Integer> {
    boolean existsByDay(LocalDate date);
}
