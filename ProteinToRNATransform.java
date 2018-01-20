import java.math.BigInteger;

public class ProteinToRNATransform {

	public static void main(String[] args) {
      String input = "MPVEYFLMSQDGKCTKRIFRFDGIVFWHAMCDSLGIVNSMNWAYFYWWEVAKQDHMHDNTGVGNFVGWVRPPMGMSWSIDICLNCTYPRPSWPQLAPEMAMCCEPVGPMDKKIRANATPKWTNGKGQNFYWPLQPEGFVGSFTMQKEDKDSQYCWSHGSSMIPGLNYMFHAFPEFNVAFSKMHRKDYTSAEVPEVCGHIWKITPHKKMHCMWGQARNVDHVSLFMRTMYDNMGKIDDVTAEDSDKHMHLVTWQFFTWRHKIYTNDDEIYFRSMDRIPQAVVVIQNFFTGSDTAVDRNMRIETTKTGHGMWQIKVAKCEWLCYFDSAMPHLINRAKTDRGSGVVWMLEDNAGECMYVTVFNRLAHWTVLQVGWKPGQPYYEMWFQHCEARMMIIGKCCMVSYVPYYPNFPSVFIRDVATPVETCPSNEKWWETHQWCGGKTITHKATVEFEHWSPIMGCEIHIDALCDKQKTHSDVTTVESRNDHIACWWPLMGMWGTLGKHWAMQALNGRSTQANNHLKVNLEVVETEWEPCFTCQKSMRHIIENFFRRPKVQPTTHLQWYFNKSGIAETTHQAEWVNRKMCNRMKIWIYWMCCRMFWTVCFIPKTMALYKRKQYVLMRANFMWTIYCGWDQVWMIKNPDLSMNAGSIADMISEFDSQCPVEWIRMRLRWFGCQHPEAGTDKIQIHTPKYKQSKLSWTNDRDYVIARPFSQYGQHWNWNVEHWAYSEMTLSQVVYESNWQCLNHQFRDPMEQDCKHEAYNTVMTCHMIVWCCWDFEETLCFWYEMMHAVWIEMHIAFKMQHAIDRYMYEQLYNQKTQLPAYHGSDNHLHCQELYRVYWMQDVWHCYCIHGSFCFPKIPTEWYTNQVGLMWMRWHCYFCPNNERMNRRFCGSLYAYYTLNDTSIIFECGNRYFTWNYIHTFGQERDCSGCAMVVDQFDAPNVLAPPPYRDLHIHMDGTLASGYVDMSHTSSCIVVDQIAWMYQHIADKSGAFLKDNITC";
      long output = transformProtein(input);      
      System.out.println(output);
	}
	public static long  transformProtein(String mRNA){
		BigInteger result = new BigInteger("1");	   
	   long finalResult =1;
	  
	   char[] mRNAchars = mRNA.toCharArray();	   
	   for(int i=0;i<=mRNAchars.length-1;i++){
		   result = result.multiply(new BigInteger(getRNAsize(mRNAchars[i]))); 
		 
	   }
	   result = result.multiply(new BigInteger("3")).mod(new BigInteger("1000000")); // multiplying with stop codons;
	   System.out.println(result);
	   return result.longValue();
	}
	
	public static String getRNAsize(char p){
		String value = "1";
		switch(p){
		case 'M':
		case 'W':
			value= "1";
			break;
		case 'N': 
		case 'C':
		case 'D':
		case 'Q':
		case 'E':
		case 'H':
		case 'K':
		case 'F':
		case 'Y':
			value = "2";
			break;
		case 'I':
			value = "3";
			break;
		case 'A': 
		case 'G':
		case 'P':
		case 'T':
		case 'V':
			value = "4";
			break;
		case 'R': 
		case 'L':
		case 'S':
			value = "6";
			break;
		default:
			value = "1";
			break;		
		}
		return value;
	}

}
