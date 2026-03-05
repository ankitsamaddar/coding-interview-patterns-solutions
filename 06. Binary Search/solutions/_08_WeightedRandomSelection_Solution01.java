/**
 * @file _08_WeightedRandomSelection_Solution01.java
 *
 * @problem Weighted Random Selection
 * @statement You are given an array of positive integers called `weights`. You
 * need to implement a class that picks an index from this array randomly. The
 * probability of picking a specific index should be proportional to its weight.
 * Specifically, the probability of picking index $i$ is $\frac{weights[i]}{\sum
 * weights}$.
 *
 * @approach Prefix Sum + Binary Search.
 *
 * @author ankitsamaddar
 * @date 2026-03-05
 *
 * Compile & Run: `javac _08_WeightedRandomSelection_Solution01.java && Get-Content input.txt | java _08_WeightedRandomSelection_Solution01 && rm -force _08_WeightedRandomSelection_Solution01.class && rm -force Solution.class`
 *
 */

import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/* ── Solution ─────────────────────────────────────────────── */

class Solution {
  private int[] prefixSums;
  Random random;
  private int totalSum;

  public Solution(int[] w) {
    this.random = new Random();
    this.prefixSums = new int[w.length];
    int sum = 0;
    for (int i = 0; i < w.length; i++) {
      sum += w[i];
      prefixSums[i] = sum;
    }
    this.totalSum = sum;
  }

  public int pickIndex() {
    // int target = random.nextInt(totalSum) + 1;
    int target = ThreadLocalRandom.current().nextInt(1, totalSum + 1);

    int l = 0, r = prefixSums.length - 1;
    while (l < r) {
      int m = l + (r - l) / 2;
      if (prefixSums[m] < target) {
        l = m + 1;
      } else {
        r = m - 1;
      }
    }
    return l;
  }
}

/* ── Driver ───────────────────────────────────────────────── */

class _08_WeightedRandomSelection_Solution01 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int t = Integer.parseInt(br.readLine().trim());
    while (t-- > 0) {
      String line = br.readLine().trim();
      String[] numStr = line.split(",");
      int[] nums = new int[numStr.length];
      for (int i = 0; i < numStr.length; i++)
        nums[i] = Integer.parseInt(numStr[i]);

      Solution obj = new Solution(nums);
      System.out.println(obj.pickIndex());
    }
  }
}
