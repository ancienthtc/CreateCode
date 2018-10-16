/* 
 * To change this template, choose Tools | Templates 
 * and open the template in the editor. 
 * @author www.zuidaima.com
 */  
package util;  
import java.awt.image.BufferedImage;  
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

import org.jbarcode.JBarcode;
import org.jbarcode.encode.EAN13Encoder;
import org.jbarcode.encode.EAN8Encoder;
import org.jbarcode.paint.EAN13TextPainter;
import org.jbarcode.paint.EAN8TextPainter;
import org.jbarcode.paint.WidthCodedPainter;  
import org.jbarcode.util.ImageUtil;  
/** 
 * ֧��EAN13, EAN8, UPCA, UPCE, Code 3 of 9, Codabar, Code 11, Code 93, Code 128, MSI/Plessey, Interleaved 2 of PostNet��
 * ����jbarcode���ɸ��������룡���Գɹ����������ң�
 * @author www.zuidaima.com
 */ 
public class OneBarcodeUtil {  
  public static void main(String[] paramArrayOfString) {  
      try {  
    	  	//����
          	JBarcode localJBarcode = new JBarcode(EAN13Encoder.getInstance(),WidthCodedPainter.getInstance(),EAN13TextPainter.getInstance());  
          	String str = "221964422113";  
          	BufferedImage localBufferedImage = localJBarcode.createBarcode(str);  
          	saveToJPEG(localBufferedImage, "EAN.jpg");
            
          	//��ȡ����
			Properties properties = new Properties();
			// ʹ��ClassLoader����properties�����ļ����ɶ�Ӧ��������
			InputStream in = OneBarcodeUtil.class.getClassLoader().getResourceAsStream("config.properties");
			// ʹ��properties�������������
			properties.load(in);
			//��ȡkey��Ӧ��valueֵ
			String path = properties.getProperty("path");
			System.out.println(path);//ok
      }  
      catch (Exception localException) {  
          localException.printStackTrace();  
      }  
  }  
  

  
  
  public static void saveToJPEG(BufferedImage paramBufferedImage, String paramString) {  
      saveToFile(paramBufferedImage, paramString, "jpeg");  
  }  
  public static void saveToFile(BufferedImage paramBufferedImage, String paramString1, String paramString2) {  
      try {  
    	  	/*��ȡ�����ļ�����ȡ·��*/
			Properties properties = new Properties();
			// ʹ��ClassLoader����properties�����ļ����ɶ�Ӧ��������
			InputStream in = OneBarcodeUtil.class.getClassLoader().getResourceAsStream("config.properties");
			// ʹ��properties�������������
			properties.load(in);
			//��ȡkey��Ӧ��valueֵ
			String path = properties.getProperty("path");
			
			FileOutputStream localFileOutputStream = new FileOutputStream( path + paramString1);
			ImageUtil.encodeAndWrite(paramBufferedImage, paramString2, localFileOutputStream, 96, 96);
          
			localFileOutputStream.close();  
      }  
      catch (Exception localException) {  
          	localException.printStackTrace();  
      }  
  }  
  
  public static byte[] getFileBytes(BufferedImage paramBufferedImage, String paramString1, String paramString2)
  {
	  byte[] bytes ;
	  try {  
		
	  	/*��ȡ�����ļ�����ȡ·��*/
		Properties properties = new Properties();
		// ʹ��ClassLoader����properties�����ļ����ɶ�Ӧ��������
		InputStream in = OneBarcodeUtil.class.getClassLoader().getResourceAsStream("config.properties");
		// ʹ��properties�������������
		properties.load(in);
		//��ȡkey��Ӧ��valueֵ
		String path = properties.getProperty("path");
			
		FileOutputStream localFileOutputStream = new FileOutputStream( path + paramString1);
		bytes =ImageUtil.encode(paramBufferedImage, paramString2, 96, 96);
    
		localFileOutputStream.close();  
		return bytes;
	}
	catch (Exception localException) {  
    	localException.printStackTrace();  
    	return null;
	}  
  }
  
}
 
