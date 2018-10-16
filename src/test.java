import java.util.Date;

import javax.xml.crypto.Data;

import util.EanCodeUtil;

public class test {
	
	public static void main(String[] args) {
		EanCodeUtil a= new EanCodeUtil();
		System.out.println( EanCodeUtil.eanCode(new Date().getTime() + "" ) );;
	}
}
