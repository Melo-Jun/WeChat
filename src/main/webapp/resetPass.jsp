<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="host" value="localhost:8080/melo/"/>
<html >
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>找回密码</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">


    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="static/js/jquery-3.4.1.min.js" ></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="static/js/bootstrap.min.js" ></script>

    <script>

        /**
         * @Description: 检验密码
         * @date: 10:25 2021/5/2
         */
        function checkPassword(){
            //1.获取密码
            password = $("#password").val();
            //2.定义正则
            var reg_pass = /^\w{1,15}/;

            //3.判断，给出提示信息
            var flag = reg_pass.test(password);
            if (flag) {
                //校验通过变绿
                $("#password").css("border", "1px solid green");
            }
            else {
                //校验不通过变红
                $("#password").css("border", "1px solid red");
            }
            return flag;
        }

        /**
         * @Description: 检验邮箱
         * @date: 20:13 2021/5/2
         */
        function checkEmail(){
            //1.获取邮箱
            email = $("#email").val();
            //2.定义正则
            var reg_email = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;

            //3.判断
            var flag = reg_email.test(email);
            if(flag){
                $("#email").css("border","1px solid green");
            }else{
                $("#email").css("border","1px solid red");
            }
            return flag;
        }

        /**
         * @Description: 窗口初始化操作
         * @date: 10:29 2021/5/2
         */
        $(function () {

            /*
             * 绑定失去焦点检验事件
             */
            $("#oldPass").blur(checkPassword());
            $("#password").blur(checkPassword);
            $("#email").blur(checkEmail);


            /**
             * @Description: 发送验证码
             * @date: 10:37 2021/5/2
             */
            $("#sendCode").click(function () {

                if (checkEmail()) {
                    $.post("user?method=sendCheckCode", {email: email}, function (serviceResult) {
                        alert(serviceResult.message);
                    })
                }else {
                    alert("邮箱格式不正确")
                }
            });


            /**
             * @Description: 找回密码事件
             * @date: 10:30 2021./5/2
             */
            $("#updatePass").click(function () {

                oldPass=$("#oldPass").val();
                alert(oldPass);
                code=$("#code").val();
                if(!checkPassword()||!checkEmail()||code==null){
                    alert("输入不正确,请按提示框要求输入,直至输入框转变为绿色");
                }
                $.post("user?method=resetPass",{email:email,password:password,code:code,oldPass:oldPass},function (result){
                    alert(result.message);
                    //修改成功成功，跳转成功页面
                    if(result.flag){
                        alert("将跳转到登录界面")
                        $(location).attr("href","login.jsp");
                    }
                });
            });
        });



    </script>

</head>

<body>
<div class="container">
    <div id="main">

        <div id="avatar">
        </div>
        <form id="registerForm" method="post" >
            <div id="account">
                <div class="input-box">
                    <input type="text"  placeholder="请输入你绑定的邮箱(QQ或163邮箱)" name="email" id="email" >
                </div>
                <div class="input-box">
                    <input type="password"  placeholder="请输入旧密码(1-15位)" name="oldPass" id="oldPass">
                </div>
                <div class="input-box">
                    <input type="password"  placeholder="请输入新密码(1-15位)" name="password" id="password">
                </div>
            </div>
        </form>
        <div class="input-box" >
            <div class="row">
                <div class="col-xs-5 col-sm-offset-2" >
                    <input type="text"  placeholder="验证码" name="code" id="code">
                </div>
                <div class="col-xs-5" >
                    <button class="sendCode-btn" name="sendCode" id="sendCode"  value="发送验证码">发送验证码</button>
                </div>
            </div>
        </div>
        <button class="register-btn" name="updatePass" id="updatePass" type="submit" value="修改密码">修改密码</button>

    </div>
</div>
</body>

<style>
    *{
        margin: 0;
        padding: 0;
    }
    /*网页背景图片*/
    body{
        background: url("img/background.jpg") no-repeat ;
        background-size: cover;
    }
    /*登陆区主体*/
    #main{
        width: 350px;
        height: 600px;
        background: rgba(0,0,0,0.5);
        margin: 40px auto;
        border-top: 8px solid #ffc5d1;
        position: relative;
    }

    /*头像区*/
    #avatar{
        width: 220px;
        height: 184px;
        background: url("img/wechat.png") no-repeat ;
        background-size: cover;
        margin: 50px auto;
        border-radius: 50%;

    }

    /*账号密码区*/

    #account{
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
    .register-btn{
        width: 75%;
        height: 35px;
        display: block;
        margin-top: 0px ;
        margin-left: 35px;
        background:#ffc5d1 ;
        border: none;
        outline: none;
        color: #fff;
        font-size: 16px;
        cursor: pointer;
    }
    /*按下按钮*/
    .register-btn:active{
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
    #footer{
        height: 40px;
        text-align: center;
        line-height: 50px;
        position: absolute;
        bottom: 0;
        width: 100%;
        border-top: 1px solid #ccc;
        margin-top: 20px;
    }
    #footer a{
        color: #ccc;
        text-decoration: none;
    }
    #footer a:hover{
        color: red
    }



</style>


</html>