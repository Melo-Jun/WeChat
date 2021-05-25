<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="host" value="localhost:8088/melo"/>
<c:set var="userId" value="${sessionScope.login.id}"/>
<html >
<head>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>朋友圈</title>

    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
    <link rel="stylesheet" href="css/reset.min.css">
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="static/js/jquery-3.4.1.min.js" ></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="static/js/bootstrap.min.js" ></script>
<script src="handyeditor/HandyEditor.min.js"></script>


</head>
<body>

<!-- 弹框部分(默认隐藏起来) -->
<div id="simpleModal" class="modal">

</div>

<div class="wrapper" id="wrapper">
    <div class="contain">
        <div class="left" style="overflow-y: auto;overflow-x: hidden" >
            <div class="top " id="momentInform"  >
            </div>
            <div class="people chat active-chat"  data-chat="person2" id="remarkBox">
            </div>
            <ul  class="people" id="left" >
            </ul>
        </div>
        <div class="right" id="rightBox" >
        </div>
    </div>
</div>

</body>

<script>

    $(function (){
        var receive = window.opener["filter"];
        //获取接收到的数据
        var type = receive["type"];
        var userId = receive["userId"];
        showMoment(userId);
    });


    /**
     * @Description: 查看单一好友的朋友圈
     * @date: 20:16 2021/5/16
     */
    function showMoment(userId){
        $.post("moment?method=showMoment",{userId:userId},function (result) {
                var momentVOs = result.data;
                //初始化朋友圈
                $("#momentInform").css({
                    "background-image": "url(upload/avatar/"+ momentVOs[0].avatar +")",
                    "background-size": "cover"
                });
                var top=
                    '<div id="top" class="top" style="top:0">' +
                    '<span class="name">' + momentVOs[0].userName + ' 的朋友圈' + '</span>' +
                    '<span id="newMoment"  class="icon" onclick="initNewMoment()" ></span>'+
                    '</div>'+
                    '<div  class="chat active-chat" data-chat="person2"  id="momentBox">' +
                    '</div>';
                document.getElementById("rightBox").innerHTML+=top;
                if(userId!=${userId}){
                    $("#newMoment").remove();
                }
                if(result.flag){
                //展示查出来的所有朋友圈消息
                for(var i=0;i<momentVOs.length;i++) {
                    var content =
                        '<div class="chat-line">' +
                        '<div   id="'+momentVOs[i].id+ "moment"+'" class="bubble you " >\n' +
                        '                    ' + momentVOs[i].content + '\n' +
                        '                </div>' +
                        '<p class="chat-left-name" >' + momentVOs[i].userName + '</p>' +
                        '<img src="upload/avatar/' + momentVOs[i].avatar + '" class="img-circle chat-image-left" lt="">\n' +
                        '</div>' +
                        '<div style="width: 100%;">' ;
                    if(momentVOs[i].photo!=null&&momentVOs[i].photo!='') {
                        var photos = momentVOs[i].photo.split(';');
                        for (var j = 0; j < photos.length; j++) {
                            content += '<img class="moment-photo"  src="upload/moment/' + photos[j] + '">\n';
                        }
                    }
                    content+=
                        '</div>' +
                        '</div>' +
                        '<div id="likeUserAvatar" class="likeUserAvatarForm" >' +
                        '</div>';
                    document.getElementById("momentBox").innerHTML+=content;
                    //对每条消息加载出点赞过的用户头像
                    $.ajax({
                        type: "POST",
                        traditional: true,
                        url: "moment?method=showLikeUser",
                        async: false,
                        data: {momentId: momentVOs[i].id},
                        dataType: "json",
                        success: function (result) {
                            var likeUsers = result.data;
                            for(var i=0;i<likeUsers.length;i++){
                                var likeUserAvatar='<img class="likeUserAvatar" src="upload/avatar/' + likeUsers[i].avatar + '">\n' ;
                                document.getElementById("likeUserAvatar").innerHTML+=likeUserAvatar;
                            }
                        }
                    });

                    var functionName='';
                    var functionType='';
                    //判断点赞状态
                    if(everLike(momentVOs[i].id)){
                        functionName='取消点赞';
                        functionType='unLike';
                    }else {
                        functionName='点赞';
                        functionType='like';
                    }
                    var btn=
                        '<p style="margin-top:5px;width: 100%">点赞数 : '+momentVOs[i].likeCount+'</p>' +
                        '<br><p style="margin-top:5px">发布时间 : '+momentVOs[i].gmtCreate +'</p>'+
                        '<div style="width: 100%">' +
                        '<button class="option-btn" onclick="showRemark('+momentVOs[i].id+')" >评论</button>' +
                        '</div>'+
                        '<div  style="width: 100%">' +
                        '<button onclick="deleteMoment('+momentVOs[i].id+')" id="delete"  class="option-btn"  style="background-color: #e4b9b9" >删除</button>' +
                        '</div>'+
                        '<div id="'+momentVOs[i].id+ "like"+'" style="width: 100%">' +
                        '<button  id="'+momentVOs[i].id+'"  class="option-btn" onclick="'+functionType+'(\''+momentVOs[i].id+'\',\''+userId+'\')" style="background-color: #e4b9b9" >'+functionName+'</button>' +
                        '</div>';
                    document.getElementById("momentBox").innerHTML+=btn;
                    //根据访问者身份动态隐藏一些组件
                    //查看别人的且自己不是管理员
                    if(userId!=${userId}&&0!=${userId}){
                        $("#delete").remove();
                        $("#newMoment").remove();
                    }
                    //查看自己的
                    else if(userId==${userId}) {
                        $("#"+momentVOs[i].id+ "like").remove();
                    }
                }
                }
        });
    }

    /**
     * @Description: 初始化发布新动态界面
     * @date: 23:38 2021/5/19
     */
    function initNewMoment(){
        document.getElementById("momentBox").innerHTML='';
        $("#remarkList").html('');
        var html=
            '<div class="back" onclick="reloadMoment()"  ></div>'+
            '<div class="icon" style="right: 25px;background-image: url(img/发布.png);"  onclick="newMoment()" ></div>'+
            '<textarea id="content" class="textarea" rows="15" cols="85">\n' +
            '        </textarea>' +
            '<br><input oninput="uploadPhoto(\'uploadMoment\',\'upload?method=uploadPhoto&&path=upload/moment\')" type="file" name="photo" id="uploadMoment" style="margin-left: 5px" />\n' +
            '<p class="text">单击图片可删除</p>' +
            '<div id="photoList" style="width:100%;">' +
            '</div>';
        document.getElementById("momentBox").innerHTML+=html;

        he = HE.getEditor('content',{
            autoHeight : true,
            autoFloat : false,
            topOffset : 0,
            lang : 'zh-jian',
            skin : 'HandyEditor',
            externalSkin : '',
            item : ['bold','italic','strike','underline','fontSize','fontName','paragraph','color','backColor','|','link','unlink','textBlock','code','selectAll','removeFormat','trash','|','expression','horizontal','orderedList','unorderedList','|','undo','redo','|']
        });
    }


    /**
     * @Description: 发布新动态
     * @date: 23:38 2021/5/19
     */
    function newMoment() {
        //遍历获取所有图片名称存入数据库
        var photos='';
        for (var i = 0; i < document.getElementById("photoList").childNodes.length; i++) {
           var photo= document.getElementById("photoList").childNodes[i].id+";";
           photos+=photo;
        }
        photos = photos.substring(0, photos.lastIndexOf(';'));
        var content =he.getHtml();
        if(content!=null&&content!=''){
            $.post("moment?method=newMoment",{userId:${userId},content:content,photo:photos},function (result){
                alert(result.message);
                if(result.flag){
                    reloadMoment();
                }
            });
        }
    }

    /**
     * @Description: 查看评论
     * @date: 23:37 2021/5/19
     */
    function showRemark(momentId){

        $.post("moment?method=showRemark",{momentId:momentId},function (result){
                $("#left").html("");
                var remarkVOs=result.data;
                for(var i=0;i<remarkVOs.length;i++){
                    var html="<div id='remarkList'>\n" +
                        "<div class='person'  >\n" +
                        '                <img src="upload/avatar/'+remarkVOs[i].avatar+'" alt="" />\n' +
                        '                <span class="name" >'+remarkVOs[i].userName+'</span>\n' +
                        '                <span class="time">'+remarkVOs[i].gmtCreate+'</span>\n' +
                        '                <br><div class="preview">'+remarkVOs[i].content+'</div>\n' +
                        '            </div>\n' +
                        '</div>';

                    document.getElementById("remarkBox").innerHTML+=html;

                }
            var userId=${userId};
            var footer=
                '<div class="write" style="bottom: 0">\n' +
                '                <input type="text" id="content" />\n' +
                '                <a  class="write-link send" onclick="newRemark(\''+userId+'\',\''+momentId+'\')"></a>\n' +
                '            </div>' ;
            document.getElementById("left").innerHTML+=footer;
        });
    }

    /**
     * @Description: 发布评论
     * @date: 23:37 2021/5/19
     */
    function newRemark(userId,momentId){

        var content= $("#content").val();
        if(content!=null&&content!='') {
            $.post("moment?method=newRemark", {userId: userId,momentId:momentId,content:content}, function (result) {
                  if(result.flag){
                      $("#remarkBox").html("");
                      showRemark(momentId);
                  }
            });
        }
    }

    /**
     * @Description: 判断是否点赞过
     * @date: 23:37 2021/5/19
     */
    function everLike(momentId){

        var returnData;
        $.ajax({
            url: "moment?method=everLike" ,
            type: "POST",
            dataType: "json",
            async: false,
            success: function (result) {
                returnData= result.flag;
            }
        });
        return returnData;

    }


    /**
     * @Description: 点赞
     * @date: 23:37 2021/5/19
     */
    function like(momentId,userId){
        $.post("moment?method=like",{userId:${userId},momentId:momentId},function (result){
            document.getElementById(momentId+"like").innerHTML='';
            var btn='<button  id="'+momentId+'"  class="option-btn" onclick="unLike('+momentId+')" style="background-color: #e4b9b9" >取消点赞</button>' ;
            document.getElementById(momentId+"like").innerHTML+=btn;
            alert("点赞成功");
            $("#rightBox").html('');
            showMoment(userId);
        });
    }

    /**
     * @Description: 取消点赞
     * @date: 23:37 2021/5/19
     */
    function unLike(momentId,userId){
        $.post("moment?method=unLike",{userId:${userId},momentId:momentId},function (result){
            document.getElementById(momentId+"like").innerHTML='';
            var btn='<button  id="'+momentId+'"  class="option-btn" onclick="like('+momentId+')" style="background-color: #e4b9b9" >点赞</button>' ;
            document.getElementById(momentId+"like").innerHTML+=btn;
            alert("取消点赞成功");
            $("#rightBox").html('');
            showMoment(userId);
        });
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
     * @Description: 上传图片工具函数
     * @date: 15:12 2021/5/12
     */
    function uploadPhoto(id,url){
        if(checkPhoto(id)) {
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
                        var id=JSON.stringify(result.data);
                        var html = "<img  id='"+result.data+"' onclick='deletePhoto("+id+")' class='moment-photo' src='upload/moment/"+result.data+"'>";
                        document.getElementById("photoList").innerHTML += html;
                    }
                }
            });
        }else {
            alert("请确认你上传的是图片类型");
        }
    }

    /**
     * @Description: 删除朋友圈
     * @date: 23:05 2021/5/19
     */
    function deleteMoment(momentId){
        $.post("moment?method=deleteMoment",{momentId:momentId},function (result){
           if(result.flag){
               alert(result.message);
               reloadMoment();
           }
        });
    }

    /**
     * @Description: 删除图片
     * @date: 17:59 2021/5/19
     */
    function deletePhoto(id){
        if(confirm("确定删除图片吗")) {
            var photo = document.getElementById(id);
            if (photo != null) {
                photo.parentNode.removeChild(photo);
            }
        }
    }

    /**
     * @Description: 重载朋友圈内容
     * @date: 23:07 2021/5/19
     */
    function reloadMoment(){
        document.getElementById("rightBox").innerHTML='';
        showMoment(${userId});
    }

