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



// Approach 3 - Java Stream API
// T.C. - O(2n)
// S.C. - O(1)
class Solution {
    public int maximumCount(int[] nums) {
        IntPredicate lambdaP = num -> num > 0;
        IntPredicate lambdaN = num -> num < 0;

        // by default count(), here returns in long
        int positiveCount = (int) Arrays.stream(nums).filter(lambdaP).count();
        int negativeCount = (int) Arrays.stream(nums).filter(lambdaN).count();

        return Math.max(positiveCount, negativeCount);
    }
}




// Approach 4 - Binary Search
// T.C. - O(2log(n))
// S.C. - O(1)
class Solution {
    public int lowerBound(int[] nums, int n, int target){
        int idx = n; // to avoid case when all 0's

        int l = 0;
        int r = n-1;

        while(l <= r){
            int mid = l + (r - l)/2;

            if(nums[mid] >= target){
                idx = mid;
                r = mid - 1;
            }
            else{
                l = mid + 1;
            }
        }

        return idx;
    }

    public int maximumCount(int[] nums) {
        int n = nums.length;

        // 1st positive number idx i.e. num >= 1
        int positiveCount = lowerBound(nums, n, 1);

        // 1st 0 or positive number idx i.e. num >= 0
        int negativeCount = lowerBound(nums, n, 0);

        return Math.max(n - positiveCount, negativeCount); // Max(n - n, 0)
    }
}