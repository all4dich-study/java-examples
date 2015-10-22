package com.company.lge.streams;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParallelStream {
    public static void main(String[] argv){
        List<Integer> intList = IntStream.range(0,10).boxed().collect(Collectors.toList());
        long start = System.currentTimeMillis();
        List<Integer> i2 = intList.parallelStream().map(d -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return d;
        }).collect(Collectors.toList());
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
