package pl.fixedpoint.marekag;

import java.util.Scanner;

public class TestClass {

	public static Scanner scanner;

	public static void main(String[] args) {

		scanner = new Scanner(System.in);

		// flag for "while" loop with "try-catch" block
		Boolean isApproxWrong = false;
		NumericMethods fp;
		do {
			try {

				System.out.println("Podaj dokładność: ");

				// check if user typed integer
				try {
					fp = new FixedPoint(scanner.nextInt());
				} catch (Exception e) {
					// if not check if he typed double
					fp = new FixedPoint(Double.parseDouble(scanner.nextLine()));
				}

				Double answer = fp.getRoot(5.0);

				if (answer != null) {
					System.out.println("Przybliżona wartość pierwiastka: "
							+ answer);
					System.out.println("Ilość kroków: " + fp.getNrOfSteps());
				} else {
					System.out.println("Niestety nie udało się w 100 krokach");
				}

				isApproxWrong = false;
			} catch (NumberFormatException e) {
				System.out
						.println("Podaj dokładność jako liczbę miejsc po przecinku lub w notacji z kropką jako separotorem dziesiętnym");
				isApproxWrong = true;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				isApproxWrong = true;
			}
		} while (isApproxWrong);
	}
}
