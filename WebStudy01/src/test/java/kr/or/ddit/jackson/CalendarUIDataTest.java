package kr.or.ddit.jackson;

import static java.time.format.TextStyle.FULL;

import java.io.IOException;
import java.time.Month;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

class CalendarUIDataTest {

	@Test
	void test() throws StreamWriteException, DatabindException, IOException {
	Locale locale = Locale.getDefault();
	Map<String, Object> target= new HashMap<>();
	target.put("months",Arrays.stream(Month.values())
							  .map(m->m.getDisplayName(FULL, locale))
							  .toArray(String[]::new));
	
	Month[] mon = Month.values();
	String month="";
	String[] arrad = new String[mon.length];
	for(int i=0;i<mon.length;i++) {
//		month+=mon[i].getDisplayName(FULL, locale);
		arrad[i]=mon[i].getDisplayName(FULL, locale);
		System.out.println(arrad[i]);
	}
	
	Map<String,Object> ma=new HashMap<>();
	ma.put("mon", arrad);
	System.out.println(month);
	
	ObjectWriter mapper = new ObjectMapper().writerWithDefaultPrettyPrinter();
	
	
	Map<String, String> locales= Arrays.stream(Locale.getAvailableLocales())
								   	   .filter(l->!l.getDisplayName().isEmpty())
								  	   .collect(Collectors.toMap(Locale::toLanguageTag, l->l.getDisplayName(l)));
	//로컬 배열 가지고 공백들 없앤 후 단어태그랑 이름으로 맵 1을 만들고 그 맵들을 모아 한 맵으로 맵2 만들기
	
	Locale[] gg = Locale.getAvailableLocales();
	Map<String,String> map = new HashMap<>();
	Map<String,Object> mapALL = new HashMap<>();
	
	for(Locale loc:gg) {
		if(!loc.getDisplayName().isEmpty()) {
			map.put(loc.toLanguageTag(),loc.getDisplayName(loc));
		}
		mapALL.put("locale",map);
	}
	
	
	System.out.println(mapALL.entrySet());
	
	target.put("locales",locales);
	
	
	Map<String, String> zones= ZoneId.getAvailableZoneIds().stream()
									 .collect(Collectors.toMap(zid->zid, zid->ZoneId.of(zid).getDisplayName(FULL, locale)));
	
	Set<String> da = ZoneId.getAvailableZoneIds();

	target.put("zones",zones);
	mapper.writeValue(System.out, target);
	
	}

}









