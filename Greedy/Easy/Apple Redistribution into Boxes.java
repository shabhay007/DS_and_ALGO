// LeetCode - 3074



// Approach 1 - Greedy + Sorting
// T.C. - O(n + mlog(m))
// S.C. - O(1)
class Solution {
    public int minimumBoxes(int[] apple, int[] capacity) {
        int n = apple.length;
        int m = capacity.length;

        int totalApple = 0;

        for(int a : apple){
            totalApple += a;
        }

        // sorting capacity
        Arrays.sort(capacity);
        int count = 0;

        // Selecting boxes with largest capacity Greedily
        for(int i = m-1; i >= 0; i--){
            totalApple -= capacity[i];

            if(totalApple <= 0){
                return m-i;
            }
        }

        return -1;
    }
}