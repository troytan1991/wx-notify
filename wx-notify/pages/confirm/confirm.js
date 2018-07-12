// pages/confirm/confirm.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    confirms: [{
      confirmerId: 1,
      nickname: 'troytan',
      datetime: '2018-07-22 07:00',
      avatarUrl: "https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKNWWKzibkQWlmvhG6gKKGCWxVjXIAyCq2XWq1r21HjTrU6T8k7jcibQzBgD4y8u4vCR2jhDiccEll5g/0"
    }, {
      confirmerId: 2,
      nickname: 'troytan',
      datetime: '2018-07-22 07:00',
      avatarUrl: "https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKNWWKzibkQWlmvhG6gKKGCWxVjXIAyCq2XWq1r21HjTrU6T8k7jcibQzBgD4y8u4vCR2jhDiccEll5g/0"
    }, {
      confirmerId: 3,
      nickname: 'troytan',
      datetime: '2018-07-22 07:00',
      avatarUrl: "https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKNWWKzibkQWlmvhG6gKKGCWxVjXIAyCq2XWq1r21HjTrU6T8k7jcibQzBgD4y8u4vCR2jhDiccEll5g/0"
    }, {
      confirmerId: 4,
      nickname: 'troytan',
      datetime: '2018-07-22 07:00',
      avatarUrl: "https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKNWWKzibkQWlmvhG6gKKGCWxVjXIAyCq2XWq1r21HjTrU6T8k7jcibQzBgD4y8u4vCR2jhDiccEll5g/0"
    }]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var confirms = JSON.parse(options.confirms)
    this.setData({
      confirms: confirms
    })
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