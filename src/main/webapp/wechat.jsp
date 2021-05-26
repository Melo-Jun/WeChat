<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="host" value="localhost:8088/melo"/>
<c:set var="userId" value="${sessionScope.login.id}"/>
<html >
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>网页版微信</title>

    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
    <link rel="stylesheet" href="css/reset.min.css">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="static/js/jquery-3.4.1.min.js" ></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="static/js/bootstrap.min.js" ></script>


</head>
<body>

<!-- 弹框部分(默认隐藏起来) -->
<div id="simpleModal" class="modal">
    <%--    <div id="myInform" style="display: none">--%>
    <%--        <div class="main">--%>
    <%--            <span class="icon" onclick="hide('myInform')"></span>--%>
    <%--            <div class="avatar"  style="background: url('img/thomas.jpg');background-size: cover ">--%>
    <%--            </div>--%>
    <%--            <form id="updateUserForm" method="post" enctype="multipart/form-data">--%>
    <%--                <div class="account">--%>
    <%--                    <div style="text-decoration: underline;margin-bottom: 5px;">修改头像</div>--%>
    <%--                    <input oninput="uploadPhoto('updatePhoto','upload?method=updateAvatar&path=TempUpload')" type="file" name="photo" id="updatePhoto" style="margin-left: 5px" />--%>
    <%--                    <div class="input-box">--%>
    <%--                        <label for="wechatId"></label><input type="text" placeholder="请输入新微信号" name="wechatId" id="wechatId" >--%>
    <%--                    </div>--%>
    <%--                    <div class="input-box">--%>
    <%--                        <label for="username"></label><input type="text" placeholder="请输入新用户名" name="userName" id="username">--%>
    <%--                    </div>--%>
    <%--                    <div class="input-box">--%>
    <%--                        <label for="oldPass"></label><input type="password" placeholder="请输入原密码" name="oldPass" id="oldPass">--%>
    <%--                    </div>--%>
    <%--                    <div class="input-box">--%>
    <%--                        <label for="newPass"></label><input type="password" placeholder="请输入新密码" name="password" id="newPass">--%>
    <%--                    </div>--%>
    <%--                </div>--%>
    <%--            </form>--%>
    <%--            <button class="register-btn" type="button" value="确定修改" onclick="updateUser()">修改</button>--%>
    <%--        </div>--%>
    <%--    </div>--%>
    <%--    <div id="friendInform" style="display: none">--%>
    <%--        <div class="main">--%>
    <%--            <span class="icon" onclick="hide('friendInform')"></span>--%>
    <%--            <div class="avatar"  style="background: url('img/thomas.jpg');background-size: cover ">--%>
    <%--            </div>--%>
    <%--                <div class="account">--%>
    <%--                    <div class="input-box">--%>
    <%--                        <p> "微信号:"+ </p>--%>
    <%--                    </div>--%>
    <%--                    <div class="input-box">--%>
    <%--                        <label for="username"></label><input type="text" placeholder="请输入新用户名" name="userName" id="username">--%>
    <%--                    </div>--%>
    <%--                    <div class="input-box">--%>
    <%--                        <label for="oldPass"></label><input type="password" placeholder="请输入原密码" name="oldPass" id="oldPass">--%>
    <%--                    </div>--%>
    <%--                    <div class="input-box">--%>
    <%--                        <label for="newPass"></label><input type="password" placeholder="请输入新密码" name="password" id="newPass">--%>
    <%--                    </div>--%>
    <%--                </div>--%>
    <%--            <button class="register-btn" type="button" value="确定修改" onclick="updateUser()">修改</button>--%>
    <%--        </div>--%>
    <%--    </div>--%>

</div>

<div class="wrapper" id="wrapper">
    <ul class="nav nav-pills" style="padding-left: 42px" >
        <li role="presentation" ><a id="notice"><p onclick="initNotice()">通知</p></a></li>
        <li role="presentation" ><a ><p onclick="loadFriend()">好友</p></a></li>
        <li role="presentation" ><a ><p onclick="initGroup()" >群聊</p> </a></li>
        <li role="presentation" ><a ><p onclick="loadAllMoment(${userId})" >朋友圈</p></a></li>
        <li role="presentation" ><a ><p onclick="loadMyInform()">我的</p></a></li>
        <li role="presentation" ><a ><p onclick="logout()">退出登录</p></a></li>
    </ul>
    <div class="contain">
        <div class="left" style="overflow-y: auto;overflow-x: hidden" >
            <div class="top" id="searchBox">
                <input id="searchText" type="text"  placeholder="请输入用户名或微信号"/>
                <a class="search" id="searchUser" ><p onclick="searchUser()"><br>-搜索</p></a>
            </div>
            <ul class="people" id="left" >
                <%--                <li class="person" data-chat="person1" id="chat1" onclick="loadChatMessage()" >--%>
                <%--                    <img src="img/thomas.jpg" alt="" />--%>
                <%--                    <span class="name" >新的朋友</span>--%>
                <%--                    <span class="time">2:09 PM</span>--%>
                <%--                    <span class="preview">I was wondering...</span>--%>
                <%--                </li>--%>
                <%--                <li class="person" data-chat="person2">--%>
                <%--                    <img src="img/dog.png" alt="" />--%>
                <%--                    <span class="name">Dog Woofson</span>--%>
                <%--                    <span class="time">1:44 PM</span>--%>
                <%--                    <span class="preview">I've forgotten how it felt before</span>--%>
                <%--                </li>--%>
                <%--                <li class="person" data-chat="person3">--%>
                <%--                    <img src="img/background.jpg" >--%>
                <%--                    <span class="name">Louis CK</span>--%>
                <%--                    <span class="time">2:09 PM</span>--%>
                <%--                    <span class="preview">But we’re probably gonna need a new carpet.</span>--%>
                <%--                </li>--%>
                <%--                <li class="person" data-chat="person4">--%>
                <%--                    <img src="img/bo-jackson.jpg" alt="" />--%>
                <%--                    <span class="name">Bo Jackson</span>--%>
                <%--                    <span class="time">2:09 PM</span>--%>
                <%--                    <span class="preview">It’s not that bad...</span>--%>
                <%--                </li>--%>
                <%--                <li class="person" data-chat="person5">--%>
                <%--                    <img src="img/michael-jordan.jpg" alt="" />--%>
                <%--                    <span class="name">Michael Jordan</span>--%>
                <%--                    <span class="time">2:09 PM</span>--%>
                <%--                    <span class="preview">Wasup for the third time likeList is --%>
                <%--you blind bitch</span>--%>
                <%--                </li>--%>
                <%--                <li class="person" data-chat="person6">--%>
                <%--                    <img src="img/drake.jpg" alt="" />--%>
                <%--                    <span class="name">Drake</span>--%>
                <%--                    <span class="time">2:09 PM</span>--%>
                <%--                    <span class="preview">howdoyoudoaspace</span>--%>
                <%--                </li>--%>
                <%--            </ul>--%>
        </div>
        <div class="right" id="rightBox" >

            <%--            <div class="top" style="top:0"><span><span class="name"></span></span></div>--%>
            <%--            <div class="chat" data-chat="person1" id="right" style="display: block;overflow-y:auto">--%>
            <%--            </div>--%>
            <%--            <div class="chat" data-chat="person2">--%>
            <%--                <div class="conversation-start">--%>
            <%--                    <span>Today, 5:38 PM</span>--%>
            <%--                </div>--%>
            <%--                <div class="bubble you">--%>
            <%--                    Hello, can you hear me?--%>
            <%--                </div>--%>
            <%--                <div class="bubble you">--%>
            <%--                    I'm in California dreaming--%>
            <%--                </div>--%>
            <%--                <div class="bubble me">--%>
            <%--                    ... about who we used to be.--%>
            <%--                </div>--%>
            <%--                <div class="bubble me">--%>
            <%--                    Are you serious?--%>
            <%--                </div>--%>
            <%--                <div class="bubble you">--%>
            <%--                    When we were younger and free...--%>
            <%--                </div>--%>
            <%--                <div class="bubble you">--%>
            <%--                    I've forgotten how it felt before--%>
            <%--                </div>--%>
            <%--            </div>--%>
            <%--            <div class="chat" data-chat="person3">--%>
            <%--                <div class="conversation-start">--%>
            <%--                    <span>Today, 3:38 AM</span>--%>
            <%--                </div>--%>
            <%--                <div class="bubble you">--%>
            <%--                    Hey human!--%>
            <%--                </div>--%>
            <%--                <div class="bubble you">--%>
            <%--                    Umm... Someone took a shit in the hallway.--%>
            <%--                </div>--%>
            <%--                <div class="bubble me">--%>
            <%--                    ... what.--%>
            <%--                </div>--%>
            <%--                <div class="bubble me">--%>
            <%--                    Are you serious?--%>
            <%--                </div>--%>
            <%--                <div class="bubble you">--%>
            <%--                    I mean...--%>
            <%--                </div>--%>
            <%--                <div class="bubble you">--%>
            <%--                    It’s not that bad...--%>
            <%--                </div>--%>
            <%--                <div class="bubble you">--%>
            <%--                    But we’re probably gonna need a new carpet.--%>
            <%--                </div>--%>
            <%--            </div>--%>
            <%--            <div class="chat" data-chat="person4">--%>
            <%--                <div class="conversation-start">--%>
            <%--                    <span>Yesterday, 4:20 PM</span>--%>
            <%--                </div>--%>
            <%--                <div class="bubble me">--%>
            <%--                    Hey human!--%>
            <%--                </div>--%>
            <%--                <div class="bubble me">--%>
            <%--                    Umm... Someone took a shit in the hallway.--%>
            <%--                </div>--%>
            <%--                <div class="bubble you">--%>
            <%--                    ... what.--%>
            <%--                </div>--%>
            <%--                <div class="bubble you">--%>
            <%--                    Are you serious?--%>
            <%--                </div>--%>
            <%--                <div class="bubble me">--%>
            <%--                    I mean...--%>
            <%--                </div>--%>
            <%--                <div class="bubble me">--%>
            <%--                    It’s not that bad...--%>
            <%--                </div>--%>
            <%--            </div>--%>
            <%--            <div class="chat" data-chat="person5">--%>
            <%--                <div class="conversation-start">--%>
            <%--                    <span>Today, 6:28 AM</span>--%>
            <%--                </div>--%>
            <%--                <div class="bubble you">--%>
            <%--                    Wasup--%>
            <%--                </div>--%>
            <%--                <div class="bubble you">--%>
            <%--                    Wasup--%>
            <%--                </div>--%>
            <%--                <div class="bubble you">--%>
            <%--                    Wasup for the third time likeList is <br />you blind bitch--%>
            <%--                </div>--%>

            <%--            </div>--%>
            <%--            <div class="chat" data-chat="person6">--%>
            <%--                <div class="conversation-start">--%>
            <%--                    <span>Monday, 1:27 PM</span>--%>
            <%--                </div>--%>
            <%--                <div class="bubble you">--%>
            <%--                    So, how's your new phone?--%>
            <%--                </div>--%>
            <%--                <div class="bubble you">--%>
            <%--                    You finally have a smartphone :D--%>
            <%--                </div>--%>
            <%--                <div class="bubble me">--%>
            <%--                    Drake?--%>
            <%--                </div>--%>
            <%--                <div class="bubble me">--%>
            <%--                    Why aren't you answering?--%>
            <%--                </div>--%>
            <%--                <div class="bubble you">--%>
            <%--                    howdoyoudoaspace--%>
            <%--                </div>--%>
            <%--            </div>--%>
            <%--            <div class="write" style="bottom: 0">--%>
            <%--                <a class="write-link attach"></a>--%>
            <%--                <input type="text" />--%>
            <%--                <a  class="write-link smiley"></a>--%>
            <%--                <a  class="write-link send"></a>--%>
            <%--            </div>--%>
        </div>
    </div>
