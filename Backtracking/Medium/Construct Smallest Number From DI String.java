// LeetCode Medium - 2375


// Approach 1 - Recursion + Backtracking -> Gives TLE
// T.C. - O(n! + n.n!)
// S.C. - O(n! + 10)
class Solution {
    public void getPermutation(int len, StringBuilder sb, Set<String> set, boolean[] visited){
        if(sb.length() == len){
            set.add(sb.toString());
            return;
        }

        for(int num = 1; num <= 9; num++){
            if(!visited[num]){
                visited[num] = true;
                sb.append(num);

                getPermutation(len, sb, set, visited);

                visited[num] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    public String smallestNumber(String pattern) {
        int n = pattern.length();
        int len = n+1;
        Set<String> set = new HashSet<>();
        boolean visited[] = new boolean[10];

        getPermutation(len, new StringBuilder(), set, visited); // generating all permutations

        String result = "";
        
        // checking for the valid ones
        for(String str : set){
            boolean flag = true;
            char nums[] = str.toCharArray();

            for(int i = 0; i<n; i++){
                char ch = pattern.charAt(i);

                int numI = nums[i] - '0';
                int numIpOne = nums[i+1] - '0';

                if((ch == 'I' && numI > numIpOne) || (ch == 'D' && numI < numIpOne)){
                    flag = false;
                    break;
                }
            }

            if(flag && (result.isEmpty() || Integer.parseInt(str) < Integer.parseInt(result))){
                result = str;
            }
        }

        // System.out.println(result);

        return result;
    }
}






// Approach 2 - Recursion + Backtracking
// T.C. - O(n!)
// S.C. - O(3n); used, nums, recursion stack
class Solution {
    public boolean isValid(int prevNum, int currNum, char prevChar){
        if((prevChar == 'I' && currNum > prevNum) || (prevChar == 'D' && currNum < prevNum)){
            return true;
        }

        return false;
    }

    public boolean getNumber(int i, String pattern, boolean[] used, int[] nums, int n){
        if(i == n+1){
            return true;
        }

        for(int num = 1; num <= 9; num++){
            if((!used[num]) && (i == 0 || isValid(nums[i-1], num, pattern.charAt(i-1)))){
                used[num] = true;
                nums[i] = num;

                // explore
                if(getNumber(i+1, pattern, used, nums, n)){
                    return true;
                }

                // backtrack
                used[num] = false;
                nums[i] = 0;
            }
        }

        return false;
    }

    public String smallestNumber(String pattern) {
        int n = pattern.length();
        boolean[] used = new boolean[10];
        StringBuilder sb = new StringBuilder();
        int nums[] = new int[n+1];

        getNumber(0, pattern, used, nums, n);

        for(int num : nums){
            sb.append(num);
        }

        return sb.toString();
    }
}






// Approach 3 - By validating next permutation
// T.C. - O(2n + n.n!)
// S.C. - O(n)
class Solution {
    public void swap(int i, int j, int[] nums){ // O(1)
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void getNextPermutation(int[] nums){ // O(n)
        int n = nums.length;

        // step 1 : finding the 1st idx where nums[i-1] < nums[i]
        int idx = -1;
        
        for(int i = n-2; i >= 0; i--){
            if(nums[i] < nums[i+1]){
                idx = i;
                break;
            }
        }


        // step 2 : finding the element which has to be swapped with
        if(idx != -1){
            int swapIdx = -1;

            for(int i = n-1; i >= idx+1; i--){
                if(nums[idx] < nums[i]){
                    swapIdx = i;
                    break;
                }
            }

            // step 4 : swap
            if(swapIdx != -1){
                swap(idx, swapIdx, nums);
            }
        }

        // step 5 : reverse the elements after the idx to make smallest 
        // but larger from the previous permutation
        int start = idx + 1;
        int end = n-1;

        while(start <= end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;

            start++;
            end--;
        }
    }

    public boolean isValid(int nums[], String pattern){ // O(n)
        int n = pattern.length();

        for(int i = 0; i<n; i++){
            char ch = pattern.charAt(i);

            if((ch == 'I' && nums[i] > nums[i+1]) || (ch == 'D' && nums[i] < nums[i+1])){
                return false;
            }
        }

        return true;
    }

    public String smallestNumber(String pattern) {
        int n = pattern.length();

        int[] nums = new int[n+1];

        for(int i = 0; i <= n; i++){ // O(n)
            nums[i] = i+1;
        }

        // we might go for n! permutations
        while(!isValid(nums, pattern)){ // O(n)
            getNextPermutation(nums); // O(n!); in worst case n! permutations can be generated
        }

        StringBuilder sb = new StringBuilder();

        for(int num : nums){ // O(n)
            sb.append(num);
        }

        return sb.toString();
    }
}






// Optimal Approach - Using Stack
// T.C. - O(2n)
// S.C. - O(n+1)
class Solution {
    public String smallestNumber(String pattern) {
        int n = pattern.length();

        Stack<Integer> stack = new Stack<>();
        int num = 1;
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i<=n; i++){
            stack.push(num);
            num++;

            if(i == n || pattern.charAt(i) == 'I'){
                while(!stack.isEmpty()){
                    sb.append(stack.pop());
                }
            }
        }

        return sb.toString();
    }
}