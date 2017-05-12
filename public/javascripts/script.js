/**
 * Created by Miguel on 04/05/2017.
 */
function formatarUTC(event) {
    var format = moment.utc(event).format();
    format = format.toString();
    format = format.substr(0, (format.length - 1));
    return format;
}

$(document).ready(function () {
    $('#modalCalendar').hide();
    $('#calendar').fullCalendar({
        header: {
            left: 'prev,next today',
            center: 'month,listWeek,listDay',
            right: 'title'
        },

        // customize the button names,
        // otherwise they'd all just say "list"
        views: {
            listDay: {buttonText: 'Dia'},
            listWeek: {buttonText: 'Semana'},
        },
        handleWindowResize: false,
        defaultView: 'month',
        defaultDate: '2017-05-12',
        navLinks: true, // can click day/week names to navigate views
        editable: true,
        eventLimit: true, // allow "more" link when too many events
        events: '/json',
        timezone: 'local',
        ignoreTimezone: true,
        eventDrop: function (event, delta, revertFunc) {
            if (!confirm("Deseja mudar?")) {
                revertFunc();
            } else {
                jsRoutes.controllers.CalendarioController.update().ajax({
                    data : JSON.stringify(event),
                    contentType : 'application/json'
                });
            }

        },

        eventClick: function (event, jsEvent, view) {
            $('#modalCalendar').show();
            $('#title').val(event.title);
            //console.log(formatarUTC(event.start));
            var start = formatarUTC(event.start),
                end = formatarUTC(event.end);

            $('#start').val(start);
            $('#end').val(end);

        }

    });
    $('#calendar').find('.fc-view-container').addClass('col-sm');
    $('#btn-close').on('click', function () {
        $('#modalCalendar').hide();
    });
});
