package com.framework.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author ChenDang
 * @date 2019/12/18 0018
 */
@Data
@Setter
@Getter
public class UserVo {

    private String idCardImgUrl;
    private String idCardReverseImgUrl;
    private String handIdCardImgUrl;
    private String businessImgUrl;
    private String headerImgUrl;
    private String nickName;
    private String status;
    private String memberGradeCd;

}
