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





// Approach 2 - Array + Simulation
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public boolean isGood(int[] nums) {
        int n = nums.length;
        int max = 0;

        for(int num : nums){
            max = Math.max(max, num);
        }

        if(n != max+1){
            return false;
        }
        
        int[] freq = new int[201];
        for(int num : nums){
            freq[num]++;
        }

        // processing
        for(int i = 1; i<max; i++){
            if(freq[i] != 1) return false;
        }

        return freq[max] == 2;
    }
}