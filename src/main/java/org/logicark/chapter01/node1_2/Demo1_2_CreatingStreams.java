package org.logicark.chapter01.node1_2;

import org.logicark.chapter01.node1_1.Demo1_1_CountLongWords;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description 流的创建
 * @Author logicark
 * @Date 2022/7/21 17:49
 */
public class Demo1_2_CreatingStreams {

    /**
     * 展示流的内容
     * @param title
     * @param stream
     * @param <T>
     */
    public static <T> void show(String title , Stream<T> stream){
        final int SIZE = 10;
        List<T> firstElements = stream.limit(SIZE + 1).collect(Collectors.toList());
        System.out.print(title + "：");
        for (int i = 0 ; i < firstElements.size(); i++){
            if (i > 0){
                System.out.print(",");
            }
            if (i < SIZE){
                System.out.print(firstElements.get(i));
            } else {
                System.out.print("...");
            }
        }
        System.out.println();
    }

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
        show("words", words);

        Stream<String> song = Stream.of("gently","down","the","stream");
        show("song",song);

        Stream<String> silence = Stream.empty();
        show("silence",silence);

        Stream<String> echos = Stream.generate(() -> "Echo");
        show("echos" ,echos);

        Stream<Double> randoms = Stream.generate(Math::random);
        show("randoms",randoms);

        Stream<BigInteger> integers = Stream.iterate(BigInteger.ONE , n -> n.add(BigInteger.ONE));
        show("integers",integers);

        Stream<String> wordsAnotherWay = Pattern.compile("\\PL+").splitAsStream(contents);
        show("wordsAnotherWay" ,wordsAnotherWay);

        try(Stream<String> lines = Files.lines(Paths.get(resourcesPath + "/alice30.txt"),StandardCharsets.UTF_8)){
            show("lines",lines);
        }
    }
}
