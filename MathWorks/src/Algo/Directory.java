package Algo;

public class Directory {
}

/*
1. leetcode 41: First missing positive(+2)
    |--swap the number to its right place O(n)
2. Given a String, you can use the characters in any order, to form the largest lexicographically string and the smallest lexicographically,
   the string must use "a,e,i,o,u" as start and "consonant" as end. (+2)
    |--sort
3. Maximum difference between two elements such that larger element appears after the smaller number (+2)
    |--1. Forward pass the min, backwards pass the max, find the mas diff
    |--2. Keep track of the current min, calculate the max difference
4. leetcode 780: Reaching Points
    |--Math problem
5. Find maximum difference in an array, using single value
6. leetcode 3: Longest Substring Without Repeating Characters
7. leetcode 103: Binary Tree Zigzag Level Order Traversal (+2)
8. leetcode 199: Binary Tree Right Side View
9. leetcode 617: Merge Two Binary Trees
10. Given a func(arr,n) which can reverse arr[0-n], use func(arr,n) to sort an array
    |--backwards, find the max, f(arr, maxIndex)
    |--f(arr, unsortedEnd)
11. leetcode 232: Implement Queue using Stacks
12. leetcode 503: Next greater element II
13. leetcode 206: Reverse Linked List
14. Given an array, find the closest larger number
    |--Forward: next greater element
    |--Backward: next greater element
    |--Compare to get closest
15. leetcode 225: Implement Stack using Queues
 */
//10.
public int[] sortUsingFunc(int[] arr) {
    for(int i = arr.length - 1; i >= 0; i--) {
        int max = 0;
        for(int j = i; j >= 0; j--)
            max = arr[max] < arr[j] ? j : max;
        f(arr, max);
        f(arr, i);
    }
}