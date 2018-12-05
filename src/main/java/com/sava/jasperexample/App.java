package com.sava.jasperexample;

public class App {
	public static void main(String[] args) {
		System.out.println("App started...");
		PrintService printService = new PrintService();
		printService.createPdfReport();
	}
}
