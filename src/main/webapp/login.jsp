<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html >
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>登录</title>
<%--    <link rel="stylesheet" type="text/css" href="css/Demo.css" >--%>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">


    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="static/js/jquery-3.4.1.min.js" ></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="static/js/bootstrap.min.js" ></script>

    <script>


        /*
        检验邮箱输入
         */
        function checkEmail(){

            email = $("#email").val();
            var login_email = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
            var flag = login_email.test(email);
            if (flag) {
                //校验通过变绿
                $("#email").css("border", "1px solid green");
            }
            else {
                //校验不通过变红
                $("#email").css("border", "1px solid red");
            }
            return flag;
        }
        /**
         * @Description: 检验密码
         * @date: 16:25 2021/5/3
         */
        function checkPassword(){
            //1.获取密码
            password = $("#password").val();
            //2.定义正则
            var login_pass = /^\w{1,15}/;

            //3.判断，给出提示信息
            var flag = login_pass.test(password);
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
         * @Description: 刷新验证码
         * @date: 16:25 2021/5/3
         */
        function changeCheckCode(){
            $("#img").attr('src',"CheckCode?method=checkCode&&time=" + new Date().getTime());
        }

        function visit(){
            $.post("user?method=visit",function (serviceResult){
                alert(serviceResult.message);
                if(serviceResult.flag){
                    $(location).attr("href","visitor.jsp");
                }
            });
        }


        $(function () {

            //默认加载页面就刷新一下验证码
            changeCheckCode();

            /**
             * 点击图片刷新验证码
             */
            $("#img").click(changeCheckCode);

            /**
             * 绑定失去焦点检验事件
             */
            $("#email").blur(checkEmail);
            $("#password").blur(checkPassword);

            /**
             * 登录按钮事件
             */
            $("#login").click(function () {

                code=$("#checkCode").val();

                var checkbox=$("#checkbox").is(':checked');
                if(!checkEmail()||!checkPassword()){
                    alert("输入不合法,请按提示框要求输入");
                }

                $.post("user?method=login",{email:email,password:password,code:code,checkbox:checkbox},function (serviceResult){
                    alert(serviceResult.message);
                    if(serviceResult.flag){
                        $(location).attr("href","wechat.jsp");
                    }
                });
            });

        });


    </script>

</head>

<body>
<div id="main">

    <div id="avatar">
    </div>
    <form id="loginForm" method="post"  >
    <div id="account">
        <div class="input-box">
            <input type="text"  placeholder="请输入邮箱" name="email" id="email" >
        </div>
        <div class="input-box">
            <input type="password"  placeholder="请输入密码" name="password" id="password">
        </div>
        <div class="input-box  ">
            <input type="text" name="checkCode" id="checkCode" placeholder="请输入验证码(忽略大小写) ">
    </div>
        <div class=" col-sm-10" >
                <label>
                    <input type="checkbox" id="checkbox" style="background-color:#e7c3c3; " >
                    <font color="#fff0f5"> 请记住我</font>
                </label>
                    <img id="img" src="CheckCode?method=checkCode" class="col-sm-offset-7 col-lg-9" style="margin-top: 0px ">
        </div>

    </div>
    </form>
    <button class="login-btn" name="login" id="login" type="submit" value="登录">登录</button>

    <div id="footer">
        <a href="register.jsp">注册</a>&nbsp&nbsp&nbsp&nbsp&nbsp
        <a href="resetPass.jsp">忘记密码?</a>&nbsp&nbsp&nbsp&nbsp&nbsp
        <a href="register.jsp">管理员模式</a>&nbsp&nbsp&nbsp&nbsp&nbsp
        <a onclick="visit()">游客模式</a>&nbsp&nbsp&nbsp&nbsp&nbsp
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
#account .input-box{
    margin: 0 auto;
    height: 50px;
    width: 250px;
    /*background: #ffbcdd;*/
}
#account .input-box input{
    height: 40px;
    width: calc(100% - 10px);
    border: none;
    outline: none;
    padding: 0 5px;
    background: rgba(0,0,0,0.5);
    color: #ffcae5;
    font-size: 16px;
}

/*登录按钮*/
.login-btn{
    width: 75%;
    height: 35px;
    display: block;
    margin-top: 55px ;
    margin-left: 35px;
    background:#ffc5d1 ;
    border: none;
    outline: none;
    color: #fff;
    font-size: 16px;
    cursor: pointer;
}
/*按下按钮*/
.login-btn:active{
    position: relative;
    top:2px;
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