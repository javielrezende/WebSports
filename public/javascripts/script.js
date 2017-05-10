/**
 * Created by Miguel on 04/05/2017.
 */
$(document).ready(function() {
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
        events: '/json'

    });
    $('#calendar').find('.fc-view-container').addClass('col-sm');



});
