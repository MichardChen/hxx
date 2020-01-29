package com.framework.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

/**
 * @author Chenwx
 * @date
 */
public class Zxing {

    // 二维码尺寸
    private static final int QRCODE_SIZE = 300;
    // LOGO宽度
    private static final int logo_width = 80;
    // LOGO高度
    private static final int logo_height = 80;
    // 图片的格式
    private static final String format = "png";

    public static boolean orCode(String content, String path) {
        // 定义二维码的参数
        HashMap<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        // 定义字符集编码格式
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        // 纠错的等级 L > M > Q > H 纠错的能力越高可存储的越少，一般使用M
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        // 设置图片边距
        hints.put(EncodeHintType.MARGIN, 2);
        try {
            // 最终生成 参数列表 （1.内容 2.格式 3.宽度 4.高度 5.二维码参数）
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);
            int bitMatrixWidth = bitMatrix.getWidth();
            int bitMatrixHeight = bitMatrix.getHeight();
            // 写入到本地
            Path file = new File(path).toPath();
            MatrixToImageWriter.writeToPath(bitMatrix, format, file);
            //插入logo
            String insertPicPath = Zxing.class.getClassLoader().getResource("/").getPath();
            insertPicPath = insertPicPath.substring(1, insertPicPath.indexOf("WEB-INF"));
            insertPicPath = insertPicPath + "/statics/images/qrCode_head_portrait.png";
            addImageWeatermark(path, insertPicPath, -1, -1, 1);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 添加图片水印
     *
     * @param targetImg
     *            目标图片路径
     * @param waterImg
     *            水印图片路径
     * @param x
     *            水印图片距离目标图片左侧的偏移量，如果x<0, 则在正中间
     * @param y
     *            水印图片距离目标图片上侧的偏移量，如果y<0, 则在正中间
     * @param alpha
     *            透明度(0.0 -- 1.0, 0.0为完全透明，1.0为完全不透明)
     */

    public final static void addImageWeatermark(String targetImg, String waterImg, int x, int y, float alpha) {
        try {
            File file = new File(targetImg);
            Image image = ImageIO.read(file);
            int width = image.getWidth(null);
            int height = image.getHeight(null);
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufferedImage.createGraphics();
            g.drawImage(image, 0, 0, width, height, null);
            Image waterImage = ImageIO.read(new File(waterImg)); // 水印文件
            int width_1 = waterImage.getWidth(null);
            int height_1 = waterImage.getHeight(null);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            int widthDiff = width - width_1;
            int heightDiff = height - height_1;
            if (x < 0) {
                x = widthDiff / 2;
            } else if (x > widthDiff) {
                x = widthDiff;
            }
            if (y < 0) {
                y = heightDiff / 2;
            } else if (y > heightDiff) {
                y = heightDiff;
            }
            g.drawImage(waterImage, x, y, width_1, height_1, null); // 水印文件结束
            g.dispose();
            ImageIO.write(bufferedImage, format, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 图片缩放
     *
     * @param sourceFile 需要缩放的图片
     * @param savePath 缩放后的图片保存路径
     * @param height 高度
     * @param width 宽度
     * @param bb 比例不对时是否需要补白
     */
    @SuppressWarnings("restriction")
    public static double resize(String sourceFile, String savePath, int height, int width, boolean bb) {
        double scale = 1;
        try {
            Image image = ImageIO.read(new File(sourceFile));
            // 计算比例
            if ((image.getHeight(null) > height) || (image.getWidth(null) > width)) {
                if (image.getHeight(null) > image.getWidth(null)) {
                    scale = (new Integer(height)).doubleValue() / image.getHeight(null);
                } else {
                    scale = (new Integer(width)).doubleValue() / image.getWidth(null);
                }
            }
            //设置新画布的大小
            int w = (int)(image.getWidth(null)*scale);
            int h = (int)(image.getHeight(null)*scale);
            Image _img = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
            BufferedImage tmp = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = tmp.createGraphics();
            //开启文字抗锯齿
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            //画图
            g2.drawImage(_img, 0, 0, null);
            g2.dispose();
            //生成输出的目标文件
            File newFile = new File(savePath);
            FileUtils.forceMkdir(newFile.getParentFile());
            /*输出到文件流*/
            FileOutputStream newImage = new FileOutputStream(savePath);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newImage);
            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tmp);
            /* 压缩质量 */
            jep.setQuality(1f, true);
            encoder.encode(tmp, jep);
            /*近JPEG编码*/
            newImage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scale;
    }
}
