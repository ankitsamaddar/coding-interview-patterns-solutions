/**
 * @file _05_ShiftZerosToTheEnd_Solution01.java
 *
 * @problem Shift Zeros to the End
 * @statement You're given an integer array. Rearrange it in place so all zeros are at the end while the non-zero elements stay in their original relative order.
 *
 * @approach Two Pointers (Unidirectional)
 *
 * @author ankitsamaddar
 * @date 2026-03-22
 *
 * Compile & Run: `javac _05_ShiftZerosToTheEnd_Solution01.java && Get-Content input.txt | java _05_ShiftZerosToTheEnd_Solution01 && rm -force _05_ShiftZerosToTheEnd_Solution01.class && rm -force Solution.class`
 */

import java.util.*;
import java.io.*;

/* ── Solution ─────────────────────────────────────────────── */

class Solution {
    void shiftZeros(int[] nums) {
      int left = 0;
      for(int right = 0; right < nums.length; right++) {
        if (nums[right] != 0) {
          int temp = nums[left];
          nums[left] = nums[right];
          nums[right] = temp;
          left++;
        }
      }
    }
}

/* ── Driver ───────────────────────────────────────────────── */

class _05_ShiftZerosToTheEnd_Solution01 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        while (t-- > 0) {
            int[] arr = Arrays.stream(br.readLine().trim().split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
            Solution obj = new Solution();
            obj.shiftZeros(arr);
            System.out.println(Arrays.toString(arr));
        }
    }
}
