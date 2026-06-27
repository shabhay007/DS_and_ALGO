// LeetCode Medium - 3020



// Approach 1 - Map + Enumeration
// T.C. - O(n * log(log(maxEl)))
// S.C. - O(n)
class Solution {
    public int maximumLength(int[] nums) {
        Map<Long, Integer> map = new HashMap<>();

        for(int num : nums){
            map.put((long) num, map.getOrDefault((long) num, 0) + 1);
        }

        int result = 0;
        if(map.containsKey(1L)){
            if(map.get(1L) % 2 == 0){
                result = map.get(1L) - 1;
            }
            else{
                result = map.get(1L);
            }
        }

        // now taking every element from the map as x and will try to find it's 
        // square in the map
        for(Map.Entry<Long, Integer> entry : map.entrySet()){
            long x = entry.getKey();

            if(x == 1){
                continue;
            }

            int freq = entry.getValue();

            long curr = x;
            int len = 0;

            while(map.containsKey(curr) && map.get(curr) >= 2){
                len += 2;
                curr *= curr;
            }

            if(map.containsKey(curr) && map.get(curr) == 1){
                len++; // it will be our peak
            }
            else{
                len--; // it can't be paired so it will be left alone
            }

            // updating result
            result = Math.max(result, len);
        }

        return result;
    }
}