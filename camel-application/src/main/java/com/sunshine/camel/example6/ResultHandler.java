package com.sunshine.camel.example6;

import java.util.List;

public class ResultHandler {
	public void printResult(List list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}
