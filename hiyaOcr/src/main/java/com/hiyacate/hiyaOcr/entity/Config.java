package com.hiyacate.hiyaOcr.entity;

import lombok.Data;

/**
 * @author hiya
 * @description
 * @date 2023-01-09 22:17
 */
@Data
public class Config {

    private String accessKeyId;

    private String accessKeySercret;

    private String type;

    private String regionId;

    private String endpoint;
}
