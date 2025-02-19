// LeetCode Medium - 1415


// Brute Force - Recursion + Backtracking, set, sorting
// Generating all good strings
// T.C. - O(2^n + 2^n + 2^n.log(2^n))
// S.C. - O(n.2^n + 2^n)
class Solution {
    public void getAllPermutations(int n, StringBuilder sb, Set<String> set){
        if(sb.length() == n){
            set.add(sb.toString());
            return;
        }

        // at every step we have two char's to choose after choosing the first character
        // O(2^n)
        for(char ch = 'a'; ch <= 'c'; ch++){
            if(sb.length() == 0 || sb.charAt(sb.length() - 1) != ch){
                // action
                sb.append(ch);

                // explore
                getAllPermutations(n, sb, set);

                // backtrack
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    public String getHappyString(int n, int k) {
        Set<String> set = new HashSet<>();

        getAllPermutations(n, new StringBuilder(), set); // O(2^n)

        List<String> list = new ArrayList<>(set); // O(2^n)
        Collections.sort(list); // O(2^n.log(2^n))

        return (list.size() >= k) ? list.get(k-1) : "";
    }
}






// Better Approach 2 - Recursion + Backtracking
// Generating all good strings
// T.C. - O(2^n)
// S.C. - O(n + 2^n)
class Solution {
    public void getAllPermutations(int n, StringBuilder sb, List<String> list){
        if(sb.length() == n){
            list.add(sb.toString());
            return;
        }

        // at every step we have two char's to choose after choosing the first character
        // O(2^n)
        // there is no need of set and sorting as in APPROACH 1, as all the generated 
        // string will good and unique and lexicographically smallest because of 
        // the condition under if block
        for(char ch = 'a'; ch <= 'c'; ch++){
            if(sb.length() == 0 || sb.charAt(sb.length() - 1) != ch){
                // action
                sb.append(ch);

                // explore
                getAllPermutations(n, sb, list);

                // backtrack
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    public String getHappyString(int n, int k) {
        List<String> list = new ArrayList<>();

        getAllPermutations(n, new StringBuilder(), list); // O(2^n)

        return (list.size() >= k) ? list.get(k-1) : "";
    }
}






// Optimal Approach 3 - Recursion + Backtracking
// Generating good strings till kth good strings
// T.C. - O(2^n)
// S.C. - O(n)
class Solution {
    public String getKthPermutation(int n, StringBuilder sb, int[] k){
        if(sb.length() == n){
            k[0]--;
            
            if(k[0] == 0){
                return sb.toString();
            }

            return "";
        }

        // at every step we have two char's to choose after choosing the first character
        // O(2^n)
        // there is no need of set and sorting as in APPROACH 1, as all the generated 
        // string will good and unique and lexicographically smallest because of 
        // the condition under if block
        for(char ch = 'a'; ch <= 'c'; ch++){
            if(sb.length() == 0 || sb.charAt(sb.length() - 1) != ch){
                // action
                sb.append(ch);

                // explore
                String result = getKthPermutation(n, sb, k);

                if(!result.isEmpty()){
                    return result;
                }

                // backtrack
                sb.deleteCharAt(sb.length() - 1);
            }
        }

        return "";
    }

    public String getHappyString(int n, int k) {
        return getKthPermutation(n, new StringBuilder(), new int[]{k}); // O(2^n)
    }
}