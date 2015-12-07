import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Exercise1 {

	
	private final List<String> words = Arrays.asList("hi", "hello", "How", "are", "you");
	
	public static void main(String[] args) {
		
		new Exercise1().exercise1Launcher();
		new Exercise1().exercise3Launcher();
		new Exercise1().exercise4Launcher();
		new Exercise1().exercise5Launcher();
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
		Optional<String> o = words.stream().map(s -> s.toUpperCase()).filter(s -> s.length() < 4).filter(s -> s.contains("E")).findFirst();
		System.out.println(o.isPresent() ? o.get() : "Not found");

	}
	
	
	private List<String> mapFunctions (Collection<String> theseWords, Function<String, String> mapFunction) {
		
		return theseWords.stream().map(mapFunction).collect(Collectors.toList());
	}
	
	private List<String> filterFunctions (Collection<String> theseWords, Predicate<String> filterFunction) {
		
		return theseWords.stream().filter(filterFunction).collect(Collectors.toList());
	}
	
	
}
