/**
 * @file _04_SearchA2DMatrix_Solution01.java
 *
 * @problem Search a 2D Matrix
 * @statement You are given an $m \times n$ matrix where each row is sorted in non-decreasing order. The first integer of each row is greater than or equal to the last integer of the previous row. Given a target integer, determine if it exists in the matrix.
 *
 * @approach Binary Search on Virtual 1D Array.
 *
 * @author ankitsamaddar
 * @date 2026-03-05
 *
 * Compile & Run: `javac _04_SearchA2DMatrix_Solution01.java && Get-Content input.txt | java _04_SearchA2DMatrix_Solution01 && rm -force _04_SearchA2DMatrix_Solution01.class && rm -force Solution.class`
 */

import java.util.*;
import java.io.*;

/* ── Solution ─────────────────────────────────────────────── */

class Solution {
    boolean matSearch(int[][] mat, int target) {
      if (mat == null || mat.length == 0 || mat[0].length == 0) {
        return false;
      }

      int m = mat.length;
      int n = mat[0].length;
      int l = 0, r = m * n - 1;

      while (l <= r) {
        int mid = l + (r-l) / 2;
        int i = mid / n, j = mid % n;
        int midValue = mat[i][j];
        if (midValue == target) {
          return true;
        }

        if (midValue < target) {
          l = mid + 1;
        } else {
          r = mid - 1;
        }
      }

      return false;
    }
}

/* ── Driver ───────────────────────────────────────────────── */

class _04_SearchA2DMatrix_Solution01 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        while (t-- > 0) {
            int rows = Integer.parseInt(br.readLine().trim());
            int cols = Integer.parseInt(br.readLine().trim());
            int[][] mat = new int[rows][cols];

            for (int i = 0; i < rows; i++) {
              String line = br.readLine().trim();
              String[] numStr = line.split(",");
              for (int j = 0; j < cols; j++) {
                mat[i][j] = Integer.parseInt(numStr[j]);
              }
            }

            int target = Integer.parseInt(br.readLine().trim());

            Solution obj = new Solution();
            if (obj.matSearch(mat, target)) {
              System.out.println("true");
            } else {
              System.out.println("false");
            }
        }
    }
}
