// leetcode:- 2563

import java.util.*;

class Solution {
    public int lowerBound(int start, int end, int[] nums, int target){
        int reqIdx = end+1; //Initializing with end+1 to indicate not found case

        while(start <= end){
            // Finding the first index where nums[i] >= target
            int mid = start + (end - start)/2;

            if(nums[mid] >= target){
                reqIdx = mid; // might be potential answer
                end = mid - 1; // looking for previous indices
            }
            else{
                start = mid+1;
            }
        }

        return reqIdx;
    }

    public int upperBound(int start, int end, int[] nums, int target){
        int reqIdx = start - 1; //Initializing with start - 1 to indicate not found case

        while(start <= end){
            // Finding the first index where nums[i] <= target
            int mid = start + (end - start)/2;

            if(nums[mid] <= target){
                reqIdx = mid; // might be potential answer
                start = mid+1; // looking for later indices
            }
            else{
                end = mid - 1;
            }
        }

        return reqIdx;
    }

    public long countFairPairs(int[] nums, int lower, int upper) {
        int n = nums.length;
        Arrays.sort(nums);
        long count = 0;

        for(int i = 0; i<n-1; i++){
            int left = lowerBound(i+1, n-1, nums, lower-nums[i]);
            int right = upperBound(i+1, n-1, nums, upper-nums[i]);

            if(left <= right){
                count += (right - left +1);
            }
        }

        return count;
    }
}