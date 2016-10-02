package deepa.statictest;

public class StaticVariable {

	public static int s1;
	private static final String s="test Static";
	static final float pi=3.14f;
	
	public StaticVariable() {
		System.out.println("inside constructor");
	}
	 String getstring(){
		System.out.println("static method\t" + s);
		return s;
	}
	public static float getfloat(){
		return pi;
	}
	
	
	static{
		
		System.out.println(" this is static  block");
	s1++;
	System.out.println("s1 \t"+ s1);
	}
	
}
