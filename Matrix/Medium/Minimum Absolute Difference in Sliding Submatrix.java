// LeetCode - 3567



// Approach 1 - Brute Force (traversing all k*k sub-matrix) - Using Sorting
// T.C. - O((m-k+1) * (n-k+1) * k^2 * log(k^2)); k^2 = total elements
// S.C. - O(k^2)
class Solution {
    public int getMinAbsDiff(int i, int j, int[][] grid, int k, int m, int n){
        // adding into set for removing duplicates
        Set<Integer> set = new HashSet<>();

        for(int row = i; row < i+k; row++){
            for(int col = j; col < j+k; col++){
                set.add(grid[row][col]);
            }
        }

        if(set.size() == 1){
            return 0;
        }

        // adding into list
        List<Integer> list = new ArrayList<>();
        for(int element : set){
            list.add(element);
        }

        // sorting to bring min elements adjacent
        Collections.sort(list);
        int diff = Integer.MAX_VALUE;

        for(int l = 0; l<list.size()-1; l++){
            diff = Math.min(diff, Math.abs(list.get(l) - list.get(l+1)));
        }

        return diff;
    }

    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] result = new int[m-k+1][n-k+1];

        for(int i = 0; i <= m-k; i++){
            for(int j = 0; j <= n-k; j++){
                result[i][j] = getMinAbsDiff(i, j, grid, k, m, n);
            }
        }

        return result;
    }
}






// Approach 2 - Brute Force (traversing all k*k sub-matrix) - Without Sorting
// T.C. - O((m-k+1) * (n-k+1) * k^2 * log(k^2)); k^2 = total elements
// S.C. - O(k^2)
class Solution {
    public int getMinAbsDiff(int i, int j, int[][] grid, int k, int m, int n){
        // ordered set to keep min elements adjacent
        TreeSet<Integer> set = new TreeSet<>();

        for(int row = i; row < i+k; row++){
            for(int col = j; col < j+k; col++){
                set.add(grid[row][col]);
            }
        }

        if(set.size() == 1){
            return 0;
        }

        int diff = Integer.MAX_VALUE;
        Integer prev = null;

        for(Integer element : set){
            if(prev != null){
                diff = Math.min(diff, element - prev);
            }

            prev = element;
        }

        return diff;
    }

    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] result = new int[m-k+1][n-k+1];

        for(int i = 0; i <= m-k; i++){
            for(int j = 0; j <= n-k; j++){
                result[i][j] = getMinAbsDiff(i, j, grid, k, m, n);
            }
        }

        return result;
    }
}