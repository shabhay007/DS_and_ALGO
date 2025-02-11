// LeetCode Medium - 1910



// Brute Force 
// T.C. - O(n^3)
// S.C. - O(1);
class Solution {
    public String delete(int start, int end, String s){
        StringBuilder sb = new StringBuilder(s);

        sb.delete(start, start + end);

        return sb.toString();
    }

    public String removeOccurrences(String s, String part) {
        int n = s.length();
        int partLen = part.length();

        while(s.contains(part)){ // O(n^2)
            int start = -1;
            int j = 0;

            for(int i = 0; i<n; i++){ // O(n)
                char ch_i = s.charAt(i);
                char ch_j = part.charAt(j);

                if(start == -1 && ch_i == ch_j){
                    start = i;
                    j++;
                }
                else if(ch_i == ch_j){
                    j++;
                }
                else{
                    // reinstate the start and j because of mismatch
                    if(start != -1){
                        i = start;
                        start = -1;
                        j = 0;
                    }
                }

                if(j >= partLen){
                    s = delete(start, partLen, s);
                    n = s.length();
                    break;
                }
            }
        }

        return s;
    }
}





// Better
// T.C. - O(n^2)
// S.C. - O(1);
class Solution {
    public String delete(int start, int end, String s){
        StringBuilder sb = new StringBuilder(s);

        sb.delete(start, start + end);

        return sb.toString();
    }

    public String removeOccurrences(String s, String part) {
        int n = s.length();
        int partLen = part.length();

        while(s.contains(part)){ // O(n)
            int start = s.indexOf(part);
            s = delete(start, partLen, s);
        }

        return s;
    }
}





// Better 
// T.C. - O((n * 2m) + m)
// S.C. - O(2n);
class Solution {
    public Boolean check(String part, Stack<Character> stack){
        Stack<Character> temp = new Stack<>();
        temp.addAll(stack);

        for(int i = part.length() - 1; i >= 0; i--){ // O(m)
            char ch = part.charAt(i);

            if(ch != temp.peek()){
                return false;
            }

            temp.pop(); // in case of matching characters
        }

        return true;
    }

    public String removeOccurrences(String s, String part) {
        int n = s.length();
        int partLen = part.length();

        Stack<Character> stack = new Stack<>();

        for(int i = 0; i<n; i++){ // O(n)
            char ch = s.charAt(i);

            stack.push(ch);

            if(stack.size() >= partLen && check(part, stack)){ // O(m)
                // remove the characters from the stack
                for(int j = 0; j<partLen; j++){ // O(m)
                    stack.pop();
                }
            }
        }

        StringBuilder result = new StringBuilder();

        while(!stack.isEmpty()){ // O(m)
            result.insert(0, stack.pop());
        }

        return result.toString();
    }
}





// Better 
// T.C. - O((n * m))
// S.C. - O(n);
class Solution {
    public String removeOccurrences(String s, String part) {
        int n = s.length();
        int m = part.length();

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i<n; i++){ // O(n)
            char ch = s.charAt(i);

            sb.append(ch);

            if(sb.length() >= m){ // O(m)
                String sub = sb.substring(sb.length() - m);

                if(sub.equals(part)){
                    sb.delete(sb.length() - m, sb.length());
                }
            }
        }

        return sb.toString();
    }
}