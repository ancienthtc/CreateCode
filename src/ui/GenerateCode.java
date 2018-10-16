package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jbarcode.JBarcode;
import org.jbarcode.encode.EAN13Encoder;
import org.jbarcode.encode.EAN8Encoder;
import org.jbarcode.encode.InvalidAtributeException;
import org.jbarcode.paint.EAN13TextPainter;
import org.jbarcode.paint.EAN8TextPainter;
import org.jbarcode.paint.WidthCodedPainter;

import util.EanCodeUtil;
import util.GenerateString;
import util.OneBarcodeUtil;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class GenerateCode extends JFrame {

	private final String version = "1.1";
	private JPanel contentPane;
	private JTextField textField_front;
	private JTextField textField_back;
	private JTextField textField_t_r_len;
	private JTextField textField_r_len;
	private JLabel lblP;
	private Icon icon;
	
	private JRadioButton radioButton;
	private JRadioButton radioButton_1;
	private JRadioButton radioButton_2;
	
	private JButton btn_y;
	private JButton btn_n;
	private JTextArea textArea;
	
	private ButtonGroup groupRadio;
	
	//菜单
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerateCode frame = new GenerateCode();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GenerateCode() {
		setTitle("\u751F\u6210\u6761\u7801 "+version);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 473, 377);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_1 = new JLabel("\u524D\u7F00");
		label_1.setBounds(15, 46, 35, 15);
		contentPane.add(label_1);
		
		textField_front = new JTextField();
		textField_front.setBounds(52, 43, 141, 21);
		contentPane.add(textField_front);
		textField_front.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u540E\u7F00");
		lblNewLabel.setBounds(15, 161, 35, 15);
		contentPane.add(lblNewLabel);
		
		//图片显示框
		lblP = new JLabel("");
		lblP.setBounds(250, 47, 200, 200);
		contentPane.add(lblP);
		
		textField_back = new JTextField();
		textField_back.setBounds(52, 158, 141, 21);
		contentPane.add(textField_back);
		textField_back.setColumns(10);
		
		radioButton = new JRadioButton("\u968F\u673A");
		radioButton.setBounds(15, 76, 61, 23);
		contentPane.add(radioButton);
		
		radioButton_1 = new JRadioButton("\u65F6\u95F4");
		radioButton_1.setBounds(15, 101, 61, 23);
		contentPane.add(radioButton_1);
		radioButton_1.setSelected(true);
		
		radioButton_2 = new JRadioButton("\u65F6\u95F4+\u968F\u673A");
		radioButton_2.setBounds(15, 126, 87, 23);
		contentPane.add(radioButton_2);
		
		textField_t_r_len = new JTextField();
		textField_t_r_len.setBounds(104, 127, 45, 21);
		contentPane.add(textField_t_r_len);
		textField_t_r_len.setColumns(10);
		
		textField_r_len = new JTextField();
		textField_r_len.setBounds(80, 77, 45, 21);
		contentPane.add(textField_r_len);
		textField_r_len.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setBounds(27, 257, 418, 78);
		contentPane.add(textArea);
		
		groupRadio = new ButtonGroup();
		groupRadio.add(radioButton);
		groupRadio.add(radioButton_1);
		groupRadio.add(radioButton_2);
		
		btn_y = new JButton("OK");
		btn_y.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String front = textField_front.getText().trim().toString();
				String back = textField_back.getText().trim().toString();
				String context = "";
				if(radioButton_1.isSelected())	//选择时间
				{
					context=EanCodeUtil.eanCode(new Date().getTime() + "" ).toString();
				}
				if(radioButton.isSelected())
				{
					String len = textField_r_len.getText().trim();
					if(len=="" || len.length()<=0)
					{
						len = "0";
					}
					if( !isNumeric(len) )
					{
						len = "0";
					}
					else
					{
						if( Integer.parseInt(len)<=0 )
						{
							len = "0";
						}
					}
					context = new GenerateString().getRandomString_Num( Integer.parseInt(len) );
				}
				if(radioButton_2.isSelected())
				{
					String len = textField_t_r_len.getText().trim();
					if(len=="" || len.length()<=0)
					{
						len = "0";
					}
					if( !isNumeric(len) )
					{
						len = "0";
					}
					else
					{
						if( Integer.parseInt(len)<=0 )
						{
							len = "0";
						}
					}
			        //获取默认选中的日期的年月日星期的值，并赋值
			        Calendar calendar = Calendar.getInstance();//日历对象
			        String yearStr = calendar.get(Calendar.YEAR)+"";//获取年份
			        int month = calendar.get(Calendar.MONTH) + 1;//获取月份
			        String monthStr = month < 10 ? "0" + month : month + "";
			        int day = calendar.get(Calendar.DATE);//获取日
			        String dayStr = day < 10 ? "0" + day : day + "";
					context = yearStr.substring(2)+monthStr+dayStr;
					context += new GenerateString().getRandomString_Num( Integer.parseInt(len) );
				}
				String xcode = front + context + back;
				if(xcode.length()>=12)
				{
					xcode = xcode.substring(0, 12);
				}
				textArea.setText(xcode);
				//复制到剪贴板
				Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();  
		        Transferable tText = new StringSelection(xcode);  
		        clip.setContents(tText, null);
		        
	    	  	//生成
		        try {
		        	JBarcode localJBarcode = new JBarcode(EAN13Encoder.getInstance(),WidthCodedPainter.getInstance(),EAN13TextPainter.getInstance());  
	          		//String str = "2219644";  
	          		BufferedImage localBufferedImage;
				
					localBufferedImage = localJBarcode.createBarcode(xcode);
					
					icon =  new ImageIcon(OneBarcodeUtil.getFileBytes(localBufferedImage, "EAN" + xcode + ".jpg" , "jpeg"));
					lblP.setIcon(icon);
					OneBarcodeUtil.saveToJPEG(localBufferedImage, "EAN" + xcode + ".jpg");
				} catch (InvalidAtributeException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				} catch (Exception e) {
					//e.printStackTrace();
				}
		        
	          	
			}
		});
		btn_y.setBounds(10, 200, 54, 36);
		contentPane.add(btn_y);
		
		btn_n = new JButton("Clear");
		btn_n.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText("");
				textField_back.setText("");
				textField_front.setText("");
				textField_r_len.setText("");
				textField_t_r_len.setText("");
				groupRadio.clearSelection();
				
				radioButton_1.setSelected(true);
			}
		});
		btn_n.setBounds(70, 200, 66, 36);
		contentPane.add(btn_n);
		
		JButton btn_save = new JButton("Save");
		btn_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String xcode = textArea.getText().trim();
	    	  	//生成
		        try {
		        	JBarcode localJBarcode = new JBarcode(EAN13Encoder.getInstance(),WidthCodedPainter.getInstance(),EAN13TextPainter.getInstance());  
	          		//String str = "2219644";  
	          		BufferedImage localBufferedImage;
				
					localBufferedImage = localJBarcode.createBarcode(xcode);
					
					icon =  new ImageIcon(OneBarcodeUtil.getFileBytes(localBufferedImage, "EAN" + xcode + ".jpg" , "jpeg"));
					lblP.setIcon(icon);
					OneBarcodeUtil.saveToJPEG(localBufferedImage, "EAN" + xcode + ".jpg");
				} catch (InvalidAtributeException e1) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				} catch (Exception e1) {
					//e.printStackTrace();
				}
			}
		});
		btn_save.setBounds(141, 200, 66, 36);
		contentPane.add(btn_save);
		
		JLabel lblNewLabel_1 = new JLabel("(\u586B\u5199\u968F\u673A\u4F4D\u6570)");
		lblNewLabel_1.setBounds(135, 78, 93, 19);
		contentPane.add(lblNewLabel_1);
		
		JLabel label = new JLabel("(\u586B\u5199\u4F4D\u6570)");
		label.setBounds(159, 128, 66, 19);
		contentPane.add(label);
		
		//菜单
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 455, 21);
		contentPane.add(menuBar);
		
		menu = new JMenu("\u5173\u4E8E");
		menuBar.add(menu);
		
		menuItem = new JMenuItem("\u5173\u4E8E");
		menu.add(menuItem);
		menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				textArea.setText("版本号"+version+"\n"
				+"说明:只有时间支持图片条码。\n更改保存路径，需用压缩软件打开本程序，更改里面的config.properties\n" 
				+"GitHub:ancienthtc");
			}
		});

		

		
	}
	
    public boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }


}
