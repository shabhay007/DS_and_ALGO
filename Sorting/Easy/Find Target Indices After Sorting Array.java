// LeetCode - 2089



// Approach 1 - Sorting
// T.C. - O(nlog(n))
// S.C. - O(1)
class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int n = nums.length;

        Arrays.sort(nums);

        List<Integer> list = new ArrayList<>();
        for(int i = 0; i<n; i++){
            if(nums[i] == target){
                list.add(i);
            }
        }

        return list;
    }
}