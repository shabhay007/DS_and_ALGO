// LeetCode - 3289



// Approach 1 - Map
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public int[] getSneakyNumbers(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int num : nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int result[] = new int[2];

        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            int key = entry.getKey();
            int value = entry.getValue();

            if(value == 2 && result[0] == 0){
                result[0] = key;
            }
            else if(value == 2 && result[1] == 0){
                result[1] = key;
                break;
            }
        }

        return result;
    }
}





// Approach 2 - Sorting
// T.C. - O(nlog(n))
// S.C. - O(1)
class Solution {
    public int[] getSneakyNumbers(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        int[] result = new int[2];

        for(int i = 1, j = 0; i < n; i++){
            if(nums[i] == nums[i-1]){
                result[j++] = nums[i];
            }
        }

        return result;
    }
}