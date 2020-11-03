package com.doubleview.commons;

import com.google.common.collect.Sets;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class HelloBike {

    public static void main(String[] args) throws IOException {
        List<String> list1 = FileUtils.readLines(new File("/Users/huchengchao/Downloads/hellobike.sql"));
        list1 = list1.stream().map(s -> s.substring(0, 10)).collect(Collectors.toList());
        System.out.println(list1.iterator().next());
        System.out.println(list1.size());
        Set<String> set1 = Sets.newHashSet(list1);
        System.out.println(set1.size());

        List<String> list2 = FileUtils.readLines(new File("/Users/huchengchao/Downloads/hellobike1.txt"));
        Set<String> set2 = Sets.newHashSet(list2);
        System.out.println(set2.size());
//
//        BloomFilter<String> cityCache = BloomFilter.create(Funnels.stringFunnel(Charset.forName("utf-8")), 500000, 0.0000001);
//        set2.forEach(cityCache::put);
//        System.out.println(cityCache.mightContain("0002660598"));


        Set<String> set3 = Sets.newHashSet();
        for (String bikeNo : set1) {
            if (!set2.contains(bikeNo)) {
                set3.add(bikeNo);
            }
        }

        Set<String> set4 = Sets.newHashSet();
        for (String bikeNo : set2) {
            if (!set1.contains(bikeNo)) {
                set4.add(bikeNo);
            }
        }
        System.out.println("-----------------");
        System.out.println(set3.size());
        System.out.println(set4.size());
    }
}
