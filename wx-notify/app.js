var config = require('config.js')

App({

  onLaunch: function(options) {
    console.log("launch-app")
    var that = this
    wx.checkSession({
      success: function() {
        that.registerGroup(options)
      },
      fail: function() {
        //session过期，重新请求
        console.log("首次登录")
        wx.login({
          success: res => {
            wx.request({
              url: config.loginUrl + "?code=" + res.code,
              method: 'PUT',
              success: res => {
                if (res.statusCode == 200) {
                  wx.setStorageSync("sessionId", res.data)
                }
                that.registerGroup(options)
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
          },
          success: function(res) {
            console.log(res)
          }
        })
      }
    })
  },

  onShow: function() {
    console.log("show-app")
    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              // 可以将 res 发送给后台解码出 unionId
              this.globalData.userInfo = res.userInfo
              console.log(res.userInfo)
            }
          })
        } else {
          //未授权，跳转授权页面
          wx.navigateTo({
            url: '/pages/auth/auth',
          })
        }
      }
    })
  },
  globalData: {
    userInfo: null
  }
})