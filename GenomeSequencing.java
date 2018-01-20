import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class GenomeSequencing {

	public static void main(String[] args) {
		System.out.println(shortestSuperString());
	}
	public static String shortestSuperString(){
	HashMap<Integer,String> hmap = new HashMap<>();
	 String result = "";
	 try {
		ArrayList<String> strings= readFile("C:/EclipseWorkspace/CompBioAss1/src/test.txt");
		while(strings.size()!=1){
			int maximumOverlap = Integer.MIN_VALUE;
			String mergedString ="";
			int string1Index=0;
			int string2Index=strings.size()-1;
			 for(int j=0;j<strings.size();j++){
				 for(int k=j+1;k<strings.size();k++){
					hmap = findOverlap(strings.get(j),strings.get(k));
					int currOverlap = (int)(hmap.keySet().toArray()[0]);
					if(maximumOverlap < currOverlap){
						maximumOverlap = currOverlap;
						mergedString = hmap.get(currOverlap);
						string1Index = j;
						string2Index = k;
					}
				 }
			 }
			
		        if (maximumOverlap == Integer.MIN_VALUE){
		           mergedString = strings.get(0).concat(strings.get(strings.size()-1));
		           strings.remove(0);
		           strings.remove(strings.size()-1);
		           strings.add(0,mergedString);
		        }
		        else
		        {
		        	strings.remove(string2Index);
		            strings.remove(string1Index);
		            strings.add(string1Index,mergedString);
		        }
		 }
		result = strings.get(0);
		
	} catch (IOException e) {
		e.printStackTrace();
	}
	 return result;
	
	}
	public static HashMap<Integer,String> findOverlap(String string1,String string2){
		HashMap<Integer,String> hmap = new HashMap<>();
		String mergedString = "";
		int overlap = Integer.MIN_VALUE;	   	
	    int min = Math.min(string1.length(), string2.length());
	    for (int i = 1; i <= min; i++)
	    {
	        if (string1.regionMatches(string1.length()-i,string2, 0, i)){
	            if (overlap < i)	            {	                
	                overlap = i;
	                mergedString = string1.concat(string2.substring(i));
	            }
	        }
	    }
	    for (int j = 1; j <= min; j++)
	    {
	        if (string1.regionMatches(0,string2, string2.length()-j, j))
	        {
	            if (overlap < j)
	            {
	                overlap = j;
	                mergedString = string2.concat(string1.substring(j));
	            }
	        }
	    }
	  hmap.put(overlap,mergedString);
	  return hmap;
	}
	public static ArrayList<String> readFile(String fileName) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    String tempString="";
	    ArrayList<String> RNAInput = new ArrayList<String>();
	    try {
	        String line = br.readLine();
	        while (line != null) {
	        	if(line.charAt(0)=='>' && tempString!=""){
	        		RNAInput.add(tempString);
	        		tempString = "";
	        	}
	        	else if(line.charAt(0)=='>' && tempString==""){
	        		
	        	}
	        	else{
	        		tempString = tempString+line;
	        	}
	        	line = br.readLine();
	        }
	        RNAInput.add(tempString);
	    } finally {
	        br.close();
	    }
	    return RNAInput;
	}

}
