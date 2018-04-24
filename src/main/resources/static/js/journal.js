$(document).ready(function () {
    $(function () {
        $('#datetimepicker').datetimepicker({
            locale: 'ru'
        });
    });
    $('.btn-block a').on('click', function () {
        $('.navbar-toggle').click()
    });
    $("#tableId tr").click(function() {
        confirm("Отправить СМС клиенту " + $(this).children(".name").html() + "?");
    });
});