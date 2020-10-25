# 第八周学习笔记

## 位运算总结以及学习体会

### 基本概念
- 对于整数类型变量中的某一位，或若干位进行操作,下列是6种操作符
1. & 按位与
全1则1，有0则0
例：21&18 = 16
即： 00010101&00010010 = 0001000
2. | 按位或
全0则0，有1则1
例：21|18 = 23
3. ^ 按位异或
不同则1，相同则0
作用;
- 将变量中的某些位取反
- 简单数据加密(穷举法可证)
- 交换两个变量(穷举法可证)
- 开关问题经常使用
4. ~ 按位非
0变1，1变0
5. << 左运算符
a << b：将a各二进制位部左移b位后得到的值，左移越界丢弃，低位补，0a的全值不因运算而改变
6. >> 右移运算符
a >> b：将a各二进制位全部右移b位后得到的值。溢出最右边的值就被丢弃
大多数编译器规定：右移时原符号位为1，右移则补1，原符号位为0，右移就补0

### 位运算要点
- 判断奇偶：
x % 2 == 1 —> (x & 1) == 1
x % 2 == 0 —> (x & 1) == 0
• x >> 1 —> x / 2
即： x = x / 2; —> x = x >> 1;
mid = (left + right) / 2; —> mid = (left + right) >> 1;
• X = X & (X-1) 清零最低位的 1
• X & -X => 得到最低位的 1
• X & ~X => 0

## 布隆过滤器 Bloom Filter
一个很长的二进制向量和一系列随机映射函数。
布隆过滤器可以用于检索一个元素是否在一个集合中。
优点是空间效率和查询时间都远远超过一般的算法，缺点是有一定的误识别率和删除困难。
判断 不存在 必定不存在； 判断 存在 不一定存在

