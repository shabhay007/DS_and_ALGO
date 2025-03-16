// LeetCode Medium - 2594



// Approach 1 (Optimal) - Binary Search
// T.C. - O(n + n.log(r))
// S.C. - O(1)
class Solution {
    public boolean isPossible(long time, int[] ranks, int cars, int n){
        long carsRepaired = 0;

        for(int i = 0; i<n; i++){
            // ignoring the T.C. of sqrt()
            carsRepaired += (long) Math.sqrt(time/ranks[i]);
        }

        return carsRepaired >= cars;
    }

    public long repairCars(int[] ranks, int cars) {
        int n = ranks.length;
        long maxRank = 0;

        for(int rank : ranks){
            maxRank = Math.max(maxRank, rank);
        }

        long l = 1;
        long r = maxRank * cars * cars; // max time depends on slowest mechanic
        long minTime = -1;

        while(l <= r){
            long mid = l + (r - l)/2;

            if(isPossible(mid, ranks, cars, n)){
                minTime = mid;
                r = mid - 1;
            }
            else{
                l = mid + 1;
            }
        }

        return minTime;
    }
}