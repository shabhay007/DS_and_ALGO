// LeetCode - 3217




class Solution {
    public ListNode modifiedList(int[] nums, ListNode head) {
        Set<Integer> set = new HashSet<>();

        for(int num : nums){
            set.add(num);
        }

        ListNode dummyNode = new ListNode(-1);
        ListNode curr = head;
        dummyNode.next = curr;
        ListNode prev = dummyNode;

        while(curr != null){
            if(set.contains(curr.val)){
                prev.next = curr.next;
                curr.next = null;
                curr = prev.next;
            }
            else{
                prev = curr;
                curr = curr.next;
            }

            
        }

        return dummyNode.next;
    }
}






// Approach 2
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public ListNode modifiedList(int[] nums, ListNode head) {
        if(head == null){
            return null;
        }

        Set<Integer> set = new HashSet<>();

        for(int num : nums){
            set.add(num);
        }

        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode curr = head;
        ListNode prev = dummyNode;

        while(curr != null){
            if(set.contains(curr.val)){
                prev.next = curr.next;
            }
            else{
                prev = curr;
            }

            curr = curr.next;
        }

        return dummyNode.next;
    }
}