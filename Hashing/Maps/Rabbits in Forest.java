// LeetCode Medium - 781



// Approach 1 - Using Map
// T.C. - O(2n)
// S.C. - O(n)
class Solution {
    public int numRabbits(int[] answers) {
        int n = answers.length;
        Map<Integer, Integer> map = new HashMap<>();

        // how many rabbits have answered same color (key), say x
        // and group size = (x + 1)
        for(int i = 0; i<n; i++){
            map.put(answers[i], map.getOrDefault(answers[i], 0) + 1);
        }

        // now processing each rabbit i.e. which rabbit belongs to which group
        int totalRabbits = 0;

        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            int x = entry.getKey();
            int countOfRabbits = entry.getValue();

            // how many groups will get formed so that all rabbits 
            // gets accommodated
            int groups = (int) Math.ceil(countOfRabbits/(x + 1.0));

            // total rabbits = total groups * size of the group
            totalRabbits += groups * (x+1);
        }

        return totalRabbits;
    }
}