// LeetCode Medium - 2845


// Approach 1 - Brute Force (Gives TLE)
// T.C. - O(n^3)
// S.C. - O(1)
class Solution {
    public boolean isValid(int start, int end, List<Integer> nums, int mod, int k){
        long count = 0;

        for(int i = start; i <= end; i++){
            if(nums.get(i) % mod == k){
                count++;
            }
        }

        return count % mod == k;
    }

    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        int n = nums.size();
        long result = 0;

        for(int i = 0; i<n; i++){
            for(int j = i; j<n; j++){
                if(isValid(i, j, nums, modulo, k)){
                    result++;
                }
            }
        }

        return result;
    }
}





// Approach 2 (Better) - Sliding Window
// T.C. - O(2n)
// S.C. - O(n)
class Solution {
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        int n = nums.size();
        int[] arr = new int[n];

        for(int i = 0; i<n; i++){
            if(nums.get(i) % modulo == k){
                arr[i] = 1;
            }
        }

        Map<Long, Long> map = new HashMap<>();
        map.put(0L, 1L);
        long sum = 0;
        long result = 0;

        for(int i = 0; i<n; i++){
            sum += arr[i];

            long r1 = sum % modulo;
            long r2 = (r1 - k + modulo) % modulo;

            result += map.getOrDefault(r2, 0L);

            map.put(r1, map.getOrDefault(r1, 0L) + 1);
        }

        return result;
    }
}






// Approach 3 (Optimal) - Prefix Sum + Map
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        int n = nums.size();
        Map<Long, Long> map = new HashMap<>();
        map.put(0L, 1L);
        long sum = 0;
        long result = 0;

        for(int i = 0; i<n; i++){
            if(nums.get(i) % modulo == k){
                sum += 1;
            }

            long r1 = sum % modulo;
            long r2 = (r1 - k + modulo) % modulo;

            result += map.getOrDefault(r2, 0L);

            map.put(r1, map.getOrDefault(r1, 0L) + 1);
        }

        return result;
    }
}