package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageProcessing {
	public void imageResize() throws IOException {
		BufferedImage image=Thumbnails.of("D://myProjects//test1.jpg").scale(1f).outputQuality(0.5f)
		.asBufferedImage(); 

		addWatermark(image);
	}

	public void addWatermark(BufferedImage image) {
		// 水印图片
		String watermark = "src" + File.separator + "resources" + File.separator + "imag" + File.separator
				+ "watermark.png";
		// 输出到文件
		String outputFile = "D://myProjects//test5.jpg";

		// 不透明度
		float opacity = 0.25f;
		try {

//		image = ImageIO.read(file);
			// 获取水印图片
			File watermarkFile = new File(watermark);
			// ImageIO读取图片
			BufferedImage watermarkImage = ImageIO.read(watermarkFile);
			Thumbnails.of(image)
					// 设置图片大小
					.size(image.getWidth(), image.getHeight())
					// 加水印 参数：1.水印位置 2.水印图片 3.不透明度0.0-1.0
					.watermark(Positions.BOTTOM_RIGHT, watermarkImage, opacity)
					// 输出到文件
					.toFile(outputFile);

			System.out.println("ok");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void process(InputStream inputStream,String fileUrl) {
		// 水印图片
		String tomcat=System.getProperty("catalina.home");
		//String watermarkUrl=tomcat+File.separator+"localImg"+ File.separator+"watermark.png";
		
		// 不透明度
		//float opacity = 0.25f;
		try {
			// 获取水印图片
			//File watermarkFile = new File(watermarkUrl);
			// ImageIO读取图片
			//BufferedImage watermarkImage = ImageIO.read(watermarkFile);
			//获取到加水印的图片
//			BufferedImage newImage = Thumbnails.of(inputStream)
//					// 设置图片大小
//					.scale(1f)
//					// 加水印 参数：1.水印位置 2.水印图片 3.不透明度0.0-1.0
//					.watermark(Positions.BOTTOM_RIGHT, watermarkImage, opacity)
//					// 转化为BufferedImage
//					.asBufferedImage();
			// 压缩图片
			Thumbnails.of(inputStream)
					.scale(1f).outputQuality(0.5f)
					.toFile(fileUrl);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
