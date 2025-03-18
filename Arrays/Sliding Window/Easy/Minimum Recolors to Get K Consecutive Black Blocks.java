// LeetCode Easy - 2379



// Approach 1 - Brute Force (Checking all the substrings)
// T.C. - O(n^3)
// S.C. - O(1)
class Solution {
    public int getMin(int start, int end, String blocks){
        int count = 0;

        for(int i = start; i<=end; i++){
            char ch = blocks.charAt(i);

            if(ch == 'W'){
                count++;
            }
        }

        return count;
    }

    public int minimumRecolors(String blocks, int k) {
        int n = blocks.length();
        int minOperations = Integer.MAX_VALUE;

        for(int i = 0; i<n; i++){
            for(int j = i; j<n; j++){
                if(j - i + 1 == k){
                    int whiteBlock = getMin(i, j, blocks);
                    minOperations = Math.min(minOperations, whiteBlock);
                }
            }
        }

        return minOperations;
    }
}




// Approach 2 - Sliding Window
// T.C. - O(2n)
// S.C. - O(1)
class Solution {
    public int minimumRecolors(String blocks, int k) {
        int n = blocks.length();
        int minOperations = Integer.MAX_VALUE;

        int l = 0;
        int r = 0;
        int whiteCount = 0;

        while(r < n){
            char ch = blocks.charAt(r);

            if(ch == 'W'){
                whiteCount++;
            }

            while(r - l + 1 > k){
                char chL = blocks.charAt(l);

                if(chL == 'W'){
                    whiteCount--;
                }

                l++;
            }

            if(r - l + 1 == k){
                minOperations = Math.min(minOperations, whiteCount);
            }

            r++;
        }

        return minOperations;
    }
}




// Approach 3 - Sliding Window with slight optimization in Approach 2
// T.C. - O(2n)
// S.C. - O(1)
class Solution {
    public int minimumRecolors(String blocks, int k) {
        int n = blocks.length();
        int minOperations = Integer.MAX_VALUE;

        int l = 0;
        int r = 0;
        int whiteCount = 0;

        while(r < n){
            char ch = blocks.charAt(r);

            if(ch == 'W'){
                whiteCount++;
            }

            if(r - l + 1 > k){
                char chL = blocks.charAt(l);

                if(chL == 'W'){
                    whiteCount--;
                }

                l++;
            }

            if(r - l + 1 == k){
                minOperations = Math.min(minOperations, whiteCount);
            }

            r++;
        }

        return minOperations;
    }
}