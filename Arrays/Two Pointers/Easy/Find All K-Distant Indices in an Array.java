// LeetCode - 2200



// Approach 1 - Brute Force
// T.C. - O(n^2)
// S.C. - O(1)
class Solution {
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        int n = nums.length;
        List<Integer> result = new ArrayList<>();

        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                if(nums[j] == key && Math.abs(i - j) <= k){
                    result.add(i);
                    break;
                }
            }
        }

        return result;
    }
}






// Approach 2 - Brute Force (Slightly better than Approach 1)
// T.C. - O(n^2)
// S.C. - O(n)
class Solution {
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        int n = nums.length;
        List<Integer> keyIndices = new ArrayList<>();
        List<Integer> result = new ArrayList<>();

        // storing indices of key
        for(int i = 0; i<n; i++){
            if(nums[i] == key){
                keyIndices.add(i);
            }
        }

        // now processing
        for(int i = 0; i<n; i++){
            for(int idx : keyIndices){
                if(Math.abs(i - idx) <= k){
                    result.add(i);
                    break;
                }
            }
        }

        return result;
    }
}




// Approach 3 (Optimal) - Two Pointers
// T.C. - O(2n); Every index is visited twice at most
// S.C. - O(1)
class Solution {
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        int n = nums.length;
        List<Integer> result = new ArrayList<>();

        for(int j = 0; j<n; j++){
            if(nums[j] == key){
                int start = Math.max(j-k, 0);
                int end = Math.min(j+k, n-1);

                if(result.size() > 0 && result.get(result.size() - 1) >= start){
                    start = result.get(result.size() - 1) + 1;
                }

                // now we got the start and the end, so fill it
                for(int i = start; i<=end; i++){
                    result.add(i);
                }
            }
        }

        return result;
    }
}