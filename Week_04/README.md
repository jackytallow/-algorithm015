# 第4周总结

## 深度优先搜索/广度优先搜索的理解与解题思路
1. 关于搜索的遍历
- 每个节点都要访问一次，仅仅只要访问一次
- 节点访问的顺序不一样，深度优先常用栈，广度优先常用队列
2. 深度优先模板
- 关于DFS深度优先的递归写法
```
Set<Node> visited = new HashSet<>();

public void dfs(Node root, Set<Node> visited) {
    if (visited.contains(root)) {//terminator
        // already visited
        return;
    }
    visited.add(root);

    // process current node here.
    for (Node nextNode : node.children) {
        if (!visited.contains(nextNode)) {
            dfs(nextNode, visited);
        }
    }
}
```
- DFS非递归写法
```
public int[] dfs(Node root){ 
    if (root == null) {
        return new int[0];
    }
    Set<Node> visited = new HashSet<>();
    Stack<Node> stack = new Stack<>();
    stack.push(root);

    while (!stack.isEmpty()) { 
        Node node = stack.pop();
        visited.add(node);

        process (node); 
        List<Node> nodes = generate_related_nodes(node);
        stack.addAll(nodes); 

        // other processing work 
        // ...
    }
}
```

2. 广度优先的代码模板
- 关于BFS的写法
```
public void bfs(Node root) {
    Set<Node> visited = new HashSet<>();
    Queue<Node> queue = new ArrayQueue<>(); 
    queue.offer(root); 
    visited.add(root);

    while (!queue.isEmpty()) { 
       Node node = queue.poll();
       visited.add(node);

       process(node); 
       List<Node> nodes = generate_related_nodes(node);
       queue.addAll(nodes);
    }
	// other processing work 
	// ...
}
```


## 贪心算法
1. 简单介绍
- 贪心算法与动态规划的不同在于它对每个子问题的解决方案都做出选择，不能回退。
- 动态规划则保存以前的运算结果，并根据以前的结果对当前进行选择，有回退功能。
- 贪心：当下做局部最优判断
- 回溯：能够回退
- 动态规划：最优选择+回退
2. 一般使用的场景
- 贪心法可以解决一些最优问题，如：求图中的最小生成树、求哈夫曼编码等。然而对于工程和生活中的问题，贪心法一般不能得到我们所要求得答案。
- 一旦一个问题可以通过贪心法来解决，那么贪心法一般是解决这个问题的最好办法。由于贪心法的高效性以及其所求的的答案比较接近最优结果，贪心法也可以用作辅助算法或者直接解决一些要求结果不特别精确的问题。
 - 简单地说，问题能够分解成子问题来解决，子问题的最优解能递推到最终问题的最优解，这种子问题最优解称为最优子结构。（银币问题适合贪心，是因为它本身的面值是倍数，如果不是倍数就不行）
- 从后往前看是否可贪心，或从某一局部看

## 二分查找的理解与解题思路
1. 关键点和前提条件
 -  循环退出条件是low<=heigh,而不是low < high,循环要考虑值相等的情况
 -  mid取值，用位运算形式加速(之前没有考虑过)
 - low和high值的更新
 - 是一个单调递增或者递减的有序集合
 - 是存在上下界的，也能够通过索引访问

2. java模板
```
public int findIndexOf(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;
    while (left <= right) {
        int mid = left + ((right - left) >> 1);
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] > target) {
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }
    return -1;
}
```

- 使用二分查找，寻找一个半有序数组 [4, 5, 6, 7, 0, 1, 2] 中间无序的地方
```
/**
 * @author jacky
 * @version 1.0.0
 * @date 2020/9/20 关于二分查找
 */
public class BinarySearch {

    //查找一个半升序数组的中间无序下标（降序就翻转一下while里面的大于和小于符号）
    public int searchMinValueIndex(int[] nums) {
        //基准值，看mid索引的值小于基准值，大于基准点，说明mid左边是有序的，直接看mid右边，否则反之
        int point = nums[0];
        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            int mid = i + (j - i) / 2;
            //如果i + 1 = j，说明j的下标值就是最小值，
            //但是还需要判断i j的 索引值，如果j值大于i值，说明这里就是无序点，返回j即可
            if (i + 1 == j) {
                if (nums[i] > nums[j]) {
                    return j;
                }
                //j 值不大于i 说明就是一个完全有序数组。反正0即可
                return 0;
            }
            if (point > nums[mid]) {
                j = mid;
            } else {
                i = mid;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();
        long start = System.currentTimeMillis();

        long end = System.currentTimeMillis();
        int index = binarySearch.searchMinValueIndex(new int[]{4, 5, 6, 7, 0, 1, 2});
        System.out.println("下标" + index);
        System.out.println("所用时间：" + (end - start));
    }
}
```