package com.example.team1_be.domain.Week;

import com.example.team1_be.domain.Group.Group;
import com.example.team1_be.domain.Group.GroupRepository;
import com.example.team1_be.domain.Schedule.Schedule;
import com.example.team1_be.domain.Schedule.ScheduleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class WeekRepositoryTest {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private WeekRepository weekRepository;

    @DisplayName("한주의 정보를 생성할 수 있다.")
    @Test
    void test1() {
        Group group = Group.builder()
                .id(1)
                .name("맘스터치")
                .phoneNumber("010-1111-1111")
                .address("부산광역시")
                .build();

        Schedule schedule = Schedule.builder()
                .id(1)
                .group(group)
                .build();

        Week.builder()
                .id(1)
                .schedule(schedule)
                .startTime(LocalDateTime.now());
    }

    @DisplayName("한주의 정보를 저장할 수 있다.")
    @Test
    void test2() {
        Group group = Group.builder()
                .id(1)
                .name("맘스터치")
                .phoneNumber("010-1111-1111")
                .address("부산광역시")
                .build();
        groupRepository.save(group);

        Schedule schedule = Schedule.builder()
                .id(1)
                .group(group)
                .build();
        scheduleRepository.save(schedule);

        Week week = Week.builder()
                .id(1)
                .schedule(schedule)
                .startTime(LocalDateTime.now())
                .build();
        weekRepository.save(week);
    }
}