</script>
</html>

<style>

    .text{
        margin-top: 8px;
         float: right;
        position: absolute;
         right: 250px;
        color: cadetblue;
    }
    .textarea{
        margin-top: 30px;
        background: url();
    }

    .likeUserAvatarForm{
        width: 100%;
        background: darkgray;
        margin-top: 10px;
    }
    .likeUserAvatar{
        width: 80px;
        height: 80px;
        margin-top: 20px;
    }
    .moment-photo{
        width: 245px;
        height: 200px;
        margin-top: 20px;
        margin-right: 20px;
        background-size: cover;
    }

    .friendInfoText{
        text-align: center;
    }

    .chat-left-name{
        font-size: 12px;
        position: absolute;
        left: 90px;
        color: darkblue;
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
    .back{
        float: left;
        margin-top: 5px;
        margin-left: 5px;
        height: 18px;
        width: 22px;
        position: absolute;
        background-image: url(img/返回.png);
    }
    .back:hover,.back:focus{
        color: #000;
        text-decoration: none;
        cursor: pointer;
    }
    .icon{
        float: right;
        right: 5px;
        height: 22px;
        width: 22px;
        position: absolute;
        background-image: url(img/newMoment.png);
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

    /*操作按钮*/
    .option-btn{
        float: right;
        margin-bottom: 5px;
        width: 150px;
        height: 35px;
        display: block;
        margin-top: 0px;
        margin-left: 5px;
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
        -webkit-transform: translate(-50%, -50%);
        transform: translate(-50%, -50%);
        background-color: #eceff1a6;
    }
    .contain .left {
        float: left;
        width: 28%;
        height: 100%;
    }
    .contain .left .top {
        position: relative;
        margin-top: 48px;
        width: 100%;
        height: 200px;
        padding: 29px;
        background-image: url(upload/avatar/e4b1fcd2-0686-464d-80bb-798198e3be89.png);
        background-size: cover;
    }
    .contain .left .top:after {
        position: absolute;
        bottom: 0;
        left: 50%;
        display: block;
        width: 80%;
        height: 1px;
        content: '';
        -webkit-transform: translate(-50%, 0);
        transform: translate(-50%, 0);
    }
    .contain .left input {
        float: left;
        width: 188px;
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
        margin-right: 2px;
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
        /* font-size: 14px; */
        display: inline-block;
        /* overflow: hidden !important; */
        /* width: 70%; */
        /* white-space: nowrap; */
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
        width: 72%;
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
        align-items: flex-start;
        justify-content: flex-start;
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
    .contain .left .write {
        position: relative;
        /* margin-top: 20px; */
        /* bottom: 29px; */
        /* left: 30px; */
        /* height: 42px; */
        padding-left: 8px;
        border: 1px solid var(--light);
        background-color: #eceff1;
        /* width: calc(100% - 550px); */
        width: 250px;
        border-radius: 5px;
    }
    .contain .left .write input {
        font-size: 16px;
        margin-top: 2p;
        float: left;
        width: 200px;
        height: 40px;
        padding: 0 10px;
        color: var(--dark);
        border: 0;
        outline: none;
        background-color: #eceff1;
        font-family: 'Source Sans Pro', sans-serif;
        font-weight: 400;
    }
    /*.contain .left .write .write-link.attach:before {*/
    /*    display: inline-block;*/
    /*    float: left;*/
    /*    width: 20px;*/
    /*    height: 42px;*/
    /*    content: '';*/
    /*    background-image: url("img/attachment.png");*/
    /*    background-repeat: no-repeat;*/
    /*    background-position: center;*/
    /*}*/
    .contain .left .write .write-link.smiley:before {
        display: inline-block;
        float: left;
        /* margin-left: 75px; */
        width: 20px;
        height: 42px;
        content: '';
        background-image: url(img/smiley.png);
        background-repeat: no-repeat;
        background-position: center;
    }
    .contain .left .write .write-link.send:before {
        display: inline-block;
        /* float: left; */
        width: 20px;
        height: 42px;
        /* margin-left: 2px; */
        content: '';
        background-image: url(img/send.png);
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
        color: honeydew;
        background-color: slategrey;
        align-self: flex-end;
        -webkit-animation-name: slideFromLeft;
        animation-name: slideFromLeft;
    }
    .contain .right .bubble.you:before {
        left: -3px;
    }
    .contain .right .bubble.me {
        margin-top: 20px;
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

