// LeetCode - 3875



// Approach 1 - Brute Force
// T.C. - O(n^2)
// S.C. - O(n)
class Solution {
    public boolean uniformArray(int[] nums1) {
        int n = nums1.length;
        int[] result = new int[n];
        boolean flag = false;

        // building even result
        for(int i = 0; i<n; i++){
            flag = false;

            for(int j = 0; j < n; j++){
                if(nums1[i] % 2 == 0){
                    result[i] = nums1[i];
                    flag = true;
                }
                else if(j != i && (nums1[i] - nums1[j]) % 2 == 0){
                    result[i] = nums1[i] - nums1[j];
                    flag = true;
                }
            }

            if(!flag){
                break;
            }
        }

        if(flag){
            return true;
        }


        // building odd result
        for(int i = 0; i<n; i++){
            flag = false;

            for(int j = 0; j < n; j++){
                if(nums1[i] % 2 != 0){
                    result[i] = nums1[i];
                    flag = true;
                    break;
                }
                else if(j != i && (nums1[i] - nums1[j]) % 2 != 0){
                    result[i] = nums1[i] - nums1[j];
                    flag = true;
                    break;
                }
            }

            if(!flag){
                return false;
            }
        }

        return flag;
    }
}






// Approach 2 - Brute Force
// T.C. - O(n^2)
// S.C. - O(1)
class Solution {
    public boolean uniformArray(int[] nums1) {
        int n = nums1.length;
        int even = 0;
        int odd = 0;

        // building even result
        for(int i = 0; i<n; i++){
            boolean canMakeEven = false;
            boolean canMakeOdd = false;

            for(int j = 0; j<n; j++){
                if(nums1[i] % 2 == 0 || (j != i && (nums1[i] - nums1[j]) % 2 == 0)){
                    canMakeEven = true;
                }
                
                if(nums1[i] % 2 != 0 || (j != i && (nums1[i] - nums1[j]) % 2 != 0)){
                    canMakeOdd = true;
                }
            }

            if(canMakeEven){
                even++;
            }

            if(canMakeOdd){
                odd++;
            }
        }

        return even == n || odd == n;
    }
}






// Approach 3 - Maths
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public boolean uniformArray(int[] nums1) {
        int n = nums1.length;

        /*
            * even - even → even
            * odd - odd → even
            * even - odd → odd
            * odd - even → odd

            So we only need to know whether the array 
            contains at least one even and/or one odd.
        */

        boolean hasEven = false;
        boolean hasOdd = false;

        for(int i = 0; i<n; i++){
            if(nums1[i] % 2 == 0){
                hasEven = true;
            }
            else{
                hasOdd = true;
            }
        }

        if(!hasEven || !hasOdd){ // already uniform
            return true;
        }

        return true;
    }
}






// Approach 4 - Most Optimal (Maths)
// T.C. - O(1)
// S.C. - O(1)
class Solution {
    public boolean uniformArray(int[] nums1) {
        /*
            An even number can use an odd number → becomes odd.
            An odd number can use an even number → becomes odd.
            Therefore, if both parities exist, we can make everything odd.
        */
        return true;
    }
}
