<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
<link rel="stylesheet" type="text/css" href="/js/nwagon/Nwagon.css" />
<script src="/js/nwagon/Nwagon.js" type="text/javascript"></script>
<script src="/js/nwagon/Nwagon_no_vml.js" type="text/javascript"></script>
</head>

<body>
	<div id="Nwagon"></div>
	<script>
		var options = {
			'legend': {
				names: ['EunJeong','HanSol','InSook','Eom','Pearl','SeungMin','TJ','Taegyu','YongYong'],
				hrefs: [
					'http://nuli.navercorp.com/sharing/blog/post/1132444',
					'http://nuli.navercorp.com/sharing/blog/post/1132442',
					'http://nuli.navercorp.com/sharing/blog/post/1132439',
					'http://nuli.navercorp.com/sharing/blog/post/1132426',
					'http://nuli.navercorp.com/sharing/blog/post/1115205',
					'http://nuli.navercorp.com/sharing/blog/post/1111811',
					'http://nuli.navercorp.com/sharing/blog/post/1111181',
					'http://nuli.navercorp.com/sharing/blog/post/1096163',
					'http://nuli.navercorp.com/sharing/blog/post/1079940'
				]
			},
			'dataset': {
				title: 'Playing time per day',
				values: [5,7,2,4,6,3,5,2,10],
				colorset: ['#DC143C', '#FF8C00', "#30a1ce"]
			},
			'chartDiv': 'Nwagon',
			'chartType': 'column',
			'chartSize': { width: 700, height: 300 },
			'maxValue': 10,
			'increment': 1
		};
		Nwagon.chart(options);
	</script>

	<div id="chart_d"></div>
	<script>
		var options = {
			'dataset': {
				title: 'Web accessibility status',
				values:[18, 12, 3, 10, 7],
				colorset: ['#2BC8C9', '#FF8C00', '#DC143C','#2EB400', '#666666'],
				fields: ['증가율', '감소율',  'C', 'D', 'E']
			},
			'donut_width' : 40,
			'core_circle_radius':60,
			'chartDiv': 'chart_d',
			'chartType': 'donut',
			'chartSize': {width:600, height:300}
		};
		Nwagon.chart(options);
	</script>

</body>
