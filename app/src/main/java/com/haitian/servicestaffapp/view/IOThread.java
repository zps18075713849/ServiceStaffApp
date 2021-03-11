package com.haitian.servicestaffapp.view;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by qiaoyuang on 2017/4/26.
 * 耗时逻辑处理线程
 */

public class IOThread {

    private static ExecutorService singleThread;

    public static Executor getSingleThread() {
        if (singleThread == null) {
            singleThread = Executors.newSingleThreadExecutor();
        }
        return singleThread;
    }

}