</div>

</body>

<script>

    /**
     * @Description: 校验输入是否有效
     * @date: 22:58 2021/5/21
     */
    function isValid(value){
        if(value==null||value==''){
            alert("输入内容不能为空");
            return false;
        }
        return true;
    }

    /**
     * @Description: 校验输入
     * @date: 22:05 2021/5/13
     */
    function checkInput(value){
        var reg = /^[\u4E00-\u9FA5A-Za-z0-9_]+$/;
        if(!reg.exec(value)){
            return false;
        }
        return true;
    }

    /**
     * @Description: 校验上传是否为图片
     * @date: 16:45 2021/5/15
     */
    function checkPhoto(id){
        var reg = /\.(png|jpg|gif|jpeg|webp)$/;
        if(reg.test($("#"+id).val())){
            return true;
        }
        return false;
    }


    /**
     * @Description: 隐藏弹窗内容
     * @date: 22:04 2021/5/13
     */
    function hide(id){
        $("#simpleModal").html("");
        $("#simpleModal").hide();
    }

    /**
     * @Description: 页面初始化调用函数
     * @date: 19:15 2021/5/21
     */
    $(function (){
        connectWebsocket();
    });

    /**
     * @Description: 加载个人信息
     * @date: 16:13 2021/5/13
     */
    function loadMyInform(){
        $.post("user?method=showUserInform",{id:${userId}},function (result) {
            var user=result.data;
            var html = '    <div id="myInform" style="display: block">\n' +
                '        <div class="main">\n' +
                '            <span class="icon" onclick="hide(\'myInform\')"></span>\n' +
                '            <br><br><div class="avatar" id="avatar" style="background: url(\'upload/avatar/'+user.avatar+'\');background-size: cover ">\n' +
                '            </div>\n' +
                '                <div class="account">\n' +
                '                    <br><div style="text-decoration: underline;margin-bottom: 5px;">修改头像</div>\n' +
                '                    <br><input oninput="uploadPhoto(\'uploadAvatar\',\'upload?method=uploadPhoto&&path=upload/avatarTemp\')" type="file" name="photo" id="uploadAvatar" style="margin-left: 5px" />\n' +
                '                    <div class="input-box">\n' +
                '                        <label for="wechatId"></label><input type="text" placeholder="原微信号: '+user.wechatId+'" name="wechatId" id="wechatId" >\n' +
                '                    </div>\n' +
                '                    <div class="input-box">\n' +
                '                        <label for="username"></label><input type="text" placeholder="原用户名: '+user.userName+'" name="userName" id="username">\n' +
                '                    </div>\n' +
                '                    <div class="input-box">\n' +
                '                        <label for="oldPass"></label><input type="password" placeholder="请输入原密码" name="oldPass" id="oldPass">\n' +
                '                    </div>\n' +
                '                    <div class="input-box">\n' +
                '                        <label for="newPass"></label><input type="password" placeholder="请输入新密码" name="password" id="newPass">\n' +
                '                    </div>\n' +
                '                </div>\n' +
                '            <button class="option-btn" type="button" value="确定修改" onclick="updateUser()">修改</button>\n' +
                '        </div>\n' +
                '    </div>\n';
            document.getElementById("simpleModal").innerHTML += html;
            $("#simpleModal").show();
        });
    }

    /**
     * @Description: 更新用户信息
     * @date: 21:18 2021/5/11
     */
    function updateUser(){
        //上传到头像储存的文件夹(非临时文件夹)
        var avatar=$("#avatar").css("background-image").split("(")[1].split(")")[0];
        avatar=avatar.substring(avatar.lastIndexOf("\/")+1,avatar.length-1);
        if($("#uploadAvatar").val()!=''){
            uploadPhoto('uploadAvatar','upload?method=uploadPhoto&&name='+avatar+'&&path=upload/avatar');
        }

        var wechatId=$("#wechatId").val()
        var userName=$("#username").val();
        var password = $("#newPass").val();
        var oldPass=$("#oldPass").val();

        if(checkInput(wechatId)&&checkInput(userName)&&checkInput(password)&&checkInput(oldPass)) {
            $.post("user?method=updateUser", {
                id: ${userId},
                wechatId: wechatId,
                userName: userName,
                password: password,
                avatar: avatar,
                oldPass: oldPass
            }, function (result) {
                alert(result.message);
                hide('myInform');
                loadMyInform();
            });
        }else {
            alert("输入内容不合法,可能包含非法字符");
            return;
        }
    }

    /**
     * @Description: 上传图片工具函数
     * @date: 15:12 2021/5/12
     */
    function uploadPhoto(id,url){
        if(checkPhoto(id)) {
            var path = url.substring(url.lastIndexOf("path") + 5, url.length);
            var formData = new FormData();
            //添加图片信息的参数
            formData.append('photo', $("#" + id)[0].files[0]);
            $.ajax({
                url: url,
                type: 'POST',
                //上传文件不需要缓存
                cache: false,
                data: formData,
                // 告诉jQuery不要去处理发送的数据
                processData: false,
                // 告诉jQuery不要去设置Content-Type请求头
                contentType: false,
                success: function (result) {
                    if (result.data != null) {
                        $("#avatar").css({
                            "background-image": "url(" + path + "/" + result.data + ")",
                            "background-size": "cover"
                        });
                    }
                    return true;
                }
            })
        }else {
            alert("请确认你上传的是图片类型");
        }
    }

    /**
     * @Description: 上传图片工具函数
     * @date: 15:12 2021/5/12
     */
    function uploadFile(chatId){
        if($("#file").val()!=null&&$("#file").val()!='') {
            var name= prompt("请输入文件名称","未命名");
            var url='upload?method=uploadPhoto&&path=upload/file/'+chatId+'/';
            var path = url.substring(url.lastIndexOf("path") + 5, url.length);
            var formData = new FormData();
            //添加图片信息的参数
            formData.append('file', $("#file" )[0].files[0]);
            $.ajax({
                url: url,
                type: 'POST',
                //上传文件不需要缓存
                cache: false,
                data: formData,
                // 告诉jQuery不要去处理发送的数据
                processData: false,
                // 告诉jQuery不要去设置Content-Type请求头
                contentType: false,
                success: function (result) {
                    if (result.data != null) {
                        var content= '<a href="download?method=downloadFile&&path=upload/file/'+chatId+'/&&fileName='+result.data+'"  class="emoji" >文件名:<br> '+name+'</a>';
                        websocket.send(JSON.stringify({
                            senderId: ${userId},
                            chatId: chatId,
                            content: content,
                            type:'file'
                        }));
                    }
                    return true;
                }
            })
        }else {
            alert("上传内容不能为空");
        }
    }

    /**
     * @Description: 上传表情包函数
     * @date: 15:12 2021/5/12
     */
    function uploadEmoji(id,url){
        if(checkPhoto(id)) {
            var uploadPath = url.substring(url.lastIndexOf("path") + 5, url.length);
            var formData = new FormData();
            //添加图片信息的参数
            formData.append('photo', $("#" + id)[0].files[0]);
            $.ajax({
                url: url,
                type: 'POST',
                //上传文件不需要缓存
                cache: false,
                data: formData,
                // 告诉jQuery不要去处理发送的数据
                processData: false,
                // 告诉jQuery不要去设置Content-Type请求头
                contentType: false,
                success: function (result) {
                    if (result.data != null) {
                        $("#emoji").css({
                            "background-image": "url(" + uploadPath + "/" + result.data + ")",
                            "background-size": "cover"
                        });
                    }
                    var path=JSON.stringify(result.data);
                    var emoji =" <img onclick='reviewEmoji("+path+")'  id='"+result.data+"' class='emoji' src='upload/emoji/"+result.data+"' alt='' />\n";
                    document.getElementById("emojiList").innerHTML+=emoji;

                    //上传到用户的表情库中
                    $.post("emoji?method=uploadEmoji",{userId:${userId},path:result.data});
                }
            });
        }else {
            alert("请确认你上传的是图片类型");
        }
    }

    /**
     * @Description: 退出登录
     * @date: 12:01 2021/5/23
     */
    function logout(){

        <%--var returnData;--%>
        <%--$.ajax({--%>
        <%--    url: "user?method=logout" ,--%>
        <%--    type: "POST",--%>
        <%--    data:{userId:${userId}},--%>
        <%--    dataType: "json",--%>
        <%--    async: false,--%>
        <%--    success: function (result) {--%>
        <%--        window.open("http://${host}/login.jsp");            }--%>
        <%--});--%>
        <%--return returnData;--%>
        $.post("user?method=logout",{userId:${userId}},function (result){
            <%--window.open("http://${host}/login.jsp");--%>
            window.location.href="login.jsp";
        });
    }

    /**
     * @Description: 加载好友列表
     * @date: 16:41 2021/5/9
     */
    function loadFriend(){

        $("#left").html('');
        $("#searchBox").html('');

        var html='<input id="searchText" type="text"  placeholder="请输入用户名或微信号"/>\n' +
            '        <a class="search" id="searchUser" ><p onclick="searchUser()"><br>-搜索</p></a>';
        document.getElementById("searchBox").innerHTML+=html;

        $.post("friend?method=loadFriend",{userId:${userId}},function (result){
            if(result.flag) {
                var friends = result.data;
                var isBlock = '';
                for (var i = 0; i < friends.length; i++) {
                    if (friends[i].isBlock == 1) {
                        isBlock = "(已拉黑)";
                    }
                    var html = "<div id='friendList'>\n" +
                        "<li class='person' onclick='showFriendChat(" + friends[i].id + ")' >\n" +
                        '                <img src="upload/avatar/' + friends[i].avatar + '"' + ' />\n' +
                        '                <span class="name" >' + friends[i].alias + isBlock + '</span>\n' +
                        '                <span class="time">' + friends[i].gmtCreate + '</span>\n' +
                        '                <span class="preview">好友描述:' + friends[i].description + '</span>\n' +
                        '            </li>\n' +
                        '</div>';
                    document.getElementById("left").innerHTML += html;
                }
            }else {
                alert("你还没有加好友");
            }
        });
    }

    /**
     * @Description: 创建群聊
     * @date: 8:46 2021/5/18
     */
    function initNewGroup(){

        var html = '    <div id="newGroupInvite" style="display: block">\n' +
            '        <div class="newGroup">\n' +
            '<p class="leftText" >待邀请好友</p>'+
            '<p class="rightText">已邀请好友</p>' +
            '<div id="leftUser" class="leftUser" >' +
            '</div>'+
            '<div id="rightUser" class="rightUser"  >' +
            '</div>'+
            '            <span class="icon" onclick="hide(\'newGroupInvite\')"></span>\n' +
            '            <button class="option-btn" type="button" value="确定创建群聊" onclick="newGroup()">创建群聊</button>\n' +
            '        </div>\n' +
            '    </div>\n';
        document.getElementById("simpleModal").innerHTML += html;
        $("#simpleModal").show();
        $.post("friend?method=loadFriend",{userId:${userId}},function (result) {
            var friends=result.data;
            for(var i=0;i<friends.length;i++){
                if(!isVisitor(friends[i].friendId)) {
                    var html =
                        '<div id='+friends[i].id+'>' +
                        '<div style="background: cadetblue" id="' + friends[i].friendId + '">' +
                        '    <span>' + friends[i].alias + '</span>\n' +
                        ' <img  style="width: 80px;margin-top: 20px;"  src="upload/avatar/' + friends[i].avatar + '" alt="" >\n' +
                        '    <button onclick="move(' + friends[i].friendId + ')" class="btn-default">增加或删除</button>' +
                        '</div>' +
                        '</div>';
                    document.getElementById("leftUser").innerHTML += html;
                }
            }
        });
    }

    /**
     * @Description: 创建群聊
     * @date: 18:15 2021/5/20
     */
    function newGroup(){
        var name=prompt("请为你的群设置群名称","吹水小组");
        var userList = [];
        for (var i = 0; i < document.getElementById("rightUser").childNodes.length; i++) {
            userList[i] = document.getElementById("rightUser").childNodes[i].id;
        }
        userList=userList.toString();
        if (userList.length == 0) {
            alert("你未选中待邀请成员");
        }else {
        $.post("chat?method=newGroup",{name:name,userList:userList,userId:${userId}},function (result){
            if(result.flag){
                alert(result.message);
                hide("newGroupInvite");
                loadGroup();
            }
        });
        }
    }

    /**
     * @Description: 邀请新的群成员
     * @date: 8:46 2021/5/18
     */
    function initInviteGroup(chatId,master){

        hide("chatMembersBox");
        var html = ' <div id="InviteGroup" style="display: block">\n' +
            '        <div class="newGroup">\n' +
            '<p class="leftText" >待邀请好友</p>'+
            '<p class="rightText">已邀请好友</p>' +
            '<div id="leftUser" class="leftUser" >' +
            '</div>'+
            '<div id="rightUser" class="rightUser"  >' +
            '</div>'+
            '            <span class="icon" onclick="showChatMember(\''+chatId+'\',\''+master+'\')"></span>\n' +
            '            <button class="option-btn" type="button"  onclick="inviteMember(\''+chatId+'\')">确定邀请</button>\n' +
            '        </div>\n' +
            '    </div>\n';
        document.getElementById("simpleModal").innerHTML += html;
        $("#simpleModal").show();
        $.post("friend?method=loadFriend",{userId:${userId}},function (result) {
            var friends=result.data;
            for(var i=0;i<friends.length;i++){
                if(!isAtGroup(friends[i].friendId,chatId)&&!isVisitor(friends[i].friendId)) {
                    var html =
                        '<div style="background: cadetblue" id="' + friends[i].friendId + '">' +
                        '    <span>' + friends[i].alias + '</span>\n' +
                        ' <img  style="width: 80px;margin-top: 20px;"  src="upload/avatar/' + friends[i].avatar + '" alt="" >\n' +
                        '    <button onclick="move(' + friends[i].friendId + ')" class="btn-default">增加或删除</button>' +
                        '</div>';
                    document.getElementById("leftUser").innerHTML += html;
                }
            }
        });
    }

    /**
     * @Description: 邀请好友进入群聊(游客不可进入)
     * @date: 18:15 2021/5/20
     */
    function inviteMember(chatId){
        if(confirm("确定邀请吗")) {
            var userList = [];
            for (var i = 0; i < document.getElementById("rightUser").childNodes.length; i++) {
                userList[i] = document.getElementById("rightUser").childNodes[i].id;
            }
            userList = userList.toString();
            if (userList.length == 0) {
                alert("你未选中待邀请成员");
            } else {
                $.post("chat?method=inviteMember", {userList: userList, chatId: chatId}, function (result) {
                    if (result.flag) {
                        alert(result.message);
                        hide("InviteGroup");
                        showGroupChat(chatId);
                    }
                });
            }
        }
    }


    function move(id) {
        var  html = $("#" + id).parent().html();
        if (document.getElementById(id).parentElement.parentElement.id == "leftUser") {
            $("#rightUser").append(html);

            $("#"+id).parent().remove();
        }else  {
            $("#leftUser").append(html);

            $("#"+id).parent().remove();
        }
    }


    /**
     * @Description: 加载群聊列表
     * @date: 16:50 2021/5/15
     */
    function loadGroup(){
        $.post("chat?method=loadGroup",{userId:${userId}},function (result) {
            document.getElementById("left").innerHTML='';
            var groups=result.data;
              for(var i=0;i<groups.length;i++){
                  var html="<div id='groupList'>\n" +
                      "<li class='person' onclick='showGroupChat("+groups[i].id+")' >\n" +
                      '                <img src="upload/groupAvatar/'+groups[i].avatar+'" alt="" />\n' +
                      '                <span class="name" >'+groups[i].name+'</span>\n' +
                      '                <span class="time">群主id:'+groups[i].master+'</span>\n' +
                      '                <span class="preview">群号:'+groups[i].number+'</span>\n' +
                      '            </li>\n' +
                      '</div>';
                  document.getElementById("left").innerHTML+=html;
              }
        });
    }

    /**
     * @Description: 加载朋友圈
     * @date: 11:24 2021/5/16
     */
    function loadAllMoment(userId){
        var params = {
            "type": "all",
            "userId": userId,
        };
        window["filter"] = params;
        window.open("http://${host}/moment.jsp");
    }

    /**
     * @Description: 展示群聊窗口
     * @date: 22:44 2021/5/15
     */
    function showGroupChat(chatId) {
        $.post("chat?method=showGroupChat", {chatId: chatId}, function (result) {
            var chat = result.data;
            $("#rightBox").html('');

            var html = '<div class="top" style="top:0">' +
                '<span class="name">In: ' + chat.name + '</span>' +
                '<input type="file" name="file" id="file" onchange="uploadFile('+chatId+')" style="display:none">'+
                "<span  onclick='initUpload("+chatId+")' class='icon' style='right:40px;background-image: url(img/发送文件.png)' ></span>" +
                "<span onclick='showChatInform(" + chat.id + ")' class='icon' style='background-image: url(img/省略号.png)' ></span>" +
                '</div>' +
                '<div id=' + chat.id + '>\n' +
                '<div class="chat active-chat" data-chat="person2" id="chatBox">' +
                '</div>' +
                '<div class="write" style="bottom: 0">\n' +
                '                <a class="write-link attach"  ></a>\n' +
                '                <input type="text" id="content" />\n' +
                '                <a  class="write-link smiley" onclick="loadGroupEmoji('+chat.id+')"></a>\n' +
                '                <a  class="write-link send" onclick="sendGroupMessage(' + chat.id + ')"></a>\n' +
                '            </div>' +
                '            </div>';

            document.getElementById("rightBox").innerHTML += html;
            //展示最近记录
            loadMessage(chat.id);
        });
    }

    function initUpload(chatId){
        $("#file").trigger("click");
    }

    /**
     * @Description: 加载好友聊天窗口
     * @date: 23:39 2021/5/10
     */
    function showFriendChat(id){

        $.post("friend?method=showFriendChat",{id:id},function (result) {

            var friend=result.data;
            $("#rightBox").html('');

            var html = '<div id="top"  class="top" style="top:0">' +
                '<span class="name">To: ' + friend.alias +'('+friend.status+')'+ '</span>' +
                '<input type="file" name="file" id="file" onchange="uploadFile('+friend.chatId+')" style="display:none">'+
                "<span  onclick='initUpload("+friend.chatId+")' class='icon' style='right:40px;background-image: url(img/发送文件.png)' ></span>" +
                "<span onclick='showFriendInform(" + friend.id + ")' class='icon' style='background-image: url(img/省略号.png)' ></span>" +
                '</div>' +
                '<div id=' + friend.chatId + '>\n' +
                '<div class="chat active-chat" data-chat="person2" id="chatBox">' +
                '</div>' +
                '<div id="write"  class="write" style="bottom: 0">\n' +
                '                <a class="write-link attach"></a>\n' +
                '                <input type="text" id="content" />\n' +
                '                <a  class="write-link smiley" onclick="loadFriendEmoji(\''+friend.friendId+'\',\''+friend.chatId+'\',\''+friend.isBlock+'\')"></a>\n' +
                '                <a  class="write-link send" onclick="sendFriendMessage(\''+friend.friendId+'\',\''+friend.chatId+'\',\''+friend.isBlock+'\')"></a>\n' +
                '            </div>' +
                '            </div>';

            document.getElementById("rightBox").innerHTML += html;
            //展示最近记录
            loadMessage(friend.chatId);
        });

    }

    function initListMessageByPage(chatId){

        var html = '<nav aria-label="Page navigation" style="bottom: 15px;position: relative;margin-left: 120px;">\n' +
            '  <ul id="page" class="pagination">\n' +
            '  </ul>\n' +
            '</nav>';
        document.getElementById("write").innerHTML = '';
        document.getElementById("write").innerHTML += html;

        var currentPage=1;

        var returnData;
        $.ajax({
            url: "message?method=listMessageByPage" ,
            type: "POST",
            data:{chatId:chatId,currentPage:currentPage},
            dataType: "json",
            async: false,
            success: function (result) {
                for(var i=1;i<result.totalPage;i++){
                    alert(i);
                    document.getElementById("page").innerHTML +=  '<li><a ><p>1</p></a></li>\n' ;

                }
            }
        });
        // return returnData;


        // $.post("message?method=listMessageByPage",{chatId:chatId,currentPage:currentPage},function (result) {
        //
        //     for(var i=1;i<result.totalPage;i++){
        //         alert(i);
        //        document.getElementById("page").innerHTML +=  '<li><a ><p>1</p></a></li>\n' ;
        //
        //     }
        // });
    }

    /**
     * @Description: 展示用户个人信息
     * @date: 20:41 2021/5/21
     */
    function showUserInform(userId){

        $.post("user?method=showUserInform",{id:userId},function (result) {
            var user=result.data;
            var html =
                '    <div id="UserInform" style="display: block">\n' +
                '        <div class="searchUserInform">\n' +
                '            <span class="search-icon" onclick="hide(\'UserInform\')"></span>\n' +
                '            <br><div id="avatar"  class="avatar" style="background: url(\'upload/avatar/'+user.avatar+'\');background-size: cover ">\n' +
                '            </div>\n' +
                '                <div class="account">\n' +
                '                    <div class="input-box">\n' +
                '                        <br><br><p class="friendInfoText"> <font color="#99FF33" > 微信号:</font>  '+ user.wechatId +'</p>\n' +
                '                    </div>\n' +
                '                    <div class="input-box">\n' +
                '                        <br><br><p class="friendInfoText"> <font color="#99FF33" >用户名:</font>  '+ user.userName +'</p>\n' +
                '                    </div>\n' +
                '                </div>\n' +
                '            <br><button class="option-btn" type="button"  onclick="addFriend('+userId+')">添加好友</button>\n' +
                '        </div>\n' +
                '</div>';

            document.getElementById("simpleModal").innerHTML += html;
            $("#simpleModal").show();
        });
    }

    /**
     * @Description: 查看好友个人信息
     * @date: 10:06 2021/5/15
     */
    function showFriendInform(id){
        $.post("friend?method=showFriendInform",{id:id},function (result){
            var friendVo=result.data;

            var functionName='';
            var functionType='';
            //判断拉黑状态
            if(friendVo.isBlock==0){
                functionName='拉黑好友';
                functionType='blockFriend';
            }else {
                functionName='取消拉黑';
                functionType='unBlockFriend';
            }
            var html='    <div id="friendInform" >\n' +
                '        <div class="main">\n' +
                '            <span class="icon" onclick="hide(\'friendInform\')"></span>\n' +
                '            <br><div id="avatar"  class="avatar" style="background: url(\'upload/avatar/'+friendVo.avatar+'\');background-size: cover ">\n' +
                '            </div>\n' +
                '                <div class="account">\n' +
                '                    <div class="input-box">\n' +
                '                        <br><br><p class="friendInfoText"> <font color="#99FF33" > 微信号:</font>  '+ friendVo.wechatId +'</p>\n' +
                '                    </div>\n' +
                '                    <div class="input-box">\n' +
                '                        <br><br><p class="friendInfoText"> <font color="#99FF33" >用户名:</font>  '+ friendVo.userName +'</p>\n' +
                '                    </div>\n' +
                '                     <div class="input-box">\n' +
                '                        <br><br><p class="friendInfoText"> <font color="#99FF33" >备注:</font>  '+ friendVo.alias +'</p>\n' +
                '                    </div>\n' +
                '                </div>\n' ;

            //若对方为管理员
            if(friendVo.userId==0)
             html+=
                '            <br><button class="option-btn" type="button"  onclick="loadAllMoment('+friendVo.userId+')">查看管理员公告</button>\n' +
                '        </div>\n' +
                '    </div>';
            //若自己为管理员
            else if(0==${userId}){
                if(friendVo.validity==1) {
                    html +=
                        '<br><button class="option-btn" type="button"  onclick="loadAllMoment(' + friendVo.userId + ')">管理朋友圈</button>\n' +
                        '            <br><button id="block" class="option-btn" type="button"  onclick="blockUser(\''+friendVo.userId+'\',\''+friendVo.id+'\')">封号</button>\n' +
                        '        </div>\n' +
                        '    </div>';
                }
                else {
                    html +=
                        '<br><button class="option-btn" type="button"  onclick="loadAllMoment(' + friendVo.userId + ')">管理朋友圈</button>\n' +
                        '            <br><button id="block" class="option-btn" type="button"  onclick="unBlockUser(\''+friendVo.userId+'\',\''+friendVo.id+'\')">取消封号</button>\n' +
                        '        </div>\n' +
                        '    </div>';
                }
            }
            else{
                html+=
                    '            <br><button class="option-btn" type="button"  onclick="loadAllMoment('+friendVo.userId+')">查看朋友圈</button>\n' +
                    '            <br><button class="option-btn" type="button"  onclick="updateAlias(\''+friendVo.id+'\',\''+friendVo.alias+'\')">修改备注</button>\n' +
                    '            <br><button id="block" class="option-btn" type="button"  onclick="'+functionType+'('+friendVo.id+')">'+functionName+'</button>\n' +
                    '            <br><button id="deleteFriend" class="option-btn" type="button"  onclick="deleteFriend('+friendVo.chatId+')">删除好友</button>\n' +
                    '        </div>\n' +
                    '    </div>';
            }

            $("#simpleModal").html(html);
            $("#simpleModal").show();

        });
    }


    /**
     * @Description: 查看群聊信息
     * @date: 23:05 2021/5/15
     */
    function showChatInform(chatId) {
        $.post("chat?method=showChatInform", {chatId: chatId}, function (result) {
            var chat=result.data;
            var html= '    <div id="chatInform" >\n' +
                '        <div class="main" style="height: 600px;margin-top: 48px">\n' +
                '            <span class="icon" onclick="hide(\'chatInform\')"></span>\n' +
                '            <br><div id="avatar"  class="avatar" style="background: url(\'upload/groupAvatar/'+chat.avatar+'\');background-size: cover ">\n' +
                '            </div>\n' +
                '                <div class="account">\n' +
                '                    <div class="input-box">\n' +
                '                        <br><br><p class="friendInfoText" > <font color="#99FF33" style="margin-bottom: 2px" > 群号:</font> '+ chat.number +'</p>\n' +
                '                    </div>\n' +
                '                    <div class="input-box">\n' +
                '                        <br><br><p class="friendInfoText"> <font color="#99FF33" style="margin-top: 12px;" >群名称:</font>  '+ chat.name +'</p>\n' +
                '                    </div>\n' +
                '                     <div class="input-box">\n' +
                '                        <br><br><p class="friendInfoText"> <font color="#99FF33" >群主id:</font>  '+ chat.master +'</p>\n' ;

            if(chat.master==${userId}){
                html+=
                    '            <br><input oninput="uploadPhoto(\'uploadAvatar\',\'upload?method=uploadPhoto&&path=upload/groupAvatarTemp\')" type="file" name="photo" id="uploadAvatar" style="margin-left: 5px;background: bottom" />\n' +
                    '            <br><button class="option-btn" type="button"  onclick="updateGroupAvatar('+chatId+')">修改群头像</button>\n' +
                    '            <br><button class="option-btn" type="button"  onclick="showChatMember(\''+chatId+'\',\''+chat.master+'\')">查看群成员</button>\n' +
                    '            <br><button class="option-btn" type="button"  onclick="updateChatName(\''+chat.id+'\',\''+chat.name+'\')">修改群名称</button>\n' +
                    '            <br><button class="option-btn" type="button"  onclick="deleteGroup('+chatId+')">解散群聊</button>\n' +
                    '                    </div>\n' +
                    '                </div>\n' +
                    '        </div>\n' +
                    '    </div>';
            }else {
                html+=
                    '            <br><button class="option-btn" type="button"  onclick="showChatMember(\''+chatId+'\',\''+chat.master+'\')">查看群成员</button>\n' +
                    '            <br><button class="option-btn" type="button"  onclick="updateMemberName('+chatId +')">修改群名片</button>\n' +
                    '            <br><button class="option-btn" type="button"  onclick="exitGroup('+chatId+')">退出群聊</button>\n' +
                    '                    </div>\n' +
                    '                </div>\n' +
                    '        </div>\n' +
                    '    </div>';
            }

            $("#simpleModal").html(html);
            $("#simpleModal").show();
        });
    }

    function showChatMember(chatId,master){

        hide("chatInform");
        $.post("chat?method=showChatMember",{chatId:chatId},function (result){
            var members=result.data;
            var member='';
            var html = '    <div id="chatMembersBox" style="display: block">\n' +
                '        <div id="chatMembers" class="chatMembers">\n' +
                '<span onclick="initInviteGroup(\''+chatId+'\',\''+master+'\')" class="icon" style="right:160px; background-image: url(img/邀请好友.png)" ></span>' +
                '<button style="width: 50px;margin-left: 300px;" class="option-btn" type="button"  onclick="showChatInform('+chatId+')">返回</button>\n'+
                '        </div>\n' +
            '    </div>\n';
            $("#simpleModal").html(html);
            for(var i=0;i<members.length;i++){
                member+=
                    '<div class="chat-line" style="height: 80px;background: steelblue;margin-top: 5px">' +
                    '<div class="bubble you" style="margin-left: 100px;width:70%;float: right" >\n' ;
                    //判断是否为群主
                    if(master==${userId}&&members[i].userId!=${userId}){
                        if(members[i].isBlock==0) {
                            member += '<button onclick="blockMember(\''+members[i].id+'\',\''+members[i].chatId+'\',\''+master+'\')" class="chat-member-btn">禁言</button>' ;
                        }else {
                            member += '<button onclick="unBlockMember(\''+members[i].id+'\',\''+members[i].chatId+'\',\''+master+'\')" class="chat-member-btn">取消禁言</button>' ;
                        }
                        member+='<button onclick="deleteMember(\''+chatId+'\',\''+members[i].id+'\',\''+master+'\')" class="chat-member-btn">踢出群聊</button>';
                    }else {
                        if(members[i].userId!=${userId}){
                            member+= '<button onclick="addFriend('+members[i].userId+')" class="chat-member-btn">添加好友</button>';
                        }
                    }
                member+=
                    '                </div>' +
                    '<p class="chat-left-name" style="font-size: 15px;" >' + members[i].memberName +'('+members[i].role+')'+ '</p>' +
                    '<img src="upload/avatar/' + members[i].memberAvatar + '" class="img-circle chat-image-left" lt="">\n' +
                    '</div>' ;
            }

            document.getElementById("chatMembers").innerHTML+=member;
            $("#simpleModal").show();
        })


    }

    /**
     * @Description: 群主修改群名称
     * @date: 15:18 2021/5/22
     */
    function updateChatName(chatId,name){
        var newName=prompt("请输入新群名",name);
        if(isValid(newName)) {
            $.post("chat?method=updateChatName",{id:chatId,name:newName},function (result){
                    alert(result.message);
                    loadGroup();
                    showGroupChat(chatId);
                    showChatInform(chatId);
            });
        }
    }

    /**
     * @Description: 更改群昵称
     * @date: 22:14 2021/5/22
     */
    function updateMemberName(chatId,oldName){
        var memberName=prompt("请输入新的群名片昵称",oldName);
        if(isValid(memberName)) {
            $.post("chat?method=updateMemberName",{userId:${userId},chat:chatId,memberName:memberName},function (result){
                alert(result.message);
                loadGroup();
                showGroupChat(chatId);
            });
        }
    }

    /**
     * @Description: 群主更改群头像
     * @date: 22:14 2021/5/22
     */
    function updateGroupAvatar(chatId){
        //上传到头像储存的文件夹(非临时文件夹)
        var avatar=$("#avatar").css("background-image").split("(")[1].split(")")[0];
        avatar=avatar.substring(avatar.lastIndexOf("\/")+1,avatar.length-1);
        if(isValid($("#uploadAvatar").val())){
            uploadPhoto('uploadAvatar','upload?method=uploadPhoto&&name='+avatar+'&&path=upload/groupAvatar');
        }

        $.post("chat?method=updateGroupAvatar",{id:chatId,avatar:avatar},function (result){
            if(result.flag){
                alert("修改成功");
                loadGroup();
                showGroupChat(chatId);
            }
        })
    }

    /**
     * @Description: 解散群聊
     * @date: 23:02 2021/5/22
     */
    function deleteGroup(chatId){
        $.post("chat?method=deleteGroup",{id:chatId},function (result){
            alert(result.message);
            hide("chatInform");
            loadGroup();
            $("#rightBox").html('');
        })
    }

    /**
     * @Description: 退出群聊
     * @date: 23:05 2021/5/22
     */
    function exitGroup(chatId){
        $.post("chat?method=exitGroup",{chatId:chatId,userId:${userId}},function (result){
            alert(result.message);
            hide("chatInform");
            loadGroup();
            $("#rightBox").html('');
        })
    }

    /**
     * @Description: 禁言群成员
     * @date: 22:14 2021/5/22
     */
    function blockMember(id,chatId,master){
        $.post("chat?method=blockMember",{id:id,isBlock:1},function (result){
            alert(result.message);
            hide("chatMembersBox")
            showChatMember(chatId,master);
        })
    }

    /**
     * @Description: 取消禁言群成员
     * @date: 22:17 2021/5/22
     */
    function unBlockMember(id,chatId,master){
        $.post("chat?method=unBlockMember",{id:id,isBlock:0},function (result){
            alert(result.message);
            hide("chatMembersBox")
            showChatMember(chatId,master);
        })
    }

    /**
     * @Description: 踢出群聊
     * @date: 22:42 2021/5/22
     */
    function deleteMember(chatId,id,master){
        $.post("chat?method=deleteMember",{id:id},function (result){
            alert(result.message);
            hide("chatMembersBox");
            showChatMember(chatId,master);
            showGroupChat(chatId);
        })
    }

    /**
     * @Description: 拉黑好友
     * @date: 15:32 2021/5/15
     */
    function blockFriend(id){
        $.post("friend?method=blockFriend",{id:id,isBlock:1},function (result){
            alert(result.message);
            hide('friendInform');
            loadFriend();
            showFriendChat(id);
        });
    }

    /**
     * @Description: 取消拉黑
     * @date: 15:32 2021/5/15
     */
    function unBlockFriend(id){
        $.post("friend?method=unBlockFriend",{id:id,isBlock:0},function (result){
            alert(result.message);
            hide('friendInform');
            loadFriend();
            showFriendChat(id);
        });
    }

    /**
     * @Description: 管理员封号
     * @date: 14:34 2021/5/23
     */
    function blockUser(userId,friendId){
        $.post("user?method=blockUser",{id:userId,validity:0},function (result){
            alert(result.message);
            hide("friendInform")
            showFriendInform(friendId);
            websocket.send(JSON.stringify({
                senderId: 0,
                receiverId:friendId,
                content: "你已被封号,未申请解封通过之前不得登录",
                type:'adminNotice'
            }));
        });
    }

    /**
     * @Description: 管理员解封
     * @date: 14:34 2021/5/23
     */
    function unBlockUser(userId,friendId){
        $.post("user?method=unBlockUser",{id:userId,validity:1},function (result){
            alert(result.message);
            hide("friendInform");
            showFriendInform(friendId);
        });
    }

    /**
     * @Description: 修改备注
     * @date: 15:33 2021/5/15
     */
    function updateAlias(id,alias) {
        var alias=prompt("请输入好友备注",alias);
        $.post("friend?method=updateAlias", {id: id,alias:alias}, function (result) {
            alert(result.message);
            hide('friendInform');
            loadFriend();
            showFriendChat(id);
        });
    }

    /**
     * @Description: 删除好友
     * @date: 17:25 2021/5/17
     */
    function deleteFriend(chatId){
        if(confirm("确认删除好友吗,好友记录将一同删除")) {
            $.post("friend?method=deleteFriend",{chatId:chatId},function(result){
               alert(result.message);
               loadFriend();
               hide('friendInform');
               $("#rightBox").html('');
            });

        }
    }

    /**
     * @Description: 发送好友间聊天消息
     * @date: 23:39 2021/5/10
     */
    function sendFriendMessage(friendId,chatId,isBlock){

        $.post("friend?method=isBlocked",{userId:friendId,chatId:chatId},function(result){
            if(result.flag){
                alert(result.message);
                return;
            }else if(isBlock==1){
                alert("你已拉黑该用户");
                return;
            }
            else{
            var content=$("#content").val();
            if(isValid(content)){
                websocket.send(JSON.stringify({
                    senderId: ${userId},
                    chatId: chatId,
                    content: content
                }));
            }
            //清空输入框
                $("#content").val('');
            }
        });

    }

    /**
     * @Description: 发送群聊消息
     * @date: 23:39 2021/5/10
     */
    function sendGroupMessage(chatId){

        $.post("chat?method=isBlocked",{userId:${userId},chatId:chatId},function(result){
            if(result.flag){
                alert(result.message);
                // return;
            }else{
                    var content=$("#content").val();

                    websocket.send(JSON.stringify({
                        senderId: ${userId},
                        chatId: chatId,
                        content: content
                    }));
                    $("#content").val('');

            }
    });
    }

    function loadFriendEmoji(friendId,chatId,isBlock) {

        var html = '    <div id="myEmoji" style="display: block">\n' +
            '        <div style="overflow-y: auto"  class="main">\n' +
            '            <span class="icon" onclick="hide(\'myEmoji\')"></span>\n' +
            '            <br><br><div class="avatar" id="emoji" style="background: url(\'upload/emoji/'+"3de8b9e7-963e-4cf6-a48e-65f230b63415.png"+'\');background-size: cover ">\n' +
            '            </div>\n' +
            '                <div style="width: 79%;margin-left: 20px;"  class="account">\n' +
            '                    <br><div style="text-decoration: underline;margin-bottom: 5px;">预览表情包</div>\n' +
            '                    <br><input oninput="uploadEmoji(\'upload\',\'upload?method=uploadPhoto&&path=upload/emoji\')" type="file" name="photo" id="upload" style="margin-left: 5px" />\n' +
            '<div id="emojiList" style=" width: 120%;right: 15px;position: relative;">' +
            '</div>' +
            '            <button style="margin-top: 230px;" class="option-btn" type="button" onclick="sendFriendPhoto(\''+friendId+'\',\''+chatId+'\',\''+isBlock+'\')">确定发送表情包</button>\n' +
            '        </div>\n' +
            '    </div>\n';

        $.post("emoji?method=loadEmoji",{userId:${userId}},function(result){
            var emojis=result.data;
            var emoji='';
            for(var i=0;i<emojis.length;i++){
                var path=JSON.stringify(emojis[i].path);
                emoji +=" <img onclick='reviewEmoji("+path+")'  id='"+emojis[i].path+"' class='emoji' src='upload/emoji/"+emojis[i].path+"' alt='' />\n" ;
            }
            document.getElementById("emojiList").innerHTML+=emoji;
        });

        $("#simpleModal").html(html);
        $("#simpleModal").show();

    }

    function loadGroupEmoji(chatId) {

        var html = '    <div id="myEmoji" style="display: block">\n' +
            '        <div style="overflow-y: auto"  class="main">\n' +
            '            <span class="icon" onclick="hide(\'myEmoji\')"></span>\n' +
            '            <br><br><div class="avatar" id="emoji" style="background: url(\'upload/emoji/'+"3de8b9e7-963e-4cf6-a48e-65f230b63415.png"+'\');background-size: cover ">\n' +
            '            </div>\n' +
            '                <div style="width: 79%;margin-left: 20px;"  class="account">\n' +
            '                    <br><div style="text-decoration: underline;margin-bottom: 5px;">预览表情包</div>\n' +
            '                    <br><input oninput="uploadEmoji(\'upload\',\'upload?method=uploadPhoto&&path=upload/emoji\')" type="file" name="photo" id="upload" style="margin-left: 5px" />\n' +
            '<div id="emojiList" style=" width: 120%;right: 15px;position: relative;">' +
            '</div>' +
            '            <button style="margin-top: 230px;" class="option-btn" type="button" onclick="sendGroupPhoto('+chatId+')">确定发送表情包</button>\n' +
            '        </div>\n' +
            '    </div>\n';

        $.post("emoji?method=loadEmoji",{userId:${userId}},function(result){
            var emojis=result.data;
            var emoji='';
            for(var i=0;i<emojis.length;i++){
                var path=JSON.stringify(emojis[i].path);
                emoji +=" <img onclick='reviewEmoji("+path+")'  id='"+emojis[i].path+"' class='emoji' src='upload/emoji/"+emojis[i].path+"' alt='' />\n" ;
            }
            document.getElementById("emojiList").innerHTML+=emoji;
        });

        $("#simpleModal").html(html);
        $("#simpleModal").show();

    }

    /**
     * @Description: 预览表情包
     * @date: 21:34 2021/5/23
     */
    function reviewEmoji(path){
        $("#emoji").css({
            "background-image": "url(upload/emoji/" + path + ")",
            "background-size": "cover"
        });
    }
    
    /**
     * @Description: 发送好友表情包
     * @date: 23:39 2021/5/10
     */
    function sendFriendPhoto(friendId,chatId,isBlock){

        //发送前判断是否被拉黑
        $.post("friend?method=isBlocked",{userId:friendId,chatId:chatId},function(result){
            if(result.flag){
                alert(result.message);
                return;
            }else if(isBlock==1){
                alert("你已拉黑该用户");
                return;
            }
                var emoji=$("#emoji").css("background-image").split("(")[1].split(")")[0];
                emoji=emoji.substring(emoji.lastIndexOf("\/")+1,emoji.length-1);
                if(emoji!=null&&emoji!='') {
                    var content= '<img class="emoji" src="upload/emoji/'+emoji+'" alt="">';
                    websocket.send(JSON.stringify({
                        senderId: ${userId},
                        chatId: chatId,
                        content: content,
                        type:'photo'
                    }));
                }
                hide("myEmoji");

        });

    }

    /**
     * @Description: 发送群聊表情包
     * @date: 23:39 2021/5/10
     */
    function sendGroupPhoto(chatId){

        $.post("chat?method=isBlocked",{userId:${userId},chatId:chatId},function(result){
            if(result.flag){
                alert(result.message);
                return;
            }else{
            var emoji=$("#emoji").css("background-image").split("(")[1].split(")")[0];
            emoji=emoji.substring(emoji.lastIndexOf("\/")+1,emoji.length-1);
            if(emoji!=null&&emoji!='') {
                var content= '<img class="emoji" src="upload/emoji/'+emoji+'" alt="">';
                websocket.send(JSON.stringify({
                    senderId: ${userId},
                    chatId: chatId,
                    content: content,
                    type:'photo'
                }));
            }
            }
            hide("myEmoji");
        });

    }

    /**
     * @Description: 初始化通知列表
     * @date: 19:24 2021/5/8
     */
    function initNotice(){
        //消除有通知未读的红色边框状态
        $("#notice").css("border","");
        $("#left").html('');
        $("#rightBox").html('');
        var html='';
        if(0!=${userId}) {
             html =
                '<div id="initNotice">\n' +
                '<li class="person" onclick="loadFriendNotice()" >\n' +
                '                <img src="img/好友.png" alt="" />\n' +
                '                <span class="name" >好友申请</span>\n' +
                '                <span class="time"></span>\n' +
                '                <span class="preview"></span>\n' +
                '            </li>\n' +
                '<li class="person" onclick="loadGroupNotice()" >\n' +
                '                <img src="img/群聊.png" alt="" />\n' +
                '                <span class="name" >群聊申请</span>\n' +
                '                <span class="time"></span>\n' +
                '                <span class="preview"></span>\n' +
                '            </li>\n' +
                '<li class="person" onclick="loadSystemNotice()" >\n' +
                '                <img src="img/系统.png" alt="" />\n' +
                '                <span class="name" >系统通知</span>\n' +
                '                <span class="time"></span>\n' +
                '                <span class="preview"></span>\n' +
                '            </li></div>\n';
        }else {
            html='<div id="initNotice">\n' +
                '<li class="person" onclick="loadUserNotice()" >\n' +
                '                <img src="img/用户.png" alt="" />\n' +
                '                <span class="name" >用户申诉</span>\n' +
                '                <span class="time"></span>\n' +
                '                <span class="preview"></span>\n' +
                '            </li>\n' ;
        }
        document.getElementById("left").innerHTML+=html;
    }

    function initGroup() {
        $("#searchBox").html('');
        var html='<input id="searchText" type="text"  placeholder="请输入群号(模糊搜索)"/>\n' +
            '        <a class="search" id="searchGroup" ><p onclick="searchGroup()"><br>-搜索</p></a>';
        document.getElementById("searchBox").innerHTML+=html;
        //消除有通知未读的红色边框状态
        $("#left").html('');
        $("#rightBox").html('');
        var html =
            '<div id="initGroup">\n' +
            '<li class="person" onclick="initNewGroup()" >\n' +
            '                <img src="img/创建群聊.png" alt="" />\n' +
            '                <span class="name" >创建群聊</span>\n' +
            '                <span class="time"></span>\n' +
            '                <span class="preview"></span>\n' +
            '            </li>\n' +
            '<li class="person" onclick="loadGroup()" >\n' +
            '                <img src="img/group.png" alt="" />\n' +
            '                <span class="name" >查看群聊</span>\n' +
            '                <span class="time"></span>\n' +
            '                <span class="preview"></span>\n' +
            '            </li>\n';
        document.getElementById("left").innerHTML += html;
    }

    /**
     * @Description: 加载好友申请通知列表
     * @date: 21:31 2021/5/6
     */
    function loadFriendNotice(){
        var type="friendNotice";
        $.post("notice?method=loadNotice",{receiverId:${userId},type:type},function (result){
                var notices = result.data;
                document.getElementById("left").innerHTML=' ';
                for(var i=0;i<notices.length;i++){

                    var html='<li id='+ notices[i].id +' class="person" onclick="showFriendNotice(\''+notices[i].id+'\',\''+notices[i].senderId+'\')" >\n' +
                        '                <img src="img/name-type.png" />\n' +
                        '                <span class="name" >通知内容:'+notices[i].content.substring(0,10)+'</span>\n' +
                        '                <span class="time">发送者id:'+notices[i].senderId+'</span>\n' +
                        '                <span class="preview">时间:'+notices[i].gmtCreate+'</span>\n' +
                        '            </li>';

                    document.getElementById("left").innerHTML+=html;
                    //判断消息是否已读
                    if(notices[i].status==0){
                        toRead(notices[i].id);
                    }
                }
        });
    }

    /**
     * @Description: 加载用户申诉通知
     * @date: 21:31 2021/5/6
     */
    function loadUserNotice(){
        $.post("notice?method=loadNotice",{receiverId:${userId}},function (result){
            var notices = result.data;
            document.getElementById("left").innerHTML=' ';
            for(var i=0;i<notices.length;i++){

                var html='<li id='+ notices[i].id +' class="person" onclick="showUserNotice(\''+notices[i].id+'\',\''+notices[i].senderId+'\')" >\n' +
                    '                <img src="img/name-type.png" />\n' +
                    '                <span class="name" >通知内容:'+notices[i].content.substring(0,10)+'</span>\n' +
                    '                <span class="time">发送者id:'+notices[i].senderId+'</span>\n' +
                    '                <span class="preview">时间:'+notices[i].gmtCreate+'</span>\n' +
                    '            </li>';

                document.getElementById("left").innerHTML+=html;
                //判断消息是否已读
                if(notices[i].status==0){
                    toRead(notices[i].id);
                }
            }
        });
    }


    /**
     * @Description: 加载群聊申请通知列表
     * @date: 12:17 2021/5/22
     */
    function loadGroupNotice(){
        var type="groupNotice";
        $.post("notice?method=loadNotice",{receiverId:${userId},type:type},function (result){
            var notices = result.data;
            document.getElementById("left").innerHTML=' ';
            for(var i=0;i<notices.length;i++){

                var html='<li id='+ notices[i].id +' class="person" onclick="showGroupNotice(\''+notices[i].id+'\',\''+notices[i].senderId+'\')" >\n' +
                    '                <img src="img/name-type.png" />\n' +
                    '                <span class="name" >通知内容:'+notices[i].content.substring(0,10)+'</span>\n' +
                    '                <span class="time">发送者id:'+notices[i].senderId+'</span>\n' +
                    '                <span class="preview">时间:'+notices[i].gmtCreate+'</span>\n' +
                    '            </li>';

                document.getElementById("left").innerHTML+=html;
                //判断消息是否已读
                if(notices[i].status==0){
                    toRead(notices[i].id);
                }
            }
        });
    }

    /**
     * @Description: 加载系统通知列表
     * @date: 12:17 2021/5/22
     */
    function loadSystemNotice(){
        var type="systemNotice";
        $.post("notice?method=loadNotice",{receiverId:${userId},type:type},function (result){
            var notices = result.data;
            document.getElementById("left").innerHTML=' ';
            for(var i=0;i<notices.length;i++){

                var html='<li id='+ notices[i].id +' class="person" onclick="showSystemNotice(\''+notices[i].id+'\',\''+notices[i].senderId+'\')" >\n' +
                    '                <img src="img/name-type.png" />\n' +
                    '                <span class="name" >通知内容:'+notices[i].content.substring(0,10)+'</span>\n' +
                    '                <span class="time">发送者id:'+notices[i].senderId+'</span>\n' +
                    '                <span class="preview">时间:'+notices[i].gmtCreate+'</span>\n' +
                    '            </li>';

                document.getElementById("left").innerHTML+=html;
                //判断消息是否已读
                if(notices[i].status==0){
                    toRead(notices[i].id);
                }
            }
        });
    }



    /**
     * @Description: 展示好友申请通知详情
     * @date: 19:40 2021/5/8
     */
    function showFriendNotice(id,senderId){

        $.post("notice?method=showNotice",{id:id,senderId:senderId},function (result) {

            var noticeVo=result.data;

            $("#rightBox").html('');

            var html=
                '<div class="chat-line">' +
                '<div   class="bubble you" style="margin-left: 100px;width:70%" >\n' +
                '                    ' + noticeVo.content + '\n' +
                '                </div>' +
                '<p class="chat-left-name" ">' + noticeVo.userName + '</p>' +
                '<img src="upload/avatar/' + noticeVo.avatar + '" class="img-circle chat-image-left" lt="">\n' +
                '</div>' ;

            document.getElementById("rightBox").innerHTML += html;

            if(noticeVo.status==0){

                var btn=
                '<button onclick="receiveFriend(\''+noticeVo.id+'\',\''+noticeVo.senderId+'\')" class="notice-btn">接受好友请求</button>'+
                '<button onclick="refuseFriend(\''+noticeVo.id+'\',\''+noticeVo.senderId+'\')" class="notice-btn">拒绝好友请求</button>';

                document.getElementById("rightBox").innerHTML += btn;
            }

        });

    }

    /**
     * @Description: 展示用户申诉通知详情
     * @date: 19:40 2021/5/8
     */
    function showUserNotice(id,senderId){

        $.post("notice?method=showNotice",{id:id,senderId:senderId},function (result) {

            var noticeVo=result.data;

            $("#rightBox").html('');

            var html=
                '<div class="chat-line">' +
                '<div   class="bubble you" style="margin-left: 100px;width:70%" >\n' +
                '                    ' + noticeVo.content + '\n' +
                '                </div>' +
                '<p class="chat-left-name" ">' + noticeVo.userName + '</p>' +
                '<img src="upload/avatar/' + noticeVo.avatar + '" class="img-circle chat-image-left" lt="">\n' +
                '</div>' ;

            document.getElementById("rightBox").innerHTML += html;

            if(noticeVo.status==0){

                var btn=
                    '<button onclick="receiveRequest(\''+noticeVo.id+'\',\''+noticeVo.senderId+'\')" class="notice-btn">同意解封用户</button>'+
                    '<button onclick="refuseRequest(\''+noticeVo.id+'\',\''+noticeVo.senderId+'\')" class="notice-btn">拒绝解封用户</button>';

                document.getElementById("rightBox").innerHTML += btn;
            }

        });

    }

    /**
     * @Description: 展示群聊申请通知详情
     * @date: 19:40 2021/5/8
     */
    function showGroupNotice(id,senderId){

        $.post("notice?method=showNotice",{id:id,senderId:senderId},function (result) {

            var noticeVo=result.data;

            $("#rightBox").html('');

            var html=
                '<div class="chat-line">' +
                '<div   class="bubble you" style="margin-left: 100px;width:70%" >\n' +
                '                    ' + noticeVo.content + '\n' +
                '                </div>' +
                '<p class="chat-left-name" ">' + noticeVo.userName + '</p>' +
                '<img src="upload/avatar/' + noticeVo.avatar + '" class="img-circle chat-image-left" lt="">\n' +
                '</div>' ;

            document.getElementById("rightBox").innerHTML += html;

            if(noticeVo.status==0){

                var btn=
                    '<button onclick="receiveAddGroup(\''+noticeVo.id+'\',\''+noticeVo.senderId+'\',\''+noticeVo.chatId+'\')" class="notice-btn">同意群聊申请</button>'+
                    '<button onclick="refuseAddGroup('+noticeVo.senderId+')" class="notice-btn">拒绝群聊申请</button>';

                document.getElementById("rightBox").innerHTML += btn;
            }

        });

    }

    /**
     * @Description: 展示群聊申请通知详情
     * @date: 19:40 2021/5/8
     */
    function showSystemNotice(id,senderId){

        updateStatus(id);
        hasRead(id);

        $.post("notice?method=showNotice",{id:id,senderId:senderId},function (result) {

            var noticeVo=result.data;

            $("#rightBox").html('');

            var html=
                '<div class="chat-line">' +
                '<div   class="bubble you" style="margin-left: 100px;width:70%" >\n' +
                '                    ' + noticeVo.content + '\n' +
                '                </div>' +
                '<p class="chat-left-name" ">' + noticeVo.userName + '</p>' +
                '<img src="upload/avatar/' + noticeVo.avatar + '" class="img-circle chat-image-left" lt="">\n' +
                '</div>' ;

            document.getElementById("rightBox").innerHTML += html;

        });

    }

    /**
     * @Description: 加载最近聊天记录
     * @date: 17:06 2021/5/14
     */
    function loadMessage(chatId){
        $.post("chat?method=loadMessage",{chatId:chatId},function (result){
            var messages=result.data;
            for(var i=messages.length-1;i>=0;i--){
                showMessage(messages[i]);
            }
        });
    }

    /**
     * @Description: 收到消息时根据类型展示
     * @date: 15:39 2021/5/6
     */
    function showMessage(message){

        switch (message.type) {
            case "friendNotice":
                alert("有新的好友验证信息,请点击左上角通知按钮查收");
                // loadFriendNotice();
                toRead('notice');
                break;
            case "groupNotice":
                alert("有新的群聊申请信息,请点击左上角通知按钮查收");
                // loadGroupNotice();
                toRead('notice');
                break;
            case "systemNotice":
                alert("有新的系统信息,请点击左上角通知按钮查收");
                // loadSystemNotice();
                toRead('notice');
                break;
            case "adminNotice":
                alert(message.content);
                window.location.href="login.jsp";
                logout();
                return;
            default:
                $.ajax({
                    type: "POST",
                    traditional: true,
                    url: "chat?method=showMemberInform",
                    async: false,
                    data: {userId: message.senderId,chatId:message.chatId},
                    dataType: "json",
                    success: function (result) {
                        var member = result.data;
                        var type = null;
                        var position = null;
                        if (message.senderId == ${userId}) {
                            type = 'me';
                            position = 'right';
                        } else {
                            type = 'you';
                            position = 'left';
                        }
                        var html =
                            '<div class="chat-line">' +
                            '<div id="'+message.chatId+'"  class="bubble ' + type + '" >\n' +
                            '                    ' + message.content + '\n' +
                            '                </div>' +
                            '<p class="chat-' + position + '-name" ">' + member.memberName + '</p>' +
                            '<img src="upload/avatar/' + member.memberAvatar + '" class="img-circle chat-image-' + position + '" lt="">\n' +
                            '</div>'
                        ;
                        if (document.getElementById("rightBox").childNodes[1] != null) {
                            var chatId = document.getElementById("rightBox").childNodes[1].id;
                            //若处在消息对应的聊天框,则显示出来
                            if (chatId == message.chatId) {
                                document.getElementById("chatBox").innerHTML += html;
                                if(message.type=='photo'){
                                    $("#"+message.chatId).css("background","unset");
                                }
                                document.getElementById("chatBox").scrollTop = document.getElementById("chatBox").scrollHeight;
                            }
                        }
                    }
                });
        }
    }

    /**
     * @Description: 标记为已读
     * @date: 15:40 2021/5/6
     */
    function hasRead(id){
        $("#"+id).css("border","");
    }
    /**
     * @Description: 标记为未读
     * @date: 15:41 2021/5/6
     */
    function toRead(id){
        $("#"+id).css("border","1px solid red");
    }

    /**
     * @Description: 更新通知消息已读未读状态
     * @date: 22:57 2021/5/20
     */
    function updateStatus(noticeId){
        $.post("notice?method=updateStatus",{id:noticeId});
    }

    /**
     * @Description: WebSocket脚本
     * @date: 9:30 2021/5/5
     */
    function connectWebsocket() {

        websocket = null;
        var url = "ws://${host}/server/chat/${sessionScope.login.id}";

        //判断当前浏览器是否支持WebSocket
        if ('WebSocket' in window) {
            websocket = new WebSocket(url);
        } else {
            alert('当前浏览器不支持 websocket,无法进行实时聊天');
        }

        //连接发生错误的回调方法
        websocket.onerror = function () {
            websocket = '';
        };

        //连接成功建立的回调方法
        websocket.onopen = function () {
        }

        //接收到服务器消息时的回调方法
        websocket.onmessage = function (event) {
            var message = eval("(" + event.data + ")");
            showMessage(message);
        }
        //连接关闭的回调方法
        websocket.onclose = function () {
            websocket = '';
            alert("连接已断开,请刷新浏览器重新连接");
        }

        //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
        window.onbeforeunload = function () {
            closeWebSocket();
        }

    }

    //关闭WebSocket连接
    function closeWebSocket() {
        websocket.close();

    }


    /**
     * @Description: 发送好友请求
     * @date: 9:31 2021/5/5
     */
    function addFriend(friendId){

        if(isFriend(friendId)){
            alert("你已添加该好友");
        } else {
                   var description=prompt("尝试一下向对方描述你自己吧","....");
                   var alias=prompt("请输入好友备注","未设置");

                   $.post("friend?method=addFriend",{userId:${userId},friendId:friendId,alias:alias,description:description},function (result) {
                       alert(result.message);
                       if(result.flag){
                           if(document.getElementById("left").childNodes[1].id=="friendList"){
                               loadFriend();
                           }
                       }
                   });
                   return true;

           }

    }

    /**
     * @Description: 同意解封用户
     * @date: 19:37 2021/5/24
     */
    function receiveRequest(noticeId,senderId){
        if(confirm("确定解封吗")) {
            //更改通知消息状态
            updateStatus(noticeId);
            $.post("user?method=unBlockUser", {id:senderId,validity:1},function (reuslt) {
                alert(reuslt.message);
                loadUserNotice();
                showUserNotice(noticeId, senderId);
            });
        }else {
            return;
        }
    }

    /**
     * @Description: 拒绝解封用户
     * @date: 19:37 2021/5/24
     */
    function refuseRequest(noticeId,senderId){
        if(prompt("确定拒绝解封该用户吗")) {
            //更改通知消息状态
            updateStatus(noticeId);
        }else {
            return;
        }
    }

    /**
     * @Description: 接受好友请求
     * @date: 22:53 2021/5/20
     */
    function receiveFriend(noticeId,friendId){

        //更改消息状态
        updateStatus(noticeId);
        //添加好友
        addFriend(friendId);
        //重载通知内容(去除button防止重复加好友)
        loadFriendNotice();
        showFriendNotice(noticeId, friendId);
    }

    /**
     * @Description: 接受群聊申请
     * @date: 13:41 2021/5/22
     */
    function receiveAddGroup(noticeId,senderId,chatId){
        if(confirm("确定接受该成员吗")) {
            //更改通知消息状态
            updateStatus(noticeId);
            $.post("chat?method=receiveAddGroup", {
                userId: senderId,
                chatId: chatId,
                role: "普通成员",
                type: "group"
            }, function (reuslt) {
                alert(reuslt.message);
                loadGroupNotice();
                showGroupNotice(noticeId, senderId);
            });
        }else {
            return;
        }
    }

    /**
     * @Description: 拒绝
     * @date: 22:54 2021/5/14
     */
    function refuseFriend(noticeId,friendId){
        //更改通知消息状态
        updateStatus(noticeId);
        var content=prompt("请输入拒绝的理由,好让对方死心","我们不合适");
        //重载通知内容(去除button防止重复加好友)
        loadFriendNotice();
        showFriendNotice(noticeId,friendId);
        //删除好友请求
        $.post("friend?method=refuseFriend",{userId:friendId,friendId:${userId},senderId:${userId},receiverId:friendId,content:content});

    }

    function isAtGroup(userId,chatId){
        var returnData;
        $.ajax({
            url: "chat?method=isAtGroup" ,
            type: "POST",
            data:{userId:userId,chatId:chatId},
            dataType: "json",
            async: false,
            success: function (result) {
                returnData= result.flag;
            }
        });
        return returnData;
    }

    /**
     * @Description: 判断是否为好友
     * @date: 10:46 2021/5/25
     */
    function isFriend(friendId){
        var returnData;
        $.ajax({
            url: "friend?method=isFriend" ,
            type: "POST",
            data:{userId:${userId},friendId:friendId},
            dataType: "json",
            async: false,
            success: function (result) {
                returnData= result.flag;
            }
        });
        return returnData;
    }

    function isVisitor(userId){

        var returnData;
        $.ajax({
            url: "user?method=isVisitor" ,
            type: "POST",
            data:{id:userId},
            dataType: "json",
            async: false,
            success: function (result) {
                returnData= result.flag;
            }
        });
        return returnData;

    }


    /**
     * @Description: 发送加群请求
     * @date: 20:48 2021/5/21
     */
    function addGroup(chatId,receiverId){

            if(isAtGroup(${userId},chatId)){
                alert("你已在群聊中");
            }else {
                if(confirm("是否确定发送加群申请")) {

                    var content=prompt("请填写加群验证消息","....");

                    $.post("chat?method=addGroup",{userId:${userId},chatId:chatId,content:content,senderId:${userId},receiverId:receiverId},function (result) {
                        alert(result.message);
                        if(result.flag){
                            if(document.getElementById("left").childNodes[1].id=="groupList"){
                                loadGroup();
                            }
                        }
                    });
                }
            }
    }


    /**
     * @Description: 加载搜索用户结果
     * @date: 9:31 2021/5/5
     */
    function loadSearchUser(user){

        var html='<li  class="person" onclick="addFriend('+user.id+')" >\n' +
            '                <img src="${pageContext.request.contextPath}/upload/avatar/'+user.avatar+'" alt="" />\n' +
            '                <span class="name" >用户名:'+user.userName+'</span>\n' +
            '                <span class="time">用户id:'+user.id+'</span>\n' +
            '                <span class="preview">微信号:'+user.wechatId+'</span>\n' +
            '            </li>';

        document.getElementById("left").innerHTML+=html;
    }

    /**
     * @Description: 加载搜索群聊结果
     * @date: 19:21 2021/5/21
     */
    function loadSearchGroup(group){

        var html='<li class="person" onclick="addGroup(\''+group.id+'\',\''+group.master+'\')" >\n' +
            '                <img src="upload/groupAvatar/'+group.avatar+'" alt="" />\n' +
            '                <span class="name" >用户名:'+group.name+'</span>\n' +
            '                <span class="time">群主id:'+group.master+'</span>\n' +
            '                <span class="preview">群号:'+group.number+'</span>\n' +
            '            </li>';

        document.getElementById("left").innerHTML+=html;
    }


    /**
     * @Description: 搜索用户功能
     * @date: 9:31 2021/5/5
     */
    function searchUser(){
        var searchText= $("#searchText").val();
        if(searchText==''||searchText==null){
            alert("搜索内容不能为空");
        }else {
            $.post("user?method=searchUser",{searchText:searchText},function (result){
                if(result.flag){
                    document.getElementById("left").innerHTML='';
                    var users = result.data;
                    for (var i = 0; i < users.length; i++) {
                        if(users[i].id!=${userId}) {
                            loadSearchUser(users[i]);
                        }
                    }
                }else {
                    alert(result.message);
                }
            });
        }
    }

    /**
     * @Description: 搜索群聊功能
     * @date: 19:20 2021/5/21
     */
    function searchGroup(){
        var number= $("#searchText").val();
        if(number==''||number==null){
            alert("搜索内容不能为空");
        }else {
            $.post("chat?method=searchGroup",{number:number},function (result){
                if(result.flag){
                    document.getElementById("left").innerHTML='';
                    var groups = result.data;
                    for (var i = 0; i < groups.length; i++) {
                        if(groups[i].type=="group") {
                            loadSearchGroup(groups[i]);
                        }
                    }
                }else {
                    alert(result.message);
                }
            });
        }
    }





