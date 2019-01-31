package com.zzb.javacore.chap14.banckdemo;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter base directory (e.g. /usr/local/): ");
        String directory = in.nextLine();
        System.out.println("Enter the keyword (e.g. volatile): ");
        String keyword = in.nextLine();
        final int FILE_QUEUE_SIZE = 10;
        final int SEARCH_THREADS = 100;
        BlockingQueue<File> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);
        FileEnumerationTask enumerationTask = new FileEnumerationTask(queue, new File(directory));
        Thread fileThread = new Thread(enumerationTask);
        fileThread.setName("Thread file ");
        fileThread.start();
        for (int i = 0; i < SEARCH_THREADS; i++) {
            Thread thread = new Thread(new SearchTask(queue, keyword));
            thread.setName("Thread : " + i);
            thread.start();
        }
    }
}

class FileEnumerationTask implements Runnable {
    public static File DUMMY = new File("");
    private BlockingQueue queue;
    private File staringDirectory;

    public FileEnumerationTask(BlockingQueue<File> queue, File startingDirectory) {
        this.queue = queue;
        this.staringDirectory = startingDirectory;
    }

    public void run() {
        try {
            enumerate(staringDirectory);
            queue.put(DUMMY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void enumerate(File diretory) throws InterruptedException {
        File[] files = diretory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                enumerate(file);
            } else {
                queue.put(file);
            }
        }
    }

}

class SearchTask implements Runnable {
    private BlockingQueue<File> queue;
    private String keyWord;

    public SearchTask(BlockingQueue<File> queue, String keyWord) {
        this.queue = queue;
        this.keyWord = keyWord;
    }

    public void run() {
        try {
            boolean done = false;
            while (!done) {
                File file = queue.take();
                if (file == FileEnumerationTask.DUMMY) {
                    queue.put(file);
                    done = true;
                } else {
                    search(file);
                }
            }
        } catch (IOException e) {
            e.getStackTrace();
        } catch (InterruptedException e1) {
            e1.getStackTrace();
        }

    }

    public void search(File file) throws IOException {
        try (Scanner in = new Scanner(file)) {
            int lineNumber = 0;
            while (in.hasNextLine()) {
                lineNumber++;
                String line = in.nextLine();
                if (line.contains(keyWord)) {
                    System.out.printf("%s:%d:%s %n", file.getPath(), lineNumber, line);
                }
            }
        }

    }
}
