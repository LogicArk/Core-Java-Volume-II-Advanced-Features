package org.logicark.chapter01.node1_3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Description 练习
 * @Author logicark
 * @Date 2022/7/28 18:49
 */
public class Practice {
    public static void main(String[] args){
        List<String> wordList = new ArrayList<>();
        wordList.add("Hello");
        wordList.add("World");
        wordList.add("Good");
        wordList.add("Luck");

        // 筛选出长度大于12的单词
        Stream<String> longWords = wordList.stream().filter(w -> w.length()>12);
        System.out.println(longWords);

        // 将所有单词都转换为小写
        Stream<String> lowercaseWords = wordList.stream().map(String::toLowerCase);
        System.out.println(lowercaseWords);

        // 筛选出每个单词的首字母
        Stream<String> firstLetters = wordList.stream().map(s -> s.substring(0 ,1));
        System.out.println(firstLetters);

        // 将字符串拆分成字符的集合
        Stream<String> letterStream = letters("boat");
        System.out.println(letterStream);

        Stream<Stream<String>> result = wordList.stream().map(w -> letters(w));
        System.out.println(result);

        Stream<String> flatResult = wordList.stream().flatMap(w -> letters(w));
        System.out.println(flatResult);
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