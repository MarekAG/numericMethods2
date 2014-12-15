package pl.fixedpoint.marekag;

/**
 * Created by Jakub on 2014-12-15.
 */
public class Validator {
    private static Boolean validationFail = true;

    public static Boolean validateNewtonStartPoints(double x1, double x2, Polynomial pol) {
        if(pol.evaluate(x1) < 0 && pol.evaluate(x2) > 0 || pol.evaluate(x1) > 0 && pol.evaluate(x2) < 0) {
                validationFail = false;
                return validationFail;

        }
        validationFail = true;
        return validationFail;
    }

    public static Boolean getLastResult() {
        return validationFail;
    }
}
