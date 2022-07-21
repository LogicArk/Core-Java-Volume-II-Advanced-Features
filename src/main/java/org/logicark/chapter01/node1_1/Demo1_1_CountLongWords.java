package org.logicark.chapter01.node1_1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * @Description 对长单词计数
 * @Author logicark
 * @Date 2022/7/21 11:41
 */
public class Demo1_1_CountLongWords {

    public static void main(String[] args) throws IOException {
        //获取maven项目的resources目录路径
        String resourcesPath = Demo1_1_CountLongWords.class.getClassLoader().getResource("").getPath();
        System.out.println(resourcesPath);

        //从文本文件中读取字符串
        String contents = new String(
                Files.readAllBytes(Paths.get(resourcesPath + "/alice30.txt")) , StandardCharsets.UTF_8
        );
        //将字符串中的单词，拆分到列表中
        List<String> words = Arrays.asList(contents.split("\\PL+"));

        //常规方式（for循环遍历列表）
        long count = 0L;
        for (String w : words){
            if (w.length() > 12 ){
                count ++;
            }
        }
        System.out.println(count);

        //使用"顺序流"方式遍历列表
        count = words.stream().filter(w -> w.length() > 12).count();
        System.out.println(count);

        //使用"并行流"方式遍历列表
        count = words.parallelStream().filter(w -> w.length() > 12).count();
        System.out.println(count);
    }
}
