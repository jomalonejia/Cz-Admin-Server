package com.cz.core.schedule.quartz;

import com.sun.javafx.tk.Toolkit;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jomalone_jia on 2017/6/27.
 */
public class TaskJobListener implements JobListener {

    private static Logger _log = LoggerFactory.getLogger(TaskJobListener.class);

    @Override
    public String getName() {
        return "taskJobListener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
        _log.info("++++++++++++++++++++++++++++ before");
    }

    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
        _log.info("++++++++++++++++++++++++++++ after");
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {

    }
}
