// LeetCode - 3542



// Approach 1 - Brute Force (Gives TLE)
// T.C. - O(n^2)
// S.C. - O(n)
class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();

        for(int num : nums){
            set.add(num);
        }

        int ops = 0;

        for(int target : set){
            if(target == 0){
                continue;
            }

            boolean flag = false;

            for(int i = 0; i<n; i++){
                if(nums[i] == target){
                    if(!flag){
                        flag = true;
                        ops++;
                    }
                }
                else if(nums[i] < target){
                    flag = false;
                }
            }
        }

        return ops;
    }
}





// Approach 2 - Monotonic Stack
// T.C. - O(2n)
// S.C. - O(n)
class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();
        int ops = 0;
        
        for(int i = 0; i<n; i++){
            // maintaining monotonic nature
            while(!stack.isEmpty() && stack.peek() > nums[i]){
                stack.pop();
            }

            // skipping 0's
            // why here & not above while loop?
            // if 0 get encountered which is strictly lower element
            // then we have to pop the greater elements to get the desired
            // result
            if(nums[i] == 0){
                continue;
            }

            // adding increasing element
            if(stack.isEmpty() || stack.peek() < nums[i]){
                ops++;
                stack.push(nums[i]);
            }
        }

        return ops;
    }
}





// Approach 3 - Monotonic Deque
// T.C. - O(2n)
// S.C. - O(n)
class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int ops = 0;
        
        for(int i = 0; i<n; i++){
            // maintaining monotonic nature
            while(!stack.isEmpty() && stack.peek() > nums[i]){
                stack.pop();
            }

            // skipping 0's
            if(nums[i] == 0){
                continue;
            }

            // adding increasing element
            if(stack.isEmpty() || stack.peek() < nums[i]){
                ops++;
                stack.push(nums[i]);
            }
        }

        return ops;
    }
}