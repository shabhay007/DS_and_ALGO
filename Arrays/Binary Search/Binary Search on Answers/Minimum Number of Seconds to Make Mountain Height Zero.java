// LeetCode Medium - 3296



// Approach - Binary Search on Answers
// T.C. - O(n * log(r))
// S.C. - O(1)
class Solution {
    public boolean check(long mid, int height, int[] time){
        long h = 0;

        for(int t : time){
            h += Math.sqrt(2*mid/t + 0.25) - 0.5;

            if(h >= height){
                return true;
            }
        }

        return h >= height;
    }

    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        int n = workerTimes.length;

        long maxTime = 0;
        for(int time : workerTimes){
            maxTime = Math.max(maxTime, time);
        }
        
        long l = 1;
        long r = (long) maxTime * mountainHeight * (mountainHeight + 1)/2;
        long result = 0;

        while(l <= r){
            long mid = l + (r - l)/2;

            if(check(mid, mountainHeight, workerTimes)){
                result = mid;
                r = mid-1;
            }
            else{
                l = mid + 1;
            }
        }

        return result;
    }
}