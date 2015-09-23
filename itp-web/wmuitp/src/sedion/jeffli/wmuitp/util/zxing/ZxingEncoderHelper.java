package sedion.jeffli.wmuitp.util.zxing;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

import sedion.jeffli.wmuitp.constant.CourseInfoConstant;
import sedion.jeffli.wmuitp.util.DateUtil;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * @author Jeff Lee
 */
public class ZxingEncoderHelper 
{
	
	public static String getImgPath(HttpServletRequest request)
	{
		return request.getSession().getServletContext()
        .getRealPath(CourseInfoConstant.getQrCodePath());	
	}
	/**
	 * 生成二维码图片
	 * @param content		内容
	 * @param width			宽度
	 * @param height		高度
	 * @param imgPath		存储图片路径
	 */
    public static void encode(String content, int width, int height, String imgPath) 
    {
    	
        Hashtable<EncodeHintType, Object> hts = new Hashtable<EncodeHintType, Object>();//用于设置QR二维码参数 
        hts.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);				// 纠错等级
        hts.put(EncodeHintType.CHARACTER_SET, "utf-8");									// 指定编码格式为UTF-8
        
        try
        {
        	
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content,				//编码内容,编码类型(这里指定为二维码),
                    BarcodeFormat.QR_CODE, width, height, hts);							//图片宽度,图片高度,设置参数 
            
            MatrixToImageWriter.writeToFile(bitMatrix, "png", new File(imgPath));					//生成的二维码图片

        }
        catch (IOException e)
		{
        	System.out.println("IO流失败");
			e.printStackTrace();
		}
        catch (WriterException e)
		{
        	System.out.println("二维码图片写入失败");
			e.printStackTrace();
		} 
        catch (SecurityException e) 
        {
        	System.err.println("存在安全管理器， 方法拒绝对文件进行写入访问");
            e.printStackTrace();
        }
    }

    @SuppressWarnings("static-access")
	public static void main(String[] args) 
    {
        String imgPath = "d:/"+DateUtil.getFormateDateSimple()+".png";
        String contents = "http://192.168.191.1:8080/wmuitp/webservice/turnSCRPresent?courseInfoID=3";
        int width = 300, height = 300;
        ZxingEncoderHelper handler = new ZxingEncoderHelper();
        handler.encode(contents, width, height, imgPath); 
    }
}