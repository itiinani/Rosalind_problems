import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class OptimalAlignments {

	private static final int GAP_PENALTY = 1;
	private static final int MOD = 134217727;

	public static void main(String[] args) throws IOException {
		//String fileName = args[0];

		ArrayList<String> strings = readFile("C:/EclipseWorkspace/CompBioAss1/src/test.txt");
		//editDistanceAlignment(strings.get(0), strings.get(1));
		String s = strings.get(0);
		String t = strings.get(1);

		int[][] alignmentMatrix = new int[s.length()][t.length()];
		int[][] numberAlignmentsMatrix = new int[s.length()][t.length()];
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < t.length(); j++) {
				alignmentMatrix[i][j] = -1;
				numberAlignmentsMatrix[i][j] = -1;
			}
		}
		int optScore = calculateAlignment(s, t, s.length() - 1, t.length() - 1, alignmentMatrix);
		System.out.println(optScore);
		int totalAlignments = calculateNumberAlignments(s, t, s.length() - 1, t.length() - 1, numberAlignmentsMatrix,
				alignmentMatrix, optScore);
		System.out.println(totalAlignments);
	}

	private static int getScore(char a, char b) {
		if (a == b)
			return 0;
		else
			return 1;
	}
	/**
	 * 
	 * @param alignmentMatrix Holds the dynamic programming state. (i, j) 
	 * @param alignmentMatrix defines the optimal alignment for string
	 * @param alignmentMatrix s[0..i], t[0..j] inclusive.
	 * @param i Index of first string
	 * @param j Index of the second string
	 * @return alignment score of s[0..i], t[0..j]
	 */
	private static int probeAlignmentScore(int[][] alignmentMatrix, int i, int j) {
		if (i < 0 && j < 0) {
			return 0;
		} else if (i < 0 && j >= 0) {
			return (j + 1) * GAP_PENALTY;
		} else if (i >= 0 && j < 0) {
			return (i + 1) * GAP_PENALTY;
		} else {
			return alignmentMatrix[i][j];
		}
	}
	/**
	 * 
	 * @param s First string
	 * @param t Second string
	 * @param i Index of first string
	 * @param j Index of the second string
	 * @param numberAlignmentsMatrix Dynamic programming state 
	 * @param numberAlignmentsMatrix holding number of optimal alignments
	 * @param numberAlignmentsMatrix of s[0..i], t[0..j]
	 * @param alignmentMatrix Holds the dynamic programming state. (i, j) 
	 * @param alignmentMatrix defines the optimal alignment for string
	 * @param alignmentMatrix s[0..i], t[0..j] inclusive.
	 * @param optScore Optimal edit alignment score of s[0..i], t[0..j]
	 * @return
	 */
	public static int calculateNumberAlignments(String s, String t, int i, int j, int[][] numberAlignmentsMatrix,
			int[][] alignmentMatrix, int optScore) {
		if (i < 0 && j < 0) {
			return 1;
		} else if (i < 0 && j >= 0) {
			return 1;
		}

		else if (i >= 0 && j < 0) {
			return 1;
		} else if (numberAlignmentsMatrix[i][j] != -1) {
			return numberAlignmentsMatrix[i][j];
		} else {
			char a = s.charAt(i);
			char b = t.charAt(j);
			int totalAlignments = 0;

			if (optScore - getScore(a, b) == probeAlignmentScore(alignmentMatrix, i - 1, j - 1)) {
				totalAlignments = (totalAlignments + calculateNumberAlignments(s, t, i - 1, j - 1,
						numberAlignmentsMatrix, alignmentMatrix, optScore - getScore(a, b))) % MOD;
			}

			if (optScore - 1 == probeAlignmentScore(alignmentMatrix, i - 1, j)) {
				totalAlignments = (totalAlignments + calculateNumberAlignments(s, t, i - 1, j, numberAlignmentsMatrix,
						alignmentMatrix, optScore - 1)) % MOD;
			}

			if (optScore - 1 == probeAlignmentScore(alignmentMatrix, i, j - 1)) {
				totalAlignments = (totalAlignments + calculateNumberAlignments(s, t, i, j - 1, numberAlignmentsMatrix,
						alignmentMatrix, optScore - 1)) % MOD;
			}
			numberAlignmentsMatrix[i][j] = totalAlignments;
			return totalAlignments;

		}

	}
	/**
	 * 
	 * @param s First string
	 * @param t Second string
	 * @param i Index of the first string
	 * @param j Index of the second string
	 * @param alignmentMatrix Holds the dynamic programming state. (i, j) 
	 * @param alignmentMatrix defines the optimal alignment for string
	 * @param alignmentMatrix s[0..i], t[0..j] inclusive.
	 * @return Optimal edit alignment score of s[0..i], t[0..j]
	 */
	public static int calculateAlignment(String s, String t, int i, int j, int[][] alignmentMatrix) {
		if (i < 0 && j < 0) {
			return 0;
		} else if (i < 0 && j >= 0) {
			return (j + 1) * GAP_PENALTY;
		} else if (i >= 0 && j < 0) {
			return (i + 1) * GAP_PENALTY;
		} else if (alignmentMatrix[i][j] != -1) {
			return alignmentMatrix[i][j];
		} else {
			char a = s.charAt(i);
			char b = t.charAt(j);
			int matchOrMismatch = getScore(a, b) + calculateAlignment(s, t, i - 1, j - 1, alignmentMatrix);
			int insertion = 1 + calculateAlignment(s, t, i - 1, j, alignmentMatrix);
			int deletion = 1 + calculateAlignment(s, t, i, j - 1, alignmentMatrix);
			alignmentMatrix[i][j] = Math.min(matchOrMismatch, Math.min(insertion, deletion));
			return alignmentMatrix[i][j];
		}
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
