// LeetCode - 1461



// Approach 1 - Brute Force (By generating all binary substring of size k) - (Gives TLE)
// T.C. - O(2^k * k)
// S.C. - O(2^k * k)
class Solution {
    public void getAllSubstr(StringBuilder sb, int k, Set<String> set){
        if(sb.length() == k){
            set.add(sb.toString());
            return;
        }

        sb.append('1');
        getAllSubstr(sb, k, set);
        sb.deleteCharAt(sb.length() - 1);

        sb.append('0');
        getAllSubstr(sb, k, set);
        sb.deleteCharAt(sb.length() - 1);
    }

    public boolean hasAllCodes(String s, int k) {
        Set<String> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        getAllSubstr(sb, k, set);
        
        // for(String str : set){
        //     System.out.println(str);
        // }

        for(String str : set){
            if(!s.contains(str)){
                return false;
            }
        }

        return true;
    }
}






// Approach 2 - Generating all substrings in s + Prunning
// T.C. - O(n.k)
// S.C. - O(2^k * k)
class Solution {
    public boolean hasAllCodes(String s, int k) {
        int n = s.length();
        Set<String> set = new HashSet<>();

        for(int i = 0; i+k <= n; i++){
            String str = s.substring(i, i+k);
            set.add(str);

            if(set.size() == (1 << k)){ // prunning
                return true;
            }
        }

        return false;
    }
}