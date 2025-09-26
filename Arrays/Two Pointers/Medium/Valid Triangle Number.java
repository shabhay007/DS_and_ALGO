// LeetCode - 611



// Approach 1 - Using concept of sum of two side > third side (Gives TLE)
// T.C. - O(n^3)
// S.C. - O(1)
class Solution {
    public int triangleNumber(int[] nums) {
        int n = nums.length;
        int count = 0;

        for(int i = 0; i<n; i++){
            for(int j = i+1; j<n; j++){
                for(int k = j+1; k<n; k++){

                    // sum of two sides should be greater than the third side
                    // to be a valid triangle
                    if(nums[i] + nums[j] > nums[k] && 
                       nums[i] + nums[k] > nums[j] && 
                       nums[j] + nums[k] > nums[i]){
                        count++;
                    }
                }
            }
        }

        return count;
    }
}





// Approach 2 - Sorting + Binary Search
// T.C. - O(nlog(n) + n^2.log(n))
// S.C. - O(1)

/*
    Because of sorting,
    nums = {2, 3, 4, 4, 4, 4, 5, 6}, i+j > k then it is obivious that i+k>j and 
    j+k>i

    And we need not to use other checks like previous approach

*/

class Solution {
    public int getValidTripletCount(int l, int r, int[] nums, int sum){
        int result = -1;

        /*
            nums = {2, 3, 4, 4, 4, 4, 5, 6}
            i = 2, j = 3, we need to find x in right till nums[i] + nums[j] > x

            in this eg. from index 2 to 5, all can form a valid triplet with
            i and j

            so to get the count, do (mid - j)
        */

        while(l <= r){
            int mid = l + (r - l)/2;

            if(nums[mid] < sum){
                result = mid;
                l = mid + 1;
            }
            else{
                r = mid - 1;
            }
        }

        return result;
    }

    public int triangleNumber(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int result = 0;

        for(int i = 0; i<n; i++){
            if(nums[i] == 0){
                continue;
            }

            for(int j = i+1; j<n; j++){
                int sum = nums[i] + nums[j];

                int count = getValidTripletCount(j+1, n-1, nums, sum);
                
                if(count != -1){
                    // do (mid - j) - Read above comments
                    result += count - j;
                }
            }
        }

        return result;
    }
}





// Approach 3 (Most Optimal) - Two Pointers
// T.C. - O(n^2)
// S.C. - O(1)

/*
    Because of sorting,
    nums = {2, 3, 4, 4, 4, 4, 5, 6}, i+j > k then it is obivious that i+k>j and 
    j+k>i

    And we need not to use other checks like approach 1

*/

class Solution {
    public int triangleNumber(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int result = 0;

        // k will point greater elements i.e. from last
        for(int k = n-1; k >= 0; k--){
            int i = 0;
            int j = k-1;

            while(i < j){
                if(nums[i] + nums[j] > nums[k]){
                    result += j - i;
                    j--;
                }
                else{
                    i++;
                }
            }
        }

        return result;
    }
}