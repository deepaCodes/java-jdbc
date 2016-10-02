package deepa.constructors;

public class ConstructorFunctions {
	private int var = 0;

	public ConstructorFunctions() {
		this("chaining constructor	test");
		System.out.println("default constructor");
		
	}

	public ConstructorFunctions(String s) {
		System.out.println("parameter constructor" + s);
	}

	public ConstructorFunctions(int i) {
		var = i;
	}

	public int geti() {
		return var;
	}
}
