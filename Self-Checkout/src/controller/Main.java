package controller;

import gui.GUIClass;

public class Main {
	public final double tax = 5.5;
	public String[] quantity = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
	public static GUIClass gui;
	
	public static void main(String args[]){
		Main main = new Main();
		gui = new GUIClass(main);
	}
	
	public void calcOutput(double item1, double item2, double item3){
		double subTotalValue = item1 + item2 + item3;
		double taxValue = (subTotalValue/100) * tax;
		double totalValue = subTotalValue + taxValue;
		gui.setOutputResults(subTotalValue, taxValue, totalValue);
	}
}
