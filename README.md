# MUA Interpreter

陈佳伟 3160102178

## 实现的功能

* 基本数据类型 `number` , `word` , `list` , `bool`  的创建与存储
* 注释 `//`
* `make <word> <value>`： 将value绑定到word上。基本操作的字不能用做这里的word。绑定后的word称作名字，位于命名空间。
* `thing <word>`：返回word所绑定的值
* `:<word>`：与thing相同
* `erase <word>`：清除word所绑定的值
* `isname <word>`：返回word是否是一个名字，true/false
* `print <value>`：输出value
* `read`：返回一个从标准输入读取的数字或字
* `readlist`：返回一个从标准输入读取的一行，构成一个表，行中每个以空格分隔的部分是list的一个元素
* 运算符operator
  - `add`, `sub`, `mul`, `div`, `mod`：`<operator> <number> <number>`
  - `eq`, `gt`, `lt`：`<operator> <number|word> <number|word>`
  - `and`, `or`：`<operator> <bool> <bool>`
  - `not`：`not <bool>`
* 退出命令 `exit`

## 运行方法



## 运行效果

![屏幕快照 2018-12-03 8.00.51](images/屏幕快照 2018-12-03 8.00.51.png)

## 实现方法



## 测试样例示例

### 基本绑定赋值取值操作：

```
make "aList [make "anotherList ["word1 5 true]]
:aList
```

```
make "aWord "this_is_a_word
:aWord
```

```
make "num 3
thing "num
:num
isname "num
print :num
```

### 算术运算与布尔运算

```
add 3 4
sub 2 8
mul 7 9
div 4 3
mod 5 2
```

```
eq "word1 "word1
eq "word1 "word2
eq "word1 "word10
gt 5 2
gt 3 4
lt 5 2
lt 3 4
```

```
not true
not false
and true false
and true true
or false false
or true false
```

### IO 操作

```
make "anotherNum read 7
:anotherNum
```

```
make "list3 readlist make "anotherList ["word1 5 true]
:list3
```

### 其他

```
add 3 4 //this is a comment
```

```
exit
```



## 可改进的地方



