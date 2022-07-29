package org.logicark.chapter01.node1_6;

import org.logicark.chapter01.node1_1.Demo1_1_CountLongWords;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @Description 练习——1.6 简单约简
 * @Author logicark
 * @Date 2022/7/29 11:26
 */
public class Practice {
    public static void main(String[] args) throws IOException {

        // 获取maven项目的resources目录路径
        String resourcesPath = Demo1_1_CountLongWords.class.getClassLoader().getResource("").getPath();
        System.out.println(resourcesPath);

        // 从文本文件中读取字符串
        String contents = new String(
                Files.readAllBytes(Paths.get(resourcesPath + "/alice30.txt")) , StandardCharsets.UTF_8
        );

        // 将数组转换成流
        Stream<String> words = Stream.of(contents.split("\\PL+"));

        // 找到最大值
        Optional<String> largest = words.max(String::compareToIgnoreCase);
        System.out.println("largest: " + largest.orElse("未找到最大值"));

        // 重新获取流
        Stream<String> words2 = Stream.of(contents.split("\\PL+"));

        // 寻找以Q开头的单词，并取第一个
        Optional<String> startWithQ = words2.filter(s -> s.startsWith("Q")).findFirst();
        System.out.println("startWithQ: " + startWithQ.orElse("未找到以Q开头的单词"));

        // 重新获取流
        Stream<String> words3 = Stream.of(contents.split("\\PL+"));

        // 寻找以Q开头的单词
        Optional<String> startWithQAny = words3.filter(s -> s.startsWith("Q")).findAny();
        System.out.println("startWithQAny: " + startWithQAny.orElse("未找到以Q开头的单词"));

        // 重新获取流
        Stream<String> words4 = Stream.of(contents.split("\\PL+"));

        // 判断一个流中是否存在以Q开头的单词。只要存在，就返回true
        boolean aWordStartWithQ = words4.parallel().anyMatch(s -> s.startsWith("Q"));
        System.out.println(aWordStartWithQ);

        // 重新获取流
        Stream<String> words5 = Stream.of(contents.split("\\PL+"));

        // 判断一个流中是否所有单词都以Q开头
        boolean allStartWithQ = words5.parallel().allMatch(s -> s.startsWith("Q"));
        System.out.println(allStartWithQ);

        // 重新获取流
        Stream<String> words6 = Stream.of(contents.split("\\PL+"));

        // 判断一个流中是否没有以Q开头的
        boolean noneStartWithQ = words6.parallel().noneMatch(s -> s.startsWith("Q"));
        System.out.println(noneStartWithQ);
    }
}
