$(document).ready(function () {
    var options              = { year: 'numeric', month: 'long', day: 'numeric', timeZone: '' };
    var xhrCalendarEvents    = new XMLHttpRequest();
    var xhrAddEvent          = new XMLHttpRequest();
    var xhrTimeZone          = new XMLHttpRequest();
    var eventList            = new Map();
    var tZ                   = new Map();
    var currentDate          = new Date();
    var year                 = currentDate.getFullYear();
    var month                = currentDate.getMonth() + 1;
    var day                  = currentDate.getDate();
    $('#calendar').attr("src", "https://calendar.yandex.ru/day?uid=641146316&show_date="+year+"-"+month+"-"+day+"&embed=&private_token=cf54ec9c0ec6b90948d04f8d9ddc71462918b926&tz_id=Asia%2FYekaterinburg");

    toggleCalendarHeight();
    updateTable("month");

    function toggleCalendarHeight() {
        var screenHeight = document.documentElement.clientHeight
            - 60 +  "px";
        document.getElementById("calendar").style.height = screenHeight;
    }

    function getUID() {
        return "ne" + Math.random().toString(36).replace(/[^a-z]+/g, '').substr(0, 5) + "yandex.ru";
    }

    function getUTC(date) {
        var dateUTC = '';
        dateUTC += date.getUTCFullYear();
        if (date.getUTCMonth() < 10) dateUTC += '0' + date.getUTCMonth();
        else dateUTC += date.getUTCMonth();
        if (date.getUTCDate() < 10) dateUTC += '0' + date.getUTCDate();
        else dateUTC += date.getUTCDate();
        dateUTC += 'T';
        if (date.getUTCHours() < 10) dateUTC += '0' + date.getUTCHours();
        else dateUTC += date.getUTCHours();
        if (date.getUTCMinutes() < 10) dateUTC += '0' + date.getUTCMinutes();
        else dateUTC += date.getUTCMinutes();
        if (date.getUTCSeconds() < 10) dateUTC += '0' + date.getUTCSeconds();
        else dateUTC += date.getUTCSeconds();
        return dateUTC;
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

        console.log(JSON.stringify(postJSON));
        xhrAddEvent.open('POST', '/calendar/addvevent', true);
        xhrAddEvent.setRequestHeader("Content-type", "application/json");
        xhrAddEvent.send(JSON.stringify(postJSON));
        xhrAddEvent.onreadystatechange = function () {
            if (xhrAddEvent.readyState == XMLHttpRequest.DONE && xhrAddEvent.status == 200) {
                console.log(xhrAddEvent.responseText);
                updateTable('month');
            }
        };
    }

    function updateTable (viewpoint) {
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
        xhrCalendarEvents.open('POST', 'calendar/eventsbyparam', true);
        xhrCalendarEvents.setRequestHeader("Content-type", "application/json");
        xhrCalendarEvents.onreadystatechange = function () {
            if (xhrCalendarEvents.readyState == XMLHttpRequest.DONE && xhrCalendarEvents.status == 200) {
                eventList = JSON.parse(xhrCalendarEvents.responseText);
                eventList = eventList.sort(function (a, b) {
                    return a.startDate.date > b.startDate.date;
                });

                for (var i = 0; i < eventList.length; i++) {
                    if ((eventList[i].description != null) && (new Date(eventList[i].startDate.date) < limitms) && (new Date(eventList[i].startDate.date) > currentDate)) {
                        var row = $('<tr class="clickrow">');
                        row.on('click', function() {
                            var confirmed = confirm("Отправить СМС клиенту " + $(this).children(".name").html() + "?");
                            if(confirmed) {
                                $(this).removeClass('danger');
                                $(this).addClass('success');
                            }
                            else {
                                $(this).removeClass('success');
                                $(this).addClass('danger');
                            }
                        });
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

                        $("#tablebody").append(row);
                    }
                }
            }
        };

        xhrCalendarEvents.send(JSON.stringify({
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
        $('#datetimepicker').datetimepicker({
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

    $('#dayviewpoint').on('click', function() {
        $("#tablebody").html("");
        updateTable('day');
    });

    $('#weekviewpoint').on('click', function() {
        $("#tablebody").html("");
        updateTable('week');
    });

    $('#monthviewpoint').on('click', function() {
        $("#tablebody").html("");
        updateTable('month');
    });

    $('#dayviewpoint_block').on('click', function() {
        $("#tablebody").html("");
        updateTable('day');
    });

    $('#weekviewpoint_block').on('click', function() {
        $("#tablebody").html("");
        updateTable('week');
    });

    $('#monthviewpoint_block').on('click', function() {
        $("#tablebody").html("");
        updateTable('month');
    });

    $('#Send').on('click', function(){
        addEvent();
    });

    $('#InputTime').keypress(function( event ) {
        event.preventDefault();
    });

    $('#add,#add_block,#viewcalendar,#viewcalendar_block,#settings,#settings_block').on('click', function() {
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