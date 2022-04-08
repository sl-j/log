package com.lei.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sun.applet.AppletResourceLoader;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogUserLoginVo {

    private String token;

    private UserInfoVo userInfo;
}
