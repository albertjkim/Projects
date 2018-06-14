package edu.nyu.cs.ak6118;
import java.util.*;
import java.math.*;
public class RandomDeleteLater {
	public static void main(String[] args) {
		double sum=0;
		for(int i=1; i<17; i++) {
			double CF = Math.pow(Math.E, -1*i*.25*.1);
			sum += .25 * i * CF*7;
		}
		System.out.println(sum);
	}
}
