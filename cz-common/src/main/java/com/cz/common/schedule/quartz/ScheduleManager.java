package com.cz.common.schedule.quartz;

import org.quartz.JobListener;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by jomalone_jia on 2017/6/27.
 */
public class ScheduleManager {

    private static Logger _log = LoggerFactory.getLogger(ScheduleManager.class);

    private Scheduler scheduler;

    private List<JobListener> jobListenerList;

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void setJobListenerList(List<JobListener> jobListenerList) {
        this.jobListenerList = jobListenerList;
    }

    public void init(){
       if(this.jobListenerList != null){
           for (JobListener jobListener : jobListenerList) {
               try {
                   this.scheduler.getListenerManager().addJobListener(jobListener);
                   if(_log.isInfoEnabled()){
                       _log.info("Add JobListener : " + jobListener.getName());
                   }
               } catch (SchedulerException e) {
                   e.printStackTrace();
               }
           }
       }
    }
}
