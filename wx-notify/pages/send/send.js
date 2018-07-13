var config = require("../../config.js")
Page({

  /**
   * 页面的初始数据
   */
  data: {
    notifies: null
  },
  /**
   * 点击通知item
   */
  onContentTap: function(e) {
    var notifyId = e.currentTarget.dataset.id
    wx.navigateTo({
      url: '/pages/notify/notify?notifyId=' + notifyId,
    })
  },
  /**
   * 发布按钮
   */
  onCreateTap: function(e) {
    wx.navigateTo({
      url: '/pages/publish/publish',
    })
  },
  //编辑按钮
  onEditTap: function(e) {
    var notify = e.currentTarget.dataset.item
    wx.navigateTo({
      url: '/pages/publish/publish?notify=' + JSON.stringify(notify),
    })
  },
  onDeleteTap: function(e) {
    var notifyId = e.currentTarget.dataset.id,
      that = this
    wx.showModal({
      title: '提示',
      content: '确定要删除该项吗?',
      success: res => {
        if (res.confirm) {
          config.request({
            url: config.deleteSendNotifyUrl + notifyId,
            method: 'DELETE',
            success: res => {
              wx.showToast({
                title: '成功删除',
              })
              that.setData({
                notifies: res.data
              })
            }
          })
        }
      }
    })
  },
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {
    var that = this
    //入口1:主页，添加授权校验
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              getApp().globalData.userInfo = res.userInfo
            }
          })
          //请求数据
          config.request({
            url: config.getSendNotifiesUrl,
            success: function(res) {
              that.setData({
                notifies: res.data
              })
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
})