package com.company.project.core;

/**
 * 项目常量
 */
public final class ProjectConstant {
    public static final String BASE_PACKAGE = "com.company.project";//生成代码所在的基础包名称，可根据自己公司的项目修改（注意：这个配置修改之后需要手工修改src目录项目默认的包路径，使其保持一致，不然会找不到类）
    public static final String MODULE = ".module";
    //   public static final String MODULE_PACKAGE = MODULE + ".*";
    public static final String MODULE_PACKAGE = MODULE + ".mall";
    public static final String MODEL_PACKAGE = BASE_PACKAGE + MODULE_PACKAGE + ".model";//生成的Model所在包
    public static final String MAPPER_PACKAGE = BASE_PACKAGE + MODULE_PACKAGE + ".dao";//生成的Mapper所在包
    public static final String MAPPER_PACKAGE_SYS = BASE_PACKAGE + MODULE + ".sys.dao";//从数据库Mapper所在包
    public static final String SERVICE_PACKAGE = BASE_PACKAGE + MODULE_PACKAGE + ".service";//生成的Service所在包
    public static final String SERVICE_IMPL_PACKAGE = SERVICE_PACKAGE + ".impl";//生成的ServiceImpl所在包
    public static final String CONTROLLER_PACKAGE = BASE_PACKAGE + MODULE_PACKAGE + ".web";//生成的Controller所在包
    public static final String MAPPER_INTERFACE_REFERENCE = BASE_PACKAGE + ".core.Mapper";//Mapper插件基础接口的完全限定名

    public static final String BASEPACKAGES = "com.company.project.module.sys.dao"
            + ",com.company.project.module.job.dao"
            + ",com.company.project.module.data.dao"
            + ",com.company.project.module.home.dao"
            + ",com.company.project.module.mq.dao";
    //public static final String[] BASEPACKAGES = {"com.company.project.module.sys.dao","com.company.project.module.job.dao", "com.company.project.module.data.dao", "com.company.project.module.home.dao", "com.company.project.module.mq.dao"};

    public static final String XLSX_SUFFIX = ".xlsx";

    public static final String XLSX_CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    public static final String MEIZU_WEATHER_URL = "http://aider.meizu.com/app/weather/listWeather";

    public static final String MRYW_TODAY_URL = "https://interface.meiriyiwen.com/article/today";

    public static final String MRYW_DAY_URL = "https://interface.meiriyiwen.com/article/day";

    public static final String TIME_MOVIE_HOT_URL = "https://api-m.mtime.cn/Showtime/LocationMovies.api";

    public static final String TIME_MOVIE_DETAIL_URL = "https://ticket-api-m.mtime.cn/movie/detail.api";

    public static final String TIME_MOVIE_COMING_URL = "https://api-m.mtime.cn/Movie/MovieComingNew.api";

    public static final String TIME_MOVIE_COMMENTS_URL = "https://ticket-api-m.mtime.cn/movie/hotComment.api";

    public static final String ONE_ID_LIST_URL = "http://v3.wufazhuce.com:8000/api/onelist/idlist/";

    public static final String ONE_LIST_URL = "http://v3.wufazhuce.com:8000/api/onelist/";

    public static final String ONE_ESSAY_URL = "http://v3.wufazhuce.com:8000/api/essay/";

    public static final String ONE_ESSAY_COMMENT_URL = "http://v3.wufazhuce.com:8000/api/comment/praiseandtime/essay/";
    /**
     * 存储当前登录用户id的字段名
     */
    public static final String CURRENT_USER_ID = "CURRENT_USER_ID";

    /**
     * token有效期（小时）
     */
    public static final int TOKEN_EXPIRES_HOUR = 72;

    /**
     * 存放Authorization的header字段
     */
    public static final String AUTHORIZATION = "authorization";



}
