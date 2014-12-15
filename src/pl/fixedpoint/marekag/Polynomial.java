package pl.fixedpoint.marekag;

public class Polynomial {
    private int[] coef;  // coefficients
    private int deg;     // degree of polynomial (0 for the zero polynomial)

    // a * x^b
    public Polynomial(int a, int b) {
        coef = new int[b + 1];
        coef[b] = a;
        deg = degree();
    }

    // return the degree of this polynomial (0 for the zero polynomial)
    public int degree() {
        int d = 0;
        for (int i = 0; i < coef.length; i++)
            if (coef[i] != 0) d = i;
        return d;
    }

    // return c = a + b
    public Polynomial plus(Polynomial b) {
        Polynomial a = this;
        Polynomial c = new Polynomial(0, Math.max(a.deg, b.deg));
        for (int i = 0; i <= a.deg; i++) c.coef[i] += a.coef[i];
        for (int i = 0; i <= b.deg; i++) c.coef[i] += b.coef[i];
        c.deg = c.degree();
        return c;
    }

    // return (a - b)
    public Polynomial minus(Polynomial b) {
        Polynomial a = this;
        Polynomial c = new Polynomial(0, Math.max(a.deg, b.deg));
        for (int i = 0; i <= a.deg; i++) c.coef[i] += a.coef[i];
        for (int i = 0; i <= b.deg; i++) c.coef[i] -= b.coef[i];
        c.deg = c.degree();
        return c;
    }

    // use Horner's method to compute and return the polynomial evaluated at x
    public double evaluate(double x) {
        double p = 0;
        for (int i = deg; i >= 0; i--)
            p = coef[i] + (x * p);
        return p;
    }

    // convert to string representation
    public String toString() {
        if (deg == 0) return "" + coef[0];
        if (deg == 1) return coef[1] + "x + " + coef[0];
        String s = coef[deg] + "x^" + deg;
        for (int i = deg - 1; i >= 0; i--) {
            if (coef[i] == 0) continue;
            else if (coef[i] > 0) s = s + " + " + (coef[i]);
            else if (coef[i] < 0) s = s + " - " + (-coef[i]);
            if (i == 1) s = s + "x";
            else if (i > 1) s = s + "x^" + i;
        }
        return s;
    }
}