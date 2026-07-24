// LeetCode - 3514



// Approach 1 - Brute Force
// T.C. - O(n^3)
// S.C. - O(maxElement)
class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;

        Set<Integer> set = new HashSet<>();

        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                for(int k = 0; k<n; k++){
                    set.add(nums[i] ^ nums[j] ^ nums[k]);
                }
            }
        }

        return set.size();
    }
}





// Approach 2 - Bit Manipulation + Set (It will give TLE)

/*
    HashSet implementation was the bottleneck on LeetCode's Java judge.
    Every iteration does:

    compute hash
    check bucket
    compare equality
    possible resize/rehash
    object overhead (Integer boxing)

    You perform roughly:

    2.25 million set.add(...)
    ~3 million result.add(...)

    That's over 5 million HashSet insertions.

    Even though the number of unique values is at most 2048, you're still calling add() millions of times.
*/

// T.C. - O(n^2 + maxEl * n)
// S.C. - O(2 * maxEl)
class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;

        Set<Integer> set = new HashSet<>();

        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                set.add(nums[i] ^ nums[j]);
            }
        }

        Set<Integer> result = new HashSet<>();
        for(int xor : set){
            for(int i = 0; i<n; i++){
                result.add(xor ^ nums[i]);
            }
        }

        return result.size();
    }
}






// Approach 3 - Boolean Array
// T.C. - O(n² + 2048·n)
// S.C. - O(2048)
class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;

        // Stores all possible nums[i] ^ nums[j]
        boolean[] pairXor = new boolean[2048];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                pairXor[nums[i] ^ nums[j]] = true;
            }
        }

        // Stores all possible (nums[i] ^ nums[j]) ^ nums[k]
        boolean[] tripletXor = new boolean[2048];

        for (int xor = 0; xor < 2048; xor++) {
            if (!pairXor[xor]) {
                continue;
            }

            for (int i = 0; i < n; i++) {
                tripletXor[xor ^ nums[i]] = true;
            }
        }

        int count = 0;
        for (boolean present : tripletXor) {
            if (present) {
                count++;
            }
        }

        return count;
    }
}