package pub.threadpool;

/**
 * �����Ƿ�ֹͬһ�̶߳�������в��������ͻ
 *
 */
public class PooledThread extends Thread {

    protected ThreadTask task;
    //�߳��ܶ��ı�־
    protected boolean running = false;
    //�̴߳�������й����Ƿ�Ҫ��ֹ
    protected boolean stopped = false;
    //�̴߳�������й����Ƿ���ͣ
    protected boolean paused = false;
    //�Ƿ�Ҫɱ����ǰ�߳�
    protected boolean killed = false;

    //���������Ŀ�����������С��MAX_TASK_SIZE��������߳��ǿ��е�
    public PooledThread(String threadId) {
        super(threadId);
    }

    public synchronized void putTask(ThreadTask task) {
        this.task = task;
    }

    public synchronized boolean isRunning() {
        return running;
    }

    public synchronized void stopTasks() {
        stopped = true;
    }

    private synchronized void clearTask() {
        task = null;
    }

    public synchronized void pauseTask() {
        paused = true;
    }

    public synchronized void continueTask() {
        paused = false;
    }

    public synchronized void kill() {
        killed = true;
    }

    public synchronized void startTask() {
        running = true;
    }

    public void run() {
        while (true) {
            if (running) {
                processTask();
                running = false;
            } else {
                setToSleep(200);
            }
            if (killed) {
                return;
            }
        }

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

    private synchronized void processTask() {
        if (stopped) {
            clearTask();
            return;
        }
        if (paused) {
            return;
        }
        if (task != null) {
            task.work();
            clearTask();
        }
    }
}
