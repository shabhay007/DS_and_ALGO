// LeetCode - 1957



// Approach 1 - Using Map
// T.C. - O(2n)
// S.C. - O(2n)
class Solution {
    public String makeFancyString(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        Map<Integer, Character> map = new HashMap<>();

        for(int i = 0; i<n - 2; i++){
            char ch1 = s.charAt(i);
            char ch2 = s.charAt(i+1);
            char ch3 = s.charAt(i+2);

            if(ch1 == ch2 && ch2 == ch3){
                map.put(i, ch1);
            }
        }


        for(int i = 0; i<n; i++){
            if(!map.containsKey(i)){
                sb.append(s.charAt(i));
            }
        }

        return sb.toString();
    }
}





// Approach 2 - Using Set
// T.C. - O(2n)
// S.C. - O(2n)
class Solution {
    public String makeFancyString(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        Set<Integer> set = new HashSet<>();

        for(int i = 0; i<n - 2; i++){
            char ch1 = s.charAt(i);
            char ch2 = s.charAt(i+1);
            char ch3 = s.charAt(i+2);

            if(ch1 == ch2 && ch2 == ch3){
                set.add(i);
            }
        }


        for(int i = 0; i<n; i++){
            if(!set.contains(i)){
                sb.append(s.charAt(i));
            }
        }

        return sb.toString();
    }
}






// Approach 3 - Simulation
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public String makeFancyString(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        sb.append(s.charAt(0));

        int freq = 1;

        for(int i = 1; i<n; i++){
            if(sb.charAt(sb.length() - 1) == s.charAt(i)){
                freq++;

                if(freq < 3){
                    sb.append(s.charAt(i));
                }
            }
            else{
                sb.append(s.charAt(i));
                freq = 1;
            }
        }

        return sb.toString();
    }
}