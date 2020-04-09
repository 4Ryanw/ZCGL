package cn.cqu.pojo;

import java.io.Serializable;

/**
 * 设备品牌实体类
 */
public class DeviceBrand implements Serializable {
    private String brandId; //品牌id
    private String brand; //品牌名称

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
