package CX1Server;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import CX1Server.Server;

public class thread_test {

	public static void main(String[] args) {
		ExecutorService pool=Executors.newSingleThreadExecutor();//线程池
	    SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		for(int i=1;i<=2;i++) {
			pool.submit(new MyThread(i));//添加
			System.out.println("时间:"+format1.format(new Date()));
		}
		pool.shutdown();
		try {
			pool.awaitTermination(5, TimeUnit.HOURS);//等待5小时除非线程池中的线程都未被调用
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Thread t=Thread.currentThread();
		System.out.printf(
				"线程名称:%s 线程ID:%d 线程优先级:%s 线程状态:%s 守护线程-%b\n",
				t.getName(),t.getId(),t.getPriority(),t.getState(),t.isDaemon());

	}
}
  
class MyThread implements Runnable{
	int n;
	MyThread(int n){
		this.n=n;
	}
	public void run() {
		Thread t=Thread.currentThread();
		t.setName("线程->"+n);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf(
				"线程名称:%s 线程ID:%d 线程优先级:%s 线程状态:%s 守护线程-%b\n",
				t.getName(),t.getId(),t.getPriority(),t.getState(),t.isDaemon());
		new Server();
	}
}