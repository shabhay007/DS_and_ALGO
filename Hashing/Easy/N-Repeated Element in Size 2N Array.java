// LeetCode - 961



// Approach 1 - Sorting
// T.C. - O(nlog(n) + n)
// S.C. - O(1)
class Solution {
    public int repeatedNTimes(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        for(int i = 1; i<n; i++){
            if(nums[i] == nums[i-1]){
                return nums[i];
            }
        }

        return -1;
    }
}





// Approach 2 - Set
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public int repeatedNTimes(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for(int num : nums){
            if(set.contains(num)){
                return num;
            }
            else{
                set.add(num);
            }
        }

        return -1;
    }
}