var config = require('../../config.js')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    notifies: []
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
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {
    var that = this
    console.log("onShow")
    config.request({
      url: config.getReceiveNotifiesUrl,
      success: function(res) {
        that.setData({
          notifies: res.data
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})