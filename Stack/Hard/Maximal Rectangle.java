// LeetCode - 85



// Approach 1 - Stack (NSL + NSR)
// T.C. - O(m*n)
// S.C. - O(n)
class Solution {
    // finding next smaller element to the left
    public int[] NSL(int[] row, int n){
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[n];

        for(int i = 0; i<n; i++){
            while(!stack.isEmpty() && row[stack.peek()] >= row[i]){
                stack.pop();
            }

            result[i] = (!stack.isEmpty()) ? stack.peek() : -1;
            stack.push(i);
        }

        return result;
    }

    // finding next smaller element to the right
    public int[] NSR(int[] row, int n){
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[n];

        for(int i = n-1; i>=0; i--){
            while(!stack.isEmpty() && row[i] <= row[stack.peek()]){
                stack.pop();
            }

            result[i] = (!stack.isEmpty()) ? stack.peek() : n;
            stack.push(i);
        }

        return result;
    }

    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] height = new int[n];
        int maxArea = 0;

        for(int i = 0; i<m; i++){
            // calculating height by accumulating every row
            for(int j = 0; j<n; j++){
                if(matrix[i][j] == '1'){
                    height[j] += 1;
                }
                else{
                    height[j] = 0;
                }
            }

            // calculating width for every row
            int[] nextSmallerToLeft = NSL(height, n);
            int[] nextSmallerToRight = NSR(height, n);

            for(int j = 0; j<n; j++){
                int width = nextSmallerToRight[j] - nextSmallerToLeft[j] - 1;
                int area = height[j] * width;
                maxArea = Math.max(maxArea, area);
            }
        }

        return maxArea;
    }
}