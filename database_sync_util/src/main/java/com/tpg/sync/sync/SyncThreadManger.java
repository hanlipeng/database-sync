package com.tpg.sync.sync;


import com.tpg.sync.factory.SyncThreadFactory;
import com.tpg.sync.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author reph
 * @date 2018/3/21
 */
public class SyncThreadManger {
    public static final String MODULE_CONFIG_PREFIX = "module";
    public static final String TABLE_CONFIG_PREFIX = "table";
    public static final String DEAL_DATA_CONFIG_PREFIX = "dealData";
    private static final String MAX_NUMBER_OF_THREADS_SUFFIX = ".maxNumberOfThreads";
    private static final String MAX_NUMBER_OF_COLUMN_SUFFIX = ".maxNumberOfColumn";
    private static ThreadPoolExecutor threadPoolExecutor;
    private SyncParentThread parentThread;
    private Logger logger = LoggerFactory.getLogger(SyncThreadManger.class);

    static {
        threadPoolExecutor = new ThreadPoolExecutor(getSumNumberOfThreads(),
                getSumNumberOfThreads(),
                1,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(), new SyncThreadFactory());
        threadPoolExecutor.allowCoreThreadTimeOut(true);
    }

    SyncThreadManger(SyncParentThread parentThread) {
        this.parentThread = parentThread;
    }

    public static int getMaxNumberOfThreadsByType(String type) {
        int i;
        try {
            i = Integer.parseInt(Utils.getThreadConfig().getProperty(type + MAX_NUMBER_OF_THREADS_SUFFIX));
        } catch (NumberFormatException e) {
            throw new RuntimeException("threadConfig中参数填写错误.请填写数字");
        }
        return i;
    }

    public static int getMaxNumberOfColumn() {
        int i;
        try {
            i = Integer.parseInt(Utils.getThreadConfig().getProperty(DEAL_DATA_CONFIG_PREFIX + MAX_NUMBER_OF_COLUMN_SUFFIX));
        } catch (NumberFormatException e) {
            throw new RuntimeException("threadConfig中参数填写错误.请填写数字");
        }
        return i;
    }

    private static int getSumNumberOfThreads() {
        int moduleThread = getMaxNumberOfThreadsByType(MODULE_CONFIG_PREFIX);
        int tableThread = getMaxNumberOfThreadsByType(TABLE_CONFIG_PREFIX);
        int dealDataThread = getMaxNumberOfThreadsByType(DEAL_DATA_CONFIG_PREFIX);
        return moduleThread + tableThread * moduleThread + dealDataThread * tableThread * moduleThread;
    }

    public void startThread() throws Exception {
        logger.info(parentThread.getStartLog());
        parentThread.beforeStartThread();
        ThreadManage threadManage = new ThreadManage(parentThread.getMaxNumberOfThreads());
        SyncSonThread thread;
        while (parentThread.hasNext(threadManage.isError())) {
            thread = parentThread.getThread();
            if (!thread.couldRun()) {
                continue;
            }
            threadManage.setThreadMark();
            thread.setThreadManage(threadManage);
            threadPoolExecutor.execute(thread);
            while (parentThread.needWait(threadManage.isRunning())) {
                Thread.sleep(50);
            }
        }
        while (threadManage.isRunning()) {
            Thread.sleep(100);
        }
        parentThread.afterEndThread(threadManage.isError());
        if (threadManage.isError()) {
            Logger logger = LoggerFactory.getLogger(this.getClass());
            logger.error(parentThread.getErrorLog(), threadManage.getError());
        }
        logger.info(parentThread.getEndLog());
    }
}

class ThreadManage {
    private boolean state = false;
    private ArrayBlockingQueue<ThreadMark> threadMarks;
    private Exception error;

    ThreadManage(int maxNumberOfThreads) {
        threadMarks = new ArrayBlockingQueue<>(maxNumberOfThreads);
    }

    public void setThreadMark() throws InterruptedException {
        threadMarks.put(new ThreadMark());
    }

    public void removeThreadMark() throws InterruptedException {
        threadMarks.take();
    }

    public void changeErrorState() {
        state = true;
    }

    public boolean isError() {
        return state;
    }

    public boolean isRunning() {
        return threadMarks.size() != 0;
    }

    public void setError(Exception error) {
        this.error = error;
    }

    public Exception getError() {
        return error;
    }
}

class ThreadMark {
}
