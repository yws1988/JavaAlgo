package collections.list;

/***
 * Given a linked list, extract all even-indexed nodes, reverse their order,
 * and append them to the end of the list in one traversal. Return the head of the modified list.
 * Input
 * head = [10, 20, 30, 40, 50, 60]
 * Output
 * [20, 40, 60, 50, 30, 10]
 */
public class ReverseEvenIndexedNodes {
    public static LinkedListNode extractAndAppendSponsoredNodes(LinkedListNode head) {
        if(head==null || head.next==null) return head;

        LinkedListNode evenHead=null;
        LinkedListNode oddHead=null;
        LinkedListNode oddTail=null;
        LinkedListNode currNode=head;

        boolean isEven = true;
        while(currNode!=null){
            LinkedListNode nextNode=currNode.next;
            currNode.next=null;

            if(isEven){
                currNode.next = evenHead;
                evenHead=currNode;
            }else{
                if(oddHead==null){
                    oddHead=currNode;
                    oddTail=currNode;
                }else{
                    oddTail.next=currNode;
                    oddTail=currNode;
                }
            }

            currNode = nextNode;
            isEven=!isEven;
        }

        oddTail.next=evenHead;
        return oddHead;
    }
    
    public static class LinkedListNode{
        public int data;
        public LinkedListNode next;
    }
}
