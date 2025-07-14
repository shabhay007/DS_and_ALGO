// LeetCode - 1290



// Approach 1 - By parsing binary String to decimal integer
 // T.C. - O(n); n = total no of nodes
 // S.C. - O(n)
 class Solution {
    public int getDecimalValue(ListNode head) {
        if(head == null){
            return 0;
        }

        StringBuilder sb = new StringBuilder();
        ListNode temp = head;

        while(temp != null){ // O(n)
            sb.append(temp.val);
            temp = temp.next;
        }

        int decimalNumber = Integer.parseInt(sb.toString(), 2); // O(n)

        return decimalNumber;
    }
}





// Approach 2 - Using Bit Manipulation
 // T.C. - O(n); n = total no of nodes
 // S.C. - O(1)
 class Solution {
    public int getDecimalValue(ListNode head) {
        if(head == null){
            return 0;
        }

        int result = 0;

        while(head != null){
            result = (result << 1) | head.val;
            head = head.next;
        }

        return result;
    }
}