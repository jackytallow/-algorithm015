## 毕业总结
不知不觉9周的算法训练营已经接近尾声，这期间训练营对自己的影响有二方面
- 第一呢，是自己对算法与数据结构的态度与认知上，从一开始觉得很难，到学习超哥的五毒神掌后，接受其中的转变，有了一个大致的方向，对整体脉络的把控
- 一方面就是知识本身，下面将数据结构与算法总体回顾下

### 数据结构
1. 一维
- 基础: 数组 array (string)，链表 linked list
- 高级: 栈 stack，队列 queue，双端队列 deque，集合 set，映射 map (hash or map)，etc
- 涉及题目：
栈 stack： 括号匹配问题、直方图、接雨水
队列 queue： 滑动窗口

2. 二维
- 基础: 树 tree，图 graph
- 高级: 二叉搜索树 binary search tree (red-black tree,AVL)，堆 heap，并查集 disjoint set，字典树 Trie，etc

3. 特殊
- 位运算 Bitwise，布隆过滤器 BloomFilter
-  LRU Cache

4. 时间复杂度图
![时间图](https://www.bigocheatsheet.com/)

5. 主定理
![主定里](https://en.wikipedia.org/wiki/Master_theorem_(analysis_of_algorithms))
- 一维数组二分查找：每次排除一半，故为O(log n)
- 二叉树的遍历：可以理解成每个节点被访问且仅访问一次，故为O(n)
- 二维矩阵的二分查找：O(n)
- 归并排序：O(n logn)


### 算法
- if-else，switch ——> branch
- for，while loop ——> literation
- 递归 Recursion (Divide & Conquer ,Backtrace)
所有高级算法或数据结构最后都会转换成以上三种
- 搜索 Search:深度优先搜索 Depth first search, 广度优先搜索 Breadth first search，A*,etc
- 动态规划 Dynamic Programming (递归+备忘录 或 迭代+DP方程)
- 二分查找 Binary Search
- 贪心 Greedy
- 数学 Math，几何 Geometry

### 学习要点
- 基本功是区别业余和职业选手的根本。深厚功底来自于 — 过遍数
- 最大的误区：只做一遍
- 五毒神掌
- 刻意练习 - 练习缺陷弱点地方、不舒服、枯燥
- 反馈 - 看题解、看国际版的高票回答

### 切题四件套
- Clarification
 Possible Solutions
compare (time/space)
- optimal （加强）
- Coding（多写）
Test cases
 - 五毒神掌
第一遍
五分钟：读题 + 思考
直接看解法：多看几种，比较解法优劣
背诵、默写好的解法
第二遍
马上自己写 ——> Leetcode提交
多种解法比较、体会 ——> 优化！
第三遍
过了一天后，再重复做题
不同解法的熟练程度——>专项练习
第四遍
过了一周后：反复回来练习相同的题目
第五遍
面试前一周恢复性训练