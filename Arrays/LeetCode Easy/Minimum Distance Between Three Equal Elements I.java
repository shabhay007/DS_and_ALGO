// LeetCode - 3740



// Approach 1 - Brute Force
// T.C. - O(n^3)
// S.C. - O(1)
class Solution {
    public int minimumDistance(int[] nums) {
        int n = nums.length;

        int minDistance = Integer.MAX_VALUE;
        for(int i = 0; i<n; i++){
            for(int j = i+1; j<n; j++){
                for(int k = j+1; k<n; k++){
                    if(nums[i] == nums[j] && nums[j] == nums[k]){
                        int curr = k - i + k - j + j - i;
                        minDistance = Math.min(minDistance, curr);
                    }
                }
            }
        }

        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }
}






// Approach 2 - Map
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public int minimumDistance(int[] nums) {
        int n = nums.length;
        Map<Integer, List<Integer>> map = new HashMap<>();

        for(int i = 0; i<n; i++){
            map.putIfAbsent(nums[i], new ArrayList<>());
            map.get(nums[i]).add(i);
        }

        int minDistance = Integer.MAX_VALUE;
        for(List<Integer> list : map.values()){
            if(list.size() < 3){
                continue;
            }

            for(int l = 0; l<list.size() - 2; l++){
                int i = list.get(l);
                int j = list.get(l+1);
                int k = list.get(l+2);
                int curr = k - i + k - j + j - i;
                minDistance = Math.min(minDistance, curr);
            }

        }

        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }
}