package pl.fixedpoint.marekag;

public class UserData {
		
	private static Double x0;
	private static Double x1;
	private static Integer epsilon;
    private static final int[] coefs = {-100, 0, 2, 1};
    private static final int[] degr = {0, 0, 1, 3};
    private  static Polynomial poly = null;

	public static Double getX0() {
		return x0;
	}

	public static void setX0(Double x0) {
		UserData.x0 = x0;
	}

	public static Double getX1() {
		return x1;
	}

	public static void setX1(Double x1) {
		UserData.x1 = x1;
	}

	public static Integer getEpsilon() {
		return epsilon;
	}

	public static void setEpsilon(Integer epsilon) {
		UserData.epsilon = epsilon;
	}
	
	public static void setEpsilon(Double epsilonDouble) {
		int epsilonInt = 0;

		while (!epsilonDouble.equals(1.0)) {
			if (epsilonDouble >= 1.0 || epsilonDouble <= 0.0)
				return;
//				throw new IllegalArgumentException(
//						"Dokładność musi być z przedziału (0,1)");
			epsilonInt++;
			epsilonDouble *= 10.0;
		}

		UserData.epsilon = epsilonInt;
	}
    public static Polynomial getFunction() {
        if(poly == null) {
            poly = new Polynomial(coefs[0], degr[0]);
            for (int i = 1; i < coefs.length; i++) {
                poly = poly.plus(new Polynomial(coefs[i], degr[i]));
            }
        }
        return poly;
    }
}
