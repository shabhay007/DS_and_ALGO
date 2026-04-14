// LeetCode Hard - 2463



// Approach 1 - DP (Memoization)
// T.C. - O(n * positions.size())
// S.C. - O(n * positions.size())
class Solution {
    int n;
    int m;
    
    public long solve(int i, int j, List<Integer> robot, List<Integer> positions, long[][] dp){
        if(i >= n){
            return 0;
        }
        
        if(j >= positions.size()){
            return (long) 1e12; // invalid distance
        }

        if(dp[i][j] != Integer.MAX_VALUE){
            return dp[i][j];
        }

        long currRobot = robot.get(i);
        long currPos = positions.get(j);

        long take = Math.abs(currPos - currRobot) + solve(i + 1, j+1, robot, positions, dp);
        long skip = solve(i, j+1, robot, positions, dp);

        return dp[i][j] = Math.min(take, skip);
    }

    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        n = robot.size();
        m = factory.length;

        // Sorting for easy tracking
        Collections.sort(robot);
        Arrays.sort(factory, (a, b) -> Integer.compare(a[0], b[0]));

        // expanding factory a/c to limit to avoid limit tracking
        List<Integer> positions = new ArrayList<>();
        for(int[] f : factory){
            for(int i = 0; i<f[1]; i++){
                positions.add(f[0]);
            }
        }

        long[][] dp = new long[n][positions.size()];
        for(long[] row : dp){
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        
        return solve(0, 0, robot, positions, dp);
    }
}






// Approach 2 - DP (Bottom Up)
// T.C. - O(n * factorySize)
// S.C. - O(n * factorySize)
class Solution {
    int n;
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        n = robot.size();

        // Sorting for easy tracking
        Collections.sort(robot);
        Arrays.sort(factory, (a, b) -> Integer.compare(a[0], b[0]));

        // expanding factory a/c to limit to avoid limit tracking
        List<Integer> positions = new ArrayList<>();
        for(int[] f : factory){
            for(int i = 0; i<f[1]; i++){
                positions.add(f[0]);
            }
        }

        int factorySize = positions.size();

        long[][] dp = new long[n+1][positions.size()+1];
        for(int i = 0; i<n; i++){
            dp[i][factorySize] = (long) 1e12; // no factories left to repair
        }

        // filling DP table using bottom up
        for(int i = n-1; i >= 0; i--){
            for(int j = factorySize - 1; j >= 0; j--){
                long take = Math.abs(positions.get(j) - robot.get(i)) + dp[i+1][j+1];
                long skip = dp[i][j+1];

                dp[i][j] = Math.min(take, skip);
            }
        }
        
        return dp[0][0];
    }
}