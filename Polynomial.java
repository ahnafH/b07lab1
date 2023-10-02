import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Polynomial {

    private double[] nzcoefficients;
    private int[] exponents;

    public Polynomial() {
    	
        nzcoefficients = new double[]{0.0};
        exponents = new int[]{0};
        
    }

    public Polynomial(double[] arr) {
    	
        List<Double> nzcoeffList = new ArrayList<>();
        List<Integer> expList = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
        	
            if (arr[i] != 0.0) {
            	nzcoeffList.add(arr[i]);
            	expList.add(i);
            }
            
        }

        nzcoefficients = new double[nzcoeffList.size()];
        exponents = new int[expList.size()];

        for (int i = 0; i < nzcoeffList.size(); i++) {
        	
            nzcoefficients[i] = nzcoeffList.get(i);
            exponents[i] = expList.get(i);
            
        }
        
    }

    public Polynomial add(Polynomial a) {
    	
        int maxDegree = Math.max(exponents[exponents.length - 1], a.exponents[a.exponents.length - 1]);
        double[] result = new double[maxDegree + 1];

        for (int i = 0; i < nzcoefficients.length; i++) {
        	
            result[exponents[i]] += nzcoefficients[i];
            
        }

        for (int i = 0; i < a.nzcoefficients.length; i++) {
        	
            result[a.exponents[i]] += a.nzcoefficients[i];
            
        }

        return new Polynomial(result);
        
    }

    public double evaluate(double x) {
    	
        double y = 0.0;

        for (int i = 0; i < nzcoefficients.length; i++) {
        	
            y += nzcoefficients[i] * Math.pow(x, exponents[i]);
            
        }

        return y;
        
    }

    public boolean hasRoot(double x) {
    	
        return evaluate(x) == 0.0;
        
    }

    public Polynomial multiply(Polynomial a) {
    	
        double[] resultcoeff = new double[nzcoefficients.length + a.nzcoefficients.length];
        int[] resultexp = new int[nzcoefficients.length + a.nzcoefficients.length];

        for (int i = 0; i < nzcoefficients.length; i++) {
        	
            for (int j = 0; j < a.nzcoefficients.length; j++) {
            	
            	resultcoeff[i + j] += nzcoefficients[i] * a.nzcoefficients[j];
            	resultexp[i + j] = exponents[i] + a.exponents[j];
            	
            }
            
        }

        return new Polynomial(resultcoeff);
        
    }

    public Polynomial(File file) throws IOException {
    	
        BufferedReader reader = new BufferedReader(new FileReader(file));
        
        String line = reader.readLine();
        reader.close();

        String[] terms = line.split("(?=[+-])");
        
        List<Double> nzcoeffList = new ArrayList<>();
        List<Integer> expList = new ArrayList<>();

        for (String term : terms) {
        	
            term = term.trim();
            
            char sign = term.charAt(0);
            double coeffecient = Double.parseDouble(term.substring(1));
            int exp = 0;

            if (term.contains("x")) {
            	
                int xIndex = term.indexOf("x");
                
                if (xIndex < term.length() - 1) {
                    String expSubstr = term.substring(xIndex + 1);
                    exp = Integer.parseInt(expSubstr);
                }
            }

            if (sign == '-') {
            	coeffecient = -coeffecient;
            }

            nzcoeffList.add(coeffecient);
            expList.add(exp);
            
        }

        nzcoefficients = new double[nzcoeffList.size()];
        exponents = new int[expList.size()];

        for (int i = 0; i < nzcoeffList.size(); i++) {
        	
            nzcoefficients[i] = nzcoeffList.get(i);
            exponents[i] = expList.get(i);
            
        }
        
    }

    public void saveToFile(String fileName) throws IOException {
    	
        StringBuilder polynomialtxt = new StringBuilder();

        for (int i = 0; i < nzcoefficients.length; i++) {
        	
            if (i > 0) {
            	
                if (nzcoefficients[i] > 0) {
                	polynomialtxt.append("+");
                } 
                
                else {
                	polynomialtxt.append("-");
                }
            }

            double coeffecient = Math.abs(nzcoefficients[i]);
            int exp = exponents[i];

            if (coeffecient != 1 || exp == 0) {
            	polynomialtxt.append(coeffecient);
            }

            if (exp > 0) {
            	
            	polynomialtxt.append("x");

                if (exp > 1) {
                	polynomialtxt.append(exp);
                }
                
            }
        }

        FileWriter writer = new FileWriter(fileName);
        
        writer.write(polynomialtxt.toString());
        writer.close();
    }
    
}