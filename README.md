# MUA Interpreter

陈佳伟 

3160102178

3160102178@zju.edu.cn

## 实现的功能

* 基本数据类型 `number` , `word` , `list` , `bool`  

* 注释 `//`

  示例：

  ```
  make "word "this_is_a_word //this is comment
  ```

* `make <word> <value>`： 将value绑定到word上。基本操作的字不能用做这里的word。绑定后的word称作名字，位于命名空间。

  示例：

  ```
  make "word "this_is_a_word
  ```

* `thing <word>`：返回word所绑定的值

  示例：

  ```
  thing "word //"word has been made before
  ```

* `:<word>`：与thing相同

  示例：

  ```
  :word //"word has been made before
  ```

* `erase <word>`：清除word所绑定的值

  示例：

  ```
  erase "word
  ```

* `isname <word>`：返回word是否是一个名字，true/false

  示例：

  ```
  isname "word //if "word has been erase, return false
  ```

  ```
  isname "thing //return true, because `thing` is a builtin operator
  ```

* `print <value>`：输出value

  示例：

  ```
  print "word //will print "word
  ```

  ```
  print :word //will print the value "word reference to, if it reference one
  ```

* `read`：返回一个从标准输入读取的数字或字

  示例：

  ```
  make "this_is_a_number read //这里要回车
  1 //这里要回车
  ```

* `readlist`：返回一个从标准输入读取的一行，构成一个表，行中每个以空格分隔的部分是list的一个元素

  示例：

  ```
  make "this_is_a_list readlist //这里要回车
  1 2 "word1 make "word2 "word3 //这里要回车
  ```

* 运算符operator
  - `add`, `sub`, `mul`, `div`, `mod`：`<operator> <number> <number>`
  - `eq`, `gt`, `lt`：`<operator> <number|word> <number|word>`
  - `and`, `or`：`<operator> <bool> <bool>`
  - `not`：`not <bool>`

* 退出命令 `exit`

* `readlist`：返回一个从标准输入读取的一行，构成一个表，行中每个以空格分隔的部分是list的一个元素

* `repeat <number> <list>`：运行list中的代码number次

* 函数定义与调用

  ```
  	make <word> [<list1> <list2>]
  		word为函数名
  		list1为参数表
  		list2为操作表
  	<functionName> <arglist>
  		<functionName>为make中定义的函数名，不需要双引号"
  		<arglist>是参数表，<arglist>中的值和函数定义时的<list1>中名字进行一一对应绑定
  ```

* `output <value>`：设定value为返回给调用者的值，但是不停止执行

* `stop`：停止执行

  `stop` 可以停止函数以及 `repeat` 和 `run` 指令

* `export`：将本地make的值输出到全局

* `isnumber <value>`：返回value是否是数字 

* `isword <value>`：返回value是否是字

* `islist <value>`：返回value是否是表 

* `isbool <value>`：返回value是否是布尔量 

* `isempty <word|list>`: 返回word或list是否是空

* `random <number>`：返回[0,number)的一个随机数

* `sqrt <number>`：返回number的平方根

* `int <number>`: floor the int

## 运行方法

```shell
java -jar MUA-Interpreter.jar
```

## 运行效果

![RunningImage](images/RunningImage.png)

## 实现方法

### 总设计

#### 类的协作关系

![MuaInterpreter](images/MuaInterpreter.png)

#### 类型系统

![TypeSystem](images/TypeSystem.png)

所有对象的基类是 `MuaObject` ，由此派生出 `MuaPrimitiveType` 和 `MuaOperator` 类。

对于 `MuaOperator` 类，

```java
abstract class MuaOperator extends MuaObject {
    public abstract MuaObject operate(ArgumentList argumentList);
    public int getArgumentNum();
}
```

抽象方法 `operate()` 是使用该函数/算子进行处理的方法，通过向其中传入 `ArgumentList` 对象，可以实现传递参数。

### 具体设计

#### InterpreterController

核心组件是 `InterpreterController` ，负责整个流程，主要流程如下：

```java
interpret() {
    initInterpreter()
    while (shouldContinue()) {
        readyForReadingInstruction()
        readInstruction() //use FrontEnd
        scanInstruction() //use Parser
        runOperations() //use Runner
        printMeaasge() //use FrontEnd
        clearAfterALoop()
    }
}
```

#### Lexer

`Lexer` 负责解析 `String instruction`，将其解析为 `List<MuaObject>` 以方便 `Runner` 去执行。

`Lexer` 的 `scan()` 函数流程大致如下：

```java
scan() {
    removeComment() //String -> String
    convertInstructionToTokenList() //String -> List<String>
    evaluateTokenListToObjectList() //List<String> -> List<MuaObject>
}
```

其中 `evaluateTokenListToObjectList()` 需要对每个 token 判断类型，从而转为 `MuaObject` ，这里使用了表驱动方法，避免了大量的 `if-else` 语句块，并且增加了可扩展性。

```
evaluateTokenListToObjectList() {
    for token in TokenList {
        for type in TypeArray {
            if (token belongs to type) {
                constructCorrespondingMuaObject()
            }
        }
    }
}
```

#### Runner

`Runner` 将 `List<MuaObject>` 对应的指令解析执行，并返回 `MuaObject` 结果。

```java
run() {
    while (objectListIterator.hasNext()) {
        evaluateCurrentObject()
        sendResultToControllerAndPrint()
    }
}
```

当 `evaluateCurrentObject()` 读取到一个函数对象 (比如 `make` 命令) 时，它会用 `objectListIterator.next()` 读取之后的若干个参数传入函数对象中处理。

#### NamespaceStack

NamespaceStack中以堆栈的形式存储了不同的变量名空间：

```java
class NamespaceStack {
    static Stack<Namespace> namespaceStack;
    ...
}
```

栈中最顶层的 `Namespace` 元素即为当前上下文的所有可引用变量名，堆栈的最底层是 global 空间。

```java
class Namespace {
    Map<String, MuaObject> map;
    ...
}
```

`Namespace` 对象用 `HashMap` 来实现名字-对象映射。

没调用一个函数就会向栈中加一个 `namespace` ，来存储对应名字；当函数退出时，栈将顶层 `namespace` 弹出。



#### 函数调用和内置运算符调用

函数和内置运算符分别用类 `MuaFunction` 和 `MuaOperator` 作为基类，分别有 `run()` 和 `operator()` 方法作为调用。



#### `number` 和 `bool` 类型作为 `word` 的类型的特例

根据要求规定， `number` 和 `bool` 是 `word` 的特例，但是由于之前代码分开来写，所以为了不对代码做太大调整，没有让 `number` 和 `bool` 持有或继承 `word` ，而是在具体的 `isword` 、`isnumber` 等函数运行时才进行相互转换。



#### 关于延迟解析

本项目的基本原则是，能尽早解析今早解析，不能尽早解析的推迟至指令执行时解析。

例如，对于 `make "func [[f] [f "helloworld ]]` ，其中 `f` 无法在仅有这条语句的时候解析出来，那么我让 `lexer` 先将它解析为 `NonDeterministicObject` ，等到指令真正执行时再查找 `Namespace` 看看是否能具体解析出来。



#### 自定义函数的实现

当定义一个函数时，先不解析它的参数表，只是把参数当作 `NonDeterministicObject` ，等到调用时，将参数表中的参数与具体传进去的实参相联系，并存储在 `HashMap` 中。



