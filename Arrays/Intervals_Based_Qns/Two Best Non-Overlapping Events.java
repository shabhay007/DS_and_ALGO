// LeetCode Medium - 2054


// Brute Force - gives TLE
class Solution {
    public int maxTwoEvents(int[][] events) {
        int n = events.length; 
        int maxVal = 0;

        for(int i = 0; i<n; i++){
            int val = events[i][2];
            maxVal = Math.max(maxVal, val);

            for(int j = 0; j<n; j++){
                if(i != j && events[i][1] < events[j][0]){
                    maxVal = Math.max(maxVal, val + events[j][2]);
                }
            }
        }

        return maxVal;
    }
}




// Better - DP - Memoization
class Solution {
    // Upper Bound of endTime
    public int binarySearch(int[][] events, int endTime){
        int l = 0;
        int r = events.length - 1;
        int result = events.length;

        while(l <= r){
            int mid = l + (r-l)/2;

            if(events[mid][0] > endTime){
                result = mid;
                r = mid - 1;
            }
            else{
                l = mid + 1;
            }
        }

        return result;
    }

    public int solve(int idx, int[][] events, int count, int[][] dp){
        if(count >= 2 || idx >= events.length){
            return 0;
        }

        if(dp[idx][count] != -1) return dp[idx][count];

        int nextValidEventIdx = binarySearch(events, events[idx][1]);
        int take = events[idx][2] + solve(nextValidEventIdx, events, count + 1, dp);

        int not_take = solve(idx+1, events, count, dp);

        return dp[idx][count] = Math.max(take, not_take);
    }

    public int maxTwoEvents(int[][] events) {
        int n = events.length;
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));
        int count = 0;

        // only i and count will change & max value of count goes to 2
        int[][] dp = new int[100001][3];
        for(int[] row : dp){
            Arrays.fill(row, -1);
        }

        return solve(0, events, count, dp);
    }
}



// Optimal - Heap
class Solution {
    public int maxTwoEvents(int[][] events) {
        // sorting based on start time
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));

        // sorting based on end time
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        int prevMax = 0;
        int result = 0;

        // O(2 * NlogN)
        for(int[] query : events){
            // it is not nested, there is only one insertion and one deletion
            // so, lonN
            while(!pq.isEmpty() && pq.peek()[1] < query[0]){
                prevMax = Math.max(prevMax, pq.poll()[2]);
            }

            result = Math.max(result, prevMax + query[2]);
            pq.offer(query);
        }

        return result;
    }
}