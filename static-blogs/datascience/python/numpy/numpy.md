
在使用python进行数据分析时，经常需要对数值数组或者矩阵进行运算，所以对numpy类库进行了简要的总结，本文按照用法和例子的方式进行组织。

![](http://140.143.205.74/wordpress/wp-content/uploads/2018/11/numpy-3.png)

&nbsp;

------

# 1.用法
## 1.1. 属性

在描述常用方法之前，先看看Numpy中的ndarray(N-Dimension Array)组成结构。
ndarray是一个多维固定大小的同质数组，就是每个元素的类型相同，如果不相同的话，会做类型提升。ndarray的构造方法主要包含如下几个属性：
1. shape, 是数组的形状，一个tuple表示
2. dtype, 是data-type数组的元素类型， 可选
3. buffer,是用来填充数组的缓冲, 可选
4. offset,是填充数组的偏移量, 可选
5. strides,是数据在内存中的步幅，例如按照某一维走到下一个元素的距离, 可选
6. order, 是表示数组维的顺序，是以行为主(c-style)，还是以列为主(fortran-style)，枚举值{'C', 'F'}, 可选
```python
import numpy as np
x = np.ndarray(shape=(2,2), dtype=int, buffer=np.array([[1,2],[3,4]]), order='C')
y = np.ndarray(shape=(2,2), dtype=int, buffer=np.array([[1,2],[3,4]]), order='F')
x[0]
Out[6]: array([1, 2])
y[0]
Out[7]: array([1, 3])
x
Out[8]:
array([[1, 2],
       [3, 4]])
y
Out[9]:
array([[1, 3],
       [2, 4]])
```

### 1. 创建数组
```python
In[2]: import numpy as np

In[3]: np.array([1, 2, 3])
Out[3]: array([1, 2, 3])

In[4]: np.array([[1,2],[2,3]])
Out[4]:
array([[1, 2],
       [2, 3]])

In[5]: np.empty((2, 3))
Out[5]:
array([[1.07250874e-311, 1.07160847e-311, 1.07160847e-311],
       [1.07160819e-311, 1.07160819e-311, 1.07160933e-311]])

In[6]: np.ones((2, 3), dtype=float)
Out[6]:
array([[1., 1., 1.],
       [1., 1., 1.]])

In[7]: np.full((3, 5), 3.14)
Out[7]:
array([[3.14, 3.14, 3.14, 3.14, 3.14],
       [3.14, 3.14, 3.14, 3.14, 3.14],
       [3.14, 3.14, 3.14, 3.14, 3.14]])


In[8]: np.arange(0, 10, 2)
Out[8]: array([0, 2, 4, 6, 8])

In[9]: np.linspace(0, 10, 5)
Out[9]: array([ 0. ,  2.5,  5. ,  7.5, 10. ])


In[10]: np.random.random((2, 5))
Out[10]:
array([[0.795963  , 0.37792775, 0.50197372,
0.93732801, 0.94327573],[0.67339724, 0.63271335,
 0.2724838 , 0.69595332, 0.35007059]])

In[11]: np.random.normal(0, 1, (2, 2))
Out[11]:
array([[-0.51592923,  0.38310647],
       [-1.11787596,  1.02059943]])

In[12]: np.random.randint(0, 10, (3, 3))
Out[12]:
array([[5, 1, 4],
       [6, 9, 1],
       [4, 5, 3]])


In[13]: np.eye(4)
Out[13]:
array([[1., 0., 0., 0.],
       [0., 1., 0., 0.],
       [0., 0., 1., 0.],
       [0., 0., 0., 1.]])


## 支持匿名函数创建
In[14]: np.fromfunction(lambda i, j: i == j, (3, 3), dtype=int)
Out[14]:
array([[ True, False, False],
       [False, True, False],
       [False, False, True]])

## 用于三维网格的生成，将两个一维数组，变成二维，类似三维坐标系
>>> nx, ny = (3, 2)
>>> x = np.linspace(0, 1, nx)
>>> y = np.linspace(0, 1, ny)
>>> xv, yv = np.meshgrid(x, y)
>>> xv
array([[ 0. , 0.5, 1. ],
[ 0. , 0.5, 1. ]])
>>> yv
array([[ 0., 0., 0.],
[ 1., 1., 1.]])
```

### 2.操纵数组

#### 改变形状
```python
## 改变数组的形状
In[14]: np.arange(6).reshape((3, 2))
Out[14]:
array([[0, 1],
       [2, 3],
       [4, 5]])

## 扁平化数组
In[16]: x = np.array([[1, 2, 3], [4, 5, 6]])
In[17]: np.ravel(x)
Out[17]: array([1, 2, 3, 4, 5, 6])
In[18]: x
Out[18]:
array([[1, 2, 3],
       [4, 5, 6]])

## 也可以使用flatten
In[20]: x.flatten()
Out[20]: array([1, 2, 3, 4, 5, 6])
In[21]: x
Out[21]:
array([[1, 2, 3],
       [4, 5, 6]])
```

#### 转置
```python
In[23]: y = np.array([[1, 2], [3, 4]])
In[24]: np.transpose(y)
Out[24]:
array([[1, 3],
       [2, 4]])
In[25]: y.T
Out[25]:
array([[1, 3],
       [2, 4]])
```

#### 变换类型
```python
In[26]: y = np.array([1, 2])
In[27]: np.asarray(y, dtype=float)
Out[27]: array([1., 2.])
```

#### 合并与分离数组
```python
## 合并
In[28]: np.concatenate((np.array([1, 2]), np.array([1, 2])))
Out[28]: array([1, 2, 1, 2])
In[29]: np.stack((np.array([1, 2]), np.array([3, 4])))
Out[29]:
array([[1, 2],
       [3, 4]])
In[30]: np.column_stack((np.array((1, 2)), np.array((3, 4))))
Out[30]:
array([[1, 3],
       [2, 4]])

## 分离（分成几块，或者指定分割的索引）
In[31]: np.split(np.arange(9), 3)
Out[31]: [array([0, 1, 2]), array([3, 4, 5]), array([6, 7, 8])]
In[32]: np.split(np.arange(9), [3, 4])
Out[32]: [array([0, 1, 2]), array([3]), array([4, 5, 6, 7, 8])]
```

#### 数组内复制
```python
In[35]: np.tile(x, 2)
Out[35]: array([1, 2, 1, 2])
In[36]: np.tile(x, (2, 2))
Out[36]:
array([[1, 2, 1, 2],
       [1, 2, 1, 2]])
```

### 3.数组索引

```python
## i:j:step (start end step)
In[4]: x = np.arange(10)
In[5]: x
Out[5]: array([0, 1, 2, 3, 4, 5, 6, 7, 8, 9])
In[6]: x[1:7:2]
Out[6]: array([1, 3, 5])
In[7]: x[-2:10]
Out[7]: array([8, 9])
In[8]: x[-3:3:-1]
Out[8]: array([7, 6, 5, 4])
In[9]: x[5:]
Out[9]: array([5, 6, 7, 8, 9])

## :在多维数组下的使用，作为该维度的占位符，相当于对某一维进行抽取
In[19]: np.arange(16).reshape((2, 2, 2, 2))
Out[19]:
array([[[[ 0,  1],
         [ 2,  3]],

        [[ 4,  5],
         [ 6,  7]]],


       [[[ 8,  9],
         [10, 11]],

        [[12, 13],
         [14, 15]]]])
In[20]: x = np.arange(16).reshape((2, 2, 2, 2))
In[21]: x
Out[21]:
array([[[[ 0,  1],
         [ 2,  3]],

        [[ 4,  5],
         [ 6,  7]]],


       [[[ 8,  9],
         [10, 11]],

        [[12, 13],
         [14, 15]]]])
In[22]: x[:,0]
Out[22]:
array([[[ 0,  1],
        [ 2,  3]],

       [[ 8,  9],
        [10, 11]]])
In[23]: x[:, :, 0]
Out[23]:
array([[[ 0,  1],
        [ 4,  5]],

       [[ 8,  9],
        [12, 13]]])
In[24]: x[:, :, :, 0]
Out[24]:
array([[[ 0,  2],
        [ 4,  6]],

       [[ 8, 10],
        [12, 14]]])
In[25]: x[:, :, :, 1]
Out[25]:
array([[[ 1,  3],
        [ 5,  7]],

       [[ 9, 11],
        [13, 15]]])
In[26]: x[0, :]
Out[26]:
array([[[0, 1],
        [2, 3]],

       [[4, 5],
        [6, 7]]])
In[27]: x[0, :, :, :]
Out[27]:
array([[[0, 1],
        [2, 3]],

       [[4, 5],
        [6, 7]]])
In[28]: x[0, :, :, 0]
Out[28]:
array([[0, 2],
       [4, 6]])

## 简写版...省略号，可以用于拓展式的:,但是只能出现一次，有点像方法参数sum(...)
In[29]: x[..., 0] ##(等价于x[:,:,:,0])
Out[29]:
array([[[ 0,  2],
        [ 4,  6]],

       [[ 8, 10],
        [12, 14]]])
In[30]: x[..., 1] ##(等价于x[:,:,:,1])
Out[30]:
array([[[ 1,  3],
        [ 5,  7]],

       [[ 9, 11],
        [13, 15]]])
In[31]: x[0,...,0] ##(等价于x[0,:,:,0])
Out[31]:
array([[0, 2],
       [4, 6]])
```

#### 使用数组进行索引
```python
In[32]: x = np.arange(12).reshape(4, 3)
In[33]: x
Out[33]:
array([[ 0,  1,  2],
       [ 3,  4,  5],
       [ 6,  7,  8],
       [ 9, 10, 11]])
In[34]: rows = np.array([[0,0],[3,3]])
In[35]: cols = np.array([[0,2],[0,2]])
In[36]: x[rows,cols]
Out[36]:
array([[ 0,  2],
       [ 9, 11]])
```

### 4.迭代数组
```python
In[37]: a = np.arange(6).reshape(2, 3)
In[38]: for x in np.nditer(a):
   ...:     print(x, end=' ')
   ...:
0 1 2 3 4 5

```

### 5.通用函数
就是将函数应用于数组上的各个元素
```python
In[40]: a = np.arange(4)
In[41]: b = np.arange(4)
In[42]: np.add(a,b)
Out[42]: array([0, 2, 4, 6])
```

### 6.线性代数
```python
## 內积和外积
In[43]: np.inner(a,b)
Out[43]: 14
In[44]: np.outer(a,b)
Out[44]:
array([[0, 0, 0, 0],
       [0, 1, 2, 3],
       [0, 2, 4, 6],
       [0, 3, 6, 9]])

## 特殊形式的矩阵乘积
In[47]: np.matmul(np.outer(a,b),np.outer(a,b))
Out[47]:
array([[  0,   0,   0,   0],
      [  0,  14,  28,  42],
      [  0,  28,  56,  84],
      [  0,  42,  84, 126]])
```

### 7.聚集函数
```python
In[51]: x = np.arange(16).reshape(4, 4)
In[52]: x.sum(0)
Out[52]: array([24, 28, 32, 36])
In[53]: x.sum(1)
Out[53]: array([ 6, 22, 38, 54])
In[54]: x.argmin(0)
Out[54]: array([0, 0, 0, 0], dtype=int64)
```

# 2.例子
## 2.1 初等函数图像
```python
## Sin Cos
import numpy as np
import matplotlib.pyplot as plt

x = np.linspace(0, np.pi * 4, 200)
plt.plot(x, np.sin(x))
plt.plot(x, np.cos(x))
plt.show()
```
![](http://140.143.205.74/wordpress/wp-content/uploads/2018/11/basic-function-graph.png)

```python
## Heart Curve
import numpy as np
import matplotlib.pyplot as plt

x = np.linspace(0, np.pi * 4, 200)
plt.subplot(111, polar=True)
plt.plot(x, 1 + np.cos(x))
plt.plot(x, 1 - np.cos(x))
plt.show()
```
![](http://140.143.205.74/wordpress/wp-content/uploads/2018/11/heart-curve.png)

```python
## Lissajous Curve
import numpy as np
import matplotlib.pyplot as plt

t = np.linspace(0, np.pi * 4, 800)
x = np.sin(7 * t)
y = np.cos(11 * t)
plt.plot(x, y)
plt.show()
```
![](http://140.143.205.74/wordpress/wp-content/uploads/2018/11/Lissajous.png)

```python
import numpy as np
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D

fig = plt.figure()
ax = Axes3D(fig)
x = np.linspace(1, np.pi * 1.5, 400)
y = np.linspace(1, np.pi * 1.5, 400)
zx, zy = np.meshgrid(x, y)
zz = np.sin(zx ** 2 + zy ** 2) / (zx ** 2 + zy ** 2)
ax.plot_surface(zx, zy, zz)
plt.show()
```
![](http://140.143.205.74/wordpress/wp-content/uploads/2018/11/water-wave.png)


