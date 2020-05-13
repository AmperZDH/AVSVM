# AVSVM
## A very simple virtual machine impelement by Java
## 【 如果你觉得没毛病，不要忘记点个Star (ง •̀_•́)ง 】

##### 帝都某七星酒店附属SCHOOL实践实验

##### 基本按照老师给的文档开发


## 使用方法
##### 1.在src级目录下创建 .txt 文件，文件名自拟
##### 2.终端或者编译器执行 scr/Main.java 即可
##### 3.src级目录下会生成二进制字节码文件，相当于java的 .class 文件，这里起的后缀名为.code【字节码生成的字节码.code文件根据个人理解实现的】

### 执行文件内容举例
##### (1) ex1.txt:
###### PUSH 2 <br/> PUSH 3 <br/> ADD <br/> HALT
##### (2) ex2.txt:
###### PUSH 4 <br/> PUSH 3 <br/> ADD <br/> DUP <br/> PUSH 5 <br/> SUB <br/> IFNE 6 <br/> PUSH 4 <br/> SUB <br/> HALT

## 目录分析
##### loader ------ 装载器
##### complier ---- 编译器
##### engin ------- 运行时数据去以及执行引擎

##### 邮箱：amberzdh@163.com
