$(document).ready(function () {
    $(function () {
        $('#datetimepicker').datetimepicker({
            locale: 'ru'
        });
    });
    $('.navbtn a').on('click', function () {
        $('.navbar-toggle').click()
    });
    $('.btn-block').on('click', function () {
        $(this).blur();
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