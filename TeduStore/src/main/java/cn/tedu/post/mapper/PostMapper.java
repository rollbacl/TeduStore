package cn.tedu.post.mapper;

import cn.tedu.post.bean.Post;
import org.apache.ibatis.annotations.Param;

public interface PostMapper {
	
	Post getPostById(@Param("id") Integer id);

}
