$(document).ready(function () {

    Array.prototype.sample = function(){
        return this[Math.floor(Math.random()*this.length)];
    } // WIP1, нужно для рандомизации иконок, убрать, когда иконки будут в базе.

    var xhrAddress = new XMLHttpRequest();
    var markersAddress;
    var markerList = new Map();
    xhrAddress.open('GET', 'admin/address', true);
    xhrAddress.send();

    var screenHeight = document.documentElement.clientHeight
        - document.getElementById("footerid").clientHeight
        - document.getElementById("myNavbar").clientHeight - 15 + "px";
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
    }), iconmediumpurple = L.icon({
        iconUrl: 'icons/map/marker_purple_64.png',
        iconSize: [32, 32]
    }), iconsmallpurple = L.icon({
        iconUrl: 'icons/map/marker_purple_64.png',
        iconSize: [16, 16]
    }), iconbigblue = L.icon({
        iconUrl: 'icons/map/marker_blue_64.png',
        iconSize: [64, 64]
    }), iconmediumblue = L.icon({
        iconUrl: 'icons/map/marker_blue_64.png',
        iconSize: [32, 32]
    }), iconsmallblue = L.icon({
        iconUrl: 'icons/map/marker_blue_64.png',
        iconSize: [16, 16]
    }), iconbigred = L.icon({
        iconUrl: 'icons/map/marker_red_64.png',
        iconSize: [64, 64]
    }), iconmediumred = L.icon({
        iconUrl: 'icons/map/marker_red_64.png',
        iconSize: [32, 32]
    }), iconsmallred = L.icon({
        iconUrl: 'icons/map/marker_red_64.png',
        iconSize: [16, 16]
    }), iconempty = L.icon({
        iconUrl: 'icons/map/transparent-square-tiles.png',
        iconSize: [1, 1]
    }); // WIP2, это всё надо в базу.

    xhrAddress.onreadystatechange = function() {
        if (xhrAddress.readyState != 4) return;
        if (xhrAddress.status != 200) {
            // предупредили, если не получили xml
            alert(xhrAddress.status + ': ' + xhrAddress.statusText);
        } else {
            markersAddress = JSON.parse(xhrAddress.responseText);
            for (var i = 0; i < markersAddress.length; i++) {
                if (markersAddress[i].parent != null) {
                    markerList.set(markersAddress[i].id.toString(), //айдишник маркера = айдишник дерева
                        L.marker([markersAddress[i].latitude, markersAddress[i].longitude],
                            {
                                icon:[iconmediumpurple, iconmediumred, iconmediumblue].sample(), // см. WIP1
                                title:markersAddress[i].name
                            }).addTo(mymap).bindPopup(markersAddress[i].description + "<br />" + markersAddress[i].name));
                }
                else {
                    markerList.set(markersAddress[i].id.toString(),
                        L.marker([markersAddress[i].latitude, markersAddress[i].longitude],
                            {
                                icon: iconempty, // города отмечаются маркерами с пустыми иконками
                                title: markersAddress[i].name
                            }).addTo(mymap));
                }
            }
            
            var treeData = [];
            var treeNode = {};
            // пересобираем объект, чтобы jstree нормально его читал, там с этим строго, см jstree.com/docs/json
            for (var i = 0; i < markersAddress.length; i++) {
                treeNode.id = markersAddress[i].id; // айдишник дерева = айдишник маркера
                if (markersAddress[i].parent == null) treeNode.parent = "#";
                else treeNode.parent = markersAddress[i].parent.id;
                treeNode.text = markersAddress[i].name;
                treeData.push(treeNode);
                treeNode = {};
            }

            $('#jstree_demo_div')
                .jstree({
                    core: {
                        data: treeData
                    }
                });
        }
    }

    function iconZoomed(mark) {
        switch (mark._icon.outerHTML.split(" ")[1].substring(22, 23)) {
            case "p":
                if (mymap.getZoom() > 7)  mark.setIcon(iconsmallpurple);
                if (mymap.getZoom() > 10) mark.setIcon(iconmediumpurple);
                if (mymap.getZoom() > 13) mark.setIcon(iconbigpurple);
                break;
            case "b":
                if (mymap.getZoom() > 7)  mark.setIcon(iconsmallblue);
                if (mymap.getZoom() > 10) mark.setIcon(iconmediumblue);
                if (mymap.getZoom() > 13) mark.setIcon(iconbigblue);
                break;
            case "r":
                if (mymap.getZoom() > 7)  mark.setIcon(iconsmallred);
                if (mymap.getZoom() > 10) mark.setIcon(iconmediumred);
                if (mymap.getZoom() > 13) mark.setIcon(iconbigred);
                break;
        }
    }

    mymap.on('zoomend', function (ev) {
        markerList.forEach(function(item) {iconZoomed(item);});
    });

    $('#jstree_demo_div').on("changed.jstree", function (e, data) {
        if (data.node.parent == "#") {
            mymap.setView(markerList.get(data.node.id).getLatLng(), 13);
        }
        else {
            mymap.setView(markerList.get(data.node.id).getLatLng(), 16);
            var popup = L.popup()
                .setLatLng(markerList.get(data.node.id).getLatLng())
                .setContent(markerList.get(data.node.id)._popup._content)
                .openOn(mymap);
        }
    });

    // Сворачиваем все города, кроме того, который выбрали.
    $('#jstree_demo_div').on('open_node.jstree', function (e, data) {
        var nodesToKeepOpen = [];
        $('#'+data.node.id).parents('.jstree-node').each(function() {
            nodesToKeepOpen.push(this.id);
        });
        nodesToKeepOpen.push( data.node.id );
        $('.jstree-node').each( function() {
            if( nodesToKeepOpen.indexOf(this.id) === -1 ) {
                $("#jstree_demo_div").jstree('close_node',this.id);
            }
        })
    });

    $("#search_node").click( function() {
        var searchedNodeId = $("#searchedNodeId").val();
        $('#jstree_demo_div').jstree("select_node", $('#'+searchedNodeId));
    });

});