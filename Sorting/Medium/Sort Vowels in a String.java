// LeetCode - 2785



// Approach 1 - Using Heap
// T.C. - O(nlog(n))
// S.C. - O(n)
class Solution {
    public boolean isVowel(char ch){
        return (ch == 'a') || (ch == 'e') || (ch == 'i') || (ch == 'o') || (ch == 'u') || (ch == 'A') || (ch == 'E') || (ch == 'I') || (ch == 'O') || (ch == 'U');
    }

    public String sortVowels(String s) {
        int n = s.length();
        PriorityQueue<Character> pq = new PriorityQueue<>();

        int i = 0;

        while(i < n){ // O(n)
            char ch = s.charAt(i);

            if(isVowel(ch)){
                pq.offer(ch); // O(log(n))
            }

            i++;
        }

        i = 0;
        StringBuilder sb = new StringBuilder(s);

        while(!pq.isEmpty() && i < n){
            char ch = s.charAt(i);

            if(isVowel(ch)){
                sb.setCharAt(i, pq.poll());
            }

            i++;
        }

        return sb.toString();
    }
}




// Approach 2 - Using Sorting
// T.C. - O(nlog(n))
// S.C. - O(n)
class Solution {
    public boolean isVowel(char ch){
        return (ch == 'a') || (ch == 'e') || (ch == 'i') || (ch == 'o') || (ch == 'u') || (ch == 'A') || (ch == 'E') || (ch == 'I') || (ch == 'O') || (ch == 'U');
    }

    public String sortVowels(String s) {
        int n = s.length();
        ArrayList<Character> list = new ArrayList<>();

        int i = 0;

        while(i < n){ // O(n)
            char ch = s.charAt(i);

            if(isVowel(ch)){
                list.add(ch);
            }

            i++;
        }

        // sorting
        Collections.sort(list); // O(nlog(n))

        i = 0; // for traversing in original string
        int j = 0; // for traversing in sorted list
        StringBuilder sb = new StringBuilder(s);

        while(i < n && j < list.size()){ // O(n)
            char ch = s.charAt(i);

            if(isVowel(ch)){
                sb.setCharAt(i, list.get(j));
                j++;
            }

            i++;
        }

        return sb.toString();
    }
}