</script>
<style>

    .chat-member-btn{
        /* position: absolute; */
        width: 100px;
        /* right: 20; */
        margin-top: 1px;
        height: 35px;
        display: block;
        margin-left: 145px;
        background: border-box;
        border: none;
        outline: none;
        color: #fff;
        font-size: 16px;
        cursor: pointer;
    }
    .chat-member-name{
        margin-top: 55px;
        right: 200px;
        position: absolute;
    }
    .chat-member-avatar{
        width: 60px;
        height: 60px;
        float: left;
        border-radius: 50%;
    }
    .chatMembers{
        overflow-y: auto;
        width: 350px;
        height: 562px;
        background: darkgray;
        text-align: center;
        margin-left: 660px;
        margin-top: 70px;
        position: relative;
    }
    .notice-btn{
        width: 40%;
        height: 35px;
        display: block;
        margin-top: 3px;
        margin-left: 200px;
        background: wheat;
        border: none;
        outline: none;
        color: #fff;
        font-size: 16px;
        cursor: pointer;
    }
    .leftText{
        width: 250px;
        float: left;
        margin-top: 5px;
        margin-bottom: 5px;
    }
    .rightText{
        width: 250px;
        float: right;
        margin-top: 5px;
        margin-bottom: 5px;
    }
    .newGroup{
        width: 550px;
        height: 562px;
        background: slategray;
        text-align: center;
        margin-left: 580px;
        margin-top: 70px;
        position: relative;
    }
    .leftUser{
        width: 250px;
        height: 500px;
        float: left;
        overflow-y: auto;
    }
    .rightUser{
        width: 250px;
        height: 500px;
        float: right;
        overflow-y: auto;
    }
    .searchUserInform{
        height: 350px;
        background: lightyellow;
        width: 350px;
        margin-top: 150px;
        margin-left: 660px;
    }
    .search-icon{
        color: #fff;
        float: right;
        font-size: 90px;
        margin-left: 330px;
        margin-top: 2px;
        height: 20px;
        width: 21px;
        position: absolute;
        background-image: url(img/cc.png);
    }
    .friendInfoText{
        text-align: center;
    }

    .chat-left-name{
        font-size: 12px;
        position: absolute;
        left: 90px;
        color: yellowgreen;
    }
    .chat-right-name{
        font-size: 12px;
        position: absolute;
        right: 90px;
        color: #e7c3c3;
    }




    .chat-image-right{
        position: absolute;
        right: 2;
        width: 65px;
        height: 60px;
    }
    .chat-image-left{
        position: absolute;
        left: 2px;
        float: left;
        width: 65px;
        height: 60px;
        margin-left: 5px;
    }

    .modal-header h2,.modal-footer h3{
        margin: 0;
    }
    .emoji{
        float: left;
        width: 100px;
        height: 100px;
        margin-top: 20px;
        margin-left: 5px;
        background-size: cover;
    }
    .icon{
        color: #fff;
        float: right;
        font-size: 90px;
        right: 10px;
        height: 22px;
        width: 22px;
        position: absolute;
        background-image: url('img/cc.png');
    }
    .icon:hover,.icon:focus{
        color: #000;
        text-decoration: none;
        cursor: pointer;
    }
    @keyframes modalopen{
        from {opacity: 0}
        to {opacity: 1}
    }

    *{
        margin: 0;
        padding: 0;
    }
    /*登陆区主体*/
    .main{
        width: 350px;
        height: 550px;
        background: cadetblue;
        text-align: center;
        margin-left: 700px;
        margin-top: 70px;
        position: relative;
    }

    /*头像区*/
    .avatar{
        width: 190px;
        height: 160px;
        background: url(img/wechat.png) no-repeat;
        background-size: cover;
        margin: auto;
        border-radius: 50%;
    }

    /*账号密码区*/

    .account{
        width: 75%;
        /*height: 200px;*/
        /*background: #ffd1e4;*/
        margin: 0 auto;
    }
    .input-box{
        height: 50px;
        width: 250px;
        /*background: #ffbcdd;*/
    }
    .input-box input{
        height: 40px;
        width: calc(100% - 10px);
        border: none;
        outline: none;
        padding: 0 5px;
        background: rgba(0,0,0,0.5);
        color: #ffcae5;
        font-size: 16px;
    }

    /*注册按钮*/
    .option-btn{
        width: 75%;
        height: 35px;
        display: block;
        margin-top: 0px ;
        margin-left: 35px;
        background: aquamarine;
        border: none;
        outline: none;
        color: #fff;
        font-size: 16px;
        cursor: pointer;
    }
    /*按下按钮*/
    .option-btn:active{
        position: relative;
        top:2px;
    }
    .sendCode-btn{
        position: absolute;
        left: 55px;
        height: 35px;
        margin-top:2px;
        background:#ffc5d1 ;
        border: none;
        outline: none;
        color: #fff;
        font-size: 16px;
        cursor: pointer;
    }

    /*登陆区底部*/
    .footer{
        height: 40px;
        text-align: center;
        line-height: 50px;
        position: absolute;
        bottom: 0;
        width: 100%;
        border-top: 1px solid #ccc;
        margin-top: 20px;
    }
    .footer a{
        color: #ccc;
        text-decoration: none;
    }
    .footer a:hover{
        color: red
    }




    *, *:before, *:after {
        box-sizing: border-box;
    }
    *{
        margin:0;
        padding:0;
    }
    img{
        border:0;
    }

    :root {
        --white: #fff;
        --black: #000;
        --bg: #f8f8f8;
        --grey: #999;
        --dark: #1a1a1a;
        --light: #e6e6e6;
        --wrapper: 1000px;
        --blue: #00b0ff;
    }

    body {
        background-color: var(--bg);
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-rendering: optimizeLegibility;
        font-family: 'Source Sans Pro', sans-serif;
        font-weight: 400;
        background-image: url("img/street.png");
        background-size: cover;
    }

    .side{
        position: absolute;
        top:50px;
        margin-left: 100px;
        width: 10%;
        height: 100%;
        border: 1px solid var(--light);
        background-color: var(--black);
    }
    .wrapper {
        position: relative;
        left: 50%;
        width: var(--wrapper);
        height: 900px;
        -webkit-transform: translate(-50%, 0);
        transform: translate(-50%, 0);
    }

    .contain {
        position: relative;
        margin-top: 30%;
        top: 5px;
        left: 50%;
        width: 90%;
        height: 65%;
        background-color: var(--white);
        -webkit-transform: translate(-50%, -50%);
        transform: translate(-50%, -50%);
    }
    .contain .left {
        float: left;
        width: 34%;
        height: 100%;
        border: 1px solid var(--light);
        background-color: var(--white);
    }
    .contain .left .top {
        position: relative;
        width: 100%;
        height: 96px;
        padding: 29px;
    }
    .contain .left .top:after {
        position: absolute;
        bottom: 0;
        left: 50%;
        display: block;
        width: 80%;
        height: 1px;
        content: '';
        background-color: var(--light);
        -webkit-transform: translate(-50%, 0);
        transform: translate(-50%, 0);
    }
    .contain .left input {
        float: left;
        width: 175px;
        height: 42px;
        padding: 0 15px;
        border: 1px solid var(--light);
        background-color: #eceff1;
        border-radius: 21px;
        font-family: 'Source Sans Pro', sans-serif;
        font-weight: 400;
    }
    .contain .left input:focus {
        outline: none;
    }
    .contain .left .search {
        display: block;
        float: left;
        width: 42px;
        height: 42px;
        margin-left: 10px;
        border: 1px solid var(--light);
        background-color: var(--blue);
        /*background-image: url("img/name-type.png");*/
        background-repeat: no-repeat;
        background-position: top 12px left 14px;
        border-radius: 50%;
    }
    .contain .left .people {
        margin-left: -1px;
        border-right: 1px solid var(--light);
        border-left: 1px solid var(--light);
        width: calc(100% + 2px);
    }
    .contain .left .people .person {
        position: relative;
        width: 100%;
        /*height: 55px;*/
        padding: 12px 10% 16px;
        cursor: pointer;
        background-color: var(--white);
    }
    .contain .left .people .person:after {
        position: absolute;
        bottom: 0;
        left: 50%;
        display: block;
        width: 80%;
        height: 1px;
        content: '';
        background-color: var(--light);
        -webkit-transform: translate(-50%, 0);
        transform: translate(-50%, 0);
    }
    .contain .left .people .person img {
        float: left;
        width: 40px;
        height: 40px;
        margin-right: 12px;
        border-radius: 50%;
    }
    .contain .left .people .person .name {
        font-size: 14px;
        line-height: 22px;
        color: var(--dark);
        font-family: 'Source Sans Pro', sans-serif;
        font-weight: 600;
    }
    .contain .left .people .person .time {
        font-size: 14px;
        position: absolute;
        top: 16px;
        right: 10%;
        padding: 0 0 5px 5px;
        color: var(--grey);
        background-color: var(--white);
    }
    .contain .left .people .person .preview {
        width: 190px;
        font-size: 14px;
        display: inline-block;
        overflow: hidden !important;
        /*width: 70%;*/
        white-space: nowrap;
        text-overflow: ellipsis;
        color: var(--grey);
    }
    .contain .left .people .person.active, .contain .left .people .person:hover {
        margin-top: -1px;
        margin-left: -1px;
        padding-top: 13px;
        border: 0;
        background-color: var(--blue);
        width: calc(100% + 2px);
        padding-left: calc(10% + 1px);
    }
    .contain .left .people .person.active span, .contain .left .people .person:hover span {
        color: var(--white);
        background: transparent;
    }
    .contain .left .people .person.active:after, .contain .left .people .person:hover:after {
        display: none;
    }
    .contain .right {
        position: relative;
        float: left;
        width: 66%;
        height: 100%;
    }
    .contain .right .top {
        width: 100%;
        height: 47px;
        padding: 15px 29px;
        background-color: #eceff1;
    }
    .contain .right .top span {
        font-size: 15px;
        color: var(--grey);
    }
    .contain .right .top span .name {
        color: var(--dark);
        font-family: 'Source Sans Pro', sans-serif;
        font-weight: 600;
    }
    .contain .right .chat {
        position: relative;
        display: none;
        overflow: auto;
        padding-left: 74px;
        padding-right: 20px;
        padding-bottom: 50px;
        /*padding: 0 35px 92px;*/
        border-width: 1px 1px 1px 0;
        border-style: solid;
        border-color: var(--light);
        height: calc(100% - 48px);
        justify-content: flex-end;
        flex-direction: column;
    }
    .contain .right .chat.active-chat {
        display: block;
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
        align-content: flex-start;
        justify-content: flex-end;
        align-items: flex-end;
    }
    .chat-line{
        width: 100%;
        /*height: 60px;*/
        margin-top: 20px;
    }
    .contain .right .chat.active-chat .bubble {
        transition-timing-function: cubic-bezier(0.4, -0.04, 1, 1);
    }
    .contain .right .chat.active-chat .bubble:nth-of-type(1) {
        -webkit-animation-duration: 0.15s;
        animation-duration: 0.15s;
    }
    .contain .right .chat.active-chat .bubble:nth-of-type(2) {
        -webkit-animation-duration: 0.3s;
        animation-duration: 0.3s;
    }
    .contain .right .chat.active-chat .bubble:nth-of-type(3) {
        -webkit-animation-duration: 0.45s;
        animation-duration: 0.45s;
    }
    .contain .right .chat.active-chat .bubble:nth-of-type(4) {
        -webkit-animation-duration: 0.6s;
        animation-duration: 0.6s;
    }
    .contain .right .chat.active-chat .bubble:nth-of-type(5) {
        -webkit-animation-duration: 0.75s;
        animation-duration: 0.75s;
    }
    .contain .right .chat.active-chat .bubble:nth-of-type(6) {
        -webkit-animation-duration: 0.9s;
        animation-duration: 0.9s;
    }
    .contain .right .chat.active-chat .bubble:nth-of-type(7) {
        -webkit-animation-duration: 1.05s;
        animation-duration: 1.05s;
    }
    .contain .right .chat.active-chat .bubble:nth-of-type(8) {
        -webkit-animation-duration: 1.2s;
        animation-duration: 1.2s;
    }
    .contain .right .chat.active-chat .bubble:nth-of-type(9) {
        -webkit-animation-duration: 1.35s;
        animation-duration: 1.35s;
    }
    .contain .right .chat.active-chat .bubble:nth-of-type(10) {
        -webkit-animation-duration: 1.5s;
        animation-duration: 1.5s;
    }
    .contain .right .write {
        position: absolute;
        bottom: 29px;
        left: 30px;
        height: 42px;
        padding-left: 8px;
        border: 1px solid var(--light);
        background-color: #eceff1;
        width: calc(100% - 58px);
        border-radius: 5px;
    }
    .contain .right .write input {
        font-size: 16px;
        float: left;
        width: 347px;
        height: 40px;
        padding: 0 10px;
        color: var(--dark);
        border: 0;
        outline: none;
        background-color: #eceff1;
        font-family: 'Source Sans Pro', sans-serif;
        font-weight: 400;
    }
    .contain .right .write .write-link.attach:before {
        display: inline-block;
        float: left;
        width: 20px;
        height: 42px;
        content: '';
        background-image: url("img/attachment.png");
        background-repeat: no-repeat;
        background-position: center;
    }
    .contain .right .write .write-link.smiley:before {
        display: inline-block;
        float: left;
        margin-left: 108px;
        width: 20px;
        height: 42px;
        content: '';
        background-image: url("img/smiley.png");
        background-repeat: no-repeat;
        background-position: center;
    }
    .contain .right .write .write-link.send:before {
        display: inline-block;
        float: left;
        width: 20px;
        height: 42px;
        margin-left: 2px;
        content: '';
        background-image: url("img/send.png");
        background-repeat: no-repeat;
        background-position: center;
    }
    .contain .right .bubble {
        font-size: 16px;
        position: relative;
        display: inline-block;
        clear: both;
        margin-bottom: 8px;
        padding: 13px 14px;
        vertical-align: top;
        border-radius: 5px;
    }
    .contain .right .bubble:before {
        position: absolute;
        top: 19px;
        display: block;
        width: 8px;
        height: 6px;
        content: '\00a0';
        -webkit-transform: rotate(29deg) skew(-35deg);
        transform: rotate(29deg) skew(-35deg);
    }
    .contain .right .bubble.you {
        margin-top: 20px;
        /*margin-right: 400px;*/
        left: 0px;
        position: relative;
        letter-spacing: 2px;
        line-height: 22px;
        float: left;
        color: var(--white);
        background-color: var(--blue);
        align-self: flex-end;
        -webkit-animation-name: slideFromLeft;
        animation-name: slideFromLeft;
    }
    .contain .right .bubble.you:before {
        left: -3px;
        background-color: var(--blue);
    }
    .contain .right .bubble.me {
        margin-top: 20px;
        margin-left: 200px;
        right: 50px;
        position: relative;
        letter-spacing: 2px;
        line-height: 22px;
        float: right;
        color: var(--dark);
        background-color: #eceff1;
        align-self: flex-end;
        -webkit-animation-name: slideFromRight;
        animation-name: slideFromRight;
    }
    .contain .right .bubble.me:before {
        right: -3px;
        background-color: #eceff1;
    }
    .contain .right .conversation-start {
        position: relative;
        width: 100%;
        margin-bottom: 27px;
        text-align: center;
    }
    .contain .right .conversation-start span {
        font-size: 14px;
        display: inline-block;
        color: var(--grey);
    }
    .contain .right .conversation-start span:before, .contain .right .conversation-start span:after {
        position: absolute;
        top: 10px;
        display: inline-block;
        width: 30%;
        height: 1px;
        content: '';
        background-color: var(--light);
    }
    .contain .right .conversation-start span:before {
        left: 0;
    }
    .contain .right .conversation-start span:after {
        right: 0;
    }


    @keyframes slideFromLeft {
        0% {
            margin-left: -200px;
            opacity: 0;
        }
        100% {
            margin-left: 0;
            opacity: 1;
        }
    }
    @-webkit-keyframes slideFromLeft {
        0% {
            margin-left: -200px;
            opacity: 0;
        }
        100% {
            margin-left: 0;
            opacity: 1;
        }
    }
    @keyframes slideFromRight {
        0% {
            margin-right: -200px;
            opacity: 0;
        }
        100% {
            margin-right: 0;
            opacity: 1;
        }
    }
    @-webkit-keyframes slideFromRight {
        0% {
            margin-right: -200px;
            opacity: 0;
        }
        100% {
            margin-right: 0;
            opacity: 1;
        }
    }



</style>
</html>