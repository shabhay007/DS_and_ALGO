// LeetCode - 3577



// Approach 1 - Maths
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int countPermutations(int[] complexity) {
        int n = complexity.length;
        long result = 1;
        int mod = (int) 1e9 + 7;

        for(int i = 1; i<n; i++){ // i = 0; will always be fixed
            if(complexity[i] <= complexity[0]){
                return 0;
            }

            result = (result * i) % mod; // factorial
        }

        return (int) result;
    }
}