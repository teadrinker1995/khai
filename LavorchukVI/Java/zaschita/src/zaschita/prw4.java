package zaschita;

import java.util.Random;

public class prw4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n,k,l,i,j,q,w,buf;
		n = k = l = 6;
		int mas[][] = new int [n][n];
		for (i = 0; i < n; i++){
			for (j = 0; j < n; j++){
				Random rand = new Random();
				mas[i][j] = rand.nextInt(100);
				System.out.print(mas[i][j] + " ");
			}
			System.out.print("\n");
		}
		int masbuf[][] = new int [n][n];
		for (i = 0; i < n/2; i++) {
			for (j = n/2; j < k; j++) {
				masbuf[i][j] = mas[i][j];
			}
			k--;
		}
		k = 0;
		for (i = n-1; i > n/2 - 1; i--) {
			for (j = k; j < n/2; j++){
				masbuf[i][j] = mas[i][j];
			}
			k++;
		}
		System.out.print("\nmasBuf[][]\n");
		for (i = 0; i < n; i++){
			for (j = 0; j < n; j++){
				System.out.print(masbuf[i][j] + " ");
			}
			System.out.print("\n");
		}
		k = n;
		for (i = 0; i < n/2; i++) {
			for (j = n/2; j < k; j++) {				
				mas[i][j] = masbuf[n-1-i][n-1-j];
				mas[n-1-i][n-1-j] = masbuf[i][j];
			}
			k--;
		}
		k = 0;
		
		System.out.print("\nmas[][]\n");
		for (i = 0; i < n; i++){
			for (j = 0; j < n; j++){
				System.out.print(mas[i][j] + " ");
			}
			System.out.print("\n");
		}
		
	}

}
