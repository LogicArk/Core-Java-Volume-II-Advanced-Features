package org.logicark.chapter01.node1_7;

import org.logicark.chapter01.node1_1.Demo1_1_CountLongWords;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @Description 练习——1.7 Optional类型
 * @Author logicark
 * @Date 2022/7/29 11:53
 */
public class Practice {
    public static void main(String[] args) throws IOException {
        /**
         * 1.7.1 如何使用Optional值
         */
        List<String> wordList = new ArrayList<>();
        Optional<String> optionalString = wordList.stream().max(String::compareTo);

        String result = optionalString.orElse("未找到最大值");
        System.out.println(result);

        result = optionalString.orElseGet(() -> Locale.getDefault().getDisplayName());
        System.out.println(result);

//        result = optionalString.orElseThrow(IllegalStateException::new);



        // 获取maven项目的resources目录路径
        String resourcesPath = Demo1_1_CountLongWords.class.getClassLoader().getResource("").getPath();
        System.out.println(resourcesPath);

        // 从文本文件中读取字符串
        String contents = new String(
                Files.readAllBytes(Paths.get(resourcesPath + "/alice30.txt")) , StandardCharsets.UTF_8
        );
        // 将数组转换成流
        Stream<String> words = Stream.of(contents.split("\\PL+"));

        List<String> results = new ArrayList<>();
        // 寻找以Q开头的单词，并取第一个
        Optional<String> optionalValue = words.filter(s -> s.startsWith("Q")).findFirst();
        optionalValue.ifPresent(v -> results.add(v));
        optionalValue.ifPresent(results::add);
        System.out.println(results);

        // 当调用ifPresent时，该函数不会返回任何值。如果想要处理函数的结果，应该使用map:
        Optional<Boolean> added = optionalValue.map(results::add);
        System.out.println(added.get());

        /**
         * 1.7.2 不适合使用Optional值的方式
         */
        // 将数组转换成流
        Stream<String> words2 = Stream.of(contents.split("\\PL+"));
        // 寻找以A开头的单词，并取第一个
        Optional<String> optionalValue2 = words2.filter(s -> s.startsWith("A")).findFirst();
//        optionalValue2.get().toLowerCase();

        /**
         * 1.7.3 创建Optional值
         */
        Double x = 6.0;
        Optional<Double> optionalDouble = (x == 0) ? Optional.empty() : Optional.of(1 / x);
        System.out.println(optionalDouble.get());

        /**
         * 1.7.4 用flatMap来构建Optional值的函数
         */
        // 求倒数的平方根
        Optional<Double> result2 = inverse(x).flatMap(Practice::squareRoot);
        System.out.println(result2.get());
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
