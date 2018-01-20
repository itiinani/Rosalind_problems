
/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/




import java.util.Scanner;


public class MortalFibonacciRabbits {

	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		System.out.print(" n = ");
		int n = input.nextInt();		
		System.out.print(" m = ");
		int m = input.nextInt();
		input.close();	
		long[] old = new long[n];
		long[] young = new long[n];
		young[0] = 1;
		old[0]=0;
		old[1]=1;
		for(int i=1;i<n;i++){
			if(i<m){
				old[i]= young[i-1]+old[i-1];
				young[i]=old[i-1];
			}
			else{
		        old[i] = young[i-1]+old[i-1]-young[i-m];
		        young[i] = old[i-1];	
			}
		}
		
		System.out.println(young[n-1]+old[n-1]);
		
	}
}

