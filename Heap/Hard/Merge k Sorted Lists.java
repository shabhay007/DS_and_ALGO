// LeetCode - 23



/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */





// Approach 1 - List + Sorting
// T.C. - O(k * n)
// S.C. - O(k * n)
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        List<Integer> list = new ArrayList<>();

        for(ListNode head : lists){
            while(head != null){
                list.add(head.val);
                head = head.next;
            }
        }

        Collections.sort(list);
        ListNode dummy = new ListNode();
        ListNode prev = dummy;

        for(Integer num : list){
            ListNode newNode = new ListNode(num);
            prev.next = newNode;
            prev = prev.next;
        }

        return dummy.next;
    }
}







// Approach 2 - Heap
// T.C. - O(k * n)
// S.C. - O(k * n)
class Pair{
    ListNode node;
    int val;

    public Pair(ListNode node, int val){
        this.node = node;
        this.val = val;
    }
}

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null){
            return null;
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.val - b.val);

        for(ListNode head : lists){
            if(head != null){
                pq.offer(new Pair(head, head.val));
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;

        while(!pq.isEmpty()){
            Pair p = pq.poll();
            ListNode currHead = p.node;
            int val = p.val;

            ListNode curr = new ListNode(val);
            prev.next = curr;
            prev = prev.next;

            if(currHead.next != null){
                pq.offer(new Pair(currHead.next, currHead.next.val));
            }
        }

        return dummy.next;
    }
}






// Approach 3 - Heap (Slightly Cleaner version of Approach 2)
// T.C. - O(k * n)
// S.C. - O(k * n)
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0){
            return null;
        }

        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);

        for(ListNode head : lists){
            if(head != null){
                pq.offer(head);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;

        while(!pq.isEmpty()){
            ListNode head = pq.poll();

            ListNode curr = new ListNode(head.val);
            prev.next = curr;
            prev = prev.next;

            if(head.next != null){
                pq.offer(head.next);
            }
        }

        return dummy.next;
    }
}