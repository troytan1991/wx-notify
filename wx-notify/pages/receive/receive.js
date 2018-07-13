var config = require('../../config.js')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    notifies: null
  },
  onContentTap: function(e) {

    wx.navigateTo({
      url: '/pages/notify/notify?notifyId=' + e.currentTarget.dataset.id,
    })
  },
  onCreateTap: function(e) {
    wx.navigateTo({
      url: '/pages/publish/publish',
    })
  },
  onDeleteTap: function(e) {
    var that = this,
      notifyId = e.currentTarget.dataset.id
    wx.showModal({
      title: '提示',
      content: '确认要删除该项吗?',
      success: res => {
        if (res.confirm) {
          config.request({
            url: config.deleteReceiveNotifyUrl + notifyId,
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
    config.request({
      url: config.getReceiveNotifiesUrl,
      success: function(res) {
        that.setData({
          notifies: res.data
        })
      }
    })
  },
})