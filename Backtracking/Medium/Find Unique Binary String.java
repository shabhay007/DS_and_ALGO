// LeetCode Medium - 1980



// Approach 1 - Backtracking (Checking all combinations)
// T.C. - O(n + 2^n)
// S.C. - O(n + n)
class Solution {
    public String getBinaryString(int idx, StringBuilder sb, int n, HashSet<String> set){
        if(idx == n){
            if(!set.contains(sb.toString())){
                return sb.toString();
            }

            return "";
        }

        for(char ch = '0'; ch <= '1'; ch++){
            sb.append(ch);

            // explore
            String str = getBinaryString(idx+1, sb, n, set);

            if(!str.isEmpty() && !set.contains(str)){
                return str;
            }

            // backtrack
            sb.deleteCharAt(sb.length() - 1);
        }

        return "";
    }

    public String findDifferentBinaryString(String[] nums) {
        int m = nums.length;
        int n = nums[0].length();

        HashSet<String> set = new HashSet<>(); // O(n); space

        for(String str : nums){ // O(n)
            set.add(str);
        }

        return getBinaryString(0, new StringBuilder(), n, set); // O(2^n)
    }
}





// Approach 2 - Simulation
// T.C. - O(n^2)
// S.C. - O(n)
class Solution {
    public String findDifferentBinaryString(String[] nums) {
        int n = nums.length;

        HashSet<Integer> set = new HashSet<>();

        // storing the decimal value of strings in nums after 
        // converting the strings (binary to decimal)
        for(String str : nums){
            set.add(Integer.parseInt(str, 2));
        }

        String result = "";

        for(int num = 0; num < (int) Math.pow(2, n); num++){ // O(n)

            // if decimal num is not present in the set, it means it could be 
            // an answer
            // so, convert it binary string and return
            if(!set.contains(num)){
                result = Integer.toBinaryString(num); // O(n)

                while(result.length() < n){ // if result's length is less, pad it with "0"
                    result = "0" + result;
                }

                return result;
            }
        }

        return "";
    }
}






// Approach 3 - Slight Optimization in Approach 2 (Loop will run only n times and not 2^n)
// T.C. - O(n^2)
// S.C. - O(n)
class Solution {
    public String findDifferentBinaryString(String[] nums) {
        int n = nums.length;

        HashSet<Integer> set = new HashSet<>();

        // storing the decimal value of strings in nums after 
        // converting the strings (binary to decimal)
        for(String str : nums){
            set.add(Integer.parseInt(str, 2));
        }

        String result = "";

        for(int num = 0; num <= n; num++){ // Slight Optimization

            // if decimal num is not present in the set, it means it could be 
            // an answer
            // so, convert it binary string and return
            if(!set.contains(num)){
                result = Integer.toBinaryString(num); // O(n)

                while(result.length() < n){ // if result's length is less, pad it with "0"
                    result = "0" + result;
                }

                return result;
            }
        }

        return "";
    }
}






// Most Optimal - Cantor's Diagonalization Argument or Diagonal Construction Method
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public String findDifferentBinaryString(String[] nums) {
        int n = nums.length;
        StringBuilder result = new StringBuilder();

        for(int i = 0; i<n; i++){
            char ch = (nums[i].charAt(i) == '0') ? '1' : '0';

            result.append(ch);
        }

        return result.toString();
    }
}