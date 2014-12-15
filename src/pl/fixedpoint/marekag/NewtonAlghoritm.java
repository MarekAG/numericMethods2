package pl.fixedpoint.marekag;

/**
 * Created by Jakub on 2014-12-08.
 */
public class NewtonAlghoritm implements NumericMethods{
    private Polynomial polynomial;
    private double x1;
    private double x2;
    private double epsilon;
    private int maxIter;
    private int counter;
    private int result;

    public NewtonAlghoritm(Polynomial polynomial, double x1, double x2, double epsilon) {



        this.polynomial = polynomial;
        this.x1 = x1;
        this.x2 = x2;
        this.epsilon = epsilon;
        this.maxIter = 1000;
    }

    public NewtonAlghoritm(Polynomial polynomial, int x1, int x2, double epsilon, int maxIter) throws Exception {

        this.polynomial = polynomial;
        this.x1 = x1;
        this.x2 = x2;
        this.epsilon = epsilon;
        this.maxIter = maxIter;
    }


    public int getIterationsNumber() {
        return counter;
    }
//    x3 + 2x-100 = 0
//    x0 = 4,  x1 = 5, eps = 5
//    f(x1) = f(4) = 64 + 8 - 100 = -28
//    f(x2) = f(5) = 125 + 10 - 100 = 35
//    x2 = 4 - (-28) x (1) / 7
//    x2 = 32/7 = 4,57
//    f(x2) = 4,5
//            | f4,5) | = 4,5 < eps

    private double compute() {
        double x1_value, x2_value, x3_value;
        double tmp_x1=x1, tmp_x2=x2, tmp_x3 = 0;
        x1_value = polynomial.evaluate(tmp_x1);
        x2_value = polynomial.evaluate(tmp_x2);
        for(int i=0; i<maxIter;i++) {

             tmp_x3 = tmp_x1 - x2_value * (tmp_x1 - tmp_x2) / (x1_value - x2_value) ;
             x3_value = polynomial.evaluate(tmp_x3);

            double test = Math.abs(x3_value);
            if(Math.abs(x3_value) < epsilon){ counter = i; return tmp_x3; }

            tmp_x2 = tmp_x1;
            tmp_x1 = tmp_x3;


            x2_value = x1_value;
            x1_value = x3_value;

        }
        counter = maxIter;
        return -1;
    }

    @Override
    public int getNrOfSteps() {
        return counter;
    }

    @Override
    public Double getRoot(Double firstApprox) {
        return null;
    }

    @Override
    public Double getResult() {
        return compute();
    }

    @Override
    public Double getResult(int precision) {
        double _result = compute();
        _result = Math.round(_result *  Math.pow(10, precision));
        _result = _result /  Math.pow(10, precision);
        return _result;
    }

}