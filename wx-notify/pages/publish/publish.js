var config = require('../../config.js')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    title: '',
    content: '',
    nickName: '',
    isEdit: false
  },
  onTitleInput: function(e) {
    this.setData({
      title: e.detail.value
    })
  },
  onContentInput: function(e) {
    this.setData({
      content: e.detail.value
    })
  },
  onNameInput: function(e) {
    this.setData({
      nickName: e.detail.value
    })
  },
  onCancelTap: function() {
    wx.navigateBack({

    })
  },
  onConfirmTap: function() {
    if (!this.validateNotify()) {
      return
    }
    config.request({
      url: config.updateNotifyUrl,
      method: 'POST',
      data: {
        title: this.data.title,
        content: this.data.content,
        name: this.data.nickName,
        status: 1,
        notifyId: this.data.notifyId
      },
      success: function() {
        wx.showToast({
          title: '修改成功',
          success: function() {
            wx.navigateBack({})
          }
        })
      }
    })
  },
  onPublishTap: function() {
    if (!this.validateNotify()) {
      return
    }
    config.request({
      url: config.createNotifyUrl,
      method: 'PUT',
      data: {
        title: this.data.title,
        content: this.data.content,
        name: this.data.nickName,
        status: 1
      },
      success: function(res) {
        wx.showModal({
          title: '发布成功',
          showCancel: false,
          confirmText: '进入查看',
          success: function() {
            wx.redirectTo({
              url: '/pages/notify/notify?notifyId=' + res.data.notifyId,
            })
          }

        })
      }
    })
  },
  validateNotify: function() {
    var data = this.data,
      flag = true,
      errMsg = ''
    if (data.title.length == 0) {
      errMsg = '标题不能为空'
      flag = false
    }
    if (data.content.length == 0) {
      errMsg = '内容不能为空'
      flag = false
    }
    if (data.nickName.length == 0) {
      errMsg = "署名不能为空"
      flag = false
    }
    if (!flag) {
      wx.showToast({
        title: errMsg,
        icon: 'none'
      })
    }
    return flag;
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    if (options.notify) {
      //编辑
      var notify = JSON.parse(options.notify)
      this.setData({
        isEdit: true,
        title: notify.title,
        content: notify.content,
        nickName: notify.nickName,
        notifyId: notify.notifyId
      })
    } else {
      this.setData({
        nickName: getApp().globalData.userInfo.nickName
      })
    }
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
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})