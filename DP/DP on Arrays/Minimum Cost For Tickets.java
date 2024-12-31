// LeetCode Medium - 983


// Recursion + Memoization
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public int solve(int i, int n, int[] days, int[] costs, int[] dp){
        if(i >= n){ // days ends
            return 0;
        }

        if(dp[i] != -1){
            return dp[i];
        }

        int oneDay = costs[0] + solve(i+1, n, days, costs, dp);

        // seven days pass
        int j = i;
        int max_7 = days[i] + 7;
        while(j < n && days[j] < max_7){
            j++;
        }
        
        int sevenDays = costs[1] + solve(j, n, days, costs, dp);


        // 30 days pass
        int k = i;
        int max_30 = days[i] + 30;
        while(k < n && days[k] < max_30){
            k++;
        }

        int thirtyDays = costs[2] + solve(k, n, days, costs, dp);

        return dp[i] = Math.min(oneDay, Math.min(sevenDays, thirtyDays));
    }

    public int mincostTickets(int[] days, int[] costs) {
        int n = days.length;
        int dp[] = new int[n+1];
        Arrays.fill(dp, -1);

        return solve(0, n, days, costs, dp);    
    }
}



// Tabulation
// T.C. - O(lastDay)
// S.C. - O(lastDay)
class Solution{
    public int mincostTickets(int[] days, int[] costs) {
        int n = days.length;
        int lastDay = days[n-1];

        HashSet<Integer> set = new HashSet<>();
        for(int day : days){
            set.add(day);
        }

        // dp[i] = min cost to travel till i days
        int dp[] = new int[lastDay+1];
        dp[0] = 0;

        for(int i = 1; i<=lastDay; i++){
            // check if we have to travel on that day or not
            if(!set.contains(i)){
                dp[i] = dp[i-1];
                continue;
            }

            dp[i] = Integer.MAX_VALUE;

            // i-1, i-7 or i-30 can be -ve index, so to avoid -ve index, using Math.max()
            // or we can use if(i-1 > 0) for every one
            int oneDayPass = costs[0] + dp[Math.max(i-1, 0)]; 
            int sevenDayPass = costs[1] + dp[Math.max(i-7, 0)]; 
            int monthlyPass = costs[2] + dp[Math.max(i-30, 0)];

            dp[i] = Math.min(oneDayPass, Math.min(sevenDayPass, monthlyPass));
        }

        return dp[lastDay];
    }
}