var screenHeight = document.documentElement.clientHeight - document.getElementById("footerid").clientHeight - document.getElementById("myNavbar").clientHeight + "px";
document.getElementById("mapid").style.height = screenHeight;

var mymap = L.map('mapid', {
	center: [58.0099, 56.25],
	zoom: 13,
	zoomControl: false,
	attributionControl: false
});
L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
	maxZoom: 32,
	id: 'mapbox.streets'
}).addTo(mymap);

var iconbigpurple = L.icon({
	iconUrl: 'icons/map/marker_purple_64.png',
	iconSize: [64,64]
});

var iconmediumpurple = L.icon({
	iconUrl: 'icons/map/marker_purple_64.png',
	iconSize: [32,32]
});

var iconsmallpurple = L.icon({
	iconUrl: 'icons/map/marker_purple_64.png',
	iconSize: [16,16]
});

var iconbigblue = L.icon({
	iconUrl: 'icons/map/marker_blue_64.png',
	iconSize: [64,64]
});

var iconmediumblue = L.icon({
	iconUrl: 'icons/map/marker_blue_64.png',
	iconSize: [32,32]
});

var iconsmallblue = L.icon({
	iconUrl: 'icons/map/marker_blue_64.png',
	iconSize: [16,16]
});

var iconbigred = L.icon({
	iconUrl: 'icons/map/marker_red_64.png',
	iconSize: [64,64]
});

var iconmediumred = L.icon({
	iconUrl: 'icons/map/marker_red_64.png',
	iconSize: [32,32]
});

var iconsmallred = L.icon({
	iconUrl: 'icons/map/marker_red_64.png',
	iconSize: [16,16]
});

//Маркеры
var Marker0  = L.marker([58.012724,56.181788], {icon:iconmediumpurple}).addTo(mymap);
var Marker1  = L.marker([58.003221,56.206545], {icon:iconmediumred}).addTo(mymap);
var Marker2  = L.marker([58.007661,56.186351], {icon:iconmediumpurple}).addTo(mymap);
var Marker3  = L.marker([57.998819,56.174089], {icon:iconmediumpurple}).addTo(mymap);
var Marker4  = L.marker([58.044244,56.049241], {icon:iconmediumblue}).addTo(mymap);
var Marker5  = L.marker([58.034101,56.027233], {icon:iconmediumred}).addTo(mymap);
var Marker6  = L.marker([58.008052,55.955197], {icon:iconmediumpurple}).addTo(mymap);
var Marker7  = L.marker([58.000388,55.955448], {icon:iconmediumpurple}).addTo(mymap);
var Marker8  = L.marker([57.977703,56.18274],  {icon:iconmediumpurple}).addTo(mymap);
var Marker9  = L.marker([57.978276,56.19475],  {icon:iconmediumblue}).addTo(mymap);
var Marker10 = L.marker([57.967303,56.166876], {icon:iconmediumpurple}).addTo(mymap);
var Marker11 = L.marker([57.969785,56.163615], {icon:iconmediumblue}).addTo(mymap);
var Marker12 = L.marker([57.964391,56.184761], {icon:iconmediumred}).addTo(mymap);
var Marker13 = L.marker([58.102011,56.303545], {icon:iconmediumpurple}).addTo(mymap);
var Marker14 = L.marker([58.11811,56.37232],   {icon:iconmediumred}).addTo(mymap);
var Marker15 = L.marker([58.106338,56.307588], {icon:iconmediumpurple}).addTo(mymap);
var Marker16 = L.marker([58.106428,56.383307], {icon:iconmediumblue}).addTo(mymap);
var Marker17 = L.marker([58.007613,56.236378], {icon:iconmediumred}).addTo(mymap);
var Marker18 = L.marker([58.01158,56.237537],  {icon:iconmediumpurple}).addTo(mymap);
var Marker19 = L.marker([58.003007,56.221475], {icon:iconmediumpurple}).addTo(mymap);
var Marker20 = L.marker([58.011375,56.214945], {icon:iconmediumpurple}).addTo(mymap);
var Marker21 = L.marker([58.014021,56.248784], {icon:iconmediumblue}).addTo(mymap);
var Marker22 = L.marker([57.981258,56.294239], {icon:iconmediumpurple}).addTo(mymap);	
var Marker23 = L.marker([58.020495,56.276165], {icon:iconmediumblue}).addTo(mymap);
var Marker24 = L.marker([58.02305,56.316769],  {icon:iconmediumred}).addTo(mymap);
var Marker25 = L.marker([58.026758,56.330504], {icon:iconmediumred}).addTo(mymap);
var Marker26 = L.marker([58.004356,56.305288], {icon:iconmediumred}).addTo(mymap);
var Marker27 = L.marker([58.004094,56.290196], {icon:iconmediumred}).addTo(mymap);
var Marker28 = L.marker([58.062888,56.359448], {icon:iconmediumpurple}).addTo(mymap);
var Marker29 = L.marker([58.014236,56.279102], {icon:iconmediumred}).addTo(mymap);
var Marker30 = L.marker([58.032686,56.308028], {icon:iconmediumpurple}).addTo(mymap);
var Marker31 = L.marker([59.405636,56.79434] , {icon:iconmediumblue}).addTo(mymap);
var Marker32 = L.marker([59.408966,56.823005] , {icon:iconmediumpurple}).addTo(mymap);
var Marker33 = L.marker([59.401688,56.809674] , {icon:iconmediumblue}).addTo(mymap);
var Marker34 = L.marker([59.402128,56.781557] , {icon:iconmediumred}).addTo(mymap);
var Marker35 = L.marker([59.420912,56.813276] , {icon:iconmediumred}).addTo(mymap);
var Marker36 = L.marker([59.407922,56.804015] , {icon:iconmediumpurple}).addTo(mymap);
var Marker37 = L.marker([59.407102,56.818981] , {icon:iconmediumblue}).addTo(mymap);
var Marker38 = L.marker([59.406319,56.843586] , {icon:iconmediumblue}).addTo(mymap);
var Marker39 = L.marker([59.3983203,56.7537957] , {icon:iconmediumpurple}).addTo(mymap);
var Marker40 = L.marker([59.40308,56.832258] , {icon:iconmediumred}).addTo(mymap);
var Marker41 = L.marker([59.423548,56.807365] , {icon:iconmediumred}).addTo(mymap);


