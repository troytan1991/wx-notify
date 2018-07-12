var config = require('../../config.js')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    notify: {},
    notifyId: null,
    confirmStatus: null,
    confirms: [],
    comments: []
  },
  onHomeTap: function() {
    wx.switchTab({
      url: '/pages/send/send',
    })
  },
  onConfirmTap: function(e) {
    var that = this,
      notify = this.data.notify
    config.request({
      url: config.confirmNotifyUrl + notify.notifyId,
      method: 'POST',
      success: function() {
        that.setData({
          confirmStatus: 2
        })
        that.refreshConfirms()
      }
    })
  },
  onInfoTap: function(e) {
    var confirms = this.data.confirms
    wx.navigateTo({
      url: '/pages/confirm/confirm?confirms=' + JSON.stringify(confirms),
    })
  },
  onCommentTap: function(e) {
    wx.navigateTo({
      url: '/pages/comment/comment?notifyId=' + this.data.notify.notifyId,
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
    var that = this,
      notifyId = options.notifyId
    this.setData({
      notifyId: notifyId
    })
    //获取通知详情
    config.request({
      url: config.getNotifyUrl + notifyId,
      success: function(res) {
        if (res.statusCode == 402) {
          wx.showModal({
            title: '无权限访问',
            content: '您可能不是所通知的群内成员',
            confirmText: "返回主页",
            success: function() {
              wx.switchTab({
                url: '/pages/send/send',
              })
            }
          })
        } else {
          that.setData({
            notify: res.data
          })
          //设置分享到群
          wx.showShareMenu({
            withShareTicket: true
          })
          //接收通知
          config.request({
            url: config.accessNotifyUrl + notifyId,
            method: 'PUT',
            success: function() {
              //获取确认状态
              config.request({
                url: config.getConfirmStatusUrl + notifyId,
                success: function(res) {
                  that.setData({
                    confirmStatus: res.data
                  })
                }
              })
            }
          })
          //刷新确认列表
          that.refreshConfirms()
        }
      }
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
    var that = this
    //获取留言列表
    config.request({
      url: config.getCommentsUrl + this.data.notifyId,
      success: function(res) {
        that.setData({
          comments: res.data
        })
      }
    })
  },
  checkAuth: function(notifyId) {
    //获取通知详情
    config.request({
      url: config.getNotifyUrl + notifyId,
      success: function(res) {
        if (res.statusCode == 402) {
          wx.showModal({
            title: '无权限访问',
            content: '您可能不是所通知的群内成员',
            confirmText: "返回主页",
            success: function() {
              wx.switchTab({
                url: '/pages/send/send',
              })
            }
          })
        } else {
          return res.data
        }
      }
    })
  },
  refreshConfirms: function(notifyId) {
    var that = this
    //获取确认列表
    config.request({
      url: config.getConfirmsUrl + this.data.notifyId,
      success: function(res) {
        that.setData({
          confirms: res.data
        })
      }
    })

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {
    return {
      title: "周末露营活动",
      path: '/pages/notify/notify?notifyId=' + this.data.notifyId
    }
  }
})