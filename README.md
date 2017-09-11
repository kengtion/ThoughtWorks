# ThoughtWorks内推作业

**目录 (Table of Contents)**

[TOCM]

[TOC]

# 运行说明
##导入项目
git clone https://github.com/kengtion/ThoughtWorks.git
##系统环境
本项目使用Java语言开发，JDK版本为jdk1.7.0_79,使用的IDE为Intellij IDEA 2017.1.4，JUnit版本为junit-4.12
##主程序
主程序入口为**cn.kengtion**包中**Main.java**类里的**public static void main(String[] args)**方法,在Intellij IDEA中可以通过Run->Run->Main执行
**输入：**在控制台中输入命令后回车
**输出：**控制台中输出文本
##JUnit单元测试
在**test.cn.kengtion**包中有部分单元测试用例，可以验证项目正确性，**但是输出与正常运行时输出不同，仅用作开发时的测试**
#项目说明
##代码结构
###文件说明
| 文件名  |  位置 |  说明 |
| ------------ | ------------ | ------------ |
| GymBean  |  cn.kengtion.Bean | 体育场对象，每个体育场实例管理自己的订单列表  |
| OrderBean  | cn.kengtion.Bean  | 订单对象，定义了订单的基本信息并提供输出收入的接口  |
| GymManager  | cn.kengtion.Utils  | 体育场管理类，执行命令的入口  |
|InputFormatUtil|cn.kengtion.Utils| 输入工具类，提供检查输入格式、将正确的输入转换为订单实例的接口|


