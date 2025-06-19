// LeetCode - 2294



// Approach 1 - Greedy + Sorting
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int partitionArray(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int max = nums[0];
        int min = nums[0];
        int count = 1;

        for(int i = 1; i<n; i++){
            if(nums[i] == max){
                continue;
            }
            else if(nums[i] > max && nums[i] - min <= k){
                continue;
            }
            else{
                min = nums[i];
                max = nums[i];
                count++;
            }
        }

        return count;
    }
}




// Approach 2 - Greedy + Sorting (Slight change in approach 1)
// T.C. - O(nlog(n))
// S.C. - O(1)
class Solution {
    public int partitionArray(int[] nums, int k) {
        Arrays.sort(nums); // nlog(n)
        int n = nums.length;
        int max = nums[0];
        int min = nums[0];
        int count = 1;

        for(int i = 1; i<n; i++){ // O(n)
            if(nums[i] >= max && nums[i] - min <= k){
                continue;
            }
            else{
                min = nums[i];
                max = nums[i];
                count++;
            }
        }

        return count;
    }
}




// Approach 3 - Greedy + Sorting (Slight change in approach 2)
// T.C. - O(nlog(n))
// S.C. - O(1)
class Solution {
    public int partitionArray(int[] nums, int k) {
        Arrays.sort(nums); // nlog(n)
        int n = nums.length;
        int min = nums[0];
        int count = 1;

        for(int i = 1; i<n; i++){ // O(n)
            if(nums[i] - min > k){
                min = nums[i];
                count++;
            }
        }

        return count;
    }
}