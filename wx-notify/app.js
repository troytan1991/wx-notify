var config = require('config.js')

App({

  onLaunch: function(options) {
    var that = this
    wx.checkSession({
      fail: function() {
        //session过期，重新请求
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
  onShow: function(options) {
    this.globalData.options = options
  },
  registerGroup: function(options) {
    if (!options.shareTicket) {
      //非转发进入,不执行关联群组操作
      return
    }
    //获取通知Id
    var notifyId = options.query.notifyId
    wx.getShareInfo({
      shareTicket: options.shareTicket,
      success: res => {
        config.request({
          url: config.registerGroupUrl,
          method: 'POST',
          data: {
            "notifyId": notifyId,
            "encryptedData": res.encryptedData,
            "iv": res.iv
          }
        })
      }
    })
  },
  globalData: {
    userInfo: null,
    options: null
  }
})