布隆过滤器(Bloom Filter)的原理和实现,详情可见->
[布隆过滤器(Bloom Filter)的原理和实现](https://www.cnblogs.com/cpselvis/p/6265825.html)
布隆过滤器(Bloom Filter)的原理和实现
- 实现代码.参考c语言实现
```c
from bitarray import bitarray
import mmh3

# bitarray 数组 存放的是二进制位 ， 二进制数组

class BloomFilter:
    def __init__(self, size, hush_num):
        """
        :param size:   bitarray 的大小
        :param hush_num:    一个元素 被分成多少个二进制位
        """
        self.size = size
        self.hash_num = hash_num
        self.bit_array = bitarray(size)
        self.bit_array.setall(0)

    def add(self, s):
        """
        :param s:  添加的元素
        """
        for seed in range(self.hash_num):
            result = mmh3.hash(s, seed) % self.size
            # result 为 bit_array 二进制数组的索引
            self.bit_array[result] = 1

    def lookup(self, s):
        for seed in range(self.hash_num):
            result = mmh3.hash(s, seed) % self.size
            if self.bit_array[result] == 0:
                return "Nope"
        return "Probably"

bf = BloomFilter(500000, 7)
bf.add("1234567890")
print (bf.lookup("1234567890"))
print (bf.lookup("123456789"))
```

## LRU Cache
LRU - least recently used
1. 两个要素： 大小 、替换策略
2. Hash Table + Double LinkedList 复杂度
 - O(1) 查询
- O(1) 修改、更新
3. 实现方式
```c
from collections import OrderedDict
class LRUCache(object):
    def __init__(self, capacity):
        """
        :param capacity:   容量大小
        """
        self.dic = OrderedDict()    # OrderedDict 字典的子类，保存了他们被添加的顺序
        self.remain = capacity

    def get(self, key):
        if key not in self.dic:
            return -1
        v = self.dic.pop(key)
        self.dic[key] = v  # key 的使用被更新了
        return v

    def put(self, key, value):
        if key in self.dic:  # 是否已存在
            self.dic.pop(key)
        else:
            if self.remain > 0:     # 是否已满
                self.remain -= 1
            else:  # 已满 按 FIFO 先进先出的顺序 返回键值对。
                self.dic.popitem(last=False)
        self.dic[key] = value
```

## 八大排序
1. 冒泡排序
- 它重复地走访过要排序的数列，一次比较两个元素，如果他们的顺序错误就把他们交换过来。走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。这个算法的名字由来是因为越小的元素会经由交换慢慢“浮”到数列的顶端。
```java
 /*
 * 冒泡排序
 * 比较相邻的元素。如果第一个比第二个大，就交换他们两个。  
 * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。  
 * 针对所有的元素重复以上的步骤，除了最后一个。
 * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。 
 * @param numbers 需要排序的整型数组
 */
public static void bubbleSort(int[] numbers)
{
    int temp = 0;
    int size = numbers.length;
    for(int i = 0 ; i < size-1; i ++)
    {
    for(int j = 0 ;j < size-1-i ; j++)
    {
        if(numbers[j] > numbers[j+1])  //交换两数位置
        {
        temp = numbers[j];
        numbers[j] = numbers[j+1];
        numbers[j+1] = temp;
        }
    }
    }
}
```

2. 快速排序
- 快速排序的基本思想：
通过一趟排序将待排序记录分割成独立的两部分，其中一部分记录的关键字均比另一部分关键字小，则分别对这两部分继续进行排序，直到整个序列有序。
```java
 /**
 * 查找出中轴（默认是最低位low）的在numbers数组排序后所在位置
 * 
 * @param numbers 带查找数组
 * @param low   开始位置
 * @param high  结束位置
 * @return  中轴所在位置
 */
public static int getMiddle(int[] numbers, int low,int high)
{
    int temp = numbers[low]; //数组的第一个作为中轴
    while(low < high)
    {
    while(low < high && numbers[high] >= temp)
    {
        high--;
    }
    numbers[low] = numbers[high];//比中轴小的记录移到低端
    while(low < high && numbers[low] < temp)
    {
        low++;
    }
    numbers[high] = numbers[low] ; //比中轴大的记录移到高端
    }
    numbers[low] = temp ; //中轴记录到尾
    return low ; // 返回中轴的位置
}

 /**
 * 
 * @param numbers 带排序数组
 * @param low  开始位置
 * @param high 结束位置
 */
public static void quickSort(int[] numbers,int low,int high)
{
    if(low < high)
    {
    　　int middle = getMiddle(numbers,low,high); //将numbers数组进行一分为二
    　　quickSort(numbers, low, middle-1);   //对低字段表进行递归排序
    　　quickSort(numbers, middle+1, high); //对高字段表进行递归排序
    }

}

 /**
 * 快速排序
 * @param numbers 带排序数组
 */
public static void quick(int[] numbers)
{
    if(numbers.length > 0)   //查看数组是否为空
    {
    quickSort(numbers, 0, numbers.length-1);
    }
}
```

3. 选择排序
- 基本思想：在要排序的一组数中，选出最小的一个数与第一个位置的数交换；然后在剩下的数当中再找最小的与第二个位置的数交换，如此循环到倒数第二个数和最后一个数比较为止。
- 代码实现
```java
 /**
 * 选择排序算法
 * 在未排序序列中找到最小元素，存放到排序序列的起始位置  
 * 再从剩余未排序元素中继续寻找最小元素，然后放到排序序列末尾。 
 * 以此类推，直到所有元素均排序完毕。 
 * @param numbers
 */
public static void selectSort(int[] numbers)
{
int size = numbers.length; //数组长度
int temp = 0 ; //中间变量

for(int i = 0 ; i < size ; i++)
{
    int k = i;   //待确定的位置
    //选择出应该在第i个位置的数
    for(int j = size -1 ; j > i ; j--)
    {
    if(numbers[j] < numbers[k])
    {
        k = j;
    }
    }
    //交换两个数
    temp = numbers[i];
    numbers[i] = numbers[k];
    numbers[k] = temp;
}
}
```
 4. 插入排序
 - 基本思想：每步将一个待排序的记录，按其顺序码大小插入到前面已经排序的字序列的合适位置（从后向前找到合适位置后），直到全部插入排序完为止。
```java
 /**  
 * 插入排序
 * 
 * 从第一个元素开始，该元素可以认为已经被排序
 * 取出下一个元素，在已经排序的元素序列中从后向前扫描 
 * 如果该元素（已排序）大于新元素，将该元素移到下一位置  
 * 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置  
 * 将新元素插入到该位置中  
 * 重复步骤2  
 * @param numbers  待排序数组
 */  
public static void insertSort(int[] numbers)
{
int size = numbers.length;
int temp = 0 ;
int j =  0;

for(int i = 0 ; i < size ; i++)
{
    temp = numbers[i];
    //假如temp比前面的值小，则将前面的值后移
    for(j = i ; j > 0 && temp < numbers[j-1] ; j --)
    {
    numbers[j] = numbers[j-1];
    }
    numbers[j] = temp;
}
}
```

5. 希尔算法
- 基本思想：
先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，待整个序列中的记录“基本有序”时再对全体记录进行依次直接插入排序
- 操作方法
```
1、选择一个增量序列t1，t2，…，tk，其中ti>tj，tk=1；
2、按增量序列个数k，对序列进行k 趟排序；
3、每趟排序，根据对应的增量ti，将待排序列分割成若干长度为m 的子序列，分别对各子表进行直接插入排序。仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
```
- 实现代码
```java
/**希尔排序的原理:根据需求，如果你想要结果从大到小排列，它会首先将数组进行分组，然后将较大值移到前面，较小值
 * 移到后面，最后将整个数组进行插入排序，这样比起一开始就用插入排序减少了数据交换和移动的次数，可以说希尔排序是加强
 * 版的插入排序
 * 拿数组5, 2, 8, 9, 1, 3，4来说，数组长度为7，当increment为3时，数组分为两个序列
 * 5，2，8和9，1，3，4，第一次排序，9和5比较，1和2比较，3和8比较，4和比其下标值小increment的数组值相比较
 * 此例子是按照从大到小排列，所以大的会排在前面，第一次排序后数组为9, 2, 8, 5, 1, 3，4
 * 第一次后increment的值变为3/2=1,此时对数组进行插入排序，
 *实现数组从大到小排
 */

    public static void shellSort(int[] data) 
    {
        int j = 0;
        int temp = 0;
        //每次将步长缩短为原来的一半
        for (int increment = data.length / 2; increment > 0; increment /= 2)
        {
        for (int i = increment; i < data.length; i++) 
        {
            temp = data[i];
            for (j = i; j >= increment; j -= increment) 
            {
            if(temp > data[j - increment])//如想从小到大排只需修改这里
            {   
                data[j] = data[j - increment];
            }
            else
            {
                break;
            }
            
            } 
            data[j] = temp;
        }
    
        }
    }
```
