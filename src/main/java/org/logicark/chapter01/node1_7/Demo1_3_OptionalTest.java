package org.logicark.chapter01.node1_7;

import org.logicark.chapter01.node1_1.Demo1_1_CountLongWords;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * @Description 程序清单1-3 optional/OptionalTest.java
 * @Author logicark
 * @Date 2022/8/1 14:15
 */
public class Demo1_3_OptionalTest {
    public static void main(String[] args) throws IOException {
        //获取maven项目的resources目录路径
        String resourcesPath = Demo1_1_CountLongWords.class.getClassLoader().getResource("").getPath();
        System.out.println(resourcesPath);

        //从文本文件中读取字符串
        String contents = new String(
                Files.readAllBytes(Paths.get(resourcesPath + "/alice30.txt")) , StandardCharsets.UTF_8
        );
        //将字符串中的单词，拆分到列表中
        List<String> wordList = Arrays.asList(contents.split("\\PL+"));

        Optional<String> optionalValue = wordList.stream().filter(s -> s.contains("fred")).findFirst();
        System.out.println(optionalValue.orElse("No Word") + " contains fred");

        Optional<String> optionalString = Optional.empty();
        String result = optionalString.orElse("N/A");
        System.out.println("result: " + result);

        result = optionalString.orElseGet(() -> Locale.getDefault().getDisplayName());
        System.out.println("result: " + result);

//        try{
//            result = optionalString.orElseThrow(IllegalStateException::new);
//            System.out.println("result: " + result);
//        } catch (Throwable t){
//            t.printStackTrace();
//        }

        optionalValue = wordList.stream().filter(s -> s.contains("red")).findFirst();
        optionalValue.ifPresent(s -> System.out.println(s + " contains red"));

        Set<String> results = new HashSet<>();
        optionalValue.ifPresent(results::add);
        Optional<Boolean> added = optionalValue.map(results::add);
        System.out.println(added);

        System.out.println(inverse(4.0).flatMap(Demo1_3_OptionalTest::squareRoot));
        System.out.println(inverse(-1.0).flatMap(Demo1_3_OptionalTest::squareRoot));
        System.out.println(inverse(0.0).flatMap(Demo1_3_OptionalTest::squareRoot));

        System.out.println("-------------------------------------------------------");

        Optional<Double> result2 = Optional.of(-4.0)
                .flatMap(Demo1_3_OptionalTest::inverse)
                .flatMap(Demo1_3_OptionalTest::squareRoot);
        System.out.println(result2);
    }

    /***
     * 求倒数
     * @param x
     * @return
     */
    public static Optional<Double> inverse(Double x){
        return x == 0 ? Optional.empty() : Optional.of(1 / x);
    }

    /***
     * 求平方根
     * @param x
     * @return
     */
    public static Optional<Double> squareRoot(Double x){
        return x < 0 ? Optional.empty() : Optional.of(Math.sqrt(x));
    }
}
