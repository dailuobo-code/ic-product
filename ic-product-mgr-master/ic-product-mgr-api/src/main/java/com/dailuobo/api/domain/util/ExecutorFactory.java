package com.dailuobo.api.domain.util;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.concurrent.*;

public class ExecutorFactory {

    public static final int Ncpu = Math.max(Runtime.getRuntime().availableProcessors(), 4);

    private static Map<TaskType,ExecutorService> executorMap = Maps.newHashMap();

    static {

        try {
            LoggerUtils.getLogger().info("----------------我是一个线程池,启动中------------,Ncpu={}",Ncpu);
            executorMap.put(TaskType.Other,Executors.newFixedThreadPool(1));
            LoggerUtils.getLogger().info("Other threadPool init finish");
            executorMap.put(TaskType.CpuTask,Executors.newFixedThreadPool(Ncpu));
            LoggerUtils.getLogger().info("CpuTask threadPool init finish");
            executorMap.put(TaskType.IOTask,new ThreadPoolExecutor(Ncpu,Ncpu * 2,60*20, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>()));
            LoggerUtils.getLogger().info("IOTask threadPool init finish");
            LoggerUtils.getLogger().info("----------------线程池初始化完成------------,executorMap={}",executorMap.keySet().size());
        } catch (Exception e) {
            LoggerUtils.getLogger().error("init threaPoll error",e);
        }
    }

    private ExecutorFactory() {
    }

    public static ExecutorService getExecutor(TaskType taskType) {
        return executorMap.get(taskType);
    }

    public enum TaskType {
        CpuTask,
        IOTask,
        Other
    }


}