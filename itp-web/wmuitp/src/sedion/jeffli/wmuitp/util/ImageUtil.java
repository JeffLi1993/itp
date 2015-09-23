package sedion.jeffli.wmuitp.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class ImageUtil 
{
	
	public static byte[] imageToBytes(String path) throws IOException {
		InputStream inputStream = new FileInputStream(path);
		BufferedInputStream in = new BufferedInputStream(inputStream);
		ByteArrayOutputStream out = new ByteArrayOutputStream(1024);

		byte[] temp = new byte[1024];
		int size = 0;
		while ((size = in.read(temp)) != -1) {
			out.write(temp, 0, size);
		}
		in.close();
		byte[] content = out.toByteArray();
		return content;
	}
}
