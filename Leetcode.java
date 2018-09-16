import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Leetcode {
	
	
	
	
	void quickSort(int []arr, int s, int e) {
			if(s >= e)
				return;

		 	int l = s;
	        int h = e;
	        int pivot = arr[s+(e-s)/2];
	        while (l < h) {
	            while (arr[l] < pivot) l++;
	            while (arr[h] > pivot) h--;
	            if (l <= h) {
	                swap(arr, l, h);
	                l++;
	                h--;
	            }
	        }
	        System.out.println("Pivot: "+ pivot);
	        printArray(arr);

	        // call quickSort() method recursively
	        quickSort(arr, s, h);
	        quickSort(arr, l, e);
	}
	
	void printArray(int []arr) {
		for(int a: arr) {
			System.out.print(a+" ");
		}
		System.out.println();
	}
	
	
	int getKthNumber(int arr[], int k) {
		
		int l = 0;
		int h = arr.length-1;
		while(l < h) {
			
			int p = getPivot(arr, l, h);
			System.out.println("p: "+p);
			if(p + 1 == k) {
				return arr[p];
			}
			
			if( k > p) {
				l = p;
			}else {
				h=p;
			}
		}
		
		return -1;
		
	}
	
	int getPivot(int []arr, int s, int e) {
		if(s > e)
			return -1;
		
		if(s == e)
			return s; 

	 	int l = s;
        int h = e;
        int pivot = arr[s+(e-s)/2];
        while (l < h) {
            while (arr[l] < pivot) l++;
            while (arr[h] > pivot) h--;
            if (l <= h) {
                swap(arr, l, h);
                l++;
                h--;
            }
        }
        
        for(int i=s; i<=e; i++) {
        	if(arr[i] == pivot)
        		return i;
        }
        
        return -1;
    }
	
	void swap(int arr[], int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	
    static boolean differByOneChar(String s1, String s2){
        
        int[] c = new int[26];
        for(int i=0; i<s1.length(); i++){
            c[s1.charAt(i) - 'a']++;
        }
        
        for(int i=0; i<s2.length(); i++){
            c[s2.charAt(i) - 'a']--;
        }
        
        boolean result = false;
        for(int i=0; i<26; i++){
            if(c[i] == 0)
                continue;
            if(c[i] == 1 || c[i] == -1){
                if(result){
                    result = false;
                    break;
                }
                result = true;
            }else{
                result = false;
                break;
            }
        }
                       
        return result;
        
    }
    
 
    static int compute(List<String> words, int N){
        int[]dp = new int[N];
        dp[0] = 1;
        int resultMax = 0;
        for(int i=1; i<N; i++){
            int max = 1;
            for(int j=i-1; j>=0; j--){
                if(words.get(i).length() - words.get(j).length() == 1){
                	if(differByOneChar(words.get(i), words.get(j))){
                		if(dp[j] + 1 > max){
                			max = dp[j]+1;
                		}
                    }
                    else{
                        break;
                    }
                }
            }
            dp[i] = max;
            resultMax = Math.max(resultMax, max);
        }
        
        return resultMax;
    }
 
 
    static int longestChain(List<String> words) {
    	
        words.sort((String s1, String s2) -> (s1.length() - s2.length()));
        
        return compute(words, words.size());
    }
	
	
	
	public static int getMaxProfit(int[] price) {
		
		if(price == null) {
			return 0;
		}
		
		int[] b = new int[price.length];
		int indx = price.length-1;
		
		for(int i=price.length-1; i>=0; i--) {
			if(price[indx] < price[i]) {
				indx = i;
			}
			
			b[i] = indx;
		}
		
		int profit = 0;
		for(int i=0; i<price.length; i++) {
			if(price[b[i]] - price[i] > 0) {
				profit += price[b[i]] - price[i];
			}
		}
	
		return profit;
	}

}
