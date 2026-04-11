// LeetCode - 3741



// Approach 1 - Map (Same as 3740 with higher constraints)
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






// Approach 2 - Map (Same as 3740 with higher constraints)
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public int minimumDistance(int[] nums) {
        int n = nums.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        int minDistance = Integer.MAX_VALUE;

        /*
            abs(i - j) + abs(j - k) + abs(k - i)
            = j - i + k - j + k - i
            = 2(k - i)

            i.e. equation depends on k and i so we'll take k as last and i as 1st
            for processing as j is in mid somewhere
        */

        for(int k = 0; k<n; k++){
            map.putIfAbsent(nums[k], new ArrayList<>());
            map.get(nums[k]).add(k);

            if(map.get(nums[k]).size() >= 3){
                List<Integer> list = map.get(nums[k]);
                int size = list.size();
                int i = list.get(size - 3);
                minDistance = Math.min(minDistance, k - i);
            }
        }

        return minDistance == Integer.MAX_VALUE ? -1 : 2 * minDistance;
    }
}