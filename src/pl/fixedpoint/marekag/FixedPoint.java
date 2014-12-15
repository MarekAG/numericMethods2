package pl.fixedpoint.marekag;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FixedPoint implements NumericMethods {

	// first element of array is root degree 
	private static final Double[] FUNCTION = { 3.0, 100.0, -2.0 };
	private int epsilon;
	private int nrOfSteps;

	public FixedPoint(int epsilon) {
		this.epsilon = epsilon;
	}

//	public FixedPoint(Double epsilonDouble,  Double x0) {
//		int epsilonInt = 0;

//		while (!epsilonDouble.equals(1.0)) {
//			if (epsilonDouble >= 1.0 || epsilonDouble <= 0.0)
//				return;
//				throw new IllegalArgumentException(
//						"Dokładność musi być z przedziału (0,1)");
//			epsilonInt++;
//			epsilonDouble *= 10.0;
//		}

//		this.epsilon = epsilonInt;
//	}

	public int getNrOfSteps() {
		return nrOfSteps;
	}

	private void setNrOfSteps(int nrOfSteps) {
		this.nrOfSteps = nrOfSteps;
	}

	public Double getRoot(Double firstApprox) {
		Double lastRounded = 0.0;
		Double presentRounded = 0.0;
		int i = 0;
		Double present = firstApprox;
		do {
			i++;
			Double last = present;
			present = rootThird(FUNCTION[1] + FUNCTION[2] * last);
			setNrOfSteps(i);

			BigDecimal lastBig = new BigDecimal(last);
			lastBig = lastBig.setScale(epsilon, RoundingMode.HALF_UP);
			lastRounded = lastBig.doubleValue();

			BigDecimal presentBig = new BigDecimal(present);
			presentBig = presentBig.setScale(epsilon, RoundingMode.HALF_UP);
			presentRounded = presentBig.doubleValue();

		} while ((!lastRounded.equals(presentRounded)) && i != 100);

		if (i == 100) {
			return null;
		}
		return presentRounded;
	}

    @Override
    public Double getResult(int precision) {
        return null;
    }

    @Override
    public Double getResult() {
        return null;
    }

    private double rootThird(Double number) {
		return Math.pow(Math.exp(1 / FUNCTION[0]), Math.log(number));
	}

}
