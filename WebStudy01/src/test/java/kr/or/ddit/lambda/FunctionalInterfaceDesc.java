package kr.or.ddit.lambda;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * 함수와 메소드의 차이 
 * JavaScript
 * 1. 함수(일급함수 혹은 일급객체형 함수) : 일급객체 함수가 익명 형태로 자동 생성. 
 * 			└ 일급객체란? 참조주소가 존재하고 해당 참조를 변수를 통해 받을 수 있으며,
 * 						다른 함수의 호출시 아규먼트로 전달이 가능하고, 반환값의 형태로도 사용이 가능
 * 	function case1_1(){...} //함수 선언식
 * 	let case1_2 = function(){...} //함수 표현식
 * 	let case1_3 = ()=>{...} 화살표 함수 표현식
 * 
 * 2. 메소드 : 해당요소를 구현하는 객체가 있음
 * 	let obj = { 
 * 		case2_1:function(){...}
 * 		,case2_2:()=>{}
 * 	}
 * 
 * 3. 
 * 	class Component{
 * 		mathod1(){
 * 
 * 		}
 * 	}
 * 
 * let comp = new Component();
 * comp.method1();
 * 
 * Java에서의 함수형 프로그래밍 지원
 * 1.8 이전까지는 일급함수가 존재하지 않았기에, 익명객체를 통해 메소드 하나를 구현하고 있는 객체의 형태로 메소드를 전달해야했음.
 * (바로 아래의 accept 구조)
 * 
 * 1.8 버전부터 lambda 표현식으로 일급함수를 표현할 수 있음 --> 추후 컴파일시에 익명 객체로 래핑됨.
 *  일급함수로 표현될 수 있는 익명 객체는 함수형 인터페이스만 가능.
 *  
 *  함수형 인터페이스? : 인터페이스가 단 하나의 추상 메소드를 지님
 * 
 * 	자주 활용되는 functional interface 타입의 종류
 * 	자비는 쉽다 ? 많이 활용되는 것들은 미리 모두 구상되어있기에 사용이 쉽다는 뜻
 * 
 * 1. 생성형 FI (Supplier.class) : 인자가 없고, 반환값이 있음.
 * 2. 필터링 FI (Predicate.class) : 하나의 인자가 있고, boolean 반환값. 
 * 3. 처리형 FI (Function.class) : 하나의 인자가 있고, 고정되지 않은 반환값이 있음.
 * 4. 소비형 FI (Consumer) : 하나의 인자가 있고, 반환값이 없음(void) 
 */

class FunctionalInterfaceDesc {
	
	//숫자 5개인 
	Stream<Integer> fiveNumberBranding(Supplier<Integer> oem){
		Integer[] brand = new Integer[5];
		
		//setAll brand라는 배열에 oem에서 얻은 값을 배열의 크기만큼 집어넣음.
		Arrays.setAll(brand, (n)->oem.get());
		return Arrays.stream(brand);
	}
	
	@Test
	void testStream() {
		//oem의 값을 여기서 전달
		fiveNumberBranding(()->new Random().nextInt())
			.filter(n->n%2==1)
			.map(n->String.format("%d is odd number",n))
			.forEach(s->System.out.println(s));
//			.forEach(System.out::println); 이게 메소드 레퍼런스
 // s->System.out.println(s) 전달된 파라미터가 다른 메소드의 파라미터로 그대로 전달할떄 메소드 레퍼런스(축약) 가능
	}
	@Disabled
	@Test
	void testLambda() {
		//기본형 데이터를 사용할 수 없음
		Supplier<Integer> supplier=()->new Random().nextInt();
		
		//인자의 타입 지정
		Predicate<Integer> predicate= n->n%2==1;
		
	    Function<Integer, String> function = n->String.format("%d is odd number",n);
		
	    Consumer<String>  consumer= s->System.out.println(s);
	    
	    //위의 일급함수 값 new Random().nextInt()를 get 얻음
	    Optional.of(supplier.get())
	    		.filter(predicate)
	    		.map(function)
	    		.ifPresent(consumer);
		
	    //최종적으로 홀수만 출력
	}
	
	
	@Disabled
	@Test
	void test() {
		File folder = new File("D:\\00.medias\\movies");
		//객체를 통해 접근할때는 함수란 표현을안씀
		folder.list(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				//대충 필터링 구조 코드
				return false;
			}
		});
		
		//자바에서 지원하는 일급함수의 형태 마치 람다식으로 느낌만 내는 것
		folder.list((d,n)-> false);
		
	}
}

//그냥 인터페이스의 상태
interface NonFI{
	void method1();
	void method2();
}

//함수형 인터페이스의 상태 - 추상 메소드가 단 하나 만 존재하면 됨 구체화된 메소드는 몇개여도 상관 없음.
@FunctionalInterface
interface FICase{
	void method1();
	
	//default 태그는 {body가 필수} 
	default void method2() {
		System.out.println("기본 메소드");
	}
}




