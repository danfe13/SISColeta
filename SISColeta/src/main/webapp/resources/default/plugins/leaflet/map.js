
		var map = L.map('map').setView([51.505, -0.09], 13);
		
		L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
			attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
		}).addTo(map);

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

		L.marker([51.5, -0.09], {icon:icon1}).addTo(map).bindPopup("<b>Hello world!</b><br />I am a popup.");

		