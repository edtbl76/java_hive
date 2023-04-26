package com.example.schedulingtasks;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component
public class ScheduledTasks {

    private static final String formatString = "HH:mm:ss";

    private static final SimpleDateFormat simpledateFormat = new SimpleDateFormat(formatString);

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.info("The time is now {}", simpledateFormat.format(new Date()));
    }
}
