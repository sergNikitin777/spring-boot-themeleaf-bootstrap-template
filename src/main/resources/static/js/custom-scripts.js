$(document).ready(function () {
    $('[data-toggle=offcanvas]').click(function () {
        $('.row-offcanvas').toggleClass('active');
    });

    $( ".column" ).sortable({
        connectWith: ".column",
        handle: ".portlet-header",
        cancel: ".portlet-toggle",
        placeholder: "portlet-placeholder ui-corner-all"
    });

    $( ".portlet" )
        .addClass( "ui-widget ui-widget-content ui-helper-clearfix ui-corner-all" )
        .find( ".portlet-header" )
        .addClass( "ui-widget-header ui-corner-all" )
        .prepend( "<span class='ui-icon ui-icon-minusthick portlet-toggle'></span>");

    $( ".portlet-toggle" ).on( "click", function() {
        var icon = $( this );
        icon.toggleClass( "ui-icon-minusthick ui-icon-plusthick" );
        icon.closest( ".portlet" ).find( ".portlet-content" ).toggle();
    });

    //Определяем рабочую область для фрейма Рабочий стол
    var footerTop = $('#footerid').position().top;
    var monitor1Top = $('#monitor1').position().top;
    var footerHeight = $('#footerid').height();
    $('#monitor1').css('height', footerTop - monitor1Top - footerHeight);


    $(".dragman").draggable();
    $(".dragman").resizable();


    //Грид
    var dataArray = [
        {number: '1', ready: 'В работе', description: 'ТО-1', status: '5', start: '01.01.2001', deadline: '02.03.2001', equipment: 'СРУ БАО-600', address: 'г.Пермь, ул. Ленина, 88',customer: 'Иванов И.И.',performer: 'Сидоров А.А.'},
        {number: '2', ready: 'Ожидается', description: 'ТО-2', status: '3', start: '01.01.2002', deadline: '02.03.2002', equipment: 'СРУ БАО-600', address: 'г.Пермь, ул. Ленина, 88',customer: 'Иванов И.И.',performer: 'Сидоров А.А.'},
        {number: '3', ready: 'Готово', description: 'ТО-1', status: '5', start: '01.01.2001', deadline: '02.03.2001', equipment: 'СРУ БАО-600', address: 'г.Пермь, ул. Ленина, 88',customer: 'Иванов И.И.',performer: 'Сидоров А.А.'},
        {number: '4', ready: 'В работе', description: 'ТО-3', status: '5', start: '01.01.2001', deadline: '02.03.2001', equipment: 'СРУ БАО-600', address: 'г.Пермь, ул. Ленина, 88',customer: 'Иванов И.И.',performer: 'Сидоров А.А.'},
        {number: '5', ready: 'В работе', description: 'ТО-1', status: '5', start: '01.01.2001', deadline: '02.03.2001', equipment: 'СРУ БАО-600', address: 'г.Пермь, ул. Ленина, 88',customer: 'Иванов И.И.',performer: 'Сидоров А.А.'}
    ];

    $("#jqGrid").jqGrid({
        datatype: 'local',
        data: dataArray,
        colModel: [
            {name: 'number', label : '№', align: 'center', editable: true},
            {name: 'ready', label : 'Готовность', align: 'center', editable: true},
            {name: 'description', label : 'Описание', align: 'center', editable: true},
            {name: 'status', label : 'Статус', align: 'center', editable: true},
            {name: 'start', label : 'Начало', align: 'center', editable: true},
            {name: 'deadline', label : 'Крайний срок', align: 'center', editable: true},
            {name: 'equipment', label : 'Оборудование', align: 'center', editable: true},
            {name: 'address', label : 'Адрес', align: 'center', editable: true},
            {name: 'customer', label : 'Заказчик', align: 'center', editable: true},
            {name: 'performer', label : 'Исполнитель', align: 'center', editable: true}
        ],
        caption: 'Список задач',
        height: 'auto',
        rowNum: 5,
        pager: '#pager',
        align: 'center',
        autowidth: true

    });

    $('#jqGrid').navGrid("#pager", {
            search: true, // show search button on the toolbar
            add: true,
            edit: true,
            del: false,
            refresh: true
        },
        {
            html5Check :  true,
            editCaption: "Изменить задачу",
            recreateForm: true,
            //checkOnUpdate : true,
            //checkOnSubmit : true,
            closeAfterEdit: true,
            errorTextFormat: function (data) {
                return 'Error: ' + data.responseText
            }
        }, // edit options
        {
            closeAfterAdd: true,
            html5Check : true,
            recreateForm: true,
            errorTextFormat: function (data) {
                return 'Error: ' + data.responseText
            }
        }, // add options
        {}, // delete options
        {
            multipleSearch: true,
            multipleGroup : true
        }
    );


});