package note.lock;

public class LockTest {

	public static void main(String[] args) {
		
		ThreadTest threadTest = new ThreadTest(100);
		
		Thread thread1=new Thread(threadTest);
		Thread thread2=new Thread(threadTest);
		
		thread1.setName("一号窗口");
		thread2.setName("二号窗口");
		
		thread1.start();
		thread2.start();

	}

}
