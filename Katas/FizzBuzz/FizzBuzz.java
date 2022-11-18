import java.util.Scanner;

public class Main {



		public static void main(String[] args)  {
			
		Scanner input = new Scanner (System.in);
		System.out.println("Introduce un número: ");
		int num = input.nextInt();
			
		counter(num);
		}

		
		public static void counter(int num) {

		
		for (int i = 0; i<=num;i++) {
			if (i%3==0 && i%5!=0) {
				System.out.println("Fizz");
			}
			else if(i%5==0 && i%3!=0) {
				System.out.println("Buzz");
			}
			else if(i%3==0 && i%5==0) {
				System.out.println("FizzBuzz");
			}
			else {
				System.out.println(i);
		}
		
		}
	}
}
