// LeetCode Medium - 2182


// Approach 1 - Counting, StringBuilder
// T.C. - O(n + 26)
// S.C. - O(26)
class Solution {
    public String repeatLimitedString(String s, int repeatLimit) {
        int n = s.length();

        int[] charFrequency = new int[26];
        for(char ch : s.toCharArray()){ // O(n)
            charFrequency[ch-'a']++;
        }

        StringBuilder sb = new StringBuilder();
        int i = 25; // start from the last, so that we can get largest character first

        // O(n + some const.)
        while(i >= 0){ // O(26)
            if(charFrequency[i] == 0){
                i--;
                continue;
            }

            char ch = (char) ('a' + i); // for getting the character
            int frequency = Math.min(charFrequency[i], repeatLimit);

            // adding the character frequency number of times
            sb.append(String.valueOf(ch).repeat(frequency)); // O(n)
            charFrequency[i] -= frequency;

            if(charFrequency[i] > 0){
                // finding next largest character
                int j = i-1;

                while(j >= 0 && charFrequency[j] == 0){ // O(26)
                    j--;
                }

                if(j < 0){
                    break;
                }

                // sb.append(String.valueOf((char) ('a' + j)));
                sb.append((char) ('a' + j));
                charFrequency[j]--;
            }
        }

        return sb.toString();
    }
}



// Approach 2 - Heap, StringBuilder
// T.C. - O(n + 26*log(26))
// S.C. - O(26)
class Solution {
    public String repeatLimitedString(String s, int repeatLimit) {
        int n = s.length();

        int[] charFrequency = new int[26];
        for(char ch : s.toCharArray()){ // O(n)
            charFrequency[ch-'a']++;
        }

        // max-heap to store characters
        PriorityQueue<Character> pq = new PriorityQueue<>((a, b) -> Character.compare(b, a));

        for(int i = 0; i<26; i++){ // O(26)
            if(charFrequency[i] > 0){
                pq.offer((char) ('a' + i));
            }
        }

        StringBuilder sb = new StringBuilder();

        // O(n + Const.)
        while(!pq.isEmpty()){ // O(26)
            char ch = pq.poll(); // O(log(26))
            int frequency = Math.min(charFrequency[ch - 'a'], repeatLimit);

            // adding the character frequency number of times
            sb.append(String.valueOf(ch).repeat(frequency)); // O(n)
            charFrequency[ch - 'a'] -= frequency;

            if(charFrequency[ch - 'a'] > 0 && !pq.isEmpty()){
                // finding next largest character
                char nextChar = pq.poll();

                sb.append(nextChar);
                charFrequency[nextChar - 'a']--;

                if(charFrequency[nextChar - 'a'] > 0){
                    pq.offer(nextChar);
                }

                pq.offer(ch);
            }
        }

        return sb.toString();
    }
}