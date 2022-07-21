package org.logicark.chapter01.node1_2;

import org.logicark.chapter01.node1_1.Demo1_1_CountLongWords;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * @Description 练习
 * @Author logicark
 * @Date 2022/7/21 15:38
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
        System.out.println(words);

        //将变长参数转换成流
        Stream<String> song = Stream.of("gently", "down", "the", "stream");
        System.out.println(song);

        //从数组中截取一部分，构成新的流
        String[] arr = new String[]{"give","me","some","color","CC"};
        Stream<String> subArrStream = Arrays.stream(arr ,2 ,4);
        System.out.println(subArrStream.count());

        //创建空流
        Stream<String> silence = Stream.empty();
        System.out.println(silence.count());

        //获得一个常量值的流
        Stream<String> echos = Stream.generate(() -> "Echo");
        System.out.println(echos);

        //获取一个随机数的流
        Stream<Double> randoms = Stream.generate(Math::random);
        System.out.println(randoms);

        //创建无限序列：0、1、2、3...........
        Stream<BigInteger> integers = Stream.iterate(BigInteger.ZERO ,n -> n.add(BigInteger.ONE));
        //(流式api是惰性的，只有遇到count()这样的终止操作，才会执行上面这行)
        //System.out.println(integers.count());

        //按照某个正则表达式，来分割一个CharSequence对象
        Stream<String> words2 = Pattern.compile("\\PL+").splitAsStream(contents);
        System.out.println(words2.count());

        //静态的Files.lines方法会返回一个包含了文件夹中所有行的Stream
        Stream<String> lines = Files.lines(
                Paths.get(resourcesPath + "/alice30.txt")
        );
        System.out.println(lines.count());


    }
}
