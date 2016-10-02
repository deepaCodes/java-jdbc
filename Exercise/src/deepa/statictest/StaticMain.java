package deepa.statictest;

public class StaticMain {

	public static void main(String[] args) {

		StaticVariable sv= new StaticVariable();
		String s=sv.getstring();
		
		System.out.println("in main \t" +StaticVariable.getfloat());
		System.out.println("in main \t"+ s);
	}

}
