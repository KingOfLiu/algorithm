#### 题解

样例1：

a = [1 7 1 4 4 5 6]

b = [1 1 4 4 5 7 6]



#### 分析

1.根据题目要求给定两个数组a、b，判断是否对a的子数组进行排序，得到b。

2.





#### 实现分析

1.创建n个对列（或者栈）q，q[i]中的元素为数组a元素（i），对应的下标。

例如，根据样例得到q完整数据为：

q[0] = []

q[1] = [0, 2]  	// a[0] = 1, a[2] = 1

q[2] = []

q[3] = []

q[4] = [3, 4] 	// a[3] = 4 a[4] = 4

q[5] = [5]		// a[5] = 5

q[6] = [6]

q[7] = [1]		// a[1] = 7



2.因为涉及到区间的修改、查询操作，所以需要使用线段树——建立线段树

根据样例建立后的线段树结构为：














3.依次遍历b

​	①。如果b[i]对应q中的元素对列为空，则表示数组a中没有相应的数组b中元素，所以返回NO

​	②。获取到b[i]对应q的对列，取到对列头部元素X（出队）。

​	③。
