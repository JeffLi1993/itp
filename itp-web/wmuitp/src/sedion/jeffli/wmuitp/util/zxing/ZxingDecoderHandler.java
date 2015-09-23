package sedion.jeffli.wmuitp.util.zxing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

/**
 * @author Jeff Lee
 */
public class ZxingDecoderHandler 
{

	/**
	 * 解码
	 * @param imgPath		二维码图片路径
	 * @return
	 */
    public String decode(String imgPath) 
    {
    	
        BufferedImage image = null;
        Result result = null;
        
        try 
        {
            image = ImageIO.read(new File(imgPath));
            if (image == null) 
            {
                System.out.println("文件不存在！");								//应该抛个异常的
            }
            
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();
            hints.put(DecodeHintType.CHARACTER_SET, "utf-8");

            result = new MultiFormatReader().decode(bitmap, hints);
            return result.getText();
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
        return null;
    }

    public static void main(String[] args) 
    {
        String imgPath = "d:/33.png";
        
        ZxingDecoderHandler handler = new ZxingDecoderHandler();
        String decodeContent = handler.decode(imgPath);
        
        System.out.println("内容如下：");
        System.out.println(decodeContent);  
    }
}