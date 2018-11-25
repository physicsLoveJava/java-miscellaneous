
pandas（python Data Analysis Tools)提供了比Numpy更强大的结构数组，可以把这种结构数组想成Excel、SQL中的二维表，当然一维和更高维度的表，pandas也支持。

## 介绍pandas中的基本对象

pandas中的对象基本上都是numpy数组对象的拓展。通过例子来说明。
### Series
序列是一个一维的标签数组，而标签也叫作索引。

构造方式：
1. array
2. numpy array
3. dict

```python
import pandas as pd

s = pd.Series(np.random.randn(5), index=['a', 'b', 'c', 'd', 'e'])
t = pd.Series({'a': 1, 'b': 2, 'c': 3})
```

构造参数（数据+索引）

### DataFrame
DataFrame是二维标签数据结构，类似excel或者SQL表格

构造方式：
1. dict of 1-D array, lists, dicts, or Series
2. 2-D array

```python
import pandas as pd

s = pd.DataFrame({'one': pd.Series([1, 2, 3]), 'two': pd.Series([1, 2, 3])})
t = pd.DataFrame(np.random.randn(5, 2))
```

## 如何使用pandas中的DataFrame
其实本身pandas功能比较强大，一般官方文档讲的很详细，因为之前看了相关资料，想应用对比方法在学习上，所以这里将pandas操作与SQL进行类比，一方面也方便记忆，记忆关联。
需要注意的是，pandas更大的功能在第三章介绍。

这里首先列一下SQL中的主要方法:
1. 条件查询、字段投射
2. 分组排序、聚合统计
3. 合并表、拆分表

### 1.条件查询、字段投射

有如下字段的user表：
name, age, address

```python

p = {'name': 'tom', 'age': 20, 'address': 'abc'}
df = pd.DataFrame([p for t in range(6)], index=range(6))
//output
  address  age name
0     abc   20  tom
1     abc   20  tom
2     abc   20  tom
3     abc   20  tom
4     abc   20  tom
5     abc   20  tom

```

#### 条件查询、字段投射

```python/sql

## select * from user where name = 'xxx'
df[df['name'] == 'xxx']

## select name, address from user
df.loc[:, ['name', 'address']]

## select name, address from user where name = 'xxx'
df.loc[df['name'] == 'xxx', ['name', 'address']]

## select name, address from user where name = 'xxx' limit 0, 5
df.loc[df['name'] == 'xxx', ['name', 'address']].head(5)

## select name, address from user where name = 'xxx' limit len-4, 5
df.loc[df['name'] == 'xxx', ['name', 'address']].tail(5)
```

**总结**

作用 | 方法 | 结果
---------|----------|---------
 选择列 | df[col] | Series
 通过标签选择列 | df.loc[label] | Series
 通过索引位置选择列 | df.iloc[loc] | Series
 选择行 | df[5:10] | DataFrame
 通过条件进行选择 | df[cond] | DataFrame

#### 分组排序、聚合统计
首先列举一下常见的groupBy场景:
```python

select count(name) from user group by name
select count(name) from user group by name having name not in ("xxx")

```

以下是pandas简单的例子:
```python

df = pd.DataFrame({'X' : ['B', 'B', 'A', 'A'], 'Y' : [1, 2, 3, 4]})
df.groupby('X')
df.groupby(['X', 'Y'])
df.groupby('X').sum()

```

GroupBy之后的元素具有哪些常用的属性与方法:
1. .groups 分组之后的列表表示
2. .count .size 统计分组的大小
3. .describe 关于每个分组的统计
还有很多属性和方法

**聚合函数**

函数名称 | 作用
---------|----------
mean() | Compute mean of groups
sum() | Compute sum of group values
size() | Compute group sizes
count() | Compute count of group
std() | Standard deviation of groups
var() | Compute variance of groups
sem() | Standard error of the mean of groups
describe() | Generates descriptive statistics
first() | Compute first of group values
last() | Compute last of group values
nth() | Take nth value, or a subset if n is a list
min() | Compute min of group values
max() | Compute max of group values

举例:
```python

g = df.groupby('X')
g.describe()
g.aggregate(np.max)
g.aggregate(np.sum)
g.agg([np.sum, np.mean, np.std])

## 使用性能优化后的函数
g.mean()
g.max()
g.sum()
```

**转化形式**
可以对分组后的每个元素进行值的转换（运算、清洗、去重）
````python
g = df.groupby('X')
g.transforma(lamda x: x + 5)
g.dropna()
g.fillna()
````

#### 合并表、拆分表
在SQL中需要经常进行关联查询，而在pandas中可以使用类似的方式实现。

```python
## dfx typeof DataFrame
pd.concat([df1, df2, df3])
pd.concat([df1, df2], axis=1, join='inner', ignore_index=True)

```

而标准的类似SQL的写法有如下方法:
```python
pd.merge(left, right, how='inner', on=None, left_on=None, right_on=None,
left_index=False, right_index=False, sort=True,
suffixes=('_x', '_y'), copy=True, indicator=False,
validate=None)
```
通过how的参数，我们可以改变不同的join类型，名称和SQL保持一致,有left,right,outer,inner。
on参数用于指定join on的键值。

### 重新改变形状和数据透视表
数据透视表可以分析不同列之间的关系

```python
In[12]: df
Out[12]:
   age  height name weight
0   20     185  tim     50
1   20     180  tim     50
2   20     180  tim     50
3   20     180  tim     50
4   20     180  tim     50
5   20     180  tim     50
In[13]: df.loc[1, 'height']=182
In[14]: df.pivot(columns='height', values='weight')
Out[14]:
height  180  182  185
0       NaN  NaN   50
1       NaN   50  NaN
2        50  NaN  NaN
3        50  NaN  NaN
4        50  NaN  NaN
5        50  NaN  NaN
```

而melt操作则是将一条记录进行属性拆分，
比如：{name:'Tom', age: 20, height: 180}
melt(id_vars=['name'])
-> {name: 'Tom', variable: 'age', value: 20} ,
{name: 'Tom', variable: 'height', value: 180}




