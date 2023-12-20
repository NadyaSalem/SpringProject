package com.onlinetherapy.SheduleJobs;

import com.onlinetherapy.service.RequestService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ClearOldRequestSchedule {
    private final RequestService requestService;

    public ClearOldRequestSchedule(RequestService requestService) {
        this.requestService = requestService;
    }

    @Scheduled(cron = "0 0 * * * ?")
    public void doInBackground(){
        this.requestService.deleteOldRequests();
    }
}
