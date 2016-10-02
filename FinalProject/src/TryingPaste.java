import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class TryingPaste {

	public static void main(String[] args) throws ScriptException {
		String s = "x+y";
		int x=10, y =5;
		
		ScriptEngineManager mgr = new ScriptEngineManager();
	    ScriptEngine engine = mgr.getEngineByName("JavaScript");
	    String foo = "40+2";
	    System.out.println(engine.eval(s));
		
		ExecutorService exec = Executors.newFixedThreadPool(1);
		Expression e = new ExpressionBuilder(s)
		        .variables("x", "y")
		        .build()
		        .setVariable("x", 10)
		        .setVariable("y", 5);
		Future<Double> future = e.evaluateAsync(exec);
		try {
			double result = future.get();
			System.out.println("result="+result);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	}

}
