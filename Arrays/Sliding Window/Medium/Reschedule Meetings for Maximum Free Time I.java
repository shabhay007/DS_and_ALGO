// LeetCode 3439



// Approach 1 - Greedy + Sliding Window
// T.C. - O(n + n)
// S.C. - O(n)
class Solution {
    public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
        int n = startTime.length;
        List<Integer> freeTime = new ArrayList<>();
        freeTime.add(startTime[0]);

        for(int i = 1; i<n; i++){
            freeTime.add(startTime[i] - endTime[i-1]);
        }

        freeTime.add(eventTime - endTime[n - 1]);

        // now performing Sliding Window
        int i = 0;
        int j = 0;
        int maxFreeTime = 0;
        int currFreeTime = 0;
        int m = freeTime.size(); // m <= n

        while(j < m){
            currFreeTime += freeTime.get(j);

            if(i < m && j-i+1 > k+1){
                currFreeTime -= freeTime.get(i);
                i++;
            }

            maxFreeTime = Math.max(maxFreeTime, currFreeTime);
            j++;
        }

        return maxFreeTime;
    }
}