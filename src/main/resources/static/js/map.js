$(document).ready(function () {

    $('.leftmenutrigger').on('click', function(e) {
        $('.side-nav').toggleClass("open");
        e.preventDefault();
    });

    Array.prototype.sample = function(){
        return this[Math.floor(Math.random()*this.length)];
    }; // WIP1, нужно для рандомизации иконок, убрать, когда иконки будут в базе.

    var xhrAddress = new XMLHttpRequest();
    var markersAddress;
    var markerList = new Map();
    xhrAddress.open('GET', 'admin/address', true);
    xhrAddress.send();

    // костыль для лифлета, нужно вручную выставлять высоту карты
    if (document.getElementById("mapid") != null) {
        function toggleMapHeight() {
            var screenHeight = document.documentElement.clientHeight
                - document.getElementById("footerid").clientHeight
                - document.getElementById("navbarid").clientHeight - 20 +  "px";
            document.getElementById("mapid").style.height = screenHeight;
            document.getElementById('mapid').style.marginTop = document.getElementById("navbarid").clientHeight/3 + "px";
        }

        toggleMapHeight(); // в первый дёргаем раз при построении окна
        $(window).resize(function () {
            toggleMapHeight(); // потом при каждом растяжении окна
            setTimeout(function () {
                toggleMapHeight();
            }, 100); //для медлительных дёргаем дважды
        });


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
    }
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
            if (document.getElementById('mapid') != null)
            {
                for (var i = 0; i < markersAddress.length; i++) {
                    if (markersAddress[i].parent != null) {
                        markerList.set(markersAddress[i].id.toString(), //айдишник маркера = айдишник дерева
                            L.marker([markersAddress[i].latitude, markersAddress[i].longitude],
                                {
                                    icon: [iconmediumpurple, iconmediumred, iconmediumblue].sample(), // см. WIP1
                                    title: markersAddress[i].name
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
            }
            var treeData = [];
            var treeNode = {};
            // пересобираем объект, чтобы jstree нормально его читал, там с этим строго, см jstree.com/docs/json
            for (var i = 0; i < markersAddress.length; i++) {
                treeNode.id = markersAddress[i].id; // айдишник дерева = айдишник маркера
                if (markersAddress[i].parent == null) {
                    treeNode.parent = "#";
                    treeNode.text = "\t&#x26EA; " + markersAddress[i].name; // Отмечаем отдельно города
                }
                else {
                    treeNode.parent = markersAddress[i].parent.id;
                    treeNode.text = markersAddress[i].name;
                }
                treeData.push(treeNode);
                treeNode = {};
            }

            $('#jstree_demo_div')
                .jstree({
                    plugins: ["addHTML"],
                    core: {
                        'themes': {
                            'name': 'proton',
                            'responsive': true,
                            'icons': false
                        },
                        data: treeData
                    }
                });
        }
    };

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
    if (mymap != null) {
        mymap.on('zoomend', function (ev) {
            markerList.forEach(function (item) {
                iconZoomed(item);
            });
        });
    }

    $.jstree.plugins.addHTML = function (options, parent) {
        this.redraw_node = function(obj, deep,
                                    callback, force_draw) {
            obj = parent.redraw_node.call(
                this, obj, deep, callback, force_draw
            );
            if (obj) {
                var node = this.get_node(jQuery(obj).attr('id'));
                if (node &&
                    node.data &&
                    ( "addHTML" in node.data ) ) {
                    jQuery(obj).append(
                        "<div style='margin-left: 50px'>" +
                        node.data.addHTML +
                        "</div>"
                    );
                }
            }
            return obj;
        };
    };

    $.jstree.defaults.addHTML = {};

    $('#jstree_demo_div').on("changed.jstree", function (e, data) {
        if (mymap != null) {
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

    $('.nav-tabs > li a[title]').tooltip();

    //Wizard
    $('a[data-toggle="tab"]').on('show.bs.tab', function (e) {

        var $target = $(e.target);

        if ($target.parent().hasClass('disabled')) {
            return false;
        }
    });

    $(".next-step").click(function (e) {

        var $active = $('.wizard .nav-tabs li.active');
        $active.next().removeClass('disabled');
        nextTab($active);

    });
    $(".prev-step").click(function (e) {

        var $active = $('.wizard .nav-tabs li.active');
        prevTab($active);

    });

    $('.header').click(function(){
        $(this).toggleClass('expand').nextUntil('tr.header').slideToggle(100);
    });

    $('.btn-expand').click (function() {
        var all = $('.header'),
            active = all.filter('.expand');

        if (all.length && all.length === active.length) {
            // All open; close them
            all.removeClass('expand').next().slideToggle(100);
        }
        else {
            // At least some are closed, open all
            all.not('.expand').addClass('expand').next().slideToggle(100);
        }
    });

    $('.header').toggleClass('expand').nextUntil('tr.header').slideToggle(100);

    $('.spinnable').hover(
        function(){ $(this).addClass('fa-spin') },
        function(){ $(this).removeClass('fa-spin') }
    )

    $('.choseone').on('click', 'li', function() {
        $('.choseone li.active').removeClass('active');
        $(this).addClass('active');
    });
});

function nextTab(elem) {
    $(elem).next().find('a[data-toggle="tab"]').click();
}
function prevTab(elem) {
    $(elem).prev().find('a[data-toggle="tab"]').click();
}