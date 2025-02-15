// LeetCode Medium - 2698



// Recursion
// T.C. - O(n * nlog(n)) = O(n^2 * log(n))
// T.C. - O(log(n))
class Solution {
    public boolean isPossible(int i, String str, int currSum, int target){
        if(i >= str.length()){
            return currSum == target;
        }

        if(currSum > target){
            return false;
        }

        boolean possible = false;

        for(int j = i; j < str.length(); j++){ // O(num of digits (d)) ~ O(log(n))
            String substr = str.substring(i, j+1);
            int value = Integer.parseInt(substr);

            if(possible || isPossible(j+1, str, currSum + value, target)){ // O(2^d)
                return true;
            }
        }

        return false;
    }

    public int punishmentNumber(int n) {
        int punishmentNumber = 0;

        for(int num = 1; num <= n; num++){ // O(n)
            long square = num*num;

            String str = Long.toString(square); // O(log(n))

            // O(d * 2^d) = O(log(n) * 2^log(n)) = O(nlog(n))
            if(isPossible(0, str, 0, num)){ // O(d * 2^d)
                punishmentNumber += square;
            }
        }

        return punishmentNumber;
    }
}






// Recursion + Memoization(DP)
// T.C. - O(n * 2^log(n^2))
// T.C. - O(n * 2^log(n^2)) + O(2^log(n^2)) (recursion stack)
class Solution {
    public boolean isPossible(int i, String str, int currSum, int target, Boolean dp[][]){
        if(i >= str.length()){
            return currSum == target;
        }

        if(currSum > target){
            return false;
        }

        if(dp[i][currSum] != null){
            return dp[i][currSum];
        }

        boolean possible = false;

        for(int j = i; j < str.length(); j++){
            String substr = str.substring(i, j+1);
            int value = Integer.parseInt(substr);

            if(possible || isPossible(j+1, str, currSum + value, target, dp)){
                return dp[i][currSum] = true;
            }
        }

        return dp[i][currSum] = possible;
    }

    public int punishmentNumber(int n) {
        int punishmentNumber = 0;

        for(int num = 1; num <= n; num++){ // O(n)
            long square = num*num; // total no of digits - log(square) = log(n^2)

            String str = Long.toString(square); // log(square)

            Boolean dp[][] = new Boolean[str.length()][num+1];

            // we have two options for every digit, O(2^log(n^2))
            if(isPossible(0, str, 0, num, dp)){
                punishmentNumber += square;
            }
        }

        return punishmentNumber;
    }
}







// Recursion
// T.C. - O(n * 2^log10(n^2))
// T.C. - O(2^log10(n^2)); recursion stack
class Solution {
    public boolean isPossible(int square, int currSum, int target){
        if(square == 0){
            return currSum == target;
        }

        if(currSum > target){
            return false;
        }

        return (isPossible(square/10, currSum + square%10, target) ||
                isPossible(square/100, currSum + square%100, target) ||
                isPossible(square/1000, currSum + square%1000, target) ||
                isPossible(square/10000, currSum + square%10000, target));
    }

    public int punishmentNumber(int n) {
        int punishmentNumber = 0;

        for(int num = 1; num <= n; num++){ // O(n)
            int square = num*num; // total no of digits - log10(square) = log10(n^2)

            // we have two options for every digit, O(2^log10(n^2))
            if(isPossible(square, 0, num)){
                punishmentNumber += square;
            }
        }

        return punishmentNumber;
    }
}