package com.google.challenges;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;


public class Answer {
	
	public static void main(String[] args) {
		System.out.println(answer(2, 21, 22));	
	}
	
	
	public static void printList(ListIterator<String> it){
		System.out.println(it.toString());
		while (it.hasNext())
			System.out.println(it.next());
	}
	
	public static void replace(List<String> list, String val, String newVal) {
	    for (ListIterator<String> it = list.listIterator(); it.hasNext(); )
	        if (val == null ? it.next() == null : val.equals(it.next()))
	            it.set(newVal);
	}
	
	
	public static String answer(int x, int y, int z){
		
		StringBuilder input = new StringBuilder();
		List<Date> date = new ArrayList<Date>();
		List<SimpleDateFormat> dateFormat;
		dateFormat = new ArrayList<SimpleDateFormat>() {
		private static final long serialVersionUID = 769708308721266431L;
			{	
		        add(new SimpleDateFormat("MM/DD/YY"));
		    }
		};
		
		input.append((Integer.toString(x).length() == 1) ? "0" + Integer.toString(x) : Integer.toString(x));
		input.append((Integer.toString(y).length() == 1) ? "0" + Integer.toString(y) : Integer.toString(y));
		input.append((Integer.toString(z).length() == 1) ? "0" + Integer.toString(z) : Integer.toString(z));
		
	 	date = checkDate(dateFormat, permutation(input.toString(), "", "", 0, 0, 2, new LinkedHashSet<String>() ));
	 	
	 	return (date.size() == 1 ? date.toString() : "Ambiguous");
	}

	
	public static LinkedHashSet<String> permutation(String str, String origStr, String appended, int length, int counter, int interval, LinkedHashSet<String> set){
		
		set.add(str + appended);
		
		if (str.isEmpty())//done processing in substring of current string move on to the next string
			return set;		
		
		if (str.equals(origStr))
			return set;
		
		if (counter == 0)
			origStr = str;		
		//reverse
		permutation(str.substring(interval) + str.substring(0, interval), origStr, appended, length, ++counter, interval, set);
		//find sub-level of string
		permutation(str.substring(interval), str, appended + str.substring(0, interval), length, 0, interval, set);		
		return set;
	}
	
	public static LinkedHashSet<List<String>> permutation2(List<String> str, List<String> origStr, List<String> appended, int length, int counter, int subStrCounter, LinkedHashSet<List<String>> set){
		
		ListIterator<String> itP;
		List<String> tmp = new ArrayList<String>();
		List<String> tmpP = new ArrayList<String>();
		
		tmp.addAll(str);
				
		if (!appended.isEmpty()) 
			tmp.addAll(appended);
		
		set.add(tmp);
		
		if (str.isEmpty())//done processing in substring of current string move on to the next string
			return set;		
		
		if (str.equals((origStr)))
			return set;
		
		if (counter == 0)
			origStr.addAll(str);
		
		//reverse string combination		
		tmpP.addAll(str);
		itP = tmpP.listIterator(tmpP.size());
		itP.add(str.get(0));
		itP = tmpP.listIterator();
		itP.next();
		itP.remove();
		permutation2(tmpP, origStr, appended, length, ++counter, subStrCounter, set);
		
		//find subLevel of string
		List<String> appendedP = new ArrayList<String>();
		ListIterator<String> aP;
		
		appendedP.addAll(appended);
		aP = appendedP.listIterator(appendedP.size());
		
		itP = str.listIterator(str.size());
		
		if (!str.isEmpty()){
			aP.add(str.get(0));
			itP = str.listIterator();
			itP = str.listIterator();
			itP.next();
			itP.remove();
		}
		permutation2(str, new ArrayList<String>(), appendedP, length, 0, 0, set);		
		return set;
	}
	
	public static List<Date> checkDate(List<SimpleDateFormat> dateFormat, LinkedHashSet<String> set){
		List<Date> date = new ArrayList<Date>();
		for (String dateList : set){	
            try {
            	dateFormat.get(0) .setLenient(false);
                date.add(dateFormat.get(0).parse(dateList.toString()));
            } catch (ParseException e) {
                //Shhh.. try other formats
            }
		}
		return date;
	}
}