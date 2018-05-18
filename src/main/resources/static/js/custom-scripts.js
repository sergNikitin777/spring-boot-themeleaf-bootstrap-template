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
            {name: 'number', label : '№', align: 'center'},
            {name: 'ready', label : 'Готовность', align: 'center'},
            {name: 'description', label : 'Описание', align: 'center'},
            {name: 'status', label : 'Статус', align: 'center'},
            {name: 'start', label : 'Начало', align: 'center'},
            {name: 'deadline', label : 'Крайний срок', align: 'center'},
            {name: 'equipment', label : 'Оборудование', align: 'center'},
            {name: 'address', label : 'Адрес', align: 'center'},
            {name: 'customer', label : 'Заказчик', align: 'center'},
            {name: 'performer', label : 'Исполнитель', align: 'center'}
        ],
        caption: 'Список задач',
        height: 'auto',
        rowNum: 5,
        // styleUI : 'Bootstrap',
        pager: '#pager',
        align: 'center',
        autowidth: true

    });


});