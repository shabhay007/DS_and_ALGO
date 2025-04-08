// LeetCode Easy - 3396



// Approach 1 (Brute Force)
// T.C. - O(n^2)
// S.C. - O(n)
class Solution {
    public boolean check(int start, int[] nums, int n){
        Set<Integer> set = new HashSet<>();

        for(int i = start; i<n; i++){
            if(set.contains(nums[i])){
                return false;
            }

            set.add(nums[i]);
        }

        return true;
    }

    public int minimumOperations(int[] nums) {
        int n = nums.length;
        int ops = 0;

        for(int i = 0; i<n; i += 3){
            if(check(i, nums, n)){
                return ops;
            }

            ops++;
        }

        return ops;
    }
}





// Approach 2 - using hash array
// T.C. - O(n)
// S.C. - O(101)
class Solution {
    public int minimumOperations(int[] nums) {
        int n = nums.length;
        int[] hash = new int[101];
        int duplicateElementIdx = 0;

        for(int i = n-1; i >= 0; i--){
            hash[nums[i]]++;

            if(hash[nums[i]] > 1){
                duplicateElementIdx = i+1;
                break;
            }
        }

        if(duplicateElementIdx % 3 == 0){
            return duplicateElementIdx/3;
        }

        return duplicateElementIdx/3 + 1;
    }
}





// Approach 3
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public int minimumOperations(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();

        for(int i = n-1; i >= 0; i--){
            if(set.contains(nums[i])){
                return (int) Math.ceil((i+1)/3.0);
            }

            set.add(nums[i]);
        }

        return 0;
    }
}