# Pair Sum — Sorted

> Time Limit: 1sec
> Space Limit: 256MB
> Difficulty: Medium
> Link: [LeetCode 167 – Two Sum II](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/)

## Description

You're given a sorted integer array (ascending order) and a target value. Find any two elements that add up to the target and return their indexes. The order of the indexes in the result doesn't matter. Return an empty array if no valid pair exists.

**Example**:

```
Input:  nums = [-5, -2, 3, 4, 6], target = 7
Output: [2, 3]
// nums[2] + nums[3] = 3 + 4 = 7
```

```
Input:  nums = [1, 1, 1], target = 2
Output: [0, 1]
// [0, 2] and [1, 2] are equally valid
```

**Input Format**:

- `nums`: an integer array sorted in non-decreasing order
- `target`: an integer

**Output Format**:
An integer array `[i, j]` where `nums[i] + nums[j] == target`, or `[]` if no pair exists.

**Constraints**:

- Array is sorted in non-decreasing order
- May contain negative values and duplicates
- Any valid pair is acceptable as output

**Code Template**:

```java
public int[] pairSumSorted(int[] nums, int target) {
    // your code here
}
```

**Hint**: Place one pointer at each end of the array. The sorted order tells you exactly which direction to move each pointer when the sum is off.

## Solution

<details>
<summary>Click to view the solution</summary>

**Code**:

```java
public int[] pairSumSorted(int[] nums, int target) {
    int left = 0, right = nums.length - 1;

    while (left < right) {
        int sum = nums[left] + nums[right];

        if (sum < target) {
            left++;      // too small — move left pointer right to increase sum
        } else if (sum > target) {
            right--;     // too large — move right pointer left to decrease sum
        } else {
            return new int[]{left, right};
        }
    }

    return new int[]{};
}
```

**Approach**: Two Pointers (Inward Traversal)

**Intuition**: The brute force scans every pair at $O(n^2)$. The word "sorted" changes everything. If the current sum is too small, moving the left pointer right is the only move that increases it — moving the right pointer left would only make things worse. This asymmetry lets us rule out candidates decisively at each step rather than checking them exhaustively.

**Mathematical/Other Foundation**:

The algorithm is correct because it never skips a valid pair. Suppose a valid pair exists at indexes $i^*$ and $j^*$ with $i^* < j^*$. Claim: at any point where $\text{left} \leq i^*$ and $\text{right} \geq j^*$, the algorithm will not eliminate both $i^*$ and $j^*$ in the same step.

- If $\text{nums}[\text{left}] + \text{nums}[\text{right}] < \text{target}$, we increment `left`. Since the array is sorted and $\text{right} \geq j^*$, we have $\text{nums}[\text{left}] + \text{nums}[j^*] \leq \text{nums}[\text{left}] + \text{nums}[\text{right}] < \text{target}$. So `left` is not $i^*$ yet — advancing it is safe.
- Symmetrically, if the sum exceeds the target, decrementing `right` cannot skip $j^*$.

Each step moves at least one pointer inward. Both pointers start $n - 1$ positions apart, so the total steps is bounded by $n$, giving $O(n)$ time.

**Algorithm**:

1. Set `left = 0`, `right = nums.length - 1`.
2. While `left < right`:- Compute `sum = nums[left] + nums[right]`.- If `sum < target`, increment `left`.- If `sum > target`, decrement `right`.- If `sum == target`, return `[left, right]`.
3. Return `[]` — pointers crossed without finding a pair.

**Complexity**:

- Time: $O(n)$ — each iteration moves at least one pointer inward; combined, the two pointers cover at most $n$ positions.
- Space: $O(1)$ — only three variables (`left`, `right`, `sum`) are used regardless of input size.

**Test Cases**:

| Input | Output | Notes |
| --- | --- | --- |
| `nums=[], target=0` | `[]` | Empty array |
| `nums=[1], target=1` | `[]` | Single element — no pair possible |
| `nums=[2,3], target=5` | `[0,1]` | Minimal valid pair |
| `nums=[2,4], target=5` | `[]` | No pair exists |
| `nums=[2,2,3], target=5` | `[0,2]` or `[1,2]` | Duplicates — any valid answer works |
| `nums=[-1,2,3], target=2` | `[0,2]` | Negative number in the pair |
| `nums=[-3,-2,-1], target=-5` | `[0,1]` | Both values negative |

**Pro Tips**:

- When you see "pair sum" on a sorted array, reach for two pointers before a hash map. Both are $O(n)$ time, but two pointers costs $O(1)$ space versus $O(n)$ — a meaningful win when memory is tight.
- In Java, returning `new int[]{}` for "not found" is idiomatic, but returning `null` makes the "found vs not found" check slightly cheaper for the caller. Confirm with the interviewer which they prefer before committing.
- The word **"sorted"** is the signal. Without it, you need a hash map ($O(n)$ space). With it, you get $O(1)$ space for free. Never ignore constraints — they are hints.
</details>

## Solutions Link

- [[JAVA] Two Pointers (Inward Traversal)](solutions/_01_PairSumSorted_Solution01.java)
