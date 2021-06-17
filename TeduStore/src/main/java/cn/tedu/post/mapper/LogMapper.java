package cn.tedu.post.mapper;

import cn.tedu.post.bean.Log;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogMapper {
	
	List<Log> getLogByUserId(
            @Param("userId") Integer userId,
            @Param("month") Integer month);

}
