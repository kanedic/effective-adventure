package kr.or.ddit.props.dao;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import kr.or.ddit.props.PersonVO;

public class PropertiesFilePersonDAOImpl implements PersonDAO {
	
	private Properties props;
	
	private static PersonDAO selfInstance;
	
	private PropertiesFilePersonDAOImpl(){
		this.props=new Properties();
		String qn = "/kr/or/ddit/props/PersonData.properties";
		
		try(
			InputStream is = this.getClass().getResourceAsStream(qn);
		){
			props.load(is);
		}catch (IOException e) {
			throw new RuntimeException(e);//예외 전환에는 원본 예외가 사라져서는 안됨. 
			//예외를 래핑을 해서 예외를 전환함 
		}
		
	}
	
	public static PersonDAO getInstance() {
		if(selfInstance==null) {
			selfInstance=new PropertiesFilePersonDAOImpl();
			}
		return selfInstance;
	}
	
	private void commit() {
		String qn = "/kr/or/ddit/props/PersonData.properties";
		try {
			
			Path filePath = Paths.get(this.getClass().getResource(qn).toURI());
			try(
					OutputStream os = Files.newOutputStream(filePath,StandardOpenOption.WRITE);				
				){
				props.store(os, LocalDateTime.now().toString());			
			}
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public int insertPerson(PersonVO person) {

		props.setProperty(person.getId(), personToString(person));
		
		commit();
		
		return 1;
	}

	@Override
	public PersonVO selectPerson(String id) {
		PersonVO person = 	Optional.ofNullable(props.getProperty(id))
									.map((v)->stringToPerson(id, v))
									.orElse(null);

		return person;
	}
	
	private String personToString(PersonVO person) {
		
		String value = String.format("%s|%s|%s|%s"
							 ,person.getName()
							 ,person.getGender()
							 ,person.getAge()
							 ,person.getAddress()
							 );
		return value;
	}
	
	private PersonVO stringToPerson(String key,String value) {
		String[] array = value.split("\\|");//크기는 4짜리 배열
		PersonVO person = new PersonVO();
		
		person.setId(key.toString());
		person.setName(array[0]);
		person.setGender(array[1]);
		person.setAge(array[2]);
		person.setAddress(array[3]);
		
		return person;
	}

	@Override
	public List<PersonVO> selectPersonList() {
		List<PersonVO> list = new ArrayList<>();
		
		for(Object key : props.keySet()) {
			String value = props.getProperty(key.toString());
			list.add(stringToPerson(key.toString(),value));
		}
		
		return list;
	}

	@Override
	public int updatePerson(PersonVO person) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deletePerson(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
