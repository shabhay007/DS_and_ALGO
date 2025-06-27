// LeetCode - 2311



// Approach 1 (Brute Force) - Recursion (Gives TLE)
// T.C. - O(2^n)
// S.C. - O(n)
class Solution {
    public boolean isLessOrEqual(String binaryStr, int k) {
        try{
            return Integer.parseInt(binaryStr, 2) <= k;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false; // in case binaryStr is too long
        }
    }

    public int getLongestBinarySub(int i, String curr, String s, int n, int k){
        if(i >= n){
            if(curr.isEmpty()) return 0;

            return (isLessOrEqual(curr, k)) ? curr.length() : 0;
        }

        int take = getLongestBinarySub(i+1, curr + s.charAt(i), s, n, k);
        int skip = getLongestBinarySub(i+1, curr, s, n, k);

        return Math.max(take, skip);
    }

    public int longestSubsequence(String s, int k) {
        int n = s.length();
        return getLongestBinarySub(0, "", s, n, k);
    }
}




// Approach 2 - DP (Memoization) (Gives TLE)
// T.C. - O(n . k)
// S.C. - O(n . k)
class Solution {
    public int getLongestBinarySub(int i, int value, String s, int k, Map<Long, Integer> dp){
        if (i == s.length()) return 0;

        long key = (((long) i) << 32) | value;
        if (dp.containsKey(key)) return dp.get(key);

        // skip
        int skip = getLongestBinarySub(i + 1, value, s, k, dp);

        // take if valid
        int take = 0;
        int digit = s.charAt(i) - '0';
        if ((long) value * 2 + digit <= k) {
            take = 1 + getLongestBinarySub(i + 1, value * 2 + digit, s, k, dp);
        }

        int res = Math.max(skip, take);
        dp.put(key, res);
        return res;
    }

    public int longestSubsequence(String s, int k) {
        Map<Long, Integer> dp = new HashMap<>();
        return getLongestBinarySub(0, 0, s, k, dp);
    }
}




//Approach-3 (Using Greedy)
//T.C : O(n)
//S.C : O(1)
class Solution {
    public int longestSubsequence(String s, int k) {
        int length = 0;
        long powerValue = 1;

        for (int i = s.length() - 1; i >= 0; --i) {
            if (s.charAt(i) == '0') {
                length++;
            } else if (powerValue <= k) {
                length++;
                k -= powerValue;
            }

            if (powerValue <= k) {
                powerValue <<= 1; // powerValue *= 2
            }
        }

        return length;
    }
}