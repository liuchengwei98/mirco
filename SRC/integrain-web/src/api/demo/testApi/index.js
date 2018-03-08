import interaction from '../../../common/interaction';

export function getServiceApi(params) {
  return interaction({
    url: 'http://10.200.64.9:7779/client/test',
    method: 'post',
    params: params
  });
}
//获取用户列表
export function getUserList(params) {
  return interaction({
    //url: 'http://10.24.18.194:8762/api/mastermicr/user/getuserlist',
    //url: 'http://10.200.64.13:8762/api/mastermicr/users/getuserslistaudit',
    //url: 'http://10.200.64.51:7950/user/getuserlist',
    // url: 'http://10.200.64.13:8762/api/mastermicr/user/getuserlist',
    url: 'http://10.24.18.194:8762/api/mastermicr/user/getuserlist',
    method: 'get',
    params: params
  });
}

//获取用户列表
export function getUserInfo(params) {
  return interaction({
    //url: 'http://10.24.18.194:8762/api/mastermicr/user/getuserlist',
    //url: 'http://10.200.64.13:8762/api/mastermicr/users/getuserslistaudit',
    //url: 'http://10.200.64.51:7950/user/getuserlist',
    // url: 'http://10.200.64.13:8762/api/mastermicr/user/getuserlist',
    url: 'http://10.200.64.80:8762/api/user/user/get/1',
    method: 'get',
    params: params
  });
}

//获取用户详情
export function getUsers(userId) {
  return interaction({
    url: 'http://10.200.64.51:7950/user/getusers/' + userId,
    method: 'get'
  });
}
