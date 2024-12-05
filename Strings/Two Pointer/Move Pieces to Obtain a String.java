// LeetCode Medium - 2337


// Brute force - gives TLE - Recursion (Checking all the possibilities), Memoization
// T.C : Exponential in the worst case due to exploring all possible swaps, 
// though memoization reduces redundant computations.
// S.C : Memoization map to store all possible states. ~ O(n^2) states possible
class Solution {
    public String swap(String str, int i, int j){
        char[] arr = str.toCharArray();
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

        return new String(arr);
    }

    public boolean solve(String start, String target, HashMap<String, Boolean> map, int n){
        if(start.equals(target)){
            return true;
        }

        //Memoization
        if(map.containsKey(start)){
            return map.get(start);
        }

        for(int i = 0; i<n; i++){
            if(start.charAt(i) == 'L' && i > 0 && start.charAt(i-1) == '_'){
                String newStr1 = swap(start, i, i-1);

                if(solve(newStr1, target, map, n) == true){
                    return true;
                }
            }
            else if(start.charAt(i) == 'R' && i < n-1 && start.charAt(i+1) == '_'){
                String newStr2 = swap(start, i, i+1);

                if(solve(newStr2, target, map, n) == true){
                    return true;
                }
            }
        }

        // storing the result
        map.put(start, false);

        return false;
    }

    public boolean canChange(String start, String target) {
        int n = start.length();

        // For Memoization
        HashMap<String, Boolean> map = new HashMap<>();

        return solve(start, target, map, n);
    }
}




// Optimal - Two Pointer
// T.C - O(n) - Iterating every character only once
// S.C - O(1) - No extra space is used
class Solution {
    public boolean canChange(String start, String target) {
        int n = start.length();
        int i = 0;
        int j = 0;

        while(i < n || j < n){
            // skipping the '_' in start
            while(i < n && start.charAt(i) == '_'){
                i++;
            }

            // skipping the '_' in target
            while(j < n && target.charAt(j) == '_'){
                j++;
            }

            // Checking if both pointers have reached the end
            if(i == n || j == n) return i == n && j == n;

            // If the characters at i and j are different, transformation is not possible
            if(start.charAt(i) != target.charAt(j)) return false;

            // Check for invalid moves
            // 'L' can't move to right
            if(start.charAt(i) == 'L' && i < j) return false;

            // 'R' can't move to left
            if(start.charAt(i) == 'R' && i > j) return false;

            i++;
            j++;
        }

        return true;
    }
}
