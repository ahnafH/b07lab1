import java.io.File;
import java.io.IOException;

public class Driver {
    public static void main(String[] args) {

        Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3));

        double[] c1 = {6, 0, 0, 5};
        Polynomial p1 = new Polynomial(c1);

        double[] c2 = {0, -2, 0, 0, -9};
        Polynomial p2 = new Polynomial(c2);

        Polynomial s = p1.add(p2);
        System.out.println("s(0.1) = " + s.evaluate(0.1));

        if (s.hasRoot(1))
            System.out.println("1 is a root of s");
        
        else
            System.out.println("1 is not a root of s");

        Polynomial m = p1.multiply(p2);
        System.out.println("m(1) = " + m.evaluate(1));

        try {
            p1.saveToFile("polynomial.txt");
            Polynomial pFile = new Polynomial(new File("polynomial.txt"));
            System.out.println("pFile(1) = " + pFile.evaluate(1));
        } 
        
        catch (IOException error) {
            System.out.println("Error: " + error.getMessage());
        }
    }
}