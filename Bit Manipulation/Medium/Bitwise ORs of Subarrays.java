// LeetCode - 898



// Brute Force Approach 1
// T.C. - O(n^2)
// S.C. - O(n^2)
class Solution {
    public int subarrayBitwiseORs(int[] arr) {
        int n = arr.length;
        Set<Integer> set = new HashSet<>();

        for(int i = 0; i<n; i++){
            int or = 0;

            for(int j = i; j<n; j++){
                or |= arr[j];

                set.add(or);
            }
        }

        return set.size();
    }
}





// Approach 2 - Using Set
// T.C. - O(n*32)
// S.C. - O(n*32)
class Solution {
    public int subarrayBitwiseORs(int[] arr) {
        int n = arr.length;
        Set<Integer> prev = new HashSet<>();
        Set<Integer> curr = new HashSet<>();
        Set<Integer> result = new HashSet<>();

        for(int i = 0; i<n; i++){

            for(int num : prev){ // O(32)
                curr.add(num | arr[i]);
                result.add(num | arr[i]);
            }

            // self element is also a subarray
            curr.add(arr[i]);
            result.add(arr[i]);

            prev = curr;
            curr = new HashSet<>();
        }

        return result.size();
    }
}