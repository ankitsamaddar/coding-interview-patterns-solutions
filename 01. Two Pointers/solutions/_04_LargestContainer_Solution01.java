/**
 * @file _04_LargestContainer_Solution01.java
 *
 * @problem Largest Container
 * @statement You're given an array of integers where each value represents the height of a vertical line drawn at that position. Any two lines, along with the x-axis, form a container. Find the pair of lines that holds the most water and return that volume.
 *
 * @approach Two Pointers (Inward Traversal, Greedy)
 *
 * @author ankitsamaddar
 * @date 2026-03-22
 *
 * Compile & Run: `javac _04_LargestContainer_Solution01.java && Get-Content input.txt | java _04_LargestContainer_Solution01 && rm -force _04_LargestContainer_Solution01.class && rm -force Solution.class`
 */

import java.util.*;
import java.io.*;

/* ── Solution ─────────────────────────────────────────────── */

class Solution {
    int maxArea(int[] heights) {
      int maxWater = 0;
      int lt = 0, rt = heights.length - 1;
      while (lt < rt) {
        int area = Math.min(heights[lt], heights[rt]) * (rt - lt);
        maxWater = Math.max(area, maxWater);
        if (heights[lt] > heights[rt]) {
          rt--;
        } else {
          lt++;
        }
      }
      return maxWater;
    }
}

/* ── Driver ───────────────────────────────────────────────── */

class _04_LargestContainer_Solution01 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        while (t-- > 0) {
            int[] arr = Arrays.stream(br.readLine().trim().split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
            Solution obj = new Solution();
            System.out.println(obj.maxArea(arr));
        }
    }
}
