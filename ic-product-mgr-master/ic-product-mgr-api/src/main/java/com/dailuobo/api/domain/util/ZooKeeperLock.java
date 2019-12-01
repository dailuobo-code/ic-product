package com.dailuobo.api.domain.util;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ZooKeeperLock {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String connectString;
    private int baseSleepTimeMs;
    private int maxRetries;
    private int timeoutMs;

    public void setConnectString(String connectString) {
        this.connectString = connectString;
    }

    public String getConnectString() {
        return connectString;
    }

    public void setBaseSleepTimeMs(int baseSleepTimeMs) {
        this.baseSleepTimeMs = baseSleepTimeMs;
    }

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    public void setTimeoutMs(int timeoutMs) {
        this.timeoutMs = timeoutMs;
    }

    private ThreadLocal<CuratorFramework> client = new ThreadLocal<>();
    private ThreadLocal<InterProcessMutex> lock = new ThreadLocal<>();

    public boolean lock(String path) {
        return lock(path, timeoutMs);
    }

    public boolean lock(String path, long timeoutMs) {
        InterProcessMutex mutex = lock.get();
        if (mutex == null) {
            CuratorFramework curatorFramework = client.get();
            if (curatorFramework == null) {
                curatorFramework = CuratorFrameworkFactory.newClient(connectString, new ExponentialBackoffRetry(baseSleepTimeMs, maxRetries));
                client.set(curatorFramework);
                curatorFramework.start();
            }
            mutex = new InterProcessMutex(curatorFramework, path);
            lock.set(mutex);
        }
        try {
            return mutex.acquire(timeoutMs, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    public void unlock() {
        InterProcessMutex mutex = lock.get();
        if (mutex != null) {
            try {
                mutex.release();
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage(), e);
            }
        }
        lock.set(null);
        CuratorFramework curatorFramework = client.get();
        if (curatorFramework != null) {
            curatorFramework.close();
        }
        client.set(null);
    }
}
