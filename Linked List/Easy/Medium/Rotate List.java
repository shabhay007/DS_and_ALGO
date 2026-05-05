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