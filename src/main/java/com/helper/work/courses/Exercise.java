package com.helper.work.courses;

import com.helper.work.courses.hackerrank.Palindrome;
import com.helper.work.courses.javafund.MyClass;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class Exercise {

    public static void main(String[] args) {
        printTime();
        log.info("java.time.LocalTime: " + java.time.LocalTime.now());
        log.info("UNIX epoch: " + System.currentTimeMillis() / 1000L);
        log.info("unixTimestamp = " + Instant.now().getEpochSecond());

        SortedMap<String, String> treeMap = new TreeMap();
        int sum = 1;
        sum += 2;
        System.out.println(sum);
        treeMap.put("Key1", "value1");
        treeMap.put("key2", "value2");
        treeMap.put("holder", "glasses");
        treeMap.put("Key10", "value1");
//        treeMap.putIfAbsent("holder", "Holder Absent");
        log.info("TreeMap: ");
        treeMap.forEach((k, v) ->  System.out.println(k + " ! " + v));

//        log.info("Tail map 1: " + treeMap.tailMap("holder").keySet().toString());

        log.info("HeadMap: ");
        var hm = treeMap.headMap("key2");
        hm.forEach((k, v) ->  System.out.println(k + " ! " + v));

        var tm = treeMap.tailMap("key2");
        log.info("Tail map 2: ");
        tm.forEach((k, v) ->  System.out.println(k + " ! " + v));

        log.info("TreeMap: ");
        treeMap.forEach((k, v) ->  System.out.println(k + " ! " + v));

        treeMap.keySet().stream().limit(2)
                        .forEach(System.out::println);
        Stream.iterate(0, x -> x+1)
                .limit(10)
                .forEach(x -> {
                    System.out.println(x);
                });
    }

    private static void treeSetTestCase() {
        List<MyClass> list = getMyClassList();
        Set<MyClass> treeSet = new TreeSet<>();
        treeSet.addAll(list);

        log.info("\nTreeSet: " + treeSet);
        log.info("\nList: " + list);

        Comparator<MyClass> comparator = (o1, o2) -> o2.compareTo(o1);
        final Set<MyClass> collect = new HashSet<>(treeSet);
        final List<MyClass> collect1 = collect.stream().sorted(comparator).collect(Collectors.toList());
        log.info("\nSet sorted: " + collect1);
    }

    private static void printTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
    }

    private static void arrayTest() {
        List<String> list = getStringList();

        Object[] toArray = list.toArray();
        final String[] a = new String[4];
//        a[3] = "ca va?";
        String[] toArray1 = getToArray1(list, a);
        Arrays.stream(toArray).forEach(System.out::println);
        System.out.println("printing string array toArray1 :");
        Arrays.stream(toArray1).forEach(System.out::println);
        System.out.println("printing a:");
        Arrays.stream(a).forEach(System.out::println);
    }

    private static List<String> getStringList() {
        List<String> list = new ArrayList();
        list.add("hola");
        list.add("hello");
        list.add("hej");
        list.add("ciao");
        list.add("ola");
        return list;
    }

    private static List<MyClass> getMyClassList() {
        List<MyClass> list = new ArrayList();
        list.add(new MyClass("label", "value"));
        list.add(new MyClass("label1", "value1"));
        list.add(new MyClass("label2", "value2"));
        list.add(new MyClass("Hola", "Hola"));
        list.add(new MyClass("Chau", "Chau"));
        return list;
    }

    private static String[] getToArray1(List<String> list, String[] a) {
        String[] toArray1 = list.toArray(a);
        toArray1[3] = "ca va?";
        return toArray1;
    }

    private static void callingPalindromeTestCase2() {
        //        final String s = "hgygsvlfcwnswtuhmyaljkqlqjjqlqkjlaymhutwsnwcwflvsgygh";
        final String s = "cwnnwcw";
        log.info("Result:" + Palindrome.palindromeIndex(s));
        log.info("Result 2:" + Palindrome.palindromeIndex2(s));
//        log.info("Result:" + Palindrome.palindromeIndex("hgygsvlfcwnswtuhmyaljkqlqj" +
//            "jqlqkjlaymhutwsnwcwflvsgygh"));
    }

    public static void callTestCases1() {
//        log.info("Result: " + Palindrome.palindromeIndex("quyjjdcgsvvsgcdjjyq"));
//        log.info("Result: " + Palindrome.palindromeIndex("hgygsvlfwcwnswtuhmyaljkqlqjjqlqkjlaymhutwsnwcflvsgygh"));
//        log.info("Result: " + Palindrome.palindromeIndex("fgnfnidynhxebxxxfmxixhsruldhsaobhlcggchboashdlurshxixmfxxxbexhnydinfngf"));
//        log.info("Result: " + Palindrome.palindromeIndex("bsyhvwfuesumsehmytqioswvpcbxyolapfywdxeacyuruybhbwxjmrrmjxwbhbyuruycaexdwyfpaloyxbcpwsoiqtymhesmuseufwvhysb"));
//        log.info("Result: " + Palindrome.palindromeIndex("fvyqxqxynewuebtcuqdwyetyqqisappmunmnldmkttkmdlnmnumppasiqyteywdquctbeuwenyxqxqyvf"));
//        log.info("Result: " + Palindrome.palindromeIndex("mmbiefhflbeckaecprwfgmqlydfroxrblulpasumubqhhbvlqpixvvxipqlvbhqbumusaplulbrxorfdylqmgfwrpceakceblfhfeibmm"));
//        log.info("Result: " + Palindrome.palindromeIndex("tpqknkmbgasitnwqrqasvolmevkasccsakvemlosaqrqwntisagbmknkqpt"));
        log.info("Result: " + Palindrome.palindromeIndex("lhrxvstsvxrhlt"));
//        log.info("Result: " + Palindrome.palindromeIndex("prcoitfiptvcxrvoalqmfpnqyhrubxspplrftomfehbbhefmotfrlppsxburhyqnpfmqlaorxcvtpiftiocrp"));
//        log.info("Result: " + Palindrome.palindromeIndex("kjowoemiduaaxasnqghxbxkiccikxbxhgqnsaxaaudimeowojk"));

    }
}
