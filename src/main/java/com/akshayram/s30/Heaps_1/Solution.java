import java.util.PriorityQueue;

class KthLargestElementInAnArray {

    public static void main(String[] args) {
        int[] arr = { 3, 2, 1, 5, 6, 4 };
        int k = 2;
        KthLargestElementInAnArray obj = new KthLargestElementInAnArray();
        int obj1 = obj.findKthLargest(arr, k);
        System.out.println(obj1);
    }

    // TC: O(nlogk) SC: O(k)
    public int findKthLargest(int[] nums, int k) {
        // max heap
        PriorityQueue<Integer> pq = new PriorityQueue<>(((a, b) -> (b - a)));
        int result = Integer.MAX_VALUE;
        int n = nums.length;
        for (int num : nums) {
            pq.add(num);
            if (pq.size() > n - k) {
                result = Math.min(result, pq.poll());
            }
        }
        return result;
    }
}

class ListNode {

    ListNode next;
    int val;

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;

    }

}

// TC: O(nlogk) SC: O(k)
class MergekSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        // Min Priority queue to keep track of smallest integer and the size will be k
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> (a.val - b.val));
        ListNode dummy = new ListNode(-1);
        ListNode head = dummy;

        // Put all the heads in the pq
        for (int i = 0; i < lists.length; i++) {

            if (lists[i] != null) {
                pq.add(lists[i]);
            }
        }

        // Remove the smallest elements and add it to the result linked list
        // Put the next node of the smallest node in pq
        while (pq.peek() != null) {
            ListNode node = pq.poll();
            head.next = node;
            head = head.next;
            if (node.next != null)
                pq.add(node.next);
        }
        return dummy.next;
    }
}

public class Solution {
}