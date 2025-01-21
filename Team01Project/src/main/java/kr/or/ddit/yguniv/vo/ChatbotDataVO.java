package kr.or.ddit.yguniv.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ChatbotDataVO  implements Serializable {
	@Size(min = 10,max = 10)
	@NotBlank
	private String chatbId;
	@Size(max = 3000)
	private String chatdExpectedCn;
	@Size(max = 3000)
	private String chatdAnsCn;
	@Size(max = 10)
	private String chatdIncludWord1;
	@Size(max = 10)
	private String chatdIncludWord2;
	@Size(max = 10)
	private String chatdIncludWord3;
	@Size(max = 10)
	private String chatdIncludWord4;
	@Size(max = 10)
	private String chatdIncludWord5;
}
