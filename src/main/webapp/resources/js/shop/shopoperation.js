$(function () {
    //获取店铺出初始信息
    var shopId = getQueryString("shopId");
    var isEdit = !!shopId;
    var initUrl = '/o2o/shop/getshopinitinfo';
    var registerShopUrl = '/o2o/shop/registershop';
    var shopInfoUrl = '/o2o/shop/getshopinfo?shopId=' + shopId;
    var editUrl = '/o2o/shop/modifyshop';
    if (isEdit) {
        getShopInfo();
    } else {
        getShopInitInfo();
    }

    function getShopInitInfo() {
        $.getJSON(initUrl, function (data) {
            if (data.success) {
                var tempHtml = '';
                var tempAreaHtml = '';
                data.shopCategoryList.map(function (item, index) {
                    tempHtml += '<option data-id="' + item.shopCategoryId + '">' + item.shopCategoryName + '</option>'
                });
                data.areaList.map(function (item, index) {
                    tempAreaHtml += '<option data-area="' + item.areaId + '">' + item.areaName + '</option>'
                });
                $('#shop-category').html(tempHtml);
                $('#area').html(tempAreaHtml);
            }
        });
    }

    function getShopInfo() {
        $.getJSON(shopInfoUrl, function (data) {
            if (data.success) {
                var shop = data.shop;
                $('#shop-name').val(shop.shopName);
                $('#shop-phone').val(shop.phone);
                $('#shop-desc').val(shop.shopDesc);
                $('#shop-addr').val(shop.shopAddr)
                var tempHtml = '';
                var tempAreaHtml = '';
                data.CategoryList.map(function (item, index) {
                    tempHtml += '<option data-id="' + item.shopCategoryId + '">' + item.shopCategoryName + '</option>'
                });
                data.areaList.map(function (item, index) {
                    tempAreaHtml += '<option data-area="' + item.areaId + '">' + item.areaName + '</option>'
                });
                $('#shop-category').html(tempHtml);
                $('#area').html(tempAreaHtml);
            }
        });
    }

    $('#submit').click(function () {
        var shop = {};
        shop.shopName = $('#shop-name').val();
        shop.shopAddr = $('#shop-addr').val();
        shop.phone = $('#shop-phone').val();
        shop.shopDesc = $('#shop-desc').val();
        shop.shopCategory = {
            shopCategoryId: $('#shop-category').find('option').not(function () {
                return !this.selected;
            }).data('id')
        };
        shop.area = {
            areaId: $('#area').find('option').not(function () {
                return !this.selected;
            }).data('area')
        };
        var shopImg = $('#shop-img')[0].files[0];
        var formData = new FormData();
        formData.append('shopImg', shopImg);
        formData.append('shopStr', JSON.stringify(shop));
        var verifyCodeActual = $('#j_captcha').val();
        if (!verifyCodeActual) {
            $.toast("请输入验证码");
            return;
        }
        formData.append('verifyCodeActual', verifyCodeActual);
        $.ajax({
            url: (isEdit ? editUrl : registerShopUrl),
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            cache: false,
            success: function (data) {
                if (data.success) {
                    $.toast('提交成功');
                } else {
                    $.toast('提交失败' + data.errMsg);
                }
                $('#captcha_img').click();
            }
        })
    })
})