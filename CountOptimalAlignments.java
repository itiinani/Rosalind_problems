import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CountOptimalAlignments {

	public static void main(String[] args) throws IOException {
		ArrayList<String> strings = readFile("C:/EclipseWorkspace/CompBioAss1/src/test.txt");
		editDistanceAlignment(strings.get(0), strings.get(1));

	}

	public static void editDistanceAlignment(String s, String t) {
		int m = s.length()+1;
		int n = t.length()+1;
		int[][] sTable = new int[m][n];
		int[][] count = new int[m][n];
		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				sTable[i][j] = -1;
				count[i][j]=-1;
			}
		}
		for (int i = 0; i < m; ++i) {
			sTable[i][0] = i;
		}
		for (int j = 0; j < n; ++j) {
			sTable[0][j] = j;
		}
		for (int i = 1; i < m; ++i) {
			for (int j = 1; j < n; ++j) {
				int costij = 0;
				if (s.charAt(i - 1) != t.charAt(j - 1)) {
					costij = 1;
				}
				int min = Math.min(sTable[i][j - 1] + 1, sTable[i - 1][j] + 1);
				sTable[i][j] = Math.min(min, sTable[i - 1][j - 1] + costij);
			}
		}
		 int[][] matrix = new int[m-1][n-1];
		    for (int i = 1, j = 0; i <m-1 ; i++, j++) {
		        matrix[j] = Arrays.copyOfRange(sTable[i], 1, n);
		    }
		int opt = sTable[m - 1][n - 1];	
		System.out.println(opt);
		System.out.println(countOptimalAlignments(s, t, s.length()-1,t.length()-1, count, matrix, opt));		
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

	public static int countOptimalAlignments(String s, String t, int i, int j, int[][] count, int[][] sTable,int opt) {
		if ((i < 0 && j < 0) || (i < 0 && j >= 0)||(i >= 0 && j < 0)) {
			return 1;
		} else if (count[i][j] != -1) {
			return count[i][j];
		} else {
			int optimalAlignments = 0;
            int cost =(s.charAt(i)==t.charAt(j))?0:1;
			if (opt-cost== getScore(sTable, i - 1, j - 1)) {
				optimalAlignments = (optimalAlignments+ countOptimalAlignments(s, t, i - 1, j - 1, count, sTable, opt - cost))% 134217727;
			}
			if (opt-1== getScore(sTable, i - 1, j)) {
				optimalAlignments = (optimalAlignments+ countOptimalAlignments(s, t, i - 1, j, count, sTable, opt - 1)) % 134217727;
			}
			if (opt-1== getScore(sTable, i, j - 1)) {
				optimalAlignments = (optimalAlignments+ countOptimalAlignments(s, t, i, j - 1, count, sTable, opt - 1)) % 134217727;
			}
			count[i][j] = optimalAlignments;
			return optimalAlignments;

		}

	}
	private static int getScore(int[][] sTable, int i, int j) {
		if (i < 0 && j < 0) {
			return 0;
		} else if (i < 0 && j >= 0) {
			return j+1;
		} else if (i >= 0 && j < 0) {
			return i+1;
		} else {
			return sTable[i][j];
		}
	}

}
