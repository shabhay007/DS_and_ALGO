// Leetcode - 1652 - Easy


// Brute Force  Approach 1
class Solution {
    public int[] decrypt(int[] code, int k) {
        int n = code.length;
        int result[] = new int[n];

        if(k == 0){
            return result;
        }
        else if(k > 0){
            //Forward circular array traversal
            for(int i = 0; i<n; i++){
                int sum = 0;

                for(int j = 1; j<=k; j++){ //sum of next k elements
                    sum += code[(i+j) % n];
                }

                result[i] = sum;
            }
        }
        else{
            //Backward circular array traversal
            for(int i = 0; i<n; i++){
                int sum = 0;

                // for(int j = 1; j <= Math.abs(k); j++){ //sum of previous k elements
                for(int j = 1; j <= -k; j++){ //sum of previous k elements
                    sum += code[(i-j+n) % n];
                }

                result[i] = sum;
            }
        }

        return result;
    }
}




// Brute Force Approach 2
class Solution {
    public int[] decrypt(int[] code, int k) {
        int n = code.length;
        int result[] = new int[n];

        if(k == 0){
            return result;
        }
        
        for(int i = 0; i<n; i++){
            if(k < 0){
                // for -ve index - j+n
                // for out of bound index - (j+n) % n
                for(int j = i - Math.abs(k); j<i; j++){
                    result[i] += code[(j+n) % n];
                }
            }
            else{
                // for out of bound index - j % n
                for(int j = i+1; j<i+k+1; j++){
                    result[i] += code[j % n];
                }
            }
        }

        return result;
    }
}




// Optimal - Sliding Window - O(n)
class Solution {
    public int[] decrypt(int[] code, int k) {
        int n = code.length;
        int result[] = new int[n];

        if(k == 0){
            return result;
        }

        int i = -1, j = -1;

        if(k > 0){
            i = 1; 
            j = k;
        }
        else{
            i = n - Math.abs(k);
            j = n-1;
        }

        int firstWindowSum = 0;
        for(int ptr = i; ptr<=j; ptr++){
            firstWindowSum += code[ptr];
        }

        for(int m = 0; m<n; m++){
            result[m] = firstWindowSum;

            // removing the previous element in the window sum
            firstWindowSum -= code[i % n];
            i++;

            // adding the next element in the window sum
            firstWindowSum += code[(j + 1) % n];
            j++;
        }

        return result;
    }
}
