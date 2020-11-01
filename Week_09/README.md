# 第九周学习笔记

## 高级动态规划

1. 递归的模板
```
def recursion(level,param1,param2,...):
	# 递归终止条件
	if level > MAX_LEVEL: 
	   process_result 
	   return 
	# 处理当前层逻辑
    process(level, data...) 

    # 往下一层
    self.recursion(level + 1, p1, ...) 

    # 清理当前层

```

2. 分治的模板
```
ef divide_conquer(problem, param1, param2, ...): 
  # 递归终止条件
  if problem is None: 
	print_result 
	return 

  # 处理当前层逻辑(分解子问题)
  data = prepare_data(problem) 
  subproblems = split_problem(problem, data) 

  # 往下一层（解决子问题）
  subresult1 = self.divide_conquer(subproblems[0], p1, ...) 
  subresult2 = self.divide_conquer(subproblems[1], p1, ...) 
  subresult3 = self.divide_conquer(subproblems[2], p1, ...) 
  …

  # 合并子结果（合并结果）
  result = process_result(subresult1, subresult2, subresult3, …)
	
  # 清理当前层
```

3. DP模板
```
function DP():
	dp = [][] # ⼆维情况
	for i = 0 .. M { 
		for j = 0 .. N { 
		dp[i][j] = _Function(dp[i’][j’]…)

		} 
	}
	return dp[M][N];
```

### 关键点
- 动态规划和递归或者分治没有根本上的区别（关键看有无最优的子结构）
- 共性：找到重复子问题
- 差异性：最优子结构、中途可以淘汰次优解

### 复杂度来源
- 状态拥有更多维度（二维、三维、或者更多、甚至需要压缩）
- 状态方程更加复杂

## 针对状态转移可以参考股票问题
```
状态定义
dp[i] [k][ 0 or 1]  (0 <= i <= n-1, 1 <= k <= K)
```
i 为天数
k 为最多交易次数 （买入并卖出算一次交易）
[0,1] 为是否持有股票
总状态数: n * K * 2 种状态
第i天，之前交易k次，今天持有股票(1) 或 今天 不持有股票 的最大收益
- 那么它的DP状态方程为：
```
dp[i][k][s] = max(buy, sell, rest)
3 层嵌套循环
for 0 <= i < n:
    for 1 <= k <= K:
        for s in {0, 1}:
             dp[i][k][s] = max(buy, sell, rest)

```

- 具体状态转移方程为
```
dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i]) 
              max(       选择 rest ,        选择 sell )
```
- 可以解释为：
```
今天(第i天)我没有持有股票，有两种可能:
1. 我昨天(第i-1天)就没有持有，然后今天选择 rest，所以我今天还是没有持有;
dp[i-1][k][0]
2. 我昨天(第i-1天)持有股票，但是今天我 sell 了，所以我今天没有持有股票了。（买入并卖出算一次交易，所以只卖出 交易k不变）
dp[i-1][k][1] + prices[i]
```

整体解决的代码如下：
```
# @author:leacoder 
# @des: 动态规划 买卖股票的最佳时机 IV(通用型) 


class Solution:
    def maxProfit(self, k: int, prices: List[int]) -> int:
        n = len(prices)
        if n <= 1: return 0
        if k > int(n / 2):  # 会超时
            # k = int(n/2)
            return self.greedy(prices)  # 使用贪心

        maxprof = 0
        profit = [[[0 for _ in range(2)] for _ in range(k + 1)] for _ in
                  range(0, len(prices))]  # DP[ii][kk][0] 第ii天完成kk次操作无股票 DP[ii][kk][1]第ii天完成kk次操作有股票 prices[ii] 第ii天股票价格

        for i in range(0, k + 1):
            profit[0][i][0] = 0  # 第 1 天 操作i 次 没有股票，所以初始值为 0
            profit[0][i][1] = - prices[0]  # 第 1 天 操作i 次 有股票， 所以初始值为 - prices[ii]

        for ii in range(1, len(prices)):  # 天数
            for kk in range(0, k + 1):  # 交易次数
                if kk == 0:  #
                    profit[ii][kk][0] = profit[ii - 1][kk][0]  # 0 次交易 今天利润 == 前一天利润
                else:
                    # 今天完成kk次操作无股票  max(前一天无股票今天不交易，前一天有股票今天卖出)  买卖一次算一笔交易 以买入算一次交易 故 profit[ii - 1][kk ][1] + prices[ii]
                    profit[ii][kk][0] = max(profit[ii - 1][kk][0], profit[ii - 1][kk][1] + prices[ii])
                # 今天完成kk次操作有股票  max(前一天有股票今天不交易，前一天无股票今天买入) 以买入算一次交易
                profit[ii][kk][1] = max(profit[ii - 1][kk][1], profit[ii - 1][kk - 1][0] - prices[ii])
                maxprof = max(maxprof, profit[ii][kk][0])
        return maxprof

    def greedy(self, prices: List[int]) -> int:
        max = 0
        for i in range(1, len(prices)):
            if prices[i] > prices[i - 1]:
                max += prices[i] - prices[i - 1]
        return max
```

## 字符串匹配算法

### 暴力法
```java
public static int forceSearch(String txt, String pat) {
	int M = txt.length();
	int N = pat.length();
	for (int i = 0; i <= M - N; i++) {
		int j;
		for (j = 0; j < N; j++) {
			if (txt.charAt(i + j) != pat.charAt(j))
			break;
		}
		if (j == N) {
			return i;
		}
		// 更加聪明？
		// 1. 预先判断– hash(txt.substring(i, M)) == hash(pat)
		// 2. KMP
	}
	return -1;
}
```

### Rabin-Karp 算法
在朴素算法中(暴力)，我们需要挨个比较所有字符，才知道目标字符串中是否包含子串。那么， 是否有别的方法可以用来判断目标字符串是否包含子串呢？（也就是加速第二层循环）
确实存在一种更快的方法。为了避免挨个字符对目标字符串和子串进行比较， 我们可以尝试一次性判断两者是否相等。因此，我们需要一个好的哈希函数（hash function）。 通过哈希函数，我们可以算出子串的哈希值，然后将它和目标字符串中的子串的哈希值进行比较。 这个新方法在速度上比暴力法有显著提升。

- 对于Rabin-Karp 算法的思想：
1. 假设子串的长度为 M (pat)，目标字符串的长度为 N (txt)
2. 计算子串的 hash 值 hash_pat
3. 计算目标字符串txt中每个长度为 M 的子串的 hash 值（共需要计算 N-M+1次）
4. 比较 hash 值：如果 hash 值不同，字符串必然不匹配; 如果 hash 值相同，还需要使用朴素算法再次判断
关键在于 3 中字符串的哈希值如何计算？如果采用常规计算hash，起不到加速。想象成滑动窗口，中间不变部分不再重复计算，只是处理 进出滑动窗口的字符的hash

### 	KMP算法
KMP算法（Knuth-Morris-Pratt）的思想就是，当子串与目标字符串不匹配时，其实你已经知道了前面已经匹配成功那 一部分的字符（包括子串与目标字符串）。KMP 算法的想法是，设法利用这个已知信息，不要把“搜索位置” 移回已经比较过的位置，继续把它向后移，这样就提高了效率。（加速二层循环，暴力法中每次比较后移一个字符，KMP算法通过已知信息找出能够后移的n个字符后移n个字符从而加速）