package rwbykit.flowable.engine.factory;

import rwbykit.flowableTemp.core.thread.ThreadPool;

import java.util.concurrent.Executor;

public class ThreadPoolFactory {

    public final static ThreadPoolFactory factory() {
        return null;
    }

    ThreadPool threadPool;

    public void addRunnable(Runnable runnable) {
        this.threadPool.addRunnable(runnable);
    }

    public Executor getExecutor() {
        return this.threadPool.getExecutor();
    }

}
