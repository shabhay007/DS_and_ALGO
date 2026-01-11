// LeetCode - 84



// Approach 1 - Stack (NSL + NSR)
// T.C. - O(m*n)
// S.C. - O(n)
class Solution {
    // finding next smaller element to the right
    public int[] getNSR(int[] heights, int n){
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[n];

        for(int i = n-1; i >= 0; i--){
            while(!stack.isEmpty() && heights[stack.peek()] >= heights[i]){
                stack.pop();
            }

            result[i] = (!stack.isEmpty()) ? stack.peek() : n;
            stack.push(i);
        }

        return result;
    }

    // finding next smaller element to the left
    public int[] getNSL(int[] heights, int n){
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[n];

        for(int i = 0; i<n; i++){
            while(!stack.isEmpty() && heights[stack.peek()] >= heights[i]){
                stack.pop();
            }

            result[i] = (!stack.isEmpty()) ? stack.peek() : -1;
            stack.push(i);
        }

        return result;
    }

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;

        int[] NSR = getNSR(heights, n);
        int[] NSL = getNSL(heights, n);
        int maxArea = 0;

        for(int i = 0; i<n; i++){
            int width = NSR[i] - NSL[i] - 1;
            int area = heights[i] * width;
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }
}