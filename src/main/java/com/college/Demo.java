package com.college;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Demo {
	public static void main(String[] args) {

		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(1);
		list.add(2);
		list.add(1);

		list.stream().forEach(e -> System.out.println(e));

		System.out.println("-----------------");
		Set<Integer> set = new HashSet<>();
		set.add(1);
		set.add(1);
		set.add(2);
		set.add(1);

		set.stream().forEach(e -> System.out.println(e));
		System.out.println("-------------");

		Map<String, Object> map = new LinkedHashMap<>();
		map.put("Linton", "250");
		map.put("Taru", "001");

		map.entrySet().forEach(e -> {
			System.out.println(e.getKey() + e.getValue());
		});

	}
}
