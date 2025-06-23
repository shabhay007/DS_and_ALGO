// LeetCode - 2081



// Approach 1 - Generating all palindromes (by Mirroing method)
// T.C. - O(n.10^(L/2) * O(logₖ(num))) (approx)
// S.C. - O(O(logₖ(num)))
class Solution {
    public String convertToBaseK(long num, int k){ // O(logₖ(num))
        StringBuilder sb = new StringBuilder();

        while(num > 0){
            sb.append(num % k);
            num = num/k;
        }

        // because of palindromic property, both works fine
        // return sb.reverse().toString();
        return sb.toString();
    }

    public boolean isPalindrome(String str){ // O(logₖ(num))
        int n = str.length();
        int i = 0;
        int j = n-1;

        while(i <= j){
            if(str.charAt(i) != str.charAt(j)){
                return false;
            }

            i++;
            j--;
        }

        return true;
    }

    public long kMirror(int k, int n) {
        long sum = 0;
        int length = 1; // to create L length palindromes

        while(n > 0){
            int halfLength = (length + 1)/2;

            // min num of length halfLength, i.e. L = 2, min = 10
            long minNum = (long) Math.pow(10, halfLength - 1);

            // max num of length halfLength, i.e. L = 2, min = 99
            long maxNum = (long) Math.pow(10, halfLength) - 1;

            for(long num = minNum; num <= maxNum; num++){
                String str = String.valueOf(num);

                String firstHalf = str;

                StringBuilder sb = new StringBuilder(str);
                sb.reverse();

                String secondHalf = sb.toString();

                String newStr = "";

                if(length % 2 == 0){
                    newStr = firstHalf + secondHalf;
                }
                else{
                    newStr = firstHalf + secondHalf.substring(1);
                }

                // converting the newStr to base k
                long newNum = Long.parseLong(newStr);
                String baseKNum = convertToBaseK(newNum, k);

                // checking if the baseKNum is palindrome or not
                if(isPalindrome(baseKNum)){
                    n--;
                    sum += newNum;

                    if(n == 0){
                        break;
                    }
                }
            }

            // incrementing length to find other lengths palindrome, i.e. 2, 3 ...
            length++;
        }

        return sum; // it will never get returned
    }
}