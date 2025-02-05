// LeetCode Easy - 1790


// Brute Force
// T.C. - O(n^2 + n)
// S.C. - O(1)
class Solution {
    public boolean areAlmostEqual(String s1, String s2) {
        int n = s1.length();

        // if character is not present
        for(int i = 0; i<n; i++){ // O(n^2)
            if(!s2.contains(String.valueOf(s1.charAt(i)))){
                return false;
            }
        }

        // if character is present, then it should have only 2
        // position with differing characters
        int count = 0;
        int first = -1;
        int second = -1;

        for(int i = 0; i < n; i++){
            if(s1.charAt(i) != s2.charAt(i)){
                count++;
                
                if(count == 1) first = i;
                if(count == 2) second = i;
                if(count > 2) return false;
            }
        }

        // if count is 2 then return true
        if(count == 2){
            return s1.charAt(first) == s2.charAt(second) && s1.charAt(second) == s2.charAt(first);
        }

        return count == 0;
    }
}





// Better
// T.C. - O(2n)
// S.C. - O(26)
class Solution {
    public boolean areAlmostEqual(String s1, String s2) {
        int n = s1.length();

        HashSet<Character> charSet = new HashSet<>();

        // adding characters in the set
        for(int i = 0; i<n; i++){ // O(n)
            charSet.add(s1.charAt(i));
        }

        // if character is present, then it should have only 2
        // position with differing characters
        int count = 0;
        int first = -1;
        int second = -1;

        for(int i = 0; i < n; i++){
            if(!charSet.contains(s2.charAt(i))){
                return false;
            }

            if(s1.charAt(i) != s2.charAt(i)){
                count++;

                if(count == 1) first = i;
                if(count == 2) second = i;
                if(count > 2) return false;
            }
        }

        // if count is 2 and alternate characters if equal then it will return true
        if(count == 2){
            return s1.charAt(first) == s2.charAt(second) && s1.charAt(second) == s2.charAt(first);
        }

        return count == 0;
    }
}





// Optimal
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public boolean areAlmostEqual(String s1, String s2) {
        int n = s1.length();

        // if character is present, then it should have only 2
        // position with differing characters
        int count = 0;
        int first = 0;
        int second = 0;

        for(int i = 0; i < n; i++){
            if(s1.charAt(i) != s2.charAt(i)){
                count++;

                if(count == 1) first = i;
                if(count == 2) second = i;
                if(count > 2) return false;
            }
        }

        // if count is 2 and alternate characters if equal then it will return true
        return s1.charAt(first) == s2.charAt(second) && s1.charAt(second) == s2.charAt(first);
    }
}