<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Ebook搜</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="keywords" content="Ebook,电子书，电子书搜索">
    <meta name="description" content="">
    <meta name="author" content="Oliver.xyy.">
    <meta name="robots" content="index">
    <meta name="application-name" content="Ebook搜">
	<!--link rel="stylesheet/less" href="less/bootstrap.less" type="text/css" /-->
	<!--link rel="stylesheet/less" href="less/responsive.less" type="text/css" /-->
	<!--script src="js/less-1.3.3.min.js"></script-->
	<!--append ‘#!watch’ to the browser URL, then refresh the page. -->
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet">
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <![endif]-->
    <!-- Fav and touch icons -->
    <link rel="shortcut icon" href="img/favicon.ico">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/scripts.js"></script>
</head>

<body>
<div class="container">
	<div class="row clearfix header">
		<div class="col-md-12">
		</div>
	</div>
	<div class="row clearfix">
		<div class="col-md-12">
			<div class="row clearfix">
				<div class="col-md-12 logo">
                    <img src="img/logo.png" class="center-block"/>
				</div>
			</div>
			<div class="row clearfix body">
                <div class="col-md-8 col-md-offset-2">
                    <form action="SearchServlet?op=search" method="post">
                        <div class="col-md-8 col-md-offset-1">
                            <input type="text" name="content" class="form-control input-lg" placeholder="输入关键字...">
                        </div>
                        <div class="col-md-3">
                            <button type="submit" class="btn btn-lg btn-primary col-md-12">E搜一下</button>
                        </div>
                    </form>
                </div>
			</div>
		</div>
	</div>
</div>

<div class="container">
    <div class="row clearfix index_footer">
        <div class="col-md-12 ">
            <div class="row clearfix">
                <div class="col-md-6 col-md-offset-3">
                    <a class="col-md-3 footer-border">免责申明</a>
                    <a class="col-md-3 footer-border">联系我们</a>
                    <a class="col-md-3 footer-border">友情链接</a>
                    <a class="col-md-3">关于我们</a>
                </div>
            </div>
            <div class="row clearfix">
                <div class="col-md-6 col-md-offset-3">
                    <p class="center-block">Copyright&copy; 2015 All Rights Reserved. Oliver.xyy. 版权所有</p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
