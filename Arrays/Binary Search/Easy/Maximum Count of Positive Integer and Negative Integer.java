// LeetCode Easy - 2529


// Approach 1
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int maximumCount(int[] nums) {
        int pos = 0;
        int neg = 0;

        for(int num : nums){
            if(num == 0){
                continue;
            }
            else if(num < 0){
                neg++;
            }
            else{
                pos++;
            }
        }

        return Math.max(neg, pos);
    }
}




// Approach 2 - Binary Search
// T.C. - O(2.log(n))
// S.C. - O(1)
class Solution {
    public int lowerBound(int[] nums, int n){
        int l = 0;
        int r = n-1;

        int pos = -1;

        while(l <= r){
            int mid = l + (r - l)/2;

            if(nums[mid] < 0){
                pos = mid;
                l  = mid + 1;
            }
            else{
                r = mid - 1;
            }
        }

        return pos;
    }

    public int upperBound(int[] nums, int n){
        int l = 0;
        int r = n-1;

        int pos = -1;

        while(l <= r){
            int mid = l + (r - l)/2;

            if(nums[mid] > 0){
                pos = mid;
                r  = mid - 1;
            }
            else{
                l = mid + 1;
            }
        }

        return pos;
    }


    public int maximumCount(int[] nums) {
        int n = nums.length;

        // finding number of negatives
        int firstNegPos = lowerBound(nums, n);
        int totalNegNumbers = (firstNegPos == -1) ? 0 : firstNegPos + 1;

        // finding number of positives
        int firstPosIdx = upperBound(nums, n);
        int totalPosNumbers = (firstPosIdx == -1) ? 0 : n - firstPosIdx;

        return Math.max(totalNegNumbers, totalPosNumbers);
    }
}