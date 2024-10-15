package ca.bcit.comp2522.setB.project.marcuslages;

public class Test<T extends Comparable> {

    T a;

    public Test(T a) {
        this.a = a;
    }

    public static <T extends Number>T sum(T a, T b) {
        return a;
    }
}
