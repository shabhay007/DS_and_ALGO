// LeetCode Easy - 3375


// Approach 1 - Set
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public int minOperations(int[] nums, int k) {
        int n = nums.length;

        // total number of unique elements > k will the required min ops
        Set<Integer> set = new HashSet<>();

        for(int num : nums){
            if(num < k){
                return -1;
            }
            else if(num > k){
                set.add(num);
            }
        }

        return set.size();
    }
}