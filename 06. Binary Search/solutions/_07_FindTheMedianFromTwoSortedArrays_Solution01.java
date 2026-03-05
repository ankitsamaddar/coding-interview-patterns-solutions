/**
 * @file _07_FindTheMedianFromTwoSortedArrays_Solution01.java
 *
 * @problem Find the Median From Two Sorted Arrays
 * @statement You are given two sorted integer arrays. Your task is to find
 * their median value as if they were merged into a single sorted sequence. The
 * overall run time complexity should be $O(\log (m+n))$.
 *
 * @approach Binary Search on Partitions.
 *
 * @author ankitsamaddar
 * @date 2026-03-05
 *
 * Compile & Run: `javac _07_FindTheMedianFromTwoSortedArrays_Solution01.java && Get-Content input.txt | java _07_FindTheMedianFromTwoSortedArrays_Solution01 && rm -force _07_FindTheMedianFromTwoSortedArrays_Solution01.class && rm -force Solution.class`
 */

import java.io.*;
import java.util.*;

/* ── Solution ─────────────────────────────────────────────── */

class Solution {
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    if (nums1.length > nums2.length) // nums1 should always be samller
      return findMedianSortedArrays(nums2, nums1);

    int m = nums1.length;
    int n = nums2.length;
    int totalLen = m + n;
    int halfLen = totalLen / 2;

    int left = 0, right = m;

    while (left <= right) {
      int i = left + (right - left) / 2;
      int j = halfLen - i;

      int L1 = (i > 0) ? nums1[i - 1] : Integer.MIN_VALUE;
      int R1 = (i < m) ? nums1[i] : Integer.MAX_VALUE;

      int L2 = (j > 0) ? nums2[j - 1] : Integer.MIN_VALUE;
      int R2 = (j < n) ? nums2[j] : Integer.MAX_VALUE;

      if (L1 <= R1 && L2 <= R2) { // Found correct partition
        if (totalLen % 2 != 0) {  // Odd
          return (double)Math.min(R1, R2);
        } else {
          return (Math.max(L1, L2) + Math.min(R1, R2)) / 2.0;
        }
      }

      if (L1 > R2) {
        right = i - 1;
      } else {
        left = i + 1;
      }
    }
    return 0.0;
  }
}


/* ── Driver ───────────────────────────────────────────────── */

class _07_FindTheMedianFromTwoSortedArrays_Solution01 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int t = Integer.parseInt(br.readLine().trim());
    while (t-- > 0) {
      String line = br.readLine().trim();
      String[] numStr = line.split(",");
      String line2 = br.readLine().trim();
      String[] numsStr2 = line2.split(",");

      int[] nums1 = new int[numStr.length];
      int[] nums2 = new int[numsStr2.length];
      for (int i = 0; i < numStr.length; i++)
        nums1[i] = Integer.parseInt(numStr[i]);

      for (int i = 0; i < numsStr2.length; i++)
        nums2[i] = Integer.parseInt(numsStr2[i]);

      Solution obj = new Solution();

      double result = obj.findMedianSortedArrays(nums1, nums2);
      System.out.println(result);
    }
  }
}
