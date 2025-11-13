// LeetCode 3228



// Approach 1 - Simulation (Brute Force - Gives TLE)
// T.C. - O(n^2)
// S.C. - O(n)
class Solution {
    public void swap(int i, int j, char[] arr){
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public int maxOperations(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        int ops = 0;

        for(int i = 0; i<n-1; i++){
            int j = i+1;

            if(arr[i] == '1' && arr[j] == '0'){
                while(j < n && arr[j] == '0'){
                    j++;
                }

                swap(i, j-1, arr);
                ops++;

                // why -1, because if we set i = 0 then i get incremented
                // after if block ends and our objective is to reset i = 0
                i = -1;
            }
        }

        return ops;
    }
}





// Approach 2 - Counting
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int maxOperations(String s) {
        int n = s.length();
        int i = 0;
        int ops = 0;
        int countOf1Seen = 0;

        while(i < n){
            if(s.charAt(i) == '0'){
                ops += countOf1Seen;

                while(i < n && s.charAt(i) == '0'){
                    i++;
                }
            }
            else{
                countOf1Seen++;
                i++;
            }
        }

        return ops;
    }
}