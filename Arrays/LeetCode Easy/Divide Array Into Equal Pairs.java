// LeetCode Easy - 2206


// Approach 1 - Using Map
// T.C. - O(2N); N = 2 * n
// S.C. - O(N)
class Solution {
    public boolean divideArray(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i<nums.length; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        // checking if it can make n pairs
        for(int count : map.values()){
            if(count % 2 != 0){
                return false;
            }
        }

        return true;
    }
}



// Approach 2 - Using Set
// T.C. - O(N);
// S.C. - O(N)
class Solution {
    public boolean divideArray(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for(int num : nums){
            if(set.contains(num)){
                set.remove(num);
            }
            else{
                set.add(num);
            }
        }

        return set.isEmpty();
    }
}




// Approach 3 - Sorting & Comparing
// T.C. - O(N.log(N))
// S.C. - O(1)
class Solution {
    public boolean divideArray(int[] nums) {
        Arrays.sort(nums);

        for(int i = 1; i<nums.length; i += 2){
            if(nums[i-1] != nums[i]){
                return false;
            }
        }

        return true;
    }
}




// Approach 4 - Bit Manipulation
// T.C. - O(N.log(N))
// S.C. - O(1)
class Solution {
    public boolean divideArray(int[] nums) {
        Arrays.sort(nums);

        for(int i = 1; i<nums.length; i += 2){
            if((nums[i] ^ nums[i-1]) != 0){
                return false;
            }
        }

        return true;
    }
}