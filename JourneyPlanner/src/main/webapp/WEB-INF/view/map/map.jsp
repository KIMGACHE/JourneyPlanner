<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=337a12732b24fa97055d9e02f1204788"></script>
<meta charset="UTF-8">
<title>카카오맵 api 페이지</title>
</head>
<body>
	<!-- 카카오맵이 나오는 페이지입니다. -->

<div id="map" style="width:100%;height:100vh;"></div>

<script>
		var container = document.getElementById('map');
		 var mapOption = {
		            center: new kakao.maps.LatLng(37.5665, 126.9780), // 초기 중심좌표 (서울)
		            level: 3 // 지도 확대 레벨
	 };

		var map = new kakao.maps.Map(container, mapOption);
		
		
	</script>

</body>
</html>