$(function () {
  
  $.ajax({
      async: false,
      url: "http://localhost:8080/SISColeta/IndexJsonDataServlet?determinador=true",
      dataType:"json",
      success: function(JsonData) {
    	  document.getElementById("boxnumberdeterminador").innerHTML = JsonData;  
      }
  });
  
  $.ajax({
      async: false,
      url: "http://localhost:8080/SISColeta/IndexJsonDataServlet?participacao=true",
      dataType:"json",
      success: function(JsonData) {
    	  document.getElementById("boxnumberparticipacao").innerHTML = JsonData;  
      }
  });
  
});
