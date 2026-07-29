// LeetCode - 3518



// Approach - Maths
// T.C. - O(n^2)
// S.C. - O(n)
class Solution {
    public long nCr(int n, int r, int k){
        // nCr == nC(n-r)
        // 5C3 == 5C2
        // 5C2 == 5C(5-2) = 5C3
        r = Math.min(r, n-r); // nCr == nC(n-r)

        long result = 1;
        for(int i = 1; i<=r; i++){ // O(n)
            result = result * (n - r + i)/i;

            if(result >= k){
                return k;
            }
        }

        return result;
    }

    public String smallestPalindrome(String s, int k) {
        int n = s.length();
        char mid = ' ';

        if(n % 2 == 1){ // given that s is pallindrome
            mid = s.charAt(n/2);
        }

        int[] count = new int[26];
        for(int i = 0; i<n; i++){ // O(n)
            char ch = s.charAt(i);
            count[ch - 'a']++;
        }

        // half frequency will be used to build half result
        for(int i = 0; i<26; i++){
            count[i] /= 2;
        }

        StringBuilder sb = new StringBuilder();
        int half = n/2;

        for(int i = 0; i<half; i++){
            // trying to fill the ith position
            // what if we could never fill a character in ith position
            boolean placedChar = false; // in ith position

            for(int j = 0; j<26; j++){
                if(count[j] > 0){
                    count[j] -= 1;

                    // now counting no. of ways
                    long ways = 1;
                    int letters = 0;

                    for(int c = 0; c<26; c++){
                        letters += count[c];
                    }

                    for(int c = 0; c<26; c++){
                        if(count[c] > 0){
                            ways *= nCr(letters, count[c], k); // O(log2(k))
                            letters -= count[c];
                        }

                        if(ways >= k){
                            break; // early break
                        }
                    }

                    if(ways >= k){ // this section contains kth SPR
                        sb.append((char) (j + 'a')); // fixed this char at ith position
                        placedChar = true;
                        break;
                    }

                    // when k >= ways
                    k -= ways;
                    count[j] += 1;
                }
            }

            if(placedChar == false){
                return "";
            }
        }

        // half result + mid + reverse(half result);
        StringBuilder halfResult = new StringBuilder(sb);

        if(mid != ' '){
            sb.append(mid);
        }

        return sb.append(halfResult.reverse()).toString();
    }
}