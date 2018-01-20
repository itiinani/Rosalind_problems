import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class BetterBWMatching {

	public static void main(String[] args) {
		 File file = new File("C:/EclipseWorkspace/CompBioAss1/src/rosalind_ba9m.txt");
		 PrintStream out = null;
			try {
				out = new PrintStream(new FileOutputStream("out2.txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        System.setOut(out);
		    List<String> list = new ArrayList<>();
		    try (Scanner scanner = new Scanner(file);) {
		        while (scanner.hasNext()) {
		            list.add(scanner.next());
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    
		  //  System.out.println(list);
		String bwt = list.get(0);
        String[] patterns = list.toArray(new String[list.size()]);
        
        for(int i = 1; i < patterns.length; ++i) {
            System.out.print(Better_BW_Matching(bwt,patterns[i]) + " ");
        }
       // System.out.println(patterns);
        out.close();
       

	}
	public static int Better_BW_Matching(String bwt,String pattern){
		char[] lastCol = bwt.toCharArray();
        char[] firstCol = Arrays.copyOf(lastCol,lastCol.length);
        Arrays.sort(firstCol);
        int top = 0;
        int bottom = firstCol.length-1;
        TreeMap<Character,Integer> firstOccurrence = createFirstOccurenceMap(firstCol);
        while(top <= bottom) {
            if(pattern.length() == 0) {
                return bottom - top + 1;
            }
            char symbol = pattern.charAt(pattern.length()-1);
            pattern = pattern.substring(0,pattern.length()-1);
            boolean flag = false;
            for(int i = top;i<= bottom; i++) {
                if(lastCol[i] == symbol) {
                   top =  firstOccurrence.get(symbol) + count(symbol, top, lastCol);
                   bottom = firstOccurrence.get(symbol)+count(symbol,bottom+1,lastCol) -1;
                   flag=true;
                   break;
                }
            }
            if(!flag){
            	return 0;
            }
        }
        return 0;
	}
	
	public static TreeMap<Character,Integer> createFirstOccurenceMap(char[] firstCol){
		TreeMap<Character,Integer> map = new TreeMap<>();
		for(int i=0;i<firstCol.length;i++){
			if(!map.containsKey(firstCol[i])){
				map.put(firstCol[i],i);
			}
		}
		return map;
	}
	
	public static int count(char symbol,int index,char[] lastCol){
		int count =0;
		for(int i=0;i<index;i++){
			if(lastCol[i]==symbol) count++;
		}
		return count;
	}

}
