// leetcode :- 2064

class Solution {
    public boolean possibleToDistribute(int x, int noOfShops, int[] arr){
        for(int products : arr){
            // noOfShops -= (int) Math.ceil((double) products/x); //Math.ceil is a very slow function

            //Alternative for Math.ceil - fast
            noOfShops -= (products + x - 1)/x;

            if(noOfShops < 0){
                return false;
            }
        }

        return true;
    }

    public int minimizedMaximum(int n, int[] quantities) {
        // int m = quantities.length;

        int left = 1;
        int right = Integer.MIN_VALUE;

        for(int num : quantities){
            right = Math.max(right, num);
        }

        int maxX = 0;
        while(left <= right){ //Binary Search
            int mid = left + (right - left)/2; //x

            if(possibleToDistribute(mid, n, quantities)){
                maxX = mid; //possible x
                right = mid - 1;
            }
            else{
                left = mid + 1;
            }
        }

        return maxX;
    }
}