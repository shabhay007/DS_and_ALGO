// LeetCode Medium - 1769


// Brute force
// T.C. - O(n^2)
// S.C. - O(1)
class Solution {
    public int[] minOperations(String boxes) {
        int n = boxes.length();
        int result[] = new int[n];

        for(int i = 0; i<n; i++){
            int noOfOps = 0;

            for(int j = 0; j<n; j++){
                if(i != j && boxes.charAt(j) == '1'){
                    noOfOps += Math.abs(j - i);
                }
            }

            result[i] = noOfOps;
        }

        return result;
    }
}



// Optimal - Prefix Sum
// T.C. - O(2 * n)
// S.C. - O(1)
class Solution {
    public int[] minOperations(String boxes) {
        int n = boxes.length();
        int cummValue = 0;
        int cummValueSum = 0;
        int[] result = new int[n];

        // populating moves for all left balls to index i
        for(int i = 0; i<n; i++){
            result[i] = cummValueSum;
            cummValue += boxes.charAt(i) - '0'; // cummulative sum from left to right
            cummValueSum += cummValue; // sum of cummulative sum from left to right
        }

        // populating moves for all left balls to index i
        cummValue = 0;
        cummValueSum = 0;
        for(int i = n-1; i>=0; i--){
            result[i] += cummValueSum; // adding moves to each index
            cummValue += boxes.charAt(i) - '0'; // cummulative sum from right to left
            cummValueSum += cummValue; // sum of cummulative sum from right to left
        }

        return result;
    }
}