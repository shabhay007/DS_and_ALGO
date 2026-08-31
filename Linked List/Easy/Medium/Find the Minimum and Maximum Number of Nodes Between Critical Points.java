// LeetCode - 2058



// Approach 1 - Linked List Traversal (Simulation)
// T.C. - O(n)
// S.C. - O(n/2) ~ O(n)
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        ListNode prev = head;
        ListNode curr = head;

        List<Integer> list = new ArrayList<>();
        int counter = 0;

        while(curr != null && curr.next != null){
            counter++;

            if(counter >= 2){
                if(curr.val > prev.val && curr.val > curr.next.val){
                    list.add(counter);
                }
                
                if(curr.val < prev.val && curr.val < curr.next.val){
                    list.add(counter);
                }
            }

            prev = curr;
            curr = curr.next;
        }

        int len = list.size();
        if(len < 2){
            return new int[]{-1, -1};
        }

        int maxDist = list.get(len - 1) - list.get(0);
        int minDist = Integer.MAX_VALUE;

        for(int i = 0; i<len-1; i++){
            minDist = Math.min(minDist, list.get(i+1) - list.get(i));
        }

        return new int[]{minDist, maxDist};
    }
}






// Approach 2 - Linked List Traversal (Simulation)
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        ListNode prev = head;
        ListNode curr = head;

        int counter = 0;
        int minDist = Integer.MAX_VALUE;
        int firstCriticalPoint = -1;
        int prevCriticalPoint = -1;

        while(curr != null && curr.next != null){
            counter++;

            if(counter >= 2){
                if((curr.val > prev.val && curr.val > curr.next.val) || 
                    (curr.val < prev.val && curr.val < curr.next.val)){

                    if(firstCriticalPoint == -1){
                        firstCriticalPoint = counter;
                    }
                    else{
                        minDist = Math.min(minDist, counter - prevCriticalPoint);
                    }
                    
                    prevCriticalPoint = counter;
                }
            }

            prev = curr;
            curr = curr.next;
        }

        if(firstCriticalPoint == -1 || prevCriticalPoint == firstCriticalPoint){
            return new int[]{-1, -1};
        }

        int maxDist = prevCriticalPoint - firstCriticalPoint;

        return new int[]{minDist, maxDist};
    }
}