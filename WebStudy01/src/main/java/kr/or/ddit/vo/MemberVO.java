package kr.or.ddit.vo;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 회원 관리용 Domain Layer
 */
//lombok = 코드 제네레이터
//자바 빈 규약을 안지키면 outline view에 오류

@Getter
@Setter
@EqualsAndHashCode(of = {"memId","memRegno1","memRegno2"}) // 해시값들 비교값 할 것들을 설정
@ToString// exclude로
public class MemberVO implements Serializable{
	private String memId;
	@ToString.Exclude
	private transient String memPass;
	private String memName;
	@ToString.Exclude
	private transient String memRegno1;
	@ToString.Exclude
	private transient String memRegno2;
	private LocalDate memBir;
	private String memZip;
	private String memAdd1;
	private String memAdd2;
	private String memHometel;
	private String memComtel;
	private String memHp;
	private String memMail;
	private String memJob;
	private String memLike;
	private String memMemorial;
	private LocalDate memMemorialday;
	private Long memMileage;
	private String memDelete;
	
	
}
