'use strict';
$(function () {
  
  //Simple implementation of direct chat contact pane toggle (TEMPORARY)
  $('[data-widget="chat-pane-toggle"]').click(function(){
    $("#myDirectChat").toggleClass('direct-chat-contacts-open');
  });

  /* ChartJS
   * -------
   * Here we will create a few charts using ChartJS
   */
  
  var chartColeta = {
          
		  /*Makes the AJAX calll (synchronous) to load a Student Data*/
		  loadColetaData : function(){
		   
		   var coletaArray =[];
		    $.ajax({
		      
		      async: false,
		       
		      url: "http://localhost:8080/SISColeta/IndexJsonDataServlet?chartproducao=true",
		       
		      dataType:"json",
		      success: function(coletaJsonData) {
		    	  coletaArray = coletaJsonData; 
		        console.log(coletaJsonData);  
		      } ,
		    statusCode: {
		        404: function() {
		          alert("Chart: Página não encontrada!");
		        }
		      }
		    })
		    .fail(function() {
		    	alert("Chart: Error ao receber dados.");
		    });
		   return coletaArray;
		  }};

  //-----------------------
  //- MONTHLY COLETA CHART -
  //-----------------------
  
  var mesColeta = ["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"]
  var d = new Date();
  var meslabel =[];
  for (var i = 0; i < 7; i++) { 
	  var mes = (d.getMonth()+2-7+i) % 12;
	  if(mes<=0){
		  mes = mes + 12;
	  }
	  meslabel.push(mesColeta[mes-1]);
  }
  
  // Get context with jQuery - using jQuery's .get() method.
  var coletaChartCanvas = $("#coletaChart").get(0).getContext("2d");
  // This will get the first returned node in the jQuery collection.
  var coletaChart = new Chart(coletaChartCanvas);

  var coletaChartData = {
    labels: meslabel,
    datasets: [
      {
        label: "Digital Goods",
        fillColor: "rgba(60,141,188,0.9)",
        strokeColor: "rgba(60,141,188,0.8)",
        pointColor: "#3b8bba",
        pointStrokeColor: "rgba(60,141,188,1)",
        pointHighlightFill: "#fff",
        pointHighlightStroke: "rgba(60,141,188,1)",
        data: chartColeta.loadColetaData()
      }
    ]
  };

  var coletaChartOptions = {
    //Boolean - If we should show the scale at all
    showScale: true,
    //Boolean - Whether grid lines are shown across the chart
    scaleShowGridLines: false,
    //String - Colour of the grid lines
    scaleGridLineColor: "rgba(0,0,0,.05)",
    //Number - Width of the grid lines
    scaleGridLineWidth: 1,
    //Boolean - Whether to show horizontal lines (except X axis)
    scaleShowHorizontalLines: true,
    //Boolean - Whether to show vertical lines (except Y axis)
    scaleShowVerticalLines: true,
    //Boolean - Whether the line is curved between points
    bezierCurve: true,
    //Number - Tension of the bezier curve between points
    bezierCurveTension: 0.3,
    //Boolean - Whether to show a dot for each point
    pointDot: false,
    //Number - Radius of each point dot in pixels
    pointDotRadius: 4,
    //Number - Pixel width of point dot stroke
    pointDotStrokeWidth: 1,
    //Number - amount extra to add to the radius to cater for hit detection outside the drawn point
    pointHitDetectionRadius: 20,
    //Boolean - Whether to show a stroke for datasets
    datasetStroke: true,
    //Number - Pixel width of dataset stroke
    datasetStrokeWidth: 2,
    //Boolean - Whether to fill the dataset with a color
    datasetFill: true,
    //String - A legend template
    legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].lineColor%>\"></span><%=datasets[i].label%></li><%}%></ul>",
    //Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
    maintainAspectRatio: false,
    //Boolean - whether to make the chart responsive to window resizing
    responsive: true
  };

  //Create the line chart
  coletaChart.Line(coletaChartData, coletaChartOptions);
 
  //---------------------------
  //- END MONTHLY COLETA CHART -
  //---------------------------
  
  //-------------
  //- PIE CHART -
  //-------------
  // Get context with jQuery - using jQuery's .get() method.

  var especieArray =[];
  $.ajax({
    
    async: false,
     
    url: "http://localhost:8080/SISColeta/IndexJsonDataServlet?maiscoletados=true",
     
    dataType:"json",
    success: function(especieJsonData) {
    	especieArray = especieJsonData; 
      console.log(especieJsonData);  
    } ,
  statusCode: {
      404: function() {
        alert("Pie: Página não encontrada!");
      }
    }
  })
  .fail(function() {
		    	alert("Pie: Error ao receber dados.");
		    });
  
  
  if(especieArray.length>4){
	  document.getElementById("pie1").innerHTML = '<i class="fa fa-circle-o text-red"></i> '+especieArray[0][1];
	  document.getElementById("pie2").innerHTML = '<i class="fa fa-circle-o text-green"></i> '+especieArray[1][1];
	  document.getElementById("pie3").innerHTML = '<i class="fa fa-circle-o text-yellow"></i> '+especieArray[2][1];
	  document.getElementById("pie4").innerHTML = '<i class="fa fa-circle-o text-aqua"></i> '+especieArray[3][1];
	  document.getElementById("pie5").innerHTML = '<i class="fa fa-circle-o text-light-blue"></i> '+especieArray[4][1];
	  
	  var pieChartCanvas = $("#pieChart").get(0).getContext("2d");
	  var pieChart = new Chart(pieChartCanvas);
	  var PieData = [
	    {
	      value: especieArray[0][0],
	      color: "#f56954",
	      highlight: "#f56954",
	      label: especieArray[0][1]
	    },
	    {
	      value: especieArray[1][0],
	      color: "#00a65a",
	      highlight: "#00a65a",
	      label: especieArray[1][1]
	    },
	    {
	      value: especieArray[2][0],
	      color: "#f39c12",
	      highlight: "#f39c12",
	      label: especieArray[2][1]
	    },
	    {
	      value: especieArray[3][0],
	      color: "#00c0ef",
	      highlight: "#00c0ef",
	      label: especieArray[3][1]
	    },
	    {
	      value: especieArray[4][0],
	      color: "#3c8dbc",
	      highlight: "#3c8dbc",
	      label: especieArray[4][1]
	    }
	  ];
	  var pieOptions = {
	    //Boolean - Whether we should show a stroke on each segment
	    segmentShowStroke: true,
	    //String - The colour of each segment stroke
	    segmentStrokeColor: "#fff",
	    //Number - The width of each segment stroke
	    segmentStrokeWidth: 1,
	    //Number - The percentage of the chart that we cut out of the middle
	    percentageInnerCutout: 50, // This is 0 for Pie charts
	    //Number - Amount of animation steps
	    animationSteps: 100,
	    //String - Animation easing effect
	    animationEasing: "easeOutBounce",
	    //Boolean - Whether we animate the rotation of the Doughnut
	    animateRotate: true,
	    //Boolean - Whether we animate scaling the Doughnut from the centre
	    animateScale: false,
	    //Boolean - whether to make the chart responsive to window resizing
	    responsive: true,
	    // Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
	    maintainAspectRatio: false,
	    //String - A legend template
	    legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<segments.length; i++){%><li><span style=\"background-color:<%=segments[i].fillColor%>\"></span><%if(segments[i].label){%><%=segments[i].label%><%}%></li><%}%></ul>",
	    //String - A tooltip template
	    tooltipTemplate: "<%=value %> <%=label%>"
	  };
	  //Create pie or douhnut chart
	  // You can switch between pie and douhnut using the method below.
	  pieChart.Doughnut(PieData, pieOptions);
	  //-----------------
	  //- END PIE CHART -
	  //-----------------

	  
  }
  else{
	  document.getElementById("pie1").innerHTML = "Dados insuficientes para geração do gráfico."
  }
  
  $.ajax({
      async: false,
      url: "http://localhost:8080/SISColeta/IndexJsonDataServlet?producao=true",
      dataType:"json",
      success: function(JsonData) {
    	  document.getElementById("boxnumbercoletados").innerHTML = JsonData;  
      }
  });
  
  $.ajax({
      async: false,
      url: "http://localhost:8080/SISColeta/IndexJsonDataServlet?emprestimos=true",
      dataType:"json",
      success: function(JsonData) {
    	  document.getElementById("boxnumberemprestimo").innerHTML = JsonData;  
      }
  });
  
  $.ajax({
      async: false,
      url: "http://localhost:8080/SISColeta/IndexJsonDataServlet?especie=true",
      dataType:"json",
      success: function(JsonData) {
    	  document.getElementById("boxnumberespecie").innerHTML = JsonData;  
      }
  });
  
  $.ajax({
      async: false,
      url: "http://localhost:8080/SISColeta/IndexJsonDataServlet?pontos=true",
      dataType:"json",
      success: function(JsonData) {
    	  document.getElementById("boxnumberponto").innerHTML = JsonData;  
      }
  });
  
});
