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
 * 支持EAN13, EAN8, UPCA, UPCE, Code 3 of 9, Codabar, Code 11, Code 93, Code 128, MSI/Plessey, Interleaved 2 of PostNet等
 * 利用jbarcode生成各种条形码！测试成功！分享给大家！
 * @author www.zuidaima.com
 */ 
public class OneBarcodeUtil {  
  public static void main(String[] paramArrayOfString) {  
      try {  
    	  	//生成
          	JBarcode localJBarcode = new JBarcode(EAN13Encoder.getInstance(),WidthCodedPainter.getInstance(),EAN13TextPainter.getInstance());  
          	String str = "221964422113";  
          	BufferedImage localBufferedImage = localJBarcode.createBarcode(str);  
          	saveToJPEG(localBufferedImage, "EAN.jpg");
            
          	//读取配置
			Properties properties = new Properties();
			// 使用ClassLoader加载properties配置文件生成对应的输入流
			InputStream in = OneBarcodeUtil.class.getClassLoader().getResourceAsStream("config.properties");
			// 使用properties对象加载输入流
			properties.load(in);
			//获取key对应的value值
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
    	  	/*读取配置文件，获取路径*/
			Properties properties = new Properties();
			// 使用ClassLoader加载properties配置文件生成对应的输入流
			InputStream in = OneBarcodeUtil.class.getClassLoader().getResourceAsStream("config.properties");
			// 使用properties对象加载输入流
			properties.load(in);
			//获取key对应的value值
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
		
	  	/*读取配置文件，获取路径*/
		Properties properties = new Properties();
		// 使用ClassLoader加载properties配置文件生成对应的输入流
		InputStream in = OneBarcodeUtil.class.getClassLoader().getResourceAsStream("config.properties");
		// 使用properties对象加载输入流
		properties.load(in);
		//获取key对应的value值
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
 
