import axios from 'axios';
import 'babel-polyfill';
import iview from 'iview';

// 创建axios实例
const service = axios.create({
  // baseURL: process.env.BASE_API, // api的base_url
  //maxRedirects: 0,
  //withCredentials: true,
  withCredentials: true,
  timeout: 5000 // 请求超时时间
});

axios.defaults.withCredentials = true;

// request拦截器
service.interceptors.request.use(config => {
  console.log('document.cookie:', document.cookie);
  console.log('document.cookie.length:', document.cookie.length);
  return config;
}, error => {
  console.log(error); // for debug
  return Promise.reject(error);
});

// respone拦截器
service.interceptors.response.use(
  response => {

    if (!response.data.operateResult) {
      // alert(window.location.href);
      // alert(document.location);
      // alert(document.location.host);
      let responseURL = response.request.responseURL;
      let strStart = responseURL.indexOf('?service=');
      let serverUrl = document.location.host.replace(':', '%3A');
      let loginUrl = responseURL.substring(0, strStart) + '?service=http%3A%2F%2F' + serverUrl;
      window.location.href = loginUrl;
    }
    return response;
  },
  error => {
    console.log('err' + error); // for debug
    // Message({
    //   message: error.message,
    //   type: 'error',
    //   duration: 5 * 1000
    // });
    // return Promise.reject(error);
    return Promise.resolve(error.response)
  }
);

export default service;
