package pl.fixedpoint.marekag;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserMenu {

	public static Scanner scanner;

	public static void main(String[] args) {

		scanner = new Scanner(System.in);
        System.out.println("Badana funkcja to: " + UserData.getFunction().toString());
		do {
			System.out.println("Podaj x0: ");
			try {
				double temp = Double.parseDouble(scanner.nextLine());
				if (temp <= 50) {
					UserData.setX0(temp);
				} else {
					System.out.println("Wartość x0 musi być mniejsz bądź równa 50");
				}
			} catch (NumberFormatException e) {
				System.out.println("Podaj liczbe!");
			}

		} while (UserData.getX0() == null);

		do {

			System.out.println("Podaj x1: ");
			try {
				double temp = Double.parseDouble(scanner.nextLine());
				UserData.setX1(temp);
                if(Validator.validateNewtonStartPoints(UserData.getX0(), UserData.getX1(), UserData.getFunction())) {
                    System.out.println("Niepoprawnie dobrane punkty startowe, punkty nie mogą być takie same i wartosci"
                            + " funkcji dla tych punktów musza mieć przeciwne znaki! x0=" +
                            UserData.getFunction().evaluate(UserData.getX0()) + " x2 = " +
                            UserData.getFunction().evaluate(UserData.getX1()));
                }
			} catch (NumberFormatException e) {
				System.out.println("Podaj liczbe!");
			}

		} while (UserData.getX1() == null || Validator.getLastResult());
		Double temp = null;
		do {
			System.out
					.println("Podaj dokładność jako liczbę miejsc po przecinku lub w notacji z kropką jako separotorem dziesiętnym: ");
			try {
				temp = Double.parseDouble(scanner.nextLine());
				if (temp >= 1 && temp <=13 && temp%1 == 0) {
					UserData.setEpsilon(temp.intValue());
				} else if (temp > 13) {
					System.out
					.println("Maksymalna ilość miejsc po przecinku to 13");
				} else {
					if (temp < 0 || temp > 1) {
						System.out
								.println("Dokładność musi być z przedziału (0,1)");
					} else {
						UserData.setEpsilon(temp);
					}
				}
			} catch (NumberFormatException | InputMismatchException ex) {
				temp = null;
			}

		} while (UserData.getEpsilon() == null);
		
		NumericMethods fixedPoint = new FixedPoint(UserData.getEpsilon());
		Double fixedPointValue = fixedPoint.getRoot(UserData.getX0());
		
		System.out.println("Metoda iteracji prostych: ");
		if (fixedPointValue != null) {
			System.out.println("Przybliżona wartość pierwiastka: "
					+ fixedPointValue);
			System.out.println("Ilość kroków: " + fixedPoint.getNrOfSteps());
		} else {
			System.out.println("Niestety nie udało się w 100 krokach");
		}
		
		//TODO: Implement NewtonMethod to match this prints:

		NumericMethods newtonMethod = new NewtonAlghoritm(UserData.getFunction(), UserData.getX0(),  UserData.getX1(), UserData.getEpsilon());
		//Double newtonMethodValue = fixedPoint.getRoot(UserData.getX0());
		
		System.out.println("Metoda iteracji prostych: ");
		if (newtonMethod.getResult() != null) {
			System.out.println("Przybliżona wartość pierwiastka: "
					+ newtonMethod.getResult(UserData.getEpsilon()));
			System.out.println("Ilość kroków: " + newtonMethod.getNrOfSteps());
		} else {
			System.out.println("Niestety nie udało się w 100 krokach");
		}

	}

}
