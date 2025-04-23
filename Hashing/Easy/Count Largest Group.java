// LeetCode Easy - 1399



// Approach 1 - Math + Map
// T.C. - O(2n + n.log(digits))
// S.C. - O(n)
class Solution {
    public int sumOfDigits(int n){
        int sum = 0;

        while(n > 0){
            int digit = n % 10;
            sum += digit;
            n = n/10;
        }

        return sum;
    }

    public int countLargestGroup(int n) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        for(int i = 1; i <= n; i++){ // O(n)
            int sum = sumOfDigits(i); // O(log(digits))

            map.putIfAbsent(sum, new ArrayList<>());
            map.get(sum).add(i);
        }

        int maxSize = 0;

        for(List<Integer> list : map.values()){
            maxSize = Math.max(maxSize, list.size());
        }

        // now finding the number of groups
        int count = 0;
        for(List<Integer> list : map.values()){
            if(list.size() == maxSize){
                count++;
            }
        }

        return count;
    }
}




// Approach 2 - Math + Map
// T.C. - O(n.log(digits))
// S.C. - O(n)
class Solution {
    public int sumOfDigits(int n){
        int sum = 0;

        while(n > 0){
            int digit = n % 10;
            sum += digit;
            n = n/10;
        }

        return sum;
    }

    public int countLargestGroup(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        int maxSize = 0;
        int count = 0;

        for(int i = 1; i <= n; i++){ // O(n)
            int sum = sumOfDigits(i); // O(log(digits))

            map.put(sum, map.getOrDefault(sum, 0) + 1);

            if(map.get(sum) == maxSize){
                count++;
            }
            else if(map.get(sum) > maxSize){
                maxSize = map.get(sum);
                count = 1;
            }
        }

        return count;
    }
}