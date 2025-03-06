// LeetCode Easy - 2965


// Approach 1 - Using Map
// T.C. - O(2n^2)
// S.C. - O(n^2)
class Solution {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int n = grid.length;
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                map.put(grid[i][j], map.getOrDefault(grid[i][j], 0) + 1);
            }
        }

        // finding the repeating element
        // now finding the missing element
        int repeatingElement = -1;
        int missingElement = -1;

        for(int num = 1; num <= n*n; num++){
            if(!map.containsKey(num)){
                missingElement = num;
            }
            else if(map.get(num) == 2){
                repeatingElement = num;
            }
        }

        return new int[]{repeatingElement, missingElement};
    }
}




// Approach 2 - Maths
// T.C. - O(n^2)
// S.C. - O(1)
class Solution {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int n = grid.length;
        long N = n*n;

        long gridSum = 0;
        long gridSqSum = 0;

        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                gridSum += grid[i][j];
                gridSqSum += grid[i][j] * grid[i][j];
            }
        }

        long sum = N * (N+1)/2; // expected sum of N natural nums
        long sqSum = N * (N+1) * (2 * N + 1) / 6; // exp. sq sum of N natural nums

        long sumDiff = gridSum - sum;
        long sqSumDiff = gridSqSum - sqSum;

        long a = (sqSumDiff/sumDiff + sumDiff) / 2;
        long b = (sqSumDiff/sumDiff - sumDiff) / 2;

        return new int[]{(int) a, (int) b};
    }
}



// Approach 3 - Set
// T.C. - O(n^2)
// S.C. - O(n^2)
class Solution {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int n = grid.length;
        long N = n*n;
        HashSet<Integer> set = new HashSet<>();
        long currSum = 0;
        int[] ans = new int[2];

        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                if(set.contains(grid[i][j])){
                    ans[0] = grid[i][j];
                }
                else{
                    set.add(grid[i][j]);
                    currSum += grid[i][j];
                }
            }
        }

        long totalSum = N * (N+1)/2;
        ans[1] = (int) (totalSum - currSum);

        return ans;
    }
}