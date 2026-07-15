// LeetCode - 3658



// Approach 1 - Maths
// T.C. - O(n * log(min(num1, num2)))
// S.C. - O(1)
class Solution {
    public int gcd(int num1, int num2){
        while(num2 != 0){
            int temp = num2;
            num2 = num1 % num2;
            num1 = temp;
        }

        return num1;
    }

    public int gcdOfOddEvenSums(int n) {
        int oddSum = 0;
        int evenSum = 0;

        int odd = 1;
        int even = 2;

        for(int i = 0; i<n; i++){
            oddSum += odd;
            odd += 2;

            evenSum += even;
            even += 2;
        }

        // System.out.println("odd - " + oddSum + " even - " + evenSum);

        return gcd(oddSum, evenSum);
    }
}






// Approach 2 - Maths
// T.C. - O(1)
// S.C. - O(1)
class Solution {
    public int gcdOfOddEvenSums(int n) {
        /** 
            oddSum = 1 + 3 + 5 + ... + (2n-1) = n^2
            evenSum = 2 + 4 + 6 + ... + 2n = n(n+1)
            GCD(n^2, n(n+1)) = n * GCD(n, n+1) = n * 1 = n
        */
        
        return n;
    }
}