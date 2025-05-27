// LeetCode - 2894



// Approach 1 (Brute Force) - Math
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int differenceOfSums(int n, int m) {
        int num1 = 0;
        int num2 = 0;

        for(int i = 1; i<=n; i++){
            if(i % m != 0){
                num1 += i;
            }
            else{
                num2 += i;
            }
        }

        return num1 - num2;
    }
}




// Approach 2 (Optimal) - Math
// T.C. - O(1)
// S.C. - O(1)
class Solution {
    public int differenceOfSums(int n, int m) {
        int totalSum = (n * (n + 1))/2;

        // floor(n/m) -> gives the count of numbers divisible by m in range (1, n)
        int k = n/m;
        int sumDivisibleByM = m * (k * (k + 1))/2;

        int sumNotDivisibleByM = totalSum - sumDivisibleByM;

        return sumNotDivisibleByM - sumDivisibleByM;
    }
}





// Approach 3 (Optimal) - Math (Simplified 2nd Approach)
// T.C. - O(1)
// S.C. - O(1)

/*
    totalSum (t) = n1 + n2
    n2 = m.(k.(k + 1))/2 (divisible by m)
    n1 = t - n2

    we have to find (n1 - n2)
    n1 - n2 = t - 2.n2
    reqSum = n.(n+1)/2 - 2m.k(k+1)/2
*/
class Solution {
    public int differenceOfSums(int n, int m) {
        // floor(n/m) -> gives the count of numbers divisible by m in range (1, n)
        int k = n/m;

        return (n * (n + 1))/2 - m * (k * (k + 1));
    }
}