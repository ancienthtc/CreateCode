package util;
/** 
 * ��ƷУ������㷨  
 * ean-13�����㷨  
 * ǰ12λ������λ�ĺ�c1  
 * ǰ12λ��ż��λ�ĺ�c2  
 * �������͸�ż���͵��������  
 * ȡ����ĸ�λ������ʮȡ�� �������λ����0����ôУ���벻��10������0��
 * @author hanmj
  */
public class EanCodeUtil {
	public static void main(String[] args) {
		String eanCode = eanCode("1538209641");
		System.out.println(eanCode);
	}
	 public static String eanCode(String code){
		 int c1=0;
		 int c2=0;
		 for(int i=0;i<code.length();i+=2){
			 	char c=code.charAt(i);
			 	//�ַ���code�е�i��λ���ϵ��ַ� 
			 	int n=c-'0';
			 	c1+=n;//�ۼ�����λ�����ֺ�   
		 }
		 for(int i=1;i<code.length();i+=2){
			 char c=code.charAt(i);//�ַ���code�е�i��λ���ϵ��ַ�
			 int n=c-'0';
			 c2+=n;//�ۼ�ż��λ�����ֺ�   
		 }
		 int cc=c1+c2*3; 
		 int check=cc%10;   
		 check=(10-cc%10)%10;
		 code+=check+"";
	     return code;
	}
}
	
