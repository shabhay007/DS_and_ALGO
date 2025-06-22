// LeetCode - 2138



// Approach 1 - String Simulation
// T.C. - O(n)
// S.C. - O(k)
class Solution {
    public String[] divideString(String s, int k, char fill) {
        int n = s.length();
        int groups = (n+k-1) / k;
        String[] str = new String[groups];

        int m = 0;

        for(int i = 0; i<groups; i++){
            StringBuilder sb = new StringBuilder();

            for(int j = 0; j<k; j++){
                if(m < n){
                    sb.append(s.charAt(m++));
                }
                else{
                    sb.append(fill);
                }
            }

            str[i] = sb.toString();
        }

        return str;
    }
}





// Approach 2 - String Simulation
// T.C. - O(n)
// S.C. - O(k)
class Solution {
    public String[] divideString(String s, int k, char fill) {
        int n = s.length();
        List<String> result = new ArrayList<>();

        for(int i = 0; i<n; i+=k){
            int end = Math.min(i+k, n);
            StringBuilder sb = new StringBuilder(s.substring(i, end));

            // in case of last group size < k
            while(sb.length() < k){
                sb.append(fill);
            }

            result.add(sb.toString());
        }

        return result.toArray(new String[result.size()]);
    }
}