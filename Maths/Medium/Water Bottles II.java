// LeetCode - 3100



// Approach 1 - Simulation
// T.C. - O(n); n = numBottles
// S.C. - O(1)
class Solution {
    public int maxBottlesDrunk(int numBottles, int numExchange) {
        int result = numBottles;
        int emptyBottles = numBottles;

        while(emptyBottles >= numExchange){
            result++;
            emptyBottles = emptyBottles - numExchange + 1;
            numExchange++;
        }

        return result;
    }
}