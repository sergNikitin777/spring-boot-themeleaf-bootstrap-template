$(document).ready(function () {

    Array.prototype.sample = function(){
        return this[Math.floor(Math.random()*this.length)];
    }

    var xhrAddress = new XMLHttpRequest();
    var markersAddress;
    var markerList = new Map();
    xhrAddress.open('GET', 'http://localhost:8080/admin/address', true);
    xhrAddress.send();

    var screenHeight = document.documentElement.clientHeight - document.getElementById("footerid").clientHeight - document.getElementById("myNavbar").clientHeight - 15 + "px";
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
        iconSize: [64, 64]
    });

    var iconmediumpurple = L.icon({
        iconUrl: 'icons/map/marker_purple_64.png',
        iconSize: [32, 32]
    });

    var iconsmallpurple = L.icon({
        iconUrl: 'icons/map/marker_purple_64.png',
        iconSize: [16, 16]
    });

    var iconbigblue = L.icon({
        iconUrl: 'icons/map/marker_blue_64.png',
        iconSize: [64, 64]
    });

    var iconmediumblue = L.icon({
        iconUrl: 'icons/map/marker_blue_64.png',
        iconSize: [32, 32]
    });

    var iconsmallblue = L.icon({
        iconUrl: 'icons/map/marker_blue_64.png',
        iconSize: [16, 16]
    });

    var iconbigred = L.icon({
        iconUrl: 'icons/map/marker_red_64.png',
        iconSize: [64, 64]
    });

    var iconmediumred = L.icon({
        iconUrl: 'icons/map/marker_red_64.png',
        iconSize: [32, 32]
    });

    var iconsmallred = L.icon({
        iconUrl: 'icons/map/marker_red_64.png',
        iconSize: [16, 16]
    });

    var iconempty = L.icon({
        iconUrl: 'icons/map/transparent-square-tiles.png',
        iconSize: [1, 1]
    });

    xhrAddress.onreadystatechange = function() {
        if (xhrAddress.readyState != 4) return;
        if (xhrAddress.status != 200) {
            alert(xhrAddress.status + ': ' + xhrAddress.statusText);
        } else {
            markersAddress = JSON.parse(xhrAddress.responseText);
            for (var i = 0; i < markersAddress.length; i++) {
                if (markersAddress[i].parent != null) {
                    markerList.set(markersAddress[i].id.toString(),
                        L.marker([markersAddress[i].latitude, markersAddress[i].longitude],
                            {
                                icon:[iconmediumpurple, iconmediumred, iconmediumblue].sample(),
                                title:markersAddress[i].name
                            }).addTo(mymap));
                }
                else {
                    markerList.set(markersAddress[i].id.toString(),
                        L.marker([markersAddress[i].latitude, markersAddress[i].longitude],
                            {
                                icon: iconempty,
                                title: markersAddress[i].name
                            }).addTo(mymap));
                }
            }

            var treeData = [];
            var treeNode = {};
            for (var i = 0; i < markersAddress.length; i++) {
                treeNode.id = markersAddress[i].id;
                if (markersAddress[i].parent == null) treeNode.parent = "#";
                else treeNode.parent = markersAddress[i].parent.id;
                treeNode.text = markersAddress[i].name;
                treeData.push(treeNode);
                treeNode = {};
            }

            console.log(treeData);

            $('#jstree_demo_div')
                .jstree({
                    core: {
                        data: treeData
                    }
                });
        }
    }

    /*function iconZoomed(index, size) {
        switch (markerList.get(index)._icon.outerHTML.split(" ")[1].substring(22, 23)) {
            case "p":
                if (size == 1) markerList.get(index).setIcon(iconsmallpurple);
                if (size == 2) markerList.get(index).setIcon(iconmediumpurple);
                if (size == 3) markerList.get(index).setIcon(iconbigpurple);
                break;
            case "b":
                if (size == 1) markerList.get(index).setIcon(iconsmallblue);
                if (size == 2) markerList.get(index).setIcon(iconmediumblue);
                if (size == 3) markerList.get(index).setIcon(iconbigblue);
                break;
            case "r":
                if (size == 1) markerList.get(index).setIcon(iconsmallred);
                if (size == 2) markerList.get(index).setIcon(iconmediumred);
                if (size == 3) markerList.get(index).setIcon(iconbigred);
                break;
        }
    }

    mymap.on('zoomend', function (ev) {
        if (mymap.getZoom() > 13) {
            for (i = 0; i < markerList.size; i++) {
                iconZoomed(i, 3);
            }
        }
        else if (mymap.getZoom() > 10) {
            for (i = 0; i < markerList.size; i++) {
                iconZoomed(i, 2);
            }
        }
        else {
            for (i = 0; i < markerList.size; i++) {
                iconZoomed(i, 1);
            }
        }
    });*/

    $('#jstree_demo_div').on("changed.jstree", function (e, data) {
        if (data.node.parent == "#") {
            mymap.setView(markerList.get(data.node.id).getLatLng(), 13);
        }
        else mymap.setView(markerList.get(data.node.id).getLatLng(), 16);
    });
});