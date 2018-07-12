var config = require("../../config.js")
Page({

  /**
   * 页面的初始数据
   */
  data: {
    notifies: []
  },
  onContentTap: function(e) {
    console.log(e)
    var notifyId = e.currentTarget.dataset.id
    wx.navigateTo({
      url: '/pages/notify/notify?notifyId=' + notifyId,
    })
  },
  onCreateTap: function(e) {
    wx.navigateTo({
      url: '/pages/publish/publish',
    })
  },
  onEditTap: function(e) {
    var notify = e.currentTarget.dataset.item
    wx.navigateTo({
      url: '/pages/publish/publish?notify=' + JSON.stringify(notify),
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {
    var that = this
    setTimeout(function() {
      config.request({
        url: config.getSendNotifiesUrl,
        success: function(res) {
          console.log(res)
          that.setData({
            notifies: res.data
          })
        }
      })
    }, 1000)
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})