package ui;
 
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Hashtable;
 
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
 
//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.EncodeHintType;
//import com.google.zxing.MultiFormatWriter;
//import com.google.zxing.common.BitMatrix;
 
public class SwingTestImg  extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JLabel label;
	private Icon icon;
	private Image image;
	public static void main(String[] args) {
		new SwingTestImg();
	}
	public SwingTestImg(){
		try{
			setTitle("测试图片简单显示");
			setSize(300,300);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			label = new JLabel();
			add(label);
			setVisible(true);
			//A：网路URL图片
//			icon = new ImageIcon(new URL("http://tp1.sinaimg.cn/3223061260/180/5659068018/1"));
			//B：项目目录下图片
//			InputStream is = SwingTestImg.class.getResourceAsStream("twodimensioncode.gif");
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			byte [] buff = new byte[100];
//			int readCount = 0;
//			while((readCount = is.read(buff,0,100)) > 0){
//				baos.write(buff,0,readCount);
//			}
//			byte [] inbyte = baos.toByteArray();
//			icon =  new ImageIcon(inbyte);
//			//C：本地磁盘图片，图片太大，会导致空白显示
//			image =  new ImageIcon("D:/1.png").getImage();
			//D：代码生成的BufferedImage二维码图片
//			Hashtable hints = new Hashtable();
//			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
//			BitMatrix matrix = new MultiFormatWriter().encode("http://www.vcspark.com/", BarcodeFormat.QR_CODE, 300, 300,hints);
//			int width = matrix.getWidth();
//			int height = matrix.getHeight();
//			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//			for(int x = 0;x < width; x++){
//				for(int y = 0;y < height; y++){
//					image.setRGB(x, y, matrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
//				}
//			}
//			icon = new ImageIcon(image);
			//实际
			InputStream is = SwingTestImg.class.getResourceAsStream("twodimensioncode.gif");
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte [] buff = new byte[100];
			int readCount = 0;
			while((readCount = is.read(buff,0,100)) > 0){
				baos.write(buff,0,readCount);
			}
			byte [] inbyte = baos.toByteArray();
			icon =  new ImageIcon(inbyte);
		}catch(Exception e){
			System.out.println("初始化失败"+e.getMessage());
			e.printStackTrace();
		}
		label.setIcon(icon);
//		label.setIcon(new ImageIcon(image));
	} 
}
