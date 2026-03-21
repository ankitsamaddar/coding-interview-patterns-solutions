/**
 * @file _02_TripletSum_Solution01.java
 *
 * @problem Triplet Sum
 * @statement Given an array of integers, find all unique triplets $[a, b, c]$ such that $a + b + c = 0$. The result must not contain duplicate triplets — `[1, 2, 3]` and `[2, 3, 1]` count as the same. Return an empty array if none exist.
 *
 * @approach Sort + Two Pointers (Fix-one, Search-pair)
 *
 * @author ankitsamaddar
 * @date 2026-03-21
 *
 * Compile & Run: `javac _02_TripletSum_Solution01.java && Get-Content input.txt | java _02_TripletSum_Solution01 && rm -force _02_TripletSum_Solution01.class && rm -force Solution.class`
 */

import java.util.*;
import java.io.*;

/* ── Solution ─────────────────────────────────────────────── */

class Solution {
    List<List<Integer>> tripletSum(int[] nums) {
      List<List<Integer>> triplets = new ArrayList<>();
      Arrays.sort(nums);
      int n = nums.length;

      for (int i = 0; i < n; i++) {
        if(nums[i] > 0) break;
        if (i > 0 && nums[i] == nums[i-1]) continue;
        int lo = i + 1, hi = n - 1;
        while (lo < hi) {
          int sum = nums[lo] + nums[hi];
          if (sum == -nums[i]) {
            triplets.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
            lo++;
            hi--;
            while (lo < hi && nums[lo] == nums[lo - 1]) lo++;
            while (lo < hi && nums[hi] == nums[hi + 1]) hi--;
          } else if (sum > -nums[i]) {
            hi--;
          } else {
            lo++;
          }
        }
      }
      return triplets;
    }
}

/* ── Driver ───────────────────────────────────────────────── */

class _02_TripletSum_Solution01 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        while (t-- > 0) {
            int[] arr = Arrays.stream(br.readLine().trim().split("[\\s,]+")).mapToInt(Integer::parseInt).toArray();
            Solution obj = new Solution();
            List<List<Integer>> results = obj.tripletSum(arr);
            System.out.println(results.toString());
        }
    }
}
