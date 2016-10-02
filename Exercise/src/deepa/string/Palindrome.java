package deepa.string;

public class Palindrome {

	public static void main(String[] args) {
	
		//String original="radar";  // word palindrome
		String reverse="";
		String s=" a man,a plan,a canal- panama!";  //Sentence palindrome
		String original= s.replaceAll("\\W","");
		
		//System.out.println(" lettersonly " + lettersonly);
		for(int i=original.length()-1;i>=0;i--){
			
		 reverse= reverse + original.charAt(i);
		}
		
		System.out.println("original  " + original);
		System.out.println("reverse   " + reverse);
		if(original.equals(reverse)){
			System.out.println("palindrome");
		}
		else{
			System.out.println("not palindrome");
		}
		
	}

}
