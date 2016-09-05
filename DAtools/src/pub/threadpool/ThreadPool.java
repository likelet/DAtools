package pub.threadpool;

import java.util.Vector;

/**
 * �̳߳�Ӧ��������һ�����
 * ά��һ���̶߳��У��ö���ʼ������
 * ά��һ���������У� �������п����̣߳������񽻸���߳�
 */
public class ThreadPool {

    protected int maxPoolSize;
    protected int initPoolSize;
    protected Vector<PooledThread> threads = new Vector<PooledThread>();
    protected boolean initialized = false;
    protected boolean hasIdleThread = false;
    protected Vector<ThreadTask> taskQueue = new Vector<ThreadTask>();
    private boolean stopAssignedThread = false;
    private int MAX_TASK_QUEUE;

    public ThreadPool(int poolSize) {
        this.maxPoolSize = poolSize + 5;
        this.initPoolSize = poolSize;
        MAX_TASK_QUEUE = poolSize;
        init();

        Thread assignThread = new Thread(new Runnable() {

            @Override
            public void run() {
                assignTask();
            }
        });
        assignThread.start();
    }

    private void init() {
        initialized = true;
        for (int i = 0; i < initPoolSize; i++) {
            //PooledThread thread = new PooledThread("��" + String.valueOf(i) + "�߳�");
            PooledThread thread = new PooledThread("Thread " + String.valueOf(i));
            thread.start();
            threads.add(thread);
        }
    }

    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
        if (maxPoolSize < getPoolSize()) {
            reSetPoolSize(maxPoolSize);
        }
    }

    /** */
    /**
     * ���赱ǰ�߳���
     * ����ɱ��ĳ�̣߳��̲߳�������ɱ�������ȵ��߳��е����������
     * ���˷��������̴��̳߳����Ƴ���̣߳�����ȴ����������
     * @param size
     */
    public void reSetPoolSize(int size) {
        if (!initialized) {
            initPoolSize = size;
            return;
        } else if (size > getPoolSize()) {
            for (int i = getPoolSize(); i < size && i < maxPoolSize; i++) {
                PooledThread thread = new PooledThread("Thread " + String.valueOf(i));
                thread.start();
                threads.add(thread);
            }
        } else if (size < getPoolSize()) {
            while (getPoolSize() > size) {
                PooledThread th = (PooledThread) threads.remove(0);
                th.kill();
            }
        }
    }

    public int getPoolSize() {
        return threads.size();
    }

    /**
     * ��������
     * @param sleepTime
     */
    protected void setToSleep(int sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * �����߳�Ӧ���ǣ�
     * ������Ŀ������MAX���߳�
     * @return
     */
    public synchronized PooledThread getIdleThread() {
        for (int i = 0; i < threads.size(); i++) {
            PooledThread thread = threads.get(i);
            if (!thread.isRunning()) {
                return thread;
            }
        }
        return null;
    }

    /**
     * ����һ��Task���̳߳أ����̳߳ط���
     * @param task
     * @return
     */
    public boolean addTaskToPool(ThreadTask task) {
        //System.out.println(threads.size());
        if (taskQueue.size() < MAX_TASK_QUEUE) {
            taskQueue.add(task);
            return true;
        }
        return false;
    }

    /**
     * ��������
     */
    private void assignTask() {
        while (!stopAssignedThread) {
            if (taskQueue.size() == 0) {
                setToSleep(400);
                continue;
            }
            PooledThread th = getIdleThread();
            if (th != null) {
                ThreadTask task = getLastTask();
                if (task != null) {
                    th.putTask(task);
                    th.startTask();
                }
            }
        }
    }

    private synchronized ThreadTask getLastTask() {
        int size = taskQueue.size();
        if (size > 0) {
            return taskQueue.remove(size - 1);
        }
        return null;
    }

    /**
     * �����������߳�
     */
    public void clearPool() {
        int size = threads.size();
        stopAssignedThread = true;
        for (int i = 0; i < size; i++) {
            PooledThread t = threads.get(i);
            t.kill();
        }

        while (!isAllThreadDead()) {
            setToSleep(200);
        }
    }

    public boolean isAllThreadDead() {
        int size = threads.size();
        for (int i = 0; i < size; i++) {
            PooledThread t = threads.get(i);
            if (t.isAlive()) {
                return false;
            }
        }
        return true;
    }
}
