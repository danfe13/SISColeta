var pontosArray =[];
		    


var pontos = new L.LayerGroup();
		
		var LeafIcon = L.Icon.extend({
			options: {
				shadowUrl: '/SISColeta/javax.faces.resource/plugins/leaflet/images/marker-shadow.png.jsf?ln=default',
				iconSize:     [25, 41],
				shadowSize:   [41, 41],
				iconAnchor:   [12, 41],
				shadowAnchor: [12, 41],
				popupAnchor:  [0, -40]
			}
		});
		var icon1 = new LeafIcon({iconUrl: '/SISColeta/javax.faces.resource/plugins/leaflet/images/marker-icon.png.jsf?ln=default'});

		$.ajax({
		      
		      async: false,
		       
		      url: "http://localhost:8080/SISColeta/IndexJsonDataServlet?mapa=true",
		       
		      dataType:"json",
		      success: function(pontosJsonData) {
		    	  $.each(pontosJsonData,function(index,ponto){
		    		  L.marker([ponto.latitude, ponto.longitude], {icon:icon1}).bindPopup(ponto.local +" - "+ponto.data).addTo(pontos);
		    		  
		    	  });
		      }
		    });
		
	    var mbAttr = 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, ' +
				'<a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
				'Imagery © <a href="http://mapbox.com">Mapbox</a>',
			mbUrl = 'https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoiZGFuZmUxMyIsImEiOiJjaWszOGJzMG0wM2p0dTZtMGV4dGFscGtvIn0.ibT6DiB96E1P3Oy-sEJcjw';

	    var grayscale   = L.tileLayer(mbUrl, {id: 'mapbox.light', attribution: mbAttr}),
		    streets  = L.tileLayer(mbUrl, {id: 'mapbox.streets',   attribution: mbAttr});

		var map = L.map('map', {
			center: [-10.78,-37.12],
			zoom: 10,
			layers: [grayscale, pontos]
		});

		var baseLayers = {
			"Escala cinza": grayscale,
			"Ruas": streets
		};

		var overlays = {
			"Pontos": pontos
		};

		L.control.layers(baseLayers, overlays).addTo(map);
	

		