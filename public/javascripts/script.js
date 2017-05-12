/**
 * Created by Miguel on 04/05/2017.
 */

function diminuirTres(event) {
    var format = moment.utc(event).format();
    format = format.toString();

    var date = format.substr(11,13);
    if (format == 'Invalid date') {
        date = 0;
    } else {
        date = parseInt(date) - 3;
    }
    console.log(date)
    if(date < 0 ){
        date = date + 24;
    }
    if(date < 10) {
        date = "0" + date.toString();
    }

    console.log(date)
    format = format.substr(0,11) + date + format.substr(13, format.length);
    console.log(format)

    return format;
}

function formatarUTC(event) {
    var format = moment.utc(event).format();
    var format = format.toString();
    format = diminuirTres(format);
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
        eventDrop: function (event, delta, revertFunc) {
            if (!confirm("Deseja mudar?")) {
                revertFunc();
            } else {
                jsRoutes.controllers.CalendarioController.update().ajax({
                    data : JSON.stringify([{
                        'id' : event.id,
                        'title' : event.title,
                        'start' : diminuirTres(event.start),
                        'end' : diminuirTres(event.end),
                        'color' : event.color
                    }]),
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
