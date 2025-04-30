// LeetCode - 1295



// Approach 1 - Using String conversion
// T.C. - O(n * d); d = avg. digits among all the numbers (d = log(num) (base 10))
// S.C. - O(d)
class Solution {
    public int findNumbers(int[] nums) {
        int count = 0;

        for(int num : nums){
            String str = String.valueOf(num);

            if(str.length() % 2 == 0){
                count++;
            }
        }

        return count;
    }
}




// Approach 2
// T.C. - O(n * d); d = avg. digits among all the numbers (d = log(num) (base 10))
// S.C. - O(1)
class Solution {
    public int countDigits(int num){
        int digits = 0;

        while(num > 0){
            num = num/10;
            digits++;
        }

        return digits;
    }

    public int findNumbers(int[] nums) {
        int count = 0;

        for(int num : nums){
            if(countDigits(num) % 2 == 0){
                count++;
            }
        }

        return count;
    }
}