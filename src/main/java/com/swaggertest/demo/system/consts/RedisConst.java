package com.swaggertest.demo.system.consts;

public class RedisConst {


  //session -> 登陆用户
  public static String LOGIN_SESSION_USER_KEY = "data_open::login_session_user_key::%s";

  //token 有效期
  public static Long TOKEN_EXPIRE_TIME_SECONDS = 3600L;

  //用户id与密钥对
  public static String USER_ID_KEYPAIR_CACHE_KEY = "data_open::user_id_keypair_cache_key::%s";

  //用户id与通行证
  public static String USER_ID_ACCESS_CACHE_KEY = "data_open::user_id_access_cache_key::%s";

  //token -> 用户
  public static String TOKEN_USER_INFO_CACHE_KEY = "data_open::token_user_info_cache_key::%s";

  //token -> 通行证
  public static String TOKEN_ACCESS_INFO_CACHE_KEY = "data_open::token_access_info_cache_key::%s";



}
