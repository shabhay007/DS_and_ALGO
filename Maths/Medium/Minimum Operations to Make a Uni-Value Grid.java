// LeetCode Medium - 2033


// Brute Force
// T.C. - O(m.n + m^2.n^2)
// S.C. - O(1)
class Solution {
    public boolean checkForValidity(int[][] grid, int x){
        // if two numbers have the same remainder when divided by x, 
        // their difference will be divisible by x
        int remainder = grid[0][0] % x;

        for(int[] row : grid){
            for(int element : row){
                if(element % x != remainder){
                    return false;
                }
            }
        }

        return true;
    }

    public int getMinOperations(int m, int n, int[][] grid, int target, int x){
        int ops = 0;

        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                if(Math.abs(target - grid[i][j]) % x != 0){
                    return Integer.MAX_VALUE;
                }

                ops += Math.abs(target - grid[i][j])/x;
            }
        }

        return ops;
    }

    public int minOperations(int[][] grid, int x) {
        int m = grid.length;
        int n = grid[0].length;

        if(!checkForValidity(grid, x)){
            return -1;
        }

        int minOperations = Integer.MAX_VALUE;

        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                minOperations = Math.min(minOperations, getMinOperations(m, n, grid, grid[i][j], x));
            }
        }

        return minOperations;
    }
}




// Approach 2 (Optimal) - Sorting, Maths
// T.C. - O(3N); N = m.n
// S.C. - O(m.n)
class Solution {
    public int minOperations(int[][] grid, int x) {
        int m = grid.length;
        int n = grid[0].length;

        List<Integer> list = new ArrayList<>();

        // populating the list
        for(int i = 0; i<m; i++){ // O(N)
            for(int j = 0; j<n; j++){
                list.add(grid[i][j]);
            }
        }

        // sorting the list
        Collections.sort(list); // O(N.logN);  N = m.n

        // finding the median
        int medianIdx = (m * n / 2);
        int median = list.get(medianIdx); // target

        // finding the min operations
        int minOperations = 0;

        for(int num : list){ // O(N)
            if(num % x != median % x){
                return -1;
            }

            minOperations += Math.abs(median - num) / x;
        }

        return minOperations;
    }
}