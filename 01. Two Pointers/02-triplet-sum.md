# Triplet Sum

> Time Limit: 1sec
> Space Limit: 256MB
> Difficulty: Medium
> Link: [LeetCode 15 – 3Sum](https://leetcode.com/problems/3sum/)

## Description

Given an array of integers, find all unique triplets $[a, b, c]$ such that $a + b + c = 0$. The result must not contain duplicate triplets — `[1, 2, 3]` and `[2, 3, 1]` count as the same. Return an empty array if none exist.

**Example**:
```
Input:  nums = [0, -1, 2, -3, 1]
Output: [[-3, 1, 2], [-1, 0, 1]]
```
Each triplet is sorted; output order doesn't matter.

**Input Format**:
A single integer array `nums` (may be unsorted, may contain duplicates).

**Output Format**:
A list of unique triplets, each sorted in ascending order.

**Constraints**:
- $0 \leq n \leq 3000$
- $-10^5 \leq \text{nums}[i] \leq 10^5$

**Code Template**:
```java
import java.util.*;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        // your code here
    }
}
```

**Hint**: Sort first, then fix one element and reduce to a two-pointer pair search on the rest.

## Solution

<details>
<summary>Click to view the solution</summary>

**Code**:
```java
import java.util.*;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> triplets = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            // Once a is positive, b and c must also be positive — can't sum to 0
            if (nums[i] > 0) break;

            // Skip duplicate 'a' values
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == -nums[i]) {
                    triplets.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    // Skip duplicate 'b' values
                    while (left < right && nums[left] == nums[left - 1]) left++;
                } else if (sum < -nums[i]) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return triplets;
    }
}
```

**Approach**: Sort + Two Pointers (Fix-one, Search-pair)

**Intuition**: Fix one element $a$ and reduce the problem to finding a pair summing to $-a$ in the remaining sorted subarray. This is exactly the Pair Sum (Sorted) problem. Since the array is sorted, a two-pointer scan handles each fixed $a$ in $O(n)$ time, giving $O(n^2)$ overall.

**Mathematical/Other Foundation**:

For any valid triplet $[a, b, c]$:

$$a + b + c = 0 \implies b + c = -a$$

Fixing $a$ turns this into a pair-sum problem with target $-a$. After sorting, $b \geq a$ and $c \geq b$, so if $a > 0$ then $b + c > 0$ regardless — no valid triplet can exist, and we break early.

$$a > 0,\ b \geq a > 0,\ c \geq b > 0\ \Rightarrow\ a + b + c > 0$$

**Algorithm**:
1. Sort `nums` ascending.
2. Iterate index `i` from `0` to `n-1` (this is our fixed element `a = nums[i]`).
3. If `nums[i] > 0`, break — all remaining sums will be positive.
4. If `nums[i] == nums[i-1]` (and `i > 0`), skip to avoid duplicate `a`.
5. Set `left = i+1`, `right = n-1`. Run two-pointer search for pairs summing to `-nums[i]`:
   - If `nums[left] + nums[right] == target`: record triplet, advance `left`, skip consecutive duplicate `b` values.
   - If sum is too small: `left++`.
   - If sum is too large: `right--`.
6. Repeat until `left >= right`, then continue outer loop.

**Complexity**:
- Time: $O(n^2)$ — sorting is $O(n \log n)$; the outer loop runs $n$ times, each with an $O(n)$ two-pointer scan; $O(n \log n) + O(n^2) = O(n^2)$
- Space: $O(n)$ — sorting uses $O(n)$ auxiliary space (Timsort); the output list is not counted as auxiliary space

**Test Cases**:

| Input | Output | Notes |
|-------|--------|-------|
| `[]` | `[]` | Empty array |
| `[0]` | `[]` | Single element |
| `[1, -1]` | `[]` | Only two elements |
| `[0, 0, 0]` | `[[0,0,0]]` | All zeros |
| `[1, 0, 1]` | `[]` | No triplet sums to zero |
| `[0,0,1,-1,1,-1]` | `[[-1,0,1]]` | Deduplication required |

**Pro Tips**:
- You only need to deduplicate `a` and `b` — `c` is uniquely determined by $c = -(a+b)$, so it can't produce a duplicate independently.
- The two-pointer search here differs from a basic pair-sum: don't stop at the first match. Keep going until `left >= right` to collect all valid pairs for the current `a`.
</details>

## Solutions Link

- [[JAVA] Sort + Two Pointers (Fix-one, Search-pair)](solutions/_02_TripletSum_Solution01.java)
