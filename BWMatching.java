import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class BWMatching {

	public static void main(String[] args) {
		 File file = new File("C:/EclipseWorkspace/CompBioAss1/src/rosalind_ba9l.txt");
		 PrintStream out = null;
			try {
				out = new PrintStream(new FileOutputStream("out1.txt"));
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
		    
		    System.out.println(list);
		String bwt = list.get(0);
        String[] patterns = list.toArray(new String[list.size()]);
        
        for(int i = 1; i < patterns.length; ++i) {
            System.out.print(BW_Matching(bwt,patterns[i]) + " ");
        }
        System.out.println(patterns);
        out.close();
       
        

	}
	public static int BW_Matching(String bwt,String pattern){
		char[] lastCol = bwt.toCharArray();
        char[] firstCol = Arrays.copyOf(lastCol,lastCol.length);
        Arrays.sort(firstCol);
        int top = 0;
        int bottom = firstCol.length-1;
        while(top <= bottom) {
            if(pattern.length() == 0) {
                return bottom - top + 1;
            }
            char symbol = pattern.charAt(pattern.length()-1);
            pattern = pattern.substring(0,pattern.length()-1);
            int topIndex = -1;
            int BottomIndex = -1;
            for(int i = top;i<= bottom; i++) {
                if(lastCol[i] == symbol) {
                    if(topIndex == -1) {
                        topIndex = i;
                        BottomIndex = i;
                    }
                    else {
                        BottomIndex = i;
                    }
                }
            }
            if(topIndex == -1 || BottomIndex == -1) {
                return 0;
            }
            top = lastToFirst(bwt,topIndex);
            bottom = lastToFirst(bwt,BottomIndex);
        }
        return 0;
	}
	public static int lastToFirst(String s,int i){
		TreeMap<Character,Integer> occurrenceMap = new TreeMap<>();
		int []rank = new int[s.length()];
		for(int j=0;j<s.length();j++){
			char key = s.charAt(j);
		  if(occurrenceMap.containsKey(key)){
			  int count = occurrenceMap.get(key);
			  count = count+1;
			  occurrenceMap.put(key,count);	
			  rank[j]=count;
		  }
		  else{
			  occurrenceMap.put(key,0);
		  }
		}
		char c = s.charAt(i);
		int position = 0;
		for(Map.Entry<Character,Integer> entry : occurrenceMap.entrySet()) {
			  char key = entry.getKey();
			  if(key!=c){
				  position = position + entry.getValue()+1;
			  }
			  else{
				  position = position + rank[i];
				  break;
			  }
			 // System.out.println(key + " => " + entry.getValue());
			}
		
		return position;		
	}

}
