import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MaximizingGapSymbols {

	public static void main(String[] args) throws IOException {
		ArrayList<String> strings = readFile("C:/EclipseWorkspace/CompBioAss1/src/rosalind_mgap.txt");
		maxGapSymbol(strings.get(0), strings.get(1));
	}
	public static void maxGapSymbol(String s1,String s2){
		int l1 =s1.length();
		int l2 = s2.length();
		int finalValue = 0;
		int[][] opt = new int[l1 + 1][l2+ 1];
		for (int i = 0; i <= l1; i++) {
			for (int j = 0; j <= l2; j++) {
				int value = Integer.MIN_VALUE;
				if(i==0 || j==0){
					value=0;
				}
				else{
					value = Math.max(opt[i][j - 1],opt[i - 1][j]);
					if (s1.charAt(i-1) == s2.charAt(j-1))
						value = Math.max(value, opt[i - 1][j - 1] + 1);
				}				
				if (value == Integer.MIN_VALUE) value=0;					
		    opt[i][j] = value;
			}
		}
		finalValue = l1+l2-2*opt[l1][l2];
		System.out.println(finalValue);
	}
	public static ArrayList<String> readFile(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String tempString = "";
		ArrayList<String> RNAInput = new ArrayList<String>();
		try {
			String line = br.readLine();
			while (line != null) {
				if (line.charAt(0) == '>' && tempString != "") {
					RNAInput.add(tempString);
					tempString = "";
				} else if (line.charAt(0) == '>' && tempString == "") {

				} else {
					tempString = tempString + line;
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
