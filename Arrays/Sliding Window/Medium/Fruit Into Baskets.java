// LeetCode - 904



// Brute Force - Gives TLE
// T.C. - O(n^2)
// S.C. - O(n);
class Solution {
    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        int maxNoOfFruits = 0;

        for(int i = 0; i<n; i++){
            HashSet<Integer> set = new HashSet<>();

            for(int j = i; j<n; j++){
                set.add(fruits[j]);

                if(set.size() <= 2){
                    maxNoOfFruits = Math.max(maxNoOfFruits, j-i+1);
                }
                else{
                    break;
                }
            }
        }

        return maxNoOfFruits;
    }
}





// Approach - Sliding window + Map
// T.C. - O(2n)
// S.C. - O(1)
class Solution {
    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        Map<Integer, Integer> map = new HashMap<>();
        int maxSize = 0;
        int i = 0;
        int j = 0;

        while(j < n){
            map.put(fruits[j], map.getOrDefault(fruits[j], 0) + 1);

            while(map.size() > 2){
                if(map.containsKey(fruits[i])){
                    map.put(fruits[i], map.get(fruits[i]) - 1);

                    if(map.get(fruits[i]) == 0){
                        map.remove(fruits[i]);
                    }
                }

                i++;
            }

            maxSize = Math.max(maxSize, j-i+1);
            j++;
        }

        return maxSize;
    }
}