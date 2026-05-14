// LeetCode - 2784



// Approach 1 - Map + Simulation
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public boolean isGood(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;

        for(int num : nums){
            max = Math.max(max, num);
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        if(n != max+1){
            return false;
        }
        
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            int element = entry.getKey();
            int count = entry.getValue();

            if(element < max && count != 1){
                return false;
            }
            else if(element == max && count != 2){
                return false;
            }
        }

        return true;
    }
}