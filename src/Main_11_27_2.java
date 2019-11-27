import java.util.List;

public class Main_11_27_2 {
  static   class ListNode {
        int val;
        ListNode next = null;
        ListNode(int val) {
            this.val = val;
        }

    }


    public ListNode partition(ListNode pHead, int x) {
      ListNode node = new ListNode(x);
      ListNode newhead =new ListNode(-1);
      newhead.next = node;
      ListNode before = newhead;
      ListNode after = node;
      ListNode cur = pHead;
      for(;cur!=null ; cur = cur.next){
          if(cur.val < x){
              ListNode t = new ListNode(cur.val);
              before.next = t;
              t.next = node;
              before = t;
          }else{
              ListNode t = new ListNode(cur.val);
              after.next = t;
              after = t;
          }
      }
       before.next = node.next;
        return newhead.next;
    }

    public static void main(String[] args) {
        Main_11_27_2 m = new Main_11_27_2();
        ListNode head = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(1);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;

        ListNode returnHead = m.partition(head, 3);
        while (returnHead != null) {
            System.out.println(returnHead.val);
            returnHead = returnHead.next;
        }
    }
}
