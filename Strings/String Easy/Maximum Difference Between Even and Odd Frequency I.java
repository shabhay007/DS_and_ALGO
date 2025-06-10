// LeetCode - 3442



// Approach 1 - Hash Array
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int maxDifference(String s) {
        int n = s.length();
        int[] freq = new int[26];

        for(char ch : s.toCharArray()){
            freq[ch - 'a']++;
        }

        int maxOdd = 0;
        int minEven = Integer.MAX_VALUE;

        for(int count : freq){ // O(26)
            if(count % 2 != 0){
                maxOdd = Math.max(maxOdd, count);
            }
            else if(count > 0){ // this edge case check is very important
                minEven = Math.min(minEven, count);
            }
        }

        return maxOdd - minEven;
    }
}