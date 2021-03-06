package pl.fixedpoint.marekag;

public interface NumericMethods {
	
	public int getNrOfSteps();
	public Double getRoot(Double firstApprox);
    public Double getResult(int precision) throws Exception;
    public Double getResult() throws Exception;
}
