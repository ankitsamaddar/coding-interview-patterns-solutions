/**
 * @file _06_LocalMaximaInArray_Solution01.java
 *
 * @problem Local Maxima in Array
 * @statement You need to find the index of any local maxima in an array. A local maxima is an element strictly greater than its immediate neighbors. For elements at the boundaries of the array, assume the neighbor outside the array is smaller. The array is guaranteed to have no adjacent duplicates.
 *
 * @approach Binary Search on the slope direction.
 *
 * @author ankitsamaddar
 * @date 2026-03-05
 *
 * Compile & Run: `javac _06_LocalMaximaInArray_Solution01.java && Get-Content input.txt | java _06_LocalMaximaInArray_Solution01 && rm -force _06_LocalMaximaInArray_Solution01.class && rm -force Solution.class`
 */

import java.util.*;
import java.io.*;

/* ── Solution ─────────────────────────────────────────────── */

class Solution {
  int findMaxima(int[] nums) {
    int left = 0, right = nums.length - 1;

    while (left < right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] > nums[mid+1]) { // decreasing order
        right = mid; // ans is mid, or towards left
      } else {
        left = mid + 1;
      }
    }
    return left;
  }
}

/* ── Driver ───────────────────────────────────────────────── */

class _06_LocalMaximaInArray_Solution01 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int t = Integer.parseInt(br.readLine().trim());
    while (t-- > 0) {
        String line = br.readLine().trim();
        String[] numStr = line.split(",");
        int[] nums = new int[numStr.length];
        for (int i = 0; i < numStr.length; i++) {
          nums[i] = Integer.parseInt(numStr[i]);
        }

        Solution obj = new Solution();
        System.out.println(obj.findMaxima(nums));
    }
  }
}
