// LeetCode - 3234



// Approach 1 (Brute Force) - Exploring All substrings (Gives TLE)
// T.C. - O(n^3)
// S.C. - O(1)
class Solution {
    public boolean isDominant(String str){
        int countOfZero = 0;
        int countOfOne = 0;

        for(int i = 0; i<str.length(); i++){
            if(str.charAt(i) == '0'){
                countOfZero++;
            }
            else{
                countOfOne++;
            }
        }

        return countOfOne >= (countOfZero * countOfZero);
    }

    public int numberOfSubstrings(String s) {
        int n = s.length();
        int result = 0;

        for(int i = 0; i<n; i++){
            for(int j = i; j<n; j++){
                String str = s.substring(i, j+1);

                if(isDominant(str)){
                    result++;
                }
            }
        }

        return result;
    }
}






// Approach 2 - Sliding Window (skipping index j wherever possible)
// T.C : Worst case O(n^2), but since we skip indices we get O(n*sqrt(n))
// S.C : O(n)
class Solution {
    public int numberOfSubstrings(String s) {
        int n = s.length();

        // counting 1 using cumm sum
        int[] cummOneCount = new int[n];
        cummOneCount[0] = (s.charAt(0) == '0') ? 0 : 1;

        for(int i = 1; i<n; i++){
            char ch = s.charAt(i);
            cummOneCount[i] = cummOneCount[i-1] + (ch == '0' ? 0 : 1);
        }

        int result = 0;

        for(int i = 0; i<n; i++){
            for(int j = i; j<n; j++){
                int one = cummOneCount[j] - ((i > 0) ? cummOneCount[i-1] : 0);
                int zero = (j-i+1) - one;
                int sq = zero * zero;

                if(sq > one){
                    // skipping j to avoid indices which is of no use
                    int wasteIndices = sq - one;

                    // now j is in correct position but loop will increment 1
                    // so subtracting 1
                    j += wasteIndices - 1;
                }
                else if(sq == one){
                    result++;
                }
                else{
                    // when one > zero * zero
                    // [i...j] is valid substring
                    result++;

                    // now checking how much j can shift right till substring
                    // remains dominant
                    int k = (int) Math.sqrt(one) - zero;
                    int next = j + k;

                    // out of bound
                    if(next >= n){
                        result += n-j-1;
                        break; // early break
                    }
                    else{
                        result += k;
                    }

                    j = next;
                }
            }
        }

        return result;
    }
}