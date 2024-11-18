// Leetcode - 1652 - Easy


// Brute Force
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