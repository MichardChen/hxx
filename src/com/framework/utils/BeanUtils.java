package com.framework.utils;

import com.framework.entity.Member;
import com.framework.vo.UserVo;

/**
 * @author ChenDang
 * @date 2019/12/18 0018
 */
public class BeanUtils {

    public static Member getMemberByUserVo(UserVo userVo){
        Member member = new Member();
        if(userVo != null){
            member.setIdCardImg(userVo.getIdCardImgUrl());
            member.setIdCardReverseImg(userVo.getIdCardReverseImgUrl());
            member.setHandIdcardImg(userVo.getHandIdCardImgUrl());
            member.setBusinessImg(userVo.getBusinessImgUrl());
            member.setIcon(userVo.getHeaderImgUrl());
            member.setNickName(userVo.getNickName());
            member.setStatus(userVo.getStatus());
            member.setMemberGradeCd(userVo.getMemberGradeCd());
        }
        return member;
    }
}
