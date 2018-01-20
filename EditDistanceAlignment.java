import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class EditDistanceAlignment {

	public static void main(String[] args) throws IOException {
		ArrayList<String> strings= readFile("C:/EclipseWorkspace/CompBioAss1/src/test.txt");
        editDistanceAlignment(strings.get(0),strings.get(1));

	}
	public static void editDistanceAlignment(String s,String t){
        int m = s.length()+1;
        int n = t.length()+1;        
        int[][] sTable = new int[m][n];
        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {
                sTable[i][j] = -1;
            }
        }        
        for(int i = 0; i < m; ++i) {
            sTable[i][0] = i;
        }
        for(int j = 0; j < n; ++j) {
            sTable[0][j] = j;
        }        
        for(int i = 1; i < m; ++i) {
            for(int j = 1; j < n; ++j) {
                int costij= 0;
                if(s.charAt(i-1) != t.charAt(j-1)) {
                  costij = 1;
                }                
                int min = Math.min(sTable[i][j-1]+1,sTable[i-1][j]+1);
                sTable[i][j] = Math.min(min,sTable[i-1][j-1]+costij);                
            }            
        }
        System.out.println(sTable[m-1][n-1]);
        getAlignments(s, t, sTable);
    }
	public static void getAlignments(String s,String t,int[][] sTable) {
		StringBuilder sNew = new StringBuilder(s), tNew = new StringBuilder(t);
		int i = s.length(), j = t.length();
		while (i != 0 && j != 0) {
			if (sTable[i][j] == sTable[i][j - 1]+1) {	
				sNew.insert(i, '-');
				j--;
			}
			else if (sTable[i][j] == sTable[i - 1][j]+1) { 	
				tNew.insert(j, '-');
				i--;
			}
			else {	
				i--;
				j--;
			}
			if (i == 0 && j > 0) {	
				sNew.insert(0, '-');
				i++;
			}	
			else if (i > 0 && j == 0) {
				tNew.insert(0, '-');
				j++;
			}
		}
		System.out.println(sNew.toString());
		System.out.println(tNew.toString());
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
