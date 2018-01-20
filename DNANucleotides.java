import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DNANucleotides {

	public static void main(String[] args) throws IOException {
		String str = readFile("C:/EclipseWorkspace/CompBioAss1/src/test.txt");
		int A=0,C=0,G=0,T=0;
		for(int i=0;i<str.length();i++){
			char c = str.charAt(i);
			switch(c){
			case 'A': A++;break;
			case 'C': C++;break;
			case 'G': G++;break;
			case 'T': T++;break;
			}
		}
		String result = A + " "+C+" "+G+" "+T;
		System.out.println(result);

	}
	
	public static String readFile(String fileName) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    String str="";
	    try {
	        String line = br.readLine();	        
	        while(line!=null){
	        	str = str.concat(line);
	        	line = br.readLine();
	        }
	        
	    } finally {
	        br.close();
	    }
	    return str;
	  
	}
}
