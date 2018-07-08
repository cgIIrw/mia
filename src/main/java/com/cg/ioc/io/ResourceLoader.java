package com.cg.ioc.io;

import java.net.URL;

// io包下，首先ResourceLoader将字符串解析为URL，通过自由方法能返回带有URL数据的资源
public class ResourceLoader {
    public Resource geResource(String location) {
        URL resource = this.getClass().getClassLoader().getResource(location);
        return new UrlResource(resource);
    }
}
