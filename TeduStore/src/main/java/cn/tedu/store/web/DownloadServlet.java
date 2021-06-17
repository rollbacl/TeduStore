package cn.tedu.store.web;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Random;

/**
 * Servlet implementation class DownloadServlet
 */
public class DownloadServlet extends 
	HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(
			HttpServletRequest request, 
			HttpServletResponse response) 
	throws ServletException, IOException {
		byte[] bytes=createPng("World!");
		//设置响应头
		response.setContentType("image/png");
		response.setContentLength(bytes.length);
		String name = "演示图片.png";
		//中文文件名必须进行URL编码处理
		name=URLEncoder.encode(name, "UTF-8");
		response.setHeader(
				"Content-Disposition",
				"attachment; filename=\""
				+name+"\"");
		//填充Body
		OutputStream out=response.getOutputStream();
		out.write(bytes);
		out.close();
	}

	//生成图片
	private byte[] createPng(String code)
		throws IOException{
		//创建图片对象
		BufferedImage img=new BufferedImage(
			100,40,BufferedImage.TYPE_3BYTE_BGR);
		//img.setRGB(0, 0, 0x0000ff); 
		//img.setRGB(50, 20, 0xffff00);
		//绘制5000个随机色点
		Random r = new Random();
		for(int i=0; i<5000; i++){
			int x = r.nextInt(img.getWidth());
			int y = r.nextInt(img.getHeight());
			int rgb = r.nextInt(0xffffff);//随机色
			img.setRGB(x, y, rgb);
		}
		//利用API绘制验证码字符串
		Graphics2D g = img.createGraphics();
		Color c = new Color(r.nextInt(0xffffff));
		g.setColor(c);
		Font font=new Font(Font.SANS_SERIF, 
				Font.ITALIC, 35);
		g.setFont(font);
		g.drawString(code, 5,34); 
		//利用API绘制5条混淆线
		for(int i=0; i<5; i++){
			int x1=r.nextInt(img.getWidth());
			int y1=r.nextInt(img.getHeight());
			int x2=r.nextInt(img.getWidth());
			int y2=r.nextInt(img.getHeight());
			g.drawLine(x1, y1, x2, y2);
		}
		
		//将图片对象编码为 png 数据
		//创建 数组输出流作为缓存区(酱油瓶)
		ByteArrayOutputStream out=
			new ByteArrayOutputStream();
		//将png图片数据(酱油)保存到缓存区
		ImageIO.write(img, "png", out);
		out.close();
		//获取缓存区中的png数据(酱油)
		byte[] bytes=out.toByteArray();
		return bytes;
	}

}
