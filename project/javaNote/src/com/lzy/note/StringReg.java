package com.lzy.note;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringReg {

	public static void main(String[] args) {
		String a = "a";
		String b = "basdsf";
		String c = "155511385";
		String d = "02541454";
		String e = "265156153153153153415";
		String f = "abfsdfabfsdfabdsfabhtr";
		String g = "aaf  fsf     gdfger   fgerg";
		// 匹配，由于具体的字符串太多，我们在此只给出几个例�?,只要有一处不符合则返回false
		System.out.println("判断�?个字符是否为a或b或c�?" + a.matches("[abc]"));
		System.out.println("判断�?个字符是否不为a或b或c�?" + a.matches("![abc]"));
		System.out.println("判断�?个字符是否是字母�?" + a.matches("[a-z A-Z]"));
		System.out.println("判断�?个字符是否是数字�?" + a.matches("[0-9]"));
		System.out.println("判断是否为长�?4�?10的字母组成的字符串：" + a.matches("[a-z A-z]{4,10}"));
		System.out.println("判断是否为长�?4�?10的字母组成的字符串：" + b.matches("[a-z A-z]{4,10}"));
		System.out.println("判断是否为首字符不为0，后边的字符�?�?4�?10位：" + c.matches("[1-9][0-9]{4,10}"));
		System.out.println("判断是否为首字符不为0，后边的字符�?�?4�?10位：" + d.matches("[1-9][0-9]{4,10}"));
		System.out.println("判断是否为首字符不为0，后边的字符�?�?4�?10位：" + e.matches("[1-9][0-9]{4,10}"));
		// 替换
		System.out.println("替换符合正则表达式的�?有字符为新字符：" + f.replaceAll("[a][b]", "#"));
		// 获取，�?�过group操作
		 String reg = "[1][5][6]";// 定义规则，找156的字符串
		 Pattern p = Pattern.compile(reg);// 将规则封装为对象
		 Matcher m = p.matcher(e);// 将正则对象和要作用的字符串相关联并获得匹配器对象
		while (m.find())// 得先找到，才能获�?
		{
			System.out.println(m.group());// 获取字符�?
			System.out.println(m.start());// �?始位�?
			System.out.println(m.end());// 结束位置，包含头不包含尾
		}
		// 切割，尤其是表达式的书写，多注意转义字符\，在此只给出�?个了例子
		String[] h = g.split(" +");
		for (String x : h) {
			System.out.println("以多个空格进行切�?:" + x);// 十分重要
		}
	}

}
