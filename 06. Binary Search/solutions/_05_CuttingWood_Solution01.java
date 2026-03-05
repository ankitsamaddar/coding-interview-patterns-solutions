/**
 * @file _05_CuttingWood_Solution01.java
 *
 * @problem Cutting Wood
 * @statement You are given an array of tree heights and a target amount of wood $k$. You need to set a blade height $H$ for your woodcutter. The machine cuts the top off every tree taller than $H$. You want to find the maximum integer height $H$ that allows you to cut at least $k$ meters of wood.
 *
 * @approach Upper-bound Binary Search.
 *
 * @author ankitsamaddar
 * @date 2026-03-05
 *
 * Compile & Run: `javac _05_CuttingWood_Solution01.java && Get-Content input.txt | java _05_CuttingWood_Solution01 && rm -force _05_CuttingWood_Solution01.class && rm -force Solution.class`
 */

import java.util.*;
import java.io.*;

/* ── Solution ─────────────────────────────────────────────── */

class Solution {
    int cutWoods(int[] heights, int k) {
      int l = 0, r = 0;
      for(int h : heights) {
        r = Math.max(r, h);
      }

      while (l < r) {
        int mid = l + (r-l) / 2 + 1; // right bias, as we consider mid as potential ans
        if (canGetEnoughWoods(heights, mid, k)) {
          l = mid; // mid can also be the answer, find higher blade value
        } else {
          r = mid - 1;
        }
      }
      return r;
    }

    private boolean canGetEnoughWoods(int[] heights, int blade, int k) {
      int getWoodCount = 0;
      for (int h : heights) {
        getWoodCount += Math.max(0, h - blade);
        if (getWoodCount >= k) // Return early
          return true;
      }

      return getWoodCount >= k;
    }
}

/* ── Driver ───────────────────────────────────────────────── */

class _05_CuttingWood_Solution01 {
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
          int k = Integer.parseInt(br.readLine().trim());

          Solution obj = new Solution();
          System.out.println(obj.cutWoods(nums, k));
        }
    }
}
