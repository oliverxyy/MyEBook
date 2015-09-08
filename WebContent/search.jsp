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
<form action="SearchServlet?op=search" method="post">
<div class="col-md-12">
    <div class="row clearfix search-header">
        <div class="col-md-12">
            <div class="row clearfix body">
                <div class="col-md-1">
                    <img src="img/logo.png" width="100" height="40"/>
                </div>
                <div class="col-md-8">
                        <div class="col-md-8">
                            <input type="text" class="form-control input-lg" name="content" value="${content}" placeholder="输入关键字...">
                        </div>
                        <div class="col-md-2">
                            <button type="submit" class="btn btn-lg btn-primary col-md-12 search_btn">E搜一下</button>
                        </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 search-result-msg">
            <div class="row clearfix">
                <div class="col-md-12 search-result-msg-text">
                    E搜帮你搜到了${count }条结果.
                </div>
            </div>
        </div>
    </div>
    <div class="row clearfix search-body">
        <div class="col-md-12">
            <div class="row clearfix">
                <div class="col-md-12">
	                <c:forEach items="${ rows }" var="row">
		                <c:choose>
							<c:when test="${row!=NULL}">
								<div class="row clearfix search-item">
			                        <a class="clearfix text" href="${row['url'] }" target="_blank">${row['text'] }</a>
			                        <a class="clearfix url" href="${row['url'] }" target="_blank">来源：${row['url'] }</a>
		                    	</div>
							</c:when>
							<c:otherwise>
							</c:otherwise>
						</c:choose>
					</c:forEach>
                </div>
            </div>
        </div>
    </div>
    <div class="row clearfix">
        <div class="pagination center-block">
        	${pagination}
        </div>
    </div>
    <div class="row clearfix search_footer">
        <div class="col-md-12 search_footer_row1">
            <a class="footer-border">免责申明</a>
            <a class="footer-border">联系我们</a>
            <a class="footer-border">友情链接</a>
            <a class="">关于我们</a>
        </div>
        <div class="col-md-12 search_footer_row2">
            <p class="col-md-12">Copyright&copy; 2015 All Rights Reserved. Oliver.xyy. 版权所有</p>
        </div>
    </div>
</div>
</form>
</body>
</html>
