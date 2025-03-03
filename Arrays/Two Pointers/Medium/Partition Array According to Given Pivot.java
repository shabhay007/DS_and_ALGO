// LeetCode Medium - 2161


// Approach 1
// T.C. - O(2n)
// S.C. - O(2n)
class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        int n = nums.length;
        List<Integer> list = new ArrayList<>();

        for(int i = 0; i<n; i++){
            if(nums[i] < pivot){
                list.add(nums[i]);
            }
        }

        for(int i = 0; i<n; i++){
            if(nums[i] == pivot){
                list.add(nums[i]);
            }
        }

        for(int i = 0; i<n; i++){
            if(nums[i] > pivot){
                list.add(nums[i]);
            }
        }

        int[] result = new int[list.size()];

        for(int i = 0; i<list.size(); i++){ // O(n)
            result[i] = list.get(i);
        }

        return result;
    }
}





// Approach 2 - Three Pointer
// T.C. - O(2n)
// S.C. - O(n)
class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        int n = nums.length;
        int[] result = new int[n];

        int countSmaller = 0;
        int countEqual = 0;

        // counting numbers smaller and equal to pivot
        for(int num : nums){
            if(num < pivot){
                countSmaller++;
            }
            else if(num == pivot){
                countEqual++;
            }
        }

        int smaller = 0;
        int equal = countSmaller;
        int greater = countSmaller + countEqual;

        // now placing the smaller, equal and greater elements in result
        for(int i = 0; i<n; i++){
            if(nums[i] < pivot){
                result[smaller++] = nums[i];
            }
            else if(nums[i] == pivot){
                result[equal++] = nums[i];
            }
            else{
                result[greater++] = nums[i];
            }
        }

        return result;
    }
}





// Approach 3 - Two Pointer Approach
// T.C. - O(2n)
// S.C. - O(n)
class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        int n = nums.length;
        int[] result = new int[n]; // O(n)

        int i = 0;
        int j = n-1;
        int left = 0;
        int right = n-1;

        // i is only for elements less than pivot
        // j is only for elements greater than pivot
        while(i < n && j >= 0){
            if(nums[i] < pivot){
                result[left++] = nums[i];
            }
            
            if(nums[j] > pivot){
                result[right--] = nums[j];
            }

            i++;
            j--;
        }

        while(left <= right){
            result[left++] = pivot;
        }

        return result;
    }
}