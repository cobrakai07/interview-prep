interface I {
    public void fun();
}

class A implements I {
    I i;

    A(I i) {
        this.i = i;
    }

    public void fun() {
        // Delegate the call to the `fun` method of `i`
        this.i.fun();
    }
}

public class FunctionInterfaceImplementation {
    public static void main(String[] args) {
        // Using a lambda to implement the functional interface `I`
        I lambdaImplementation = () -> System.out.println("Lambda Implementation!");

        // Passing the lambda as an argument to the constructor of class A
        A obj = new A(lambdaImplementation);

        // Calling the `fun` method, which delegates to the lambda
        obj.fun();
    }
}
