// LeetCode Medium - 2226



// Approach 1 - Brute Force (Gives TLE)
// T.C. - O(n + max * n)
// S.C. - O(1)
class Solution {
    public int maximumCandies(int[] candies, long k) {
        int n = candies.length;
        int max = 0;

        for(int candy : candies){ // O(n)
            max = Math.max(max, candy);
        }

        // finding the maxCandy
        for(int c = max; c >= 1; c--){
            int count = 0;

            for(int i = 0; i<n; i++){
                // no of children can get candies from this pile
                count += candies[i]/c;
            }

            // since we are traversing from max to low, so 1st max will be our ans.
            if(count >= k){
                return c;
            }
        }

        return 0;
    }
}




// Approach 2 (Optimal) - Binary Search
// T.C. - O(n + nlog(max))
// S.C. - O(1)
class Solution {
    public boolean isPossibleToDistributeEqually(int mid, int[] arr, long k){
        long count = 0;

        for(int candy : arr){
            count += candy / mid;

            if(count >= k){
                return true;
            }
        }

        return false;
    }

    public int maximumCandies(int[] candies, long k) {
        int n = candies.length;

        long totalCandies = 0;
        int max = 0;

        for(int candy : candies){ // O(n)
            totalCandies += candy;
            max = Math.max(max, candy);
        }

        if(totalCandies < k){
            return 0; // candies can't be distributed equally
        }

        int low = 1;
        int high = max;
        int maxNumOfCandy = 0;

        while(low <= high){ // O(log(n))
            int mid = low + (high - low)/2;

            if(isPossibleToDistributeEqually(mid, candies, k)){ // O(n)
                maxNumOfCandy = mid;
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }
        }

        return maxNumOfCandy;
    }
}