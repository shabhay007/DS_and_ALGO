// LeetCode Hard - 1751



// Approach 1 (Brute Force) - Recursion (Gives TLE)
// T.C. - O(nlog(n) + 2^n)
// S.C. - O(n)
class Solution {
    public int getMax(int i, int n, int[][] events, int k, int prev){
        if(i >= n || k == 0){
            return 0;
        }

        int take = 0;

        if(prev == -1 || events[i][0] > prev){
            take += events[i][2] + getMax(i+1, n, events, k-1, events[i][1]);
        }

        int skip = getMax(i+1, n, events, k, prev);

        return Math.max(take, skip);
    }

    public int maxValue(int[][] events, int k) {
        int n = events.length;

        // sorting on the basis of startDay
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));

        return getMax(0, n, events, k, -1);
    }
}







// Approach 2 - Recursion + Memoization (Using Map)
// T.C. - O(nlog(n) + n * k * u); u = no of prev
// S.C. - O(n * k * u)
class Solution {
    public int getMax(int i, int n, int[][] events, int k, int prev, Map<String, Integer> dp){
        if(i >= n || k == 0){
            return 0;
        }

        String key = i + "-" + k + "-" + prev;

        if(dp.containsKey(key)){
            return dp.get(key);
        }

        int take = 0;

        if(prev == -1 || events[i][0] > prev){
            take += events[i][2] + getMax(i+1, n, events, k-1, events[i][1], dp);
        }

        int skip = getMax(i+1, n, events, k, prev, dp);

        // storing in map
        dp.put(key, Math.max(take, skip));

        return Math.max(take, skip);
    }

    public int maxValue(int[][] events, int k) {
        int n = events.length;

        // sorting on the basis of startDay
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));

        Map<String, Integer> dp = new HashMap<>();

        return getMax(0, n, events, k, -1, dp);
    }
}






// Approach 3 - Recursion + Memoization + Linear Search
// T.C. - O(nlog(n) + n * k * n)
// S.C. - O(n * k)
class Solution {
    public int getMax(int i, int n, int[][] events, int k, int[][] dp){
        if(i >= n || k == 0){
            return 0;
        }

        if(dp[i][k] != -1){
            return dp[i][k];
        }

        // finding next non-overlapping event using linear search
        int nextIndex = i+1;

        while(nextIndex < n){
            if(events[nextIndex][0] <= events[i][1]){
                nextIndex++;
            }
            else{
                break;
            }
        }

        int take = events[i][2] + getMax(nextIndex, n, events, k-1, dp);

        int skip = getMax(i+1, n, events, k, dp);

        return dp[i][k] = Math.max(take, skip);
    }

    public int maxValue(int[][] events, int k) {
        int n = events.length;

        // sorting on the basis of startDay
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));

        int[][] dp = new int[n+1][k+1];

        for(int[] row : dp){
            Arrays.fill(row, -1);
        }

        return getMax(0, n, events, k, dp);
    }
}







// Approach 4 - Recursion + Memoization + Binary Search
// T.C. - O(nlog(n) + n * k * log(n))
// S.C. - O(n * k)
class Solution {
    public int getNextIndex(int i, int n, int[][] events, int prevEnd){
        int l = i;
        int r = n-1;

        while(l <= r){
            int mid = l + (r - l)/2;

            if(events[mid][0] > prevEnd){
                r = mid - 1;
            }
            else{
                l = mid + 1;
            }
        }

        return l;
    }

    public int getMax(int i, int n, int[][] events, int k, int[][] dp){
        if(i >= n || k == 0){
            return 0;
        }

        if(dp[i][k] != -1){
            return dp[i][k];
        }

        // finding next non-overlapping event using binary search
        int nextIndex = getNextIndex(i+1, n, events, events[i][1]);

        int take = events[i][2] + getMax(nextIndex, n, events, k-1, dp);

        int skip = getMax(i+1, n, events, k, dp);

        return dp[i][k] = Math.max(take, skip);
    }

    public int maxValue(int[][] events, int k) {
        int n = events.length;

        // sorting on the basis of startDay
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));

        int[][] dp = new int[n+1][k+1];

        for(int[] row : dp){
            Arrays.fill(row, -1);
        }

        return getMax(0, n, events, k, dp);
    }
}