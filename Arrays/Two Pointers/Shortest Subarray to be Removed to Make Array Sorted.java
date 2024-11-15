//leetcode - 1574. 

class Solution {
    public int findLengthOfShortestSubarray(int[] arr) {
        int n = arr.length;
        int j = n-1;

        //Finding j from right side from where all the elements in right side are sorted
        while(j > 0 && arr[j] >= arr[j-1]){
            j--;
        }

        int i = 0;

        //initializing len = j becuase j to n-1 are sorted and we may have to delete
        //elements starting from i to j-1
        int len = j;

        //starts finding correct i and j in order to find min length to delete
        while(i < j && (i == 0 || arr[i] >= arr[i-1])){
            while(j < n && arr[i] > arr[j]){
                j++;
            }

            len = Math.min(len, j-i-1);
            i++;
        }

        return len;
    }
}