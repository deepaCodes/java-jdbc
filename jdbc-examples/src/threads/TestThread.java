package threads;

public class TestThread {

	public static void main(String[] args) throws InterruptedException  {

		
		System.out.println("main method...");
		
		Thread1 t1 = new Thread1();
		
		Thread2 r = new Thread2();
		Thread t2 = new Thread(r);
		
		t1.start();
		t2.start();
	
		
		//Thread.sleep(1000);
		
		System.out.println("after thread..");
	}

}
