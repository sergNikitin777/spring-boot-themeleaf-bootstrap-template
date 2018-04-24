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
        var confirmed = confirm("Отправить СМС клиенту " + $(this).children(".name").html() + "?")
        if(confirmed) {
            $(this).removeClass('danger');
            $(this).addClass('success');
        }
        else {
            $(this).removeClass('success');
            $(this).addClass('danger');
        }
    });
});