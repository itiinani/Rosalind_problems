import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class OverlapsGraph {

	public static void main(String[] args) {
		System.out.println(findEdges());
	}
	public static String findEdges(){
		String finalResult = "";
		ArrayList<String> result = new ArrayList<>();
		ArrayList<String> tags = new ArrayList<>();
		ArrayList<String> DNAStrings = new ArrayList<>();
		String fileName = "C:/EclipseWorkspace/CompBioAss1/src/test.txt";
		try {
			readFile(fileName,tags,DNAStrings);
			result = createAdjacencyList(tags, DNAStrings);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(int i=0;i<result.size();i++){
			finalResult = finalResult+result.get(i)+"\n";
		}
		return finalResult;
	}
	public static ArrayList<String> createAdjacencyList(ArrayList<String> tags,ArrayList<String> DNAStrings){
		ArrayList<String> result = new ArrayList<>();
		for(int i=0;i<DNAStrings.size();i++){
			for(int j=i+1;j<DNAStrings.size();j++){	
				String string1 = DNAStrings.get(i);
				String string2 = DNAStrings.get(j);
				if (string1.regionMatches(string1.length()-3,string2, 0, 3)){
				  result.add(tags.get(i)+ " "+tags.get(j));         
			    }
				if (string1.regionMatches(0,string2,string2.length()-3, 3)){
					  result.add(tags.get(j)+ " "+tags.get(i));         
			    }  
				    
			}
		}
		return result;
	}
	public static void readFile(String fileName,ArrayList<String> tags,ArrayList<String> DNAStrings) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    String tempString="";	   
	    try {
	        String line = br.readLine();
	        while (line != null) {
	        	if(line.charAt(0)=='>' && tempString!=""){
	        		DNAStrings.add(tempString);
	        		tags.add(line.substring(1));
	        		tempString = "";
	        	}
	        	else if(line.charAt(0)=='>' && tempString==""){
	        		tags.add(line.substring(1));
	        	}
	        	else{
	        		tempString = tempString+line;
	        	}
	        	line = br.readLine();
	        }
	       DNAStrings.add(tempString);
	    } finally {
	        br.close();
	    }
	  
	}

}
