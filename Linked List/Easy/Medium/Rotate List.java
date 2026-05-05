// LeetCode - 61



// Approach 1 - Reconstruction Using List
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null || k == 0){
            return head;
        }

        List<ListNode> list = new ArrayList<>();
        int n = 0;
        ListNode temp = head;

        while(temp != null){
            list.add(temp);
            temp = temp.next;
            n++;
        }

        int K = k % n;
        if(K == 0){
            return head;
        }

        int breakPoint = n - K;
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;

        // second part :- rotated front part
        for(int i = breakPoint; i<n; i++){
            curr.next = list.get(i);
            curr = curr.next;
        }

        // first part :- remaining, initially in first part
        for(int i = 0; i<breakPoint; i++){
            curr.next = list.get(i);
            curr = curr.next;
        }

        curr.next = null;

        return dummy.next;
    }
}






// Approach 2 - In place rotation
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null || k == 0){
            return head;
        }

        ListNode temp = head;
        int n = 0;

        while(temp != null){
            temp = temp.next;
            n++;
        }

        int K = k % n;
        if(K == 0){
            return head;
        }

        int breakPoint = n - K;
        ListNode curr = head;
        
        for(int i = 1; i<breakPoint; i++){
            curr = curr.next;
        }

        ListNode newHead = curr.next;
        curr.next = null;

        // finding tail of new head
        ListNode tail = newHead;
        while(tail.next != null){
            tail = tail.next;
        }

        tail.next = head;

        return newHead;
    }
}







// Approach 3 - Optimised In place rotation
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null || k == 0){
            return head;
        }

        ListNode tail = head;
        int n = 1;

        while(tail.next != null){
            tail = tail.next;
            n++;
        }

        tail.next = head; // connecting tail with head to make it circular
        int breakPoint = n - (k % n);

        // finding new tail
        ListNode newTail = head;
        for(int i = 1; i<breakPoint; i++){
            newTail = newTail.next;
        }

        // breaking the circular list
        ListNode newHead = newTail.next;
        newTail.next = null;

        return newHead;
    }
}