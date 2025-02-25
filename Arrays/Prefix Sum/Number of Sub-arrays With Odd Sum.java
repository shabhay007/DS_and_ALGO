// LeetCode Medium - 1524


// Brute Force - Gives TLE
// T.C. - O(n^2)
// S.C. - O(1)
class Solution {
    public int numOfSubarrays(int[] arr) {
        int n = arr.length;
        int count = 0;
        int mod = (int) 1e9 + 7;

        for(int i = 0; i<n; i++){
            int sum = 0;

            for(int j = i; j<n; j++){
                sum += arr[j];

                if(sum % 2 != 0){
                    count++;
                }
            }
        }

        return count % mod;
    }
}






// Approach 2 - Prefix Sum
// T.C. - O(2n)
// S.C. - O(n)
class Solution {
    public int numOfSubarrays(int[] arr) {
        int n = arr.length;
        int mod = (int) 1e9 + 7;

        int[] prefixSum = new int[n];
        int subarraySum = 0;

        for(int i = 0; i<n; i++){
            subarraySum += arr[i];

            prefixSum[i] = subarraySum;
        }

        int evenCount = 1; // as 0 is evenSum, so evenSumCount will always be 1
        int oddCount = 0;
        int count = 0;

        // odd + even = odd;
        // even + odd = odd;
        for(int sum : prefixSum){
            if(sum % 2 == 0){
                count = (count + oddCount) % mod;
                evenCount++; // found one evenSum, so increment evenCount
            }
            else{
                count = (count + evenCount) % mod;
                oddCount++; // found one oddSum, so increment oddCount
            }
        }

        return count;
    }
}





// Approach 3 - Prefix Sum
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int numOfSubarrays(int[] arr) {
        int n = arr.length;
        int mod = (int) 1e9 + 7;

        int evenCount = 1; // as 0 is evenSum, so evenSumCount will always be 1
        int oddCount = 0;
        int count = 0;
        int sum = 0;

        // odd + even = odd;
        // even + odd = odd;
        for(int i = 0; i<n; i++){
            sum += arr[i];

            if(sum % 2 == 0){
                count = (count + oddCount) % mod;
                evenCount++; // found one evenSum, so increment evenCount
            }
            else{
                count = (count + evenCount) % mod;
                oddCount++; // found one oddSum, so increment oddCount
            }
        }

        return count;
    }
}