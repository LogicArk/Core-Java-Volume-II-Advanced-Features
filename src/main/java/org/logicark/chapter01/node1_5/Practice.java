package org.logicark.chapter01.node1_5;

import org.logicark.chapter01.node1_1.Demo1_1_CountLongWords;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * @Description 练习——1.5 其他的流转换
 * @Author logicark
 * @Date 2022/7/29 10:03
 */
public class Practice {
    public static void main(String[] args) throws IOException {

        //获取maven项目的resources目录路径
        String resourcesPath = Demo1_1_CountLongWords.class.getClassLoader().getResource("").getPath();
        System.out.println(resourcesPath);

        //从文本文件中读取字符串
        String contents = new String(
                Files.readAllBytes(Paths.get(resourcesPath + "/alice30.txt")) , StandardCharsets.UTF_8
        );

        //将数组转换成流
        Stream<String> words = Stream.of(contents.split("\\PL+"));

        // 剔除流中重复的元素
        Stream<String> uniqueWords = Stream.of("merrily","merrily","merrily","gently").distinct();
        System.out.println(uniqueWords.count());

        // 对流进行排序，使得最长的字符串排在前面
        Stream<String> longestFirst = words.sorted(Comparator.comparing(String::length).reversed());
        System.out.println(longestFirst);

        // peek方法会产生另外一个流，它的元素与原来流中的元素相同，但每次获取一个元素时都会调用一个函数。这对于调试来说很方便
        Object[] powers = Stream.iterate(1.0 ,p -> p * 2)
                .peek(e -> System.out.println("Fetching " + e))
                .limit(20)
                .toArray();
        System.out.println(powers);
    }
}