var config = require('../../config.js')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    groupUsers: [{
      groupId: 'tGhL3b4jeAzJabqJDbJZY4KgWvch8',
      nickname: 'troytan'
    }, {
      groupId: 'tGhL3b4jeAzJabqJDbJZY4KgWvch8',
      nickname: 'troytan1'
    }]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that = this
    config.request({
      url: config.getGroupUsersUrl,
      success: function(res) {
        that.setData({
          groupUsers: res.data
        })
      }
    })
  },
  onNameInput:function(e){
    var index = e.currentTarget.dataset.id,groupUsers=this.data.groupUsers
    groupUsers[index].nickname = e.detail.value
    this.setData({
        groupUsers: groupUsers
    })
  },
  onDeleteTap: function(e) {
    var that = this
    wx.showModal({
      title: '提示',
      content: '确认要删除吗？',
      success: res => {
        if (res.confirm) {
          config.request({
            url: config.deleteGroupUserUrl,
            method: 'DELETE',
            data: e.currentTarget.dataset.item,
            success: function(res) {
              wx.showToast({
                title: '成功删除',
              })
              that.setData({
                groupUsers: res.data
              })
            }
          })
        }
      }
    })
  },
  onConfirmTap: function(e) {
    var that = this
    config.request({
      url: config.updateGroupUsersUrl,
      method: 'POST',
      data: this.data.groupUsers,
      success: function(){
        wx.showModal({
          title: '提示',
          content: '更新成功',
          showCancel:false,
          success:function(res){
            wx.navigateBack({
              
            })
          }
        })
      
      }
    })
  },
  onCancelTap: function() {
    wx.showModal({
      title: '提示',
      content: '未保存，确认返回吗?',
      confirmText: '返回',
      success: res => {
        if (res.confirm) {
          wx.navigateBack({

          })
        }
      }
    })
  }
})