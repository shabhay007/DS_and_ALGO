// LeetCode - 2154



// Approach 1 - Set
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public int findFinalValue(int[] nums, int original) {
        Set<Integer> set = new HashSet<>();

        for(int num : nums){
            set.add(num);
        }

        while(set.contains(original)){
            original *= 2;
        }

        return original;
    }
}





// Approach 2 - Sorting
// T.C. - O(nlog(n) + n)
// S.C. - O(1)
class Solution {
    public int findFinalValue(int[] nums, int original) {
        int n = nums.length;
        Arrays.sort(nums);

        for(int i = 0; i<n; i++){
            if(nums[i] == original){
                original *= 2;
            }
        }

        return original;
    }
}