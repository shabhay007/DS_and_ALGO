// LeetCode Easy - 1346


// Brute Force Approach
class Solution {
    public boolean checkIfExist(int[] arr) {
        int n = arr.length;

        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                if(i != j && arr[i] == 2 * arr[j]){
                    return true;
                }
            }
        }

        return false;
    }
}


// Approach 2 - Optimal :- Using HashSet
class Solution {
    public boolean checkIfExist(int[] arr) {
        int n = arr.length;
        HashSet<Integer> set = new HashSet<>();

        for(int num : arr){
            if(set.contains(num * 2) || (num % 2 == 0 && set.contains(num/2))){
                return true;
            }

            set.add(num);
        }

        return false;
    }
}