var markerList = {
	"0": Marker0,
	"1": Marker1,
	"2": Marker2,
	"3": Marker3,
	"4": Marker4,
	"5": Marker5,
	"6": Marker6,
	"7": Marker7,
	"8": Marker8,
	"9": Marker9,
	"10": Marker10,
	"11": Marker11,
	"12": Marker12,
	"13": Marker13,
	"14": Marker14,
	"15": Marker15,
	"16": Marker16,
	"17": Marker17,
	"18": Marker18,
	"19": Marker19,
	"20": Marker20,
	"21": Marker21,
	"22": Marker22,
	"23": Marker23,
	"24": Marker24,
	"25": Marker25,
	"26": Marker26,
	"27": Marker27,
	"28": Marker28,
	"29": Marker29,
	"30": Marker30,
	"31": Marker31,
	"32": Marker32,
	"33": Marker33,
	"34": Marker34,
	"35": Marker35,
	"36": Marker36,
	"37": Marker37,
	"38": Marker38,
	"39": Marker39,
	"40": Marker40,
	"41": Marker41		
};

function iconZoomed(index, size) {
	switch (markerList[index]._icon.outerHTML.split(" ")[1].substring(22,23)) {
		case "p":
		if (size == 1) markerList[index].setIcon(iconsmallpurple);
		if (size == 2) markerList[index].setIcon(iconmediumpurple);
		if (size == 3) markerList[index].setIcon(iconbigpurple);
		break;
		case "b":
		if (size == 1) markerList[index].setIcon(iconsmallblue);
		if (size == 2) markerList[index].setIcon(iconmediumblue);
		if (size == 3) markerList[index].setIcon(iconbigblue);
		break;
		case "r":
		if (size == 1) markerList[index].setIcon(iconsmallred);
		if (size == 2) markerList[index].setIcon(iconmediumred);
		if (size == 3) markerList[index].setIcon(iconbigred);
		break;
	}
}

mymap.on('zoomend', function(ev) {
	if (mymap.getZoom()>13) {
		for (i = 0; i < 31; i++) {
			iconZoomed(i, 3);
		}
	}
	else if (mymap.getZoom()>10) {			
		for (i = 0; i < 31; i++) {
			iconZoomed(i, 2);
		}	
	}
	else {
		for (i = 0; i < 31; i++) {
			iconZoomed(i, 1);
		}	
	}
})