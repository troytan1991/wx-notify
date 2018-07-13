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
    comments: null,
    scrollHeight: 560
  },
  /**
   * 点击home回到主页
   */
  onHomeTap: function() {
    wx.switchTab({
      url: '/pages/send/send',
    })
  },
  /**
   * 确认通知事件
   */
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
  /**
   * 确认信息栏事件
   */
  onInfoTap: function(e) {
    var confirms = this.data.confirms
    wx.navigateTo({
      url: '/pages/confirm/confirm?confirms=' + JSON.stringify(confirms),
    })
  },
  /**
   * 编辑留言
   */
  onCommentTap: function(e) {
    wx.navigateTo({
      url: '/pages/comment/comment?notifyId=' + this.data.notify.notifyId,
    })
  },
  /**
   * 创建通知
   */
  onCreateTap: function(e) {
    wx.navigateTo({
      url: '/pages/publish/publish',
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that = this
    //获取通知id
    this.setData({
      notifyId: options.notifyId
    })
    // 设置分享到群
    wx.showShareMenu({
      withShareTicket: true
    })
    wx.getSystemInfo({
      success: function(res) {
        that.setData({
          scrollHeight: res.windowHeight - res.windowWidth / 750 * 90
        })
      },
    })
  },
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {
    //入口2:通知页，添加授权校验
    var that = this
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              // 可以将 res 发送给后台解码出 unionId
              getApp().globalData.userInfo = res.userInfo
              that.registerGroup()
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
  refreshData: function() {
    var that = this,
      notifyId = this.data.notifyId
    //接收通知
    config.request({
      url: config.accessNotifyUrl + notifyId,
      method: 'PUT',
      success: function(res) {
        if (!res.data) {
          //无权限访问
          wx.showModal({
            title: '无权限访问',
            content: '您可能不是所通知的群内成员',
            showCancel: false,
            confirmText: "返回主页",
            success: function() {
              wx.switchTab({
                url: '/pages/send/send',
              })
            }
          })
        } else {
          //获取通知详情
          config.request({
            url: config.getNotifyUrl + notifyId,
            success: function(res) {
              if (res.statusCode == 200) {
                //刷新通知数据
                that.setData({
                  notify: res.data
                })
                //获取确认状态
                config.request({
                  url: config.getConfirmStatusUrl + notifyId,
                  success: function(res) {
                    that.setData({
                      confirmStatus: res.data
                    })
                  }
                })
                //刷新确认列表
                that.refreshConfirms()
                that.refreshComments()
              }
            }
          })
        }
      }
    })

  },
  //关联群组信息
  registerGroup: function() {
    var options = getApp().globalData.options,
      that = this
    if (!options.shareTicket) {
      //非转发进入,不执行关联群组操作
      that.refreshData()
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
            that.refreshData()
          }
        })
      }
    })
  },
  /**
   * 获取确认列表
   */
  refreshConfirms: function() {
    var that = this
    config.request({
      url: config.getConfirmsUrl + this.data.notifyId,
      success: function(res) {
        that.setData({
          confirms: res.data
        })
      }
    })
  },
  refreshComments: function() {
    var that = this
    config.request({
      url: config.getCommentsUrl + this.data.notifyId,
      success: function(res) {
        that.setData({
          comments: res.data
        })
      }
    })
  },
  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {
    return {
      title: "群消息通知",
      path: '/pages/notify/notify?notifyId=' + this.data.notifyId
    }
  }
})