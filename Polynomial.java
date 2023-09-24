public class Polynomial {
	
	double [] coefficients;
	
	public Polynomial(){
		
		coefficients = new double [] {0.0};
	}

	public Polynomial(double [] arr){
		
		coefficients = arr;
	}

	public Polynomial add(Polynomial a) {
		
		int maxDegree = Math.max(coefficients.length, a.coefficients.length);
        double [] result = new double[maxDegree];

        for (int i = 0; i < coefficients.length; i++) {
            result[i] = coefficients[i];
        }

        for (int i = 0; i < a.coefficients.length; i++) {
            result[i] += a.coefficients[i];
        }

        return new Polynomial(result);
	}
	
	public double evaluate(double x) {
		
		double y = 0.0;
		
		for (int i = 0; i < coefficients.length; i++) {
			y += coefficients[i] * Math.pow(x, i);
		}
		
		return y; 
	}
	
	public boolean hasRoot(double x) {

		return evaluate(x) == 0.0;
	}
	
}