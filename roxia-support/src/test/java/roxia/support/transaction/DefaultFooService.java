
package roxia.support.transaction;

public class DefaultFooService implements FooService {

    public Foo getFoo(String fooName) {
    	System.out.println("getFoo fooName");
        throw new UnsupportedOperationException();
    }

    public Foo getFoo(String fooName, String barName) {
    	System.out.println("getFoo fooName,barName");
        throw new UnsupportedOperationException();
    }

    public void insertFoo(Foo foo) {
    	System.out.println("insertFoo");
        throw new UnsupportedOperationException();
    }

    public void updateFoo(Foo foo) {
    	System.out.println("updateFoo");
        throw new UnsupportedOperationException();
    }
}