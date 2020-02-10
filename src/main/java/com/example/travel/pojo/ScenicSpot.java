package com.example.travel.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "t_scenic_spot")
public class ScenicSpot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "scenic_spot_name")
    private String scenicSpotName;

    @Column(name = "scenic_spot_address")
    private String scenicSpotAddress;

    /**
     * 简介
     */
    @Column(name = "scenic_spot_describe")
    private String scenicSpotDescribe;

    /**
     * 详细描述
     */
    @Column(name = "scenic_spot_synopsis")
    private String scenicSpotSynopsis;

    /**
     * 全景图
     */
    private String img;

    /**
     * 图片组
     */
    private String imgs;

    /**
     * 经度
     */
    @Column(name = "s_longitude")
    private BigDecimal sLongitude;

    /**
     * 纬度
     */
    @Column(name = "s_dimension")
    private BigDecimal sDimension;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return scenic_spot_name
     */
    public String getScenicSpotName() {
        return scenicSpotName;
    }

    /**
     * @param scenicSpotName
     */
    public void setScenicSpotName(String scenicSpotName) {
        this.scenicSpotName = scenicSpotName == null ? null : scenicSpotName.trim();
    }

    /**
     * @return scenic_spot_address
     */
    public String getScenicSpotAddress() {
        return scenicSpotAddress;
    }

    /**
     * @param scenicSpotAddress
     */
    public void setScenicSpotAddress(String scenicSpotAddress) {
        this.scenicSpotAddress = scenicSpotAddress == null ? null : scenicSpotAddress.trim();
    }

    /**
     * 获取简介
     *
     * @return scenic_spot_describe - 简介
     */
    public String getScenicSpotDescribe() {
        return scenicSpotDescribe;
    }

    /**
     * 设置简介
     *
     * @param scenicSpotDescribe 简介
     */
    public void setScenicSpotDescribe(String scenicSpotDescribe) {
        this.scenicSpotDescribe = scenicSpotDescribe == null ? null : scenicSpotDescribe.trim();
    }

    /**
     * 获取详细描述
     *
     * @return scenic_spot_synopsis - 详细描述
     */
    public String getScenicSpotSynopsis() {
        return scenicSpotSynopsis;
    }

    /**
     * 设置详细描述
     *
     * @param scenicSpotSynopsis 详细描述
     */
    public void setScenicSpotSynopsis(String scenicSpotSynopsis) {
        this.scenicSpotSynopsis = scenicSpotSynopsis == null ? null : scenicSpotSynopsis.trim();
    }

    /**
     * 获取全景图
     *
     * @return img - 全景图
     */
    public String getImg() {
        return img;
    }

    /**
     * 设置全景图
     *
     * @param img 全景图
     */
    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    /**
     * 获取图片组
     *
     * @return imgs - 图片组
     */
    public String getImgs() {
        return imgs;
    }

    /**
     * 设置图片组
     *
     * @param imgs 图片组
     */
    public void setImgs(String imgs) {
        this.imgs = imgs == null ? null : imgs.trim();
    }

    /**
     * 获取经度
     *
     * @return s_longitude - 经度
     */
    public BigDecimal getsLongitude() {
        return sLongitude;
    }

    /**
     * 设置经度
     *
     * @param sLongitude 经度
     */
    public void setsLongitude(BigDecimal sLongitude) {
        this.sLongitude = sLongitude;
    }

    /**
     * 获取纬度
     *
     * @return s_dimension - 纬度
     */
    public BigDecimal getsDimension() {
        return sDimension;
    }

    /**
     * 设置纬度
     *
     * @param sDimension 纬度
     */
    public void setsDimension(BigDecimal sDimension) {
        this.sDimension = sDimension;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}