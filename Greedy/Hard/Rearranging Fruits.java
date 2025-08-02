// LeetCode - 2561



// Approach 1 - Greedy + Sorting + Map
// T.C. - O(n + m + nlog(n))
// S.C. - O(m + n); m = distinct elements in map
class Solution {
    public long minCost(int[] basket1, int[] basket2) {
        int n = basket1.length;
        Map<Integer, Integer> map = new HashMap<>(); // O(m)
        int minElement = Integer.MAX_VALUE;

        for(int num : basket1){
            map.put(num, map.getOrDefault(num, 0) + 1);
            minElement = Math.min(minElement, num);
        }

        // finding the extra elements which we need to balance
        for(int num : basket2){
            map.put(num, map.getOrDefault(num, 0) - 1);
            minElement = Math.min(minElement, num);
        }

        // now putting those extra elements in a list
        List<Integer> list = new ArrayList<>(); // O(n)

        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            int key = entry.getKey(); // cost
            int count = entry.getValue();

            if(count % 2 != 0){
                return -1;
            }

            for(int i = 1; i <= Math.abs(count/2); i++){
                list.add(key);
            }
        }

        // after getting the final list, sort the list
        Collections.sort(list);

        // now finding the min cost
        long result = 0;

        for(int i = 0; i<list.size()/2; i++){
            result += Math.min(list.get(i), minElement * 2);
        }

        return result;
    }
}