const userMessage = {
  state: {
    //用户ID
    userId: ''
  },
  getters: {
    userId: state => state.userId
  },
  mutations: {
    SET_USERID: (state, userId) => {
      state.userId = userId;
    }
  }
};

export default userMessage;
