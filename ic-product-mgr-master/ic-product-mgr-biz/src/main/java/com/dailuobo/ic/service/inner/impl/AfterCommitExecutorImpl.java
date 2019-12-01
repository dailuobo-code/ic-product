package com.dailuobo.ic.service.inner.impl;

import com.dailuobo.ic.service.inner.AfterCommitExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class AfterCommitExecutorImpl extends TransactionSynchronizationAdapter implements AfterCommitExecutor, DisposableBean{

    private static final Logger LOGGER = LoggerFactory.getLogger(AfterCommitExecutorImpl.class);
    private static final ThreadLocal<List<Runnable>> runnables = new ThreadLocal<>();

    private static ExecutorService EXECUTOR_SERVICE =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    @Override
    public void destroy() {
        EXECUTOR_SERVICE.shutdown();
    }

    @Override
    public void execute(Runnable runnable) {
        LOGGER.info("Submitting new runnable {} to run after commit", runnable);

        if (!TransactionSynchronizationManager.isSynchronizationActive()) {
            LOGGER.info("Transaction synchronization is NOT ACTIVE. Executing right now runnable {}", runnable);
            runnable.run();
            return;
        }

        List<Runnable> threadRunnables = runnables.get();
        if (threadRunnables == null) {
            threadRunnables = new ArrayList<>();
            runnables.set(threadRunnables);
            TransactionSynchronizationManager.registerSynchronization(this);
        }
        threadRunnables.add(runnable);
    }

    @Override
    public void executeSync(Runnable runnable) {
        LOGGER.info("Submitting new runnable {} to run after commit", runnable);

        if (!TransactionSynchronizationManager.isSynchronizationActive()) {
            LOGGER.info("Transaction synchronization is NOT ACTIVE. Synchronize execute runnable {}", runnable);
            EXECUTOR_SERVICE.execute(runnable);
            return;
        }

        List<Runnable> threadRunnables = runnables.get();
        if (threadRunnables == null) {
            threadRunnables = new ArrayList<>();
            runnables.set(threadRunnables);
            TransactionSynchronizationManager.registerSynchronization(this);
        }
        threadRunnables.add(runnable);
    }

    @Override
    public void afterCommit() {
        List<Runnable> threadRunnables = runnables.get();
        LOGGER.info("Transaction successfully committed, executing {} runnables", threadRunnables.size());
        for (int i = 0; i < threadRunnables.size(); i++) {
            Runnable runnable = threadRunnables.get(i);
            LOGGER.info("Executing runnable {}", runnable);
            try {
                EXECUTOR_SERVICE.execute(runnable);
            } catch (RuntimeException e) {
                LOGGER.error("Failed to execute runnable " + runnable, e);
            }
        }
    }

    @Override
    public void afterCompletion(int status) {
        LOGGER.info("Transaction completed with status {}", status == STATUS_COMMITTED ? "COMMITTED" : "ROLLED_BACK");
        runnables.remove();
    }

}
