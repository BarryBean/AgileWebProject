# AgileWebProject
XJTU-AgileWebDevelopment 

# Work1

## 实现
int会超出边界，所以使用double做返回值或者取模1e9+7，写了两个函数
## 要求
实现 fibonacci 数列，要求(以Java为例)如下
- 不要提交依赖包等文件
- 分两次提交，第一次提交README.md，第二次提交代码
- 函数名为 Fibonacci.of()
- of是一个静态方法，入参是int，返回值是int
- 在main函数用循环打印1~200的Fibonacci数列

# Work2

## 实现
模拟问题。直接暴力模拟结果....

基础思路是：
1. 提取出五张牌的数字和花色。
2. 判断牌型。用一个数组存储黑白的牌型。
- 先根据花色判断同花；
- 再根据数字判断顺子；
- 同花和顺子就是同花顺；
- 再判断其他牌型，如对子，三条，四条；
- 用set和map遍历数组，set.size()=5判断高牌，4判断对子，3判断三条和俩对子，2判断四条和三带二。
3. 若牌型不同，直接比较牌型的索引，索引大的获胜。
4. 牌型相同则根据牌型规则比较大小。
- 顺子和同花顺比较max牌；
- 三条，葫芦，四条比较重复牌的大小；
- 对子和俩对子，有对子比较对子，没有就比较单牌；
- 高牌和同花，从大到小比较单牌。

# Work3

## 要求

构建一个Restful API，能够完成Todo list的以下功能。

- 返回所有Todo任务
- 创建一个新的Todo任务
- 返回一个指定ID的Todo任务
- 删除一个Todo任务

## 实现

SpringBoot实现，数据ORM为MyBatis+Druid+MySQL，使用MyBatis逆向程序生成。封装了一个消息类，在更新、提交、删除时，返回对应成功或失败的消息信息。

实现了以下功能。

```java
GET /tasks/
POST /tasks/
GET /tasks/{id}
DELETE /tasks/{id}
```

# Work4

## 要求

用react实现一个Todo List

- 渲染一个todo List
- 可以添加新的 todo 事项
- 可以区分已完成和未完成事项
- 可以将未完成的事项标记为已完成"

## 实现

1. TodoList整体作为一个大组件；
2. 列表中的每个列表项ListItem作为一个组件；
3. 任务的添加框Dialog作为一组件。
4. 每个任务拥有id，name和status。

