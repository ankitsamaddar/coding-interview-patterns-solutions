# coding-interview-patterns-solutions

## 01. Two Pointers

1. [Pair Sum — Sorted](<./01. Two Pointers/01-pair-sum-sorted.md>) : You're given a sorted integer array (ascending order) and a target value. Find any two elements that add up to the target and return their indexes. The order of the indexes in the result doesn't matter. Return an empty array if no valid pair exists.
2. [Triplet Sum](<./01. Two Pointers/02-triplet-sum.md>) : Given an array of integers, find all unique triplets $[a, b, c]$ such that $a + b + c = 0$. The result must not contain duplicate triplets — `[1, 2, 3]` and `[2, 3, 1]` count as the same. Return an empty array if none exist.
3. [Is Palindrome Valid](<./01. Two Pointers/03-is-palindrome-valid.md>) : Given a string that may contain letters, digits, spaces, and punctuation, decide if it reads the same forward and backward after stripping all non-alphanumeric characters. The comparison is case-insensitive.
4. [Largest Container](<./01. Two Pointers/04-largest-container.md>) : You're given an array of integers where each value represents the height of a vertical line drawn at that position. Any two lines, along with the x-axis, form a container. Find the pair of lines that holds the most water and return that volume.
5. [Shift Zeros to the End](<./01. Two Pointers/05-shift-zeros-to-the-end.md>) : You're given an integer array. Rearrange it in place so all zeros are at the end while the non-zero elements stay in their original relative order.

## 06. Binary Search

1. [Find the Insertion Index](<./06. Binary Search/01-find-the-insertion-index.md>) :
   You have a sorted array of unique integers and a target value. Return the index of the target if it exists. If it does not, return the index where it would be inserted to maintain sorted order.
2. [First and Last Occurrences of a Number](<./06. Binary Search/02-first-and-last-occurrences-of-a-number.md>) : Given an array of integers sorted in non-decreasing order, find the starting and ending position of a given target value. If the target is not found in the array, return `[-1, -1]`.
3. [Search in Rotated Sorted Array](<./06. Binary Search/03-search-in-rotated-sorted-array.md>) :
   Given an array of unique integers sorted in ascending order that has been rotated at an unknown pivot, write a function to search for a target value. If the target exists, return its index. If not, return -1.
4. [Search a 2D Matrix](<./06. Binary Search/04-search-a-2d-matrix.md>) : You are given an $m \times n$ matrix where each row is sorted in non-decreasing order. The first integer of each row is greater than or equal to the last integer of the previous row. Given a target integer, determine if it exists in the matrix.
5. [Cutting Wood](<./06. Binary Search/05-cutting-wood.md>) : You are given an array of tree heights and a target amount of wood $k$. You need to set a blade height $H$ for your woodcutter. The machine cuts the top off every tree taller than $H$. You want to find the maximum integer height $H$ that allows you to cut at least $k$ meters of wood.
6. [Local Maxima in Array](<./06. Binary Search/06-local-maxima-in-array.md>) : You need to find the index of any local maxima in an array. A local maxima is an element strictly greater than its immediate neighbors. For elements at the boundaries of the array, assume the neighbor outside the array is smaller. The array is guaranteed to have no adjacent duplicates.
7. [Find the Median From Two Sorted Arrays](<./06. Binary Search/07-find-the-median-from-two-sorted-arrays.md>) : You are given two sorted integer arrays. Your task is to find their median value as if they were merged into a single sorted sequence. The overall run time complexity should be $O(\log (m+n))$.
8. [Weighted Random Selection](<./06. Binary Search/08-weighted-random-selection.md>) : You are given an array of positive integers called `weights`. You need to implement a class that picks an index from this array randomly. The probability of picking a specific index should be proportional to its weight. Specifically, the probability of picking index $i$ is $\frac{{weights[i]}}{{\sum weights}}$.

