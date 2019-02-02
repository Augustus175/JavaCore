package com.zzb.javacore.chap14.threadpooltest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class ThreadPoolTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter base directory (e.g. /usr/local/) : ");
        String directory = in.nextLine();
        System.out.println("Enter keyword (e.g volatile): ");
        String keyword = in.nextLine();

//        ExecutorService pool = Executors.newCachedThreadPool();
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 4, 10L, TimeUnit.MICROSECONDS
                , new ArrayBlockingQueue<>(20));
        Matchcounter counter = new Matchcounter(new File(directory), keyword, pool);
        Future<Integer> result = pool.submit(counter);
        try {
            System.out.println(result.get() + " matching files.");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pool.shutdown();
        int largestPoolsize = ((ThreadPoolExecutor) pool).getLargestPoolSize();
        System.out.println("largest pool size = " + largestPoolsize);

    }
}

class Matchcounter implements Callable<Integer> {
    private File directory;
    private String keyword;
    private ExecutorService pool;
    private int count;

    public Matchcounter(File directory, String keyword, ExecutorService pool) {
        this.directory = directory;
        this.keyword = keyword;
        this.pool = pool;
    }

    public Integer call() {
        count = 0;
        try {
            File[] files = directory.listFiles();
            List<Future<Integer>> results = new ArrayList<>();
            for (File file :
                    files) {
                if (file.isDirectory()) {
                    Matchcounter matchcounter = new Matchcounter(file, keyword, pool);
                    Future<Integer> result;
                    int i = 0;
                    while (true) {
                        try {
                            result = pool.submit(matchcounter);
                            break;
                        } catch (RejectedExecutionException e) {
                            e.printStackTrace();
                            System.out.println("----------" + (i++) + "----------");
                            Thread.sleep(100);
                        }
                    }
                    results.add(result);
                } else {
                    if (search(file)) {
                        count++;
                    }
                }
                for (Future<Integer> result :
                        results) {
                    try {
                        count += result.get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (
                InterruptedException e) {
            e.printStackTrace();
        }
        return count;
    }

    public boolean search(File file) {
        try {
            try (Scanner in = new Scanner(file)) {
                boolean found = false;
                while (!found && in.hasNextLine()) {
                    String line = in.nextLine();
                    if (line.contains(keyword)) {
                        found = true;
                    }
                }
                return found;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
