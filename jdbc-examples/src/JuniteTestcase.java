import junit.framework.TestCase;


public class JuniteTestcase extends TestCase {

	private Add addInstance;
	
	@Override
    protected void setUp() throws Exception
    {
		addInstance = new Add();
	}
	
	public void testxyz(){
		
		int result = this.addInstance.add(1,2);
		
		assertEquals(3, result);
	}
	
	public void testsubstract(){
		
		int result = this.addInstance.substract(5,2);
		
		assertEquals(3, result);
	}
	
}
