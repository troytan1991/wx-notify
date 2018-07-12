var root = "https://troytan1991.club:8081/notify/rest"; //remote
// var root = "http://localhost:8080/noitfy/rest"; //local
var config = {
  //user
  loginUrl: `${root}/user/login`,
  updateUserUrl: `${root}/user`,
  registerGroupUrl: `${root}/user/group`,

  //notify
  getNotifyUrl: `${root}/notify/`,
  getSendNotifiesUrl: `${root}/notify/sendNotifies`,
  getReceiveNotifiesUrl: `${root}/notify/receiveNotifies`,
  updateNotifyUrl: `${root}/notify`,
  createNotifyUrl: `${root}/notify`,
  confirmNotifyUrl: `${root}/notify/confirm/`,
  getConfirmsUrl: `${root}/notify/confirms/`,
  getConfirmStatusUrl: `${root}/notify/confirmStatus/`,
  accessNotifyUrl: `${root}/notify/access/`,

  //comment
  getCommentsUrl: `${root}/comment/comments/`,
  createCommentUrl: `${root}/comment`,

  //封装的请求
  request: function({
    url,
    method = 'GET',
    data = {},
    success
  }) {
    //拼接请求url
    var sessionStr = sessionStr = url.indexOf("?") == -1 ? "?sessionId=" : "&sessionId="
    wx.getStorage({
      key: 'sessionId',
      success: function(res) {
        wx.request({
          url: url + sessionStr + res.data, //带有sessionId的url
          method: method,
          data: data,
          success: function(res) {
            var msg = ""
            if (res.statusCode == 401) {
              //授权失败，则重新登录
              console.log("重新登录1")
              wx.login({
                success: res => {
                  wx.request({
                    url: config.loginUrl + "?code=" + res.code,
                    method: 'PUT',
                    success: res => {
                      if (res.statusCode == 200) {
                        wx.setStorageSync("sessionId", res.data)
                      }
                    }
                  })
                },
                fail: function() {
                  wx.showToast({
                    title: '登录失败',
                    icon: 'none'
                  })
                }
              })
            } else if (res.statusCode == 500) {
              msg = "服务器内部错误"
            } else {
              success(res)
              return
            }
            wx.showToast({
              title: msg,
              icon: 'none'
            })
          },
          fail: function() {
            wx.showToast({
              title: '请求失败',
              icon: 'none'
            })
          }
        })
      },
      fail: function() {
        //获取失败，则重新登录
        console.log("重新登录2")
        wx.login({
          success: res => {
            wx.request({
              url: config.loginUrl + "?code=" + res.code,
              method: 'PUT',
              success: res => {
                if (res.statusCode == 200) {
                  wx.setStorageSync("sessionId", res.data)
                }
              }
            })
          },
          fail: function() {
            wx.showToast({
              title: '登录失败',
              icon: 'none'
            })
          }
        })
      }
    })
  },

  login: function() {
    wx.login({
      success: res => {
        wx.request({
          url: config.loginUrl + "?code=" + res.code,
          method: 'PUT',
          success: res => {
            if (res.statusCode == 200) {
              wx.setStorageSync("sessionId", res.data)
            }
          }
        })
      },
      fail: function() {
        wx.showToast({
          title: '登录失败',
          icon: 'none'
        })
      }
    })
  }
};
module.exports = config