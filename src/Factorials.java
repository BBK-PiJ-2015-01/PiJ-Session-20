import java.util.function.IntFunction;
import java.util.stream.LongStream;

public class Factorials {
	
	private static final int LIMIT = 1000;

	public static void main(String[] args) {
		
		System.out.println(timer(25, Factorials::factorialRecursive));
		System.out.println(timer(25, Factorials::factorialIterative));
		System.out.println(timer(25, Factorials::factorial));
	}
	
	public static long factorialRecursive (int n) {
			
		if (n > LIMIT) {
			throw new IllegalArgumentException();
		}
			
		return (n == 1) ? 1 : n * factorialRecursive(n-1);
	}
	
	public static long factorialIterative (int n) {
			
		if (n > LIMIT) {
			throw new IllegalArgumentException();
		}
		long returnLong = 1;
		for (int i = 2 ; i <= n ; i++) {
			returnLong = returnLong * i;
		}
		return returnLong;
	}
	
	private static long timer (int n , IntFunction<Long> aMethod) {
		
		long startTime = System.nanoTime();
		long returnLong =  aMethod.apply(n);
		long duration = System.nanoTime() - startTime;
		System.out.print("Elapsed duration: " + duration + " (ns). Result =  ");
		return returnLong;
	}
	
	
	public static long factorial (int n) {
			
		if (n > LIMIT) {
			throw new IllegalArgumentException();
		}
		return LongStream.rangeClosed(2,n).reduce(1, (a,b) -> a*b);		
	}
}