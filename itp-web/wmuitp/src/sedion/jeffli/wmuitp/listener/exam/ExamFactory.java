package sedion.jeffli.wmuitp.listener.exam;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ExamFactory {
	private Lock lock = new ReentrantLock();
	private BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(100);//设置缓冲区大小
	private Condition conditon = lock.newCondition();
	public Lock getLock() {
		return lock;
	}
	public BlockingQueue<String> getBlockingQueue() {
		return blockingQueue;
	}
	public Condition getConditon() {
		return conditon;
	}
	
}
