// LeetCode Easy - 1863


// Approach 1 - Backtracking
// T.C. - O(2^n * l); l = avg length of all subsets
// S.C. - O(2n)
class Solution {
    public void subsetXOR(int i, int[] nums, int n, List<Integer> list, int[] xorTotal){
        if(i == n){
            int xor = 0;

            for(int j = 0; j<list.size(); j++){
                xor ^= list.get(j);
            }

            xorTotal[0] += xor;

            return;
        }

        list.add(nums[i]);
        subsetXOR(i + 1, nums, n, list, xorTotal);
        list.remove(list.size() - 1);

        // skip
        subsetXOR(i + 1, nums, n, list, xorTotal);
    }

    public int subsetXORSum(int[] nums) {
        int n = nums.length;
        int[] xorTotal = new int[1];
        List<Integer> list = new ArrayList<>();

        subsetXOR(0, nums, n, list, xorTotal);

        return xorTotal[0];
    }
}




// Approach 2 - Space improvement on Approach 1
// T.C. - O(2^n)
// S.C. - O(n)
class Solution {
    public int subsetXOR(int i, int[] nums, int n, int xor){
        if(i == n){
            return xor;
        }

        int take = subsetXOR(i + 1, nums, n, xor ^ nums[i]);

        int skip = subsetXOR(i + 1, nums, n, xor);

        return take + skip;
    }

    public int subsetXORSum(int[] nums) {
        int n = nums.length;

        return subsetXOR(0, nums, n, 0);
    }
}




// Approach 3 (MIK) - Observation based (not easy to come up with this solution)
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int subsetXORSum(int[] nums) {
        int n = nums.length;

        int or = 0;

        // step 1 : finding or of all elements
        for(int i = 0; i<n; i++){
            or |= nums[i];
        }

        // step 2 : appending (n-1) 0's at the last of the or value
        // and to do so, left shift operator(<<) is used
        or = (or << (n-1));

        return or;

    }
}