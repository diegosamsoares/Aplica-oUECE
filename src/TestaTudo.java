

public class TestaTudo {

	public static void main(String[] args) {
		
		int ant=0;
		int prx=1;
		
		
		System.out.println(ant);
		System.out.println(prx);
		for (int i = 0; i < 11; i++) {
			ant=prx-ant;
			prx=prx+ant;
			
			
			
			System.out.println(prx);
		}
	}
}
