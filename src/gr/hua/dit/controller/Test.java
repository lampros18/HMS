package gr.hua.dit.controller;

import java.io.File;
import java.time.Instant;

public class Test {
	public static void main(String[] args) {
		System.out.println(Test.class.getProtectionDomain().getCodeSource().getLocation().getPath());
	}
}
