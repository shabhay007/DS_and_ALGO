// LeetCode Medium - 2554


// Greedy - using set
// T.C - O(banned.length + n);
// S.C - O(m) - size of banned array
class Solution {
    public int maxCount(int[] banned, int n, int maxSum) {
        // Keeping a set to check if the number is banned or not in O(1) time
        HashSet<Integer> set = new HashSet<>();
        for(int num : banned){
            set.add(num);
        }

        int count = 0;
        long sum = 0;

        // loop to the range 1 - n
        for(int i = 1; i<=n; i++){
            // if the number is banned then continue to next iteration
            if(set.contains(i)) continue;

            sum += i;

            // at any moment sum > maxSum then break from the loop
            if(sum > maxSum) break;

            // after adding i to the sum and sum <= maxSum, increment the counter
            if(sum <= maxSum){
                count++;
            }
        }

        return count;
    }
}