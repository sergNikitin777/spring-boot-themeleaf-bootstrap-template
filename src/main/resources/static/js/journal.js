$(document).ready(function () {
    var options                     = { year: 'numeric', month: 'long', day: 'numeric', timeZone: '' };
    var xhrCalendarActualEvents     = new XMLHttpRequest();
    var xhrCalendarRemovedEvents    = new XMLHttpRequest();
    var xhrAddEvent                 = new XMLHttpRequest();
    var xhrDeleteEvent              = new XMLHttpRequest();
    var eventList                   = new Map();
    var eventListR                  = new Map();
    var currentDate                 = new Date();
    var idToAmend;
    var year                        = currentDate.getFullYear();
    var month                       = currentDate.getMonth() + 1;
    var day                         = currentDate.getDate();
    $('#calendar').attr("src", "https://calendar.yandex.ru/day?uid=641146316&show_date="+year+"-"+month+"-"+day+"&embed=&private_token=cf54ec9c0ec6b90948d04f8d9ddc71462918b926&tz_id=Asia%2FYekaterinburg");

    toggleCalendarHeight();
    updateTable("month");

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
        var location            = $('#InputAddressamend').val() + ', ' + $('#InputCityamend').val();
        var description         = 'Водитель ' + $('#InputDriveramend').val() + '; ' + $('#InputCaramend').val() + '; ' +
            $('#InputGofersamend').val() + ' грузчик(ов); ' + $('#descriptionamend').val();

        postJSON.startDate = dateandtimeLocal;
        postJSON.durationHours = 1;
        postJSON.durationMinutes = 0;
        postJSON.eventName = summary;
        postJSON.eventDescription = description;
        postJSON.eventLocation = location;

        xhrAddEvent.open('POST', '/calendar/addvevent', true);
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
        var location            = $('#InputAddress').val() + ', ' + $('#InputCity').val();
        var description         = 'Водитель ' + $('#InputDriver').val() + '; ' + $('#InputCar').val() + '; ' +
            $('#InputGofers').val() + ' грузчик(ов); ' + $('#description').val();

        postJSON.startDate = dateandtimeLocal;
        postJSON.durationHours = 1;
        postJSON.durationMinutes = 0;
        postJSON.eventName = summary;
        postJSON.eventDescription = description;
        postJSON.eventLocation = location;

        xhrAddEvent.open('POST', '/calendar/addvevent', true);
        xhrAddEvent.setRequestHeader("Content-type", "application/json");
        xhrAddEvent.send(JSON.stringify(postJSON));
        xhrAddEvent.onreadystatechange = function () {
            if (xhrAddEvent.readyState == XMLHttpRequest.DONE && xhrAddEvent.status == 200) {
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
                xhrAddEvent.open('POST', '/calendar/addvevent', true);
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
                xhrAddEvent.open('POST', '/calendar/addvevent', true);
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
        xhrDeleteEvent.open('POST', '/calendar/delvevent', true);
        xhrDeleteEvent.setRequestHeader("Content-type", "application/json");
        xhrDeleteEvent.send(JSON.stringify(postJSON));
        xhrDeleteEvent.onreadystatechange = function () {
            if (xhrDeleteEvent.readyState == XMLHttpRequest.DONE && xhrDeleteEvent.status == 200) {
                updateTable('month');
            }
        };
    }

    function updateTable (viewpoint) {
        $("#tablebody").html("");
        $("#tableId").faLoading({icon: "fa-refresh", spin: true});
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
                    return a.startDate.date < b.startDate.date;
                });

                for (var i = 0; i < eventList.length; i++) {
                    if ((eventList[i].description != null) && (new Date(eventList[i].startDate.date) < limitms) && (new Date(eventList[i].startDate.date) > currentDate)) {
                        var row = $('<tr class="normal" id="' + eventList[i].uid.value + '">');
                        var dtLocalized = new Date(eventList[i].startDate.date).toLocaleString('ru-RU', {
                            year: 'numeric', month: 'long', day: 'numeric',
                            hour: 'numeric', minute: 'numeric'
                        });
                        var description = ('' + eventList[i].description.value).split(';');
                        var locationvalue = '';
                        if (eventList[i].location == null) locationvalue = 'Не указан';
                        else locationvalue = eventList[i].location.value;

                        row.append('<td class = "datetime">' + dtLocalized + '</td>');
                        row.append('<td class = "name">' + eventList[i].summary.value + '</td>');
                        row.append('<td class = "location">' + locationvalue + '</td>');
                        row.append('<td class = "driver">' + description[0].split(' ')[1] + '</td>');
                        row.append('<td class = "car">' + description[1] + '</td>');
                        row.append('<td class = "gofers">' + description[2].split(' ')[1] + '</td>');
                        row.append('<td class = "description">' + description[3] + '</td>');
                        row.append('<td class = "buttons"><a class="btn btn-sm edit"><i class="fa fa-edit"></i></a>&nbsp;' +
                            '<a class="btn btn-sm remove"><i class="fa fa-trash-o"></i></a></td>');

                        $("#tablebody").append(row);
                    }
                }
                xhrCalendarRemovedEvents.open('POST', 'calendar/eventsbyparam', true);
                xhrCalendarRemovedEvents.setRequestHeader("Content-type", "application/json");
                xhrCalendarRemovedEvents.onreadystatechange = function () {
                    if (xhrCalendarRemovedEvents.readyState == XMLHttpRequest.DONE && xhrCalendarRemovedEvents.status == 200) {
                        eventListR = JSON.parse(xhrCalendarRemovedEvents.responseText);
                        eventListR = eventListR.sort(function (a, b) {
                            return a.startDate.date < b.startDate.date;
                        });

                        for (var i = 0; i < eventListR.length; i++) {
                            if ((eventListR[i].description != null) && (new Date(eventListR[i].startDate.date) < limitms) && (new Date(eventListR[i].startDate.date) > currentDate)) {
                                var row = $('<tr class="strikeout" id="' + eventListR[i].uid.value + '">');
                                var dtLocalized = new Date(eventListR[i].startDate.date).toLocaleString('ru-RU', {
                                    year: 'numeric', month: 'long', day: 'numeric',
                                    hour: 'numeric', minute: 'numeric'
                                });
                                var description = ('' + eventListR[i].description.value).split(';');
                                var locationvalue = '';
                                if (eventListR[i].location == null) locationvalue = 'Не указан';
                                else locationvalue = eventListR[i].location.value;

                                row.append('<td class = "datetime">' + dtLocalized + '</td>');
                                row.append('<td class = "name">' + eventListR[i].summary.value + '</td>');
                                row.append('<td class = "location">' + locationvalue + '</td>');
                                row.append('<td class = "driver">' + description[0].split(' ')[1] + '</td>');
                                row.append('<td class = "car">' + description[1] + '</td>');
                                row.append('<td class = "gofers">' + description[2].split(' ')[1] + '</td>');
                                row.append('<td class = "description">' + description[3] + '</td>');
                                row.append('<td class = "buttons"><a class="btn btn-sm rebuild"><i class="fa fa-undo"></i></a>&nbsp;' +
                                    '<a class="btn btn-sm btn-danger delete" style="background-color: red"><i class="fa fa-trash-o"></i></a></td>');

                                $("#tablebody").append(row);
                            }
                        }
                        $('#tableId').faLoading(false);
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

    $(function () {
        var $address = $('#InputAddress');
        var $addressamend = $('#InputAddressamend');
        $address.kladr({
            oneString: true
        });
        $addressamend.kladr({
            oneString: true
        });
    });

    $(function () {
        $('#datetimepicker').datetimepicker({
            locale: 'ru',
            sideBySide: true
        });
    });

    $(function () {
        $('#datetimepickeramend').datetimepicker({
            locale: 'ru',
            sideBySide: true
        });
    });

    // Пользовательские события
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
        $('#panel4').modal("open");
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
                    $('#InputCityamend').val(eventList[i].location.value.split(',')[2]);
                    $('#InputAddressamend').val(eventList[i].location.value.split(',')[0] + ', ' + eventList[i].location.value.split(',')[1]);
                }
                $('#InputDriveramend').val(description[0].split(' ')[1]);
                $('#InputCaramend').val(description[1]);
                $('#InputGofersamend').val(description[2].split(' ')[1]);
                $('#descriptionamend').val(description[3]);
            }
        }
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
        $('#panel4').modal('close');
        amendEventById(idToAmend);
    });

    $('#InputTime').keypress(function( event ) {
        event.preventDefault();
    });

    $('#viewcalendar,#viewcalendar_block,#settings,#settings_block').on('click', function() {
        $('.hidebtn').attr('style', 'visibility: hidden');
    });

    $('#view,#view_block').on('click', function(){
        $('.hidebtn').attr('style', 'visibility: visible');
    });

    $(window).resize(function () {
        toggleCalendarHeight(); // потом при каждом растяжении окна
        setTimeout(function () {
            toggleCalendarHeight();
        }, 100); //для медлительных дёргаем дважды
    });
});