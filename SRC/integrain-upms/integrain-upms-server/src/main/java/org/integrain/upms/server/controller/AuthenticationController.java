package org.integrain.upms.server.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

import org.integrain.upms.server.bean.AuthenticationUser;
import org.integrain.upms.server.bean.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public void login(@RequestHeader HttpHeaders httpHeaders,HttpServletResponse response) {
		Object result = null;
        try {
            User userWeb = this.obtainUserFormHeader(httpHeaders);
            //当没有 传递 参数的情况
            if(userWeb == null){
                result = new ResponseEntity<AuthenticationUser>(HttpStatus.NOT_FOUND);
            }

            //数据库查找
            /*User dbUser = this.userService.login(userWeb.getUsername());

            if (dbUser != null) {
                if (!dbUser.getPassword().equals(userWeb.password)) {
                    //密码不匹配
                    result = new ResponseEntity<AuthUser>(HttpStatus.BAD_REQUEST);
                }else if (dbUser.isDisable()) {
                    //禁用 403
                    result = new ResponseEntity<AuthUser>(HttpStatus.FORBIDDEN);
                }else if (dbUser.isLocked()) {
                    //锁定 423
                    result = new ResponseEntity<AuthUser>(HttpStatus.LOCKED);
                }else if (dbUser.isExpired()) {
                    //过期 428
                    result = new ResponseEntity<AuthUser>(HttpStatus.PRECONDITION_REQUIRED);
                }else{
                    //正常的数据
                    //直接返回User数据
                    AuthUser AuthUser = new AuthUser();
                    AuthUser.setUsername(dbUser.getUsername());

                    result = AuthUser;
                }
            } else {
                //不存在 404
                result = new ResponseEntity<AuthUser>(HttpStatus.NOT_FOUND);
            }*/
            
            AuthenticationUser AuthUser = new AuthenticationUser();
            AuthUser.setUsername(userWeb.getUserId());
            result = AuthUser;
            
        } catch (UnsupportedEncodingException e) {
            result = new ResponseEntity<AuthenticationUser>(HttpStatus.BAD_REQUEST);
        }

        //将数据输出到客户端
        this.writeJSON(response, result);
	}
	
	/**
     * 将授权加密的信息返回
     * 根据请求头获取用户名及密码
     *
     * @param httpHeaders
     * @return
     * @throws UnsupportedEncodingException
     */
    private User obtainUserFormHeader(HttpHeaders httpHeaders) throws UnsupportedEncodingException {
        /**
         *
         * This allows the CAS server to reach to a remote REST endpoint via a POST for verification of credentials.
         * Credentials are passed via an Authorization header whose value is Basic XYZ where XYZ is a Base64 encoded version of the credentials.
         */
        //根据官方文档，当请求过来时，会通过把用户信息放在请求头authorization中，并且通过Basic认证方式加密
        String authorization = httpHeaders.getFirst("authorization");//将得到 Basic Base64(用户名:密码)
        if(StringUtils.isEmpty(authorization)){
            return null;
        }

        String baseCredentials = authorization.split(" ")[1];
        String usernamePassword = new String(Base64Utils.decodeFromString(baseCredentials), "UTF-8");//用户名:密码
        String credentials[] = usernamePassword.split(":");
        System.out.println("httpHeaders="+httpHeaders);
        System.out.println("username="+credentials[0]);
        System.out.println("password="+credentials[1]);
        return new User(credentials[0], credentials[1]);
    }
    
    /**
     * 在SpringMvc中获取到Session
     * @return
     */
    @SuppressWarnings("rawtypes")
    public void writeJSON(HttpServletResponse response,Object object){
        try {
            //设定编码 
            response.setCharacterEncoding("UTF-8");
            //表示是json类型的数据
            response.setContentType("application/json");
            //获取PrintWriter 往浏览器端写数据
            PrintWriter writer = response.getWriter();

            //当是不正常的数据的时候，设定状态
            if(object instanceof ResponseEntity) {
                HttpStatus status = ((ResponseEntity) object).getStatusCode();
                //设定状态码表
                response.setStatus(status.value());
            }

            ObjectMapper mapper = new ObjectMapper(); //转换器
            //获取到转化后的JSON 数据
            String json = mapper.writeValueAsString(object);
            //写数据到浏览器
            writer.write(json);
            //刷新，表示全部写完，把缓存数据都刷出去
            writer.flush();

            //关闭writer
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
}
