// LeetCode Easy - 1475


// Approach 1 - Brute Force
// T.C. - O(n^2); constraints are small, so it will be accepted
// S.C. - O(1)
class Solution {
    public int[] finalPrices(int[] prices) {
        int n = prices.length;

        for(int i = 0; i<n; i++){
            for(int j = i+1; j<n; j++){
                if(prices[j] <= prices[i]){
                    prices[i] = prices[i] - prices[j];
                    break;
                }
            }
        }

        return prices;
    }
}



// Approach 2 - Monotonic Stack
// T.C. - O(2 * n)
// S.C. - O(n)
class Solution {
    public int[] finalPrices(int[] prices) {
        int n = prices.length;

        int result[] = new int[n];
        for(int i = 0; i<n; i++){
            result[i] = prices[i];
        }

        Stack<Integer> stack = new Stack<>(); // Monotonic Stack

        // O(2 * n) as we are visiting every element 2 times
        // first, while iterating and 2nd, while removing element from stack
        for(int i = 0; i<n; i++){
            // this while loop is responsible for monotonic nature
            // whether it is increasing or decreasing
            while(!stack.isEmpty() && prices[i] <= prices[stack.peek()]){
                result[stack.peek()] = prices[stack.peek()] - prices[i];
                stack.pop();
            }

            stack.push(i);
        }

        return result;
    }
}