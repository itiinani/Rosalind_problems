import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GCContent {

	public static void main(String[] args) {
		try {
			System.out.println(findLogOfProbability());
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	public static String findLogOfProbability() throws IOException{
		String fileName = "C:/EclipseWorkspace/CompBioAss1/src/test.txt";
		ArrayList<String> input = new ArrayList<>();
		readFile(fileName,input);
		String result ="";
		String dnaString = input.get(0);
		double singleValue=0;
		for(int i=1;i<input.size();i++){
		 double GCcontent = Double.parseDouble(input.get(i));
			for(int j=0;j<dnaString.length();j++){
				char c = dnaString.charAt(j);
				if("AT".contains(c+""))					
					singleValue = singleValue + Math.log10((1-GCcontent)/2); 
				else
					singleValue = singleValue + Math.log10((GCcontent)/2); 
			}
			result = result + String.format("%.3f",singleValue)+" ";
			singleValue =0;
		}
		return result;
	}
	public static void readFile(String fileName,ArrayList<String> input) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    try {
	        String line = br.readLine();
	        input.add(line);
	        line = br.readLine();
	        String[] splitStr = line.trim().split("\\s+");
	        for(String s : splitStr){
	        	input.add(s);
	        }	      
	    } finally {
	        br.close();
	    }
	  
	}
}
