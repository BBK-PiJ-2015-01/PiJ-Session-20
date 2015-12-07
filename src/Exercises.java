import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class Exercises {

	private final List<String> words = Arrays.asList("hi", "hello", "How", "are", "you");

	public static void main(String[] args) {

		new Exercises().exercise1Launcher();
		new Exercises().exercise3Launcher();
		new Exercises().exercise4Launcher();
		new Exercises().exercise5Launcher();
		new Exercises().exercise7Launcher();
		new Exercises().exercise8Launcher();
		new Exercises().exercise9Launcher();
		new Exercises().exercise10Launcher();
		new Exercises().exercise11Launcher();
		new Exercises().exercise12Launcher();
	}

	private void exercise1Launcher() {

		words.stream().forEach((w) -> System.out.println("  " + w));

	}

	private void exercise3Launcher() {

		List<String> excitingList = mapFunctions(words, s -> s + "!");
		excitingList.stream().forEach((w) -> System.out.println(w));

		List<String> eyeList = mapFunctions(words, s -> s.replace("i", "eye"));
		eyeList.stream().forEach((w) -> System.out.println(w));

		List<String> upperCaseList = mapFunctions(words, s -> s.toUpperCase());
		upperCaseList.stream().forEach((w) -> System.out.println(w));
	}

	private void exercise4Launcher() {

		System.out.println("Filter on word length < 4");
		List<String> shortList = filterFunctions(words, s -> s.length() < 4);
		shortList.stream().forEach((w) -> System.out.println(w));

		System.out.println("Filter on words containing 'h'");
		List<String> hWordsList = filterFunctions(words, s -> s.contains("h"));
		hWordsList.stream().forEach((w) -> System.out.println(w));

		System.out.println("Filter on even length words");
		List<String> evenWordsList = filterFunctions(words, s -> (s.length() % 2) == 0);
		evenWordsList.stream().forEach((w) -> System.out.println(w));
	}

	private void exercise5Launcher() {

		System.out.println("Special selection");
		Optional<String> o = words.stream().map(s -> s.toUpperCase()).filter(s -> s.length() < 4)
				.filter(s -> s.contains("E")).findFirst();
		System.out.println(o.isPresent() ? o.get() : "Not found");
	}

	private void exercise7Launcher() {

		System.out.println("Concatenation via reduce only");
		Optional<String> o = words.stream().reduce((s1, s2) -> s1.toUpperCase() + s2.toUpperCase());
		System.out.println(o.isPresent() ? o.get() : "Something went wrong");

	}

	private void exercise8Launcher() {

		System.out.println("Concatenation via map reduce");
		Optional<String> o = words.stream().map(s -> s.toUpperCase()).reduce((s1, s2) -> s1 + s2);
		System.out.println(o.isPresent() ? o.get() : "Something went wrong");

	}
	
	private void exercise9Launcher() {

		System.out.println("Concatenation using comma via reduce only");
		Optional<String> o = words.stream().reduce((s1, s2) -> s1 + "," + s2);
		System.out.println(o.isPresent() ? o.get() : "Something went wrong");
	}
	
	private void exercise10Launcher() {

		System.out.println("Random list of Doubles");
		Exercises.randomDoubleList(12).stream().forEach(d -> System.out.println(d));
	}
	
	private void exercise11Launcher() {

		System.out.println("Stepped list");
		Exercises.orderedNumberList(50,5,12).stream().forEach(d -> System.out.println(d));
	}
	
	private void exercise12Launcher() {

		System.out.println("Sum of integers in parallel");
		Optional<Integer> o  = Exercises.orderedNumberList(50,5,12000).stream().reduce( (i1, i2) -> i1 + i2);
		System.out.println(o.isPresent() ? o.get() : "Something went wrong");
		o  = Exercises.orderedNumberList(50,5,12000).stream().parallel().reduce( (i1, i2) -> i1 + i2);
		System.out.println(o.isPresent() ? o.get() : "Something went wrong");
	}
	
	
	private static List<Double> randomDoubleList(int n) {
		
		Random r = new Random();
		DoubleStream.Builder builder = DoubleStream.builder();

		for(int i = 0; i < n; i++) {
			builder.add(r.nextDouble());
		}
		return builder.build().boxed().collect(Collectors.toList());	
	}
	
	private static List<Integer> orderedNumberList(int start, int step, int size) {
		
		IntStream.Builder builder = IntStream.builder();

		for(int i = 0; i < size; i++) {
			builder.add(start + i * step);
		}
		return builder.build().boxed().collect(Collectors.toList());	
	}
	
	private List<String> mapFunctions(Collection<String> theseWords, Function<String, String> mapFunction) {

		return theseWords.stream().map(mapFunction).collect(Collectors.toList());
	}

	private List<String> filterFunctions(Collection<String> theseWords, Predicate<String> filterFunction) {

		return theseWords.stream().filter(filterFunction).collect(Collectors.toList());
	}

}
