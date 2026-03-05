/**
 * @file _03_SearchInRotatedSortedArray_Solution01.java
 *
 * @problem Search in Rotated Sorted Array
 * @statement Given an array of unique integers sorted in ascending order that has been rotated at an unknown pivot, write a function to search for a target value. If the target exists, return its index. If not, return -1.
 *
 * @approach Modified Binary Search.
 *
 * @author ankitsamaddar
 * @date 2026-03-05
 *
 * Compile & Run: `javac _03_SearchInRotatedSortedArray_Solution01.java && Get-Content input.txt | java _03_SearchInRotatedSortedArray_Solution01 && rm -force _03_SearchInRotatedSortedArray_Solution01.class`
 */

import java.util.*;
import java.io.*;

/* ── Solution ─────────────────────────────────────────────── */

class Solution {
    int search (int[] nums, int target) {
      if(nums == null || nums.length == 0) {
        return -1;
      }

      int l = 0, r = nums.length - 1;
      while (l <= r) {
        int m = l + (r-l) /2;
        if (nums[m] == target) {
          return m;
        }
        // Left half sorted
        if (nums[l] < nums[m]) {
          if (target >= nums[l] && target < nums[m]) {
            r = m - 1;
          } else {
            l = m + 1;
          }
        } else { // Right half sorted
          if (target > nums[m] && target <= nums[r]) {
            l = m + 1;
          } else {
            r = m - 1;
          }
        }
      }
      return -1;
    }
}

/* ── Driver ───────────────────────────────────────────────── */

class _03_SearchInRotatedSortedArray_Solution01 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        while (t-- > 0) {
            String line = br.readLine().trim();
            String[] numStr = line.split(",");
            int[] nums = new int[numStr.length];
            for(int i = 0; i < numStr.length; i++) {
              nums[i] = Integer.parseInt(numStr[i]);
            }
            int target = Integer.parseInt(br.readLine().trim());

            Solution obj = new Solution();
            System.out.println(obj.search(nums, target));
        }
    }
}
