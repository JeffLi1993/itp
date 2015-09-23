package sedion.jeffli.wmuitp.web.servlet.validation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/servlet/Validation",loadOnStartup=9)
public class Validation extends HttpServlet {

	public static final int WIDTH=120;
	public static final int HEIGHT=25;
	static String validation="";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		validation=request.getParameter("validation");
		BufferedImage image=new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
//		String validation=request.getParameter("timeStamp");

		Graphics2D g=(Graphics2D) image.getGraphics();
		//设置背景色
		setBackGround(g);
		//设置边框
		setBorder(g);
		//画干扰线
		drawRandomLine(g);
		//写随机数
		drawRandomNum(g);
		//设置不缓存
		response.setIntHeader("Expires", -1);
		response.setHeader("Cache", "no-cache");
		response.setHeader("Pragma", "no-cache");
		
		
		//写给浏览器
		response.setContentType("image/jpeg");
		ImageIO.write(image, "jpg", response.getOutputStream());
		
	}

			//设置背景色
			private void setBackGround(Graphics g)
			{	
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, WIDTH, HEIGHT);
			}
			
			//设置边框
			private void setBorder(Graphics g)
			{
				g.setColor(Color.BLUE);
				g.drawRect(1, 1, WIDTH-2, HEIGHT-2);
			}
			
			//画干扰线
			private void drawRandomLine(Graphics g)
			{
				g.setColor(Color.GREEN);
				for(int i=0;i<5;i++)
				{
					int x1=new Random().nextInt(WIDTH);
					int y1=new Random().nextInt(HEIGHT);
					
					int x2=new Random().nextInt(WIDTH);
					int y2=new Random().nextInt(HEIGHT);
					g.drawLine(x1, y1, x2, y2);
					
				}
			}
			
//			String base="1234567890abcdefghigklmnopqrstuvwxyz";
			
			//写随机数
			private void drawRandomNum(Graphics2D g)
			{
				String validation1="";
				g.setColor(Color.RED);
				g.setFont(new Font("宋体",Font.BOLD,20));

				int x=5;
				for(int i=0;i<4;i++)
				{
					int degree=new Random().nextInt(60)-30;
					String ch=validation.charAt(i)+"";
					validation1+=ch+"";
//					String ch=validation.charAt(i)+"";
					g.rotate(degree*Math.PI/180,x,20);//设置旋转弧度
					g.drawString(ch, x, 20);
					g.rotate(-degree*Math.PI/180,x,20);
					x+=30;
				}
//				System.out.println(validation1);
	}
			
	//test
		@Override
		public void init() throws ServletException {
			super.init();
			System.out.println("验证码初始化");
		}
}
