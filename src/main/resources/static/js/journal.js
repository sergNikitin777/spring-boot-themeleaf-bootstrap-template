// !!! РАЗБИТЬ НА МОДУЛИ !!!
$(document).ready(function () {
    var options                     = { year: 'numeric', month: 'long', day: 'numeric', timeZone: '' };
    var xhrCalendarActualEvents     = new XMLHttpRequest();
    var xhrCalendarRemovedEvents    = new XMLHttpRequest();
    var xhrAddEvent                 = new XMLHttpRequest();
    var xhrDeleteEvent              = new XMLHttpRequest();
    var xhrShowDrivers              = new XMLHttpRequest();
    var xhrShowCars                 = new XMLHttpRequest();
    var carsList                    = new Map();
    var driversList                 = new Map();
    var eventList                   = new Map();
    var eventListR                  = new Map();
    var currentDate                 = new Date();
    var idToAmend;
    var year                        = currentDate.getFullYear();
    var month                       = currentDate.getMonth() + 1;
    var day                         = currentDate.getDate();
    $('#calendar').attr("src", "https://calendar.yandex.ru/day?uid=641146316&show_date="+year+"-"+month+"-"+day+"&embed=&private_token=cf54ec9c0ec6b90948d04f8d9ddc71462918b926&tz_id=Asia%2FYekaterinburg");

    toggleCalendarHeight();
    toggleTables();
    updateTable("month");
    updateSettings();

    function toggleTables() {
        if($(window).width() < 991) {
            $('#tablefull').hide();
            $('#tabledriversfull').hide();
            $('#tablecarsfull').hide();
            $('#tablemobile').show();
            $('#tabledriversmobile').show();
            $('#tablecarsmobile').show();
        }
        else {
            $('#tablefull').show();
            $('#tabledriversfull').show();
            $('#tablecarsfull').show();
            $('#tablemobile').hide();
            $('#tabledriversmobile').hide();
            $('#tablecarsmobile').hide();
        };
    }
    function toggleCalendarHeight() {
        var screenHeight = document.documentElement.clientHeight
            - 60 +  "px";
        document.getElementById("calendar").style.height = screenHeight;
    }

    function amendEventById(id) {
        var postJSON = {
            "calPostfix": "/events-5825759",
            "calPrefix": "/calendars/",
            "caldavHost": "caldav.yandex.ru",
            "caldavPort": 443,
            "password": "kjubcn123456",
            "protocol": "https",
            "userName": "logistikatest@yandex.ru"
        };
        var postJSONtoRemove = {
            "calPostfix": "/events-5825759",
            "calPrefix": "/calendars/",
            "caldavHost": "caldav.yandex.ru",
            "caldavPort": 443,
            "password": "kjubcn123456",
            "protocol": "https",
            "userName": "logistikatest@yandex.ru",
            "uid": id
        };

        var dateandtime         = $('#InputTimeamend').val();
        var dateandtimeLocal    = new Date(dateandtime.substr(6, 4), dateandtime.substr(3, 2) - 1, dateandtime.substr(0, 2),
            dateandtime.substr(11, 2), dateandtime.substr(14, 2));
        var summary             = $('#InputClientamend').val();
        var location            = $('#InputAddressamend').val();
        var description         = 'Водитель ' + $('#InputDriveramend').val() + '; ' + $('#InputCaramend').val() + '; ' +
            $('#InputGofersamend').val() + ' грузчик(ов); ' + $('#InputSumamend').val() + ' рублей; ' + $('#descriptionamend').val();

        postJSON.startDate = dateandtimeLocal;
        postJSON.durationHours = 1;
        postJSON.durationMinutes = 0;
        postJSON.eventName = summary;
        postJSON.eventDescription = description;
        postJSON.eventLocation = location;

        xhrAddEvent.open('POST','calendar/addvevent', true);
        xhrAddEvent.setRequestHeader("Content-type", "application/json");
        xhrAddEvent.send(JSON.stringify(postJSON));
        xhrAddEvent.onreadystatechange = function () {
            if (xhrAddEvent.readyState == XMLHttpRequest.DONE && xhrAddEvent.status == 200) {
                deleteEvent(postJSONtoRemove);
            }
        };
    }

    function addEvent(){
        var postJSON = {
            "calPostfix": "/events-5825759",
            "calPrefix": "/calendars/",
            "caldavHost": "caldav.yandex.ru",
            "caldavPort": 443,
            "password": "kjubcn123456",
            "protocol": "https",
            "userName": "logistikatest@yandex.ru"
        };
        var dateandtime         = $('#InputTime').val();
        var dateandtimeLocal    = new Date(dateandtime.substr(6, 4), dateandtime.substr(3, 2) - 1, dateandtime.substr(0, 2),
            dateandtime.substr(11, 2), dateandtime.substr(14, 2));
        var summary             = $('#InputClient').val();
        var location            = $('#InputAddress').val();
        var description         = 'Водитель ' + $('#InputDriver').val() + '; ' + $('#InputCar').val() + '; ' +
            $('#InputGofers').val() + ' грузчик(ов); ' + $('#InputSum').val() + ' рублей; ' + $('#description').val();

        postJSON.startDate = dateandtimeLocal;
        postJSON.durationHours = 1;
        postJSON.durationMinutes = 0;
        postJSON.eventName = summary;
        postJSON.eventDescription = description;
        postJSON.eventLocation = location;

        xhrAddEvent.open('POST', 'calendar/addvevent', true);
        xhrAddEvent.setRequestHeader("Content-type", "application/json");
        xhrAddEvent.send(JSON.stringify(postJSON));
        xhrAddEvent.onreadystatechange = function () {
            if (xhrAddEvent.readyState == XMLHttpRequest.DONE && xhrAddEvent.status == 200) {
                $('#view').tab('show');
                updateTable('month');
            }
        };
    }

    function removeEvent(id) {
        var postJSONtoAdd = {
            "calPostfix": "/events-5903307",
            "calPrefix": "/calendars/",
            "caldavHost": "caldav.yandex.ru",
            "caldavPort": 443,
            "password": "kjubcn123456",
            "protocol": "https",
            "userName": "logistikatest@yandex.ru"
        };
        var postJSONtoRemove = {
            "calPostfix": "/events-5825759",
            "calPrefix": "/calendars/",
            "caldavHost": "caldav.yandex.ru",
            "caldavPort": 443,
            "password": "kjubcn123456",
            "protocol": "https",
            "userName": "logistikatest@yandex.ru",
            "uid": id
        };

        for (var i = 0; i < eventList.length; i++) {
            if (eventList[i].uid.value == id) {
                var dt = new Date(eventList[i].startDate.date);
                var summary = eventList[i].summary.value;
                var description = eventList[i].description.value;
                var locationvalue;
                if (eventList[i].location == null) locationvalue = null;
                else locationvalue = eventList[i].location.value;
                postJSONtoAdd.startDate = dt;
                postJSONtoAdd.durationHours = 1;
                postJSONtoAdd.durationMinutes = 0;
                postJSONtoAdd.eventName = summary;
                postJSONtoAdd.eventDescription = description;
                postJSONtoAdd.eventLocation = locationvalue;
                xhrAddEvent.open('POST', 'calendar/addvevent', true);
                xhrAddEvent.setRequestHeader("Content-type", "application/json");
                xhrAddEvent.send(JSON.stringify(postJSONtoAdd));
                xhrAddEvent.onreadystatechange = function () {
                    if (xhrAddEvent.readyState == XMLHttpRequest.DONE && xhrAddEvent.status == 200) {
                        updateTable('month');
                    }
                };
                break;
            }
        }

        deleteEvent(postJSONtoRemove);
    }

    function rebuildEvent(id) {
        var postJSONtoAdd = {
            "calPostfix": "/events-5825759",
            "calPrefix": "/calendars/",
            "caldavHost": "caldav.yandex.ru",
            "caldavPort": 443,
            "password": "kjubcn123456",
            "protocol": "https",
            "userName": "logistikatest@yandex.ru"
        };
        var postJSONtoRemove = {
            "calPostfix": "/events-5903307",
            "calPrefix": "/calendars/",
            "caldavHost": "caldav.yandex.ru",
            "caldavPort": 443,
            "password": "kjubcn123456",
            "protocol": "https",
            "userName": "logistikatest@yandex.ru",
            "uid": id
        };

        for (var i = 0; i < eventListR.length; i++) {
            if (eventListR[i].uid.value == id) {
                var dt = new Date(eventListR[i].startDate.date);
                var summary = eventListR[i].summary.value;
                var description = eventListR[i].description.value;
                var locationvalue;
                if (eventListR[i].location == null) locationvalue = null;
                else locationvalue = eventListR[i].location.value;
                postJSONtoAdd.startDate = dt;
                postJSONtoAdd.durationHours = 1;
                postJSONtoAdd.durationMinutes = 0;
                postJSONtoAdd.eventName = summary;
                postJSONtoAdd.eventDescription = description;
                postJSONtoAdd.eventLocation = locationvalue;
                xhrAddEvent.open('POST', 'calendar/addvevent', true);
                xhrAddEvent.setRequestHeader("Content-type", "application/json");
                xhrAddEvent.send(JSON.stringify(postJSONtoAdd));
                xhrAddEvent.onreadystatechange = function () {
                    if (xhrAddEvent.readyState == XMLHttpRequest.DONE && xhrAddEvent.status == 200) {
                        updateTable('month');
                    }
                };
                break;
            }
        }

        deleteEvent(postJSONtoRemove);
    }

    function deleteEvent(postJSON) {
        xhrDeleteEvent.open('POST', 'calendar/delvevent', true);
        xhrDeleteEvent.setRequestHeader("Content-type", "application/json");
        xhrDeleteEvent.send(JSON.stringify(postJSON));
        xhrDeleteEvent.onreadystatechange = function () {
            if (xhrDeleteEvent.readyState == XMLHttpRequest.DONE && xhrDeleteEvent.status == 200) {
                updateTable('month');
            }
        };
    }

    function updateSettings() {
        $("#tablecarsfullbody").html("");
        $("#tablecarsfull").faLoading({icon: "fa-refresh", spin: true});
        $("#tablecarsmobilebody").html("");
        $("#tablecarsmobile").faLoading({icon: "fa-refresh", spin: true});
        $("#tabledriversfullbody").html("");
        $("#tabledriversfull").faLoading({icon: "fa-refresh", spin: true});
        $("#tabledriversmobilebody").html("");
        $("#tabledriverssmobile").faLoading({icon: "fa-refresh", spin: true});
        xhrShowCars.open('GET', 'admin/autos', true);
        xhrShowCars.send();
        xhrShowCars.onreadystatechange = function() {
            if (xhrShowCars.readyState == XMLHttpRequest.DONE && xhrShowCars.status == 200) {
                carsList = JSON.parse(xhrShowCars.responseText);
                console.log(carsList);
                for (var i = 0; i < carsList.length; i++) {
                    var rowfull = $('<tr class="normal" id="' + carsList[i].id + '">');
                    var rowmobile = $('<tr class="normal row-header expand">');
                    var mobileinfo = $('<tr class="normal" id="' + carsList[i].id + '">');

                    rowfull.append('<td class = "marc">' + carsList[i].mark + '</td>'); // FYI: класс mark в LESS уже занят
                    rowfull.append('<td class = "model">' + carsList[i].model + '</td>');
                    rowfull.append('<td class = "lisenceplate">' + carsList[i].licensePlate + '</td>');
                    rowfull.append('<td class = "type">' + carsList[i].type + '</td>');
                    rowfull.append('<td class = "buttons"><a class="btn btn-sm editcar"><i class="fa fa-edit"></i></a>&nbsp;' +
                        '<a class="btn btn-sm removecar"><i class="fa fa-trash-o"></i></a></td>');
                    $("#tablecarsfullbody").append(rowfull);

                    rowmobile.append('<td class="lisenceplate" colspan="2">' + carsList[i].licensePlate + '</td>');
                    $("#tablecarsmobilebody").append(rowmobile);

                    mobileinfo.append('<td><div>Марка: '+ carsList[i].mark +'</div>' +
                        '<div>Модель: ' + carsList[i].model + '</div>' +
                        '<div>Класс: ' + carsList[i].type + '</div></td>');
                    mobileinfo.append('<td class = "buttons"><a class="btn editcar"><i class="fa fa-edit"></i>&nbsp;Изменить</a>&nbsp;' +
                        '<a class="btn removecar"><i class="fa fa-trash-o"></i>&nbsp;Удалить</a></td>');
                    $("#tablecarsmobilebody").append(mobileinfo);
                }
                $('#tablecarsfull').faLoading(false);
                $('#tablecarsmobile').faLoading(false);
            }
        }
        xhrShowDrivers.open('GET', 'admin/auto/drivers', true);
        xhrShowDrivers.send();
        xhrShowDrivers.onreadystatechange = function() {
            if (xhrShowDrivers.readyState == XMLHttpRequest.DONE && xhrShowDrivers.status == 200) {
                driversList = JSON.parse(xhrShowDrivers.responseText);
                console.log(driversList);
                $('#tabledriversfull').faLoading(false);
                $('#tabledriverssmobile').faLoading(false);
            }
        }
    }

    function updateTable (viewpoint) {
        $("#tablefullbody").html("");
        $("#tablefull").faLoading({icon: "fa-refresh", spin: true});
        $("#tablemobilebody").html("");
        $("#tablemobile").faLoading({icon: "fa-refresh", spin: true});
        var limitms = new Date();
        switch (viewpoint) {
            case "day":   {
                limitms.setDate(limitms.getDate() + 1);
                break;
            }
            case "week":  {
                limitms.setDate(limitms.getDate() + 7);
                break;
            }
            case "month": {

                limitms.setMonth(limitms.getMonth() + 1);
                break;
            }
        }

        xhrCalendarActualEvents.open('POST', 'calendar/eventsbyparam', true);
        xhrCalendarActualEvents.setRequestHeader("Content-type", "application/json");
        xhrCalendarActualEvents.onreadystatechange = function () {
            if (xhrCalendarActualEvents.readyState == XMLHttpRequest.DONE && xhrCalendarActualEvents.status == 200) {
                eventList = JSON.parse(xhrCalendarActualEvents.responseText);
                eventList = eventList.sort(function (a, b) {
                    if (a.startDate.date < b.startDate.date) return -1;
                    if (a.startDate.date > b.startDate.date) return 1;
                    if (a.startDate.date == b.startDate.date) return 0;
                });
                for (var i = 0; i < eventList.length; i++) {
                    if ((eventList[i].description != null) && (new Date(eventList[i].startDate.date) < limitms) && (new Date(eventList[i].startDate.date) > currentDate)) {
                        var rowfull = $('<tr class="normal" id="' + eventList[i].uid.value + '">');
                        var rowmobile = $('<tr class="normal row-header expand">');
                        var mobileinfo = $('<tr class="normal" id="' + eventList[i].uid.value + '">');

                        var dtLocalized = new Date(eventList[i].startDate.date).toLocaleString('ru-RU', {
                            year: 'numeric', month: 'long', day: 'numeric',
                            hour: 'numeric', minute: 'numeric'
                        });
                        var description = ('' + eventList[i].description.value).split(';');
                        var locationvalue = '';
                        if (eventList[i].location == null) locationvalue = 'Не указан';
                        else locationvalue = eventList[i].location.value.split(',');

                        rowfull.append('<td class = "datetime">' + dtLocalized + '</td>');
                        rowfull.append('<td class = "name">' + eventList[i].summary.value + '</td>');
                        rowfull.append('<td class = "location">' + locationvalue[locationvalue.length - 3] + ',' +locationvalue[locationvalue.length - 2] + ',' + locationvalue[locationvalue.length - 1] + '</td>');
                        rowfull.append('<td class = "driver">' + description[0].split(' ')[1] + '</td>');
                        rowfull.append('<td class = "car">' + description[1] + '</td>');
                        rowfull.append('<td class = "gofers">' + description[2].split(' ')[1] + '</td>');
                        rowfull.append('<td class = "sum">' + description[3].split(' ')[1] + '</td>');
                        rowfull.append('<td class = "description">' + description[4] + '</td>');
                        rowfull.append('<td class = "buttons"><a class="btn btn-sm edit"><i class="fa fa-edit"></i></a>&nbsp;' +
                            '<a class="btn btn-sm remove"><i class="fa fa-trash-o"></i></a></td>');
                        $("#tablefullbody").append(rowfull);

                        rowmobile.append('<td class="datetime">' + dtLocalized + '</td>');
                        rowmobile.append('<td class="location">' + locationvalue[locationvalue.length - 3] + ',' +locationvalue[locationvalue.length - 2] + ',' + locationvalue[locationvalue.length - 1] +  '</td>');
                        $("#tablemobilebody").append(rowmobile);

                        mobileinfo.append('<td><div>Клиент: '+ eventList[i].summary.value +'</div>' +
                            '<div>Водитель: ' + description[0].split(' ')[1] + '</div>' +
                            '<div>Машина: ' + description[1] + '</div>' +
                            '<div>Грузчики: ' + description[2].split(' ')[1] + '</div>' +
                            '<div>Сумма заказа: ' + description[3].split(' ')[1] + '</div>' +
                            '<div>Описание: ' + description[4] + '</div></td>');
                        mobileinfo.append('<td class = "buttons"><a class="btn edit"><i class="fa fa-edit"></i>&nbsp;Изменить</a>&nbsp;' +
                            '<a class="btn remove"><i class="fa fa-trash-o"></i>&nbsp;Удалить</a></td>');
                        $("#tablemobilebody").append(mobileinfo);
                    }
                }
                xhrCalendarRemovedEvents.open('POST', 'calendar/eventsbyparam', true);
                xhrCalendarRemovedEvents.setRequestHeader("Content-type", "application/json");
                xhrCalendarRemovedEvents.onreadystatechange = function () {
                    if (xhrCalendarRemovedEvents.readyState == XMLHttpRequest.DONE && xhrCalendarRemovedEvents.status == 200) {
                        eventListR = JSON.parse(xhrCalendarRemovedEvents.responseText);
                        eventListR = eventListR.sort(function (a, b) {
                            if (a.startDate.date < b.startDate.date) return -1;
                            if (a.startDate.date > b.startDate.date) return 1;
                            if (a.startDate.date == b.startDate.date) return 0;
                        });

                        for (var i = 0; i < eventListR.length; i++) {
                            if ((eventListR[i].description != null) && (new Date(eventListR[i].startDate.date) < limitms) && (new Date(eventListR[i].startDate.date) > currentDate)) {
                                var rowfull = $('<tr class="strikeout" id="' + eventListR[i].uid.value + '">');
                                var rowmobile = $('<tr class="strikeout row-header expand" >');
                                var mobileinfo = $('<tr class="normal" id="' + eventList[i].uid.value + '">');

                                var dtLocalized = new Date(eventListR[i].startDate.date).toLocaleString('ru-RU', {
                                    year: 'numeric', month: 'long', day: 'numeric',
                                    hour: 'numeric', minute: 'numeric'
                                });
                                var description = ('' + eventListR[i].description.value).split(';');
                                var locationvalue = '';
                                if (eventListR[i].location == null) locationvalue = 'Не указан';
                                else locationvalue = eventListR[i].location.value.split(',');

                                rowfull.append('<td class = "datetime">' + dtLocalized + '</td>');
                                rowfull.append('<td class = "name">' + eventListR[i].summary.value + '</td>');
                                rowfull.append('<td class = "location">' + locationvalue[locationvalue.length - 3] + ',' +locationvalue[locationvalue.length - 2] + ',' + locationvalue[locationvalue.length - 1] + '</td>');
                                rowfull.append('<td class = "driver">' + description[0].split(' ')[1] + '</td>');
                                rowfull.append('<td class = "car">' + description[1] + '</td>');
                                rowfull.append('<td class = "gofers">' + description[2].split(' ')[1] + '</td>');
                                rowfull.append('<td class = "sum">' + description[3].split(' ')[1] + '</td>');
                                rowfull.append('<td class = "description">' + description[4] + '</td>');
                                rowfull.append('<td class = "buttons"><a class="btn btn-sm rebuild"><i class="fa fa-undo"></i></a>&nbsp;' +
                                    '<a class="btn btn-sm btn-danger delete" style="background-color: red"><i class="fa fa-trash-o"></i></a></td>');
                                $("#tablefullbody").append(rowfull);

                                rowmobile.append('<td class="datetime">' + dtLocalized + '</td>');
                                rowmobile.append('<td class="location">' + locationvalue[locationvalue.length - 3] + ',' +locationvalue[locationvalue.length - 2] + ',' + locationvalue[locationvalue.length - 1] +  '</td>');
                                $("#tablemobilebody").append(rowmobile);

                                mobileinfo.append('<td><div>Клиент: '+ eventListR[i].summary.value +'</div>' +
                                    '<div>Водитель: ' + description[0].split(' ')[1] + '</div>' +
                                    '<div>Машина: ' + description[1] + '</div>' +
                                    '<div>Грузчики: ' + description[2].split(' ')[1] + '</div>' +
                                    '<div>Сумма заказа: ' + description[3].split(' ')[1] + '</div>' +
                                    '<div>Описание: ' + description[4] + '</div></td>');
                                mobileinfo.append('<td class = "buttons"><a class="btn rebuild"><i class="fa fa-undo"></i>&nbsp;Восстановить</a>&nbsp;' +
                                    '<a class="btn btn-danger delete" style="background-color: red"><i class="fa fa-trash-o"></i>&nbsp;Удалить</a></td>');
                                $("#tablemobilebody").append(mobileinfo);
                            }
                        }
                        $('.row-header').toggleClass('expand').nextUntil('tr.row-header').slideToggle(100);
                        $('#tablefull').faLoading(false);
                        $('#tablemobile').faLoading(false);
                    }
                };

                xhrCalendarRemovedEvents.send(JSON.stringify({
                    "calPostfix": "/events-5903307",
                    "calPrefix": "/calendars/",
                    "caldavHost": "caldav.yandex.ru",
                    "caldavPort": 443,
                    "password": "kjubcn123456",
                    "protocol": "https",
                    "userName": "logistikatest@yandex.ru"
                }));
            }
        };

        xhrCalendarActualEvents.send(JSON.stringify({
            "calPostfix": "/events-5825759",
            "calPrefix": "/calendars/",
            "caldavHost": "caldav.yandex.ru",
            "caldavPort": 443,
            "password": "kjubcn123456",
            "protocol": "https",
            "userName": "logistikatest@yandex.ru"
        }));
    }

    // плагины ввода
    $(function () {
        $('#InputAddress').kladr({
            oneString: true
        });
        $('#InputAddressamend').kladr({
            oneString: true
        });
        $('#InputRegion').kladr({
            type: $.kladr.type.region
        });
        $('#datetimepicker').datetimepicker({
            locale: 'ru',
            sideBySide: true
        });
        $('#datetimepickeramend').datetimepicker({
            locale: 'ru',
            sideBySide: true
        });
    });

    var slideCount = $('#slider ul li').length;
    var slideWidth = $('#slider ul li').width();
    var slideHeight = $('#slider ul li').height();
    var sliderUlWidth = slideCount * slideWidth;

    $('#slider').css({ width: slideWidth, height: slideHeight });

    $('#slider ul').css({ width: sliderUlWidth, marginLeft: - slideWidth });

    $('#slider ul li:last-child').prependTo('#slider ul');

    function moveLeft() {
        $('#slider ul').animate({
            left: + slideWidth
        }, 200, function () {
            $('#slider ul li:last-child').prependTo('#slider ul');
            $('#slider ul').css('left', '');
        });
    };

    function moveRight() {
        $('#slider ul').animate({
            left: - slideWidth
        }, 200, function () {
            $('#slider ul li:first-child').appendTo('#slider ul');
            $('#slider ul').css('left', '');
        });
    };

    $('a.control_prev').click(function () {
        moveLeft();
    });

    $('a.control_next').click(function () {
        moveRight();
    });

    // Пользовательские события
    $(document).on('click', '.row-header' , function(){
        $(this).toggleClass('expand').nextUntil('tr.row-header').slideToggle(100);
    });

    $('.navbtn').on('click', function () {
        $('.navbar-toggle').click();
    });
    $('.btn').on('click', function () {
        $(this).blur();
    });

    $(document).on('click', '.remove', function() {
        var button = $(this).parent();
        if ((button.parent()[0].id != '') && (button.parent()[0].id != null)) {
            if (confirm("Удалить заявку?")) {
                removeEvent('' + button.parent()[0].id);
            }
        }
    });

    $(document).on('click', '.delete', function() {
        var button = $(this).parent();
        if ((button.parent()[0].id != '') && (button.parent()[0].id != null)) {
            if (confirm("Удалить заявку окончательно?")) {
                deleteEvent({
                    "calPostfix": "/events-5903307",
                    "calPrefix": "/calendars/",
                    "caldavHost": "caldav.yandex.ru",
                    "caldavPort": 443,
                    "password": "kjubcn123456",
                    "protocol": "https",
                    "userName": "logistikatest@yandex.ru",
                    "uid": button.parent()[0].id
                });
            }
        }
    });

    $(document).on('click', '.rebuild', function() {
        var button = $(this).parent();
        if ((button.parent()[0].id != '') && (button.parent()[0].id != null)) {
            if (confirm("Восстановить заявку?")) {
                rebuildEvent('' + button.parent()[0].id);
            }
        }
    });

    $('.modal').modal({
        dismissible: true
    });

    $(document).on('click', '.edit', function() {
        idToAmend = $(this).parent().parent()[0].id;
        $('#modal_edit').modal("open");
        for (var i = 0; i < eventList.length; i++) {
            if (eventList[i].uid.value == idToAmend) {
                var description = ('' + eventList[i].description.value).split(';');
                var dateString = new Date(eventList[i].startDate.date).toLocaleString('ru-RU', {
                    year: 'numeric', month: 'numeric', day: 'numeric',
                    hour: 'numeric', minute: 'numeric'
                }).split(',');
                $('#InputTimeamend').val(dateString[0]+dateString[1]);
                $('#InputClientamend').val(eventList[i].summary.value);
                if (eventList[i].location != null) {
                    $('#InputAddressamend').val(eventList[i].location.value);
                }
                $('#InputDriveramend').val(description[0].split(' ')[1]);
                $('#InputCaramend').val(description[1]);
                $('#InputGofersamend').val(description[2].split(' ')[1]);
                $('#InputSumamend').val(description[3].split(' ')[1]);
                $('#descriptionamend').val(description[4]);
            }
        }

        $('#Amend').prop('disabled', true);
    });

    $(document).on('click', '.addbtn', function() {
        $('#InputNotification :first').prop('selected', true);
        $('#notifyDriver').prop('checked', false);
        $('#notifyClient').prop('checked', false);
        $('#InputTime').val('');
        $('#InputClient').val('');
        $('#InputAddres').val('');
        $('#InputDriver').val('');
        $('#InputCar').val('');
        $('#InputGofers').val('');
        $('#InputSum').val('');
        $('#description').val('');

        $('#Send').prop('disabled', true);
    });

    function buttonInvisible(button) {
        button.parent().parent().attr('style', 'visibility: hidden; display: none');
    }

    function buttonVisible(button) {
        button.parent().parent().attr('style', 'visibility: visible; display: block');
    }

    buttonInvisible($('#addCar'));
    buttonInvisible($('#addDriver'));
    buttonInvisible($('.addbtn'));
    buttonInvisible($('.viewbtn'));
    buttonInvisible($('.viewclndrbtn'));
    buttonInvisible($('.settingsbtn'));

    $('#viewcalendar,#viewcalendar_block,#settings,#settings_block,#view,#view_block').on('click', function() {
        buttonInvisible($('#addCar'));
        buttonInvisible($('#addDriver'));
    });

    $('#buttonprofile').on('click', function() {
        $('#panel_settings').tab('show');
        buttonInvisible($('#addCar'));
        buttonInvisible($('#addDriver'));
    });

    $('#buttondrivers').on('click', function() {
        $('#panel_settings').tab('show');
        buttonInvisible($('#addCar'));
        buttonVisible($('#addDriver'));
    });

    $('#buttoncars').on('click', function() {
        $('#panel_settings').tab('show');
        buttonVisible($('#addCar'));
        buttonInvisible($('#addDriver'));
    });

    if ($('#panel_login').hasClass('active')) {
        buttonInvisible($('.addbtn'));
        buttonInvisible($('.viewbtn'));
        buttonInvisible($('.viewclndrbtn'));
        buttonInvisible($('.settingsbtn'));
    }

    $('#login').on('click', function() {
        buttonVisible($('.addbtn'));
        buttonVisible($('.viewbtn'));
        buttonVisible($('.viewclndrbtn'));
        buttonVisible($('.settingsbtn'));
    });

    $('#dayviewpoint').on('click', function() {
        updateTable('day');
    });

    $('#weekviewpoint').on('click', function() {
        updateTable('week');
    });

    $('#monthviewpoint').on('click', function() {
        updateTable('month');
    });

    $('#dayviewpoint_block').on('click', function() {
        updateTable('day');
    });

    $('#weekviewpoint_block').on('click', function() {
        updateTable('week');
    });

    $('#monthviewpoint_block').on('click', function() {
        updateTable('month');
    });

    $('#Send').on('click', function(){
        addEvent();
    });

    $('#Amend').on('click', function(){
        $('#modal_edit').modal('close');
        amendEventById(idToAmend);
    });

    $('.validate').keyup(function() {
        var emptyAdd = false;
        var emptyAmend = false;

        $('.amend-input input').each(function() {
            if ($(this).val().length == 0) {
                emptyAmend = true;
            }
        });
        $('.amend-input textarea').each(function() {
            if ($(this).val().length == 0) {
                emptyAmend = true;
            }
        });

        $('.add-input input').each(function() {
            if ($(this).val().length == 0) {
                emptyAdd = true;
            }
        });
        $('.add-input textarea').each(function() {
            if ($(this).val().length == 0) {
                emptyAdd = true;
            }
        });

        if (emptyAmend) {
            $('#Amend').prop('disabled', true);
        } else {
            $('#Amend').prop('disabled', false);
        }

        if (emptyAdd) {
            $('#Send').prop('disabled', true);
        } else {
            $('#Send').prop('disabled', false);
        }
    });

    $('#InputTime').keypress(function( event ) {
        event.preventDefault();
    });

    //костыль tab-pane
    $('#buttonprofile').on('click', function() {
        $('#panel_settings_drivers').attr('style', 'display: none');
        $('#panel_settings_cars').attr('style', 'display: none');
        $('#panel_settings_profile').attr('style', 'display: block');
    });

    $('#buttondrivers').on('click', function() {
        $('#panel_settings_drivers').attr('style', 'display: block');
        $('#panel_settings_cars').attr('style', 'display: none');
        $('#panel_settings_profile').attr('style', 'display: none');
    });

    $('#buttoncars').on('click', function() {
        $('#panel_settings_drivers').attr('style', 'display: none');
        $('#panel_settings_cars').attr('style', 'display: block');
        $('#panel_settings_profile').attr('style', 'display: none');
    });

    $('#viewcalendar,#viewcalendar_block,#settings,#settings_block').on('click', function() {
        $('.hidebtn').attr('style', 'visibility: hidden');
    });

    $('#view,#view_block').on('click', function(){
        $('.hidebtn').attr('style', 'visibility: visible');
    });

    $(window).resize(function () {
        toggleTables();
        toggleCalendarHeight();
        setTimeout(function () {
            toggleCalendarHeight();
        }, 100);
    });
});