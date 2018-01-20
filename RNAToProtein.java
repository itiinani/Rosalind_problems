import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class RNAToProtein {
 public static void main(String args[]) throws IOException{
	 String protein = readFile("C:/EclipseWorkspace/CompBioAss1/src/test.txt");
	 System.out.println(transformRNAToProtein(protein));
 }
 public static String transformRNAToProtein(String protein){
	 String result ="";
	 HashMap<String,String> RNAProteinMap = createHashMap(new HashMap<String,String>());
	 if(protein.length()<=3){
		 return result;
	 }
	 for(int i=0;i<=protein.length()-3;i+=3){
	    String subString = protein.substring(i,i+3);
	    String temp = RNAProteinMap.get(subString);
	    if(temp!="Stop"){
	    	result = result+temp;
	    }	    
	 }
	 return result;
 }
 public static HashMap<String,String> createHashMap(HashMap<String,String> RNAProteinMap){
	 RNAProteinMap.put("UUU", "F");
	 RNAProteinMap.put("UUC","F");
	 RNAProteinMap.put("UUA","L");
	 RNAProteinMap.put("UUG","L");
	 RNAProteinMap.put("UCU","S");
	 RNAProteinMap.put("UCC","S");
	 RNAProteinMap.put("UCA","S");
	 RNAProteinMap.put("UCG","S");
	 RNAProteinMap.put("UAU","Y");
	 RNAProteinMap.put("UAC","Y");
	 RNAProteinMap.put("UAA","Stop");
	 RNAProteinMap.put("UAG","Stop");
	 RNAProteinMap.put("UGU","C");
	 RNAProteinMap.put("UGC","C");
	 RNAProteinMap.put("UGA","Stop");
	 RNAProteinMap.put("UGG","W");
	 RNAProteinMap.put("CUU","L");
	 RNAProteinMap.put("CUC","L");
	 RNAProteinMap.put("CUA","L");
	 RNAProteinMap.put("CUG","L");
	 RNAProteinMap.put("CCU","P");
	 RNAProteinMap.put("CCC","P");
	 RNAProteinMap.put("CCA","P");
	 RNAProteinMap.put("CCG","P");
	 RNAProteinMap.put("CAU","H");
	 RNAProteinMap.put("CAC","H");
	 RNAProteinMap.put("CAA","Q");
	 RNAProteinMap.put("CAG","Q");
	 RNAProteinMap.put("CGU","R");
	 RNAProteinMap.put("CGC","R");
	 RNAProteinMap.put("CGA","R");
	 RNAProteinMap.put("CGG","R");
	 RNAProteinMap.put("AUU","I");
	 RNAProteinMap.put("AUC","I");
	 RNAProteinMap.put("AUA","I");
	 RNAProteinMap.put("AUG","M");
	 RNAProteinMap.put("ACU","T");
	 RNAProteinMap.put("ACC","T");
	 RNAProteinMap.put("ACA","T");
	 RNAProteinMap.put("ACG","T");
	 RNAProteinMap.put("AAU","N");
	 RNAProteinMap.put("AAC","N");
	 RNAProteinMap.put("AAA","K");
	 RNAProteinMap.put("AAG","K");
	 RNAProteinMap.put("AGU","S");
	 RNAProteinMap.put("AGC","S");
	 RNAProteinMap.put("AGA","R");
	 RNAProteinMap.put("AGG","R");
	 RNAProteinMap.put("GUU","V");
	 RNAProteinMap.put("GUC","V");
	 RNAProteinMap.put("GUA","V");
	 RNAProteinMap.put("GUG","V");
	 RNAProteinMap.put("GCU","A");
	 RNAProteinMap.put("GCC","A");
	 RNAProteinMap.put("GCA","A");
	 RNAProteinMap.put("GCG","A");
	 RNAProteinMap.put("GAU","D");
	 RNAProteinMap.put("GAC","D");
	 RNAProteinMap.put("GAA","E");
	 RNAProteinMap.put("GAG","E");
	 RNAProteinMap.put("GGU","G");
	 RNAProteinMap.put("GGC","G");
	 RNAProteinMap.put("GGA","G");
	 RNAProteinMap.put("GGG","G");
	 return RNAProteinMap;
 }
public static String readFile(String fileName) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();
	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        return sb.toString();
	    } finally {
	        br.close();
	    }
	}
}
