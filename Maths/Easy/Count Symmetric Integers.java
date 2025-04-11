// LeetCode Easy - 2843


// Approach 1 (Brute Force)
// T.C. - O(k.n); k = high - low, n = half the no. of digits in num
// S.C. - O(1)
class Solution {
    public boolean isSymmetric(int num){
        String str = String.valueOf(num);
        int m = str.length();

        if(m % 2 != 0){
            return false;
        }
        
        int n = m/2;
        int sum1 = 0;
        int sum2 = 0;

        for(int i = 0; i < n; i++){
            sum1 += str.charAt(i) - '0';
            sum2 += str.charAt(i+n) - '0';
        }

        return sum1 == sum2;
    }

    public int countSymmetricIntegers(int low, int high) {
        int count = 0;

        for(int i = low; i <= high; i++){
            if(isSymmetric(i)){
                count++;
            }
        }

        return count;
    }
}





// Approach 2
// T.C. - O(k); k = high - low + 1
// S.C. - O(1)
class Solution {
    public int countSymmetricIntegers(int low, int high) {
        int count = 0;

        for(int i = low; i <= high; i++){
            if(i >= 10 && i <= 99 && (i % 11 == 0)){ // 2 digits
                count++;
            }
            else if(i >= 1000 && i <= 9999){ // 4 digits
                int firstDigit = (i / 1000);
                int secondDigit = (i / 100) % 10;

                int thirdDigit = (i / 10) % 10;
                int fourthDigit = (i / 1) % 10;

                if(firstDigit + secondDigit == thirdDigit + fourthDigit){
                    count++;
                }
            }
        }

        return count;
    }
}