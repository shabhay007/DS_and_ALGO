// LeetCode - 2751



// Approach 1 - Greedy (Sorting) + Stack
// T.C. - O(nlog(n))
// S.C. - O(n)
class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = healths.length;
        List<int[]> list = new ArrayList<>();

        for(int i = 0; i<n; i++){
            if(directions.charAt(i) == 'R'){
                list.add(new int[]{positions[i], healths[i], 1, i});
            }
            else{
                list.add(new int[]{positions[i], healths[i], -1, i});
            }
        }

        Collections.sort(list, (a, b) -> a[0] - b[0]);

        Stack<int[]> stack = new Stack<>();
        for(int[] curr : list){
            int position = curr[0];
            int health = curr[1];
            int dir = curr[2];
            int idx = curr[3];

            // handling collision chaining
            while(!stack.isEmpty() && stack.peek()[2] == 1 && dir == -1){
                int[] prev = stack.pop();

                if(health > prev[1]){
                    health -= 1;
                }
                else if(health < prev[1]){
                    prev[1] -= 1;
                    stack.push(prev);
                    health = 0; // curr is to be removed
                    break;
                }
                else{
                    // curr & prev both have to be removed
                    health = 0;
                    break;
                }
            }
            
            if(health > 0){
                stack.push(new int[]{position, health, dir, idx});
            }
        }

        // in case empty stack
        if(stack.isEmpty()){
            return new ArrayList<>();
        }

        List<int[]> temp = new ArrayList<>();
        for(int[] curr : stack){
            temp.add(curr);
            // System.out.print(Arrays.toString(curr));
        }
        
        // sorting based on index to maintain order
        Collections.sort(temp, (a, b) -> a[3] - b[3]);

        List<Integer> result = new ArrayList<>();
        for(int[] curr : temp){
            result.add(curr[1]);
        }

        return result;
    }
}







// Approach 2 - Stack
// T.C : O(nlogn)
// T.C : O(n)
class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        Integer[] indices = new Integer[n];
        
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        Stack<Integer> stack = new Stack<>();

        Arrays.sort(indices, (i, j) -> Integer.compare(positions[i], positions[j]));

        List<Integer> result = new ArrayList<>();
        
        for (int currentIndex : indices) {
            if (directions.charAt(currentIndex) == 'R') {
                stack.push(currentIndex);
            } 
            else {
                while (!stack.isEmpty() && healths[currentIndex] > 0) {
                    int topIndex = stack.pop();

                    if (healths[topIndex] > healths[currentIndex]) {
                        healths[topIndex] -= 1;
                        healths[currentIndex] = 0;
                        stack.push(topIndex);
                    } 
                    else if (healths[topIndex] < healths[currentIndex]) {
                        healths[currentIndex] -= 1;
                        healths[topIndex] = 0;
                    } 
                    else {
                        healths[currentIndex] = 0;
                        healths[topIndex] = 0;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (healths[i] > 0) {
                result.add(healths[i]);
            }
        }

        return result;
    }
}