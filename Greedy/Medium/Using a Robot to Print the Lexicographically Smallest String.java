// LeetCode - 2434



// Approach 1 - Greedy approach using map/freqArray
// T.C. - O(n.26)
// S.C. - O(n)
class Solution {
    public char findMinChar(int[] freq){
        // take any char which is greater than 'z'; which could
        // act like infinity
        char ch = '{';

        for(int i = 0; i<26; i++){
            if(freq[i] > 0){
                ch = (char) (i + 'a');
                break;
            }
        }

        return ch;
    }

    public String robotWithString(String s) {
        int n = s.length();
        int[] freq = new int[26];

        for(int i = 0; i<n; i++){ // O(n)
            char ch = s.charAt(i);
            freq[ch - 'a']++;
        }
        
        StringBuilder t = new StringBuilder();
        StringBuilder result = new StringBuilder();
        char minCh = findMinChar(freq);

        // O(n)
        // Each char is added to t once and removed once
        for(int i = 0; i<n; i++){
            char currCh = s.charAt(i);
            freq[currCh - 'a']--;

            // pushing curr char to robot
            t.append(currCh);

            // updating min char
            minCh = findMinChar(freq);

            while(t.length() > 0 && t.charAt(t.length() - 1) <= minCh){
                result.append(t.charAt(t.length() - 1));
                t.deleteCharAt(t.length() - 1);
            }
        }

        // reverse the leftover in t and append in result
        t.reverse();
        result.append(t);

        return result.toString();
    }
}





// Approach 2 - Greedy approach using Stack
// T.C. - O(n.26)
// S.C. - O(n)
class Solution {
    public char findMinChar(int[] freq){
        // take any char which is greater than 'z'; which could
        // act like infinity
        char ch = '{';

        for(int i = 0; i<26; i++){
            if(freq[i] > 0){
                ch = (char) (i + 'a');
                break;
            }
        }

        return ch;
    }

    public String robotWithString(String s) {
        int n = s.length();
        int[] freq = new int[26];

        for(int i = 0; i<n; i++){ // O(n)
            char ch = s.charAt(i);
            freq[ch - 'a']++;
        }
        
        Stack<Character> t = new Stack<>();
        StringBuilder result = new StringBuilder();
        char minCh = findMinChar(freq);

        // O(n)
        // Each char is added to t once and removed once
        for(int i = 0; i<n; i++){
            char currCh = s.charAt(i);
            freq[currCh - 'a']--;

            // pushing curr char to robot
            t.push(currCh);

            // updating min char
            minCh = findMinChar(freq);

            while(!t.isEmpty() && t.peek() <= minCh){
                result.append(t.pop());
            }
        }

        // reverse the leftover in t and append in result
        while(!t.isEmpty()){
            result.append(t.pop());
        }

        return result.toString();
    }
}