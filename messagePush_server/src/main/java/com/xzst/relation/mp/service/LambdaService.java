package com.xzst.relation.mp.service;

import com.xzst.relation.mp.model.lambda.Employee;
import io.swagger.models.auth.In;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class LambdaService {
    private static final Logger logger = Logger.getLogger(LambdaService.class);

    public void listService() {
        List<String> list = new ArrayList<>();
        list.add("lihongtaoxxxxxxxxxxx");
        list.add("lihongtao");
        list.add("lihongtaollllllllllllllllllll");
        list.add("yeweifeng");
        list.add("meizhou");
        list.add("lihongtaorrrrrrrrrrrrrrrrr");
        list.forEach(i -> {
            logger.info(i);
        });

        logger.info("************************************************************************************************");

        list.sort((e1, e2) -> {
            return e1.length() - e2.length();
        });

        list.forEach(i -> {
            logger.info(i);
        });

        logger.info("************************************************************************************************");


        logger.info("************************************************************************************************");

        list.replaceAll(i -> {
            return i.toUpperCase();
        });

        list.forEach(i -> {
            logger.info(i);
        });

        logger.info("************************************************************************************************");

        list.removeIf(str -> {
            return str.startsWith("y") || str.startsWith("Y");
        });
        list.forEach(i -> {
            logger.info(i);
        });
        logger.info("************************************************************************************************");

    }


    public void mapService() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "lihongtao");
        map.put("2", "yeweo");
        map.put("3", "liu");

        map.forEach((k, v) -> logger.info(k + " : " + v));

        logger.info("************************************************************************************************");
        String a = map.getOrDefault("4", "保定");
        logger.info("---- a:" + a);

        map.forEach((k, v) -> logger.info(k + " : " + v));
        logger.info("************************************************************************************************");


        String b = map.putIfAbsent("5", "北高岭");
        logger.info("--------- b: " + b);

        map.forEach((k, v) -> logger.info(k + " : " + v));

        logger.info("************************************************************************************************");


        map.remove("2", "xxxxx");

        map.forEach((k, v) -> logger.info(k + " : " + v));

        logger.info("************************************************************************************************");

        map.replaceAll((k, v) -> v.toUpperCase());
        map.forEach((k, v) -> logger.info(k + " : " + v));
        logger.info("************************************************************************************************");
    }


    public void listObjectService() {

        Employee e1 = new Employee("John", 253434, 3000, 9922001);
        Employee e2 = new Employee("Ace", 222323223, 2000, 5924001);
        Employee e3 = new Employee("Keith22", 1351, 4100, 3924401);
        Employee e4 = new Employee("Keith55", 1351, 2000, 3924401);
        Employee e5 = new Employee("Keith33", 35123, 7000, 3924401);
        Employee e7 = new Employee("Keith334", 35123, 6000, 3924401);
        Employee e8 = new Employee("Keith335", 35123, 6000, 3924401);
        Employee e6 = new Employee("Keith11", 1351, 2000, 3924401);

        List<Employee> employees = new ArrayList<>();
        employees.add(e1);
        employees.add(e2);
        employees.add(e3);
        employees.add(e4);
        employees.add(e5);
        employees.add(e6);
        employees.add(e7);
        employees.add(e8);

        Collections.sort(employees, Comparator.comparing(Employee::getName));

        employees.forEach(
                e -> {
                    logger.info(e);
                }
        );
        logger.info("************************************************************************************************");
        Collections.sort(employees, Comparator.comparing(Employee::getAge).thenComparing(Employee::getName));
        employees.forEach(
                e -> {
                    logger.info(e);
                }
        );
        logger.info("************************************************************************************************");

        employees.stream().forEachOrdered(
                logger::info
        );
        logger.info("************************************************************************************************");

        Map<Integer, List<Employee>> map = employees.stream().collect(Collectors.groupingBy(Employee::getAge, LinkedHashMap::new, Collectors.toList()));


        map.forEach((k, v) -> {
            logger.info(k + " : " + v);
        });


        logger.info("************************************************************************************************");
        Map<Integer, List<Employee>> reversedMap = new LinkedHashMap<>();
        map.entrySet().stream().sorted((en1, en2) -> {
            return -(en1.getKey() - en2.getKey());
        }).forEachOrdered(en -> {
            reversedMap.put(en.getKey(), en.getValue());
        });

        reversedMap.forEach((k, v) -> {
            logger.info(k + " : " + v);
        });

        logger.info("************************************************************************************************");

        //理解Collectors.mapping 方法的意义
        Map<Integer, List<String>> groupMap =
                employees.stream().collect(Collectors.groupingBy(Employee::getAge, LinkedHashMap::new, Collectors.mapping(Employee::getName, Collectors.toList())));

        groupMap.forEach((k, v) -> {
            logger.info(k + " : " + v);
        });

        logger.info("************************************************************************************************");

        Map<Integer, String> listToMap1 = employees.stream().collect(Collectors.toMap(Employee::getAge, Employee::getName, (oldValue, newValue) -> newValue));
        listToMap1.forEach((k, v) -> {
            logger.info(k + " : " + v);
        });

        logger.info("************************************************************************************************");

        //key值不能重复
        Map<String, Integer> listToMap2 = employees.stream().collect(Collectors.toMap(Employee::getName, Employee::getAge));
        listToMap2.forEach((k, v) -> {
            logger.info(k + " : " + v);
        });

        logger.info("************************************************************************************************");


        Map<Integer, String> listToMap3 = employees.stream().collect(Collectors.toMap(Employee::getAge, Employee::getName, (oldValue, newValue) -> newValue, LinkedHashMap::new));
        Map<Integer, String> listToMap3_1 = employees.stream().collect(Collectors.toMap(e -> e.getAge(), e -> e.getName(), (oldValue, newValue) -> newValue, () -> new LinkedHashMap<>()));
        listToMap3.forEach((k, v) -> {
            logger.info(k + " : " + v);
        });
        logger.info("************************************************************************************************");

        //多条件分组

        Map<Integer, Map<Double, List<Employee>>> mulGroupMap = employees.parallelStream().collect(Collectors.groupingBy(Employee::getAge, Collectors.groupingBy(Employee::getSalary, Collectors.toList())));

        mulGroupMap.forEach((k, v) -> {
            v.forEach((k1, v1) -> {
                logger.info(k + "_" + k1 + " : " + v1);
            });
        });

        logger.info("************************************************************************************************");


        Double salarySum1 = employees.parallelStream().mapToDouble(Employee::getSalary).sum();
        Double salarySum2 = employees.parallelStream().mapToDouble(e -> e.getSalary()).sum();

        logger.info(salarySum1 + "    " + salarySum2);

    }


    public void lambdaListParalleService() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
        Long start = System.currentTimeMillis();
        List<Integer> listBak = new ArrayList<>();
        Map<String, Integer> countMap = new HashMap<>();
        countMap.put("k", 0);
        list.parallelStream().forEach(e -> {
                    listBak.add(e);
                    countMap.put("k", countMap.get("k") + 1);
                    logger.info(e);
                }
        );
        Long over1 = System.currentTimeMillis();
        logger.info(countMap);
        logger.info((over1 - start) + "毫秒");
        logger.info("************************************************************************************************");
        list.sort((e1, e2) -> {
            return e1 - e2;
        });
        list.forEach(e -> {
                    logger.info(e);
                }
        );
        Long over2 = System.currentTimeMillis();
        logger.info((over2 - over1) + "毫秒");

    }


    public void lambdaStringOrder() {
        List<String> list = new ArrayList<>();
        list.add("13");
        list.add("11");
        list.add("18");
        list.add("21");
        list.add("01");
        list.add("");

        list.stream().
                filter(e -> !"".equals(e)).
                sorted((e1, e2) -> {
                    return -e1.compareTo(e2);
                }).forEachOrdered(e -> {
//                    logger.info(e);
                }

        );

        logger.info("************************************************************************************************");

        String str = "equal(lg_a29c8814b14a46a384e52cd6ef6ddedd)&equal(rzfh_8d27487dc04040b0b1a2d207404653e2)&equal(rzrq_a515be61963248458f075613a6c495cc)";
        Matcher mat = Pattern.compile("(?<=\\()[^\\)]+").matcher(str);

        while (mat.find()) {
            logger.info(mat.group());
        }

        int count = mat.groupCount();

        for (int i = 0; i <count ; i++) {
            logger.info("xxxxxxx: "+mat.group(i));
        }

    }

}
