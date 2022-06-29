package com.example.bootleetcode.demo.easy;

/**
 * 将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 */
public class Leet21 {

    //循环+双指针
//    public ListNode merge(ListNode l1,ListNode l2){
//        if(l1==NULL)
//            return l2;
//        if(l2==NULL)
//            return l1;
//        ListNode resultNode = new ListNode(0);
//        ListNode p = resultNode;
//        while(l1 != null && l2 !=null){
//            if(l1.val < l2.val){
//                p.next = l1;
//                l1 = l1.next;
//            }else{
//                p.next = l2;
//                l2 = l2.next;
//            }
//            p.next;
//        }
//        if(l1 != null){
//            p.next = l1;
//        }
//        if(l2 != null){
//            p.next = l2;
//        }
//        return resultNode.next;
//    }
//
//    //递归
//    public ListNode merge2(ListNode l1,ListNode l2){
//        if(l1==NULL)
//            return l2;
//        if(l2==NULL)
//            return l1;
//        if(l1.val < l2.val){
//            l1.next = mergeTwoLists(l1.next,l2);
//            return l1;
//        }else{
//            l2.next = mergeTwoLists(l1,l2.next);
//            return l2;
//        }
//    }
}
