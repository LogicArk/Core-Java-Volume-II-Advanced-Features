package org.logicark.chapter01.node1_4;

import org.logicark.chapter01.node1_1.Demo1_1_CountLongWords;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Description 练习
 * @Author logicark
 * @Date 2022/7/29 09:27
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

        // 生成数据为Double类型随机数的无限流，并截取前100个数
        Stream<Double> randoms = Stream.generate(Math::random).limit(100);
        System.out.println(randoms.count());

        // 生成流时，跳过（排除）前n个元素。例如：跳过第一个元素
        Stream<String> words = Stream.of(contents.split("\\PL+")).skip(1);
        System.out.println(words.count());

        // 用Stream类的静态concat方法，将两个流连接起来
        Stream<String> combined = Stream.concat(
                letters("Hello"),letters("World")
        );
        System.out.println(combined.count());

    }

    /**
     * 将字符串拆分成字符的集合
     * @param s 单个字符串
     * @return
     */
    public static Stream<String> letters(String s){
        List<String> result = new ArrayList<>();
        for (int i = 0; i < s.length(); i++){
            result.add(s.substring(i, i + 1));
        }
        return result.stream();
    }
}
