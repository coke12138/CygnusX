package CX1Server;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import CX1Server.Server;

public class thread_test {

	public static void main(String[] args) {
		ExecutorService pool=Executors.newSingleThreadExecutor();//�̳߳�
	    SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		for(int i=1;i<=2;i++) {
			pool.submit(new MyThread(i));//���
			System.out.println("ʱ��:"+format1.format(new Date()));
		}
		pool.shutdown();
		try {
			pool.awaitTermination(5, TimeUnit.HOURS);//�ȴ�5Сʱ�����̳߳��е��̶߳�δ������
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Thread t=Thread.currentThread();
		System.out.printf(
				"�߳�����:%s �߳�ID:%d �߳����ȼ�:%s �߳�״̬:%s �ػ��߳�-%b\n",
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
		t.setName("�߳�->"+n);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf(
				"�߳�����:%s �߳�ID:%d �߳����ȼ�:%s �߳�״̬:%s �ػ��߳�-%b\n",
				t.getName(),t.getId(),t.getPriority(),t.getState(),t.isDaemon());
		new Server();
	}
}