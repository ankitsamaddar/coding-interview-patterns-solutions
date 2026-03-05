/**
 * @file _02_FirstAndLastOccurrencesOfANumber_Solution01.java
 *
 * @problem First and Last Occurrences of a Number
 * @statement Given an array of integers sorted in non-decreasing order, find the starting and ending position of a given target value. If the target is not found in the array, return `[-1, -1]`.
 *
 * @approach Modified Binary Search (Lower and Upper Bound).
 *
 * @author ankitsamaddar
 * @date 2026-03-05
 *
 * Compile & Run: `javac _02_FirstAndLastOccurrencesOfANumber_Solution01.java && Get-Content input.txt | java _02_FirstAndLastOccurrencesOfANumber_Solution01 && rm -force _02_FirstAndLastOccurrencesOfANumber_Solution01.class`
 */

import java.util.*;
import java.io.*;

/* ── Solution ─────────────────────────────────────────────── */

class Solution {
    int[] firstLastOccurence(int[] nums, int target) {
      if (nums.length == 0) {
        return new int[]{-1, -1};
      }

      int left = findFirst(nums, target);
      int right = findLast(nums, target);

      return new int[]{left, right};
    }

    int findFirst(int[] nums, int target) {
      int l = 0, r = nums.length - 1;
      while(l < r) {
        int m = l + (r - l) / 2;
        if (nums[m] > target) {
          r = m - 1;
        } else if (nums[m] < target) {
          l = m + 1;
        } else {
          r = m; // found, but find leftmost
        }
      }

      return nums[l] == target ? l : -1;
    }

    int findLast(int[] nums, int target) {
      int l = 0, r = nums.length - 1;
      while (l < r) {
        int m = l + (r-l) / 2 + 1; // bias to right
        if (nums[m] > target) {
          r = m - 1;
        } else if (nums[m] < target) {
          l = m + 1;
        } else {
          l = m; // found, but find rightmost
        }
      }

      return nums[r] == target ? r : -1;
    }
}

/* ── Driver ───────────────────────────────────────────────── */

class _02_FirstAndLastOccurrencesOfANumber_Solution01 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        while (t-- > 0) {
            String line = br.readLine().trim();
            String[] numStr = line.split(", ");
            int[] nums = new int[numStr.length];

            for (int i = 0; i < numStr.length; i++) {
              nums[i] = Integer.parseInt(numStr[i]);
            }

            int target = Integer.parseInt(br.readLine().trim());

            Solution obj = new Solution();
            int[] result = obj.firstLastOccurence(nums, target);

            for(int n : result) {
              System.out.print(n + " ");
            }
            System.out.println();
        }
    }
}
