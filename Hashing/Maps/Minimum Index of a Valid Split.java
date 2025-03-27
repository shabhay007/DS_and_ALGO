// LeetCode Medium - 2780


// Approach 1 - Using Map (Gives TLE)
// T.C. - O(n^2)
// S.C. - O(n)
class Solution {
    public int[] getDominantElement(int start, int end, List<Integer> list){
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = start; i <= end; i++){
            map.put(list.get(i), map.getOrDefault(list.get(i), 0) + 1);
        }

        // getting the dominant element
        int[] element = new int[2];

        for(int key : map.keySet()){
            if(element[1] < map.get(key)){
                element[0] = key;
                element[1] = map.get(key);
            }
        }

        return element;
    }

    public int minimumIndex(List<Integer> nums) {
        int n = nums.size();

        for(int i = 0; i<n-1; i++){
            int[] element1 = getDominantElement(0, i, nums);
            int[] element2 = getDominantElement(i+1, n-1, nums);

            if(element1[0] == element2[0]){
                if((element1[1] > (i-0+1)/2) && (element2[1] > (n-i-1)/2)){
                    return i;
                }
            }
        }

        return -1;
    }
}




// Approach 2 - Using 2 Maps
// T.C. - O(2n)
// S.C. - O(2n)
class Solution {
    public int minimumIndex(List<Integer> nums) {
        int n = nums.size();

        Map<Integer, Integer> map2 = new HashMap<>();

        for(int i = 0; i < n; i++){
            int num = nums.get(i);
            map2.put(num, map2.getOrDefault(num, 0) + 1);
        }

        Map<Integer, Integer> map1 = new HashMap<>();

        for(int i = 0; i < n; i++){
            int num = nums.get(i);

            map1.put(num, map1.getOrDefault(num, 0) + 1);
            map2.put(num, map2.getOrDefault(num, 0) - 1);

            int n1 = i+1;
            int n2 = n-i-1;

            if(map1.get(num) > n1/2 && map2.get(num) > n2/2){
                return i;
            }
        }

        return -1;
    }
}




// Approach 4 - Majority Element + prefix sum concept
// T.C. - O(3n)
// S.C. - O(1)
class Solution {
    public int minimumIndex(List<Integer> nums) {
        int n = nums.size();

        // step 1 : finding majority element using Boore-Moyre Voting Algorithm
        int maj = -1;
        int count = 0;
        
        for(int i = 0; i<n; i++){
            if(count == 0){
                maj = nums.get(i);
                count = 1;
            }
            else if(nums.get(i) == maj){
                count++;
            }
            else{
                count--;
            }
        }

        // step 2 : finding the count of majority element
        int freq = 0;
        for(int i = 0; i<n; i++){
            if(nums.get(i) == maj){
                freq++;
            }
        }

        // step 3 : finding the valid split index
        int leftCount = 0;
        int rightCount = freq;
        
        for(int i = 0; i<n; i++){
            if(nums.get(i) == maj){
                leftCount++;

                rightCount--; 
            }

            int n1 = i+1;
            int n2 = n-i-1;

            if(leftCount * 2 > n1 && rightCount * 2 > n2){
                return i;
            }
        }

        return -1;
    }
}