$(document).ready(function () {
    var options = { year: 'numeric', month: 'long', day: 'numeric', timeZone: '' };
    var xhrCalendarEvents = new XMLHttpRequest();
    var eventList = new Map();
    updateTable();

    function updateTable () {
        xhrCalendarEvents.open('POST', 'calendar/eventsbyparam', true);
        xhrCalendarEvents.setRequestHeader("Content-type", "application/json");
        xhrCalendarEvents.onreadystatechange = function () {
            if (xhrCalendarEvents.readyState == XMLHttpRequest.DONE && xhrCalendarEvents.status == 200) {
                eventList = JSON.parse(xhrCalendarEvents.responseText);
                eventList = eventList.sort(function (a, b) {
                    return a.startDate.date > b.startDate.date;
                });

                for (var i = 0; i < eventList.length; i++) {
                    if (eventList[i].description != null) {
                        var index = i + 1;
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
                            timeZone: eventList[i].startDate.timeZone,
                            hour: 'numeric', minute: 'numeric'
                        });
                        var description = ('' + eventList[i].description.value).split(';');

                        row.append('<th scope = "row">' + index + '</th>');
                        row.append('<td class = "datetime">' + dtLocalized + '</td>');
                        row.append('<td class = "name">' + eventList[i].summary.value + '</td>');
                        row.append('<td class = "location">' + eventList[i].location.value + '</td>');
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
            locale: 'ru'
        });
    });

    $('.navbtn').on('click', function () {
        $('.navbar-toggle').click();
    });
    $('.btn').on('click', function () {
        $(this).blur();
    });
    $('.addbtn').on('click', function () {
        $('#refresh').hide();
        $('#refresh_block').hide();
        $('#report').hide();
        $('#report_block').hide();
    });
    $('.viewbtn').on('click', function () {
        $('#refresh').show();
        $('#refresh_block').show();
        $('#report').show();
        $('#report_block').show();
    });

    $('#refresh').on('click', function () {
        $("#tablebody").html("");
        updateTable ();
    });
    $('#refresh_block').on('click', function () {
        $("#tablebody").html("");
        updateTable ();
    });
});