/**
 * @file _01_PairSumSorted_Solution01.java
 *
 * @problem Pair Sum — Sorted
 * @statement You're given a sorted integer array (ascending order) and a target value. Find any two elements that add up to the target and return their indexes. The order of the indexes in the result doesn't matter. Return an empty array if no valid pair exists.
 *
 * @approach Two Pointers (Inward Traversal)
 *
 * @author ankitsamaddar
 * @date 2026-03-21
 *
 * Compile & Run: `javac _01_PairSumSorted_Solution01.java && Get-Content input.txt | java _01_PairSumSorted_Solution01 && rm -force _01_PairSumSorted_Solution01.class && rm -force Solution.class`
 */

import java.util.*;
import java.io.*;

/* ── Solution ─────────────────────────────────────────────── */

class Solution {
    int[] pairSumSorted (int[] nums, int target) {
      int lo = 0, hi = nums.length - 1;
      while (lo < hi) {
        int sum = nums[lo] + nums[hi];
        if (sum == target) {
          return new int[]{lo, hi};
        } else if (sum > target) {
          hi--;
        } else {
          lo++;
        }
      }
      return new int[]{};
    }
}

/* ── Driver ───────────────────────────────────────────────── */

class _01_PairSumSorted_Solution01 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        while (t-- > 0) {
            int[] arr = Arrays.stream(br.readLine().trim().split("[\\s+,]+")).mapToInt(Integer::parseInt).toArray();
            int K = Integer.parseInt(br.readLine().trim());
            Solution obj = new Solution();
            int[] result = obj.pairSumSorted(arr, K);
            System.out.println(Arrays.toString(result));
        }
    }
}
