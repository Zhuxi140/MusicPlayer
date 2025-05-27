package generator.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

/**
* 
* @TableName music
*/
public class Music implements Serializable {

    /**
    * 
    */
    @NotNull(message="[]不能为空")
    @ApiModelProperty("")
    private Integer id;
    /**
    * 歌曲名称
    */
    @NotBlank(message="[歌曲名称]不能为空")
    @Size(max= 100,message="编码长度不能超过100")
    @ApiModelProperty("歌曲名称")
    @Length(max= 100,message="编码长度不能超过100")
    private String title;
    /**
    * 歌手
    */
    @NotBlank(message="[歌手]不能为空")
    @Size(max= 100,message="编码长度不能超过100")
    @ApiModelProperty("歌手")
    @Length(max= 100,message="编码长度不能超过100")
    private String singer;
    /**
    * 作词
    */
    @NotBlank(message="[作词]不能为空")
    @Size(max= 100,message="编码长度不能超过100")
    @ApiModelProperty("作词")
    @Length(max= 100,message="编码长度不能超过100")
    private String lyric;
    /**
    * 作曲
    */
    @NotBlank(message="[作曲]不能为空")
    @Size(max= 100,message="编码长度不能超过100")
    @ApiModelProperty("作曲")
    @Length(max= 100,message="编码长度不能超过100")
    private String composition;
    /**
    * 制作人
    */
    @NotBlank(message="[制作人]不能为空")
    @Size(max= 100,message="编码长度不能超过100")
    @ApiModelProperty("制作人")
    @Length(max= 100,message="编码长度不能超过100")
    private String producer;
    /**
    * 公司
    */
    @NotBlank(message="[公司]不能为空")
    @Size(max= 100,message="编码长度不能超过100")
    @ApiModelProperty("公司")
    @Length(max= 100,message="编码长度不能超过100")
    private String company;
    /**
    * 专辑
    */
    @Size(max= 50,message="编码长度不能超过50")
    @ApiModelProperty("专辑")
    @Length(max= 50,message="编码长度不能超过50")
    private String album;
    /**
    * 歌词文件路径
    */
    @Size(max= 500,message="编码长度不能超过500")
    @ApiModelProperty("歌词文件路径")
    @Length(max= 500,message="编码长度不能超过500")
    private String lyricData;
    /**
    * 秒数
    */
    @ApiModelProperty("秒数")
    private Integer duration;
    /**
    * MinIO对象存储路径
    */
    @NotBlank(message="[MinIO对象存储路径]不能为空")
    @Size(max= 500,message="编码长度不能超过500")
    @ApiModelProperty("MinIO对象存储路径")
    @Length(max= 500,message="编码长度不能超过500")
    private String filePath;
    /**
    * 绑定用户id
    */
    @NotNull(message="[绑定用户id]不能为空")
    @ApiModelProperty("绑定用户id")
    private Integer uploaderId;
    /**
    * 码率(kbps)
    */
    @ApiModelProperty("码率(kbps)")
    private Integer bitrate;
    /**
    * 
    */
    @ApiModelProperty("")
    private Date createdAt;
    /**
    * 
    */
    @ApiModelProperty("")
    private Date updatedAt;

    /**
    * 
    */
    private void setId(Integer id){
    this.id = id;
    }

    /**
    * 歌曲名称
    */
    private void setTitle(String title){
    this.title = title;
    }

    /**
    * 歌手
    */
    private void setSinger(String singer){
    this.singer = singer;
    }

    /**
    * 作词
    */
    private void setLyric(String lyric){
    this.lyric = lyric;
    }

    /**
    * 作曲
    */
    private void setComposition(String composition){
    this.composition = composition;
    }

    /**
    * 制作人
    */
    private void setProducer(String producer){
    this.producer = producer;
    }

    /**
    * 公司
    */
    private void setCompany(String company){
    this.company = company;
    }

    /**
    * 专辑
    */
    private void setAlbum(String album){
    this.album = album;
    }

    /**
    * 歌词文件路径
    */
    private void setLyricData(String lyricData){
    this.lyricData = lyricData;
    }

    /**
    * 秒数
    */
    private void setDuration(Integer duration){
    this.duration = duration;
    }

    /**
    * MinIO对象存储路径
    */
    private void setFilePath(String filePath){
    this.filePath = filePath;
    }

    /**
    * 绑定用户id
    */
    private void setUploaderId(Integer uploaderId){
    this.uploaderId = uploaderId;
    }

    /**
    * 码率(kbps)
    */
    private void setBitrate(Integer bitrate){
    this.bitrate = bitrate;
    }

    /**
    * 
    */
    private void setCreatedAt(Date createdAt){
    this.createdAt = createdAt;
    }

    /**
    * 
    */
    private void setUpdatedAt(Date updatedAt){
    this.updatedAt = updatedAt;
    }


    /**
    * 
    */
    private Integer getId(){
    return this.id;
    }

    /**
    * 歌曲名称
    */
    private String getTitle(){
    return this.title;
    }

    /**
    * 歌手
    */
    private String getSinger(){
    return this.singer;
    }

    /**
    * 作词
    */
    private String getLyric(){
    return this.lyric;
    }

    /**
    * 作曲
    */
    private String getComposition(){
    return this.composition;
    }

    /**
    * 制作人
    */
    private String getProducer(){
    return this.producer;
    }

    /**
    * 公司
    */
    private String getCompany(){
    return this.company;
    }

    /**
    * 专辑
    */
    private String getAlbum(){
    return this.album;
    }

    /**
    * 歌词文件路径
    */
    private String getLyricData(){
    return this.lyricData;
    }

    /**
    * 秒数
    */
    private Integer getDuration(){
    return this.duration;
    }

    /**
    * MinIO对象存储路径
    */
    private String getFilePath(){
    return this.filePath;
    }

    /**
    * 绑定用户id
    */
    private Integer getUploaderId(){
    return this.uploaderId;
    }

    /**
    * 码率(kbps)
    */
    private Integer getBitrate(){
    return this.bitrate;
    }

    /**
    * 
    */
    private Date getCreatedAt(){
    return this.createdAt;
    }

    /**
    * 
    */
    private Date getUpdatedAt(){
    return this.updatedAt;
    }

}
