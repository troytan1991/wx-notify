var config = require("../../config.js")
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },
  onGotUserInfo: function(e) {
    var userInfo = e.detail.userInfo
    if (e.detail.errMsg === "getUserInfo:ok") {
      //记录用户数据，返回上层页面
      getApp().globalData.userInfo = userInfo
      config.request({
        url: config.updateUserUrl,
        method: 'POST',
        data: userInfo,
        success: function() {
          wx.navigateBack({})
        }
      })
    } else {
      //跳转失败页面，重新授权
      wx.navigateTo({
        url: '/pages/authFail/authFail',
      })
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    wx.getSystemInfo({
      success: function(res) {
        console.log(res)
      },
    })
  },

})