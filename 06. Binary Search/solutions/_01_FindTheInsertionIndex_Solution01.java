/**
 * @file _01_FindTheInsertionIndex_Solution01.java
 *
 * @problem Find the Insertion Index
 * @statement You have a sorted array of unique integers and a target value. Return the index of the target if it exists. If it does not, return the index where it would be inserted to maintain sorted order.
 *
 * @approach Lower Bound Binary Search.
 *
 * @author ankitsamaddar
 * @date 2026-03-05
 *
 * Compile & Run: `javac _01_FindTheInsertionIndex_Solution01.java && Get-Content input.txt | java _01_FindTheInsertionIndex_Solution01 && rm -force _01_FindTheInsertionIndex_Solution01.class`
 */

import java.util.*;
import java.io.*;

/* ── Solution ─────────────────────────────────────────────── */

class Solution {
    int findInsertIndex(int[] nums, int target) {
      int n = nums.length;
      int l = 0, r = n;
      while (l < r) {
        int mid = l + (r-l) / 2;
        if (nums[mid]  >= target) {
          r = mid;
        } else {
          l = mid + 1;
        }
      }
      return l;
    }
}

/* ── Driver ───────────────────────────────────────────────── */

class _01_FindTheInsertionIndex_Solution01 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        while (t-- > 0) {
          String line = br.readLine().trim();
          String[] numsStr = line.split(" ");
          int[] nums = new int[numsStr.length];
          for(int i = 0; i < numsStr.length; i++) {
            nums[i] = Integer.parseInt(numsStr[i]);
          }
          int target = Integer.parseInt(br.readLine().trim());
          Solution obj = new Solution();
          System.out.println(obj.findInsertIndex(nums, target));
        }
    }
}
