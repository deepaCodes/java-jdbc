import java.io.*;

public class FileReadWrite {
  public static void main(String[] args)throws IOException{
	  FileInputStream in = null;
	  FileOutputStream out=null;
	  try{
		  in= new FileInputStream("inputtext.txt");
		  out= new FileOutputStream("outputext.txt");
		 int s;
		while((s=in.read())!=-1){
			System.out.println(s);
		}
	  }
	  finally{
		  if(in!=null)
			  in.close();
		  if(out!=null)
			  out.close();
	  }
  }
}
