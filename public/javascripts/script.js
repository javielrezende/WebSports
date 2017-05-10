/**
 * Created by Miguel on 04/05/2017.
 */
$(document).ready(function() {
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
            listDay: { buttonText: 'Dia' },
            listWeek: { buttonText: 'Semana' },
        },
        handleWindowResize: false,
        defaultView: 'month',
        defaultDate: '2017-05-12',
        navLinks: true, // can click day/week names to navigate views
        editable: true,
        eventLimit: true, // allow "more" link when too many events
        events: '/json',
        eventClick: function (event, jsEvent, view) {
            $('#modalCalendar').show();
            $('#title').val(event.title);
            console.log(moment(event.start, moment.ISO_8601).format());
            var a = moment.utc(event.start).format(),
                b = moment.utc(event.end).format();
            a = a.toString();
            a = a.substr(0,(a.length - 1));
            b = b.toString();
            b = b.substr(0,(b.length - 1));

            $('#start').val(a);
            $('#end').val(b);

        }

    });
    $('#calendar').find('.fc-view-container').addClass('col-sm');
    $('#btn-close').on('click', function () {
       $('#modalCalendar').hide();
    });
});
