// LeetCode - 1518



// Approach 1 - Simulation
// T.C. - O(log (base n) n); n = numExchange
// S.C. - O(1)
class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        int result = numBottles;
        int emptyBottles = numBottles;

        while(emptyBottles >= numExchange){
            result += emptyBottles/numExchange;
            emptyBottles = (emptyBottles/numExchange) + (emptyBottles % numExchange);
        }

        return result;
    }
}