## 第七周总结

## Trie总结
1. Trie介绍
- 单词查找树
- 应用于模糊搜素，统计和排序大量的字符串
- 优点:最大限度减少无效字符串比较，查询效率比哈希表高
2. 基本性质
- 结点本身不存完整单词
- 从根节点到某一结点。路径上经过的字符连接起来，为该结点对应的字符串
- 每个结点的所有子节点路径代表的的字符串都不相同
3. 核心思想
- Trie树的核心思想是空间换时间
- 利用字符串的公共前缀来降低查询时间的开销以达到提高效率的目的


## Trie的模板
```java
//Java
class Trie {
    private boolean isEnd;
    private Trie[] next;
    /** Initialize your data structure here. */
    public Trie() {
        isEnd = false;
        next = new Trie[26];
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word == null || word.length() == 0) return;
        Trie curr = this;
        char[] words = word.toCharArray();
        for (int i = 0;i < words.length;i++) {
            int n = words[i] - 'a';
            if (curr.next[n] == null) curr.next[n] = new Trie();
            curr = curr.next[n];
        }
        curr.isEnd = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node != null && node.isEnd;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Trie node = searchPrefix(prefix);
        return node != null;
    }

    private Trie searchPrefix(String word) {
        Trie node = this;
        char[] words = word.toCharArray();
        for (int i = 0;i < words.length;i++) {
            node = node.next[words[i] - 'a'];
            if (node == null) return null;
        }
        return node;
    }
}
```

```kotlin
internal class Trie {
    private var isEnd: Boolean = false
    private val next: Array<Trie?> = arrayOfNulls<Trie?>(26)

    /** Inserts a word into the trie.  */
    fun insert(word: String?) {
        if (word == null || word.length == 0) return
        var curr: Trie? = this
        val words: CharArray = word.toCharArray()
        for (i in words.indices) {
            val n = words[i] - 'a'
            if (curr!!.next[n] == null) curr.next[n] = Trie()
            curr = curr.next[n]
        }
        curr!!.isEnd = true
    }

    /** Returns if the word is in the trie.  */
    fun search(word: String): Boolean {
        val node = searchPrefix(word)
        return node != null && node.isEnd
    }

    /** Returns if there is any word in the trie that starts with the given prefix.  */
    fun startsWith(prefix: String): Boolean {
        val node = searchPrefix(prefix)
        return node != null
    }

    private fun searchPrefix(word: String): Trie? {
        var node: Trie? = this
        val words: CharArray = word.toCharArray()
        for (i in words.indices) {
            node = node!!.next[words[i] - 'a']
            if (node == null) return null
        }
        return node
    }

}

```
 
## 并查集 Disjoint Set
1. 基本思想
- makeSet(s)：建立一个新的并查集，其中包含 s 个单元素集合。
- unionSet(x, y)：把元素 x 和元素 y 所在的集合合并，要求 x 和 y 所在的集合不相交，如果相交则不合并。- 
- find(x)：找到元素 x 所在的集合的代表，该操作也可以用于判断两个元素是否位于同一个集合，只要将它们各自的代表比较一下就可以了。
2. 并查集代码模板
```java
import java.util.Scanner;

public class UnionFind {

    public static int[] parent;

    //获得该集合的老大，带路径压缩
    public static int get_boss(int v) {
        if (parent[v] == v)
            return v;
        else {
            parent[v] = get_boss(parent[v]);
            return parent[v];
        }
    }

    //合并两个集合
    public static void Merge(int v, int u) {
        int t1 = get_boss(v);
        int t2 = get_boss(u);
        parent[t2] = t1;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();   //n个人
        int m = sc.nextInt();   //m个关系

        parent = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            Merge(a, b);
        }

        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (parent[i] == i) {
                count++;
            }
        }

        System.out.println("团队数量：" + count);
    }

}
```