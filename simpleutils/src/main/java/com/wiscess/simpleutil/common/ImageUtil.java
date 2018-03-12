package com.wiscess.simpleutil.common;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by liuBo
 * 2018/3/12.
 */
public class ImageUtil {
    /**
     * 图片压缩
     * 仅支持8位RGB颜色分量，不存在alpha，png不要用这个方法压缩
     * @param srcImage  源图片文件路径或文件本身        （如：srcImage="G:/32/2015101713.jpg"或者srcImage= new File(xxx)）
     * @param tarImage  目的图片文件路径或文件本身    （如：tarImage="G:/32/2015101713_720_720.jpg"tarImage= new File(xxx)）
     * @param maxPixel  转换的像素                 （如：maxPixel=720）
     */
    private static void transformer(Object srcImage,Object tarImage,int maxPixel) {
        transformer(srcImage, tarImage, maxPixel,BufferedImage.TYPE_3BYTE_BGR,null);
    }

    /**
     * 图片压缩
     * @param srcImage  源图片文件路径或文件本身        （如：srcImage="G:/32/2015101713.jpg"或者srcImage= new File(xxx)）
     * @param tarImage  目的图片文件路径或文件本身    （如：tarImage="G:/32/2015101713_720_720.jpg"tarImage= new File(xxx)）
     * @param maxPixel  转换的像素                 （如：maxPixel=720）
     * @param formatName  ImageIO支持的格式                 （如：jpeg）
     * @param type
     * BufferedImage.TYPE_INT_RGB：8 位 RGB 颜色分量，不带alpha通道。
     * BufferedImage.TYPE_INT_ARGB：8 位 RGBA 颜色分量，带alpha通道。
     * BufferedImage.TYPE_INT_ARGB_PRE：8 位 RGBA 颜色分量，已预乘以 alpha。
     * BufferedImage.TYPE_INT_BGR：8 位 RGB 颜色分量Windows 或 Solaris 风格的图像，不带alpha通道。
     * BufferedImage.TYPE_3BYTE_BGR：8位RGB颜色分量，用3字节存储Blue、Green和Red三种颜色，不存在alpha。
     * BufferedImage.TYPE_4BYTE_ABGR：8位RGBA颜色分量，用3字节存储Blue、Green和Red三种颜色以及1字节alpha。
     * BufferedImage.TYPE_4BYTE_ABGR_PRE：具有用3字节存储的Blue、Green和Red三种颜色以及1字节alpha。
     * BufferedImage.TYPE_USHORT_565_RGB：具有5-6-5RGB颜色分量（5位Red、6位Green、5位Blue）的图像，不带alpha。
     * BufferedImage.TYPE_USHORT_555_RGB：具有5-5-5RGB颜色分量（5位Red、5位Green、5位Blue）的图像，不带alpha。
     * BufferedImage.TYPE_BYTE_GRAY：表示无符号byte灰度级图像（无索引）。
     * BufferedImage.TYPE_USHORT_GRAY：表示一个无符号short 灰度级图像（无索引）。
     * BufferedImage.TYPE_BYTE_BINARY：表示一个不透明的以字节打包的 1、2 或 4 位图像。
     * BufferedImage.TYPE_BYTE_INDEXED：表示带索引的字节图像。
     */
    public static void transformer(Object srcImage,Object tarImage,int maxPixel,int type,String formatName) {
        File srcImageFile,tarImageFile;

        if (srcImage==null||tarImage==null){
            return ;
        }
        if (srcImage instanceof File){
            srcImageFile = (File) srcImage;
        }else{
            srcImageFile = new File(srcImage.toString());
        }

        if (tarImage instanceof File){
            tarImageFile = (File) tarImage;
        }else{
            tarImageFile = new File(tarImage.toString());
        }

        // 生成图片转化对象
        AffineTransform transform = new AffineTransform();
        // 通过缓存读入缓存对象
        BufferedImage image = null;
        try {
            image = ImageIO.read(srcImageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();
        int changeWidth = 0;
        int changeHeight = 0;
        double scale = 0;
        if (maxPixel != 0) {
            if (imageWidth > imageHeight) {
                changeWidth = maxPixel;
                scale = (double) changeWidth / (double) imageWidth;
                changeHeight = (int) (imageHeight * scale);
            } else {
                changeHeight = maxPixel;
                scale = (double) changeHeight / (double) imageHeight;
                changeWidth = (int) (imageWidth * scale);
            }
        }
        // 生成转换比例
        transform.setToScale(scale, scale);
        // 生成转换操作对象
        AffineTransformOp transOp = new AffineTransformOp(transform, null);
        //生成压缩图片缓冲对象
        BufferedImage basll = new BufferedImage(changeWidth, changeHeight,
                type);
        //生成缩小图片
        transOp.filter(image, basll);
        try {
            //输出缩小图片
            ImageIO.write(basll, StringUtil.isEmpty(formatName)?"jpeg":formatName,tarImageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
