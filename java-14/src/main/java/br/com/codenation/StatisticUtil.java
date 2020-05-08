package br.com.codenation;


import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.google.common.math.Quantiles;
import com.google.common.primitives.Ints;

public class StatisticUtil {
	
	public static int average(int[] elements) {
		return new Double(IntStream.of(elements).sorted().average().getAsDouble()).intValue();
	}
	
	public static int mode(int[] elements) {

		Entry<Integer, Long> moda = null;

		try {


			moda = Ints.asList(elements).stream()
					.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
					.entrySet().stream()
					.sorted(Map.Entry.<Integer, Long>comparingByValue()
					.reversed())
					.limit(1).findFirst().get();
			
			   return moda.getKey();

		    } catch (Exception e) {
		    	
		       throw new RuntimeException(e);
		} 

	}

	public static int median(int[] elements) {

		Double median =  Quantiles.median().compute(  
				Ints.asList(elements)
				.stream()
				.sorted((o1,o2)-> o1.compareTo(o2))
				.collect(Collectors.toList()));
		
		return median.intValue();
	}
}



     