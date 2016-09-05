package pub.threadpool;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadPoolTest extends Thread {

    ThreadPoolTest(final String file, int poolSize) {
        final ThreadPool pool = new ThreadPool(poolSize);
        Thread cmdThread = new Thread() {

            public void run() {
                while (true) {
                    try {
                        SimpleTask task = new SimpleTask(file);
                        if (!pool.addTaskToPool(task)) {
                            Thread.sleep(100);
                        }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ThreadPoolTest.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        cmdThread.start();
    }

    public static void main(String[] args) {
        new ThreadPoolTest("E:/max.txt", 15);
    }
}

/*
public static void main(String[] args) {
System.out.println("quit�˳�");
System.out.println("task A 10��������B��ʱ��Ϊ10��");
System.out.println("size 2 ���õ�ǰ�̳߳ش�СΪ2");
System.out.println("max 3�����̳߳�����߳���Ϊ3");
System.out.println();

final ThreadPool pool = new ThreadPool(3, 2);

Thread cmdThread = new Thread() {

public void run() {

BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

while (true) {
try {
String line = reader.readLine();
String words[] = line.split(" ");
if (words[0].equalsIgnoreCase("quit")) {
System.out.println("I am quit");
pool.clearPool();
break;
} else if (words[0].equalsIgnoreCase("size") && words.length >= 2) {
try {
int size = Integer.parseInt(words[1]);
pool.reSetPoolSize(size);
} catch (Exception e) {
}
} else if (words[0].equalsIgnoreCase("max") && words.length >= 2) {
try {
int max = Integer.parseInt(words[1]);
pool.setMaxPoolSize(max);
} catch (Exception e) {
}
} else if (words[0].equalsIgnoreCase("task") && words.length >= 3) {
try {
int timelen = Integer.parseInt(words[2]);
System.out.println("AA");
SimpleTask task = new SimpleTask(words[1], timelen * 1000);
if (!pool.addTaskToPool(task)) {
System.out.println("��������ʧ�ܣ������̶߳�����");
}
} catch (Exception e) {
}
}

} catch (IOException e) {
e.printStackTrace();
}
}
}
};

cmdThread.start();
}

 * 
 */
class SimpleTask implements ThreadTask {

    private String file;

    public SimpleTask(String file) {
        this.file = file;
    }

    public void work() {
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String line = null;
            if ((line = in.readLine()) != null) {
                int max = Integer.parseInt(line);
                int score = (int) (Math.random() * 10000);
                Thread.sleep(score);
                System.out.print(Thread.currentThread().getName() + ": " + score);
                if (score > max) {
                    FileWriter fw = new FileWriter(file);
                    fw.append(score + "\n");
                    fw.flush();
                    fw.close();
                    System.out.print("\tUpdate");
                }
                System.out.println("");
            }
            in.close();
        } catch (InterruptedException ex) {
            Logger.getLogger(SimpleTask.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
