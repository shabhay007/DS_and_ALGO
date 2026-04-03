// LeetCode Hard - 3661



// Approach 1 - DP (Memoization) + Binary Search
// T.C. - O(nlogn + wlogw); w = walls
// S.C. - O(n)
class Solution {
    // Binary search: first index >= target
    public int lowerBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] >= target) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    // Binary search: first index > target
    public int upperBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] > target) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    public int countWalls(int[] walls, int l, int r){
        int left = lowerBound(walls, l);
        int right = upperBound(walls, r);

        return right - left;
    }
    
    public int solve(int i, List<int[]> list, List<int[]> range, int[] walls, int prevDir, int[][] dp){
        if(i == list.size()){
            return 0;
        }

        if(dp[i][prevDir] != -1){
            return dp[i][prevDir];
        }

        int leftStart = range.get(i)[0];

        if(prevDir == 1){
            leftStart = Math.max(leftStart, range.get(i-1)[1] + 1);
        }

        int leftTake = countWalls(walls, leftStart, list.get(i)[0]) 
                        + solve(i + 1, list, range, walls, 0, dp); // fired left

        int rightTake = countWalls(walls, list.get(i)[0], range.get(i)[1]) 
                        + solve(i + 1, list, range, walls, 1, dp); // fired right

        return dp[i][prevDir] = Math.max(leftTake, rightTake);
    }
    
    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;
        int m = walls.length;

        List<int[]> list = new ArrayList<>();
        for(int i = 0; i<n; i++){
            list.add(new int[]{robots[i], distance[i]});
        }

        // sorting position wise
        Collections.sort(list, (a, b) -> Integer.compare(a[0], b[0]));
        Arrays.sort(walls); // to use binary search

        // preparing range of each robot
        List<int[]> range = new ArrayList<>();
        for(int i = 0; i<n; i++){
            int pos = list.get(i)[0];
            int d = list.get(i)[1];

            int leftLimit = (i == 0) ? 1 : list.get(i-1)[0] + 1;
            int rightLimit = (i == n-1) ? (int) 1e9 : list.get(i+1)[0] - 1;

            // final range
            int left = Math.max(leftLimit, pos-d);
            int right = Math.min(pos+d, rightLimit);

            range.add(new int[]{left, right});
        }

        // prev = 0 (previous robo fired bullet to left)
        // prev = 1 (previous robo fired bullet to right)
        int[][] dp = new int[n][2];
        for(int[] row : dp){
            Arrays.fill(row, -1);
        }

        return solve(0, list, range, walls, 0, dp);
    }
}