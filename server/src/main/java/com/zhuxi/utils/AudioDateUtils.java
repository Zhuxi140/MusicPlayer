package com.zhuxi.utils;


import com.zhuxi.DTO.MusicDTO;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FrameGrabber;
import org.springframework.stereotype.Component;

import java.util.Map;

@Log4j2
@Component
@Data
public class AudioDateUtils {

    /**
     * 获取音频信息
     * @param filePath 文件路径
     * @return  音频信息
      */
    public  MusicDTO extractMetadata(String filePath){
        MusicDTO meta = new MusicDTO();

        try (FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(filePath)) {
            grabber.start();

            // 提取技术信息并存储
            TechnicalInfo(grabber, meta);

            //  获取标签信息并存储
            MetadataTags(grabber, meta);

            // 获取文件格式并存储
            Format(grabber, meta);

            meta.setFile_path(filePath);

            return meta;
        }catch (FFmpegFrameGrabber.Exception e)
            {
                handleFfmpegException(filePath, e);
            } catch (FrameGrabber.Exception e) {
            log.error("未知解析失败",e);
        }
        return meta;
}

    /**
     * 获取技术信息并存储
     * @param grabber FFmpegFrameGrabber对象
     * @param meta DTO
     */
    public static void TechnicalInfo(FFmpegFrameGrabber grabber, MusicDTO meta){

        int v = (int) (grabber.getLengthInTime() / 1_000_000.0);
        meta.setDuration(v); // 存储时长
        meta.setCodeType(grabber.getAudioCodecName());  // 存储编码格式
        meta.setBitrate(grabber.getAudioBitrate());  //  存储比特率

      /*  meta.setXXX(grabber.getSampleRate());  // 存储采样率
        meta.setXXX(grabber.getAudioChannels());        // 存储声道数*/
    }

    /**
     * 获取标签信息并存储
     * @param grabber FFmpegFrameGrabber对象
     * @param meta DTO
     */
    public static void MetadataTags(FFmpegFrameGrabber grabber, MusicDTO meta){

        Map<String, String> metadata = grabber.getMetadata(); //  获取标签信息
       /* for(Map.Entry<String,String> entry : metadata.entrySet()){
            String key = entry.getKey().toLowerCase();
            String value = entry.getValue();
            log.info("key:{},value:{}", key, value);
        }*/
        for(Map.Entry<String, String> entry : metadata.entrySet()) {

            String key = entry.getKey().toLowerCase(); //  将标签名转换为小写
            String value = entry.getValue(); //  获取标签值
            log.info("key:{},value:{}", key, value);
            ;
            switch (key) {
                case "title":  //  标题
                case "titel":
                case "标题":
                    meta.setTitle(value);
                    break;
                case "artist": //  歌手
                case "artist(s)":
                case "singer":
                case "艺人":
                    meta.setSinger(value);
                    break;
                case "album": //  专辑
                case "专辑":
                    meta.setAlbum(value);
                    break;
                case "company": //   公司
                case "publisher":
                case "label":
                    meta.setCompany(value);
                    break;
                case "lyric": // 作词
                case "lyricist":
                case "lyrics by":
                case "词作者":
                case "作词":
                    meta.setLyric(value);
                    break;
                case "composition": //  作曲
                case "arranger":
                case "composer":
                case "written by":
                case "曲作者":
                case "作曲":
                    meta.setComposition(value);
                    break;

            }
        }
    }

    /**
     * 自动检测文件格式
     */
    public static void Format(FFmpegFrameGrabber grabber, MusicDTO meta){
        try{
            String format = grabber.getFormat();
            if(format !=null && !format.isEmpty()){
                String primaryFormat = format.split(",")[0];
                meta.setFormat(primaryFormat);
            }
        }catch (Exception e){
            log.error("自动检测文件格式失败",e);
        }
    }


    /**
     * 处理FFmpeg异常
     * @param filePath 文件路径
     * @param e FFmpeg异常
     */
    private static void handleFfmpegException(String filePath, FFmpegFrameGrabber.Exception e) {
        String errorMsg = e.getMessage();

        if (errorMsg.contains("No such file or directory")) {
            log.error("文件不存在: {}", filePath);
        } else if (errorMsg.contains("Invalid data found")) {
            log.error("不支持的音频格式: {}", filePath);
        } else if (errorMsg.contains("Permission denied")) {
            log.error("文件访问权限不足: {}", filePath);
        } else {
            log.error("FFmpeg解析失败: {} | 错误: {}", filePath, errorMsg);
        }
    }
}


