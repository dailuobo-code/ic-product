package com.dailuobo.ic.service.inner;

import java.util.concurrent.Executor;

public interface AfterCommitExecutor extends Executor {

    void execute(Runnable runnable);

    void executeSync(Runnable runnable);
}