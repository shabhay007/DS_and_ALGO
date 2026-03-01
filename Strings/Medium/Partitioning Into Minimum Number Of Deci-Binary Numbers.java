// LeetCode - 1689



// Approach - 1
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int minPartitions(String n) {
        for(int num = 9; num >= 0; num--){
            if(n.contains(num + "")){
                return num;
            }
        }

        return 0;
    }
}






// Approach - 2
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int minPartitions(String n) {
        int max = 0;

        for(int i = 0; i<n.length(); i++){
            max = Math.max(max, n.charAt(i) - '0');
        }

        return max;
    }
}






// Approach - 3 (Brute Force)
// T.C. - O(n * d) ~ O(n); d = max digit (0 - 9)
// S.C. - O(n)
class Solution {
    public int minPartitions(String n) {
        char[] arr = n.toCharArray();
        int count = 0;

        while(true){
            boolean flag = false;

            for(int i = 0; i<arr.length; i++){
                if(arr[i] != '0'){
                    arr[i]--; // subtract by 1
                    flag = true;
                }
            }

            if(!flag){
                break;
            }

            count++;
        }

        return count;
    }
}