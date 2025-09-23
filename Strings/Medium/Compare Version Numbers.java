// LeetCode - 165



// Approach 1 - Conversion of String to Int
// T.C. - O(max(arr1, arr2))
// S.C. - O(1000) ~ O(1)
class Solution {
    public int compareVersion(String version1, String version2) {
        String[] arr1 = version1.split("\\.");
        String[] arr2 = version2.split("\\.");
        int len1 = arr1.length;
        int len2 = arr2.length;

        // // now comparing
        int size = Math.max(arr1.length, arr2.length);

        for(int i = 0; i<size; i++){
            int num1 = (i < len1) ? Integer.parseInt(arr1[i]) : 0;
            int num2 = (i < len2) ? Integer.parseInt(arr2[i]) : 0;

            if(num1 < num2){
                return -1;
            }
            else if(num1 > num2){
                return 1;
            }
        }

        return 0;
    }
}