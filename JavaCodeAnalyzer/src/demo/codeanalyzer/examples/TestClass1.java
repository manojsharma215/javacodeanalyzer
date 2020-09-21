package demo.codeanalyzer.examples;

public final class TestClass1 {


	public static void main(String args[]) {
		System.out.println("This is a test");
		System.out.println("This is a test");
		System.out.println("This is a test");
		System.out.println("This is a test");

	}
	@Deprecated
	public void test(int a, int b, int c) {
		System.out.println("This is a deprecated method");
		System.out.println("This is a deprecated method");
		System.out.println("This is a deprecated method");
		System.out.println("This is a deprecated method");
	}

	@Override
	public boolean equals(Object obj) {
		return true;
	}

}
