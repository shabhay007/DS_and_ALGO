// LeetCode - 2048



// Approach 1 - Brute Force
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public boolean isBalanced(int mid){
        int[] freq = new int[10];

        while(mid > 0){
            int d = mid % 10;
            freq[d]++;

            mid /= 10;
        }

        for(int i = 0; i<10; i++){
            if(freq[i] > 0 && freq[i] != i){
                return false;
            }
        }

        return true;
    }

    public int nextBeautifulNumber(int n) {
        
        for(int i = n+1; i<1000000; i++){
            if(isBalanced(i)){
                return i;
            }
        }   

        // if any number's solution/balanced number goes beyond 1e6
        // then for this constraint 1224444 will be the ans
        return 1224444;
    }
}